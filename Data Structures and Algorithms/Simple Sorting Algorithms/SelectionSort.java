// Selection sort (repeatedly find the smallest value and bring towards the front of the array).
// Runtime complexity: worst and average case: O(n^2).

public class SelectionSort {

	private static void selectionSort(int[] array) {
		for (int i = 0; i < array.length - 1; ++i) {
			int index = i;
			for (int j = i + 1; j < array.length; ++j) {
				if (array[index] > array[j]) {
					index = j;
				}
			}

			int smallest = array[index];
			array[index] = array[i];
			array[i] = smallest;
		}
	}

	public static void main(String[] args) {
		int[] array = { 5, 4, 3, 2, 1 };
		System.out.println("Array Before Selection Sort");
		printArray(array);

		selectionSort(array);

		System.out.println();
		System.out.println("Array After Selection Sort");
		printArray(array);
	}

	private static void printArray(int[] array) {
		for (int i = 0; i < array.length; ++i) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
