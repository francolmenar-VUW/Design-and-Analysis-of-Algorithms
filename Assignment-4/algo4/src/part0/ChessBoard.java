package part0;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.border.*;

import part1.Model;


@SuppressWarnings("serial")
public class ChessBoard extends JFrame implements Observer {

	private Model model;
	private Controller control;

    private JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares;
    private JPanel chessBoard;
    private JToolBar tool;

    private JButton time;
    private JButton setSize;
    private JButton start;

    private static final char INITIAL_LETTER = 'A';
    private boolean showResult = false;


    public ChessBoard(Model m, Controller c) {
        super("KnightsTour");//I put the board in the screen
    	this.model = m;
    	m.addObserver(this);
    	control = c;
        createBoard();
    }

    /**
     * It creates the chess board
     */
    public final void createBoard() {
    	setUpTool();
    	setUpGui();//set up the main GUI
    	createBoardSquares();//create the chess board squares
    	fillTheBoard();//fill the chess board
    	run();
    }

    /**
     * It sets up the tool bar adding the corresponding buttons
     */
	private void setUpTool() {
		tool = new JToolBar();
        tool.setFloatable(false);

        start = createButton("Start");
        time = createButton("Timing");
        setSize = createButton("Set size");

        tool.add(start);
        tool.add(time);//I initialize the buttons
        tool.add(setSize);
	}

	/**
	 * It creates a JButton given a string
	 * It assigns it an action listener
	 * @param string: the name of the button
	 * @return: the button
	 */
	private JButton createButton(String string) {
		JButton button = new JButton(string);
		button.addActionListener(control);
		button.setName(string);
		return button;
	}

	/**
     * It set ups the main GUI adding the chessBoard to the main panel
     */
    private void setUpGui() {
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        chessBoard = new JPanel(new GridLayout(0, model.getSize() + 1));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(tool, BorderLayout.PAGE_START);
        gui.add(chessBoard);
	}

	/**
     * It creates the chess board squares
     */
    private void createBoardSquares() {
    	chessBoardSquares = new JButton[model.getSize()][model.getSize()];//I create all the squares of the board
    	Insets buttonMargin = new Insets(0,0,0,0);
        for (int i = 0; i < chessBoardSquares.length; i++) {//I go through all the board
            for (int j = 0; j < chessBoardSquares[i].length; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon(
                		new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));//I set the icon
                b.setIcon(icon);
                if(isWhite(i,j)) {//I check if the piece is black or white
                    b.setBackground(Color.WHITE);
                    if(showResult) {//If the board has a result I print it
                    	setResultToSquare(i, j, Color.BLACK, b);//I copy the result to the square
                    }
                }
                else {
                    b.setBackground(Color.BLACK);
                    if(showResult) {//If the board has a result I print it
                    	setResultToSquare(i, j, Color.WHITE, b);//I copy the result to the square
                    }
                }
                chessBoardSquares[j][i] = b;//I add the square to the array of squares
            }
        }
	}

    /**
     * It sets the result of the algorithm to a certain square of the board
     *
     * @param i: the row of the square
     * @param j: the column of the square
     * @param color: the color of the text in the square
     * @param square: the square of the board where we are going to set the result
     */
    private void setResultToSquare(int i, int j, Color color, JButton square) {
    	String position = Integer.toString(model.getKnight().getBoard()[i][j].getPosition());
    	square.setText(position);
    	square.setHorizontalTextPosition(SwingConstants.CENTER );
    	square.setForeground(color);
	}

	/**
     * It checks if it is a white or a black square
     * @param i: the coordinates of the square
     * @param j
     * @return: true if it is a white square and false if it is black
     */
	private boolean isWhite(int i, int j) {
		if ((j % 2 == 1 && i % 2 == 1)
                || (j % 2 == 0 && i % 2 == 0)) {return true;}
		else{ return false;}
	}

    /**
     * It fills the chess board
     */
    private void fillTheBoard() {
    	chessBoard.add(new JLabel(""));
        for (int i = 0; i < model.getSize(); i++) {// fill the top row
            chessBoard.add(
                    new JLabel(Character.toString((char) (INITIAL_LETTER + i)),
                    SwingConstants.CENTER));

        }
        for (int i = 0; i < model.getSize(); i++) {// fill the black non-pawn piece row
            for (int j = 0; j < model.getSize(); j++) {
                switch (j) {//I check if it is a header of a piece
                    case 0:
                        chessBoard.add(new JLabel("" + (i + 1),
                                SwingConstants.CENTER));
                    default:
                        chessBoard.add(chessBoardSquares[j][i]);
                }
            }
        }
	}

    /**
     * It sets the frame
     */
    public void run() {
        this.add(this.getGui());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	pack();


    	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    	this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        this.setMinimumSize(this.getSize());
        this.setVisible(true);
    }

    /**
     * It checks for the actions to perform after a change in the model
     */
    @Override
	public void update(Observable o, Object arg) {
    	if(model.getState().equals(model.getSIZE())) {//The setSize button is pressed
    		actionSizePresssed();
    	}
    	else if(model.getState().equals(model.getNEW_SIZE())) {//The board is resized
    		actionNewSize();
    	}
    	else if(model.getState().equals(model.getTIMING())) {//The timing for the algorithm is asked
    		actionTiming();
    	}
    	else if(model.getState().equals(model.getSTART())){//The start button is pressed
    		actionStart();
    	}
	}

	/**
     * It is executed when the button for the new size is pressed
     * It shows a JDialog for introducing the new size of the board
     */
    private void actionSizePresssed() {
		new TalkWindow(this, control).setVisible(true);//I create the JDialog
	}

    /**
     * It is executed when the new size is introduced in the JDialog
     * It changes the size of the board to the desired size
     */
    private void actionNewSize() {
    	this.showResult = false;
		this.dispose();//I close the window
		deleteElements();//I delete the elements of the window
		createBoard();//I create the new window
	}

    /**
     * It is executed when the button timing is pressed
     * It executed the algorithm and shows a window with the timing of its execution
     */
	private void actionTiming() {
		actionStart();
		new TimingPanel(model.getTime()).setVisible(true);
	}

	/**
	 * It is executed when the start button is pressed
	 * It prints the solution of the algorithm
	 */
	private void actionStart() {
		showResult = true;//Show the results
		this.dispose();//I close the window
		deleteElements();//I delete the elements of the window
		createBoard();//I create the new window
	}

	/**
     * It deletes all the elements of the screen
     */
	private void deleteElements() {
		this.getContentPane().removeAll();
		gui = new JPanel(new BorderLayout(3, 3));
		chessBoardSquares = null;
		chessBoard = null;
		tool = null;
		time = null;
		setSize = null;
	}

	public final JComponent getChessBoard() {
        return chessBoard;
    }

    public final JComponent getGui() {
        return gui;
    }
}