package classifier;

/**
 * Class that executes the the Bayesian classifier's algorithm, including training and result generation.
 * 
 * @author cadebe Created: November 2016
 *
 */

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import enums.Text;

import java.util.TreeMap;

public class Bayes {

	private HashMap<String[], Character> trainingText = new HashMap<String[], Character>();
	private HashMap<String, int[]> dictionary = new HashMap<String, int[]>();
	private HashMap<String, double[]> result = new HashMap<String, double[]>();

	private int docCountA = 0;
	private int docCountB = 0;
	private double resA = 0.0;
	private double resB = 0.0;

	private String[] tokensCatA;
	private String[] tokensCatB;
	private String[] tokensCatX;

	private char resultCategory = ' ';
	private DecimalFormat df = new DecimalFormat("0.###");

	public Bayes(FileHandlerOutput output) {

		tokensCatA = new String[output.getTokensCatA().length];
		tokensCatB = new String[output.getTokensCatB().length];
		tokensCatX = new String[output.getTokensCatX().length];

		copyArray(tokensCatA, output.getTokensCatA());
		copyArray(tokensCatB, output.getTokensCatB());
		copyArray(tokensCatX, output.getTokensCatX());
	}

	private void copyArray(String[] self, String[] other) {
		for (int i = 0; i < other.length; ++i) {
			self[i] = other[i];
		}
	}

	/**
	 * Method that trains the classifier.
	 */
	public void train() {
		countWords();
		calculateProbabilities();
	}

	/**
	 * Method that lists the words used in both of the training sets and their
	 * respective occurrences within the dictionary HashMap.
	 */
	private void countWords() {

		trainingText.put(tokensCatA, 'A');
		trainingText.put(tokensCatB, 'B');

		Iterator<Entry<String[], Character>> trainingIter = trainingText.entrySet().iterator();

		while (trainingIter.hasNext()) {

			Entry<String[], Character> pair = trainingIter.next();
			// System.out.println(pair.getKey() + " = " + pair.getValue());

			String[] tokens = (String[]) pair.getKey();
			char category = (char) pair.getValue();

			if (category == 'A') {
				docCountA += tokens.length;
			} else if (category == 'B') {
				docCountB += tokens.length;
			}

			for (int i = 0; i < tokens.length; ++i) {
				int[] dictionaryKey = dictionary.get(tokens[i]);
				if (dictionaryKey == null) {
					if (category == 'A') {
						int[] arrayA = { 1, 0 };
						dictionary.put(tokens[i], arrayA);
					} else if (category == 'B') {
						int[] arrayB = { 0, 1 };
						dictionary.put(tokens[i], arrayB);
					}
				} else {
					int[] valueArray = dictionary.get(tokens[i]);
					if (category == 'A') {
						int valA = ++valueArray[0];
						int valB = valueArray[1];
						int[] arrayA = { valA, valB };
						dictionary.put(tokens[i], arrayA);
					} else if (category == 'B') {
						int valA = valueArray[0];
						int valB = ++valueArray[1];
						int[] arrayB = { valA, valB };
						dictionary.put(tokens[i], arrayB);
					}
				}
			}

			// trainingIter.remove();
		}

		// System.out.println("docCountA = " + docCountA);
		// System.out.println("docCountB = " + docCountB);
	}

	/**
	 * Method that calculates the frequency values for each of the words listed
	 * in the dictionary by applying Bayes' Theorem.
	 */
	private void calculateProbabilities() {
		Iterator<Entry<String, int[]>> dictionaryIter = dictionary.entrySet().iterator();
		while (dictionaryIter.hasNext()) {
			Entry<String, int[]> pair = dictionaryIter.next();

			String key = (String) pair.getKey();
			int[] countArray = (int[]) pair.getValue();

			double freqA = ((double) countArray[0]) / docCountA;
			double freqB = ((double) countArray[1]) / docCountB;

			// Probability via Bayes rule
			if (freqA + freqB > 0.0) {
				double probA = freqA / (freqA + freqB);
				double probB = 1 - probA;
				double[] resultArray = { probA, probB };
				result.put(key, resultArray);
			}

			// dictionaryIter.remove(); // avoids a
			// ConcurrentModificationException
		}
	}

	public int getDictionarySize() {
		return dictionary.size();
	}

	public int getResultSize() {
		return result.size();
	}

