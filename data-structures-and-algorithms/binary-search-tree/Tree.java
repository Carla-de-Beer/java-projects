// Carla de Beer
// Date created: 25/02/2014

public class Tree<T extends Comparable<? super T>> {

	public class TreeNode<T extends Comparable<? super T>> {
		public T info;
		public TreeNode<T> left, right;

		public TreeNode(T info) {
			this.info = info;
			this.left = this.right = null;
		}
	}

	protected TreeNode<T> root;

	public Tree() {
		root = null;
	}

	public void printTree() {
		printTree(root, 0);
	}

	public void printTree(TreeNode<T> p, int depth) {
		if (p != null) {
			if (!(p.left == null && p.right == null))
				printTree(p.right, depth + 1);
			for (int i = 1; i <= depth; i++)
				System.out.print("   ");
			if (p.left == null && p.right == null)
				System.out.println(p.info + " ");
			else
				System.out.println(p.info);
			printTree(p.left, depth + 1);
		}
	}

	void output(TreeNode<T> node, String s) {
		if (node != null) {
			System.out.println(s + " with node " + node.info);
		} else {
			System.out.println(s + " received null node");
		}
	}

	/**
	 * Inserts the given element into the tree. The element is inserted at the
	 * first available (null) node.
	 */
	public void insert(T info) {
		root = insert(root, info);
	}

	/**
	 * This method is called by public void insert(T), above. The method is
	 * recursive.
	 */
	protected TreeNode<T> insert(TreeNode<T> node, T info) {

		output(node, "insert");

		if (node == null)
			return new TreeNode<T>(info);
		else {
			if (info.compareTo(node.info) < 0)// (info < node.info)
				node.left = insert(node.left, info);
			else
				node.right = insert(node.right, info);
			return node;
		}
	}

	boolean search(TreeNode<T> node, T info) {
		if (node == null)
			return false;
		if (info.equals(node.info)) {
			return true;
		}
		if (info.compareTo(node.info) < 0)
			return search(node.left, info);
		else
			return search(node.right, info);
	}

	/**
	 * Method that deletes the given element from the tree, if it is found.
	 * Follows the "Delete by Copying" method for nodes with 2 children.
	 */
	public void delete(T info) {
		root = delete(root, info);
	}

	/**
	 * This method is called by public void delete(T), above. The method is
	 * recursive and uses the "Delete by Copying" method for nodes with 2
	 * children.
	 */
	protected TreeNode<T> delete(TreeNode<T> node, T info) {

		output(node, "delete");

		if (node == null)
			return null;
		else if (info == node.info) {
			if (node.left == null)
				return node.right;
			else if (node.right == null)
				return node.left;
			else {
				node.left = change(node.left, node);
				return node;
			}
		} else {
			if (info.compareTo(node.info) < 0)
				node.left = delete(node.left, info);
			else
				node.right = delete(node.right, info);
			return node;
		}
	}

	public TreeNode<T> change(TreeNode<T> node, TreeNode<T> toRemove) {
		if (node.right == null) {
			toRemove.info = node.info;
			return node.left;
		} else {
			node.right = change(node.right, toRemove);
			return node;
		}
	}

	public int countLeaves() {
		return countLeaves(root);
	}

	protected int countLeaves(TreeNode<T> node) {
		if (node == null)
			return 0;
		if (node.left == null && node.right == null)
			return 1;
		else
			return countLeaves(node.left) + countLeaves(node.right);
	}

	public int nodeCount() {
		return nodeCount(root);
	}

	private int nodeCount(TreeNode<T> node) {
		if (node == null)
			return 0;
		return 1 + nodeCount(node.left) + nodeCount(node.right);
	}

}