// Carla de Beer
// Recursively implemented bubble sorter
// Date created: 06/05/2014

import java.util.Arrays;

public class RecursiveBubbleSorter<T extends Comparable<? super T>> {

	// Recursively implemented bubble sort method
	public T[] sort(T[] l) {

		collectOutput(l);

		if (l.length > 0) {

			for (int j = l.length - 1; j > 0; --j) {

				if (l[j].compareTo(l[j - 1]) < 0) {
					T currentNumber = l[j];
					l[j] = l[j - 1];
					l[j - 1] = currentNumber;
				}
			}
			T[] small;
			if (l.length > 1) {
				small = sort(Arrays.copyOfRange(l, 1, l.length));
				int pos = l.length - small.length;
				for (int k = 0; k < small.length; ++k)
					l[k + pos] = small[k];
			}
		}

		return l;
	}

	static int i = 0;

	protected static int increment() {
		return i++;
	}

	static String output = "";

	void collectOutput(T[] l) {
		output += "[";
		for (int i = 0; i < l.length; ++i) {
			output += l[i].toString() + ",";
		}

		output = output.substring(0, output.length() - 1);
		output += "]";
		output += "\n";
	}

	public String getOutput() {
		return output;
	}

	public void clearOutput() {
		output = "";
	}
}
