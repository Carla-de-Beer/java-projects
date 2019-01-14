// Insertion sort (compare and sort the first two values, then continue with each successive array element).
// Runtime complexity: worst and average case: O(n^2).

public class InsertionSort {

	public static void insertionSort(int[] array) {
		for (int j = 1; j < array.length; ++j) {
			int i = j - 1;
			int key = array[j];

			while ((i > -1) && array[i] > key) {
				array[i + 1] = array[i];
				--i;
			}

			array[i + 1] = key;
		}
	}

	public static void main(String[] args) {
		int[] array = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
		System.out.println("Array before insertion sort");
		printArray(array);

		insertionSort(array);

		System.out.println();
		System.out.println("Array after insertion sort");
		printArray(array);
	}

	private static void printArray(int[] array) {
		for (int i = 0; i < array.length; ++i) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

}
