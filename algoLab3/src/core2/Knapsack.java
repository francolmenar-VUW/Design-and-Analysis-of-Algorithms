package core2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Exceptions.WrongInputsException;
import gitCopiado.Item;

public class Knapsack {
	private final static int CELL_SIZE = 6;//Variables to print the table
	private final static int EMPTY_CELL_SIZE = 8;

	  /**
     * It creates the knapsack table and return the best result
	 * @param repetition: the number of duplicated items that can be of each type
     * @param MaxWeight: the maximum weight
     * @param weight: the array of different weights
     * @param value: the array of different values
     * @param NumberOfValues: number of different values
     * @param ask: If it is true it ask for the user interaction to print the table
     * @return the biggest possible value to be stored in the knapsack
	 * @throws WrongInputsException
     */
	public static int knapSack(int MaxWeight, int weight[], int value[], int[] repetition, int NumberOfValues, boolean ask) throws WrongInputsException{
		if(weight.length != value.length) {
			throw new WrongInputsException();
		}
		ArrayList<Integer> intList = new ArrayList<Integer>();
		for (int index = 0; index < value.length; index++)
		{
		    intList.add(value[index]);
		}

        ArrayList<ArrayList<Integer>> list = createSubsets(intList);

        int i = 0;
        /*
        for(ArrayList<Integer> aux : list) {
        	System.out.print("List " + i + ": ");
        	for(int j = 0; j < aux.size(); j++) {
        		System.out.print(" " + aux.get(j) + " ");
        	}
        	System.out.println();
        	i++;
        }
        */

	    int max = 0;
		return max;
	}


	 public static ArrayList<ArrayList<Integer>> createSubsets(ArrayList<Integer> set) {
	        ArrayList<ArrayList<Integer>> allsubsets = new ArrayList<ArrayList<Integer>>();//It is the list of all the different subsets
	        int maxSubsets = (int) Math.pow(2, set.size());//I calculate all the possible subsets that can be created
	        for (int i = 0; i < maxSubsets; i++) {
	            ArrayList<Integer> subset = new ArrayList<Integer>();//I create the new subset
	            populateSet(subset, set, i);//I introduce the elements in the new subset
	            allsubsets.add(subset);//I add the new subset to the list of all the subsets
	        }
	        return allsubsets;
	    }


	private static void populateSet(ArrayList<Integer> subset, ArrayList<Integer> set, int i) {
		for (int j = 0; j < set.size(); j++) {
            if (((i >> j) & 1) == 1) {
                subset.add(set.get(j));
            }
        }
	}
}

