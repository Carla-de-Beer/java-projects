//Carla de Beer
//Circular linked list and self-organising linked list
//Date created: 18/02/2014
//Date revised: 21/06/2014

public class CircularDLList<T> {
	protected class DLNode<T> {
		public T info = null;
		public DLNode<T> prev = null;
		public DLNode<T> next = null;
	}

	protected DLNode<T> tail = null;
	int size = 0;

	/**
	 * Returns a string describing the contents of the list.
	 */

	@Override
	public String toString() {
		String result = "< ";

		if (!isEmpty()) {
			DLNode<T> current = tail.next;
			while (current != tail) {
				result = result.concat(current.info.toString());
				result = result.concat(" ");
				current = current.next;
			}
			result = result.concat(current.info.toString());
			result = result.concat(" ");
		}

		result = result.concat(">");
		return result;
	}

	/**
	 * Inserts a new element at the head (beginning) of the list.
	 * 
	 * @param info
	 *            The element to be added to the list
	 */
	public void addToHead(T info) {
		DLNode<T> temp = new DLNode<T>();
		temp.info = info;

		if (isEmpty()) { // if is empty
			tail = temp;
			tail.next = tail;
			tail.prev = tail;
		}

		else { // more than one node
			DLNode<T> head = tail.next;
			tail.next = temp;
			temp.next = head;
			head.prev = temp;
			temp.prev = tail;
		}
	}

	/**
	 * Inserts a new element at the tail (end) of the list.
	 * 
	 * @param info
	 *            The element to add to the list
	 */
	public void addToTail(T info) {
		DLNode<T> temp = new DLNode<T>();
		temp.info = info;

		if (isEmpty()) { // if is empty
			tail = temp;
			tail.next = tail;
			tail.prev = tail;
		}

		else { // more than one node
			DLNode<T> head = tail.next;
			tail.next = temp;
			temp.prev = tail;
			head.prev = temp;
			temp.next = head;
			tail = temp;
		}
	}

	/**
	 * Removes and returns the first element from the list. Throws an
	 * EmptyListException if the list is empty.
	 * 
	 * @return The first element of the list (before removal)
	 */
	public T removeFromHead() throws EmptyListException {

		if (isEmpty()) { // if is empty
			throw new EmptyListException("Error: Empty list.");
		}

		DLNode<T> head = tail.next;
		T el = head.info;
		// If only one element in the list
		if (tail.prev == null && tail.next == null) {
			tail = null;
		} else { // more than one node

			DLNode<T> newHead = head.next;
			tail.next = newHead;
			newHead.prev = tail;
			head = newHead;
		}
		return el;
	}

	/**
	 * Removes and returns the last element from the list. Throws an
	 * EmptyListException if the list is empty.
	 * 
	 * @return The last element of the list (before removal)
	 */
	public T removeFromTail() {

		if (isEmpty()) { // if is empty
			throw new EmptyListException("Error: Empty list.");
		}
		T el = tail.info;
		// Only one element in the list
		if (tail.prev == null && tail.next == null) {
			tail = null;
		} else { // more than one node
			DLNode<T> head = tail.next;
			DLNode<T> penultimnate = tail.prev;
			penultimnate.next = head;
			head.prev = penultimnate;
			tail = penultimnate;
		}
		return el;
	}

	/**
	 * Returns the first element of the list. Throws an EmptyListException if
	 * the list is empty
	 * 
	 * @return The first element of the list
	 */
	public T head() {

		if (isEmpty()) { // if is empty
			throw new EmptyListException();
		}
		// only one lement in the list
		if (tail.prev == tail && tail.next == tail) 
													
			return tail.info;
		else { // more than one node
			DLNode<T> head = tail.next;
			return head.info;
		}

	}

	/**
	 * Returns the last element of the list Must throw an EmptyListException if
	 * the list is empty
	 * 
	 * @return The last element of the list
	 */
	public T tail() {

		if (isEmpty()) { // if is empty
			throw new EmptyListException("Error: Empty list.");
		}

		else
			return tail.info;

	}

	/**
	 * Finds and returns the given element. Throws an EmptyListException if
	 * the list is empty.
	 * 
	 * @param info
	 *            The value of the element to find
	 * @return if found: the found element if not: null
	 */
	public T find(T info) {

		if (isEmpty()) { // if is empty
			throw new EmptyListException("Error: Empty list.");
		} else { // more than one node
			boolean found = false;
			DLNode<T> current = tail;
			do {
				current = current.next;
				if (current.info == info)
					found = true;
			} while (current != tail && !found);

			if (found && current.info == info) {
				return current.info;
			} else
				return null;
		}

	}

	/**
	 * Returns whether the list has elements or not
	 * 
	 * @return True if the list has n0 elements; false otherwise.
	 */
	public boolean isEmpty() {
		return (tail == null);
	}

	/**
	 * Returns the size of the list
	 * 
	 * @return The number of elements in the list
	 */
	public int getSize() {
		if (tail == null)
			return size;
		else {
			DLNode<T> current = tail;
			do {
				current = current.next;
				size++;
			} while (current != tail);
			return size;
		}
	}

}

