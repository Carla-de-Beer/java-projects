// Carla de Beer
// List class implemented in terms of a Queue implemented in terms of a Stack
// No native Java libraries were used in this project
// Date created: 15/02/2014

public class Stack<T> {

	/**
	 * Class Node, as defined inside class Stack<T>
	 */
	private class Node {
		/**
		 * Constructor with parameters
		 * 
		 * @param data
		 * @param n
		 */
		public Node(final T data, Node n) {
			this.element = data;
			this.next = n;
			// n = null;
		}

		/**
		 * Constructor with parameter: allows for the default setting of the
		 * value for n
		 * 
		 * @param data
		 */
		public Node(final T data) {
			this(data, null);
		}

		T element;
		Node next;
	}

	private Node top;

	/**
	 * Default constructor: instantiates top
	 */
	public Stack() {
		top = null;
	}

	/**
	 * Copy constructor
	 * 
	 * @param other
	 */
	public Stack(final Stack<T> other) {
		top = null;
		// if stack is not empty, reset it first (delete all contents)
		if (!isEmpty()) {

			// continue deleting nodes while there are any remaining
			while (top != null) {
				// Node temp = top;//new Node(null);// null;
				// temp = top;
				top = top.next;
			}
		}

		if (other.top == null) {
			top = null;
		} else {
			// the situation where otherStack contains at least 1 element
			// start by copying the first node in otherStack
			Node source = other.top;
			top = new Node(source.element, null);
			Node destination = top;
			source = source.next;

			// copy the remaining nodes
			while (source != null) {
				Node newNode = new Node(source.element, null);
				destination.next = newNode;
				destination = newNode;
				source = source.next;
			}
		}
	}

	/**
	 * Assignment method: simulates the effect of the "operator=" function in
	 * C++ (makes allowance for deep copying)
	 * 
	 * @param other
	 * @return this
	 */
	public Stack<T> assign(final Stack<T> other) {
		if (this != other) {
			// if stack is not empty, reset it first (delete all contents)
			if (!isEmpty()) {
				// Node temp = new Node(null);// null;

				// continue deleting nodes while there are any remaining
				while (top != null) {
					// temp = top;
					top = top.next;
				}
			}

			if (other.top == null) {
				// the situation where otherStack is empty
				top = null;
			} else {
				// the situation where otherStack contains at least 1 element
				// start by copying the first node in otherStack
				Node source = other.top;
				top = new Node(source.element, null);

				Node destination = top;
				source = source.next;

				// copy the remaining nodes
				while (source != null) {
					Node newNode = new Node(source.element, null);
					destination.next = newNode;
					destination = newNode;
					source = source.next;
				}
			}

		}
		return this;
	}

	/**
	 * getCopy() method: simulates the effect of the "operator=" function in C++
	 * (makes allowance for deep copying)
	 * 
	 * @return temp
	 */
	public Stack<T> clone() {
		Stack<T> temp = new Stack<T>();
		temp.top = top;
		return temp;
	}

	/**
	 * Method to allow for the pushing of an element value into the stack
	 * 
	 * @param el
	 */
	public void push(final T el) {
		Node newNode = new Node(el, top);
		top = newNode;
	}

	/**
	 * Method to allow for the popping of the top element value from the stack
	 * 
	 * @return
	 * @throws MyException
	 */
	public T pop() throws MyException {

		if (isEmpty()) {
			throw new MyException(
					"Error: Attempt to access top in an empty stack.");
		} else {
			Stack<T> element = new Stack<T>();
			Node temp = top;
			element.push(temp.element);
			top = top.next;
			temp = null;
			return element.peek();

		}
	}

	/**
	 * Method to allow for peeking at the top element value in the stack
	 * 
	 * @return
	 * @throws MyException
	 */
	public T peek() throws MyException {

		if (isEmpty()) {
			throw new MyException(
					"Error: Attempt to access top in an empty stack");
		}
		return top.element;

	}

	/**
	 * Method to test whether a stack is empty
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return (top == null);
	}

	@Override
	/**
	 * Object class toString() method overridden
	 */
	public String toString() {

		String result = "[";

		if (!isEmpty()) {
			Node temp = top;
			result = result.concat(temp.element.toString());
			temp = temp.next;

			while (temp != null) {
				result = result.concat(",");
				result = result.concat(temp.element.toString());
				temp = temp.next;
			}
		}

		result = result.concat("]");
		return result;

	}
}

