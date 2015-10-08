import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Main executing class.
 * 
 * @author cadebe 02.10.2015
 * 
 */
public class Main {

	private static NeuralNetwork neuralNetwork;
	// number of input nodes
	private static final int NUM_NODES = 28;
	// total number of training data

	// ArrayList with AT values: AT = training accuracy
	private static ArrayList<Double> list_AT = new ArrayList<Double>();
	// ArrayList with DG values: DG = data generalisation accuracy
	private static ArrayList<Double> list_DG = new ArrayList<Double>();
	// ArrayList with training set root mean square values
	private static ArrayList<Double> list_RMSE_AT = new ArrayList<Double>();
	// ArrayList with validation set root mean square values
	private static ArrayList<Double> list_RMSE_DG = new ArrayList<Double>();

	// ArrayLists for storing average values for AT, DG, RMSE_AT, RMSE_DG
	private static ArrayList<InputLayer> inputPatterns = new ArrayList<InputLayer>();
	private static ArrayList<Double> average_AT = new ArrayList<Double>();
	private static ArrayList<Double> average_DG = new ArrayList<Double>();
	private static ArrayList<Double> average_RMSE_AT = new ArrayList<Double>();
	private static ArrayList<Double> average_RMSE_DG = new ArrayList<Double>();

	// Input variables
	private static int hiddenLayerSize = 55;
	private static double learningRate = 0.5;
	private static double momentum = 0.5;
	private static int maxEpoch = 50;
	private static int numRuns = 50;
	private static double trainingValuationRatio = 0.8;

