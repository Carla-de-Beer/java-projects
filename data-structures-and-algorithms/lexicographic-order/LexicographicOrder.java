// Carla de Beer
// Program that generates the lexicographic ordering for a given character array
// Based on: https://www.quora.com/How-would-you-explain-an-algorithm-that-generates-permutations-using-lexicographic-ordering
// Created: August 2016

public class LexicographicOrder {

	private static void reverseTail(char[] a, int start) {
		char[] chunk = new char[a.length - start];
		System.arraycopy(a, start, chunk, 0, chunk.length);
		reverseArray(chunk);
		System.arraycopy(chunk, 0, a, start, chunk.length);
	}

	static private void reverseArray(char[] arr) {
		for (int i = 0; i < arr.length / 2; ++i) {
			char temp = arr[i];
			arr[i] = arr[arr.length - i - 1];
			arr[arr.length - i - 1] = temp;
		}
	}

	static private void swap(char[] a, int i, int j) {
		char temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	static private void printOutput(char[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
		}
		System.out.println();
	}

	static private void printCount(int count) {
		if (count < 10) {
			System.out.print(count + ":   ");
		} else if (count >= 10 && count < 100) {
			System.out.print(count + ":  ");
		} else {
			System.out.print(count + ": ");
		}
	}

	private static int factorial(int n) {
		if (n == 1) {
			return 1;
		}
		return n * factorial(n - 1);
	}

	public static void main(String[] args) {
		char[] order = { 'A', 'B', 'C', 'D', 'E' };
		int count = 0;
		int total = factorial(order.length);

		System.out.println("There are " + total + " permutations for this input sequence:");
		System.out.println();

		while (true) {

			char[] neworder = new char[order.length];
			System.arraycopy(order, 0, neworder, 0, order.length);

			int largest1 = -1;
			for (int i = 0; i < order.length - 1; i++) {
				if (order[i] < order[i + 1] && i > largest1) {
					largest1 = i;
				}
			}
			count++;
			if (largest1 == -1) {
				printCount(count);
				printOutput(order);
				break;
			}

			int largest2 = 0;
			for (int i = 0; i < order.length; i++) {
				if (order[largest1] < order[i] && i > largest2) {
					largest2 = i;
				}
			}

			swap(neworder, largest1, largest2);
			reverseTail(neworder, largest1 + 1);
			order = neworder;
			printCount(count);
			printOutput(order);
		}
	}
}
