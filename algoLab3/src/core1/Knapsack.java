package core1;

import java.util.Scanner;
import core1.WrongInputsException;

public class Knapsack {
	private final static int CELLSIZE = 6;//Variables to print the table
	private final static int EMPTYCELLSIZE = 8;

	  /**
     * It creates the knapsack table and return the best result
     * @param W: the maximum weight
     * @param wt: the array of different weights
     * @param val: the array of different values
     * @param n: number of different values
     * @param ask: If it is true it ask for the user interaction to print the table
     * @return the biggest possible value to be stored in the knapsack
	 * @throws WrongInputsException
     */
	public static int knapSack(int W, int wt[], int val[], int n, boolean ask) throws WrongInputsException{
	    /*				I create the table
	 	The columns represent the maximum allowed weight
	 	The rows represent the  maximum allowed value (for example,
	 	if we are in value 3, we can use the value number 0,..,3   */
		if(wt.length != val.length) {
			throw new WrongInputsException();
		}

	     int table [][] = new int[n][W+1];

	     populateTable(table, W, wt, val, n);
	     if(ask) {printTable(table, W, wt, val, n);}

	     if(table.length > 0) {return table[n-1][W];}//I check if there is any element
	     else {return 0;}
	    }

	/**
	 * It makes all the calculations to populate the table
	 * @param table: the knapsack table
	 * @param W: the maximum weight
     * @param wt: the array of different weights
     * @param val: the array of different values
     * @param n: number of different values
	 */
	private static void populateTable(int[][] table, int W, int[] wt, int[] val, int n) {
	     int availableWeight, previousOptimum;//Auxiliary for the loop

		 for (int i = 0; i < n; i++){//Rows -> the values available
	         for (int j = 0; j <= W; j++) {//Columns -> the maximum weight
	             if (wt[i]  <= j){//I check if the weight I am going to obtain of the weight array is bigger than the actual maximum weight
	            	 if(i == 0) {
	            		 table[i][j] = val[i];
	            	 }
	            	 else {
	            		 availableWeight = j-wt[i];//It is the weight that would be available using the weight of the weight array
		            	 previousOptimum = table[i-1][availableWeight];//It is the optimum solution previously calculated using the availableWeight
		            	 table[i][j] = max(val[i] + previousOptimum,  table[i-1][j]);//I calculate the optimum solution between using the new item or not
	            	 }
	             }
	             else{//As the weight that I should use of the weight array is bigger than the maximum weight, I copy the previous result
	            	 if(j == 0 || i == 0) {table[i][j] = 0;}
	            	 else{table[i][j] = table[i-1][j];}
	             }
	         }
	      }
	 }

	/**
	 * It returns the max of two numbers
	 * @param a: They are the numbers to be compared
	 * @param b
	 * @return the max value of both numbers
	 */
    private static int max(int a, int b) {
    	if(a > b) { return a;}
    	else { return b;}
    }

	 /**
	  * It prints the table if the user wants to
      * @param W: the maximum weight
      * @param wt: the array of different weights
      * @param val: the array of different values
      * @param n: number of different values
	  * @param table: the knapsack table
	  */
	private static void printTable(int[][] table, int W, int[] wt, int[] val, int n) {
		if(userWants()) {//The user wants to print the table
			printKnapsackTable(table, W, wt, val, n);
		}
	}

	/**
	 * It ask to the user if he wants to print the table
	 * @return
	 */
	private static boolean userWants() {
		Scanner reader = new Scanner(System.in);// Reading from System.in
		System.out.println("Do you want to print the knapsack table? (Yes/No)");
		String answer = reader.next();
		reader.close();
		if(answer.equals("Yes")) {return true;}
		else {return false;}
	}

	/**
	 * It prints the knapsack table
	 * @param table: the knapsack table
     * @param W: the maximum weight
     * @param wt: the array of different weights
     * @param val: the array of different values
     * @param n: number of different values
	 */
	private static void printKnapsackTable(int[][] table, int W, int[] wt, int[] val, int n) {
		String output = "";

		output = headerColumns(output, W);
		output = rows(table, output, W, wt, val, n);

		System.out.println(output);//I print the table
	}


	/**
	 * It prints the header of the columns
	 * @param output: the String that contains the table
     * @param W: the maximum weight
	 */
	private static String headerColumns(String output, int W) {
		for(int j = 0; j < EMPTYCELLSIZE-1; j++) {//The header of the first column is empty
			output += " ";
		}
		for(int i = 0; i <= W; i++) {
			if(i == 0) { output = copyNumber(output, 0);}
			else{output = copyNumber(output, i);}
		}
		return output += "\n\n";
	}

	/**
	 * It copy a number into a string
	 * @param output: the String that contains the table
	 * @param number: the number to be copied
	 */
	private static String copyNumber(String output, int number) {
		for(int j = 0; j < CELLSIZE; j++) {//I copy the number and the correct number of spaces to the String
			if(j == 2) {//I copy the number in the third position
				output += Integer.toString(number);
				j += String.valueOf(number).length();
			}
			else {
				output +=" ";
			}
		}
		return output;
	}


	/**
	 * It prints the rows of the table
	 * @param table: the knapsack table
	 * @param output: the String that contains the table
	 * @param W: the maximum weight
     * @param wt: the array of different weights
     * @param val: the array of different values
     * @param n: number of different values
	 * @return: the String that contains the table
	 */
	private static String rows(int[][] table, String output, int W, int[] wt, int[] val, int n) {
		for (int i = n-1; i >= 0; i--){//Rows -> the values available
	         for (int j = 0; j <= W; j++) {//Columns -> the maximum weight
	        	 if(j == 0) {
	        			 output = rowHeader(output, wt, val, i);
	        	 }
        		 output = copyNumber(output, table[i][j]);//I copy the value in the table
	         }
	         output += "\n\n";
		}
	    return output;
	}

	/**
	 * It prints the header row
	 * @param output: the String that contains the table
     * @param wt: the array of different weights
     * @param val: the array of different values
	 * @param i: the row
	 * @return: the String that contains the table
	 */
	private static String rowHeader(String output, int[] wt, int[] val, int i) {
		int aux = 1;
		 output += Integer.toString(wt[i]);
		 output +="(";
		 aux += String.valueOf(wt[i]).length() + 1;

		 output += Integer.toString(val[i]);
		 output +=")";
		 aux += String.valueOf(val[i]).length() + 1;
		 if(aux < EMPTYCELLSIZE) {
				for(int j = aux; j < EMPTYCELLSIZE; j++) {
					output += " ";
				}
		 }
		 return output;
	}

}
