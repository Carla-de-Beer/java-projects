
/**
 * Class that converts an image into a binary file.
 */

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) throws Exception {
		// Read in a locally stored image file
		BufferedImage image = ImageIO.read(new File("./Images/blue-rose.jpeg"));

		// Write it to byte array in-memory (jpg format)
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", b);

		// Create a byte array and convert it to a StringBuilder
		byte[] jpgByteArray = b.toByteArray();

		StringBuilder sb = new StringBuilder();
		for (byte by : jpgByteArray)
			sb.append(Integer.toBinaryString(by & 0xFF));

		System.out.println(sb.toString());

		// Write the binary content to a file
		File file = new File("./Results/blue-rose.bin");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(sb.toString());
		}
	}
}
