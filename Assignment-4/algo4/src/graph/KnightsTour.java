package graph;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.UndirectedSubgraph;
import org.jgrapht.alg.BiconnectivityInspector;

import ecs100.UI;

public class KnightsTour {
	private boolean exist = false;

	private int path = 0;
	private int size;
	private final static int[][] moves = {{1,-2},{2,-1},{2,1},{1,2},{-1,2},
		        {-2,1},{-2,-1},{-1,-2}};

	private final int BASIC_OFSET = 100;
	private final int RADIX = 20;

	private Square start;

	private UndirectedGraph <Square, DefaultEdge> graph;
	private ConnectivityInspector<Square, DefaultEdge> connectivity;//To check if the graph is connected or not
	private BiconnectivityInspector<Square, DefaultEdge> biconnectivity;//To check if the graph is biconnected or not


	public KnightsTour(int size) {
		this.size = size;
		createGraph();
	}

	/**
	 * It creates the graph
	 */
	public void createGraph() {
		graph = new SimpleGraph<>(DefaultEdge.class);
		createVertex();//I create the vertex of the graph
		createEdges(this.graph);
		connectivity = new ConnectivityInspector<Square, DefaultEdge>(graph);
	}

	/**
	 * It creates the vertex of the graph
	 */
	private void createVertex() {
		for(int i = 0; i < this.size; i++) {//I go through all the board
			for(int j = 0; j < this.size; j++) {
				graph.addVertex(new Square(i, j));//I add the new vertex
			}
		}
	}

	/**
	 * It creates the edges of the graph
	 * @param graph
	 */
	private void createEdges(UndirectedGraph<Square, DefaultEdge> graph) {
		for(Square vertex : graph.vertexSet()) {//I go thorugh all the vertex of the graph
			int [][] nextMovements = getStaticMoves(vertex.getX(), vertex.getY());
			for(int i = 0; i < nextMovements.length; i++) {
				Square newEdge = getVertex(nextMovements[i][0],nextMovements[i][1], this.graph);
				if(graph.containsVertex(newEdge)) {
					if(!graph.containsEdge(vertex, newEdge) && !graph.containsEdge(newEdge, vertex)) {//I check if the edge already exists
						graph.addEdge(vertex, newEdge);
					}
				}
			}
		}
	}

