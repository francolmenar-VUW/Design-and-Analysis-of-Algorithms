package completition1;

import java.util.ArrayList;
import java.util.Scanner;

import Exceptions.WrongInputsException;
import core1.Item;

public class Main {
	private static int MaxWeight = 0;
	private static final boolean ASK_USER = false;//It controls if the user is going to be asked for introduce the elements or not
	private static final boolean TEST_MODE = false;

	protected static final int DEFAULT_MAXIMUM_WEIGHT = 10;//Default values
	protected static final String [] DEFAULT_NAME = new String[]{"Diamond", "Gold", "Silver", "Platinum"};
	protected static final int [] DEFAULT_VALUE = new int[]{30, 14, 16, 9};
	protected static final int [] DEFAULT_WEIGHT = new int[]{6,3,4,2};
	protected static final int [] DEFAULT_REPETITION = new int[]{2,2,2,2};

	private static final int NUMBER_OF_PLOTS = 30;
	private static final int BASE_NUMBER_OF_ELEMENTS = 3;
	private static final int SUM = 1;
	private static final int DEFAULT_MAX_WEIGHT = 100;//For the timer


	/**
	 * It ask for the user to introduce the elements
	 * @param elements: The list which will contain the elements
     * @param MaxWeight: the maximum weight
	 * @return: the array with the repetitions
	 */
	private static int[] askUser(ArrayList<Item> elements) {
		int NumberOfValues = 0,value, weight;
		String name = "";
		Scanner reader = new Scanner(System.in);// Reading from System.in
		while(MaxWeight < 1) {//Ask about the weight
			System.out.println("What is the maximum weight of the bag?");
			MaxWeight = reader.nextInt();
		}
		while(NumberOfValues == 0) {//I check that the user introduces at least 1 element
			System.out.println("How many different elements do you want?");
			NumberOfValues = reader.nextInt();
		}
	    int repetition[] = new int [NumberOfValues];
		for(int i = 0; i < NumberOfValues; i++) {
			System.out.println("\t\tItem" + (i + 1) + "\n");
			System.out.println("Introduce the name of the Item ");
			name = reader.next();
			System.out.println("Introduce the value of " + name);
			value = reader.nextInt();
			System.out.println("Introduce the weight of " + name);
			weight = reader.nextInt();
			System.out.println("Introduce the number of " + name + " do you want to create ");
			repetition[i] = reader.nextInt();
			Item item = new Item(name, value, weight);
			elements.add(item);
		}
		reader.close();
		return repetition;
	}

	/**
	 * It sets the elements to a default values
	 * @param elements: The list which will contain the elements
     * @param MaxWeight: the maximum weight
	 * @return: the array with the repetitions
	 */
	protected static int[] defaultValues(ArrayList<Item> elements) {
		MaxWeight = DEFAULT_MAXIMUM_WEIGHT;
		for(int i = 0; i < DEFAULT_VALUE.length; i++) {
			Item item = new Item(DEFAULT_NAME[i], DEFAULT_VALUE[i], DEFAULT_WEIGHT[i]);//New item
			elements.add(item);//Item to the list
		}
	    return DEFAULT_REPETITION;
	}

	/**
	 * It performs a normal execution
	 * @param elements: The list which will contain the elements
	 * @param repetition: the number of duplicated items that can be of each type
     * @param MaxWeight: the maximum weight
	 */
	private static int normalExecution(ArrayList<Item> elements, int[] repetition, int MaxWeight) {
		return Knapsack.knapSack(elements, repetition,MaxWeight, TEST_MODE);
	}

