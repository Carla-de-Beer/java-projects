// Carla de Beer
// Graphs: Dijkstra's shortest path algorithm, cycle detection, strongly connected search
// Date created: 25/04/2014
public class Main {

	public static void main(String[] args) {

		System.out.println("Graph builder:");
		System.out.println();

		Graph graph1 = Graph.buildFromFile("C:/dijkstra.graph");
		System.out.println(graph1);

		System.out.println();
		System.out.println("---------------------------");
		System.out.println();

		System.out.println("Dijkstra's shortest path algorithm:");
		System.out.println();

		if (graph1.dijkstra('d', 'j') == true) {
			System.out.println("Node is reachable");
		} else {
			System.out.println("Node is unreachable");
		}

		printArray(graph1.dist); // 4 9 11 0 5 8 12 1 9 11
		printArray(graph1.pred); // 3 5 5 -1 0 4 9 3 5 8

		System.out.println();
		System.out.println("---------------------------");
		System.out.println();
		System.out.println("Cycle detection: ");
		System.out.println();

		Graph graph2 = Graph.buildFromFile("C:/cycle.graph");
		graph2.detectCycles();

		System.out.println(graph2.cycles); // [2->0->1->2]

		System.out.println();
		System.out.println("---------------------------");
		System.out.println();
		System.out.println("Strongly connected search: ");
		System.out.println();
		Graph graph3 = Graph.buildFromFile("C:/strong.graph");
		graph3.stronglyConnectedSearch();

		System.out.println(graph3.scc); // [5 2 0, 10, 9, 8, 6 7 3 4 1]
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (i == arr.length - 1) {
				System.out.println(arr[i]);
			} else {
				System.out.print(arr[i] + " ");
			}
		}
	}

}