	/**
	 * It returns a specific vertex of a graph
	 * @param y :The coordinates of the vertex
	 * @param x
	 * @param graph: the graph we are going to use
	 * @return: the vertex if it is in the graph, if not it is returned  null
	 */
	public Square getVertex(int x, int y, Graph<Square, DefaultEdge> graph) {
		for(Object aux : graph.vertexSet()) {
			Square vertex = (Square) (aux);
			if(vertex.getX() == x && vertex.getY() == y) {
				return vertex;
			}
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
	private int[][] getStaticMoves(int row, int col) {
		int[][] possibleLocations = new int[8][2];
		int possible = 0;//The counter of possible destinations
		for (int[] move: moves) {
			if (isAllowed(row, col, move)) {
				possibleLocations[possible][0] = move[0] + row;
				possibleLocations[possible][1] = move[1] +  col;
				possible++;
			}
		}
		int[][] actualMoves = new int[possible][2];
		for (int i = 0; i < possible; i++) {
			actualMoves[i] = possibleLocations[i];
		}
		return actualMoves;
	}

	/**
	 * It checks if a movement to the coordinates i and j is possible
	 * with a board of size size
	 * @param movesAux: the movement that it is going to be performed
	 *
	 * @param i: the row of the square
	 * @param j: the column of the square
	 * @return: true if the movement is allowed and false otherwise
	 */
	public boolean isAllowed(int i, int j, int[] movesAux) {
		int weight = i + movesAux[0];//Auxiliary variables to check if the moves
		int height = j + movesAux[1] ;//are valid or not
		if(weight >= 0 && height >= 0 &&
				weight < this.size && height < this.size) {//I check that the Knight does not go out of the board
			return true;//The movement is valid
		}
		return false;//Invalid movement
	}

	/**
	 * It prints the graph using the ecs100 library
	 * @param subgraph
	 */
	public void printGraph(UndirectedGraph<Square, DefaultEdge> graph2) {
        UI.setWindowSize( (BASIC_OFSET + RADIX) * graph2.vertexSet().size(), (BASIC_OFSET + RADIX) * graph2.vertexSet().size());//Full screen
        UI.setColor(Color.red);
        for (Object edge : graph2.edgeSet()){//I draw the edges
        	Square source = (Square) graph2.getEdgeSource((DefaultEdge) edge);
        	Square target = (Square) graph2.getEdgeTarget((DefaultEdge) edge);
        	UI.drawLine(source.getX() * BASIC_OFSET + RADIX, source.getY()* BASIC_OFSET + RADIX,
        			target.getX() * BASIC_OFSET + RADIX, target.getY() * BASIC_OFSET + RADIX);
        }
        for (Object aux : graph2.vertexSet()){//I draw the vertex
            Square vertex = (Square) (aux);
            UI.setColor(Color.green);
			UI.fillOval(vertex.getX()* BASIC_OFSET, vertex.getY()* BASIC_OFSET, RADIX * 2, RADIX * 2);
            UI.setColor(Color.blue);
            UI.drawString(vertex.getX() + "," + vertex.getY(),
            		vertex.getX()* BASIC_OFSET + (int)(RADIX/4), vertex.getY()* BASIC_OFSET + RADIX + (int) ( RADIX/4));//I write the String of the vertex in the center
        }
	}

	/**
	 * It checks for each square if starting in that position
	 * there is a valid tour
	 *
	 * @return: true if there is a valid tour and false otherwise
	 */
	public boolean calculateTour() {
		int counter = 0;//The counter of the pieces that we have checked
		for(int i = 0; i < this.size; i++) {//I go through all the board
			for(int j = 0; j < this.size; j++) {
				createGraph();
				counter = 0;
				path = 0;
				Square vertex = getVertex(i, j, this.graph);//I get the vertex
				start = vertex;
				if(knightBT(vertex, counter)) {//I check if there is a valid tour for this position
					setExist(true);
					return true;//It is a valid tour
				}
			}
		}
		setExist(false);
		createGraph();
		return false;//There is no valid tour
	}

	/**
	 * It performs the search for a valid path
	 *
	 * @param counter: The counter of the pieces that we have checked
	 * @param vertex: the actual vertex that we are checking
	 * @return: true if there is a valid tour and false otherwise
	 */
	public boolean knightBT(Square vertex, int counter) {
		if(vertex.isVisited() ) {
			return false;//the tour is not valid
		}
		vertex.setVisited(true);
		vertex.setPosition(path++);
		if(counter == (size * size -1)) {//We found a valid tour
			return true;
		}
		if(checkMoves(vertex, counter)) {//We check for the possible moves
			return true;//We found a path
		}
		vertex.setVisited(false);
		path--;
		return false;
	}

	/**
	 * It checks all the possible moves for the square in the position[i][j]
	 *
	 * @param vertex: the actual vertex that we are checking
	 * @param counter: The counter of the pieces that we have checked
	 * @return: true if there is a valid tour and false otherwise
	 */
	public boolean checkMoves(Square vertex, int counter) {
		ArrayList<Square> neighboursVertex = getNeighboursVertex(vertex, true);//I get the unvisited neighbors vertex
		for(Square neighbour : neighboursVertex) {//I iterate through all the neighbors
			if(connectedness(neighbour) &&
					validDegree(graphNotVisited(), neighbour) &&
					//biconnected(neighbour) &&
					knightBT(neighbour, counter + 1)
					) {//We call recursively to the method
				return true;//We found a correct path
			}
		}
		return false;//We did not find a correct path
	}


	/**
	 * It checks if the graph obtained by deleting neighbor from the subgraph
	 * of elements not chosen is connected
	 *
	 * @param neighbour: the vertex we want to delete
	 * @return: true if the remaining graph is connected and otherwise false is returned
	 */
	private boolean connectedness(Square neighbour) {
		UndirectedSubgraph<Square, DefaultEdge> subgraph = graphNotVisited();//Graph not visited
		subgraph.removeVertex(neighbour);
		if(subgraph.vertexSet().isEmpty()) {//There is no more choices
			return true;
		}
		connectivity = new ConnectivityInspector<>(subgraph);
		if(connectivity.isGraphConnected()) {//If the remaining graph is connected, it is a valid candidate
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * It returns the subgraph that has not been visited
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private UndirectedSubgraph<Square, DefaultEdge> graphNotVisited() {
		Set <Square> nonVisitedVertex = new HashSet<Square>();
		for(Square vertex : graph.vertexSet()) {
			if(!vertex.isVisited()) {//If the vertex has not been visited we store it
				nonVisitedVertex.add(vertex);
			}
		}
		return new UndirectedSubgraph(graph, nonVisitedVertex);
	}

	/**
	 * It gets all the vertex which are connected to the given one
	 * If unvisited is true, it will only returned the unvisited vertex
	 *
	 * @param vertex: The vertex we are using as reference
	 * @return: the list of vertex
	 */
	private ArrayList<Square> getNeighboursVertex(Square vertex, boolean unvisited) {
		Set <DefaultEdge> edgeSet = graph.edgesOf(vertex);//I get all the edges of the vertex
		ArrayList<Square> neighboursVertex = new ArrayList<>();//The list for the vertex
        for (DefaultEdge edge : edgeSet){//I go through all the neighbors
        	Square target = graph.getEdgeTarget(edge);//I get the neighbor vertex
        	Square source = graph.getEdgeSource(edge);//I get the neighbor vertex
        	if(unvisited) {//It returns only unvisited nodes
        		if(!target.isVisited() && !target.equals(vertex)) {//If it is not visited we store it
        			neighboursVertex.add(target);//I add the vertex
            	}
            	else if(!source.isVisited() && !source.equals(vertex)) {//If it is not visited we store it
        			neighboursVertex.add(source);//I add the vertex
            	}
        	}
        	else {//all nodes
        		if(!target.equals(vertex)) {
        			neighboursVertex.add(target);//I add the vertex
            	}
            	else if(!source.equals(vertex)) {
        			neighboursVertex.add(source);//I add the vertex
            	}
        	}
		}
		return neighboursVertex;
	}

	/**
	 * It checks if all the vertex of a graph has at least degree 2 apart from
	 * the vertex that we take as initial and another trivial one which will be the end
	 * of the path
	 *
	 * @param graph : the graph that we are going to evaluate
	 * @param vertex : the vertex that we take as the start point
	 * @return: true if at most two vertex has degree lower than 2 and false otherwise
	 */
	private boolean validDegree(UndirectedSubgraph<Square, DefaultEdge> graph, Square vertex) {
		int counter = 0;
		for(Square aux: graph.vertexSet()) {//I go through all the vertex of the graph
			if(graph.degreeOf(aux) < 2) {//Invalid degree
				counter++;
				if(counter > 2) {//We have reach the maximum wrong degrees
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * It checks if the subgraph resulted from adding the start node to the
	 * unvisited graph and adding the edge (start, vertex) is biconnected
	 *
	 * @param vertex: the actual vertex that we are checking
	 * @return: true if it is biconnected and false otherwise
	 */
	private boolean biconnected(Square vertex) {
		Set <Square> vertexSet = nonVisitedVertex();//Graph not visited
		if(vertexSet.isEmpty()) {//There is no more choices
			return true;
		}
		Square aux1 = new Square(start.getX(), start.getY());
		UndirectedGraph<Square, DefaultEdge> subgraph = new SimpleGraph<>(DefaultEdge.class);
		for(Square vertexToAdd : vertexSet) {
			subgraph.addVertex(vertexToAdd);
		}
		subgraph.addVertex(aux1);//I add the starting node
		subgraph.addEdge(aux1, vertex);
		ArrayList<Square> neighboursVertex = getNeighboursVertex(start, false);//I get the neighbors of the starting node
		for(Square aux2 : neighboursVertex) {//I go through the neighbors of start
			subgraph.addVertex(aux2);//I add the neighbor to the subgraph
		}
		createEdges(subgraph);

		biconnectivity = new BiconnectivityInspector<>(subgraph);
		if(biconnectivity.isBiconnected()) {//We check if the resulting subgraph is biconnected
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * It returns the set of vertex not visited
	 * @return
	 */
	private Set<Square> nonVisitedVertex() {
		Set <Square> nonVisitedVertex = new HashSet<Square>();
		for(Square vertex : graph.vertexSet()) {
			if(!vertex.isVisited()) {//If the vertex has not been visited we store it
				nonVisitedVertex.add(vertex);
			}
		}
		return nonVisitedVertex;
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

	public UndirectedGraph<Square, DefaultEdge> getGraph() {
		return graph;
	}

	public void setGraph(UndirectedGraph<Square, DefaultEdge> graph) {
		this.graph = graph;
	}

}
