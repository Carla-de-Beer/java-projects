// Carla de Beer 
// January 2015
// A Java BufferedImages class experiment based on code found at 
// http://alvinalexander.com/blog/post/java/getting-rgb-values-for-each-pixel-in-image-using-java-bufferedi
// Class that gets the RGB values of an image and calculates the bin classes for each image, 
// stored inside an ArrayList. Used as a base study for a larger artificial neural network project.

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class BufferedImageTest extends Component {
	static private int size;
	static private BufferedImage image;
	static private ArrayList<Integer> list = new ArrayList<Integer>();

	static private int c0, c1, c2, c3, c4, c5, c6, c7, c8;
	static private int c9, c10, c11, c12, c13, c14, c15, c16, c17;
	static private int c18, c19, c20, c21, c22, c23, c24, c25, c26;
	static private int red, green, blue, alpha;
	private String name = "7.jpg";

	public void printPixelARGB(int pixel) {
		alpha = (pixel >> 24) & 0xff;
		red = (pixel >> 16) & 0xff;
		green = (pixel >> 8) & 0xff;
		blue = (pixel) & 0xff;
		// System.out.println("rgba: " + red + ", " + green + ", " + blue + ", "
		// + alpha);
		getClasses();
	}

	private void marchThroughImage(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		// System.out.println("width, height: " + w + ", " + h);

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				// System.out.println("x,y: " + j + ", " + i);
				int pixel = image.getRGB(j, i);
				printPixelARGB(pixel);
				// System.out.println("");
			}
		}
	}

	public BufferedImageTest() {
		try {
			// get the BufferedImage, using the ImageIO class
			image = ImageIO.read(this.getClass().getResource(name));
			marchThroughImage(image);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	static private void getClasses() {
		if ((red >= 0 && red <= 85) && (green >= 0 && green <= 85)
				&& (blue >= 0 && blue <= 85))
			c0++;
		else if ((red >= 0 && red <= 85) && (green >= 0 && green <= 85)
				&& (blue >= 86 && blue <= 170))
			c1++;
		else if ((red >= 0 && red <= 85) && (green >= 86 && green <= 170)
				&& (blue >= 0 && blue <= 85))
			c2++;
		else if ((red >= 86 && red <= 170) && (green >= 0 && green <= 85)
				&& (blue <= 0 && blue >= 85))
			c3++;
		else if ((red >= 0 && red <= 85) && (green >= 86 && green <= 170)
				&& (blue >= 85 && blue <= 170))
			c4++;
		else if ((red >= 86 && red <= 170) && (green >= 86 && green <= 170)
				&& (blue >= 0 && blue <= 85))
			c5++;
		else if ((red >= 86 && red <= 170) && (green >= 0 && green <= 85)
				&& (blue >= 86 && blue <= 170))
			c6++;
		else if ((red >= 86 && red <= 170) && (green >= 86 && green <= 170)
				&& (blue >= 86 && blue <= 170))
			c7++;
		else if ((red >= 0 && red <= 85) && (green >= 0 && green <= 85)
				&& (blue >= 171 && blue <= 255))
			c8++;
		else if ((red >= 0 && red <= 85) && (green >= 171 && green <= 255)
				&& (blue >= 0 && blue <= 85))
			c9++;
		else if ((red >= 171 && red <= 255) && (green >= 0 && green <= 85)
				&& (blue >= 0 && blue <= 85))
			c10++;
		else if ((red >= 171 && red <= 255) && (green >= 171 && green <= 255)
				&& (blue >= 0 && blue <= 85))
			c11++;
		else if ((red >= 171 && red <= 255) && (green >= 0 && green <= 85)
				&& (blue >= 171 && blue <= 255))
			c12++;
		else if ((red >= 0 && red <= 85) && (green >= 171 && green <= 255)
				&& (blue >= 171 && blue <= 255))
			c13++;
		else if ((red >= 171 && red <= 255) && (green >= 171 && green <= 255)
				&& (blue >= 171 && blue <= 255))
			c14++;
		else if ((red >= 171 && red <= 255) && (green >= 0 && green <= 85)
				&& (blue >= 86 && blue <= 170))
			c15++;
		else if ((red >= 171 && red <= 255) && (green >= 86 && green <= 170)
				&& (blue >= 0 && blue <= 85))
			c16++;
		else if ((red >= 86 && red <= 170) && (green >= 171 && green <= 255)
				&& (blue >= 0 && blue <= 85))
			c17++;
		else if ((red >= 0 && red <= 85) && (green >= 171 && green <= 255)
				&& (blue >= 86 && blue <= 170))
			c18++;
		else if ((red >= 86 && red <= 170) && (green >= 0 && green <= 86)
				&& (blue >= 171 && blue <= 255))
			c19++;
		else if ((red >= 171 && red <= 255) && (green >= 0 && green <= 85)
				&& (blue >= 86 && blue <= 170))
			c20++;
		else if ((red >= 171 && red <= 255) && (green >= 86 && green <= 170)
				&& (blue >= 86 && blue <= 170))
			c21++;
		else if ((red >= 86 && red <= 170) && (green >= 171 && green <= 255)
				&& (blue >= 86 && blue <= 170))
			c22++;
		else if ((red >= 86 && red <= 170) && (green >= 86 && green <= 170)
				&& (blue >= 171 && blue <= 255))
			c23++;
		else if ((red >= 171 && red <= 255) && (green >= 171 && green <= 255)
				&& (blue >= 86 && blue <= 170))
			c24++;
		else if ((red >= 171 && red <= 255) && (green >= 86 && green <= 170)
				&& (blue >= 171 && blue <= 255))
			c25++;
		else if ((red >= 86 && red <= 170) && (green >= 171 && green <= 255)
				&& (blue >= 171 && blue <= 255))
			c26++;
	}

	static private void printClasses() {

		System.out.println("c0 = " + c0);
		System.out.println("c1 = " + c1);
		System.out.println("c2 = " + c2);
		System.out.println("c3 = " + c3);
		System.out.println("c4 = " + c4);
		System.out.println("c5 = " + c5);
		System.out.println("c6 = " + c6);
		System.out.println("c7 = " + c7);
		System.out.println("c8 = " + c8);
		System.out.println("c9 = " + c9);
		System.out.println("c10 = " + c10);
		System.out.println("c11 = " + c11);
		System.out.println("c12 = " + c12);
		System.out.println("c13 = " + c13);
		System.out.println("c14 = " + c14);
		System.out.println("c15 = " + c15);
		System.out.println("c16 = " + c16);
		System.out.println("c17 = " + c17);
		System.out.println("c18 = " + c18);
		System.out.println("c19 = " + c19);
		System.out.println("c20 = " + c20);
		System.out.println("c21 = " + c21);
		System.out.println("c22 = " + c22);
		System.out.println("c23 = " + c23);
		System.out.println("c24 = " + c24);
		System.out.println("c25 = " + c25);
		System.out.println("c26 = " + c26);
	}

	static private void makeList() {
		list.add(c0);
		list.add(c1);
		list.add(c2);
		list.add(c3);
		list.add(c4);
		list.add(c5);
		list.add(c6);
		list.add(c7);
		list.add(c8);
		list.add(c9);
		list.add(c10);
		list.add(c11);
		list.add(c12);
		list.add(c13);
		list.add(c14);
		list.add(c15);
		list.add(c16);
		list.add(c17);
		list.add(c18);
		list.add(c19);
		list.add(c20);
		list.add(c21);
		list.add(c22);
		list.add(c23);
		list.add(c24);
		list.add(c25);
		list.add(c26);
	}

	public static void main(String[] args) {

		new BufferedImageTest();

		int w = image.getWidth();
		int h = image.getHeight();
		System.out.println("Width = " + w);
		System.out.println("Height = " + h);
		System.out.println("Image size = " + h * w);
		System.out.println("");

		printClasses();

		makeList();
	}
}