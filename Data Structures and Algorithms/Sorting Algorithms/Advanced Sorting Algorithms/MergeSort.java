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

	private static void sort(int[] arr, int begin, int end) {
		if (begin < end) {
			int mid = (begin + end) / 2;
			sort(arr, begin, mid);
			sort(arr, mid + 1, end);
			mergeSort(arr, begin, mid, end);
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

		sort(array, 0, array.length - 1);

		System.out.println();
		System.out.println("Array after merge sort:");
		printArray(array);
	}

}
