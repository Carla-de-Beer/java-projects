
/**
 * Client class executing the Naive Bayesian Classifier.
 * 
 * @author cadebe Created: November 2016
 *
 */

import java.io.File;

public class Main {

	public static void main(String[] args) {

		String folderPathA = "./sourceFiles/Business";
		String folderPathB = "./sourceFiles/Sport";
		String filePathX = "./sourceFiles/CategoryX/X-01.txt";

		String categoryA = "Business";
		String categoryB = "Sport";

		char A = 'A';
		char B = 'B';

		String folderPathX = "./sourceFiles/CategoryX/";
		final File folder = new File(folderPathX);

		// Either run the classifier against all of the files inside the given
		// folder ...

		listFilesForFolder(folder, folderPathA, folderPathB, folderPathX, categoryA, categoryB, A, B);

		// ... or classify a single input file
		// runSingleBayes(folderPathA, folderPathB, filePathX, categoryA,
		// categoryB, A, B);

	}

	public static void listFilesForFolder(final File folder, String folderPathA, String folderPathB, String folderPathX,
			String categoryA, String categoryB, char A, char B) {
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
				}
			}
		}
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
		System.out.println("----- dictionary HashMap size: " + bayes.getDictionarySize());
		// bayes.printResultHashMap();
		// System.out.println("----- result HashMap size: " +
		// bayes.getResultSize());
		bayes.combineProbablities();
		System.out.println();
		bayes.printResultValues();
		bayes.displayClassificationResult();
		System.out.println();
	}

}
