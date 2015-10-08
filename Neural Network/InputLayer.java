import java.io.File;
import java.io.IOException;

/**
 * Input layer class - contains the input neurons.
 * 
 * @author cadebe
 * 
 */
public class InputLayer {

	private InputNeuron[] inputPattern;
	private FeatureExtractor readData;
	// Boolean value that determines whether an input pattern
	// is part of the first class
	private boolean isClass0;
	private File file;

	/**
	 * Constructor with parameters.
	 * 
	 * @param file
	 *            File object that contains the file information to be extracted
	 * @param isEnglish_
	 *            boolean value that determines whether the input value belongs
	 *            to the English language test set
	 * @param size
	 *            integer value that expresses the input layer size
	 * @throws IOException
	 */
	public InputLayer(File file_, boolean isClass0, int size)
			throws IOException {
		inputPattern = new InputNeuron[size];

		this.isClass0 = isClass0;
		file = file_;
		for (int i = 0; i < inputPattern.length - 1; i++) {
			inputPattern[i] = new InputNeuron(0);
		}
		inputPattern[inputPattern.length - 1] = new InputNeuron(-1);
		readData = new FeatureExtractor(inputPattern, size);
		readData.readImage(file);
		readData.setFrequencies();
	}

	/**
	 * Getter method that returns the size of the input layer.
	 * 
	 * @return integer value that defines the size of the input pattern
	 */
	public int getSize() {
		return inputPattern.length;
	}

	/**
	 * Method that retrieves the input neuron.
	 * 
	 * @param node
	 *            integer value that defines the node number
	 * @return InputNeuron object
	 */
	public InputNeuron getInputNode(int neuron) {
		if (neuron >= 0 && neuron < inputPattern.length) {
			return inputPattern[neuron];
		}
		return null;
	}

	/**
	 * Method that assigns label to input pattern.
	 * 
	 * @return integer value the expresses the classification type: isClass0 =
	 *         0, isClass1 = 1
	 */
	public int getType() {
		return isClass0 ? 0 : 1;
	}
}
