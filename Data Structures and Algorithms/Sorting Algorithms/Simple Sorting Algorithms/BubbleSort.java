// Bubble sort (pair-wise comparison of values in an array).
// Runtime complexity: worst and average case: O(n^2).

public class BubbleSort {

	private static void bubbleSort(int[] array) {
		int n = array.length;
		int temp = 0;

		for (int i = 0; i < n; ++i) {
			for (int j = 1; j < n - i; ++j) {
				if (array[j - 1] > array[j]) {
					temp = array[j - 1];
					array[j - 1] = array[j];
					array[j] = temp;
				}
			}
			printArray(array);
		}
	}

	private static void bubbleSortReverse(int[] array) {
		int n = array.length;
		int temp = 0;

		for (int i = 0; i < n; ++i) {
			for (int j = 1; j < n - i; ++j) {
				if (array[j - 1] <= array[j]) {
					temp = array[j - 1];
					array[j - 1] = array[j];
					array[j] = temp;
				}
			}
			printArray(array);
		}
	}

	public static void main(String[] args) {
		System.out.println("--------------------------------------------");
		System.out.println("Array before bubble sort:");
		System.out.println("--------------------------------------------");
		int[] array = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
		printArray(array);
		System.out.println();

		bubbleSort(array);
		System.out.println();

		System.out.println("Sorted array:");
		printArray(array);

		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("Array before bubble sort (reverse):");
		System.out.println("--------------------------------------------");
		printArray(array);
		System.out.println();

		bubbleSortReverse(array);
		System.out.println();

		System.out.println("Reverse sorted array:");
		printArray(array);

		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("Array before bubble sort:");
		System.out.println("--------------------------------------------");
		int[] array2 = { 6, 5, 3, 1, 8, 7, 2, 4 };
		printArray(array2);
		System.out.println();

		bubbleSort(array2);
		System.out.println();

		System.out.println("Sorted array:");
		printArray(array2);
	}

	private static void printArray(int[] array) {
		for (int i = 0; i < array.length; ++i) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

}