	private static JFrame frame;

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) throws Exception {
		try {
			// Set UI look and feel
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			System.out.println(e);
		}

		ArrayList<InputLayer> photoList = getInputPatterns("./Photos/", true,
				"Photos");
		ArrayList<InputLayer> cartoonList = getInputPatterns("./Cartoons/",
				false, "Cartoons");
		inputPatterns = createList(photoList, cartoonList);

		// hiddenLayerSize = getUserIntInput("Number of Hidden Nodes?", 2, 55);
		// learningRate = getUserDoubleInput("Learning Rate(%) ?", 0, 100) /
		// 100;
		// momentum = getUserDoubleInput("Momentum Rate(%) ?", 0, 100) / 100;
		// maxEpoch = getUserIntInput("Maximum number of epochs?", 1, 100);
		// numRuns = getUserIntInput("Number of trials?", 5, 100);
		// trainingValuationRatio = getUserDoubleInput(
		// "Percentage of data to use for data separation(%) ?", 0, 100) / 100;
		printHeader();

		for (int i = 1; i <= numRuns; i++) {
			neuralNetwork = new NeuralNetwork(hiddenLayerSize, learningRate,
					momentum, NUM_NODES, NUM_NODES);

			// Best to randomise input for learning so that the network
			// does not learn the data in the same order each time
			// so ... shuffle first
			Collections.shuffle(inputPatterns);

			// System.out.println(inputPatterns.size());

			list_AT.clear();
			list_DG.clear();

			// For each of the number of runs ...
			for (int epoch = 0; epoch < maxEpoch; ++epoch) {
				// Set up the neural network
				double accuracyAT = train(trainingValuationRatio);
				double accuracyDG = validate(trainingValuationRatio);
				double rmse_train = train_RMSE(trainingValuationRatio);
				double rmse_validate = validate_RMSE(trainingValuationRatio);

				// System.out.println(rmse_train);

				list_AT.add(accuracyAT);
				list_DG.add(accuracyDG);
				list_RMSE_AT.add(rmse_train);
				list_RMSE_DG.add(rmse_validate);
			}

			if (i % 5 == 0)
				System.out.print("* ");
			writeToFile("Epoch Results\\Run " + (i) + " - epoch results",
					list_AT, list_DG, list_RMSE_AT, list_RMSE_DG, "Epoch", true);
		}
		System.out.println();
		System.out.println();

		double totalAverage_AT = 0.0;
		double totalAverage_DG = 0.0;
		DecimalFormat df1 = new DecimalFormat("##.##########");

		System.out
				.println("Processing complete.\n\n\n******************************************\n");
		System.out.println("RESULTS\n");

		System.out
				.println("Training set RMSE values (" + numRuns + " runs):\n");

		for (int j = 0; j < average_RMSE_AT.size(); ++j) {
			if (j < 10)
				System.out.println("RMSE_AT (average) run  " + j + ": "
						+ df1.format(average_RMSE_AT.get(j)));
			else
				System.out.println("RMSE_AT (average) run " + j + ": "
						+ df1.format(average_RMSE_AT.get(j)));
			// System.out.println(average_DG.get(j));
			totalAverage_AT += average_AT.get(j);
		}

		// System.out.println();
		// System.out.println(totalAverage_AT);

		System.out.println();
		System.out
				.println("Validation set RMSE values (" + numRuns + " runs):");
		System.out.println();

		for (int j = 0; j < average_RMSE_DG.size(); ++j) {
			if (j < 10)
				System.out.println("RMSE_DG (average) run  " + j + ": "
						+ df1.format(average_RMSE_DG.get(j)));
			else
				System.out.println("RMSE_DG (average) run " + j + ": "
						+ df1.format(average_RMSE_DG.get(j)));
			totalAverage_DG += average_DG.get(j);
		}

		double averageOfAverage_AT = totalAverage_AT / numRuns;
		double averageOfAverage_DG = totalAverage_DG / numRuns;

		printFooter(averageOfAverage_AT, averageOfAverage_DG);

		writeToFile("Results - averages", average_AT, average_DG,
				average_RMSE_AT, average_RMSE_DG, "Run", false);

	}

	/**
	 * Method that implements the training functionality of the neural network.
	 * 
	 * @param dataDivision
	 *            double value that specifies ration between training and
	 *            validation data
	 * @return double value that expresses the accuracy
	 */
	private static double train(double dataDivision) {
		neuralNetwork.resetAccuracy();

		int index = (int) (inputPatterns.size() * dataDivision);
		for (int i = 0; i < index; i++) {
			neuralNetwork.train(inputPatterns.get(i));
		}
		double accuracy = neuralNetwork.getAccuracy(index);
		return accuracy;
	}

	/**
	 * Method that changes the ratio between training and validation sets.
	 * 
	 * @param dataDivide
	 *            double value that defines the percentage of training data.
	 * @return double value that defines the accuracy
	 */
	private static double validate(double dataDivision) {
		double accuracy = 0;
		int index = (int) (inputPatterns.size() * dataDivision);
		for (int i = index; i < inputPatterns.size(); i++) {
			int outcome = neuralNetwork.feedForward(inputPatterns.get(i));
			if (outcome == inputPatterns.get(i).getType()) {
				accuracy += 1;
			}
		}
		accuracy = (accuracy / (inputPatterns.size() - index)) * 100;
		return accuracy;
	}

	/**
	 * Method that calculates the root mean square error (RMSE) for the training
	 * set.
	 * 
	 * @param dataDivision
	 *            double value that indicates the ratio of the validation data
	 *            set
	 * @return double value that expresses the training RMSE
	 */
	private static double train_RMSE(double dataDivision) {
		double mse = 0.0;
		double output = 0.0;
		int target = 0;
		neuralNetwork.resetAccuracy();
		int index = (int) (inputPatterns.size() * dataDivision);

		for (int i = 0; i < index; ++i) {
			target = inputPatterns.get(i).getType();
			output = neuralNetwork.feedForward(inputPatterns.get(i));
			mse += (target - output) * (target - output);
		}
		return Math.sqrt(mse / index);
	}

	/**
	 * Method that calculates the root mean square error (RMSE) for the
	 * validation set.
	 * 
	 * @param dataDivision
	 *            double value that indicates the ratio of the validation data
	 *            set
	 * @return double value that expresses the validation RMSE
	 */
	private static double validate_RMSE(double dataDivision) {
		double mse = 0.0;
		double output = 0.0;
		int target = 0;
		neuralNetwork.resetAccuracy();
		int index = (int) (inputPatterns.size() * dataDivision);

		for (int i = index; i < inputPatterns.size(); ++i) {
			target = inputPatterns.get(i).getType();
			output = neuralNetwork.feedForward(inputPatterns.get(i));
			mse += (target - output) * (target - output);
		}
		return Math.sqrt(mse / (inputPatterns.size() - index));
	}

	/**
	 * Method that creates the list of input patterns.
	 * 
	 * @param photoList_
	 *            ArrayList of InputLayer objects
	 * @param cartoonList_
	 *            ArrayList of InputLayer objects
	 * @return ArrayList of InputLayer objects
	 */
	private static ArrayList<InputLayer> createList(
			ArrayList<InputLayer> photoList_, ArrayList<InputLayer> cartoonList_) {
		ArrayList<InputLayer> patterns = new ArrayList<InputLayer>();
		Random rand = new Random();
		int i = 0;
		int size = cartoonList_.size() + photoList_.size();
		while (i < size) {
			int next = rand.nextInt(2);
			if (cartoonList_.size() <= 0) {
				next = 1;
			}
			if (photoList_.size() <= 0) {
				next = 0;
			}
			if (next == 0) {
				patterns.add(cartoonList_.remove(0));
			} else {
				patterns.add(photoList_.remove(0));
			}
			++i;
		}
		return patterns;
	}

	/**
	 * Method that converts the data in the chosen files into an ArrayList of
	 * frequency values that can be used as InputLayer objects to the neural
	 * net's input nodes.
	 * 
	 * @param filePath
	 *            String that indicates the filepath to the folder selected
	 * @param isClass0
	 *            Boolean expresses whether an item is part of class0 or class1
	 * @param name
	 *            String value that describes the name of the data set
	 * @return ArrayList containing InputLayer objects
	 * @throws IOException
	 */
	private static ArrayList<InputLayer> getInputPatterns(String filePath,
			boolean isClass0, String name) throws IOException {
		ArrayList<InputLayer> list = new ArrayList<InputLayer>();
		try {
			JFileChooser chooser = new JFileChooser(filePath);
			chooser.setDialogTitle("Choose any  number of " + name
					+ " as input files");
			chooser.setMultiSelectionEnabled(true);
			chooser.showOpenDialog(frame);
			chooser.setMultiSelectionEnabled(true);
			File[] files = chooser.getSelectedFiles();
			for (int i = 0; i < files.length; i++) {
				list.add(new InputLayer(files[i], isClass0, NUM_NODES));
			}
		} catch (Exception e) {
			System.out.println("There is an error reading the file: " + e);
		}
		return list;
	}

	/**
	 * Method that writes the AT and DG values to a file.
	 * 
	 * @param filename
	 *            String value to specify file name
	 * @param AT_values
	 *            Double value to specify AT
	 * @param DG_values
	 *            Double value to specify DG
	 * @param id
	 *            String to indicate whether epoch or file average data should
	 *            be written
	 * @param getAVG
	 *            Boolean value to determine whether epoch or file average data
	 *            should be written
	 */
	private static void writeToFile(String filename,
			ArrayList<Double> AT_values, ArrayList<Double> DG_values,
			ArrayList<Double> trainRMSE, ArrayList<Double> validateRMSE,
			String id, boolean getAVG) {

		if (getAVG) {
			double avg_AT = 0.0;
			double avg_DG = 0.0;
			double avg_RMSE_AT = 0.0;
			double avg_RMSE_DG = 0.0;

			for (int i = 0; i < AT_values.size(); ++i) {
				avg_AT += AT_values.get(i);
				avg_DG += DG_values.get(i);
				avg_RMSE_AT += trainRMSE.get(i);
				avg_RMSE_DG += validateRMSE.get(i);
			}
			avg_AT /= AT_values.size();
			avg_DG /= DG_values.size();
			avg_RMSE_AT /= trainRMSE.size();
			avg_RMSE_DG /= validateRMSE.size();
			average_AT.add(avg_AT);
			average_DG.add(avg_DG);
			average_RMSE_AT.add(avg_RMSE_AT);
			average_RMSE_DG.add(avg_RMSE_DG);
		}

		File file = new File(filename + ".csv");
		try {
			DataOutputStream dataStream = new DataOutputStream(
					new FileOutputStream(file));

			if (getAVG) {
				for (int i = 0; i < AT_values.size(); i++) {
					int epoch = i + 1;
					writeData(dataStream, id + "," + epoch);
					writeData(dataStream, ",," + "AT" + "," + AT_values.get(i)
							+ ",,");
					writeData(dataStream, "DG" + "," + DG_values.get(i) + ",,");
					writeData(dataStream, "RMSE_AT" + "," + trainRMSE.get(i)
							+ ",,");
					writeData(dataStream, "RMSE_DG" + "," + validateRMSE.get(i)
							+ "\n");
				}
			} else {
				for (int i = 0; i < AT_values.size(); i++) {
					int epoch = i + 1;
					writeData(dataStream, id + "," + epoch);
					writeData(dataStream,
							",," + "AT (ave)" + "," + AT_values.get(i) + ",,");
					writeData(dataStream, "DG (ave)" + "," + DG_values.get(i)
							+ ",,");
					writeData(dataStream,
							"RMSE_AT (ave)" + "," + trainRMSE.get(i) + ",,");
					writeData(dataStream,
							"RMSE_DG (ave)" + "," + validateRMSE.get(i) + "\n");
				}
			}
			try {
				dataStream.close();
			} catch (IOException e) {
				System.out.println("Cannot close file.");
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error writing output data file: " + filename);
		}
	}

	/**
	 * Method used by writeToFile().
	 * 
	 * @param dataStream
	 *            DataOutputStream
	 * @param data
	 *            String of data to be written
	 */
	private static void writeData(DataOutputStream dataStream, String data) {
		try {
			dataStream.writeBytes(data);
			dataStream.flush();
		} catch (IOException e) {
			System.out.println("There has been an error:" + e);
		}
	}

	/**
	 * Method that presents the user with a GUI for entering an integer value.
	 * 
	 * @param question
	 * @param min
	 * @param max
	 * @return in Integer value input
	 */
	private static int getUserIntInput(String question, int min, int max) {
		int in = min - 1;
		while (in < min || in > max) {
			String userIn = JOptionPane.showInputDialog(question + " (" + min
					+ " - " + max + ")");
			in = Integer.parseInt(userIn);
		}
		return in;
	}

	/**
	 * Method that presents the user with a GUI for entering an integer value.
	 * 
	 * @param question
	 * @param min
	 * @param max
	 * @return in Double value input
	 */
	private static double getUserDoubleInput(String question, double min,
			double max) {
		double in = min - 1;
		while (in < min || in > max) {
			String userIn = JOptionPane.showInputDialog(question + " (" + min
					+ " - " + max + ")");
			in = Double.parseDouble(userIn);
		}
		return in;
	}

	/**
	 * Method that prints information to the console at the start of the
	 * execution.
	 */
	private static void printHeader() {
		System.out.println("\nSystem parameters: \n");
		System.out
				.println("Number of hidden layer neurons: " + hiddenLayerSize);
		System.out.println("Learning rate: " + learningRate);
		System.out.println("Momentum: " + momentum);
		System.out.println("Maximum number of epochs: " + maxEpoch);
		System.out.println("Number of runs: " + numRuns);
		System.out.println("Training set division ratio: "
				+ trainingValuationRatio);
		System.out.println();
		System.out.println("Processing ... \n");
	}

	/**
	 * Method that prints information to the console at the end of the
	 * execution.
	 */
	private static void printFooter(double averageOfAverage_AT,
			double averageOfAverage_DG) {
		DecimalFormat df2 = new DecimalFormat("##.####");

		System.out.println("\n\n******************************************\n");
		System.out.println("Average AT accuracy (" + numRuns + " runs): "
				+ df2.format(averageOfAverage_AT) + "%");

		System.out.println("Average DG accuracy (" + numRuns + " runs): "
				+ df2.format(averageOfAverage_DG) + "%");

		System.out.println("\n******************************************");
		System.out
				.println("\nFor a summary of AT, DG and RMSE results, \nrefer to \"Results - averages.csv\".");
		System.out.println("\n******************************************");
		System.out.println("******************************************");
	}
}