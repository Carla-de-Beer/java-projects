// Carla de Beer
// The Knapsack Problem is a well known problem of combinatorial optimisation. 
// This code provides a naive recursive implementation to this problem.
// Time complexity for this algorithm is O(n^2).
// Date created: 17/02/2014; revised: January 2019

public class Knapsack {
	// values
	private double[] values;
	// weights
	private double[] weights;
	// maximum weight
	private final double W = 5.0;

	Knapsack() {
		// values
		values = new double[] { 8.0, 7.0, 4.0, 5.0, 3.0, 7.0, 9.5, 4.5 };
		// weights
		weights = new double[] { 1.8, 1.2, 1.0, 2.4, 3.6, 4.0, 1.4, 1.8 };
	}

	public double start() {
		return knapsack(weights.length - 1, W);
	}

	private double knapsack(int w, double c) {
		if (w == 0 || c == 0) {
			return 0;
		} else if (weights[w] > c) {
			return knapsack(w - 1, c); // don't add the item
		} else {
			double value1 = knapsack(w - 1, c); // don't add the item
			double value2 = values[w] + knapsack(w - 1, c - weights[w]); // add the item
			return Math.max(value1, value2);
		}
	}
}