// Carla de Beer
// Date created: 09/06/2018

/**
 * Recursive binary search implementation using generics. 
 * The runtime complexity for this algorithm is O(log n). 
 * Note, the array must be sorted.
 */

public class BinarySearch<T extends Comparable<T>> {

	public int binarySearch(T[] arr, T x, int len, int pos) {
		if (len < pos) {
			return -1;
		}

		int mid = pos + (len - pos) / 2;

		if (arr[mid].compareTo(x) == 0) {
			return mid;
		}

		// If element is present in left sub-array
		else if (arr[mid].compareTo(x) > 0) {
			return binarySearch(arr, x, mid - 1, pos);
		}

		// If element is present in right sub-array
		return binarySearch(arr, x, len, mid + 1);
	}

	public static void main(String args[]) {
		System.out.println("Integer search:");

		BinarySearch<Integer> binarySearch = new BinarySearch<Integer>();
		Integer arr[] = { 2, 3, 4, 10, 40, 55, 70, 85, 90, 100 };
		int result = binarySearch.binarySearch(arr, 90, arr.length - 1, 0);

		if (result == -1) {
			System.out.println("Value not found");
		} else {
			System.out.println("Value found at index: " + result);
		}

		System.out.println();
		System.out.println("Double search:");

		BinarySearch<Double> binarySearchDouble = new BinarySearch<Double>();
		Double arrDouble[] = { 3.43, 3.54, 5.85, 7.83, 8.55, 8.90, 9.01, 9.32, 9.87, 9.99 };
		int resultDouble = binarySearchDouble.binarySearch(arrDouble, 8.55, arrDouble.length - 1, 0);

		if (resultDouble == -1) {
			System.out.println("Value not found");
		} else {
			System.out.println("Value found at index: " + resultDouble);
		}
	}
}
