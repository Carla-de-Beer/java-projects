// Carla de Beer
// Graphs: Dijkstra's shortest path algorithm, cycle detection, strongly connected search
// Date created: 25/04/2014

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Graph {

	// Maximum number of vertices allowed
	protected int V;
	// Number of edges currently in the graph
	protected int E;
	// The adjacency matrix
	protected int[][] matrix;
	// Distance array
	public int[] dist;
	// Pred array
	public int[] pred;
	// Visitation number
	private int i;
	// Cycle detection
	private int[] num;

	public Graph(int V) {
		this.V = V;
		this.E = 0;
		this.matrix = new int[V][V];
	}

	public int getV() {
		return V;
	}

	public int getE() {
		return E;
	}

	public void addEdge(int from, int to, int weight) {
		if (matrix[from][to] == 0) {
			matrix[from][to] = weight;
			E++;
		}
	}

	public static Graph buildFromFile(String file) {
		Graph graph = null;

		try {
			FileInputStream fstream = new FileInputStream(file);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			graph = new Graph(new Integer(br.readLine()));

			String strLine;
			while ((strLine = br.readLine()) != null) {
				String[] temp = strLine.split(" ");

				int from = temp[0].charAt(0) - 'a' + 0;
				int to = temp[1].charAt(0) - 'a' + 0;
				int weight = Integer.parseInt(temp[2]);

				graph.addEdge(from, to, weight);
			}
			in.close();
			br.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

		return graph;
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
				temp += "  " + (matrix[i][j] == 0 ? "-" : matrix[i][j]);
			}
			temp += "\n";
		}

		return temp;
	}

	public boolean dijkstra(char from, char to) {
		return dijkstra(from - 'a' + 0, to - 'a' + 0);
	}

	public boolean dijkstra(int from, int to) {

		// !!!!check if graph is ZERO!!!
		if (V == 0)
			return false;

		num = new int[V];
		dist = new int[V];
		pred = new int[V];

		ArrayList<Integer> toBeChecked = new ArrayList<Integer>();

		for (int v = 0; v < getV(); v++) {
			dist[v] = Integer.MAX_VALUE;
			toBeChecked.add(v);
			pred[v] = -1;
			// System.out.println(toBeChecked.get(v));
		}

		// System.out.println(toBeChecked.size()); // works

		dist[from] = 0;

		while (!toBeChecked.isEmpty()) {

			int v = vertexCheck(dist, toBeChecked);

			// System.out.println(toBeChecked.);
			// System.out.println("v = " + v);

			// int i = toBeChecked.indexOf(v);
			// System.out.println("i = " + i);
			int temp = 0;

			for (int k = 0; k < toBeChecked.size(); ++k) {
				if (v == toBeChecked.get(k)) {
					temp = k;
				}
			}
			toBeChecked.remove(temp);
			// toBeChecked.remove(v);

			for (int k = 0; k < toBeChecked.size(); k++) {
				// System.out.println(toBeChecked.get(k));
			}

			for (int u = 0; u < getV(); u++) {
				if (toBeChecked.contains(u) && matrix[v][u] != 0) {

					if (dist[u] > (dist[v] + matrix[v][u])) {
						dist[u] = dist[v] + matrix[v][u];
						pred[u] = v;
					}
					// System.out.println("matrix = " + matrix[v][u]);
				}
			}

			for (int k = 0; k < V; k++) {
				// System.out.println("dist = " + dist[k]);
			}
		}

		for (int k = 0; k < V; k++) {
			if (dist[k] == Integer.MAX_VALUE)
				return false;
		}

		return true;
	}

	private int vertexCheck(int[] numbers, ArrayList<Integer> toBeChecked) {
		int minValue = Integer.MAX_VALUE;
		int vertex = 0;
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] < minValue && toBeChecked.contains(i)) {
				minValue = numbers[i];
				vertex = i;
			}
		}
		return vertex;
	}

	public ArrayList<String> cycles;

	public void detectCycles() {

		// !!!!check if graph is ZERO!!!
		if (V == 0)
			return;

		num = new int[V];
		pred = new int[V];

		for (int v = 0; v < getV(); ++v) {
			num[v] = 0;
		}
		cycles = new ArrayList<String>();
		i = 1;

		for (int j = 0; j < getV(); ++j) {
			if (num[j] == 0) {
				detectCycles(j);
			}
		}
	}

	public void detectCycles(int v) {
		num[v] = i++;

		for (int u = 0; u < getV(); u++) {
			if (matrix[v][u] != 0) {
				if (num[u] == 0) {
					pred[u] = v;
					detectCycles(u);
				} else if (num[u] != Integer.MAX_VALUE) {
					pred[u] = v;
					// cycles.add(u + "->" + v + "->" + pred[v] + "->" + u);
					cycles.add(v + "->" + u + "->" + pred[v] + "->" + v);
				}

			}
		}
		num[v] = Integer.MAX_VALUE;
	}

	public ArrayList<String> scc;

	ArrayList<Integer> stack = new ArrayList<Integer>();

	public void stronglyConnectedSearch() {

		// !!!!check if graph is ZERO!!!
		if (V == 0)
			return;
		num = null;
		num = new int[V];

		pred = null;
		pred = new int[V];

		scc = new ArrayList<String>();

		i = 1;
		for (int v = 0; v < getV(); v++) {

			if (num[v] == 0) {
				strongDFS(v);
			}
		}
	}

	protected void strongDFS(int v) {

		pred[v] = num[v] = i++;
		stack.add(v);
		for (int u = 0; u < getV(); u++) {
			if (matrix[v][u] != 0) {

				if (num[u] == 0) {
					strongDFS(u);
					pred[v] = Math.min(pred[v], pred[u]);
				} else if (num[u] < num[v] && stack.contains(u)) {
					pred[v] = Math.min(pred[v], pred[u]);
				}
			}
		}
		if (pred[v] == num[v]) {
			String result = "";
			int w = (stack.remove(stack.size() - 1)).intValue();
			while (w != v) {
				result += w + " ";
				w = (stack.remove(stack.size() - 1)).intValue();
			}
			result += w;
			scc.add(result);
		}
	}
}

