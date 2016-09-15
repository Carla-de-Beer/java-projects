
/**
 * Class that runs a GA on the basis of a random selection strategy.
 * The crossover strategy makes use of Modified Order Crossover (MOX), as described in:
 * http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.91.9167&rep=rep1&type=pdf
 */

import java.util.ArrayList;

public class RandomStrategy extends Strategy {

	private MyRandom myRandom = new MyRandom();

	public RandomStrategy(ArrayList<Route> populationList, int numPop, int maxIter, double crossoverRate,
			double mutationRate, double generationGap, int numCities) {
		super(populationList, numPop, maxIter, crossoverRate, mutationRate, generationGap, numCities);
	}

	@Override
	public void runGA() {
		printStartInfo();
		int counter = 0;

		// Outer while loop that runs for the number of generations required
		while (counter < getMaxIter()) {
			calculateOptimal();
			if (counter < 9) {
				System.out.println((counter + 1) + ":   " + getBestFitness());
			} else if (counter >= 10 && counter < 99) {
				System.out.println((counter + 1) + ":  " + getBestFitness());
			} else if (counter >= 100 && counter < 999) {
				System.out.println((counter + 1) + ": " + getBestFitness());
			}

			calculateBestEver();
			generatePopulation();
			counter++;
		}
	}

	private void generatePopulation() {

		ArrayList<Route> newPopulationList = new ArrayList<Route>();
		ArrayList<Route> nextPopulationList = new ArrayList<Route>();

		while (newPopulationList.size() < getNumPop()) {
			ArrayList<City> parentA = new ArrayList<City>();
			ArrayList<City> parentB = new ArrayList<City>();
			ArrayList<City> child = new ArrayList<City>();

			// Randomly select two parents from the population
			int randA = myRandom.randomInt(getNumPop());
			int randB = myRandom.randomInt(getNumPop());
			parentA = (getPopulationList().get(randA).getChromosome());
			parentB = (getPopulationList().get(randB).getChromosome());

			double cProb = Math.random();
			double mRand = Math.random();

			// Crossover, if applicable
			if (getCrossOverRate() > cProb) {
				crossover(parentA, parentB, child);
				// If crossover: mutate, if applicable
				if (getMutationRate() > mRand) {
					mutate(child);
				}
				// PopulatenewPopulation with the offspring
				Route newRoute = new Route(child, false);
				newPopulationList.add(newRoute);
			}
		}

		nextPopulationList = new ArrayList<Route>(newPopulationList);

		// Apply elitism if required
		if (getNumElite() > 0) {
			setPopulationList(createEliteList(nextPopulationList));
		} else {
			setPopulationList(newPopulationList);
		}
		newPopulationList.clear();
	}

	public void printStartInfo() {
		System.out.println("GENETIC ALGORITHM\n");
		System.out.println("Selection strategy: Random Selection");
		printStartInfoStrategy();
	}
}
