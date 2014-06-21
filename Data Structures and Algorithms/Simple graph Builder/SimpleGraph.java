// Carla de Beer
// Simple graph creation class
// Date created: 15/04/2014

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SimpleGraph {

	// Maximum number of vertices allowed
	protected int V;
	// Number of edges currently in the graph
	protected int E;
	// The adjacency matrix
	protected boolean[][] adjacencyMatrix;
	// Use this variable for DFS & BFS
	// This variable stores the current visitation number during traversal
	public int i;
	// Use this variable for DFS & BFS
	// This array stores each vertex's visitation number
	public int[] num;
	// This ArrayList stores edges as they are visited in the following
	// form (from:to)
	// For example: If node 1 is visited and then node 5, then call
	// edges.add("1:5")
	public ArrayList<String> edges;
	// BFS - using an ArrayList rather than a queue
	protected ArrayList<Integer> queue;

	public SimpleGraph(int V) {
		this.V = V;
		this.E = 0;
		this.adjacencyMatrix = new boolean[V][V];
	}

	public int getV() {
		return V;
	}

	public int getE() {
		return E;
	}

	public void addEdge(int v, int w) {

		if (!adjacencyMatrix[v][w])
			E++;
		adjacencyMatrix[v][w] = true;
		adjacencyMatrix[w][v] = true;
	}

	public void DFS(int V) {

		num[V] = i++;

		for (int u = 0; u < getV(); ++u) {
			if (adjacencyMatrix[V][u] == true) {
				if (num[u] == 0) {
					edges.add(V + ":" + u);
					DFS(u);
				}
			}
		}
	}

	public void depthFirstSearch() {

		// !!!!check if graph is ZERO!!!
		if (V == 0)
			return;

		num = new int[V];

		for (int v = 0; v < getV(); ++v) {
			num[v] = 0;
		}
		edges = new ArrayList<String>();
		i = 1;

		for (int j = 0; j < getV(); ++j) {
			if (num[j] == 0) {
				DFS(j);
			}
		}
		System.out.println(edges);
	}

	public void breadthFirstSearch() {

		// !!!!check if graph is ZERO!!!
		if (V == 0) {
			return;
		}

		num = new int[V];

		for (int u = 0; u < getV(); ++u) {
			num[u] = 0;
		}

		edges = new ArrayList<String>();
		i = 1;

		// Create a queue for BFS using an ArrayList
		queue = new ArrayList<Integer>();

		for (int v = 0; v < getV(); ++v) {
			if (num[v] == 0) {
				// Mark the current node as visited and enqueue it
				num[v] = i++;
				queue.add(v);

				while (!queue.isEmpty()) {

					int temp = queue.remove(0);

					for (int u = 0; u < getV(); ++u) {
						// printNum();
						if (adjacencyMatrix[temp][u] == true) {
							if (num[u] == 0) {
								num[u] = i++;
								queue.add(u);
								edges.add(temp + ":" + u);
							}
						}
					}
				}
			}
		}
		System.out.println(edges);
	}

	public StringTokenizer loadTokanizer(String file) {
		boolean success = false;
		String fileData = "";

		try {
			File graphFile = new File(file);
			FileInputStream graphIn = new FileInputStream(graphFile);

			byte[] data = new byte[1];
			while (graphIn.read(data) > 0) {
				fileData += new String(data);
			}

			success = true;
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("IOException");
			System.exit(1);
		}

		if (success) {
			StringTokenizer token = new StringTokenizer(fileData, " \n\r");
			return token;
		}

		return null;
	}

	public void buildFromFile(String file) {
		StringTokenizer token = loadTokanizer(file);

		while (token.hasMoreTokens()) {

			// System.out.print(token.nextToken(" "));
			String x = token.nextToken();
			String p = token.nextToken();

			int stop = Integer.parseInt(p);
			int num = Integer.parseInt(x);

			for (int i = 0; i < stop; ++i) {
				addEdge(num, Integer.parseInt(token.nextToken()));
			}
		}
	}

	@Override
	public String toString() {
		String temp = "  ";

		for (int i = 0; i < V; i++) {
			temp += " " + (i < 10 ? " " : "") + i;
		}

		temp += "\n";

		for (int i = 0; i < V; i++) {
			temp += (i < 10 ? " " : "") + i;
			for (int j = 0; j < V; j++) {
				temp += "  " + (adjacencyMatrix[i][j] == true ? "1" : "-");
			}
			temp += "\n";
		}
		return temp;
	}

	public void printGraph() {
		System.out.println(this.toString());
	}

	public void printNum() {
		for (int U = 0; U < this.V; U++) {
			if (U == this.V - 1) {
				System.out.println(this.num[U]);
			} else {
				System.out.print(this.num[U] + " ");
			}
		}
	}
}

