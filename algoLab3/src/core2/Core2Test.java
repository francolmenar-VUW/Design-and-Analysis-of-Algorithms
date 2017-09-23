package core2;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class Core2Test {

	/**
	 * It test a trivial result with the default values
	 */
	@Test
	public void knapSackTest1() {
		ArrayList<Item> elements = new ArrayList<>();
		int repetition [];
		repetition = Main.defaultValues(elements);//I use a default values
		assertTrue(48 == Knapsack.knapSack(elements, repetition,Main.DEFAULT_MAXIMUM_WEIGHT, true));
	}

	/**
	 * It test a more complex example
	 */
	@Test
	public void knapSackTest2() {
		ArrayList<Item> elements = new ArrayList<>();
		String name [] = new String []{"a", "b", "c", "d", "e", "f"};//Create the elements
		int value [] = new int [] {15,12,9,7,4,3};
		int weight [] = new int [] {30,25,23,20,10,5};
		int repetition [] = new int [] {3,3,3,3,3,3};

		for(int i = 0; i < value.length; i++) {
			Item item = new Item(name[i], value[i], weight[i]);//New item
			elements.add(item);//Item to the list
		}
		assertTrue(51 == Knapsack.knapSack(elements, repetition, 100, true));
	}

}
