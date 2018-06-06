//Carla de Beer
//Single Linked list
//Date created: 06/06/2018

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

	public Boolean isEmpty() {
		return (head == null);
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

	public int length() {
		return count;
	}

	public void destroyList() {
		Node<T> curr = head;
		Node<T> next = null;
		if (!isEmpty()) {
			while (curr != null) {
				next = curr.next;
				curr = next;
				head = next;
				count--;
			}
			head = null;
		}
	}

	public void addToHead(T info) {
		Node<T> temp = new Node<T>();
		temp.info = info;

		if (isEmpty()) {
			head = temp;
			head.next = null;
		} else {
			temp.next = head;
			head = temp;
		}
		count++;
	}

	public void reverseList() {
		Node<T> prev = null;
		Node<T> next = null;
		Node<T> curr = head;
		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}

		head = prev;
	}

	public Node<T> front() {
		return head;
	}

	public Node<T> back() {
		return null;
	}

	public Boolean search(T info) {
		Boolean found = false;
		if (!isEmpty()) {
			Node<T> curr = head;
			Node<T> next = null;
			while (curr != null) {
				if (curr.info == info) {
					return true;
				} else {
					next = curr.next;
					curr = next;
				}
			}
		}
		return found;
	}

	public void deleteNode(T info) {
	}

	public void copyList(T other) {

	}

}
