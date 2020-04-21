package part2;

import java.util.Observable;

public class Model extends Observable{
	private String state;
	private int size;

	private final static int DEFAULT_SIZE = 8;

	private final String SIZE = "SetSize";
	private final String TIMING = "Timing";
	private final String NEW_SIZE = "New size";
	private final String START = "Start";

	private KnightsTour knight;
	private long time;



	public Model() {
		size = DEFAULT_SIZE;
	}

	/**
	 * The button to show the timing is pressed
	 */
	public void timing() {
		long startTime, endTime;
		startTime = System.nanoTime();//Calculate the time
		this.knight = new KnightsTour(getSize());
		this.knight.calculateTour();//I calculate the tour
		endTime = System.nanoTime();
		time = (long) (endTime - startTime) / 1000000;
		notifyView(TIMING);
	}

	/**
	 * The button to change the size is pressed
	 */
	public void setSize() {
		notifyView(SIZE);
	}

	/**
	 * It modifies the size of the board of chess
	 * @param size: the new size
	 */
	public void newSize(int size) {
		setSize(size);
		notifyView(NEW_SIZE);
	}

	/**
	 * It runs the algorithm and prints the solution
	 */
	public void start() {
		this.knight = new KnightsTour(getSize());
		this.knight.calculateTour();//I calculate the tour
		notifyView(START);
	}

	/**
	 * It changes the state of the model and it notifies it to the view
	 * @param newState: the new state of the model
	 */
	private void notifyView(String newState) {
		setState(newState);
		setChanged();
	    notifyObservers();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getSIZE() {
		return SIZE;
	}

	public String getTIMING() {
		return TIMING;
	}

	public String getNEW_SIZE() {
		return NEW_SIZE;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public static int getDEFAULT_SIZE() {
		return DEFAULT_SIZE;
	}

	public String getSTART() {
		return START;
	}

	public KnightsTour getKnight() {
		return knight;
	}

	public void setKnight(KnightsTour knight) {
		this.knight = knight;
	}

	public static int getDefaultSize() {
		return DEFAULT_SIZE;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

}
