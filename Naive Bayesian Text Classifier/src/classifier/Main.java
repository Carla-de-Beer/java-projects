package classifier;

/**
 * Client class executing the Naive Bayesian Classifier.
 * The text files for the training an classification were taken from New York Times 
 * archive articles (November 2016), relating to one of two categories: BUSINESS or SPORT.
 * 
 * @author cadebe Created: November 2016
 *
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import enums.Path;
import enums.Text;

public class Main {

	private static String text = "";
	private static String[] tokens;
	private static char[] tokenChars;
	private static int incorrect = 0;

	private static String folderPathA = Path.FOLDERPATH_A.toString();
	private static String folderPathB = Path.FOLDERPATH_B.toString();
	private static String folderPathX = Path.FOLDERPATH_X.toString();
	private static String filePathX = Path.FILEPATH_X.toString();
	private static String resultFile = Path.RESULT_FILE.toString();

	private static String categoryA = Text.CAT_A.toString();
	private static String categoryB = Text.CAT_B.toString();

	private static char A = 'A';
	private static char B = 'B';

	private static final File folder = new File(folderPathX);

	public static void main(String[] args) {

		// Either run the classifier against all of the files inside the given
		// folder ...
		batchClassification();

		// ... or classify a single input file
		// runSingleBayes(folderPathA, folderPathB, filePathX, categoryA,
		// categoryB, A, B);

	}

	private static void batchClassification() {
		makeTokenChars(resultFile);
		listFilesForFolder(folder, folderPathA, folderPathB, folderPathX, categoryA, categoryB, A, B);
		printAverageResult();
	}

	public static void makeTokenChars(String resultFile) {
		readResultFiles();
		tokens = text.split("[\\W+\\d+]");
		tokenChars = new char[tokens.length];

		for (int i = 0; i < tokens.length; ++i) {
			tokenChars[i] = tokens[i].charAt(0);
		}
	}

	public static void readResultFiles() {
		BufferedReader reader = null;
		try {
			File file = new File(resultFile);
			reader = new BufferedReader(new FileReader(file));

			String line;
			while ((line = reader.readLine()) != null) {
				text += line;
				text += " ";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void listFilesForFolder(final File folder, String folderPathA, String folderPathB, String folderPathX,
			String categoryA, String categoryB, char A, char B) {
		int count = 0;
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry, folderPathA, folderPathB, folderPathX, categoryA, categoryB, A, B);
			} else {
				if (!fileEntry.getName().equals(Text.DS_STORE.toString())) {
					// Set the input values

					String filePathX = folderPathX + fileEntry.getName();
					System.out.println(Text.FILE_PATH + filePathX);

					FileHandlerInput input = new FileHandlerInput();
					input.setPathA(folderPathA);
					input.setPathB(folderPathB);
					input.setPathX(filePathX);
					input.setCategoryA(categoryA);
					input.setCategoryB(categoryB);
					input.setCharA(A);
					input.setCharB(B);

					// Create and execute the FileHandler
					FileHandler fileHandler = new FileHandler(input);

					// Generate the Output object
					FileHandlerOutput output = fileHandler.generateOutput();

					// Create the Bayes object with the Output object as input
					// parameter
					Bayes bayes = new Bayes(output);
					bayes.train();
					bayes.combineProbablities();
					System.out.println();
					bayes.printResultValues();
					bayes.displayClassificationResult();
					System.out.println();
					char result = bayes.getResultCategory();
					System.out.println(Text.CLASSIFICATION.toString() + result);
					if (result != tokenChars[count]) {
						System.out.println(Text.INCORRECT);
						incorrect++;
					} else {
						System.out.println(Text.CORRECT);
					}

					System.out.println();
					System.out.println(Text.LINES);
					System.out.println();
					count++;
				}
			}
		}
	}

	private static void printAverageResult() {
		double average = 100 - ((double) incorrect / tokenChars.length) * 100;
		String length = String.valueOf(tokenChars.length);
		System.out.println(Text.LINES);
		System.out.println();
		System.out.println(Text.ACCURACY);
		System.out.println(length + Text.RUNS);
		System.out.println(average + Text.PERCENTAGE.toString());
		System.out.println();
		System.out.println(Text.LINES);
		System.out.println(Text.LINES);
	}

	public static void runSingleBayes(String folderPathA, String folderPathB, String filePathX, String categoryA,
			String categoryB, char A, char B) {
		// Set the input values
		FileHandlerInput input = new FileHandlerInput();
		input.setPathA(folderPathA);
		input.setPathB(folderPathB);
		input.setPathX(filePathX);
		input.setCategoryA(categoryA);
		input.setCategoryB(categoryB);
		input.setCharA(A);
		input.setCharB(B);

		// Create and execute the FileHandler
		FileHandler fileHandler = new FileHandler(input);

		// Generate the Output object
		FileHandlerOutput output = fileHandler.generateOutput();

		// Create the Bayes object with the Output object as input parameter
		Bayes bayes = new Bayes(output);
		bayes.train();
		// bayes.printDictionaryHashMap();
		System.out.println(Text.DICTIONARY_SIZE.toString() + bayes.getDictionarySize());
		// bayes.printResultHashMap();
		// System.out.println(Text.RESULT_SIZE.toString()+
		// bayes.getResultSize());
		bayes.combineProbablities();
		System.out.println();
		bayes.printResultValues();
		bayes.displayClassificationResult();
		System.out.println();
		System.out.println(Text.LINES);
		System.out.println(Text.LINES);
	}

}
