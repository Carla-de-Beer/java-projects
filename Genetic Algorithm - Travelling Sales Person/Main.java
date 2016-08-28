import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static final int NUM_CITIES = 20;
	private static ArrayList<City> path = new ArrayList<>();
	private static Route route;
	private static RandomStrategy randomStrategy;

	public static void main(String[] args) {

		int maxIter = 200;
		int numPop = 100;
		double crossoverRate = 80.0;
		double mutationRate = 10.0;
		double percentageGap = 1.0;

		// Dummy test data for now
		String[] names = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
				"S", "T" };
		double[] lat = { 10.05, 27.03, 81.87, 33.67, 82.98, 24.34, 77.35, 73.82, 12.36, 63.25, 23.83, 65.83, 23.46,
				67.21, 48.39, 24.89, 74.35, 25.87, 36.78, 76.37 };
		double[] lon = { 67.33, 73.23, 83.48, 72.62, 26.78, 87.88, 65.78, 89.52, 31.24, 56.87, 18.94, 45.89, 34.76,
				63.67, 34.82, 76.51, 78.35, 23.78, 38.23, 54.27 };

		for (int i = 0; i < names.length; ++i) {
			double x = lat[i];
			double y = lon[i];
			path.add(new City(x, y, names[i]));
		}

		route = new Route(path);
		randomStrategy = new RandomStrategy(path, numPop, maxIter, crossoverRate, mutationRate, percentageGap);
		randomStrategy.printStartInfo();
		randomStrategy.runGA();
		System.out.print("Optimal route: ");
		for (int i = 0; i < route.chromosome.size(); ++i) {
			System.out.print(randomStrategy.getOptimalRoute().getChromosome().get(i).getName());
		}
	}

	private static int randomInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min)) + min;

		return randomNum;
	}

	private static int randomInt(int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max));
		return randomNum;
	}

	private static double randomDouble() {
		Random rand = new Random();
		double randomNum = 1 * rand.nextDouble();
		return randomNum;
	}

}
