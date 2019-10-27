import java.io.IOException;
import java.util.Random;

/**
 * Abstract class that decouples the client from the service provider.
 * 
 * @author cadebe
 * 
 */
public abstract class Strategy {

	private final int COUNT = 32;

	public Strategy() {
	}

	public void runGA() throws IOException {
	};

	public void printStartInfo() {
	};

	public double getAverage() {
		return 0.0;
	};

	public String getBestSolution() {
		return null;
	}

	public double getBestFitness() {
		return 0.0;
	}

	/**
	 * Method that undertakes the mutation of a chromosome. A random position in
	 * the chromosome string is taken, and depending on the value of a second
	 * string is mutated into another
	 * 
	 * @param DNA
	 *            string value of the chromosome input
	 * @throws IOException
	 */
	public void mutate(StringBuilder DNA, String sequence) throws IOException {

		DNA.append(sequence);

		Random r = new Random();
		int randVal = r.nextInt(COUNT - 0);

		int randChar = r.nextInt(2 - 0);
		// System.out.println("Random char position: " + randVal);
		// System.out.println("Character choice: " + randChar);

		if (sequence.charAt(randVal) == 'B') {
			if (randChar == 0)
				DNA.setCharAt(randVal, 'S');
			else if (randChar == 1)
				DNA.setCharAt(randVal, 'H');
		} else if (sequence.charAt(randVal) == 'S') {
			if (randChar == 0)
				DNA.setCharAt(randVal, 'B');
			else if (randChar == 1)
				DNA.setCharAt(randVal, 'H');
		} else if (sequence.charAt(randVal) == 'H') {
			if (randChar == 0)
				DNA.setCharAt(randVal, 'B');
			else if (randChar == 1)
				DNA.setCharAt(randVal, 'S');
		}
		// System.out.println("R: " + DNA);
		// return DNA.toString();

		sequence = DNA.toString();
	}

	/**
	 * Method that undertakes the crossover of two strings. Java does NOT pass
	 * strings by reference. Note to self: Remember to feed only StringBuilder
	 * into this method, NOT strings themselves. Must NOT create NEW object
	 * inside function.
	 * 
	 * @param sb_A
	 *            StringBuilder A (first parent)
	 * @param sb_B
	 *            StringBuilder B (second parent)
	 * @param num
	 *            integer value to express the number of cross-over points
	 */
	public void crossOver(StringBuilder sb_A, StringBuilder sb_B, String A,
			String B, int num) {

		if (num != 1)
			return;

		Random r = new Random();
		int randVal = r.nextInt(COUNT - 0);

		sb_A.append(A.substring(0, randVal));
		// System.out.println(sb_A);
		sb_A.append(B.substring(randVal, COUNT));
		// System.out.println(sb_A);

		sb_B.append(B.substring(0, randVal));
		// System.out.println(sb_B);
		sb_B.append(A.substring(randVal, COUNT));
		// System.out.println(sb_B);

		A = sb_A.toString();
		B = sb_B.toString();
	}

	/**
	 * Method that employs selection sort on the array containing the double
	 * values, and shadow-masks the sorting pattern onto the object array to
	 * ensure that both arrays are sorted to the same pattern.
	 * 
	 * @param arr1
	 *            array containing the fitness double values
	 * @param arr2
	 *            array containing the Trader object values
	 */
	public void selectionSort(double[] arr1, Trader[] arr2) {

		int i, j, minIndex;
		double tmp1;
		Trader tmp2;

		int n = arr1.length;

		for (i = 0; i < n - 1; i++) {

			minIndex = i;

			for (j = i + 1; j < n; j++)

				// Checks by value of the double array
				if (arr1[j] < arr1[minIndex])
					minIndex = j;

			if (minIndex != i) {

				tmp1 = arr1[i];
				tmp2 = arr2[i];

				arr1[i] = arr1[minIndex];
				arr2[i] = arr2[minIndex];

				arr1[minIndex] = tmp1;
				arr2[minIndex] = tmp2;
			}
		}
	}

	/**
	 * Method to print out vital GA information at the start of runGA()
	 */
	public void printEndInfo() {

		System.out.println("\nProcessing complete.\n");

		System.out
				.println("\n****************************************************");
	}
}
