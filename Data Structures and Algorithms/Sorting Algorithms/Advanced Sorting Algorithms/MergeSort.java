// Merge sort
// Runtime complexity: worst and average case: O(nlogn).

public class MergeSort {

	private static void mergeSort(int[] array, int begin, int mid, int end) {
		int l = (mid - begin) + 1;
		int r = end - mid;

		int[] leftArray = new int[l];
		int[] rightArray = new int[r];

		for (int i = 0; i < l; ++i) {
			leftArray[i] = array[begin + i];
		}

		for (int i = 0; i < r; ++i) {
			rightArray[i] = array[mid + 1 + i];
		}

		int i = 0, j = 0;
		int k = begin;

		while (i < l && j < r) {
			if (leftArray[i] <= rightArray[j]) {
				array[k++] = leftArray[i++];
			} else {
				array[k++] = rightArray[j++];
			}
		}

		while (i < l) {
			array[k++] = leftArray[i++];
		}

		while (j < r) {
			array[k++] = rightArray[j++];
		}
	}

	private static void sort(int[] array, int begin, int end) {
		if (begin < end) {
			int mid = (begin + end) / 2;
			sort(array, begin, mid);
			sort(array, mid + 1, end);
			mergeSort(array, begin, mid, end);
			printArray(array);
		}
	}

	private static void printArray(int[] array) {
		for (int i = 0; i < array.length; ++i) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] array = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
		System.out.println("Array before merge sort:");
		printArray(array);

		System.out.println();

		sort(array, 0, array.length - 1);

		System.out.println();
		System.out.println("Array sorted:");
		printArray(array);

		System.out.println();

		int[] array2 = { 6, 5, 3, 1, 8, 7, 2, 4 };
		System.out.println("Array before merge sort:");
		printArray(array2);

		System.out.println();

		sort(array2, 0, array2.length - 1);

		System.out.println();
		System.out.println("Array sorted:");
		printArray(array2);
	}

}
