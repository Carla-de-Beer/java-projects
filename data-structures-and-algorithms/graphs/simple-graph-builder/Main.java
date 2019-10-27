// Carla de Beer
// Simple graph creation class
// Date created: 15/04/2014

public class Main {

	public static void main(String[] args) {

		SimpleGraph empty = new SimpleGraph(5);

		System.out.println("--------------- TESTING EMPTY:");
		System.out.println();

		System.out.println("DFS on empty: ");
		empty.depthFirstSearch();
		empty.printNum();

		System.out.println("BFS on empty: ");
		empty.breadthFirstSearch();
		empty.printNum();
		System.out.println();

		System.out.println("Number of edges: " + empty.getE());
		System.out.println("Number of vertices: " + empty.getV());
		System.out.println();
		System.out.println(empty);
		System.out.println();

		System.out.println("----------------------------------");
		System.out.println();

		SimpleGraph graph = new SimpleGraph(9);

		graph.addEdge(0, 4);
		graph.addEdge(0, 2);
		graph.addEdge(0, 7);
		graph.addEdge(2, 8);
		graph.addEdge(1, 5);
		graph.addEdge(2, 7);
		graph.addEdge(3, 8);
		graph.addEdge(4, 5);
		graph.addEdge(4, 8);
		graph.addEdge(5, 7);
		graph.addEdge(5, 7);

		System.out.println(graph);
		System.out.println("Number of edges: " + graph.getE());
		System.out.println("Number of vertices: " + graph.getV());
		System.out.println();

		graph.depthFirstSearch();
		graph.printNum();
		System.out.println();

		graph.breadthFirstSearch();
		graph.printNum();
		System.out.println();

		graph.buildFromFile("C:/figure.graph");
		System.out.println(graph);
	}
}

