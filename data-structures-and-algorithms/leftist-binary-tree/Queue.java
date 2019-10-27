// Carla de Beer
// Leftist Binary Tree 
// (based on code by Mark Allen Weiss in "Data Structures and Algorithm Analysis in C++")
// Date created: 22/03/2014

public class Queue<T> {
	class Node {

		LeftistNode<T> element;
		Node next;

		public Node(LeftistNode<T> in) {
			element = in;
			next = null;
		}
	}

	Node first;

	public Queue() {
		first = null;
	}

	public Queue(LeftistNode<T> node) {
		first = new Node(node);
	}

	public boolean isEmpty() {
		return first == null;
	}

	public void enqueue(LeftistNode<T> node) {
		if (this.isEmpty()) {
			first = new Node(node);
		} else {
			Node temp = first;

			while (temp.next != null) {
				temp = temp.next;
			}

			temp.next = new Node(node);
		}
	}

	public LeftistNode<T> dequeue() {
		if (this.isEmpty()) {
			return null;
		} else {
			Node temp = first;
			first = first.next;
			return temp.element;
		}
	}
}

