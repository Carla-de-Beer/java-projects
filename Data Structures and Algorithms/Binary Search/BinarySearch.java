// Carla de Beer
// Date created: 09/06/2018

/**
 * Recursive binary search implementation using generics. The runtime complexity
 * for this algorithm is O(log n). Note, the array must be sorted.
 */

public class BinarySearch<T extends Comparable<T>> {

	public int binarySearch(T[] arr, T x, int begin, int len) {
		if (len < begin) {
			return -1;
		}

		int mid = begin + (len - begin) / 2;

		// If element is present in the middle
		if (arr[mid].compareTo(x) == 0) {
			return mid;
		}

		// If element is present in left sub-array
		else if (arr[mid].compareTo(x) > 0) {
			return binarySearch(arr, x, begin, mid - 1);
		}

		// If element is present in right sub-array
		return binarySearch(arr, x, mid + 1, len);
	}

	public static void main(String args[]) {
		System.out.println("Integer search:");

		BinarySearch<Integer> binarySearch = new BinarySearch<Integer>();
		Integer array[] = { 2, 3, 4, 10, 40, 55, 70, 85, 90, 100 };
		int n = 90;
		int result = binarySearch.binarySearch(array, n, 0, array.length - 1);

		if (result == -1) {
			System.out.println("Value " + n + " not found.");
		} else {
			System.out.println("Value " + n + " found at index: " + result + ".");
		}

		System.out.println();
		System.out.println("Integer search (value not present):");

		n = 0;
		int result2 = binarySearch.binarySearch(array, 93, 0, array.length - 1);

		if (result2 == -1) {
			System.out.println("Value " + n + " not found.");
		} else {
			System.out.println("Value " + n + " found at index: " + result2 + ".");
		}

		System.out.println();
		System.out.println("Double search:");

		BinarySearch<Double> binarySearchDouble = new BinarySearch<Double>();
		Double arrDouble[] = { 3.43, 3.54, 5.85, 7.83, 8.55, 8.90, 9.01, 9.32, 9.87, 9.99 };
		double m = 8.55;
		int result3 = binarySearchDouble.binarySearch(arrDouble, 8.55, 0, arrDouble.length - 1);

		if (result3 == -1) {
			System.out.println("Value " + m + " not found.");
		} else {
			System.out.println("Value " + m + " found at index: " + result3 + ".");
		}

		System.out.println();
		System.out.println("Double search (value not present):");

		m = 8.75;
		int result4 = binarySearchDouble.binarySearch(arrDouble, 8.75, 0, arrDouble.length - 1);

		if (result4 == -1) {
			System.out.println("Value " + m + " not found.");
		} else {
			System.out.println("Value " + m + " found at index: " + result4 + ".");
		}
	}
}
