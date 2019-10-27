// Carla de Beer
// Singly-linked linked list
// Date created: 06/06/2018

public class SLinkedList<T extends Comparable<T>> {

	private class Node<S> {
		private T info = null;
		private Node<S> next = null;
	}

	private Node<T> head;
	private int count;

	public SLinkedList() {
		this.head = null;
		this.count = 0;
	}

	public boolean isEmpty() {
		return (head == null);
	}

	public int length() {
		return count;
	}

	public boolean addToHead(T info) {
		Node<T> temp = new Node<T>();
		temp.info = info;

		if (isEmpty()) {
			head = temp;
			head.next = null;
			count++;
			return true;
		} else {
			temp.next = head;
			head = temp;
			count++;
			return true;
		}
	}

	public Node<T> head() {
		return head;
	}

	public Node<T> tail() {
		return null;
	}

	public String printList() {
		String result = "< ";

		if (!isEmpty()) {
			Node<T> current = head;
			while (current != null) {
				result = result.concat(current.info.toString());
				result = result.concat(" ");
				current = current.next;
			}
		}

		result = result.concat(">");
		return result;
	}

	public String printNodeInfo(Node<T> node) {
		return node.info.toString();
	}

	public boolean search(T info) {
		if (!isEmpty()) {
			Node<T> current = head;
			while (current != null) {
				if (current.info == info) {
					return true;
				} else {
					current = current.next;
				}
			}
		}
		return false;
	}

	public boolean deleteNode(T info) {
		if (isEmpty()) {
			return false;
		}

		// If head node contains the data to be removed
		if (info.compareTo(head.info) == 0) {
			head = head.next;
			count--;
			return true;
		}

		Node<T> current = head;
		Node<T> previous = null;

		// Else continue to traverse the list
		while (current != null && info.compareTo(current.info) != 0) {
			previous = current;
			current = current.next;
		}

		if (current == null) {
			return false;
		}

		previous.next = current.next;
		count--;
		return true;
	}

	public void destroyList() {
		if (!isEmpty()) {
			Node<T> current = head;
			Node<T> next = null;
			while (current != null) {
				next = current.next;
				current = current.next;
				head = next;
				count--;
			}
			head = null;
		}
	}

	public void reverseList() {
		if (!isEmpty() && count > 1) {
			Node<T> current = head;
			Node<T> previous = null;
			Node<T> next = null;
			while (current != null) {
				next = current.next;
				current.next = previous;
				previous = current;
				current = next;
			}

			head = previous;
		}
	}

	public void copyList(T other) {

	}
}
