import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * RouletteStrategy class: implementation of a genetic algorithm, with roulette
 * selection, to solve the trading optimisation problem.
 * 
 * @author cadebe
 * 
 */
public class RouletteStrategy extends Strategy {

	private String filePath;
	private int numPop;
	private int maxIter;
	private double crossoverRate;
	private double mutationRate;
	private double percentageGap;
	private int generationGap;
	private Trader optimalTrader;
	double optimalValue;
	double average = 0.0;

	private ArrayList<Trader> newPopulationList = new ArrayList<Trader>();
	private ArrayList<Trader> currentPopulationList = new ArrayList<Trader>();

	private ArrayList<Double> currentListFitness = new ArrayList<Double>();
	private ArrayList<Double> newListFitness = new ArrayList<Double>();

	private ArrayList<Trader> rouletteList = new ArrayList<Trader>();

	/**
	 * Parameterised constructor for Trader; takes the filepath of the input
	 * file as argument, as well as an integer value to define the population
	 * size, an integer value to express the maximum number of iterations, a
	 * String value to define the selection strategy, a double value to express
	 * the crossover rate, a double value for the mutation rate, an integer
	 * value for the tournament size, if tournament selection is used, and an
	 * integer value to define the generation gap.
	 * 
	 * @param filePath_
	 * @param numPop_
	 * @param maxIter_
	 * @param strategy_
	 * @param crossoverRate_
	 * @param mutationRate_
	 * @param tournamentSize_
	 * @param percentageGap_
	 * @throws IOException
	 */
	public RouletteStrategy(String filePath, int numPop, int maxIter,
			double crossoverRate, double mutationRate, double percentageGap)
			throws IOException {
		super();

		this.filePath = filePath;
		this.numPop = numPop;
		this.maxIter = maxIter;
		this.crossoverRate = crossoverRate;
		this.mutationRate = mutationRate;
		this.percentageGap = percentageGap;
		this.generationGap = (int) (this.numPop * this.percentageGap / 100.0);
		this.optimalTrader = new Trader(this.filePath);
		this.optimalValue = 0.0;
	}

	/**
	 * Method that implements the genetic algorithm.
	 * 
	 * @throws IOException
	 */
	public void runGA() throws IOException {

		// Start with a clean slate
		newPopulationList.clear();

		// Initialise ArrayList of traders
		for (int i = 0; i < numPop; ++i) {
			currentPopulationList.add(new Trader(filePath));
			// System.out.println(list.get(i).getChromosome());
		}

		int counter = 0;
		double sum = 0.0;

		createRouletteList();

		// Outer while loop that runs for the number of generations required
		while (counter < maxIter) {

			// Inner while loop that fills the newPopulationList ArrayList
			// before elitism is applied

			while (newPopulationList.size() < numPop) {

				String parentA = "";
				String parentB = "";
				String childA = "";
				String childB = "";

				// Roulette selection is used

				if (rouletteList.size() > 0) {
					Random r = new Random();
					int rand1 = r.nextInt(rouletteList.size() - 0);
					int rand2 = r.nextInt(rouletteList.size() - 0);

					// System.out.println("rand1: " + rand1);
					// System.out.println("rand2: " + rand2);

					parentA = rouletteList.get(rand1).getChromosome();
					parentB = rouletteList.get(rand2).getChromosome();
				} else {
					// in case the roulette list is empty
					// resort to random selection
					int rand1 = randomGenerator();
					int rand2 = randomGenerator();

					parentA = currentPopulationList.get(rand1).getChromosome();
					parentB = currentPopulationList.get(rand2).getChromosome();
				}

				double crossRand = Math.random();
				double muteRand = Math.random();

				// Crossover, if applicable
				if (crossoverRate > crossRand) {

					StringBuilder a = new StringBuilder();
					StringBuilder b = new StringBuilder();

					crossOver(a, b, parentA, parentB, 1);

					childA = a.toString();
					childB = b.toString();

				} else {
					childA = parentA;
					childB = parentB;
				}

				// Mutate, if applicable
				if (mutationRate > muteRand) {

					StringBuilder sb_a = new StringBuilder();
					StringBuilder sb_b = new StringBuilder();

					mutate(sb_a, childA);
					mutate(sb_b, childB);

					childA = sb_a.toString();
					childB = sb_b.toString();
				}

				// Populate the ArrayList newPopulation
				// with the offspring

				Trader new1 = new Trader(filePath);
				Trader new2 = new Trader(filePath);

				new1.setChromosome(childA);
				new2.setChromosome(childB);

				newPopulationList.add(new2);
				newPopulationList.add(new1);
			}

			counter++;

			getOptimal();

			System.out.println(getBestFitness());
			sum += getBestFitness();

			// Apply elitism, only if the generation gap > 0
			if (generationGap > 0) {

				createCurrentFitnessList(currentPopulationList);
				createNextFitnessList(newPopulationList);

				double[] listArray = new double[currentListFitness.size()];
				Trader[] listTraderArray = new Trader[currentPopulationList
						.size()];

				double[] newListArray = new double[newListFitness.size()];
				Trader[] newlistTraderArray = new Trader[newPopulationList
						.size()];

				for (int i = 0; i < numPop; ++i) {
					listArray[i] = currentListFitness.get(i);
					listTraderArray[i] = currentPopulationList.get(i);
					newListArray[i] = newListFitness.get(i);
					newlistTraderArray[i] = newPopulationList.get(i);
				}

				selectionSort(listArray, listTraderArray);
				selectionSort(newListArray, newlistTraderArray);

				currentPopulationList = new ArrayList<Trader>(
						Arrays.asList(listTraderArray));
				newPopulationList = new ArrayList<Trader>(
						Arrays.asList(newlistTraderArray));

				// Create a new ArrayList handOver that forms the hand over
				// from one generation to the next,
				// consisting of the best part of the current generation
				// replacing the worst part of the next generation
				ArrayList<Trader> handOver = new ArrayList<Trader>();

				for (int i = numPop - generationGap; i < currentPopulationList
						.size(); ++i)
					handOver.add(currentPopulationList.get(i));

				for (int i = generationGap; i < numPop; ++i)
					handOver.add(newPopulationList.get(i));

				currentPopulationList = new ArrayList<Trader>(handOver);
			}
			// Else if the generation gap value is zero,
			// replace entire current generation with the new generation
			else
				currentPopulationList = new ArrayList<Trader>(newPopulationList);

			newPopulationList.clear();

			// End elitism
		}

		// output average fitness value after each generation
		average = sum / ((double) maxIter);
		System.out.println();

		super.printEndInfo();

	}

