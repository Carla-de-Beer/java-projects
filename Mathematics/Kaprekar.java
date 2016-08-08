
// Program to calculate the number of iterations needed in order to find the Kaprekar constant 
// for a 4-digit number with at least three different digits. 
// For simplicity's sake, only number starting with a non-zero value were accepted.
// 01.08.2016
// Carla de Beer
// Kapekar's constant: https://en.wikipedia.org/wiki/6174_(number)

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Kaprekar {

	private static int KAPREKAR = 6174;
	private static int LIMIT = 20;

	public static void main(String[] args) throws IOException {

		int total = 0;
		double average = 0;
		for (int i = 0; i < LIMIT; ++i) {
			total += generateKaprekar(i);
		}
		average = total / (double) LIMIT;
		printAverage(average);
	}

	private static int generateKaprekar(int iter) {

		int[] randArray = new int[4];
		randArray = generateRandomArray();

		// Check if more than two occurrences of a single digits is present
		int[] sorted = new int[4];
		for (int i = 0; i < 4; ++i) {
			sorted[i] = randArray[i];
		}

		Arrays.sort(sorted);
		if (sorted[0] == sorted[1] && sorted[0] == sorted[2] && sorted[0] == sorted[3]) {
			randArray = generateRandomArray();
		}
		if (sorted[0] == sorted[1] && sorted[0] == sorted[2]) {
			randArray = generateRandomArray();
		}

		// Proceed to calculate the Kaprekar constant
		int num = convertToInt(randArray);
		int original = num;
		Integer result;

		int count = 0;
		result = 0;

		while (result != KAPREKAR) {
			Integer[] high;
			Integer[] array = convertToArray(num);
			Arrays.sort(array, Collections.reverseOrder());
			high = array;

			int[] hh = new int[high.length];
			int[] ll = new int[high.length];

			for (int i = 0; i < array.length; ++i) {
				hh[i] = high[i].intValue();
			}

			Arrays.sort(hh);
			ll = hh;

			Arrays.sort(array, Collections.reverseOrder());
			high = array;

			Integer h;
			int l;

			l = convertToInt(ll);
			// System.out.println("ascending: " + l);

			h = convertToInteger(high);
			// System.out.println("descending: " + h);

			result = h.intValue() - l;
			// System.out.println("result: " + result);

			num = result;
			count++;
		}
		printResult(iter, original, count);
		return count;
	}

	private static Integer[] convertToArray(int input) {
		String temp = Integer.toString(input);
		Integer[] array = new Integer[temp.length()];
		for (int i = 0; i < temp.length(); i++) {
			array[i] = temp.charAt(i) - '0';
		}
		return array;
	}

	private static int convertToInt(int[] input) {
		StringBuilder strNum = new StringBuilder();
		for (int num : input) {
			strNum.append(num);
		}
		int finalInt = Integer.parseInt(strNum.toString());
		return finalInt;
	}

	private static Integer convertToInteger(Integer[] input) {
		StringBuilder strNum = new StringBuilder();
		for (int num : input) {
			strNum.append(num);
		}
		int finalInt = Integer.parseInt(strNum.toString());
		return new Integer(finalInt);
	}

	private static int[] generateRandomArray() {
		Random rand = new Random();
		int[] randArray = new int[4];
		// Set first value not to be zero
		int first = rand.nextInt(9) + 1;
		randArray[0] = first;
		for (int i = 1; i < 4; ++i) {
			int randomNum = rand.nextInt(9 + 1);
			randArray[i] = randomNum;
		}
		// for (int i = 0; i < randArray.length; ++i) {
		// System.out.println("randArray[" + i + "] = " + randArray[i]);
		// }
		return randArray;
	}

	private static void printResult(int iter, int original, int count) {
		System.out.println();
		System.out.println("Iteration: " + iter);
		System.out.println("Input value: " + original);
		System.out.println("Kaprekar constant found after " + count + " iterations");
		System.out.println();
		System.out.println("---------------------------------------------------");
	}

	private static void printAverage(double average) {
		System.out.println("---------------------------------------------------");
		System.out.println("---------------------------------------------------");
		System.out.println();
		System.out.println("Average number of iterations needed over " + LIMIT + " runs: ");
		System.out.printf("%.2f", average);
		System.out.println();
		System.out.println();
		System.out.println("---------------------------------------------------");
	}
}
