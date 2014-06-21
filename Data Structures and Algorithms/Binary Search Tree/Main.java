// Carla de Beer
// Binary Search Tree implementation
// Date created: 25/02/2014

/**
 * Binary Search Tree implementation that inserts and deletes nodes from the
 * tree
 */

public class Main {

	public static void main(String[] args) {

		Tree<Integer> myTree = new Tree<Integer>();

		myTree.insert(1);
		myTree.printTree();
		myTree.insert(1);
		myTree.printTree();

		myTree.insert(15);
		myTree.insert(4);
		myTree.insert(20);
		myTree.insert(17);
		myTree.insert(19);

		myTree.printTree();
		myTree.delete(17);
		myTree.printTree();

		myTree.insert(5);
		myTree.insert(6);
		myTree.insert(2);
		myTree.insert(1);
		myTree.insert(4);
		myTree.printTree();

		myTree.delete(5);
		myTree.printTree();

		myTree.delete(1);
		myTree.printTree();

		myTree.delete(4);
		myTree.printTree();

		myTree.delete(7);
		myTree.printTree();

		myTree.delete(3);
		myTree.printTree();

		myTree.delete(2);
		myTree.printTree();

		myTree.delete(6);
		myTree.printTree();

		myTree.delete(5);
		myTree.printTree();

		myTree.delete(15);
		myTree.printTree();

	}
}

