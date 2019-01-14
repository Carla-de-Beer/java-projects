/**
 * The Sorter class, containing a reference to the concrete strategy that should
 * be used.
 */

public class Sorter {

	private SortingStrategy strategy;

	Sorter() {
	}

	public void setSorter(SortingStrategy strategy) {
		this.strategy = strategy;
	}

	public void runSort(int[] array) {
		strategy.sort(array);
	}

	public void print(int[] array) {
		System.out.print("[ ");
		for (int i = 0; i < array.length; ++i) {
			System.out.print(array[i]);

			if (i < array.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println(" ]");
		System.out.println();
	}

}
