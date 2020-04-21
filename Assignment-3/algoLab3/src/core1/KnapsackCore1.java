package core1;

import java.util.ArrayList;
import java.util.Scanner;

import Exceptions.WrongInputsException;
import core1.Item;

public class KnapsackCore1 {
	private final static int CELL_SIZE = 6;//Variables to print the table
	private final static int EMPTY_CELL_SIZE = 8;

	  /**
     * It creates the knapsack table and return the best result
     * @param MaxWeight: the maximum weight
	 * @param elements: The list which will contain the elements
     * @param ask: If it is true it ask for the user interaction to print the table
     * @return the biggest possible value to be stored in the knapsack
	 * @throws WrongInputsException
     */
	public static int knapSack(ArrayList<Item> elements, int MaxWeight, boolean ask) throws WrongInputsException{
	    /*				I create the table
	 	The columns represent the maximum allowed weight
	 	The rows represent the  maximum allowed value (for example,
	 	if we are in value 3, we can use the value number 0,..,3   */

	     int table [][] = new int[elements.size()][MaxWeight+1];
	     if(!checkValues(elements)) {throw new WrongInputsException();};

	     populateTable(table, MaxWeight, elements);
	     if(ask) {printTable(table, MaxWeight, elements);}

	     if(table.length > 0) {return table[elements.size()-1][MaxWeight];}//I check if there is any element
	     else {return 0;}
	    }

	/**
	 * It checks that the elements have correct values
	 * @param elements: The list which will contain the elements
	 * @return true if the valus are correct and false otherwise
	 */
	private static boolean checkValues(ArrayList<Item> elements) {
		for(Item item : elements) {
			if(item.getValue() <= 0 || item.getWeight() <= 0 ) {
				return false;
			}
		}
		return true;
	}

	/**
	 * It makes all the calculations to populate the table
	 * @param elements: The list which will contain the elements
	 * @param table: the knapsack table
	 * @param MaxWeight: the maximum weight
	 */
	private static void populateTable(int[][] table, int MaxWeight, ArrayList<Item> elements) {
	     int availableWeight, previousOptimum;//Auxiliary for the loop

		 for (int i = 0; i < elements.size(); i++){//Rows -> the values available
			 Item elem = elements.get(i);//The actual element
	         for (int j = 0; j <= MaxWeight; j++) {//Columns -> the maximum weight
	             if (elem.getWeight()  <= j){//I check if the weight I am going to obtain of the weight array is bigger than the actual maximum weight
	            	 if(i == 0) {
	            		 table[i][j] = elem.getValue();
	            	 }
	            	 else {
	            		 availableWeight = j-elem.getWeight();//It is the weight that would be available using the weight of the weight array
		            	 previousOptimum = table[i-1][availableWeight];//It is the optimum solution previously calculated using the availableWeight
		            	 table[i][j] = max(elem.getValue() + previousOptimum,  table[i-1][j]);//I calculate the optimum solution between using the new item or not
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
	  * @param elements: The list which will contain the elements
      * @param MaxWeight: the maximum weight
	  * @param table: the knapsack table
	  */
	private static void printTable(int[][] table, int MaxWeight, ArrayList<Item> elements) {
		if(userWants()) {//The user wants to print the table
			printKnapsackTable(table, MaxWeight, elements);
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
	 * @param elements: The list which will contain the elements
     * @param MaxWeight: the maximum weight
	 * @param table: the knapsack table
	 */
	private static void printKnapsackTable(int[][] table, int MaxWeight, ArrayList<Item> elements) {
		String output = "";

		output = headerColumns(output, MaxWeight);
		output = rows(table, output, MaxWeight, elements);

		System.out.println(output);//I print the table
	}

	/**
	 * It prints the header of the columns
	 * @param output: the String that contains the table
     * @param MaxWeight: the maximum weight
	 */
	private static String headerColumns(String output, int MaxWeight) {
		for(int j = 0; j < EMPTY_CELL_SIZE-1; j++) {//The header of the first column is empty
			output += " ";
		}
		for(int i = 0; i <= MaxWeight; i++) {
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
		for(int j = 0; j < CELL_SIZE; j++) {//I copy the number and the correct number of spaces to the String
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
	 * @param elements: The list which will contain the elements
	 * @param table: the knapsack table
	 * @param output: the String that contains the table
	 * @param MaxWeight: the maximum weight
	 * @return: the String that contains the table
	 */
	private static String rows(int[][] table, String output, int MaxWeight, ArrayList<Item> elements) {
		for (int i = elements.size()-1; i >= 0; i--){//Rows -> the values available
	         for (int j = 0; j <= MaxWeight; j++) {//Columns -> the maximum weight
	        	 if(j == 0) {
	        			 output = rowHeader(output, elements, i);
	        	 }
        		 output = copyNumber(output, table[i][j]);//I copy the value in the table
	         }
	         output += "\n\n";
		}
	    return output;
	}

	/**
	 * It prints the header row
	 * @param i
	 * @param elements
	 * @param output: the String that contains the table
     * @param weight: the array of different weights
     * @param value: the array of different values
	 * @param i: the row
	 * @return: the String that contains the table
	 */
	private static String rowHeader(String output, ArrayList<Item> elements, int i) {
		 int aux = 1;
		 Item elem = elements.get(i);
		 output += Integer.toString(elem.getWeight());
		 output +="(";
		 aux += String.valueOf(elem.getWeight()).length() + 1;

		 output += Integer.toString(elem.getValue());
		 output +=")";
		 aux += String.valueOf(elem.getValue()).length() + 1;
		 if(aux < EMPTY_CELL_SIZE) {
				for(int j = aux; j < EMPTY_CELL_SIZE; j++) {
					output += " ";
				}
		 }
		 return output;
	}

}
