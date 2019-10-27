//Carla de Beer
//Circular linked list and self-organising linked list
//Date created: 18/02/2014
//Date revised: 21/06/2014

public class SOCircularLList<T> extends CircularLList<T> {

	/**
	 * Searches for and returns the specified element. Re-organises the list
	 * according to the "transpose" strategy. Throws a ListEmptyException when
	 * appropriate.
	 *
	 * @param The
	 *            value of the element to find
	 * @return The found element
	 */
	@Override
	public T find(T info) {

		if (isEmpty()) {
			throw new EmptyListException("Error: Empty list.");
		}

		else {
			Node<T> head = tail.next;
			Node<T> current = tail;

			boolean found = false;

			do {
				current = current.next;
				if (current.info == info)
					found = true;
			}

			while (current != tail && !found);
			Node<T> currentPrev = current.prev;
			Node<T> currentNext = current.next;

			// Add stop for first node
			if (found && current.info == info) {
				// swap
				if (info != tail.next.info) {
					currentPrev.prev.next = current;
					current.prev = currentPrev.prev;

					current.next = currentPrev;
					currentPrev.prev = current;

					currentPrev.next = currentNext;
					currentNext.prev = currentPrev;
				}

				if (info == tail.info) {
					currentPrev.prev.next = current;
					current.prev = currentPrev.prev;

					current.next = currentPrev;
					currentPrev.prev = current;

					tail = currentPrev;

					currentPrev.next = head;
					head.prev = currentPrev;
				}

				return current.info;

			} else if (!found) {
				addToHead(info);
				return current.info;
			}
			return null;
		}
	}
}
