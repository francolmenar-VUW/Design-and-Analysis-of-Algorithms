package completition1;

import java.util.ArrayList;

import core1.Item;

public class Knapsack {
	private static int max = 0;

	 /**
     * It calculates the best result of the knapsack problem by brute force
	 * @param elements: The list which will contain the elements
	 * @param repetition: the number of duplicated items that can be of each type
     * @param MaxWeight: the maximum weight
     * @param testMode: If it is true, nothing is printed in the screen
     * @return the biggest possible value to be stored in the knapsack
     */
	public static int knapSack(ArrayList<Item> elements, int[] repetition, int MaxWeight, boolean testMode){
		elements = createAllElements(elements, repetition);//I create all the elements	  

		return max;
	}

	/**
	 * I create the list with all the elements including the repetitions
	 * @param elements: The list which will contain the elements
	 * @param repetition: the number of duplicated items that can be of each type
	 * @return the list with all the elements
	 */
	private static ArrayList<Item> createAllElements(ArrayList<Item> elements, int[] repetition) {
		ArrayList<Item> allElements = new ArrayList<>();
		for(int i = 0; i < elements.size(); i++) {
			for(int j = 0; j < repetition[i]; j++) {
				allElements.add(elements.get(i));
			}
		}
		return allElements;
	}

}