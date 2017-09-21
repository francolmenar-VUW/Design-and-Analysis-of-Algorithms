package core1;

import core1.Knapsack;


public class Main {

	private static final int NUMBER_OF_PLOTS = 30;
	private static final int BASE_NUMBER_OF_ELEMENTS = 10;
	private static final int SUM = 1000;
	private static final int DEFAULT_MAX_WEIGHT = 1000;


	/**
/	 * It performs a normal execution
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
	@SuppressWarnings("unused")
	private static void runTimePlot() throws WrongInputsException {
		int aux;
		long sum = 0;
		long duration [] = new long[NUMBER_OF_PLOTS];
		System.out.println("Number of elements\t\tTime(milliseconds)");
		for(int i = 0; i < NUMBER_OF_PLOTS; i++) {
			aux = BASE_NUMBER_OF_ELEMENTS + (SUM * i);//The total number of elements that we are going to create
			int val[] = new int[aux];//I create the arrays
		    int wt[] = new int[aux];

		    fillArray(val, wt, aux);//I fill the arrays
		    //duration [i] = lessReliableTime(wt,val);
		    duration [i] = moreReliableTime(wt, val);//I calculate the average time of executing the program with a certain amount of elements
			sum += duration[i];

		    System.out.println("\n" + aux + "\t\t\t\t" + duration[i]);
		}
		System.out.println("\n\nThe average time is " + (long) (sum/NUMBER_OF_PLOTS) + " millisecons with " + NUMBER_OF_PLOTS + " executions");
	}


	/**
	 * It fills the arrays with random numbers between 1 and 5
	 * @param val: the arrays
	 * @param wt
	 * @param aux:The index of the loop
	 */
	private static void fillArray(int[] val, int[] wt, int aux) {
		int randomNum;
		for(int j = 0; j < aux; j++) {
			randomNum = 1 + (int)(Math.random() * 5);
			val[j] = randomNum;
			randomNum = 1 + (int)(Math.random() * 5);
			wt[j] = randomNum;
		}
	}

	/**
	 * It executes the program 1 time and calculate the time of its execution
	 * @param wt: the array of different weights
     * @param val: the array of different values
	 * @return the average time to execute the program
	 * @throws WrongInputsException
	 */
	@SuppressWarnings("unused")
	private static long lessReliableTime(int[] wt, int[] val) throws WrongInputsException {
		long startTime, endTime;
		startTime = System.nanoTime();//Calculate the time
		normalExecution(DEFAULT_MAX_WEIGHT, wt, val, val.length, false);
		endTime = System.nanoTime();
		return (long) (endTime - startTime) / 1000000;
	}

	/**
	 * It executes the program 30 times and calculate the average time of its execution
	 * Because of it, it is slower than the upper method
	 * @param wt: the array of different weights
     * @param val: the array of different values
	 * @return the average time to execute the program
	 * @throws WrongInputsException
	 */
	private static long moreReliableTime(int[] wt, int[] val) throws WrongInputsException {
		long auxTime [] = new long [30];
		long sum = 0, startTime, endTime;
		for(int i = 0; i < 30; i++) {
			startTime = System.nanoTime();//Calculate the time
			normalExecution(DEFAULT_MAX_WEIGHT, wt, val, val.length, false);
			endTime = System.nanoTime();
			auxTime [i] = (endTime - startTime) / 1000000;
			sum += auxTime[i];
		}
		return (long) (sum/30);
	}

	public static void main(String[] args) throws WrongInputsException {
		int val[] = new int[]{60, 100, 120};
	    int wt[] = new int[]{10, 20, 30};
	    int  W = 50;
	    int n = val.length;
		System.out.println(normalExecution(W, wt, val, n, true));
	    //runTimePlot();
	}
}
