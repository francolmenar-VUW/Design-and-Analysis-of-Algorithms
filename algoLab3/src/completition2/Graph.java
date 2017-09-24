package completition2;

import java.util.ArrayList;

import core1.Item;

public class Graph {
	private int MaxWeight;
	DoublyLinkedListImpl <Node> list = new DoublyLinkedListImpl <Node>();

	public Graph (ArrayList<Item> elements, int[] repetition, int MaxWeight) {
		this.MaxWeight = MaxWeight;
		createGraph(elements, repetition);//I create the graph
	}

	/**
	 * It creates the graph
	 * @param elements: The list which will contain the elements
	 * @param repetition: the number of duplicated items that can be of each type
	 */
	private void createGraph(ArrayList<Item> elements, int[] repetition) {
		int [] id = new int [repetition.length];
		list.addFirst(0, 0, id);//It is the start node
		for(int i = 0; i < 1; i++) {//For all the different elements
			for( int j = 0; j < repetition[i]-1; j++) {//For all the repetitions of the same element
				Node newNode = createNextNode(list.getTail(), elements, repetition, i, j);//I create the next node if it is possible
				System.out.println(i);
				list.addLast(newNode.getValue(), newNode.getWeight(), newNode.getId());
			}
		}
		list.iterateBackward();
	}

	/**
	 * It creates the next node if it is possible
	 * @param auxNode
	 * @param elements: The list which will contain the elements
	 * @param repetition: the number of duplicated items that can be of each type
	 * @param i: index of what element are we iterating
	 * @param j: index of how many repetitions of the elements we have
	 * @return the next node if it is possible to create it and null otherwise
	 */
	private Node createNextNode(Node auxNode, ArrayList<Item> elements, int[] repetition, int i, int j) {
		int [] auxId = auxNode.getId();
		int auxWeight = 0, auxValue = 0;
		auxId[i]++;//I update the id of the new node
		auxWeight = auxNode.getWeight() + elements.get(i).getWeight();
		auxValue = auxNode.getValue() +  elements.get(i).getValue();
		if(auxWeight > MaxWeight) {return null;}//We cannot create that node
		else {//We create the next node
			Node newNode = new Node(auxValue, auxWeight, auxId, null, null);
			return newNode;
		}
	}
}
