package core2;

import java.util.ArrayList;

import Exceptions.WrongInputsException;
import core1.Item;


public class KnapsackCore2 {
	private static int max = 0;

	 /**
     * It calculates the best result of the knapsack problem by brute force
	 * @param elements: The list which will contain the elements
	 * @param repetition: the number of duplicated items that can be of each type
     * @param MaxWeight: the maximum weight
     * @param testMode: If it is true, nothing is printed in the screen
     * @return the biggest possible value to be stored in the knapsack
	 * @throws WrongInputsException
     */
	public static int knapSack(ArrayList<Item> elements, int[] repetition, int MaxWeight, boolean testMode) throws WrongInputsException{
		if(MaxWeight <= 0 || elements.size() == 0) {return 0;}//Check for errors
		if(!checkValues(elements, repetition)) {throw new WrongInputsException();};
		elements = createAllElements(elements, repetition);//I create all the elements
		elements = createSubsets(elements, MaxWeight);//I create all the subsets

		int finalWeight = calculateWeight(elements);
		if(!testMode) {
			System.out.println("\t\tThe best list has the value " + max + " and a weight " + finalWeight + " is :\n");
	        for(Item aux : elements) {
	        	System.out.print(aux.getName() + "(" + aux.getValue() + ")" + ",");
	        }
		}
		return max;
	}

	/**
	 * It checks that the elements have correct values
	 * @param elements: The list which will contain the elements
	 * @param repetition: the number of duplicated items that can be of each type
	 * @return true if the values are correct and false otherwise
	 */
	private static boolean checkValues(ArrayList<Item> elements, int[] repetition) {
		int i = 0;
		for(Item item : elements) {
			if(item.getValue() <= 0 || item.getWeight() <= 0 || repetition[i] < 1) {
				return false;
			}
			i++;
		}
		return true;
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

	/**
	 * It create all the possible subsets of a set of elements
	 * @param MaxWeight: the maximum weight * @throws WrongInputsException
	 * @param set: set which contains the original elements
	 * @return: a list of lists which has all the possible subsets of the original set
	 */
	 public static ArrayList<Item> createSubsets(ArrayList<Item> elements, int maxWeight) {
		 	ArrayList<Item> bestSubset = new  ArrayList<Item>();//It is the best subsets
	        int aux = 0;
	        int maxSubsets = (int) Math.pow(2, elements.size());//I calculate all the possible subsets that can be created
	        for (int i = 0; i < maxSubsets; i++) {
	            ArrayList<Item> subset = new ArrayList<Item>();//I create the new subset
	            aux = populateSet(subset, elements, i, maxWeight);//I introduce the elements in the new subset
	            if(aux > max) {//We have a new best value
	            	max = aux;//We copy the new maximum value
	            	bestSubset = subset;//And the list of items
	            }
	        }
	        return bestSubset;
	    }

	/**
	 * It populates a set with certain elements in order to generate the correct permutation
     * @param MaxWeight: the maximum weight * @throws WrongInputsException
	 * @param subset: The set where the elements are introduced
	 * @param set: The set where the elements are from
	 * @param i: The index to know where we are
	 * @return: It returns the value of the set if its weight is not over the maximum weight allowed
	 */
	private static int populateSet(ArrayList<Item> subset, ArrayList<Item> elements, int i, int maxWeight) {
		int setWeight = 0, setValue = 0;
		for (int j = 0; j < elements.size(); j++) {
            if (((i >> j) & 1) == 1) {
                subset.add(elements.get(j));
                setWeight += elements.get(j).getWeight();
                setValue += elements.get(j).getValue();
            }
        }
		if(setWeight <= maxWeight) {return setValue;}//The weight of the set is allowed
		else { return -1;}
	}

	/**
	 * It calculates the weight of a list of items
	 * @param elements: The list which will contain the elements
	 * @return the weight of the list
	 */
	private static int calculateWeight(ArrayList<Item> elements) {
		int weight = 0;
		for(Item item : elements) {
			weight += item.getWeight();
		}
		return weight;
	}
}

