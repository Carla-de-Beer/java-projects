// Runtime complexity is O(log n)

public class BinarySearch<T extends Comparable<T>> {

	public int binarySearch(T arr[], int len, int pos, T x) {
		if (len >= pos) {
			int mid = pos + (len - pos) / 2;

			if (arr[mid].compareTo(x) == 0) {
				return mid;
			}

			// If element is present in left sub-array
			if (arr[mid].compareTo(x) > 0) {
				return binarySearch(arr, mid - 1, pos, x);
			}

			// If element is present in right sub-array
			return binarySearch(arr, len, mid + 1, x);
		}
		return -1;
	}

	public static void main(String args[]) {
		BinarySearch<Integer> binarySearch = new BinarySearch<Integer>();
		Integer arr[] = { 2, 3, 4, 10, 40, 55, 70, 85, 90, 100 };
		int result = binarySearch.binarySearch(arr, arr.length - 1, 0, 3);

		if (result == -1) {
			System.out.println("Value not found");
		} else {
			System.out.println("Value found at index: " + result);
		}
	}
}
