package core1;

import static org.junit.Assert.*;
import core1.Knapsack;
import org.junit.Test;

public class Core1Test {

	/**
	 * It test a trivial result
	 * @throws WrongInputsException
	 */
	@Test
	public void knapSackTest1() throws WrongInputsException {
		int val[] = new int[]{60, 100, 120};
	    int wt[] = new int[]{2, 20, 30};
	    int  W = 10;
	    int n = val.length;
	    assertTrue(60 == Knapsack.knapSack(W, wt, val, n,false));
	}

	/**
	 * It test a more complex result
	 * @throws WrongInputsException
	 */
	@Test
	public void knapSackTest2() throws WrongInputsException {
		int val[] = new int[]{60, 100, 120};
	    int wt[] = new int[]{10, 20, 30};
	    int  W = 50;
	    int n = val.length;
	    assertTrue(220 == Knapsack.knapSack(W, wt, val, n,false));
	}

	/**
	 * Weight is 0
	 * @throws WrongInputsException
	 */
	@Test
	public void knapSackTest3() throws WrongInputsException {
		int val[] = new int[]{60, 100, 120};
	    int wt[] = new int[]{10, 20, 30};
	    int  W = 0;
	    int n = val.length;
	    assertTrue(0 == Knapsack.knapSack(W, wt, val, n,false));
	}

	/**
	 * There is no elements
	 * @throws WrongInputsException
	 */
	@Test
	public void knapSackTest4() throws WrongInputsException {
		int val[] = new int[]{};
	    int wt[] = new int[]{};
	    int  W = 50;
	    int n = val.length;
	    assertTrue(0 == Knapsack.knapSack(W, wt, val, n,false));
	}

	/**
	 * The data is wrong
	 * @throws WrongInputsException
	 */
	@Test(expected = WrongInputsException.class)
	public void knapSackTest5() throws WrongInputsException {
		int val[] = new int[]{1};
	    int wt[] = new int[]{};
	    int  W = 50;
	    int n = val.length;
	    Knapsack.knapSack(W, wt, val, n,false);
	}

	/**
	 * The data is wrong again
	 * @throws WrongInputsException
	 */
	@Test(expected = WrongInputsException.class)
	public void knapSackTest6() throws WrongInputsException {
		int val[] = new int[]{};
	    int wt[] = new int[]{1};
	    int  W = 50;
	    int n = val.length;
	    Knapsack.knapSack(W, wt, val, n,false);
	}
}
