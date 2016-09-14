
/**
 * The crossover strategy makes use of Modified Order Crossover (MOX), as described in:
 * http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.91.9167&rep=rep1&type=pdf
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Population {

	private int numCities;
	private int numPop;
	private int maxIter;
	private double crossoverRate;
	private double mutationRate;
	private Route optimalRoute;
	private double optimalValue;
	private int numElite;
	private MyRandom myRandom = new MyRandom();
	private ArrayList<Route> populationList = new ArrayList<Route>();
	private ArrayList<City> overallBestRoute = new ArrayList<City>();
	private double overallBestFitness = Double.POSITIVE_INFINITY;

	public Population(ArrayList<Route> populationList, int numPop, int maxIter, double crossoverRate,
			double mutationRate, double generationGap, int numCities) {
		this.numCities = numCities;
		this.numPop = numPop;
		this.maxIter = maxIter;
		this.crossoverRate = crossoverRate / 100;
		this.mutationRate = mutationRate / 100;
		numElite = (int) (this.numPop * generationGap / 100);
		this.populationList = new ArrayList<Route>(populationList);
		this.optimalRoute = null;
		this.optimalValue = Double.POSITIVE_INFINITY;
	}

	public void runGA() {

		printStartInfo();
		int counter = 0;

		// Outer while loop that runs for the number of generations required
		// while (optimalValue > 610.00) {
		while (counter < maxIter) {
			calculateOptimal();
			System.out.println(getBestFitness());
			calculateBestEver();
			generatePopulation();
			counter++;
		}
	}

	public void generatePopulation() {

		ArrayList<Route> newPopulationList = new ArrayList<Route>();

		while (newPopulationList.size() < numPop) {
			ArrayList<City> parentA = new ArrayList<City>();
			ArrayList<City> parentB = new ArrayList<City>();
			ArrayList<City> child = new ArrayList<City>();

			// Randomly select two parents from the population
			int randA = myRandom.randomInt(numPop);
			int randB = myRandom.randomInt(numPop);
			parentA = (populationList.get(randA).getChromosome());
			parentB = (populationList.get(randB).getChromosome());

			double cProb = Math.random();
			double mRand = Math.random();

			// Crossover, if applicable
			if (crossoverRate > cProb) {
				crossover(parentA, parentB, child);
			} else {
				child = new ArrayList<City>(parentA);
			}

			// Mutate, if applicable
			if (mutationRate > mRand) {
				mutate(child);
			}

			// Populate the ArrayList newPopulation
			// with the offspring
			Route newRoute = new Route(child, false);
			newPopulationList.add(newRoute);
		}

		populationList = new ArrayList<Route>(newPopulationList);

		// Apply elitism if required;
		// makes use of hashmaps sorted by value
		if (numElite > 0) {

			HashMap<Route, Double> map = new HashMap<Route, Double>();
			for (int i = 0; i < populationList.size(); ++i) {
				map.put(populationList.get(i), populationList.get(i).calculateFitness());
			}

			Set<Entry<Route, Double>> set = map.entrySet();
			List<Entry<Route, Double>> ascendingList = new ArrayList<Entry<Route, Double>>(set);
			List<Entry<Route, Double>> descendingList = new ArrayList<Entry<Route, Double>>(set);

			// Sort ascending
			Collections.sort(ascendingList, new Comparator<Map.Entry<Route, Double>>() {
				public int compare(Map.Entry<Route, Double> value1, Map.Entry<Route, Double> value2) {
					return (value1.getValue()).compareTo(value2.getValue());
				}
			});

			// Sort descending
			Collections.sort(descendingList, new Comparator<Map.Entry<Route, Double>>() {
				public int compare(Map.Entry<Route, Double> value1, Map.Entry<Route, Double> value2) {
					return (value2.getValue()).compareTo(value1.getValue());
				}
			});

			// for (Map.Entry<Route, Double> entry : list) {
			// System.out.println(entry.getKey() + " => " + entry.getValue());
			// }

			ArrayList<Route> eliteList = new ArrayList<Route>();
			for (int i = 0; i < numElite; ++i) {
				descendingList.set(i, ascendingList.get(i));
			}

			for (Map.Entry<Route, Double> entry : descendingList) {
				eliteList.add(entry.getKey());
			}

			populationList.clear();
			populationList = new ArrayList<Route>(eliteList);
		}
	}

	public void crossover(ArrayList<City> parentA, ArrayList<City> parentB, ArrayList<City> child) {

		ArrayList<City> end = new ArrayList<City>();
		int rand = myRandom.randomInt(numCities);

		// Copy over first part of the chromosome
		for (int i = 0; i < rand; ++i) {
			child.add(parentA.get(i));
		}

		// Copy over second part of the chromosome
		for (int i = rand; i < parentA.size(); ++i) {
			end.add(parentA.get(i));
		}

		int[] nums = new int[end.size()];

		// get index values
		for (int i = 0; i < end.size(); ++i) {
			City x = end.get(i);
			for (int j = 0; j < parentB.size(); ++j) {
				if (x.getName().equals(parentB.get(j).getName())) {
					nums[i] = j;
				}
			}
		}

		Arrays.sort(nums);
		ArrayList<City> res = new ArrayList<City>();

		for (int i = 0; i < nums.length; ++i) {
			res.add(parentB.get(nums[i]));
		}

		// concatenate the two parts
		child.addAll(res);
	}

	public void mutate(ArrayList<City> path) {
		int rand1 = myRandom.randomInt(numCities);
		int rand2 = myRandom.randomInt(numCities);
		Collections.swap(path, rand1, rand2);
	}

	public void calculateOptimal() {
		double fitnessValue = 0.0;
		for (int i = 0; i < populationList.size(); ++i) {
			fitnessValue = populationList.get(i).calculateFitness();
			if (fitnessValue < optimalValue) {
				optimalRoute = new Route(populationList.get(i));
				optimalValue = fitnessValue;
			}
		}
	}

	private void calculateBestEver() {
		ArrayList<City> currentBestRoute = optimalRoute.getChromosome();
		double currentBestFitness = optimalValue;
		if (currentBestFitness < overallBestFitness) {
			overallBestRoute = new ArrayList<City>(currentBestRoute);
			overallBestFitness = currentBestFitness;
		}
	}

	public final Route getOptimalRoute() {
		return optimalRoute;
	}

	public final ArrayList<City> getBestSolution() {
		return optimalRoute.getChromosome();
	}

	public final double getBestFitness() {
		return optimalValue;
	}

	public void printStartInfo() {
		System.out.println("GENETIC ALGORITHM\n");
		System.out.println("Population size: " + numPop);
		System.out.println("Max number generations: " + maxIter);
		System.out.println("Strategy: Random");
		System.out.println("1-point cross-over: Yes");
		System.out.println("Cross-over rate: " + crossoverRate * 100 + "%");
		System.out.println("Mutation rate: " + mutationRate * 100 + "%");
		System.out.println("\nRESULTS:\n");
	}

	public void printResult() {
		System.out.println("\nProcessing complete.\n");
		System.out.println("\n****************************************************");
		System.out.println();
		System.out.println("Optimal fitness value: " + overallBestFitness);
		System.out.print("Optimal route: ");
		for (int i = 0; i < numCities; ++i) {
			System.out.print(overallBestRoute.get(i).getName());
			if (i < numCities - 1) {
				System.out.print("->");
			}
		}
	}
}
