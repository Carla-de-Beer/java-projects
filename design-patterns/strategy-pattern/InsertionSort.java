/**
 * Insertion sorter (concrete SortingStrategy implementation).
 */

public class InsertionSort implements SortingStrategy {

	public void sort(int[] array) {
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

}