	/**
	 * It calculates how much time does the algorithm long for different inputs
	 */
	@SuppressWarnings("unused")
	private static void runTimePlot(){
		ArrayList<Item> elements = new ArrayList<>();
		int aux;
		long duration [] = new long[NUMBER_OF_PLOTS], sum = 0;
		System.out.println("Number of elements\t\tTime(milliseconds)");
		for(int i = 0; i < NUMBER_OF_PLOTS; i++) {
			elements.clear();
			aux = BASE_NUMBER_OF_ELEMENTS + (SUM * i);//The total number of elements that we are going to create
		    int repetition [] = new int[aux];
		    fillList(elements, repetition, aux);//I fill the arrays
		    duration [i] = lessReliableTime(elements,repetition);
		    //duration [i] = moreReliableTime(elements, repetition);//I calculate the average time of executing the program with a certain amount of elements
			sum += duration[i];

		    System.out.println("\n" + aux + "\t\t\t\t" + duration[i]);
		}
		System.out.println("\n\nThe average time is " + (long) (sum/NUMBER_OF_PLOTS) + " millisecons with " + NUMBER_OF_PLOTS + " executions");
	}

	/**
	 * It fills the list with random numbers between 1 and 5
	 * @param elements: The list which will contain the elements
	 * @param repetition: the number of duplicated items that can be of each type
     * @param MaxWeight: the maximum weight
	 */
	private static void fillList(ArrayList<Item> elements, int[] repetition, int aux) {
		int randomNum, value, weight;
		for(int j = 0; j < aux; j++) {
			String name = "a";
			randomNum = 1 + (int)(Math.random() * 5);
			value = randomNum;
			randomNum = 1 + (int)(Math.random() * 5);
			weight = randomNum;
			randomNum = 1 + (int)(Math.random() * 5);
			repetition[j] = randomNum;
			name.concat(Integer.toString(j));//I modify the name of the element
			Item item = new Item(name, value, weight);
			elements.add(item);//Item to the list
		}
	}

	/**
	 * It executes the program 1 time and calculate the time of its execution
	 * @param elements: The list which will contain the elements
	 * @param repetition: the number of duplicated items that can be of each type
     * @param MaxWeight: the maximum weight
	 * @return the average time to execute the program
	 */
	@SuppressWarnings("unused")
	private static long lessReliableTime(ArrayList<Item> elements, int[] repetition){
		long startTime, endTime;
		startTime = System.nanoTime();//Calculate the time
		normalExecution(elements, repetition, DEFAULT_MAX_WEIGHT);
		endTime = System.nanoTime();
		return (long) (endTime - startTime) / 1000000;
	}

	/**
	 * It executes the program 30 times and calculate the average time of its execution
	 * Because of it, it is slower than the upper method
	 * @param elements: The list which will contain the elements
	 * @param repetition: the number of duplicated items that can be of each type
     * @param MaxWeight: the maximum weight
	 * @return the average time to execute the program
	 */
	@SuppressWarnings("unused")
	private static long moreReliableTime(ArrayList<Item> elements, int[] repetition){
		long auxTime [] = new long [30];
		long sum = 0, startTime, endTime;
		for(int i = 0; i < 30; i++) {
			startTime = System.nanoTime();//Calculate the time
			normalExecution(elements, repetition, DEFAULT_MAX_WEIGHT);
			endTime = System.nanoTime();
			auxTime [i] = (endTime - startTime) / 1000000;
			sum += auxTime[i];
		}
		return (long) (sum/30);
	}

	public static void main(String[] args) throws WrongInputsException {
		ArrayList<Item> elements = new ArrayList<>();
		int repetition [];
		if(ASK_USER) {
			repetition = askUser(elements);//I ask to the user to introduce the elements
		}
		else {
			repetition = defaultValues(elements);//I use a default values
		}

		/*		*** PRINT ***		*/
 		for(int i = 0; i < elements.size(); i++) {
			Item elem = elements.get(i);
			System.out.println("Name: " + elem.getName() + "\tValue: " + elem.getValue() +
					"\tWeight: " + elem.getWeight() + "\tRepetition: " + repetition[i] + "\n");
		}
		normalExecution(elements, repetition, MaxWeight);
	    //runTimePlot();
	}

}

