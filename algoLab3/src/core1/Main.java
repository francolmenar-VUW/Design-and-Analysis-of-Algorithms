package core1;

import core1.Knapsack;


public class Main {

	public static void main(String[] args) throws WrongInputsException {
	 	int val[] = new int[]{60, 100, 120};
	    int wt[] = new int[]{10, 20, 30};
	    int  W = 50;
	    int n = val.length;
	    System.out.println(Knapsack.knapSack(W, wt, val, n, true));
	}
}
