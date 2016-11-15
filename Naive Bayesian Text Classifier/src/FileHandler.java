import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class FileHandler {

	String pathA = "./sourceFiles/Business";
	String pathB = "./sourceFiles/Sport";
	String pathX = "";

	private String[] tokensCatA;
	private String[] tokensCatB;
	private String[] tokensCatX;

	char A = 'A';
	char B = 'B';

	private String textA = "";
	private String textB = "";
	private String textX = "";

	String categoryA = "Business";
	String categoryB = "Sport";

	public FileHandler(String pathX) {

		final File folderA = new File(pathA);
		listFilesForFolder(folderA, tokensCatA, categoryA, A);

		final File folderB = new File(pathB);
		listFilesForFolder(folderB, tokensCatB, categoryB, B);

		readFilesX(pathX);
		tokeniseTextX(textX);

		// System.out.println(text);
		// System.out.println(tokensCatA[0]);
		// System.out.println(tokensCatA.length);
		// System.out.println(tokensCatB.length);
		// System.out.println(tokensCatX.length);
	}

	public void listFilesForFolder(final File folder, String[] tokensCat, String folderName, char z) {
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

	public void readFilesA(String filePath) {
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

	public void readFilesB(String filePath) {
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

	public void readFilesX(String filePath) {
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

	public void tokeniseTextA(String text) {
		tokensCatA = text.split("[\\W+\\d+]");
		tokensCatA = Arrays.stream(tokensCatA).filter(s -> (s != null && s.length() > 0)).toArray(String[]::new);

		for (int i = 0; i < tokensCatA.length; ++i) {
			tokensCatA[i] = tokensCatA[i].toLowerCase();
		}

		// for (int i = 0; i < tokensCatA.length; ++i) {
		// System.out.println(tokensCatA[i]);
		// }
	}

	public void tokeniseTextB(String text) {
		tokensCatB = text.split("[\\W+\\d+]");
		tokensCatB = Arrays.stream(tokensCatB).filter(s -> (s != null && s.length() > 0)).toArray(String[]::new);

		for (int i = 0; i < tokensCatB.length; ++i) {
			tokensCatB[i] = tokensCatB[i].toLowerCase();
		}

		// for (int i = 0; i < tokensCatB.length; ++i) {
		// System.out.println(tokensCatB[i]);
		// }
	}

	public void tokeniseTextX(String text) {
		tokensCatX = text.split("[\\W+\\d+]");
		tokensCatX = Arrays.stream(tokensCatX).filter(s -> (s != null && s.length() > 0)).toArray(String[]::new);

		for (int i = 0; i < tokensCatX.length; ++i) {
			tokensCatX[i] = tokensCatX[i].toLowerCase();
		}

		// for (int i = 0; i < tokensCatX.length; ++i) {
		// System.out.println(tokensCatX[i]);
		// }
	}

	public String[] getTokensCatA() {
		return tokensCatA;
	}

	public String[] getTokensCatB() {
		return tokensCatB;
	}

	public String[] getTokensCatX() {
		return tokensCatX;
	}
}