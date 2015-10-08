import java.util.Random;

/**
 * HiddenNeuron class.
 * 
 * @author cadebe
 * 
 */
public class HiddenNeuron {

	int fanin;
	double[] weights = null;
	InputLayer inputLayer;
	double frequency;

	/**
	 * Constructor with parameters.
	 * 
	 * @param size
	 *            integer value that expresses the number of hidden layer
	 *            neurons
	 * @param fanin
	 *            integer value that defines the number of weights into the
	 *            hidden layer
	 * @param inputLayer_
	 *            InputLayer object
	 */
	public HiddenNeuron(int size, int fanin, InputLayer inputLayer_) {

		this.fanin = fanin;
		this.weights = new double[size];
		this.inputLayer = inputLayer_;
		this.frequency = 0;

		// Randomise the weights to small double values within the active range
		// of the activation function
		Random random = new Random();
		double min = -(1 / Math.sqrt(fanin));
		double max = (1 / Math.sqrt(fanin));

		// Initialise the hidden neuron weights to the randomised values
		// described above
		for (int i = 0; i < weights.length; i++) {
			weights[i] = (random.nextDouble() * ((max - min)) + min);
		}
	}

	/**
	 * Method that adjust the hidden neuron weights.
	 * 
	 * @param errorSignal
	 *            double value that expresses the error signal values
	 * @param l
	 *            double value that defines the learning rate
	 * @param m
	 *            double value that defined the momentum rate
	 * @param deltaWeight_
	 *            array of double values that defines the delta weight values
	 *            from the input layer
	 * @return double value that expresses the delta weight
	 */
	public double[] adjustWeights(double errorSignal, double l, double m,
			double[] deltaWeight_) {
		double[] deltaWeight = new double[weights.length];
		for (int i = 0; i < weights.length; i++) {
			deltaWeight[i] = -l * errorSignal
					* inputLayer.getInputNode(i).getFrequency();
			if (deltaWeight_ != null) {
				deltaWeight[i] += m * deltaWeight_[i];
			}

			weights[i] += deltaWeight[i];
		}
		return deltaWeight;
	}

	/**
	 * Method that sets the input layer values.
	 * 
	 * @param inputLayer_
	 *            InputLayer objects
	 */
	public void setInput(InputLayer inputLayer_) {
		this.inputLayer = inputLayer_;
	}

	/**
	 * Getter method that retrieves the net input value
	 * 
	 * @return double value that defines the net input value
	 */
	public double getNetInput() {
		double netSum = 0;
		for (int i = 0; i < weights.length; i++) {
			netSum += weights[i] * inputLayer.getInputNode(i).getFrequency();
		}
		return netSum;
	}

	/**
	 * Getter method that retrieves the frequency value.
	 * 
	 * @return double value that defines the frequency value.
	 */
	public double getFrequency() {
		return frequency;
	}

	/**
	 * Method that updates the frequency value.
	 */
	public void updateFrequency() {
		if (this.frequency != -1) {
			this.frequency = NeuralNetwork.sigmoid(getNetInput());
		}
	}
}