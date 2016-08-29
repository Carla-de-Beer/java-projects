import java.util.ArrayList;
import java.util.Collections;

public class Route {

	public ArrayList<City> chromosome;
	public double fitness;
	public double normFitness;

	public Route(ArrayList<City> path) {
		this.chromosome = new ArrayList<>();
		for (City c : path) {
			this.chromosome.add(c);
		}
		// Shuffle the ArrayList to obtain different permutations
		Collections.shuffle(chromosome);
	}

	public double calculateFitness() {
		double sum = sumDistance(chromosome);
		fitness = sum;
		return fitness;
	}

	private double sumDistance(ArrayList<City> path) {
		double sum = 0;
		for (int i = 0; i < path.size() - 1; ++i) {
			City a = path.get(i);
			City b = path.get(i + 1);
			double d = distSq(a.getLon(), a.getLat(), b.getLon(), b.getLat());
			sum += d;
		}
		return sum;
	}

	private double distSq(double x1, double y1, double x2, double y2) {
		return ((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}

	public final double getFitness() {
		return fitness;
	}

	public final ArrayList<City> getChromosome() {
		return chromosome;
	}

	public void setChromosome(ArrayList<City> chromosome) {
		this.chromosome = chromosome;
	}

}