	/**
	 * Method that cumulatively computes the probability values of the input
	 * text being of either category A or category B.
	 */
	public void combineProbablities() {
		// Combined probabilities
		// http://www.paulgraham.com/naivebayes.html
		double productA = 1.0;
		double productB = 1.0;

		// Multiply probabilities together
		if (tokensCatX.length == 1) {
			Iterator<Entry<String, double[]>> resultIter = result.entrySet().iterator();
			while (resultIter.hasNext()) {
				Entry<String, double[]> pair = resultIter.next();
				String resultKey = (String) pair.getKey();
				double[] resultValue = (double[]) pair.getValue();

				if (resultKey.equals(tokensCatX[0])) {
					if (resultValue[0] == 1.0 && resultValue[1] == 0.0) {
						productA = 1.0;
						productB = 0.0;
					} else if (resultValue[0] == 0.0 && resultValue[1] == 1) {
						productA = 0.0;
						productB = 1.0;
					} else {
						if (resultValue[0] > 0.0) {
							productA *= resultValue[0];
						}
						if (resultValue[1] > 0.0) {
							productB *= resultValue[1];
						}
					}
				}
			}
		} else if (tokensCatX.length > 1) {

			boolean A = false;
			boolean B = false;

			double totalA = 0.0;
			double totalB = 0.0;
			for (int i = 0, l = tokensCatX.length; i < l; ++i) {
				String newWord = tokensCatX[i];

				Iterator<Entry<String, double[]>> resultIter = result.entrySet().iterator();
				while (resultIter.hasNext()) {
					Entry<String, double[]> pair = resultIter.next();
					String resultKey = (String) pair.getKey();
					double[] resultValue = (double[]) pair.getValue();

					if (resultKey.equals(newWord)) {
						totalA += resultValue[0];
						totalB += resultValue[1];
					}
				}
			}

			// Make provision for all words being of the same category,
			// or most of the words being of one, and fewer of the other,
			// otherwise we are multiplying both sides by zero.
			if (totalA > 0 && totalB == 0.0) {
				A = true;
			} else if (totalB > 0 && totalA == 0.0) {
				B = true;
			} else if (totalA > 0 && totalB > 0 && totalA > totalB) {
				A = true;
			} else if (totalA > 0 && totalB > 0 && totalB > totalA) {
				B = true;
			}

			for (int i = 0; i < tokensCatX.length; ++i) {
				String newWord = tokensCatX[i];

				Iterator<Entry<String, double[]>> resultIter = result.entrySet().iterator();
				while (resultIter.hasNext()) {
					Entry<String, double[]> pair = resultIter.next();
					String resultKey = (String) pair.getKey();
					double[] resultValue = (double[]) pair.getValue();

					if (resultKey.equals(newWord)) {

						if (A && !B) {
							if (resultValue[0] > 0) {
								productA *= resultValue[0];
							}
							productB = 0;
						} else if (!A && B) {
							if (resultValue[1] > 0) {
								productB *= resultValue[1];
							}
							productA = 0;
						} else if ((!A && !B) || (A && B)) {
							if (resultValue[0] > 0) {
								productA *= resultValue[0];
							}
							if (resultValue[1] > 0) {
								productB *= resultValue[1];
							}
						}
					}
				}
			}
		}

		// System.out.println("tokensCatX.length = " + tokensCatX.length);

		// Apply formula
		resA = (double) productA / (double) (productA + productB);
		resB = (double) productB / (double) (productB + productA);

	}

	/**
	 * Method that displays the classification result.
	 */
	public void displayClassificationResult() {
		System.out.println();
		if (resA > resB) {
			resultCategory = 'A';
			System.out.println(Text.RESULT_CAT_A + df.format(resA * 100) + Text.PERCENT);
		} else if (resA < resB) {
			resultCategory = 'B';
			System.out.println(Text.RESULT_CAT_B + df.format(resB * 100) + Text.PERCENT);
		} else if (resA == resB) {
			System.out.println(Text.RESULT_EITHER);
		}
	}

	/**
	 * Method that sorts the HashMap alphabetically, loops through the value
	 * array and outputs the key and array for each entry.
	 */
	public void printDictionaryHashMap() {
		Map<String, int[]> map = new TreeMap<String, int[]>(dictionary);
		for (Map.Entry<String, int[]> pair : map.entrySet()) {

			String key = (String) pair.getKey();
			int[] valueArray = (int[]) pair.getValue();
			System.out.print(key + ": ");
			for (int i = 0; i < valueArray.length; ++i) {
				System.out.print(valueArray[i] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Method that sorts the HashMap alphabetically, loops through the value
	 * array and outputs the key and array for each entry.
	 */
	public void printResultHashMap() {
		Map<String, double[]> map = new TreeMap<String, double[]>(result);
		for (Map.Entry<String, double[]> pair : map.entrySet()) {

			String key = (String) pair.getKey();
			double[] valueArray = (double[]) pair.getValue();
			System.out.print(key + ": ");
			for (int i = 0; i < valueArray.length; ++i) {
				System.out.print(valueArray[i] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Method that prints probability results of the input text being of either
	 * category A or category B.
	 */
	public void printResultValues() {
		System.out.println(Text.PROB_A.toString() + resA);
		System.out.println(Text.PROB_B.toString() + resB);
	}

	public char getResultCategory() {
		return resultCategory;
	}
}
