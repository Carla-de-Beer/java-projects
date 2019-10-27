// Carla de Beer
// Date created: 25/02/2014

/**
 * Binary Search Tree implementation that inserts and deletes nodes from the
 * tree
 */

public class Main {

	public static void main(String[] args) {

		Tree<Integer> myTree = new Tree<Integer>();

		myTree.insert(1);
		System.out.println();
		myTree.printTree();
		System.out.println();

		myTree.insert(1);
		System.out.println();
		myTree.printTree();
		System.out.println();

		myTree.insert(15);
		myTree.insert(4);
		myTree.insert(20);
		myTree.insert(17);
		myTree.insert(19);
		System.out.println();

		myTree.printTree();
		System.out.println();

		myTree.delete(17);
		System.out.println();
		myTree.printTree();
		System.out.println();

		myTree.insert(5);
		myTree.insert(6);
		myTree.insert(2);
		myTree.insert(1);
		myTree.insert(4);
		System.out.println();
		myTree.printTree();
		System.out.println();

		System.out.println("Count total number of nodes in tree: "
				+ myTree.nodeCount());
		System.out.println();

		myTree.delete(5);
		System.out.println();
		myTree.printTree();
		System.out.println();

		myTree.delete(1);
		System.out.println();
		myTree.printTree();
		System.out.println();

		myTree.delete(4);
		System.out.println();
		myTree.printTree();

		myTree.delete(7);
		System.out.println();
		myTree.printTree();

		System.out.println();
		System.out.println("Count number of leaf nodes in tree: "
				+ myTree.countLeaves());

		System.out.println("Count total number of nodes in tree: "
				+ myTree.nodeCount());
		System.out.println();

		myTree.delete(3);
		System.out.println();
		myTree.printTree();
		System.out.println();

		myTree.delete(2);
		System.out.println();
		myTree.printTree();
		System.out.println();

		myTree.delete(6);
		System.out.println();
		myTree.printTree();
		System.out.println();

		myTree.delete(5);
		System.out.println();
		myTree.printTree();
		System.out.println();

		myTree.delete(15);
		System.out.println();
		myTree.printTree();
		System.out.println();

		System.out.println("Count number of leaf nodes in tree: "
				+ myTree.countLeaves());

		System.out.println("Count total number of nodes in tree: "
				+ myTree.nodeCount());
	}
}
