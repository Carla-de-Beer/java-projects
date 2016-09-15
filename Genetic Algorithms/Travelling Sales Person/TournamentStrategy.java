
/**
 * Class that runs a GA on the basis of a tournament selection strategy.
 * The crossover strategy makes use of Modified Order Crossover (MOX), as described in:
 * http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.91.9167&rep=rep1&type=pdf
 */

import java.util.ArrayList;
import java.util.Random;

public class TournamentStrategy extends Strategy {

	private int tournamentSize;
	ArrayList<Route> tournamentList = new ArrayList<Route>();
	private ArrayList<Double> tourFitness = new ArrayList<Double>();

	public TournamentStrategy(ArrayList<Route> populationList, int numPop, int maxIter, double crossoverRate,
			double mutationRate, double generationGap, int numCities, int tournamentSize) {
		super(populationList, numPop, maxIter, crossoverRate, mutationRate, generationGap, numCities);

		this.tournamentSize = tournamentSize;
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

	private void generatePopulation() {

		ArrayList<Route> newPopulationList = new ArrayList<Route>();
		ArrayList<Route> nextPopulationList = new ArrayList<Route>();

		while (newPopulationList.size() < getNumPop()) {
			ArrayList<City> parentA = new ArrayList<City>();
			ArrayList<City> parentB = new ArrayList<City>();
			ArrayList<City> child = new ArrayList<City>();

			Route tourA = null;
			Route tourB = null;

			tournamentList.clear();
			for (int i = 0; i < tournamentSize; ++i) {
				tournamentList.add(tournamentSelector());
			}

			for (int i = 0; i < tournamentSize; ++i) {
				tourFitness.add(tournamentList.get(i).calculateFitness());
			}

			tourA = getFittestTour(tourA);
			tourB = getFittestTour(tourB);

			parentA = tourA.getChromosome();
			parentB = tourB.getChromosome();

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

		// Apply elitism, if required
		if (getNumElite() > 0) {
			setPopulationList(createEliteList(nextPopulationList));
		} else {
			setPopulationList(newPopulationList);
		}
		newPopulationList.clear();
	}

	private Route tournamentSelector() {
		Random r = new Random();
		int randVal = r.nextInt(getNumPop());
		return getPopulationList().get(randVal);
	}

	private Route getFittestTour(Route tour) {
		double fittest = 0.0;
		double fitnessValue = 0.0;
		for (int i = 0; i < tournamentSize; ++i) {
			fitnessValue = tourFitness.get(i);
			if (fittest < fitnessValue) {
				tour = tournamentList.get(i);
				fittest = tourFitness.get(i);
			}
		}
		return tour;
	}

	public void printStartInfo() {
		System.out.println("GENETIC ALGORITHM\n");
		System.out.println("Selection strategy: Tournament Selection");
		System.out.println("Population size: " + getNumPop());
		System.out.println("Max number generations: " + getMaxIter());
		System.out.println("1-point crossover strategy: MOX");
		System.out.println("Crossover rate: " + getCrossOverRate() * 100 + "%");
		System.out.println("Mutation rate: " + getMutationRate() * 100 + "%");
		if (getNumElite() > 0) {
			System.out.println("Elitism applied: Yes");
			System.out.println("Generation gap: " + getNumElite() + " members");
		} else {
			System.out.println("Elitism applied: No");
		}
		System.out.println("Tournament size: " + tournamentSize);
		System.out.println("\nBest fitness level per generation:\n");

	}

}
