public class InputNeuron {
	public int total;
	public double frequency;

	public InputNeuron(double freq) {
		frequency = freq;
	}

	public void increaseSize() {
		total++;
	}

	public void setFrequency(double LayerTotal) {
		frequency = (double) total / LayerTotal;
	}

	public void scaleFrequency(double min, double max) {
		double scale = ((frequency - min) / (max - min));
		double nMax = 1 / Math.sqrt(3);
		double nMin = (-1) / Math.sqrt(3);
		frequency = (scale * (nMax - nMin) + nMin);
	}

	public double getFrequency() {
		return frequency;
	}

	public int getSize() {
		return total;
	}
}
