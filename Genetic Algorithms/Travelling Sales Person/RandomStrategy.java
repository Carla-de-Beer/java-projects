
/**
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
			System.out.println(getBestFitness());
			calculateBestEver();
			generatePopulation();
			counter++;
		}
	}

	@Override
	public void generatePopulation() {

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
				// if you are crossing over, mutate
				// Mutate, if applicable
				if (getMutationRate() > mRand) {
					mutate(child);
				}
				// Populate the ArrayList newPopulation
				// with the offspring
				Route newRoute = new Route(child, false);
				newPopulationList.add(newRoute);
			}
		}

		nextPopulationList = new ArrayList<Route>(newPopulationList);

		// Apply elitism if required; sort hashmaps by value
		if (getnumElite() > 0) {
			// populationList = new
			// ArrayList<Route>(createEliteList(nextPopulationList));
			setPopulationList(createEliteList(nextPopulationList));
		} else {
			// else, if no elitism applied, carry new population over as is
			// populationList = new ArrayList<Route>(newPopulationList);
			setPopulationList(newPopulationList);
		}
		newPopulationList.clear();
	}

	@Override
	public void printStartInfo() {
		System.out.println("GENETIC ALGORITHM\n");
		System.out.println("Population size: " + getNumPop());
		System.out.println("Max number generations: " + getMaxIter());
		System.out.println("Strategy: Random");
		System.out.println("1-point crossover strategy: MOX");
		System.out.println("Crossover rate: " + getCrossOverRate() * 100 + "%");
		System.out.println("Mutation rate: " + getMutationRate() * 100 + "%");
		System.out.println("\nRESULTS:\n");
	}

	@Override
	public void printResult() {
		System.out.println("\nProcessing complete.\n");
		System.out.println("\n****************************************************");
		System.out.println();
		System.out.println("Optimal fitness value: " + getOverallBestFitness());
		System.out.print("Optimal route: ");
		for (int i = 0; i < getNumCities(); ++i) {
			System.out.print(getOverallBestRoute().get(i).getName());
			if (i < getNumCities() - 1) {
				System.out.print("->");
			}
		}
	}

}
