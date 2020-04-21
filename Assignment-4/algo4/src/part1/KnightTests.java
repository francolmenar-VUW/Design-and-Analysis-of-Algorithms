package part1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class KnightTests {
	KnightsTour knight;

	/**
	 * A default knight tour is created
	 */
	@Before
	public void defaultKnight() {
		knight = new KnightsTour(Model.getDEFAULT_SIZE());
	}

	/**
	 * It creates a knightRour and checks that it is created correctly
	 */
	@Test
	public void testConstructor1() {
		assertTrue(knight.getBoard().length == Model.getDEFAULT_SIZE());
	}

	/**
	 * It creates a knightRour and checks that it is created correctly
	 */
	@Test
	public void testConstructor2() {
		boolean check = true;
		for(int i = 0; i < knight.getBoard().length; i++) {
			if(knight.getBoard()[i].length !=
					Model.getDEFAULT_SIZE()) {//It checks that all the rows has the same length
				check = false;
			}
		}
		assertTrue(check);
	}

	/**
	 * It checks if the initialization of the board is performed correctly
	 */
	@Test
	public void checkInitializeBoard1() {
		boolean check = true;
		Square[][] board = knight.getBoard();
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] == null) {//I check if there is any square not initialize
					check = false;
				}
			}
		}
		assertTrue(check);
	}

	/**
	 * It checks that after executing initializeBoard there is no data in the squares
	 * of previous execution of calculateTour
	 */
	@Test
	public void checkInitializeBoard2() {
		boolean check = true;
		knight.calculateTour();//I calculate a path
		knight.initializeBoard(Model.getDEFAULT_SIZE());//I initialize again the board
		Square[][] board = knight.getBoard();
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] == null ||
						board[i][j].getPosition() != 0 ||
						board[i][j].isVisited() == true) {//I check if there is any square with wrong initial info
					check = false;
				}
			}
		}
		assertTrue(check);
	}

	/**
	 * It checks that the checking of valid movements is performed correctly
	 */
	@Test
	public void checkIsAllowed1() {
		int [] move = {1,1};
		assertTrue(knight.isAllowed(0,0,move,knight.getBoard().length));//A valid movement
	}

	/**
	 * It checks that the checking of valid movements is performed correctly
	 */
	@Test
	public void checkIsAllowed2() {
		int [] move = {0,0};
		assertTrue(knight.isAllowed(0,0,move,knight.getBoard().length));//A valid movement
	}

	/**
	 * It checks that the checking of valid movements is performed correctly
	 */
	@Test
	public void checkIsAllowed3() {
		int [] move = {7,7};
		assertTrue(knight.isAllowed(0,0,move,knight.getBoard().length));//A valid movement
	}

	/**
	 * It checks that the checking of valid movements is performed correctly
	 */
	@Test
	public void checkIsAllowed4() {
		int [] move = {-1,-1};
		assertFalse(knight.isAllowed(0,0,move,knight.getBoard().length));//A valid movement
	}

	/**
	 * It checks that the checking of valid movements is performed correctly
	 */
	@Test
	public void checkIsAllowed5() {
		int [] move = {9,0};
		assertFalse(knight.isAllowed(0,0,move,knight.getBoard().length));//A valid movement
	}

	/**
	 * It checks if the checkMoves methods checks the conditions right
	 */
	@Test
	public void checkCheckMoves1() {
		assertTrue(knight.checkMoves(0,0,0));//A valid check with a valid path
	}

	/**
	 * It checks if the checkMoves methods checks the conditions right
	 */
	@Test
	public void checkCheckMoves2() {
		knight.initializeBoard(2);//This size has not a valid path
		assertFalse(knight.checkMoves(0,0,0));//There is no path
	}

	/**
	 * It checks if the knightBT trivial checks
	 */
	@Test
	public void checkKnightBT1() {
		knight.getBoard()[0][0].setVisited(true);
		assertFalse(knight.knightBT(0,0,0));//There is no path
	}

	/**
	 * It checks if the knightBT trivial checks
	 */
	@Test
	public void checkKnightBT2() {
		assertTrue(knight.knightBT(0,0,62));//There is no more squares to check
	}

	/**
	 * It checks if the knightBT trivial checks
	 */
	@Test
	public void checkKnightBT3() {
		knight.initializeBoard(2);//This size has not a valid path
		assertFalse(knight.knightBT(0,0,0));//There is no path from that position
	}
	
	/**
	 * It checks if the calculateTour method with a valid size
	 */
	@Test
	public void checkCalculateTour1() {
		knight.initializeBoard(7);
		assertTrue(knight.calculateTour());//There is a path from that position
	}
	
	/**
	 * It checks if the calculateTour method with a invalid size
	 */
	@Test
	public void checkCalculateTour2() {
		knight.initializeBoard(2);
		assertFalse(knight.calculateTour());//There is no path from that position
	}

}
