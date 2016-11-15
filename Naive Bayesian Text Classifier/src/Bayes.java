import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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

	private DecimalFormat df = new DecimalFormat("0.###");
	FileHandler fileHandler;

	public Bayes(FileHandler fileHandler, String pathX) {

		fileHandler = new FileHandler(pathX);

		tokensCatA = new String[fileHandler.getTokensCatA().length];
		tokensCatB = new String[fileHandler.getTokensCatB().length];
		tokensCatX = new String[fileHandler.getTokensCatX().length];

		copyArray(tokensCatA, fileHandler.getTokensCatA());
		copyArray(tokensCatB, fileHandler.getTokensCatB());
		copyArray(tokensCatX, fileHandler.getTokensCatX());
	}

	private void copyArray(String[] self, String[] other) {
		for (int i = 0; i < other.length; ++i) {
			self[i] = other[i];
		}
	}

	public void train() {
		countWords();
		calculateProbabilities();
	}

	private void countWords() {

		trainingText.put(tokensCatA, 'A');
		trainingText.put(tokensCatB, 'B');

		Iterator trainingIter = trainingText.entrySet().iterator();

		while (trainingIter.hasNext()) {

			Map.Entry pair = (Map.Entry) trainingIter.next();
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
					int[] valueArray = (int[]) dictionary.get(tokens[i]);
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

	private void calculateProbabilities() {
		Iterator dictionaryIter = dictionary.entrySet().iterator();
		while (dictionaryIter.hasNext()) {
			Map.Entry pair = (Map.Entry) dictionaryIter.next();

			String key = (String) pair.getKey();
			int[] countArray = (int[]) pair.getValue();

			double freqA = ((double) countArray[0]) / docCountA; // countA
			double freqB = ((double) countArray[1]) / docCountB; // countB

			// Probability via Bayes rule
			if (freqA + freqB != 0) {
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

	public void combineProbablities() {
		// Combined probabilities
		// http://www.paulgraham.com/naivebayes.html
		double productA = 1.0;
		double productB = 1.0;

		// Multiply probabilities together
		if (tokensCatX.length == 1) {
			Iterator resultIter = result.entrySet().iterator();
			while (resultIter.hasNext()) {
				Map.Entry pair = (Map.Entry) resultIter.next();

				String resultKey = (String) pair.getKey();
				double[] resultValue = (double[]) pair.getValue();

				for (int i = 0; i < resultValue.length; ++i) {
					if (resultKey.equals(tokensCatX[0])) {
						if (resultValue[0] == 1 && resultValue[1] < 0.00000001) {
							productA = 1;
							productB = 0;
						} else if (resultValue[1] == 1 && resultValue[0] < 0.00000001) {
							productA = 0;
							productB = 1;
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
			}
		} else if (tokensCatX.length > 1) {
			for (int i = 0; i < tokensCatX.length; ++i) {
				String newWord = tokensCatX[i];

				Iterator resultIter = result.entrySet().iterator();
				while (resultIter.hasNext()) {
					Map.Entry pair = (Map.Entry) resultIter.next();

					String resultKey = (String) pair.getKey();
					double[] resultValue = (double[]) pair.getValue();

					if (resultKey.equals(newWord)) {
						if (resultValue[0] > 0.0) {
							productA *= resultValue[0];
						}
						if (resultValue[1] > 0.0) {
							productB *= resultValue[1];
						}
						// resultIter.remove();
					}
				}
			}
		}

		// Apply formula
		resA = (double) productA / (double) (productA + productB);
		resB = (double) productB / (double) (productB + productA);
	}

	public void displayResult() {
		System.out.println();
		System.out.println("--------------------------------");
		if (resA > resB) {
			System.out.println(
					"Classification result: BUSINESS \nCertainty probablility: " + df.format(resA * 100) + "%");
		} else if (resA < resB) {
			System.out.println("Classification result: SPORT \nCertainty probablility: " + df.format(resB * 100) + "%");
		} else if (resA == resB) {
			System.out.println("RESULT: There is an equal probability of the input text being of either category");
		}
		System.out.println("--------------------------------");
		System.out.println("--------------------------------");
	}

	/**
	 * Sorts the HashMap alphabetically, loops through the value array and
	 * outputs the key and array for each entry.
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
	 * Sorts the HashMap alphabetically, loops through the value array and
	 * outputs the key and array for each entry.
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

	public void printResultValues() {
		System.out.println("resA: " + resA);
		System.out.println("resB: " + resB);
	}
}
