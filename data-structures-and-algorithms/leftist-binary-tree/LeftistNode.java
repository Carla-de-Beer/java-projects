// Carla de Beer
// Leftist Binary Tree 
// (based on code by Mark Allen Weiss in "Data Structures and Algorithm Analysis in C++")
// Date created: 22/03/2014

public class LeftistNode<T> {

	protected T element;
	int npl;
	int dim;
	protected LeftistNode<T>[] numNodes;

	/**
	 * Parameterised constructor
	 * 
	 * @param d
	 * @param _data
	 */
	public LeftistNode(int d, T _data) {
		npl = 0;
		element = _data;
		dim = d;
		numNodes = (LeftistNode<T>[]) new LeftistNode[dim];
		for (int i = 0; i < dim; ++i) {
			numNodes[i] = null;
		}
	}

	protected int getLastIndex() {
		return dim - 1;
	}

	/**
	 * Method to check whether a tree is empty or not
	 * 
	 * @return Boolean value
	 */
	public boolean isEmpty() {
		boolean isEmpty = true;

		for (int i = 0; i < dim && isEmpty; i++) {
			if (numNodes[i] != null) {
				isEmpty = false;
			}
		}
		return isEmpty;
	}

	public String toString() {
		String returnString = "" + element;
		return returnString;
	}
}
