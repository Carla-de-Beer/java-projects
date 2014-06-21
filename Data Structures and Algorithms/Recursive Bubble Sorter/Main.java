// Carla de Beer
// Recursively implemented Bubble Sorter
// Date created: 06/05/2014

public class Main {

	public static void main(String[] args) {

		Integer array[] = { 23, 34, 5, 2, 17, 0, 33, 12, 9, 3, 5 };
		Integer reverse[] = { 100, 90, 80, 70, 60, 50, 40, 30, 20, 10, 0 };
		Integer single[] = { 0 };
		Integer empty[] = {};
		Integer same[] = { 7, 7, 7, 7, 7 };
		Character chars[] = { 'd', 'b', 'r', 'v', 'n', 'b', 'z', 'x', 'a' };

		RecursiveBubbleSorter<Integer> intSorter = new RecursiveBubbleSorter<Integer>();

		RecursiveBubbleSorter<Character> charSorter = new RecursiveBubbleSorter<Character>();

		intSorter.sort(array);
		System.out.println(intSorter.getOutput());
		intSorter.clearOutput();
		System.out.println();

		for (int i = 0; i < array.length; ++i)
			System.out.print(array[i] + " ");

		// intSorter.sort(reverse);
		// System.out.println(intSorter.getOutput());
		// intSorter.clearOutput();
		// System.out.println();
		//
		// intSorter.sort(single);
		// System.out.println(intSorter.getOutput());
		// intSorter.clearOutput();
		// System.out.println();
		//
		// intSorter.sort(empty);
		// System.out.println(intSorter.getOutput());
		// intSorter.clearOutput();
		// System.out.println();

		System.out.println();

		intSorter.sort(same);

		System.out.println();

		charSorter.sort(chars);

	}

}

