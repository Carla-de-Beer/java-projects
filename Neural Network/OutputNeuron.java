import java.util.Random;

/**
 * OutputNeuron class.
 * 
 * @author cadebe
 * 
 */
public class OutputNeuron {

	HiddenLayer hiddenLayer;
	double[] weights = null;
	double frequency;
	int fanin;
	int type = -1;

	/**
	 * Constructor with parameters that defines the OutputNeuron.
	 * 
	 * @param size
	 *            integer value that expresses the number of output neurons
	 * @param fanin
	 *            integer value of weights leading into an output neuron node
	 * @param hiddenLayer_
	 *            HiddenLayer object
	 */
	public OutputNeuron(int size, int fanin, HiddenLayer hiddenLayer_) {
		this.fanin = fanin;
		this.hiddenLayer = hiddenLayer_;
		this.frequency = 0;

		this.weights = new double[hiddenLayer_.getSize()];
		Random random = new Random();
		double min = -(1 / Math.sqrt(fanin));
		double max = (1 / Math.sqrt(fanin));

		for (int i = 0; i < weights.length; i++) {
			weights[i] = (random.nextDouble() * ((max - min)) + min);
		}
	}

	/**
	 * Method that adjust the hidden-to-output layer neurons.
	 * 
	 * @param errorSig
	 *            double value that defines the error signal value
	 * @param l
	 *            double value that defines the learning rate
	 * @param m
	 *            double value that defines the momentum rate
	 * @param deltaWeight_
	 *            2D array of double values that defines the delta weight
	 *            adjustment
	 * @return double value that defines the delta weight
	 */
	public double[] adjustWeights(double errorSig, double l, double m,
			double[] deltaWeight_) {
		double[] deltaWeight = new double[weights.length];
		for (int i = 0; i < weights.length; i++) {
			deltaWeight[i] = -l * errorSig
					* hiddenLayer.getHiddenNode(i).getFrequency();
			if (deltaWeight_ != null) {
				deltaWeight[i] += m * deltaWeight_[i];
			}

			weights[i] += deltaWeight[i];
		}
		return deltaWeight;
	}

	/**
	 * Method that sets the input layer.
	 * 
	 * @param hiddenLayer_
	 *            HiddenLayer object
	 */
	public void setInput(HiddenLayer hiddenLayer_) {
		this.hiddenLayer = hiddenLayer_;
	}

	/**
	 * Getter method that retrieves the training accuracy value.
	 * 
	 * @param correctValue
	 *            double value that defines the correct value
	 * @return integer value that defines the accuracy
	 */
	public int getTrainingAccuracy(double correct) {
		if (correct == 1 && hiddenLayer.getType() == this.type) {
			return 1;
		} else if (correct == 0 && hiddenLayer.getType() != this.type) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Getter method that retrieves the error value.
	 * 
	 * @return double value that expresses the error value
	 */
	public double getErrorSignal() {
		double target = -1;
		if (type == hiddenLayer.getType())
			target = 1;
		else
			target = 0;
		return -(target - frequency) * (1 - frequency) * frequency;
	}

	/**
	 * Getter method that retrieves the net value of the hidden layer.
	 * 
	 * @return double value that defines the net value
	 */
	public double getNetHidden() {
		double netValue = 0;
		for (int i = 0; i < weights.length; i++) {
			netValue += weights[i]
					* hiddenLayer.getHiddenNode(i).getFrequency();
		}
		return netValue;
	}

	/**
	 * Getter method that retrieves the output value.
	 * 
	 * @param min
	 *            double value that defines the activation function minimum
	 *            threshold value
	 * @param max
	 *            double value that defines the activation function maximum
	 *            threshold value
	 * @return double value that expresses the frequency
	 */
	public double getOutput(double min, double max) {
		updateFrequency();
		double result = (double) (((int) (frequency * 100)) / 100.0);
		double upper = (double) (((int) (max * 100)) / 100.0);
		double lower = (double) (((int) (min * 100)) / 100.0);
		if (result <= lower) {
			return 0;
		}
		if (result >= upper) {
			return 1;
		}
		return frequency;
	}

	/**
	 * Getter method that retrieves the frequency.
	 * 
	 * @return double value that defines the frequency
	 */
	public double getFrequency() {
		return frequency;
	}

	/**
	 * Method that updates the frequency value.
	 */
	public void updateFrequency() {
		this.frequency = NeuralNetwork.sigmoid(getNetHidden());
	}
}
