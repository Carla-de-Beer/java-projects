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
			printArray(array);
		}
	}

	public static void insertionSortReverse(int[] array) {
		for (int j = 1; j < array.length; ++j) {
			int i = j - 1;
			int key = array[j];

			while ((i > -1) && array[i] <= key) {
				array[i + 1] = array[i];
				--i;
			}

			array[i + 1] = key;
			printArray(array);
		}
	}

	public static void main(String[] args) {
		System.out.println("--------------------------------------------");
		System.out.println("Array before insertion sort:");
		System.out.println("--------------------------------------------");
		int[] array = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
		printArray(array);
		System.out.println();

		insertionSort(array);
		System.out.println();

		System.out.println("Sorted array:");
		printArray(array);

		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("Array before insertion sort (reverse):");
		System.out.println("--------------------------------------------");
		printArray(array);
		System.out.println();

		insertionSortReverse(array);
		System.out.println();

		System.out.println("Reverse sorted array:");
		printArray(array);

		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("Array before insertion sort:");
		System.out.println("--------------------------------------------");
		int[] array2 = { 6, 5, 3, 1, 8, 7, 2, 4 };
		printArray(array2);
		System.out.println();

		insertionSort(array2);
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
