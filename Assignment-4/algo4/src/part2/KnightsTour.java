package part2;

import java.text.DecimalFormat;

public class KnightsTour {
	private boolean exist = false;
	private Square[][] board;
	private int path = 0;
	private final static int[][] moves = {{1,-2},{2,-1},{2,1},{1,2},{-1,2},
		        {-2,1},{-2,-1},{-1,-2}};

	public KnightsTour(int size) {
		initializeBoard(size);//I reset the board for the next try
	}

	/**
	 * It checks for each square if starting in that position
	 * there is a valid tour
	 *
	 * @return: true if there is a valid tour and false otherwise
	 */
	public boolean calculateTour() {
		int counter = 0;//The counter of the pieces that we have checked
		for(int i = 0; i < board.length; i++) {//I go through all the board
			for(int j = 0; j < board[i].length; j++) {
				initializeBoard(board.length);//I reset the board for the next try
				counter = 0;
				path = 0;
				if(knightBT(i,j, counter)) {//I check if there is a valid tour for this position
					setExist(true);
					return true;//It is a valid tour
				}
			}
		}
		setExist(false);
		initializeBoard(board.length);//I reset the board for the next try
		return false;//There is no valid tour
	}

	/**
	 * It initializes all the squares of the board
	 * @param size: the size of the board
	 */
	public void initializeBoard(int size) {
		board = new Square[size][size];//I create all the pieces of the board
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				board[i][j] = new Square();
			}
		}
	}

	/**
	 * It performs the backtracking search
	 *
	 * @param counter: The counter of the pieces that we have checked
	 * @param i: the row of the square
	 * @param j: the column of the square
	 * @return: true if there is a valid tour and false otherwise
	 */
	public boolean knightBT(int i, int j, int counter) {
		if(board[i][j].isVisited()) {//If we have visited the actual square or there is no more squares
			return false;//the tour is not valid
		}
		board[i][j].setVisited(true);//We set the square as visited
		board[i][j].setPosition(path++);//Update the position of the counter
		if(counter == (board.length * board.length -1)) {//We found a valid tour
			return true;
		}
		//if(prune(i, j, 0)) {

		/*
		int[][] aux = getStaticMoves(i,j);
		System.out.println("Path " + path + "\tCounter " + counter + "\tin the position (" + i + "," + j + ")");
		for(int ii = 0; ii < aux.length; ii++) {
			for(int jj = 0; jj < aux[ii].length; jj++) {
				System.out.print(aux[ii][jj] + ",");
			}
			System.out.println();
		}
		*/

			if(checkMoves(i, j, counter)) {//We check for the possible moves
				return true;//We found a path
			}
		//}
		board[i][j].setVisited(false);//We set the square as not visited
		path--;
		return false;
	}

	/**
	 * It calculates if the path is going to have possible movements in 5 moves
	 * @param k
	 *
	 * @param i: the row of the square
	 * @param j: the column of the square
	 * @return: true if there are paths, otherwise false is returned
	 */
	private Boolean prune(int i, int j, int k) {
		if(k > 5) {
			return true;
		}
		int[][] aux = getStaticMoves(i,j);
		k++;
		if(aux == null) {
			return false;
		}
		for(int ii = 0; ii < aux.length; ii++) {
			prune(aux[ii][0], aux[ii][1], k);
		}
		return null;
	}

	/**
	 * Determine possible moves from a position on the board
	 *
	 * @param row position
	 * @param col position
	 *
	 * @return possible movement matrix
	 */
	protected int[][] getStaticMoves(int row, int col) {
		int[][] possibleMoves = new int[8][2];
		int[] mov = {row, col};
		int possible = 0;
		for (int[] move: moves) {
			if (isAllowed(move[0], move[1], mov, board.length)) {
				possibleMoves[possible++] = move;
			}
		}
		int[][] actualMoves = new int[possible][2];
		for (int i = 0; i < possible; i++) {
			actualMoves[i] = possibleMoves[i];
		}
		return actualMoves;
	}

	/**
	 * Preview position after move
	 *
	 * @param move to make
	 * @param row current
	 * @param col location
	 *
	 * @return move transform matrix
	 */
	protected int[] peekMove(int[] move, int row, int col) {
		return new int[] {
			row + move[0],
			col + move[1]
		};
	}

	/**
	 * It checks all the possible moves for the square in the position[i][j]
	 *
	 * @param i: the row of the square
	 * @param j: the column of the square
	 * @param counter: The counter of the pieces that we have checked
	 * @return: true if there is a valid tour and false otherwise
	 */
	public boolean checkMoves(int i, int j, int counter) {
		for(int z = 0; z < moves.length; z++) {//I iterate through all the possible moves of the Knight
			if(isAllowed(i, j,moves[z], board.length) &&
					knightBT(i + moves[z][0], j + moves[z][1], counter + 1)) {//We call recursively to the method
				return true;//We found a correct path
			}
		}
		return false;//We did not find a correct path
	}

	/**
	 * It checks if a movement to the coordinates i and j is possible
	 * with a board of size size
	 * @param movesAux
	 *
	 * @param i: the row of the square
	 * @param j: the column of the square
	 * @param size: size of the board
	 * @return: true if the movement is allowed and false otherwise
	 */
	public boolean isAllowed(int i, int j, int[] movesAux, int size) {
		int weight = i + movesAux[0];//Auxiliary variables to check if the moves
		int height = j + movesAux[1] ;//are valid or not
		if(weight >= 0 && height >= 0 &&
				weight < size && height < size) {//I check that the Knight does not go out of the board
			return true;//The movement is valid
		}
		return false;//Invalid movement
	}

	public void print() {
		DecimalFormat twodigits = new DecimalFormat("00");
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.print("   " + twodigits.format(board[i][j].getPosition()));
			}
			System.out.println();
		}
	}

	public Square[][] getBoard() {
		return board;
	}

	public void setBoard(Square[][] board) {
		this.board = board;
	}

	public static int[][] getMoves() {
		return moves;
	}

	public boolean isExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}

	public int getPath() {
		return path;
	}

	public void setPath(int path) {
		this.path = path;
	}

}
