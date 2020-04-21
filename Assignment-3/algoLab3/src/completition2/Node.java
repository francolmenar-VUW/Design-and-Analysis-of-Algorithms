package completition2;

public class Node {
	protected int value;
	protected int weight;
	protected int [] id;

	protected Node next;
	protected Node previous;

	public Node(int value, int weight, int[] id,  Node next, Node prev) {
		super();
		this.value = value;
		this.weight = weight;
		this.id = id;
		this.next = next;
		this.previous = prev;
	}


	public Node getNext() {
		return next;
	}


	public void setNext(Node next) {
		this.next = next;
	}


	public Node getPrevious() {
		return previous;
	}


	public void setPrevious(Node previous) {
		this.previous = previous;
	}


	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int[] getId() {
		return id;
	}
	public void setId(int[] id) {
		this.id = id;
	}


}
