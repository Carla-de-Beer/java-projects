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
		}
	}

	public static void main(String[] args) {
		int[] array = { 5, 4, 3, 2, 1 };

		System.out.println("Array Before Bubble Sort");
		printArray(array);
		System.out.println();

		bubbleSort(array);

		System.out.println("Array After Bubble Sort");
		printArray(array);
	}

	private static void printArray(int[] array) {
		for (int i = 0; i < array.length; ++i) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

}
