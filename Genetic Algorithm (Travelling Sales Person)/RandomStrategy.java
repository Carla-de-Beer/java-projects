import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RandomStrategy {

	private static int NUM_CITIES = 20;
	private int numPop;
	private int maxIter;
	private double crossoverRate;
	private double mutationRate;
	private double percentageGap;
	private int generationGap;
	private Route optimalRoute;
	public double optimalValue;
	public double average = 0.0;
	private MyRandom myRandom = new MyRandom();

	private ArrayList<City> path = new ArrayList<>();

	private ArrayList<Route> newPopulationList = new ArrayList<>();
	private ArrayList<Route> currentPopulationList = new ArrayList<>();

	private ArrayList<Double> currentListFitness = new ArrayList<>();
	private ArrayList<Double> newListFitness = new ArrayList<>();

	public RandomStrategy(ArrayList<City> path, int numPop, int maxIter, double crossoverRate, double mutationRate,
			double percentageGap) {
		this.numPop = numPop;
		this.maxIter = maxIter;
		this.crossoverRate = crossoverRate;
		this.mutationRate = mutationRate;
		this.percentageGap = percentageGap;
		this.generationGap = (int) (this.numPop * this.percentageGap / 100.0);

		for (City c : path) {
			this.path.add(c);
		}

		this.optimalRoute = null;
		this.optimalValue = Double.POSITIVE_INFINITY;
	}

	public Route runGA() {
		// Start with a clean slate
		newPopulationList.clear();

		// Initialise ArrayList of routes
		for (int i = 0; i < numPop; ++i) {
			currentPopulationList.add(new Route(this.path));
			// Route route = currentPopulationList.get(i);
			// for (int j = 0; j < NUM_CITIES; ++j) {
			// System.out.print(route.getChromosome().get(j).getName());
			// }
			// System.out.println();
		}

		int counter = 0;
		double sum = 0.0;

		// Outer while loop that runs for the number of generations required
		while (counter < maxIter) {

			// Inner while loop that fills the newPopulationList ArrayList
			// before elitism is applied
			while (newPopulationList.size() < numPop) {

				ArrayList<City> parentA = new ArrayList<>();
				ArrayList<City> parentB = new ArrayList<>();
				ArrayList<City> childA = new ArrayList<>();
				ArrayList<City> childB = new ArrayList<>();

				// Randomly select two parents from the population of
				// Routes
				int rand1 = myRandom.randomInt(numPop);
				int rand2 = myRandom.randomInt(numPop);

				parentA = (currentPopulationList.get(rand1).getChromosome());
				parentB = (currentPopulationList.get(rand2).getChromosome());

				double crossRand = Math.random();
				double muteRand = Math.random();

				// System.out.println();

				// Crossover, if applicable
				if (crossoverRate > crossRand) {

					crossover(parentA, parentB, childA, childB);
					// System.out.println("Parent: ");
					// for (int i = 0; i < parentA.size(); ++i) {
					// System.out.print(parentA.get(i).getName());
					// }
					//
					// System.out.println();
					// for (int i = 0; i < parentA.size(); ++i) {
					// System.out.print(parentB.get(i).getName());
					// }
					// System.out.println();
					// System.out.println("Child: ");
					// for (int i = 0; i < childA.size(); ++i) {
					// System.out.print(childA.get(i).getName());
					// }
					//
					// System.out.println();
					// for (int i = 0; i < childB.size(); ++i) {
					// System.out.print(childB.get(i).getName());
					// }
					// System.out.println();
				} else {
					for (int i = 0; i < parentA.size(); ++i) {
						childA.add(parentA.get(i));
						childB.add(parentB.get(i));
					}
				}

				// Mutate, if applicable
				if (mutationRate > muteRand) {
					mutate(childA);
					mutate(childB);
				}

				// Populate the ArrayList newPopulation
				// with the offspring
				Route new1 = new Route(this.path);
				Route new2 = new Route(this.path);

				new1.setChromosome(childA);
				new2.setChromosome(childB);

				newPopulationList.add(new2);
				newPopulationList.add(new1);
			}

			counter++;

			getOptimal();
			StringBuilder sb = new StringBuilder(NUM_CITIES);
			for (int i = 0; i < NUM_CITIES; ++i) {
				sb.append(optimalRoute.getChromosome().get(i).getName());
				if (i < NUM_CITIES - 1) {
					sb.append("->");
				}
			}

			// System.out.println(getBestFitness() + ": " + sb.toString());
			System.out.println(getBestFitness());
			sum += getBestFitness();

			// Apply elitism, only if the generation gap > 0
			if (generationGap > 0) {

				createCurrentFitnessList(currentPopulationList);
				createNextFitnessList(newPopulationList);

				double[] listArray = new double[currentListFitness.size()];
				Route[] listRouterArray = new Route[currentPopulationList.size()];

				double[] newListArray = new double[newListFitness.size()];
				Route[] newlistRouteArray = new Route[newPopulationList.size()];

				for (int i = 0; i < numPop; ++i) {
					listArray[i] = currentListFitness.get(i);
					listRouterArray[i] = currentPopulationList.get(i);
					newListArray[i] = newListFitness.get(i);
					newlistRouteArray[i] = newPopulationList.get(i);
				}

				selectionSort(listArray, listRouterArray);
				selectionSort(newListArray, newlistRouteArray);

				currentPopulationList = new ArrayList<>(Arrays.asList(listRouterArray));
				newPopulationList = new ArrayList<>(Arrays.asList(newlistRouteArray));

				// Create a new ArrayList handOver that forms the hand over
				// from one generation to the next,
				// consisting of the best part of the current generation
				// replacing the worst part of the next generation
				ArrayList<Route> handOver = new ArrayList<>();

				for (int i = numPop - generationGap; i < currentPopulationList.size(); ++i)
					handOver.add(currentPopulationList.get(i));

				for (int i = generationGap; i < numPop; ++i)
					handOver.add(newPopulationList.get(i));

				currentPopulationList = new ArrayList<>(handOver);
			}
			// Else if the generation gap value is zero,
			// replace entire current generation with the new generation
			else
				currentPopulationList = new ArrayList<>(newPopulationList);

			newPopulationList.clear();

			// End elitism
		}

		// output average fitness value after each generation
		average = sum / (maxIter);
		System.out.println();
		printEndInfo();
		return optimalRoute;

	};

	// The crossover strategy makes use of Modified Order Crossover (MOX),
	// as described in:
	// http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.91.9167&rep=rep1&type=pdf
	private void crossover(ArrayList<City> parentA, ArrayList<City> parentB, ArrayList<City> childA,
			ArrayList<City> childB) {

		ArrayList<City> endA = new ArrayList<>();
		ArrayList<City> endB = new ArrayList<>();

		int rand = myRandom.randomInt(NUM_CITIES);

		// Copy over first part of the chromosome
		for (int i = 0; i < rand; ++i) {
			childA.add(parentA.get(i));
			childB.add(parentB.get(i));
		}

		// Copy over second part of the chromosome
		for (int i = rand; i < parentA.size(); ++i) {
			endA.add(parentA.get(i));
			endB.add(parentB.get(i));
		}

		int[] numsA = new int[endA.size()];
		int[] numsB = new int[endB.size()];

		// get index values
		for (int i = 0; i < endA.size(); ++i) {
			City x = endA.get(i);
			City y = endB.get(i);

			for (int j = 0; j < parentB.size(); ++j) {
				if (x == parentB.get(j)) {
					numsA[i] = j;
				}
				if (y == parentA.get(j)) {
					numsB[i] = j;
				}
			}
		}

		City[] strA = new City[endA.size()];
		endA.toArray(strA);
		City[] strB = new City[endB.size()];
		endB.toArray(strB);

		selectionSort(numsA, strA);
		selectionSort(numsB, strB);

		endA.clear();
		endB.clear();

		for (int i = 0; i < strA.length; ++i) {
			endA.add(strA[i]);
			endB.add(strB[i]);
		}

		// concatenate the two parts
		childA.addAll(endA);
		childB.addAll(endB);
	}

	private void mutate(ArrayList<City> path) {
		int rand1 = myRandom.randomInt(NUM_CITIES);
		int rand2 = myRandom.randomInt(NUM_CITIES);
		Collections.swap(path, rand1, rand2);
	}

	public void getOptimal() {
		double fitnessValue = 0.0;
		for (int i = 0; i < newPopulationList.size(); ++i) {
			fitnessValue = newPopulationList.get(i).calculateFitness();
			if (optimalValue > fitnessValue) {
				optimalRoute = newPopulationList.get(i);
				optimalValue = fitnessValue;
			}
		}
	}

	public Route getOptimalRoute() {
		return optimalRoute;
	}

	public final ArrayList<City> getBestSolution() {
		return optimalRoute.getChromosome();
	}

	public double getBestFitness() {
		return optimalValue;
	}

	public double getAverage() {
		return average;
	}

	private ArrayList<Double> createCurrentFitnessList(ArrayList<Route> listCurrent) {
		currentListFitness.clear();
		for (int i = 0; i < numPop; ++i) {
			currentListFitness.add(listCurrent.get(i).calculateFitness());
		}
		return currentListFitness;
	}

	private ArrayList<Double> createNextFitnessList(ArrayList<Route> listNew) {
		newListFitness.clear();
		for (int i = 0; i < numPop; ++i) {
			newListFitness.add(listNew.get(i).calculateFitness());
		}
		return newListFitness;
	}

	public void selectionSort(int[] arr1, City[] arr2) {
		int i, j, minIndex;
		int tmp1;
		City tmp2;
		int n = arr1.length;

		for (i = 0; i < n - 1; i++) {
			minIndex = i;
			for (j = i + 1; j < n; j++) {
				if (arr1[j] < arr1[minIndex]) {
					minIndex = j;
				}
			}

			if (minIndex != i) {
				tmp1 = arr1[i];
				tmp2 = arr2[i];

				arr1[i] = arr1[minIndex];
				arr2[i] = arr2[minIndex];

				arr1[minIndex] = tmp1;
				arr2[minIndex] = tmp2;
			}
		}
	}

	public void selectionSort(double[] arr1, Route[] arr2) {
		int i, j, minIndex;
		double tmp1;
		Route tmp2;
		int n = arr1.length;

		for (i = 0; i < n - 1; i++) {
			minIndex = i;
			for (j = i + 1; j < n; j++) {

				if (arr1[j] < arr1[minIndex]) {
					minIndex = j;
				}
			}

			if (minIndex != i) {
				tmp1 = arr1[i];
				tmp2 = arr2[i];

				arr1[i] = arr1[minIndex];
				arr2[i] = arr2[minIndex];

				arr1[minIndex] = tmp1;
				arr2[minIndex] = tmp2;
			}
		}
	}

	public void printStartInfo() {
		System.out.println("GENETIC ALGORITHM\n");
		System.out.println("Population size: " + numPop);
		System.out.println("Max number generations: " + maxIter);
		System.out.println("Strategy: Random");
		System.out.println("1-point cross-over applied: Yes");
		System.out.println("Cross-over rate: " + crossoverRate);
		System.out.println("Mutation rate: " + mutationRate);
		System.out.println("Generation gap: " + generationGap);
		if (generationGap > 0)
			System.out.println("Elitism applied: Yes\n");
		if (generationGap <= 0)
			System.out.println("No elitism applied because generation gap is 0\n");
		System.out.println("RESULTS\n");
	}

	public void printEndInfo() {

		System.out.println("\nProcessing complete.\n");

		System.out.println("\n****************************************************");
	}

}
