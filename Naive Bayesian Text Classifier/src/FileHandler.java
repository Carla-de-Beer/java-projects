
/**
 * Class that reads the folders specified via a FileHandlerInput object and provides relevant 
 * array information to the Bayes class needed for classification.
 * 
 * @author cadebe Created: November 2016
 *
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class FileHandler {

	private String[] tokensCatA;
	private String[] tokensCatB;
	private String[] tokensCatX;

	private String textA;
	private String textB;
	private String textX;

	FileHandlerOutput output;

	public FileHandler(FileHandlerInput input) {

		final File folderA = new File(input.getPathA());
		listFilesForFolder(folderA, tokensCatA, input.getCategoryA(), input.getCharA());

		final File folderB = new File(input.getPathB());
		listFilesForFolder(folderB, tokensCatB, input.getCategoryB(), input.getCharB());

		readFilesX(input.getPathX());
		tokeniseTextX(textX);

		// System.out.println(text);
		// System.out.println(tokensCatA[0]);
		// System.out.println(tokensCatA.length);
		// System.out.println(tokensCatB.length);
		// System.out.println(tokensCatX.length);
	}

	/**
	 * Method that creates the FileHandlerOutput object needed for use by the
	 * Bayes class.
	 * 
	 * @return
	 */
	public FileHandlerOutput generateOutput() {
		return new FileHandlerOutput(tokensCatA, tokensCatB, tokensCatX);
	}

	/**
	 * Method that recursively iterates through the folder structure to read
	 * each of the files provided within the folder of text to be classified.
	 * 
	 * @param folder
	 * @param tokensCat
	 * @param folderName
	 * @param z
	 */
	private void listFilesForFolder(final File folder, String[] tokensCat, String folderName, char z) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry, tokensCat, folderName, z);
			} else {
				if (!fileEntry.getName().equals(".DS_Store")) {
					if (z == 'A') {
						readFilesA("./sourceFiles/" + folderName + "/" + fileEntry.getName());
						tokeniseTextA(textA);
					} else if (z == 'B') {
						readFilesB("./sourceFiles/" + folderName + "/" + fileEntry.getName());
						tokeniseTextB(textB);
					}
				}
			}
		}
	}

	private void readFilesA(String filePath) {
		BufferedReader reader = null;
		try {
			File file = new File(filePath);
			reader = new BufferedReader(new FileReader(file));

			String line;
			while ((line = reader.readLine()) != null) {
				textA += line;
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

	private void readFilesB(String filePath) {
		BufferedReader reader = null;
		try {
			File file = new File(filePath);
			reader = new BufferedReader(new FileReader(file));

			String line;
			while ((line = reader.readLine()) != null) {
				textB += line;
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

	private void readFilesX(String filePath) {
		BufferedReader reader = null;
		try {
			File file = new File(filePath);
			reader = new BufferedReader(new FileReader(file));

			String line;
			while ((line = reader.readLine()) != null) {
				textX += line;
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

	private void tokeniseTextA(String text) {
		tokensCatA = text.split("[\\W+\\d+]");
		tokensCatA = Arrays.stream(tokensCatA).filter(s -> (s != null && s.length() > 0)).toArray(String[]::new);
		convertToLowerCase(tokensCatA);
	}

	private void tokeniseTextB(String text) {
		tokensCatB = text.split("[\\W+\\d+]");
		tokensCatB = Arrays.stream(tokensCatB).filter(s -> (s != null && s.length() > 0)).toArray(String[]::new);
		convertToLowerCase(tokensCatB);
	}

	private void tokeniseTextX(String text) {
		tokensCatX = text.split("[\\W+\\d+]");
		tokensCatX = Arrays.stream(tokensCatX).filter(s -> (s != null && s.length() > 0)).toArray(String[]::new);
		convertToLowerCase(tokensCatX);
	}

	private void convertToLowerCase(String[] array) {
		for (int i = 0; i < array.length; ++i) {
			array[i] = array[i].toLowerCase();
		}

		// for (int i = 0; i < array.length; ++i) {
		// System.out.println(array[i]);
		// }
	}

}