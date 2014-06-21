// Carla de Beer
// Knapsack algorithm - recursive implementation
// Date created: 17/02/2014

public class Knapsack {

	// values
	public double[] values = new double[] { 8.0, 7.0, 4.0, 5.0, 3.0, 7.0, 9.5,
			4.5 };
	// weights
	public double[] weights = new double[] { 1.8, 1.2, 1.0, 2.4, 3.6, 4.0, 1.4,
			1.8 };
	// maximum weight
	public double W = 5.0;

	public double start() {
		// entry point into recursive function
		return knapsack(values.length - 1, W);
	}

	public double knapsack(int i, double W) {
		if (i < 0) {
			return 0;
		}
		if (weights[i] > W) {
			return knapsack(i - 1, W);
		} else {
			return Math.max(knapsack(i - 1, W), knapsack(i - 1, W - weights[i])
					+ values[i]);
		}
	}
}
