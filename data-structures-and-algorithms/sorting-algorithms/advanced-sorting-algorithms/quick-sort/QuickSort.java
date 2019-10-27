// Merge sort
// Runtime complexity: worst and average case: O(nlogn).

public class QuickSort {

	private static int partition(int[] array, int low, int high) {
		int pivot = array[high];
		int i = low - 1; // index of smaller element

		for (int j = low; j < high; ++j) {
			// Is current element smaller or equal to pivot?
			if (array[j] <= pivot) {
				i++;

				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}

		// Move pointer
		int temp = array[i + 1];
		array[i + 1] = array[high];
		array[high] = temp;

		return i + 1;
	}

	private static void sort(int[] array, int low, int high) {
		if (high > low) {
			int pivot = partition(array, low, high);
			sort(array, low, pivot - 1);
			sort(array, pivot + 1, high);
			printArray(array);
		}
	}

	static void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] array = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
		System.out.println("Array before quick sort:");
		printArray(array);

		System.out.println();

		sort(array, 0, array.length - 1);

		System.out.println();
		System.out.println("Array sorted:");
		printArray(array);

		System.out.println();

		int[] array2 = { 6, 5, 3, 1, 8, 7, 2, 4 };
		System.out.println("Array before quick sort:");
		printArray(array2);

		System.out.println();

		sort(array2, 0, array2.length - 1);

		System.out.println();
		System.out.println("Array sorted:");
		printArray(array2);
	}

}
