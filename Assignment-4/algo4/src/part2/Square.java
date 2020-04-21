package part2;

public class Square {
	private boolean visited;
	private int position;

	public Square() {
		this.visited = false;
		this.position = 0;
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
}
