// Selection sort (repeatedly find the smallest value and bring towards the front of the array).
// Runtime complexity: worst and average case: O(n^2).

public class SelectionSort {

	private static void selectionSort(int[] array) {
		int smallest = 0;
		for (int i = 0; i < array.length; ++i) {
			int index = i;
			for (int j = i + 1; j < array.length; ++j) {
				if (array[index] > array[j]) {
					index = j;
				}
			}

			smallest = array[index];
			array[index] = array[i];
			array[i] = smallest;
			printArray(array);
		}
	}

	private static void selectionSortReverse(int[] array) {
		int largest = 0;
		for (int i = 0; i < array.length; ++i) {
			int index = i;
			for (int j = i + 1; j < array.length; ++j) {
				if (array[index] <= array[j]) {
					index = j;
				}
			}

			largest = array[index];
			array[index] = array[i];
			array[i] = largest;
			printArray(array);
		}
	}

	public static void main(String[] args) {
		System.out.println("--------------------------------------------");
		System.out.println("Array before selection sort:");
		System.out.println("--------------------------------------------");
		int[] array = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
		printArray(array);
		System.out.println();

		selectionSort(array);
		System.out.println();

		System.out.println("Sorted array:");
		printArray(array);

		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("Array before selection sort (reverse):");
		System.out.println("--------------------------------------------");
		printArray(array);
		System.out.println();

		selectionSortReverse(array);
		System.out.println();

		System.out.println("Reverse sorted array:");
		printArray(array);

		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("Array before selection sort:");
		System.out.println("--------------------------------------------");
		int[] array2 = { 6, 5, 3, 1, 8, 7, 2, 4 };
		printArray(array2);
		System.out.println();

		selectionSort(array2);
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
