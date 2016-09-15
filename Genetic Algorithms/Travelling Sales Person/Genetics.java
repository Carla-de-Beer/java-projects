
/**
 * Genetics class that enables the Strategy Design Pattern.
 * Runs the GA and calculates the Haversine distance.
 */

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Genetics {

	private Strategy strategy;
	private double sumHaversine = 0.0;

	public Genetics(Strategy strategy) {
		this.strategy = strategy;
	}

	public void start() {
		strategy.runGA();
		strategy.printResult();
		printDistanceTravelled();
	}

	private void printDistanceTravelled() {
		calculateHaversine(strategy.getBestSolution());
		System.out.println();
		NumberFormat formatter = new DecimalFormat("#0.000");
		System.out.println("Haversine distance: " + formatter.format(sumHaversine) + "km");
	}

	private double haversine(double lat1, double lat2, double lon1, double lon2) {
		double p = 0.017453292519943295;
		double a = 0.5 - Math.cos((lat2 - lat1) * p) / 2
				+ Math.cos(lat1 * p) * Math.cos(lat2 * p) * (1 - Math.cos((lon2 - lon1) * p)) / 2;
		return 12742 * Math.asin(Math.sqrt(a));
	}

	private void calculateHaversine(ArrayList<City> path) {
		for (int i = 0; i < strategy.getNumCities() - 1; ++i) {
			sumHaversine += haversine(path.get(i).getLat(), path.get(i + 1).getLat(), path.get(i).getLon(),
					path.get(i + 1).getLon());
		}
	}
}
