import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) {

		String filePath = "./sourceFiles/CategoryX/";
		final File folder = new File(filePath);

		listFilesForFolder(folder, filePath);
	}

	public static void listFilesForFolder(final File folder, String filePath) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry, filePath);
			} else {
				if (!fileEntry.getName().equals(".DS_Store")) {
					String path = filePath + fileEntry.getName();
					System.out.println("File path:\n" + path);
					FileHandler fileHandler = new FileHandler(path);
					Bayes bayes = new Bayes(fileHandler, path);
					bayes.train();
					// bayes.printDictionaryHashMap();
					// System.out.println("----- dictionary HashMap size: " +
					// bayes.getDictionarySize());
					// bayes.printResultHashMap();
					// System.out.println("----- result HashMap size: " +
					// bayes.getResultSize());
					bayes.combineProbablities();
					System.out.println();
					bayes.printResultValues();
					bayes.displayResult();
					System.out.println();
				}
			}
		}
	}

}
