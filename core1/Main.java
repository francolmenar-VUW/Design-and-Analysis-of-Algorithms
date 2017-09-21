package core1;

import core1.Knapsack;


public class Main {

	private static final int NUMBER_OF_PLOTS = 10;
	private static final int BASE_NUMBER_OF_ELEMENTS = 10;
	private static final int SUM = 1000;
	private static final int DEFAULT_MAX_WEIGHT = 1000;


	/**
	 * It performs a normal execution
     * @param W: the maximum weight
     * @param wt: the array of different weights
     * @param val: the array of different values
     * @param n: number of different values
     * @param ask: If it is true it ask for the user interaction to print the table
	 * @throws WrongInputsException
	 */
	private static int normalExecution(int W, int[] wt, int[] val, int n, boolean b) throws WrongInputsException {
		return Knapsack.knapSack(W, wt, val, n, b);
	}

	/**
	 * It calculates how much time does the algorithm long for different inputs
	 * @throws WrongInputsException
	 */
	private static void runTimePlot() throws WrongInputsException {
		int aux, randomNum;
		long sum = 0;
		long duration [] = new long[NUMBER_OF_PLOTS];
		for(int i = 0; i < NUMBER_OF_PLOTS; i++) {
			aux = BASE_NUMBER_OF_ELEMENTS + (SUM * i);//The total number of elements that we are going to create
			int val[] = new int[aux];//I create the arrays
		    int wt[] = new int[aux];;
			for(int j = 0; j < aux; j++) {//I fill the arrays
				randomNum = 1 + (int)(Math.random() * 5);
				val[j] = randomNum;
				randomNum = 1 + (int)(Math.random() * 5);
				wt[j] = randomNum;
			}
			long startTime = System.nanoTime();
			normalExecution(DEFAULT_MAX_WEIGHT, wt, val, val.length, false);
			long endTime = System.nanoTime();
			duration [i] = (endTime - startTime) / 1000000;
			sum += duration[i];
		    System.out.println("\n\nThe time that it takes for the execution with " + aux + " values is " + duration[i] + " milliseconds");
		}
		System.out.println("\n\nThe average time is: " + (long) (sum/NUMBER_OF_PLOTS) + " millisecons");
	}

	public static void main(String[] args) throws WrongInputsException {
		int val[] = new int[]{60, 100, 120};
	    int wt[] = new int[]{10, 20, 30};
	    int  W = 50;
	    int n = val.length;
		//sysonormalExecution(W, wt, val, n, true);
	    runTimePlot();
	}
}
