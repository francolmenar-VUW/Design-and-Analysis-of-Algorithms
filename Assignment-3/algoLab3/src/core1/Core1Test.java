package core1;

import static org.junit.Assert.*;

import java.util.ArrayList;

import core1.KnapsackCore1;
import core1.MainCore1;
import core1.Item;

import org.junit.Test;

import Exceptions.WrongInputsException;

public class Core1Test {

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
	 * It test a trivial result
	 * @throws WrongInputsException
	*/
	@Test
	public void knapSackTest1() throws WrongInputsException {
		ArrayList<Item> elements = new ArrayList<>();
		String name [] = new String []{"a", "b", "c"};//Create the elements
		int value[] = new int[]{60, 100, 120};
	    int weight[] = new int[]{2, 20, 30};
	    int  MaxWeight = 10;
	    createElements(elements,value, weight, name);
	    assertTrue(60 == KnapsackCore1.knapSack(elements, MaxWeight,false));
	}

	/**
	 * It test a trivial result with the default values
	 */
	@Test
	public void knapSackTest2() throws WrongInputsException {
		ArrayList<Item> elements = new ArrayList<>();
		MainCore1.defaultValues(elements);//I use a default values
	    assertTrue(220 == KnapsackCore1.knapSack(elements, MainCore1.DEFAULT_MAXIMUM_WEIGHT,false));
	}

	/**
	 * Weight is 0
	 * @throws WrongInputsException
	 */
	@Test
	public void knapSackTest3() throws WrongInputsException {
		ArrayList<Item> elements = new ArrayList<>();
		String name [] = new String []{"a", "b", "c"};//Create the elements
		int value[] = new int[]{60, 100, 120};
	    int weight[] = new int[]{10, 20, 30};
	    int  MaxWeight = 0;
	    createElements(elements,value, weight, name);
	    assertTrue(0 == KnapsackCore1.knapSack(elements, MaxWeight,false));
	}


	/**
	 * There is no elements
	 * @throws WrongInputsException
	 */
	@Test
	public void knapSackTest4() throws WrongInputsException {
		ArrayList<Item> elements = new ArrayList<>();
		String name [] = new String []{};//Create the elements
		int value[] = new int[]{};
	    int weight[] = new int[]{};
	    int  MaxWeight = 50;
	    createElements(elements,value, weight, name);
	    assertTrue(0 == KnapsackCore1.knapSack(elements, MaxWeight,false));

	}

	/**
	 * The data is wrong
	 * @throws WrongInputsException
	 */
	@Test(expected = WrongInputsException.class)
	public void knapSackTest5() throws WrongInputsException {
		ArrayList<Item> elements = new ArrayList<>();
		String name [] = new String []{"a"};//Create the elements
		int value[] = new int[]{1};
	    int weight[] = new int[]{0};
	    int  MaxWeight = 50;
	    createElements(elements,value, weight, name);
	    KnapsackCore1.knapSack(elements, MaxWeight,false);
	}

	/**
	 * The data is wrong again
	 * @throws WrongInputsException
	 */
	@Test(expected = WrongInputsException.class)
	public void knapSackTest6() throws WrongInputsException {
		ArrayList<Item> elements = new ArrayList<>();
		String name [] = new String []{"a"};//Create the elements
		int value[] = new int[]{-1};
	    int weight[] = new int[]{0};
	    int  MaxWeight = 50;
	    createElements(elements,value, weight, name);
	    KnapsackCore1.knapSack(elements, MaxWeight,false);
	}
}
