
/**
 * Class that runs a GA on the basis of a rank selection strategy.
 * The crossover strategy makes use of Modified Order Crossover (MOX), as described in:
 * http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.91.9167&rep=rep1&type=pdf
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class RankStrategy extends Strategy {

	private ArrayList<Route> rankList = new ArrayList<Route>();
	private MyRandom myRandom = new MyRandom();

	public RankStrategy(ArrayList<Route> populationList, int numPop, int maxIter, double crossoverRate,
			double mutationRate, double generationGap, int numCities) {
		super(populationList, numPop, maxIter, crossoverRate, mutationRate, generationGap, numCities);
	}

	@Override
	public void runGA() {
		printStartInfo();
		int counter = 0;

		createRankList();
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

			// Randomly select two parents from the population
			int randA = myRandom.randomInt(rankList.size());
			int randB = myRandom.randomInt(rankList.size());
			parentA = (rankList.get(randA).getChromosome());
			parentB = (rankList.get(randB).getChromosome());

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

	@Override
	public ArrayList<Route> createEliteList(ArrayList<Route> nextPopulationList) {

		HashMap<Route, Double> mapNext = new HashMap<Route, Double>();
		for (int i = 0; i < nextPopulationList.size(); ++i) {
			mapNext.put(nextPopulationList.get(i), nextPopulationList.get(i).calculateFitness());
		}

		HashMap<Route, Double> mapCurrent = new HashMap<Route, Double>();
		for (int i = 0; i < rankList.size(); ++i) {
			mapCurrent.put(rankList.get(i), rankList.get(i).calculateFitness());
		}

		Set<Entry<Route, Double>> setCurrent = mapCurrent.entrySet();
		List<Entry<Route, Double>> ascendingList = new ArrayList<Entry<Route, Double>>(setCurrent);

		Set<Entry<Route, Double>> setNext = mapNext.entrySet();
		List<Entry<Route, Double>> descendingList = new ArrayList<Entry<Route, Double>>(setNext);

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

		ArrayList<Route> eliteList = new ArrayList<Route>();
		for (int i = 0; i < getNumElite(); ++i) {
			descendingList.set(i, ascendingList.get(i));
		}

		for (Map.Entry<Route, Double> entry : descendingList) {
			eliteList.add(entry.getKey());
		}

		return eliteList;
	}

	private ArrayList<Route> createRankList() {
		double totalFitness = 0.0;
		double average = 0.0;
		ArrayList<Double> fitList = new ArrayList<Double>();

		for (int i = 0; i < getNumPop(); ++i) {
			fitList.add(getPopulationList().get(i).calculateFitness());
		}

		for (int i = 0; i < getNumPop(); ++i) {
			totalFitness += fitList.get(i);
		}

		average = totalFitness / getNumPop();
		for (int i = 0; i < getNumPop(); ++i) {
			int n = (int) (fitList.get(i) / average);
			for (int j = 0; j < n; ++j) {
				rankList.add(getPopulationList().get(i));
			}
		}
		return rankList;
	}

	public void printStartInfo() {
		System.out.println("GENETIC ALGORITHM\n");
		System.out.println("Selection strategy: Rank Selection");
		printStartInfoStrategy();
	}
}
