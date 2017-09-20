package core1;

public class Main {
	//http://techieme.in/solving-01-knapsack-problem-using-dynamic-programming/

	/**
	 * It returns the max of two numbers
	 * @param a: They are the numbers to be compared
	 * @param b
	 * @return the max value of both numbers
	 */
    public static int max(int a, int b) {
    	if(a > b) { return a;}
    	else { return b;}
    }

    /**
     * It
     * @param W: the maximum weight
     * @param wt: the array of different weights
     * @param val: the array of different values
     * @param n: number of different values
     * @return the biggest possible value to be stored in the knapsack
     */
	 public static int knapSack(int W, int wt[], int val[], int n){
	    /*				I create the table:
	 	The columns represent the maximum allowed weight
	 	The rows represent the  maximum allowed value (for example,
	 	 if we are in value 3, we can use the value number 0,..,3 */

	     int K[][] = new int[n][W+1];

	     // Build table K[][] in up bottom manner
	     for (int i = 0; i <= n; i++){//Rows
	         for (int j = 0; j <= W; j++) {//Columns
	             if (i==0 || j==0){
	                  K[i][j] = 0;
	             }
	             else if (wt[i-1] <= j){
	                   K[i][j] = max(val[i-1] + K[i-1][j-wt[i-1]],  K[i-1][j]);
	             }
	             else{
	                   K[i][j] = K[i-1][j];
	             }
	         }
	      }
	      return K[n][W];
	    }

	public static void main(String[] args) {
	 	int val[] = new int[]{60, 100, 120};
	    int wt[] = new int[]{10, 20, 30};
	    int  W = 50;
	    int n = val.length;
	    System.out.println(knapSack(W, wt, val, n));
	}
}
