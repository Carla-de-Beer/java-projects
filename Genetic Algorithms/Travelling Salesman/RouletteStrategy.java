
/**
 * Class that runs a GA on the basis of a roulette selection strategy.
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

public class RouletteStrategy extends Strategy {

	ArrayList<Route> rouletteList = new ArrayList<Route>();
	private MyRandom myRandom = new MyRandom();

	public RouletteStrategy(ArrayList<Route> populationList, int numPop, int maxIter, double crossoverRate,
			double mutationRate, double generationGap, int numCities) {
		super(populationList, numPop, maxIter, crossoverRate, mutationRate, generationGap, numCities);
	}

	@Override
	public void runGA() {
		printStartInfo();
		int counter = 0;

		createRouletteList();
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

			if (rouletteList.size() > 0) {
				// Randomly select two parents from the rouletteList
				int randA = myRandom.randomInt(rouletteList.size());
				int randB = myRandom.randomInt(rouletteList.size());
				parentA = (rouletteList.get(randA).getChromosome());
				parentB = (rouletteList.get(randB).getChromosome());
			} else {
				// Randomly select two parents from the population
				int randA = myRandom.randomInt(getNumPop());
				int randB = myRandom.randomInt(getNumPop());
				parentA = (getPopulationList().get(randA).getChromosome());
				parentB = (getPopulationList().get(randB).getChromosome());
			}

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

		if (rouletteList.size() > 0) {
			for (int i = 0; i < rouletteList.size(); ++i) {
				mapCurrent.put(rouletteList.get(i), rouletteList.get(i).calculateFitness());
			}
		} else {
			for (int i = 0; i < getPopulationList().size(); ++i) {
				mapCurrent.put(getPopulationList().get(i), getPopulationList().get(i).calculateFitness());
			}
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

	private ArrayList<Route> createRouletteList() {
		rouletteList.clear();
		for (int i = 0; i < getNumPop(); ++i) {
			int n = (int) (getPopulationList().get(i).calculateFitness() * 0.001);
			for (int j = 0; j < n; ++j) {
				rouletteList.add(getPopulationList().get(i));
			}
		}
		return rouletteList;
	}

	public void printStartInfo() {
		System.out.println("GENETIC ALGORITHM\n");
		System.out.println("Selection strategy: Roulette Selection");
		printStartInfoStrategy();
	}

}
