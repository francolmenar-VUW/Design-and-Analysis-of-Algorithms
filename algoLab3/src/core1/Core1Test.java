package core1;

import static org.junit.Assert.*;
import core1.Knapsack;
import org.junit.Test;

import Exceptions.WrongInputsException;

public class Core1Test {

	/**
	 * It test a trivial result
	 * @throws WrongInputsException
	 */
	@Test
	public void knapSackTest1() throws WrongInputsException {
		int value[] = new int[]{60, 100, 120};
	    int weight[] = new int[]{2, 20, 30};
	    int  MaxWeight = 10;
	    int NumberOfValues = value.length;
	    assertTrue(60 == Knapsack.knapSack(MaxWeight, weight, value, NumberOfValues,false));
	}

	/**
	 * It test a more complex result
	 * @throws WrongInputsException
	 */
	@Test
	public void knapSackTest2() throws WrongInputsException {
		int value[] = new int[]{60, 100, 120};
	    int weight[] = new int[]{10, 20, 30};
	    int  MaxWeight = 50;
	    int NumberOfValues = value.length;
	    assertTrue(220 == Knapsack.knapSack(MaxWeight, weight, value, NumberOfValues,false));
	}

	/**
	 * Weight is 0
	 * @throws WrongInputsException
	 */
	@Test
	public void knapSackTest3() throws WrongInputsException {
		int value[] = new int[]{60, 100, 120};
	    int weight[] = new int[]{10, 20, 30};
	    int  MaxWeight = 0;
	    int NumberOfValues = value.length;
	    assertTrue(0 == Knapsack.knapSack(MaxWeight, weight, value, NumberOfValues,false));
	}

	/**
	 * There is no elements
	 * @throws WrongInputsException
	 */
	@Test
	public void knapSackTest4() throws WrongInputsException {
		int value[] = new int[]{};
	    int weight[] = new int[]{};
	    int  MaxWeight = 50;
	    int NumberOfValues = value.length;
	    assertTrue(0 == Knapsack.knapSack(MaxWeight, weight, value, NumberOfValues,false));
	}

	/**
	 * The data is wrong
	 * @throws WrongInputsException
	 */
	@Test(expected = WrongInputsException.class)
	public void knapSackTest5() throws WrongInputsException {
		int value[] = new int[]{1};
	    int weight[] = new int[]{};
	    int  MaxWeight = 50;
	    int NumberOfValues = value.length;
	    Knapsack.knapSack(MaxWeight, weight, value, NumberOfValues,false);
	}

	/**
	 * The data is wrong again
	 * @throws WrongInputsException
	 */
	@Test(expected = WrongInputsException.class)
	public void knapSackTest6() throws WrongInputsException {
		int value[] = new int[]{};
	    int weight[] = new int[]{1};
	    int  MaxWeight = 50;
	    int NumberOfValues = value.length;
	    Knapsack.knapSack(MaxWeight, weight, value, NumberOfValues,false);
	}
}
