/**
 * HiddenLayer class - contains the hidden node neurons.
 * 
 * @author cadebe
 * 
 */
public class HiddenLayer {

	private HiddenNeuron[] hiddenNeuron;
	public int fanin = -1;
	private InputLayer inputLayer;

	/**
	 * 
	 * @param size
	 *            integer value that expresses the number of hidenn layer
	 *            neurons
	 * @param fanin
	 *            integer that expresses the number of incoming weights
	 * @param weightSize
	 */
	public HiddenLayer(int size, int fanin, int weightSize) {
		hiddenNeuron = new HiddenNeuron[size];
		this.fanin = fanin;
		for (int i = 0; i < hiddenNeuron.length; i++) {
			hiddenNeuron[i] = new HiddenNeuron(weightSize, fanin, null);
		}
		hiddenNeuron[hiddenNeuron.length - 1].frequency = -1;
		hiddenNeuron[hiddenNeuron.length - 1].weights = new double[0];
	}

	/**
	 * Method that adjusts the weights in the hidden layer.
	 * 
	 * @param outErrorSignals
	 *            array of double values that contain the output layer signal
	 * @param l
	 *            double value that expresses the learning rate
	 * @param m
	 *            double value that expresses the momentum value
	 * @param deltaOutSignals
	 *            2D array that contains the delta values output layer signals
	 * @return
	 */
	public double[][] adjustWeights(double[] outErrorSignals, double l,
			double m, double[][] deltaOutSignals) {
		double[][] deltaWeight = new double[hiddenNeuron.length][];
		for (int i = 0; i < outErrorSignals.length; i++) {
			if (deltaOutSignals == null) {
				deltaWeight[i] = hiddenNeuron[i].adjustWeights(
						outErrorSignals[i], l, m, null);
			} else {
				deltaWeight[i] = hiddenNeuron[i].adjustWeights(
						outErrorSignals[i], l, m, deltaOutSignals[i]);
			}
		}
		return deltaWeight;
	}

	public void setInput(InputLayer inputLayer) {
		this.inputLayer = inputLayer;
		for (int i = 0; i < hiddenNeuron.length; i++) {
			hiddenNeuron[i].setInput(inputLayer);
		}
	}

	/**
	 * Method that updates the frequency of the hidden neurons.
	 */
	public void updateFrequency() {
		for (int i = 0; i < hiddenNeuron.length; i++) {
			hiddenNeuron[i].updateFrequency();
		}
	}

	/**
	 * Getter method that retrieves the size value of the hidden layer.
	 * 
	 * @return integer value that defines the hidden layer size
	 */
	public int getSize() {
		return hiddenNeuron.length;
	}

	/**
	 * Getter method that retrieves the input layer label value.
	 * 
	 * @return integer value that defines the label value
	 */
	public int getType() {
		return inputLayer.getType();
	}

	/**
	 * Getter method that retrieves the hidden node specified.
	 * 
	 * @param node
	 *            integer value that defines the node to be retrieved
	 * @return HiddenNeuron object corresponding to the node value
	 */
	public HiddenNeuron getHiddenNode(int neuron) {
		if (neuron >= 0 && neuron < hiddenNeuron.length) {
			return hiddenNeuron[neuron];
		}
		return null;
	}
}