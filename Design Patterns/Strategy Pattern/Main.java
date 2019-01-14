// Carla de Beer
// Strategy Pattern example (created by means of an interface).
// This pattern define a family of (sorting) algorithms, encapsulating each one, and making them interchangeable. 
// Created: January 2019

public class Main {

	public static void main(String[] args) {
		Sorter sorter;

		System.out.println("Bubble sorter at work:");
		int[] array = { 90, 80, 70, 60, 50, 40, 30, 20, 10, 0 };
		sorter = new Sorter();
		sorter.setSorter(new BubbleSort());
		sorter.runSort(array);
		sorter.print(array);

		System.out.println("Insertion sorter at work:");
		int[] array2 = { 95, 85, 75, 65, 55, 45, 35, 25, 15, 0 };
		sorter.setSorter(new InsertionSort());
		sorter.runSort(array2);
		sorter.print(array2);

		System.out.println("Selection sorter at work:");
		int[] array3 = { 99, 88, 77, 66, 55, 44, 33, 22, 11, 0 };
		sorter.setSorter(new SelectionSort());
		sorter.runSort(array3);
		sorter.print(array3);
	}

}