	/**
	 * Method that gets the optimal trader and its solution.
	 * 
	 * @throws IOException
	 */
	public void getOptimal() throws IOException {
		double fitnessValue = 0.0;
		for (int i = 0; i < newPopulationList.size(); ++i) {
			fitnessValue = newPopulationList.get(i).calcFitness();
			// System.out.println(fitnessValue);
			if (optimalValue < fitnessValue) {
				optimalTrader = newPopulationList.get(i);
				optimalValue = fitnessValue;
			}
		}
	}

	/**
	 * Getter method that gets the best solution, for external use
	 * 
	 * @return the optimal trader's chromosome as a string value
	 */
	public final String getBestSolution() {
		return optimalTrader.getChromosome();
	}

	/**
	 * Getter method that gets the best trader, for external use
	 * 
	 * @return optimalValue which is a double representing the optimal trader's
	 *         fitness value
	 */
	public final double getBestFitness() {
		return optimalValue;
	}

	/**
	 * Random value generator for use in the random selection strategy.
	 * Generates a random integer value from the number of individuals in the
	 * population.
	 * 
	 * @return randval integer value
	 */
	public final int randomGenerator() {

		Random r = new Random();
		int randVal = r.nextInt(numPop - 0);
		// System.out.println(randVal);

		return randVal;
	}

	/**
	 * Getter method that returns the average fitness value over the run of
	 * maximum iterations, for external use
	 * 
	 * @return
	 */
	public final double getAverage() {
		return average;
	}

	/**
	 * Method that populates the ArrayList rouletteList with Trader objects, the
	 * quantity of which depends on the frequency of the trader relative to its
	 * fitness, i.e. we add each parent to the ArrayList N-number of times where
	 * N is equal to its percentage score
	 * 
	 * @return rouletteList ArrayList that contains the Trader objects for
	 *         roulette selection
	 * @throws IOException
	 */
	private ArrayList<Trader> createRouletteList() throws IOException {

		rouletteList.clear();

		for (int i = 0; i < numPop; ++i) {

			int n = (int) (currentPopulationList.get(i).calcFitness() * 0.0001);
			// System.out.println(n);
			for (int j = 0; j < n; ++j) {
				rouletteList.add(currentPopulationList.get(i));
			}
		}

		// for (int i = 0; i < rouletteList.size(); ++i)
		// System.out.println(rouletteList.get(i));
		// System.out.println(rouletteList.size());

		return rouletteList;
	}

	/**
	 * Method that calculates the fitness values of all the individuals in the
	 * population.
	 * 
	 * @return fitList ArrayList containing the fitness values of all the
	 *         individuals in the population
	 * @throws IOException
	 */
	private ArrayList<Double> createCurrentFitnessList(
			ArrayList<Trader> listCurrent) throws IOException {

		currentListFitness.clear();

		for (int i = 0; i < numPop; ++i) {
			currentListFitness.add(listCurrent.get(i).calcFitness());
		}
		return currentListFitness;
	}

	private ArrayList<Double> createNextFitnessList(ArrayList<Trader> listNew)
			throws IOException {

		newListFitness.clear();

		for (int i = 0; i < numPop; ++i) {
			newListFitness.add(listNew.get(i).calcFitness());
		}
		return newListFitness;
	}

	/**
	 * Method to print out vital GA information at the start of runGA()
	 */
	public void printStartInfo() {

		System.out.println("GENETIC ALGORITHM\n");
		System.out.println("Data set: " + "\"" + filePath + "\"");
		System.out.println("Population size: " + numPop);
		System.out.println("Max number generations: " + maxIter);
		System.out.println("Strategy: Roulette");
		System.out.println("1-point cross-over applied: Yes");
		System.out.println("Cross-over rate: " + crossoverRate);
		System.out.println("Mutation rate: " + mutationRate);
		System.out.println("Generation gap: " + generationGap);
		if (generationGap > 0)
			System.out.println("Elitism applied: Yes\n");
		if (generationGap <= 0)
			System.out
					.println("No elitism applied because generation gap is 0\n");

		System.out.println("RESULTS\n");
	}

}
