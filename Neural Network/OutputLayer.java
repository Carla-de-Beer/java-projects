/**
 * OutputLayer class - contains the output neurons.
 * 
 * @author cadebe
 * 
 */
public class OutputLayer {

	private OutputNeuron[] outNeuron;
	private HiddenLayer hiddenLayer;
	public int fanin = -1;
	private int accuracy_AT = 0;

	/**
	 * Constructor with parameters that define the output layer object.
	 * 
	 * @param size
	 *            integer value that defines the size of the output layer
	 * @param fanin
	 *            integer value that defines the number of weights leading into
	 *            an output layer node
	 * @param hiddenLayer_
	 *            HiddenLayer object
	 */
	public OutputLayer(int size, int fanin, HiddenLayer hiddenLayer_) {
		outNeuron = new OutputNeuron[size];
		this.fanin = fanin;
		for (int i = 0; i < outNeuron.length; i++) {
			outNeuron[i] = new OutputNeuron(hiddenLayer_.getSize(), fanin,
					hiddenLayer_);
		}
	}

	/**
	 * Method that adjusts the weights from the output layer to the input layer.
	 * 
	 * @param outErrorSig
	 *            array of double values that defines the error signal values
	 * @param l
	 *            double value that defines the learning rate
	 * @param m
	 *            double value that defines the momentum rate
	 * @param deltaOutSig
	 *            2D array of delta values
	 * @return double value that defines the delta weight
	 */
	public double[][] adjustWeights(double[] outErrorSig, double l, double m,
			double[][] deltaOutSig) {
		double[][] deltaWeight = new double[outNeuron.length][];
		for (int i = 0; i < outErrorSig.length; i++) {
			if (deltaOutSig == null) {
				deltaWeight[i] = outNeuron[i].adjustWeights(outErrorSig[i], l,
						m, null);
			} else {
				deltaWeight[i] = outNeuron[i].adjustWeights(outErrorSig[i], l,
						m, deltaOutSig[i]);
			}
		}
		return deltaWeight;
	}

	/**
	 * Method that classifies the output value obtained, with reference to the
	 * activation minimum and maximum threshold values.
	 * 
	 * @param min
	 *            double value that defines the minimum value of the activation
	 *            threshold
	 * @param max
	 *            double value that defines the maximum value of the activation
	 *            threshold
	 * @return double value that defines the output classification
	 */
	public int classify(double min, double max) {
		double out1 = outNeuron[0].getOutput(min, max);
		double out2 = outNeuron[1].getOutput(min, max);

		if (out1 == 1 && out2 == 0)
			return 0;
		if (out1 == 0 && out2 == 1)
			return 1;
		return -1;
	}

	/**
	 * Method that sets the input to the output layer nodes.
	 * 
	 * @param hiddenLayer_
	 *            HiddenLayer object
	 */
	public void setInput(HiddenLayer hiddenLayer_) {
		this.hiddenLayer = hiddenLayer_;
		for (int i = 0; i < outNeuron.length; i++) {
			outNeuron[i].setInput(hiddenLayer_);
		}
	}

	/**
	 * Getter method that retrieves an output neuron.
	 * 
	 * @param node
	 *            integer value that defines the node number whose output is to
	 *            be obtained
	 * @return OutputNeuron corresponding to the output node neuron provided
	 */
	public OutputNeuron getOutputNode(int neuron) {
		if (neuron >= 0 && neuron < outNeuron.length) {
			return outNeuron[neuron];
		}
		return null;
	}

	/**
	 * Getter method that retrieves the number of neurons in the output layer.
	 * 
	 * @return integer value that defines the size of the output layer
	 */
	public int getSize() {
		return outNeuron.length;
	}

	/**
	 * Getter method that retrieves the training data accuracy.
	 * 
	 * @param min
	 *            double value that defines the minimum value of the activation
	 *            function threshold
	 * @param max
	 *            double value that defines the maximum value of the activation
	 *            function threshold
	 * @return double value corresponding to the training accuracy
	 */
	public int getTrainingAccuracy(double min, double max) {
		accuracy_AT = 0;
		boolean result = true;
		for (int i = 0; i < outNeuron.length && result; i++) {
			int out = outNeuron[i].getTrainingAccuracy(outNeuron[i].getOutput(
					min, max));
			if (out == 0) {
				result = false;
			}
		}
		if (result) {
			return 1;
		}
		return accuracy_AT;
	}

	/**
	 * Method that updates the output neuron frequency.
	 */
	public void updateFrequency() {
		for (int i = 0; i < outNeuron.length; i++) {
			outNeuron[i].updateFrequency();
		}
	}

	/**
	 * Getter method that retrieves the output layer error values.
	 * 
	 * @return double value that defines the error value
	 */
	public double[] getOutputErrorSignals() {
		double[] errorSignals = new double[outNeuron.length];
		for (int i = 0; i < errorSignals.length; i++) {
			errorSignals[i] = outNeuron[i].getErrorSignal();
		}
		return errorSignals;
	}

	/**
	 * Getter method that retrieves the hidden error values.
	 * 
	 * @param outErrorSignals
	 *            array of double values that contain the error values from the
	 *            output layer
	 * @return double value that defines the error value
	 */
	public double[] getHiddenErrorSignals(double[] outErrorSignals) {
		double[] errorSignals = new double[hiddenLayer.getSize()];
		for (int i = 0; i < errorSignals.length; i++) {
			for (int j = 0; j < outErrorSignals.length; j++) {
				double w_kj = outNeuron[j].weights[i];
				double sig_o_k = outErrorSignals[j];
				double y_j = hiddenLayer.getHiddenNode(i).frequency;

				errorSignals[i] += w_kj * sig_o_k * (1 - y_j) * y_j;
			}
		}
		return errorSignals;
	}
}
