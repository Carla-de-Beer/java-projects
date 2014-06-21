// Carla de Beer
// Circular linked list and self-organising linked list
// Date created: 18/02/2014
// Date revised: 21/06/2014

public class Main {

	public static void main(String[] args) {

		CircularDLList<Integer> list1 = new CircularDLList<Integer>();
		CircularDLList<Integer> emptyList = new CircularDLList<Integer>();
		SOCircularDLList<Integer> so = new SOCircularDLList<Integer>();
		SOCircularDLList<Integer> emptySOList = new SOCircularDLList<Integer>();

		list1.addToHead(5);
		list1.addToHead(3);
		list1.addToHead(2);
		list1.addToHead(4);
		list1.addToTail(9);
		list1.addToHead(1);

		so.addToHead(5);
		so.addToHead(3);
		so.addToHead(9);
		so.addToHead(7);
		so.addToHead(1);

		try {
			emptySOList.find(9);

		} catch (EmptyListException e) {
			System.out.println(e);
		}
		list1.removeFromHead();
		list1.removeFromTail();

		System.out.println("List1 = " + list1);

		int headVal = list1.head();
		int tailVal = list1.tail();
		int answer = list1.find(5);
		boolean isEmpty1 = emptyList.isEmpty();
		boolean isEmpty2 = list1.isEmpty();
		int size1 = list1.getSize();
		int size2 = emptyList.getSize();

		System.out.println("Head = " + headVal);
		System.out.println("Head = " + tailVal);
		System.out.println("Find = " + answer);
		System.out.println("Is List2 empty? = " + isEmpty1);
		System.out.println("Is List1 empty? = " + isEmpty2);
		System.out.println("List1 size = " + size1);
		System.out.println("List2 size = " + size2);
		System.out.println("List1 = " + list1);

		try {
			emptyList.removeFromHead();
		}

		catch (EmptyListException e) {
			System.out.println(e);
		}
		try {
			emptyList.removeFromTail();
		}

		catch (EmptyListException e) {
			System.out.println(e);
		}

		System.out.println("SO = " + so);
		int findShift1 = so.find(7);
		System.out.println("Find / shift = " + findShift1);
		System.out.println("SO = " + so);

		int findShift2 = so.find(1);
		System.out.println("Find / shift = " + findShift2);
		System.out.println("SO = " + so);

		int findShift3 = so.find(5);
		System.out.println("Find / shift = " + findShift3);
		System.out.println("SO = " + so);

		int findShift4 = so.find(9);
		System.out.println("Find / shift = " + findShift4);
		System.out.println("SO = " + so);

		int findShift5 = so.find(9);
		System.out.println("Find / shift = " + findShift5);
		System.out.println("SO = " + so);

		int findShift6 = so.find(9);
		System.out.println("Find / shift = " + findShift6);
		System.out.println("SO = " + so);

		int findShift7 = so.find(5);
		System.out.println("Find / shift = " + findShift7);
		System.out.println("SO = " + so);

		int findShift8 = so.find(5);
		System.out.println("Find / shift = " + findShift8);
		System.out.println("SO = " + so);

		int findShift9 = so.find(5);
		System.out.println("Find / shift = " + findShift9);
		System.out.println("SO = " + so);

		int findShift10 = so.find(3);
		System.out.println("Find / shift = " + findShift10);
		System.out.println("SO = " + so);

		int findShift11 = so.find(11);
		System.out.println("Find / shift = " + findShift11);
		System.out.println("SO = " + so);

	}
}

