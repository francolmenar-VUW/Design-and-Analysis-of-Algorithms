package completition2;

import java.util.NoSuchElementException;

public class DoublyLinkedListImpl<E> {

	private Node head;
	private Node tail;
	private int size;

	public DoublyLinkedListImpl() {
		size = 0;
	}

	/**
	 * returns the size of the linked list
	 * @return
	 */
	public int size() { return size; }

	/**
	 * return whether the list is empty or not
	 * @return
	 */
	public boolean isEmpty() { return size == 0; }

	/**
	 * adds element at the starting of the linked list
	 * @param element
	 */
	public void addFirst(int value, int weight, int[] id) {
		Node tmp = new Node(value, weight, id, head, null);
		if(head != null ) {head.previous = tmp;}
		head = tmp;
		if(tail == null) { tail = tmp;}
		size++;
		System.out.print("adding: ");
		for(int z = 0; z < id.length; z++) {
			System.out.print(id[z] + ",");
		}
		System.out.println(")");
	}

	/**
	 * adds element at the end of the linked list
	 * @param element
	 */
	public void addLast(int value, int weight, int[] id) {

		Node tmp = new Node(value, weight, id, null, tail);
		if(tail != null) {tail.next = tmp;}
		tail = tmp;
		if(head == null) { head = tmp;}
		size++;
		System.out.print("adding: ");
		for(int z = 0; z < id.length; z++) {
			System.out.print(id[z] + ",");
		}
		System.out.println(")");
	}

	/**
	 * this method walks forward through the linked list
	 */
	public void iterateForward(){

		System.out.println("iterating forward..");
		Node tmp = head;
		while(tmp != null){
			for(int z = 0; z < tmp.getId().length; z++) {
				System.out.print(tmp.getId()[z] + ",");
			}
			System.out.println(")");
			tmp = tmp.next;
		}
	}

	/**
	 * this method walks backward through the linked list
	 */
	public void iterateBackward(){

		System.out.println("iterating backword..");
		Node tmp = tail;
		while(tmp != null){
			for(int z = 0; z < tmp.getId().length; z++) {
				System.out.print(tmp.getId()[z] + ",");
			}
			System.out.println(")");
			tmp = tmp.previous;
		}
	}

	/**
	 * this method removes element from the start of the linked list
	 * @return
	 */
	public int[] removeFirst() {
		if (size == 0) throw new NoSuchElementException();
		Node tmp = head;
		head = head.next;
		head.previous = null;
		size--;
		System.out.print("deleted: ");
		for(int z = 0; z < tmp.getId().length; z++) {
			System.out.print(tmp.getId()[z] + ",");
		}
		System.out.println(")");
		return tmp.getId();
	}

	/**
	 * this method removes element from the end of the linked list
	 * @return
	 */
	public int[] removeLast() {
		if (size == 0) throw new NoSuchElementException();
		Node tmp = tail;
		tail = tail.previous;
		tail.next = null;
		size--;
		System.out.print("deleted: ");
		for(int z = 0; z < tmp.getId().length; z++) {
			System.out.print(tmp.getId()[z] + ",");
		}
		System.out.println(")");
		return tmp.getId();
	}


	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	public Node getTail() {
		return tail;
	}

	public void setTail(Node tail) {
		this.tail = tail;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}


}
