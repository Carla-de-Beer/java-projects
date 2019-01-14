/**
 * Selection sorter (concrete SortingStrategy implementation).
 */

public class SelectionSort implements SortingStrategy {

	public void sort(int[] array) {
		for (int i = 0; i < array.length - 1; ++i) {
			int index = i;
			for (int j = i + 1; j < array.length; ++j) {
				if (array[index] > array[j]) {
					index = j;
				}
			}

			int smallest = array[index];
			array[index] = array[i];
			array[i] = smallest;
		}
	}

}
