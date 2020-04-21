package graph;

public class Square {
	private boolean visited;
	private int position;

	private int x;
	private int y;

	public Square(int x, int y) {
		this.visited = false;
		this.position = 0;
		this.x = x;
		this.y = y;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}


	@Override
	public String toString() {
		return "Square [x=" + x + ", y=" + y + "]";
	}


}
