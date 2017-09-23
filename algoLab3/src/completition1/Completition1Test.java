package completition1;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Exceptions.WrongInputsException;
import core1.Item;

public class Completition1Test {

	/**
	 * It transforms the Strings of data into a list of Items
	 * @param elements: The list which will contain the elements
	 * @param value: The array with the data
	 * @param weight
	 * @param name
	 */
	private void createElements(ArrayList<Item> elements, int[] value, int[] weight, String[] name) {
		for(int i = 0; i < value.length; i++) {
			Item item = new Item(name[i], value[i], weight[i]);//New item
			elements.add(item);//Item to the list
		}
	}

	/**
	 * It test a trivial result with the default values
	 * @throws WrongInputsException
	 */
	@Test
	public void knapSackTest1() throws WrongInputsException {
		ArrayList<Item> elements = new ArrayList<>();
		int repetition [];
		repetition = MainCompletition1.defaultValues(elements);//I use a default values
		assertTrue(48 == KnapsackCompletition1.knapSack(elements, repetition, MainCompletition1.DEFAULT_MAXIMUM_WEIGHT, false));
	}

	/**
	 * It test a more complex example
	 * @throws WrongInputsException
	 */
	@Test
	public void knapSackTest2() throws WrongInputsException {
		ArrayList<Item> elements = new ArrayList<>();
		String name [] = new String []{"a", "b", "c", "d", "e", "f"};//Create the elements
		int value [] = new int [] {15,12,9,7,4,3};
		int weight [] = new int [] {30,25,23,20,10,5};
		int repetition [] = new int [] {3,3,3,3,3,3};
	    createElements(elements,value, weight, name);
		assertTrue(51 == KnapsackCompletition1.knapSack(elements, repetition, 100, false));
	}

	/**
	 * It test a trivial result
	 * @throws WrongInputsException
	*/
	@Test
	public void knapSackTest3() throws WrongInputsException {
		ArrayList<Item> elements = new ArrayList<>();
		String name [] = new String []{"a", "b", "c"};//Create the elements
		int value[] = new int[]{60, 100, 120};
	    int weight[] = new int[]{2, 20, 30};
	    int  MaxWeight = 10;
	    createElements(elements,value, weight, name);
		int repetition [] = new int [] {1,1,1};
		assertTrue(60 == KnapsackCompletition1.knapSack(elements, repetition, MaxWeight, false));
	}

	/**
	 * Weight is 0
	 * @throws WrongInputsException
	 */
	@Test
	public void knapSackTest4() throws WrongInputsException {
		ArrayList<Item> elements = new ArrayList<>();
		String name [] = new String []{"a", "b", "c"};//Create the elements
		int value[] = new int[]{60, 100, 120};
	    int weight[] = new int[]{10, 20, 30};
	    int  MaxWeight = 0;
	    createElements(elements,value, weight, name);
	    int repetition [] = new int [] {1,1,1};
		assertTrue(0 == KnapsackCompletition1.knapSack(elements, repetition, MaxWeight, false));
	}

	/**
	 * There is no elements
	 * @throws WrongInputsException
	 */
	@Test
	public void knapSackTest5() throws WrongInputsException {
		ArrayList<Item> elements = new ArrayList<>();
		String name [] = new String []{};//Create the elements
		int value[] = new int[]{};
	    int weight[] = new int[]{};
	    int  MaxWeight = 50;
	    int repetition [] = new int [] {1,1,1};
	    createElements(elements,value, weight, name);
		assertTrue(0 == KnapsackCompletition1.knapSack(elements, repetition, MaxWeight, false));
	}

	/**
	 * The data is wrong
	 * @throws WrongInputsException
	 */
	@Test(expected = WrongInputsException.class)
	public void knapSackTest6() throws WrongInputsException {
		ArrayList<Item> elements = new ArrayList<>();
		String name [] = new String []{"a"};//Create the elements
		int value[] = new int[]{1};
	    int weight[] = new int[]{0};
	    int  MaxWeight = 50;
	    int repetition [] = new int [] {1,1,1};
	    createElements(elements,value, weight, name);
	    KnapsackCompletition1.knapSack(elements, repetition, MaxWeight, false);
	}

	/**
	 * The data is wrong
	 * @throws WrongInputsException
	 */
	@Test(expected = WrongInputsException.class)
	public void knapSackTest7() throws WrongInputsException {
		ArrayList<Item> elements = new ArrayList<>();
		String name [] = new String []{"a"};//Create the elements
		int value[] = new int[]{1};
	    int weight[] = new int[]{0};
	    int  MaxWeight = 50;
	    int repetition [] = new int [] {1,-1,1};
	    createElements(elements,value, weight, name);
	    KnapsackCompletition1.knapSack(elements, repetition, MaxWeight, false);
	}

}
