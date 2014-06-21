// Carla de Beer
// Leftist Binary Tree 
// (based on code by Mark Allen Weiss in "Data Structures and Algorithm Analysis in C++")
// Date created: 22/03/2014

public class Main {
	public static void main(String[] args) {

		System.out.println("Testing enqueue:");
		System.out.println();
		System.out.println();

		LeftistBinaryTree<Integer> heap = new LeftistBinaryTree<Integer>(2);
		LeftistBinaryTree<Integer> empty = new LeftistBinaryTree<Integer>(2);

		heap.enqueue(5);
		heap.enqueue(8);
		heap.enqueue(9);
		heap.enqueue(19);
		heap.enqueue(32);
		heap.enqueue(7);
		heap.enqueue(17);
		heap.enqueue(20);
		heap.enqueue(24);
		heap.enqueue(21);
		heap.enqueue(1);

		heap.printTree();
		System.out.println();
		System.out.println();

		System.out.println();
		System.out.println("Smallest value in heap = " + heap.findMin());
		System.out.println();

		// -------------------------------------------------------------

		System.out.println("Testing dequeue:");
		System.out.println();
		System.out.println();

		heap.dequeue();
		heap.dequeue();
		heap.printTree();

		System.out.println();
		System.out.println();
		System.out.println("Smallest value in heap = " + heap.findMin());
		System.out.println();

		// -------------------------------------------------------------

		System.out.println("Testing traversals:");
		System.out.println();
		System.out.println("Breadth first:");
		String a = heap.breadthFirstSearch();
		System.out.println(a);
		System.out.println();

		System.out.println("Preorder:");
		String b = heap.depthFirstPreOrder();
		System.out.println(b);
		System.out.println();

		System.out.println("Postorder:");
		String c = heap.depthFirstPostOrder();
		System.out.println(c);
		System.out.println();

		System.out.println("Testing traversals on empty trees:");

		String x = empty.depthFirstPreOrder();
		System.out.println(x);

		String xy = empty.depthFirstPostOrder();
		System.out.println(xy);

		String xx = empty.breadthFirstSearch();
		System.out.println(xx);

		int numItems = 100;
		LeftistBinaryTree<Integer> h = new LeftistBinaryTree<Integer>(2);
		LeftistBinaryTree<Integer> h1 = new LeftistBinaryTree<Integer>(2);

		int i = 37;

		for (i = 37; i != 0; i = (i + 37) % numItems)
			if (i % 2 == 0)
				h1.enqueue(new Integer(i));
			else
				h.enqueue(new Integer(i));

		System.out.println();
		System.out.println("Merging trees: ");

		h.merge(h1);

		System.out.println();

		h.printTree();

		// -----------------------

		LeftistBinaryTree<Integer> test1 = new LeftistBinaryTree<Integer>(2);
		LeftistBinaryTree<Integer> test2 = new LeftistBinaryTree<Integer>(2);

		test1.enqueue(3);
		test1.enqueue(8);
		test1.enqueue(10);
		test1.enqueue(21);
		test1.enqueue(14);
		test1.enqueue(23);
		test1.enqueue(17);
		test1.enqueue(26);

		test2.enqueue(6);
		test2.enqueue(7);
		test2.enqueue(12);
		test2.enqueue(18);
		test2.enqueue(24);
		test2.enqueue(37);
		test2.enqueue(33);
		test2.enqueue(18);
		System.out.println();

		System.out.println("-----------------------------------");
		System.out.println();
		test1.printTree();
		System.out.println();
		System.out.println("-----------------------------------");
		System.out.println();

		test2.printTree();
		System.out.println();
		System.out.println("-----------------------------------");
		System.out.println();

		System.out.println("Breadth first:");
		String aa = test2.breadthFirstSearch();
		System.out.println(aa);
		System.out.println();

		System.out.println("Preorder:");
		String bb = test2.depthFirstPreOrder();
		System.out.println(bb);
		System.out.println();

		System.out.println("Postorder:");
		String cc = test2.depthFirstPostOrder();
		System.out.println(cc);
		System.out.println();

		test1.merge(test2);

		System.out.println();
		System.out.println();
		test1.printTree();
		System.out.println();
		System.out.println();

		System.out.println("Breadth first:");
		String aaa = test1.breadthFirstSearch();
		System.out.println(aaa);
		System.out.println();

		System.out.println("Preorder:");
		String bbb = test1.depthFirstPreOrder();
		System.out.println(bbb);
		System.out.println();

		System.out.println("Postorder:");
		String ccc = test1.depthFirstPostOrder();
		System.out.println(ccc);
		System.out.println();

	}
}

