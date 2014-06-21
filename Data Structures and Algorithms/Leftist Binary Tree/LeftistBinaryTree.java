// Carla de Beer
// Leftist Binary Tree 
// (based on code by Mark Allen Weiss in "Data Structures and Algorithm Analysis in C++")
// Date created: 22/03/2014

public class LeftistBinaryTree<T extends Comparable<? super T>> {

	private int numChildren;
	private LeftistNode<T> root;

	/**
	 * The parameter d indicates the maximum number of children that any node is
	 * allowed to have. Nodes may have any number of children from 2 upwards.
	 * 
	 * @param d
	 */
	public LeftistBinaryTree(int d) {
		if (d >= 2)
			numChildren = d;

		else if (d < 2)
			numChildren = d = 2;

		root = null;
	}

	public void insert(T x) {
		root = merge(new LeftistNode<T>(numChildren, x), root);
	}

	/**
	 * Method inserting an element into the tree
	 * 
	 * @param element
	 * @return Boolean value: true if the operation was successful and false
	 *         otherwise
	 */
	public boolean enqueue(T element) {

		boolean result = false;

		if (isEmpty()) {
			LeftistNode<T> node = new LeftistNode<T>(numChildren, element);
			root = merge(node, root);
			result = true;

		} else {
			LeftistNode<T> node = new LeftistNode<T>(numChildren, element);
			root = merge(node, root);
			result = true;
		}
		return result;
	}

	/**
	 * Method to remove and return the smallest element from the heap. If no
	 * such element exists, return null.
	 * 
	 * @return Smallest item in the tree
	 */
	public T dequeue() {

		if (this.isEmpty()) {
			System.out.println("The heap is empty");
			return null;
		} else {
			T minItem = root.element;

			if (root.isEmpty()) {
				root = null;
			} else {
				root = merge(root.numNodes[0],
						root.numNodes[root.getLastIndex()]);
			}
			return minItem;
		}
	}

	// -------------------------------------------------------------------------
	// MERGE

	public void merge(LeftistBinaryTree<T> rhs) {
		if (this == rhs) // Avoid merging oneself
			return;

		root = merge(root, rhs.root);
		rhs.root = null;
	}

	private LeftistNode<T> merge(LeftistNode<T> node1, LeftistNode<T> node2) {
		if (node1 == null)
			return node2;
		if (node2 == null)
			return node1;
		if ((node1.element).compareTo(node2.element) < 0)
			return merge1(node1, node2);
		else
			return merge1(node2, node1);
	}

	private LeftistNode<T> merge1(LeftistNode<T> node1, LeftistNode<T> node2) {

		if (node1.numNodes[0] == null) {
			node1.numNodes[0] = node2;
		} else {

			node1.numNodes[node1.getLastIndex()] = merge(
					node1.numNodes[node1.getLastIndex()], node2);

			if (node1.numNodes[0].npl < node1.numNodes[node1.getLastIndex()].npl) {
				swapChildren(node1);
			}

			node1.npl = node1.numNodes[node1.getLastIndex()].npl + 1;
		}

		return node1;
	}

	private void swapChildren(LeftistNode<T> n) {
		LeftistNode<T> temp = n.numNodes[0];
		n.numNodes[0] = n.numNodes[n.getLastIndex()];
		n.numNodes[n.getLastIndex()] = temp;
	}

	// -------------------------------------------------------------------------
	// TRAVERSALS

	/**
	 * Method returning a string containing the elements in this tree in breadth
	 * first order.
	 * 
	 * @return String value containing the breadth first traversal of the tree
	 */
	public String breadthFirstSearch() {

		Queue<T> queue = new Queue<T>();
		String result = "";

		if (isEmpty()) {
			result = result.concat("[");
			result = result.concat("]");
			return result;
		}

		queue.enqueue(root);
		result = result.concat("[");
		while (!queue.isEmpty()) {
			LeftistNode<T> traverse = queue.dequeue();
			result = result.concat(traverse.element + ",");

			if (traverse.numNodes[0] != null)
				queue.enqueue(traverse.numNodes[0]);
			if (traverse.numNodes[root.getLastIndex()] != null)
				queue.enqueue(traverse.numNodes[root.getLastIndex()]);
		}

		result = result.substring(0, result.length() - 1);
		result = result.concat("]");

		return result;
	}

	/**
	 * Method returning a string representation of the elements (between square
	 * brackets and comma separated) in the tree in pre-order.
	 * 
	 * @return String value containing the depth first traversal of the tree
	 */
	public String depthFirstPreOrder() {
		String result = "";

		if (isEmpty()) {
			result = result.concat("[");
			result = result.concat("]");
			return result;
		}

		System.out.print("[");
		printPreOrderRec(root);
		result = result.concat("");
		System.out.print("]");
		return result;
	}

	// -------------------------- Helper function

	private void printPreOrderRec(LeftistNode<T> currRoot) {

		LeftistNode<T> stop = findRightMostNode(root);

		if (currRoot == null) {
			return;
		}
		if (stop == currRoot)
			System.out.print(currRoot.element);
		else {
			System.out.print(currRoot.element + ",");

			printPreOrderRec(currRoot.numNodes[0]);
			printPreOrderRec(currRoot.numNodes[currRoot.getLastIndex()]);
		}
	}

	/**
	 * Method returning a string representation of the elements (between square
	 * brackets and comma separated) in the tree in post-order.
	 * 
	 * @return String value containing the post-order traversal of the tree
	 */
	public String depthFirstPostOrder() {
		String result = "";

		if (isEmpty()) {
			result = result.concat("[");
			result = result.concat("]");
			return result;
		}

		System.out.print("[");
		printPostOrderRec(root);
		System.out.print("");
		System.out.println("]");

		return result;
	}

	// -------------------------- Helper function

	private void printPostOrderRec(LeftistNode<T> currRoot) {

		if (currRoot == null) {
			return;
		}

		printPostOrderRec(currRoot.numNodes[0]);
		printPostOrderRec(currRoot.numNodes[root.getLastIndex()]);

		if (currRoot == root)
			System.out.print(currRoot.element);
		else
			System.out.print(currRoot.element + ",");

	}

	// -------------------------------------------------------------------------
	// ADDITIONAL FUNCTIONALITIES

	public T findMin() {
		if (isEmpty())
			return null;
		return root.element;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void makeEmpty() {
		root = null;
	}

	// -------------------- Print:

	public void printTree() {
		printTree(root, 0);
	}

	private void printTree(LeftistNode<T> p, int depth) {
		if (p != null) {

			if (!(p.numNodes[0] == null && p.numNodes[p.getLastIndex()] == null)) {
				printTree(p.numNodes[p.getLastIndex()], depth + 1);
			}
			for (int j = 1; j <= depth; j++) {
				System.out.print("   ");
			}
			if (p.numNodes[0] == null && p.numNodes[p.getLastIndex()] == null) {
				System.out.println(p.element + " ");
			} else {
				System.out.println(p.element);
			}
			printTree(p.numNodes[0], depth + 1);
		}
	}

	// -------------------- Get right-most node:

	private LeftistNode<T> findRightMostNode(LeftistNode<T> root) {

		for (; root != null; root = root.numNodes[root.getLastIndex()]) {
			if (root.numNodes[root.getLastIndex()] == null
					&& root.numNodes[0] == null) // return
				// rightmost
				// node
				break;
			else if (root.numNodes[root.getLastIndex()] == null
					&& root.numNodes[0] != null) {
				// or the left node if one exists
				root = root.numNodes[0];
				break;
			}
		}
		return root;
	}
}
