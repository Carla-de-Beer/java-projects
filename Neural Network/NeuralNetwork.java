import java.util.ArrayList;

/**
 * NeuralNetwork class. Undertakes the training and sets the accuracy.
 * 
 * @author cadebe
 * 
 */
public class NeuralNetwork {

	private InputLayer inputLayer;
	private HiddenLayer hiddenLayer;
	private OutputLayer outputLayer;

	// Sigmoid function threshold values
	private final double THRESHOLD_MIN = 0.3;
	private final double THRESHOLD_MAX = 0.7;

	private double learningRate;
	private double momentum;

	private ArrayList<Integer> list_AT;

	/**
	 * Constructor with parameters.
	 * 
	 * @param hiddenLayerSize
	 *            integer value that defines the number of hidden neurons
	 * @param learningRate_
	 *            double value that defines the learning rate
	 * @param momentum_
	 *            double value that defines the momentum
	 * @param fanin
	 *            integer value that defines the number of input weights
	 * @param inputSize
	 *            integer value that defines the input size
	 */
	public NeuralNetwork(int hiddenLayerSize, double learningRate_,
			double momentum_, int fanin, int inputSize) {
		learningRate = learningRate_;
		momentum = momentum_;
		hiddenLayer = new HiddenLayer(hiddenLayerSize, fanin, inputSize);
		outputLayer = new OutputLayer(2, fanin, hiddenLayer);

		outputLayer.getOutputNode(0).type = 0;
		outputLayer.getOutputNode(1).type = 1;
		list_AT = new ArrayList<Integer>();
	}

	public void setInputs(InputLayer inputLayer_) {
		inputLayer = inputLayer_;
		hiddenLayer.setInput(inputLayer);
		outputLayer.setInput(hiddenLayer);
	}

	/**
	 * Method that defines the training of the neural network.
	 * 
	 * @param inputLayer_
	 *            InputLayer object fed into the training algorithm
	 */
	public void train(InputLayer inputLayer_) {
		setInputs(inputLayer_);
		double[][] outErrorSignals = null;
		double[][] hidErrorSignals = null;

		int value_AT = 0;
		hiddenLayer.updateFrequency();
		outputLayer.updateFrequency();
		int accuracy = outputLayer.getTrainingAccuracy(THRESHOLD_MIN,
				THRESHOLD_MAX);
		value_AT += accuracy;

		double[] errorSigOutput = outputLayer.getOutputErrorSignals();
		outErrorSignals = outputLayer.adjustWeights(errorSigOutput,
				learningRate, momentum, outErrorSignals);
		double[] errorSigHidden = outputLayer
				.getHiddenErrorSignals(errorSigOutput);
		hidErrorSignals = hiddenLayer.adjustWeights(errorSigHidden,
				learningRate, momentum, hidErrorSignals);

		list_AT.add(value_AT);
	}

	/**
	 * Method that expresses the sigmoid activation function.
	 * 
	 * @param net
	 *            double value that contains the input parameter (the net value
	 *            from the summing function) as input to the function
	 * @return double value as function output
	 */
	public static double sigmoid(double net) {
		double sigmoidValue = 1 + (Math.pow(Math.E, -net));
		return 1.0 / sigmoidValue;
	}

	/**
	 * Method that parses InputLayer accuracy based on the activation function
	 * min and max threshold values.
	 * 
	 * @param inputLayer_
	 *            InputLayer object as method input
	 * @return
	 */
	public int feedForward(InputLayer inputLayer_) {
		setInputs(inputLayer_);
		hiddenLayer.updateFrequency();
		outputLayer.updateFrequency();
		return outputLayer.classify(THRESHOLD_MIN, THRESHOLD_MAX);
	}

	/**
	 * Method that returns the network accuracy based on the average value of
	 * each of the list of accuracies.
	 * 
	 * @param P_T
	 *            integer value
	 * @return double value that expresses the accuracy of the network
	 */
	public double getAccuracy(int patternSize) {
		double accuracy = getAccuracy();
		return (accuracy / patternSize) * 100;
	}

	/**
	 * Getter method that retrieves the accuracy value.
	 * 
	 * @return double value that expresses the accuracy
	 */
	private int getAccuracy() {
		int accuracy = 0;
		for (int i = 0; i < list_AT.size(); i++) {
			accuracy += list_AT.get(i);
		}
		return accuracy;
	}

	/**
	 * Method to reset the accuracy of the system.
	 */
	public void resetAccuracy() {
		list_AT.clear();
	}
}
