
/**
 * Client class executing the Naive Bayesian Classifier.
 * 
 * @author cadebe Created: November 2016
 *
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	private static String text = "";
	private static String[] tokens;
	private static char[] tokenChars;
	private static int incorrect = 0;

	private static String folderPathA = "./sourceFiles/Business";
	private static String folderPathB = "./sourceFiles/Sport";
	private static String filePathX = "./sourceFiles/CategoryX/X-01.txt";
	private static String resultFile = "./sourceFiles/Results/results.txt";

	private static String categoryA = "Business";
	private static String categoryB = "Sport";

	private static char A = 'A';
	private static char B = 'B';

	private static String folderPathX = "./sourceFiles/CategoryX/";
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
				if (!fileEntry.getName().equals(".DS_Store")) {
					// Set the input values

					String filePathX = folderPathX + fileEntry.getName();
					System.out.println("File path:\n" + filePathX);

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
					System.out.println("Classification: Category " + result);
					if (result != tokenChars[count]) {
						System.out.println("INCORRECT RESULT");
						incorrect++;
					} else {
						System.out.println("CORRECT RESULT");
					}

					System.out.println();
					System.out.println("-------------------------------------");
					System.out.println();
					count++;
				}
			}
		}
	}

	private static void printAverageResult() {
		double average = 100 - ((double) incorrect / tokenChars.length) * 100;
		String length = String.valueOf(tokenChars.length);
		System.out.println("-------------------------------------");
		System.out.println();
		System.out.println("CLASSIFICATION ACCURACY:");
		System.out.println(length + " runs");
		System.out.println(average + "% correct");
		System.out.println();
		System.out.println("-------------------------------------");
		System.out.println("-------------------------------------");
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
		System.out.println("Dictionary HashMap size: " + bayes.getDictionarySize());
		// bayes.printResultHashMap();
		// System.out.println("----- result HashMap size: " +
		// bayes.getResultSize());
		bayes.combineProbablities();
		System.out.println();
		bayes.printResultValues();
		bayes.displayClassificationResult();
		System.out.println();
		System.out.println("-------------------------------------");
		System.out.println("-------------------------------------");
	}

}
