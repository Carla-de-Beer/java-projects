public class Main {

	public static void main(String[] args) {

		SLinkedList<Integer> list1 = new SLinkedList<Integer>();

		System.out.println("Adding to the list:");

		list1.addToHead(5);
		list1.addToHead(3);
		list1.addToHead(2);
		list1.addToHead(4);
		list1.addToHead(1);

		System.out.println("Print list: " + list1.printList());
		System.out.println("list length: " + list1.length());
		System.out.println();

		System.out.println("Get head: " + list1.printNodeInfo(list1.front()));
		System.out.println();

		System.out.println("Reverse list:");
		list1.reverseList();
		System.out.println("Print list: " + list1.printList());
		System.out.println();

		System.out.println("Found 9?: " + list1.search(9));
		System.out.println("Found 2?: " + list1.search(2));
		System.out.println();

		// System.out.println("Remove node with info value '2' from list:");
		// list1.deleteNode(2);
		// System.out.println("list length: " + list1.length());
		// System.out.println("Print list: " + list1.printList());
		// System.out.println("Found 2?: " + list1.search(2));
		// System.out.println();

		System.out.println("Destroy list:");
		list1.destroyList();
		System.out.println("list length: " + list1.length());
		System.out.println("Is list empty? " + list1.isEmpty());
		System.out.println(list1.printList());
		System.out.println();

		System.out.println("Found 9?: " + list1.search(9));
		System.out.println("Found 2?: " + list1.search(2));
		System.out.println();

	}

}
