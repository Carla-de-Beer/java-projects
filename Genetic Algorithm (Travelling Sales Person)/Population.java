
/**
 * The crossover strategy makes use of Modified Order Crossover (MOX), as described in:
 * http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.91.9167&rep=rep1&type=pdf
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Population {

	public int numCities;
	public int numPop;
	public int maxIter;
	public double crossoverRate;
	public double mutationRate;
	public double percentageGap;
	public Route optimalRoute;
	public double optimalValue;
	public double average = 0.0;
	public MyRandom myRandom = new MyRandom();
	public ArrayList<Route> populationList = new ArrayList<Route>();
	public ArrayList<City> bestEverRoute = new ArrayList<City>();
	public ArrayList<City> overallBestRoute = new ArrayList<City>();
	public double overallBestFitness = Double.POSITIVE_INFINITY;

	public Population(ArrayList<Route> populationList, int numPop, int maxIter, double crossoverRate,
			double mutationRate, int numCities) {
		this.numCities = numCities;
		this.numPop = numPop;
		this.maxIter = maxIter;
		this.crossoverRate = crossoverRate / 100;
		this.mutationRate = mutationRate / 100;
		this.populationList = new ArrayList<Route>(populationList);
		this.optimalRoute = null;
		this.optimalValue = Double.POSITIVE_INFINITY;
	}

	public void runGA() {

		printStartInfo();
		int counter = 0;

		// Outer while loop that runs for the number of generations required
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
