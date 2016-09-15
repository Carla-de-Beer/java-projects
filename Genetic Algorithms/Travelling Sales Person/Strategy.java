import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public abstract class Strategy {

	private int numPop;
	private int maxIter;
	private double crossoverRate;
	private double mutationRate;
	private int numCities;
	private ArrayList<Route> populationList = new ArrayList<Route>();
	private ArrayList<City> overallBestRoute = new ArrayList<City>();
	private double overallBestFitness = Double.POSITIVE_INFINITY;
	private Route optimalRoute;
	private double optimalValue;
	private int numElite;
	private MyRandom myRandom = new MyRandom();

	public Strategy(ArrayList<Route> populationList, int numPop, int maxIter, double crossoverRate, double mutationRate,
			double generationGap, int numCities) {
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

	// ------------------------------------------
	// ABSTRACT METHODS
	// ------------------------------------------

	public void generatePopulation() {
	}

	public void runGA() {
	}

	public void printStartInfo() {
	}

	public void printResult() {
	}

	// ------------------------------------------
	// CONCRETE METHODS
	// ------------------------------------------

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

	public Route getOptimalRoute() {
		return optimalRoute;
	}

	public ArrayList<City> getBestSolution() {
		return optimalRoute.getChromosome();
	}

	public double getBestFitness() {
		return optimalValue;
	}

	public void calculateBestEver() {
		ArrayList<City> currentBestRoute = optimalRoute.getChromosome();
		double currentBestFitness = optimalValue;
		if (currentBestFitness < overallBestFitness) {
			overallBestRoute = new ArrayList<City>(currentBestRoute);
			overallBestFitness = currentBestFitness;
		}
	}

	public ArrayList<Route> createEliteList(ArrayList<Route> nextPopulationList) {

		HashMap<Route, Double> mapNext = new HashMap<Route, Double>();
		for (int i = 0; i < nextPopulationList.size(); ++i) {
			mapNext.put(nextPopulationList.get(i), nextPopulationList.get(i).calculateFitness());
		}

		HashMap<Route, Double> mapCurrent = new HashMap<Route, Double>();
		for (int i = 0; i < populationList.size(); ++i) {
			mapCurrent.put(populationList.get(i), populationList.get(i).calculateFitness());
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
		for (int i = 0; i < numElite; ++i) {
			descendingList.set(i, ascendingList.get(i));
		}

		for (Map.Entry<Route, Double> entry : descendingList) {
			eliteList.add(entry.getKey());
		}

		return eliteList;
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

	public int getNumPop() {
		return numPop;
	}

	public int getMaxIter() {
		return maxIter;
	}

	public int getNumCities() {
		return numCities;
	}

	public int getnumElite() {
		return numElite;
	}

	public double getOverallBestFitness() {
		return overallBestFitness;
	}

	public double getCrossOverRate() {
		return crossoverRate;
	}

	public double getMutationRate() {
		return mutationRate;
	}

	public ArrayList<City> getOverallBestRoute() {
		return overallBestRoute;
	}

	public ArrayList<Route> getPopulationList() {
		return populationList;
	}

	public void setPopulationList(ArrayList<Route> list) {
		populationList = new ArrayList<Route>(list);
	}

}
