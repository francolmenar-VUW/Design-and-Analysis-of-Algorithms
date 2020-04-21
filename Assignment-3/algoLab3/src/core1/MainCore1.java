package core1;

import java.util.ArrayList;

import Exceptions.WrongInputsException;
import core1.KnapsackCore1;
import core1.Item;

public class MainCore1 {
	private static int MaxWeight = 0;
	private static final boolean ASK_USER = false;//It controls if the user is going to be asked or not


	protected static final int DEFAULT_MAXIMUM_WEIGHT = 50;//Default values
	protected static final String [] DEFAULT_NAME = new String[]{"Diamond", "Gold", "Silver"};
	protected static final int [] DEFAULT_VALUE = new int[]{60, 100, 120};
	protected static final int [] DEFAULT_WEIGHT = new int[]{10, 20, 30};

	private static final int NUMBER_OF_PLOTS = 30;
	private static final int BASE_NUMBER_OF_ELEMENTS = 10;
	private static final int SUM = 500;
	private static final int DEFAULT_MAX_WEIGHT = 1000;


	/**
	 * It sets the elements to a default values
	 * @param elements: The list which will contain the elements
     * @param MaxWeight: the maximum weight
	 */
	protected static void defaultValues(ArrayList<Item> elements) {
		MaxWeight = DEFAULT_MAXIMUM_WEIGHT;
		for(int i = 0; i < DEFAULT_VALUE.length; i++) {
			Item item = new Item(DEFAULT_NAME[i], DEFAULT_VALUE[i], DEFAULT_WEIGHT[i]);//New item
			elements.add(item);//Item to the list
		}
	}

	/**
	 * It performs a normal execution
     * @param MaxWeight: the maximum weight
     * @param elements: The list which will contain the elements
     * @param ask: If it is true it ask for the user interaction to print the table
	 * @throws WrongInputsException
	 */
	private static int normalExecution(ArrayList<Item> elements, int MaxWeight, boolean b) throws WrongInputsException {
		return KnapsackCore1.knapSack(elements, MaxWeight, b);
	}

	/**
	 * It calculates how much time does the algorithm long for different inputs
	 * @throws WrongInputsException
	 */
	@SuppressWarnings("unused")
	private static void runTimePlot() throws WrongInputsException {
		ArrayList<Item> elements = new ArrayList<>();
		int aux;
		long duration [] = new long[NUMBER_OF_PLOTS], sum = 0;
		System.out.println("Number of elements\t\tTime(milliseconds)");
		for(int i = 0; i < NUMBER_OF_PLOTS; i++) {
			aux = BASE_NUMBER_OF_ELEMENTS + (SUM * i);//The total number of elements that we are going to create
		    fillList(elements, aux);//I fill the list
		    //duration [i] = lessReliableTime(elements);
		    duration [i] = moreReliableTime(elements);//I calculate the average time of executing the program with a certain amount of elements
			sum += duration[i];

		    System.out.println("\n" + aux + "\t\t\t\t" + duration[i]);
		}
		System.out.println("\n\nThe average time is " + (long) (sum/NUMBER_OF_PLOTS) + " millisecons with " + NUMBER_OF_PLOTS + " executions");
	}

	/**
	 * It fills the list with random numbers between 1 and 5
	 * @param elements: The list which will contain the elements
	 */
	private static void fillList(ArrayList<Item> elements, int aux) {
		int randomNum, value, weight;
		for(int j = 0; j < aux; j++) {
			String name = "a";
			randomNum = 1 + (int)(Math.random() * 5);
			value = randomNum;
			randomNum = 1 + (int)(Math.random() * 5);
			weight = randomNum;
			name.concat(Integer.toString(j));//I modify the name of the element
			Item item = new Item(name, value, weight);
			elements.add(item);//Item to the list
		}
	}

	/**
	 * It executes the program 1 time and calculate the time of its execution
	 * @param elements: The list which will contain the elements
	 * @return the average time to execute the program
	 * @throws WrongInputsException
	 */
	@SuppressWarnings("unused")
	private static long lessReliableTime(ArrayList<Item> elements) throws WrongInputsException {
		long startTime, endTime;
		startTime = System.nanoTime();//Calculate the time
		normalExecution( elements,DEFAULT_MAX_WEIGHT, false);
		endTime = System.nanoTime();
		return (long) (endTime - startTime) / 1000000;
	}

	/**
	 * It executes the program 30 times and calculate the average time of its execution
	 * Because of it, it is slower than the upper method
	 * @param elements: The list which will contain the elements
	 * @return the average time to execute the program
	 * @throws WrongInputsException
	 */
	private static long moreReliableTime(ArrayList<Item> elements) throws WrongInputsException {
		long auxTime [] = new long [30];
		long sum = 0, startTime, endTime;
		for(int i = 0; i < 30; i++) {
			startTime = System.nanoTime();//Calculate the time
			normalExecution( elements,DEFAULT_MAX_WEIGHT, false);
			endTime = System.nanoTime();
			auxTime [i] = (endTime - startTime) / 1000000;
			sum += auxTime[i];
		}
		return (long) (sum/30);
	}

	public static void main(String[] args) throws WrongInputsException {
		ArrayList<Item> elements = new ArrayList<>();
	    defaultValues(elements);//I use a default values
	    /*		*** PRINT ***		*/
 		for(int i = 0; i < elements.size(); i++) {
			Item elem = elements.get(i);
			System.out.println("Name: " + elem.getName() + "\tValue: " + elem.getValue() +
					"\tWeight: " + elem.getWeight() + "\n");
		}
		//System.out.println(normalExecution(elements, MaxWeight, ASK_USER));
	    runTimePlot();
	}
}
