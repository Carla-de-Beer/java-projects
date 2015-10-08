import java.awt.image.BufferedImage;
/**
 * FeatureExtractor class that undertakes the extraction of input data from the
 * input file selected. The class receives the array of input neurons that are
 * to be filled with the frequencies of the data extracted from the file.
 * 
 * @author cadebe
 * 
 */
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FeatureExtractor {

	private InputNeuron[] inputPattern = null;

	public FeatureExtractor(InputNeuron[] inputPattern_, int size) {
		inputPattern = new InputNeuron[size];
		for (int i = 0; i < size; ++i) {
			inputPattern[i] = inputPattern_[i];
		}
	}

	/**
	 * Method that sets the frequencies for each input node.
	 */
	public void setFrequencies() {
		int Total = 0;
		for (int i = 0; i < inputPattern.length; i++) {
			Total += inputPattern[i].getSize();
		}
		double min = Float.MAX_VALUE;
		double max = Float.MIN_VALUE;

		for (int i = 0; i < inputPattern.length; i++) {
			inputPattern[i].setFrequency(Total);

			if (inputPattern[i].getFrequency() > max) {
				max = inputPattern[i].getFrequency();
			}
			if (inputPattern[i].getFrequency() < min) {
				min = inputPattern[i].getFrequency();
			}
		}
		for (int i = 0; i < inputPattern.length; i++) {
			inputPattern[i].scaleFrequency(min, max);
		}
		inputPattern[inputPattern.length - 1].frequency = -1;
	}

	/**
	 * Method that reads each of the input files, and calculates the number of
	 * occurrences and frequencies of each colour bin.
	 * 
	 * @param path
	 *            String value to indicate file path
	 * @throws IOException
	 */
	public void readImage(File f) {
		try {
			BufferedImage BImage = ImageIO.read(f);

			for (int y = 0; y < BImage.getHeight(); y++) {
				for (int x = 0; x < BImage.getWidth(); x++) {
					int rgb = BImage.getRGB(x, y);
					int r = (rgb >> 16) & 0xFF;
					int g = (rgb >> 8) & 0xFF;
					int b = (rgb) & 0xFF;
					assignToDiscreteClasses(r, g, b);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void assignToDiscreteClasses(int r, int g, int b) {
		if (r >= 0 && r <= 85)
			if (g >= 0 && g <= 85)
				if (b >= 0 && b <= 85)
					inputPattern[0].increaseSize();
		if (r >= 0 && r <= 85)
			if (g >= 0 && g <= 85)
				if (b >= 86 && b <= 170)
					inputPattern[1].increaseSize();
		if (r >= 0 && r <= 85)
			if (g >= 0 && g <= 85)
				if (b >= 171 && b <= 255)
					inputPattern[2].increaseSize();
		if (r >= 0 && r <= 85)
			if (g >= 86 && g <= 170)
				if (b >= 0 && b <= 85)
					inputPattern[3].increaseSize();
		if (r >= 0 && r <= 85)
			if (g >= 86 && g <= 170)
				if (b >= 86 && b <= 170)
					inputPattern[4].increaseSize();
		if (r >= 0 && r <= 85)
			if (g >= 86 && g <= 170)
				if (b >= 171 && b <= 255)
					inputPattern[5].increaseSize();
		if (r >= 0 && r <= 85)
			if (g >= 171 && g <= 255)
				if (b >= 0 && b <= 85)
					inputPattern[6].increaseSize();
		if (r >= 0 && r <= 85)
			if (g >= 171 && g <= 255)
				if (b >= 86 && b <= 170)
					inputPattern[7].increaseSize();
		if (r >= 0 && r <= 85)
			if (g >= 171 && g <= 255)
				if (b >= 171 && b <= 255)
					inputPattern[8].increaseSize();
		if (r >= 86 && r <= 170)
			if (g >= 0 && g <= 85)
				if (b >= 0 && b <= 85)
					inputPattern[9].increaseSize();
		if (r >= 86 && r <= 170)
			if (g >= 0 && g <= 85)
				if (b >= 86 && b <= 170)
					inputPattern[10].increaseSize();
		if (r >= 86 && r <= 170)
			if (g >= 0 && g <= 85)
				if (b >= 171 && b <= 255)
					inputPattern[11].increaseSize();
		if (r >= 86 && r <= 170)
			if (g >= 86 && g <= 170)
				if (b >= 0 && b <= 85)
					inputPattern[12].increaseSize();
		if (r >= 86 && r <= 170)
			if (g >= 86 && g <= 170)
				if (b >= 86 && b <= 170)
					inputPattern[13].increaseSize();
		if (r >= 86 && r <= 170)
			if (g >= 86 && g <= 170)
				if (b >= 171 && b <= 255)
					inputPattern[14].increaseSize();
		if (r >= 86 && r <= 170)
			if (g >= 171 && g <= 255)
				if (b >= 0 && b <= 85)
					inputPattern[15].increaseSize();
		if (r >= 86 && r <= 170)
			if (g >= 171 && g <= 255)
				if (b >= 86 && b <= 170)
					inputPattern[16].increaseSize();
		if (r >= 86 && r <= 170)
			if (g >= 171 && g <= 255)
				if (b >= 171 && b <= 255)
					inputPattern[17].increaseSize();
		if (r >= 171 && r <= 255)
			if (g >= 0 && g <= 85)
				if (b >= 0 && b <= 85)
					inputPattern[18].increaseSize();
		if (r >= 171 && r <= 255)
			if (g >= 0 && g <= 85)
				if (b >= 86 && b <= 170)
					inputPattern[19].increaseSize();
		if (r >= 171 && r <= 255)
			if (g >= 0 && g <= 85)
				if (b >= 171 && b <= 255)
					inputPattern[20].increaseSize();
		if (r >= 171 && r <= 255)
			if (g >= 86 && g <= 170)
				if (b >= 0 && b <= 85)
					inputPattern[21].increaseSize();
		if (r >= 171 && r <= 255)
			if (g >= 86 && g <= 170)
				if (b >= 86 && b <= 170)
					inputPattern[22].increaseSize();
		if (r >= 171 && r <= 255)
			if (g >= 86 && g <= 170)
				if (b >= 171 && b <= 255)
					inputPattern[23].increaseSize();
		if (r >= 171 && r <= 255)
			if (g >= 171 && g <= 255)
				if (b >= 0 && b <= 85)
					inputPattern[24].increaseSize();
		if (r >= 171 && r <= 255)
			if (g >= 171 && g <= 255)
				if (b >= 86 && b <= 170)
					inputPattern[25].increaseSize();
		if (r >= 171 && r <= 255)
			if (g >= 171 && g <= 255)
				if (b >= 171 && b <= 255)
					inputPattern[26].increaseSize();
	}
}
