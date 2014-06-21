// Carla de Beer
// List class implemented in terms of a Queue implemented in terms of a Stack
// No native Java libraries were used in this project
// Date created: 15/02/2014

public class List<T> {

	private Queue<T> queue;

	/**
	 * Default constructor: instantiates the queue object
	 */
	public List() {
		queue = new Queue<T>();
	}

	/**
	 * Copy constructor
	 * 
	 * @param other
	 */
	public List(final List<T> other) {
		queue = new Queue<T>();
		(this.queue).assign((other.queue)); // deep copy
	}

	/**
	 * Assignment method: simulates the effect of the "operator=" function in
	 * C++ (makes allowance for deep copying)
	 * 
	 * @param other
	 * @return this
	 */
	public List<T> assign(final List<T> other) {
		if (this != other) {
			while (!this.isEmpty()) {
				queue.dequeue();
			}

			(this.queue) = (other.queue).clone();
		}
		return this;
	}

	/**
	 * getCopy() method: simulates the effect of the "operator=" function in C++
	 * (makes allowance for deep copying)
	 * 
	 * @return temp
	 */
	public List<T> clone() {
		List<T> temp = new List<T>();
		temp.queue = queue;
		return temp;
	}

	/**
	 * Method to add element to the front of the list
	 * 
	 * @param el
	 * @throws MyException
	 */
	public void addToFront(final T el) throws MyException {

		if (!isEmpty()) {
			Queue<T> temp = new Queue<T>();
			temp.enqueue(el);
			while (!(queue.isEmpty())) {
				temp.enqueue(queue.dequeue());
			}
			queue.assign(temp); // deep copy
		} else
			queue.enqueue(el);

	}

	/**
	 * Method to add element to the back of the list
	 * 
	 * @param el
	 */
	public void addToBack(final T el) {
		queue.enqueue(el);
	}

	/**
	 * Method to add element at the given index of the list
	 * 
	 * @param index
	 * @param el
	 */
	public void insertAtIndex(final int index, final T el) {
		if (!isEmpty()) {
			// Check for index out of bounds
			int count = 0;
			Queue<T> temp1 = new Queue<T>(queue);

			while (!(temp1.isEmpty())) {
				count++;
				temp1.dequeue();
			}

			if ((count - 1) < index || index < 0 || index != (int) (index)) {
				throw new MyException("Error: Index out of bounds or invalid");
			}

			Queue<T> temp2 = new Queue<T>();
			Queue<T> temp3 = new Queue<T>();
			int i = 0;

			while (!(queue.isEmpty())) {
				temp2.enqueue(queue.dequeue());
			}

			while (i < index) {
				temp3.enqueue(temp2.dequeue());
				++i;
			}

			temp3.enqueue(el);

			while (!temp2.isEmpty()) {
				temp3.enqueue(temp2.dequeue());
			}
			queue.assign(temp3); // deep copy
		}

		else if (queue.isEmpty() && index == 0) {
			queue.enqueue(el);
		} else if (queue.isEmpty() && index != 0) {
			throw new MyException(
					"Error: Attempt to access element in an empty list");
		}

	}

	/**
	 * Method to delete an element at a given index in the list
	 * 
	 * @param index
	 * @return element value of the item deleted
	 * @throws MyException
	 */
	public T deleteAtIndex(final int index) throws MyException {

		if (isEmpty()) {
			throw new MyException(
					"Error: Attempt to access element in an empty list");
		} else {
			// Check for index out of bounds
			int count = 0;
			Queue<T> temp1 = new Queue<T>(queue); // deep copy

			while (!(temp1.isEmpty())) {
				count++;
				temp1.dequeue();
			}

			if ((count - 1) < index || index < 0 || index != (int) (index)) {
				throw new MyException("Error: Index out of bounds or invalid");
			}

			// deleteAtIndex
			Queue<T> temp2 = new Queue<T>();
			Queue<T> temp3 = new Queue<T>();
			int i = 0;

			while (!(queue.isEmpty())) {
				temp2.enqueue(queue.dequeue());
			}

			while (i < index) {
				temp3.enqueue(temp2.dequeue());
				++i;
			}

			T answer = temp2.dequeue();

			while (!temp2.isEmpty()) {
				temp3.enqueue(temp2.dequeue());
			}

			queue.assign(temp3); // deep copy
			return answer;
		}
	}

	/**
	 * Method to get element at a given index in the list
	 * 
	 * @param index
	 * @return element value of the item deleted
	 * @throws MyException
	 */
	public T get(final int index) throws MyException {
		try {
			if (isEmpty()) {
				throw new MyException(
						"Error: Attempt to access element in an empty list");
			}

			else {
				// Check for index out of bounds
				int count = 0;
				Queue<T> temp1 = new Queue<T>(queue); // deep copy
				// temp1 = queue;

				while (!(temp1.isEmpty())) {
					count++;
					temp1.dequeue();
				}

				if ((count - 1) < index || index < 0 || index != (int) (index)) {
					throw new MyException(
							"Error: Index out of bounds or invalid");
				}

				// Get value
				Queue<T> temp2 = new Queue<T>(queue); // deep copy
				// temp2 = queue;
				int i = 0;

				while (i < (index)) {
					temp2.dequeue();
					++i;
				}

				return temp2.dequeue();
			}
		} catch (MyException e) {
			throw e;
		}
	}

	/**
	 * Method to set element at a given index in the list
	 * 
	 * @param index
	 * @param el
	 * @throws MyException
	 */
	public void set(final int index, final T el) throws MyException {
		if (!isEmpty()) {
			// Check for index out of bounds
			int count = 0;

			Queue<T> temp1 = new Queue<T>(queue); // deep copy
			// temp1 = queue;

			while (!(temp1.isEmpty())) {
				count++;
				temp1.dequeue();
			}

			if ((count - 1) < index || index < 0 || index != (int) (index)) {
				throw new MyException("Error: Index out of bounds or invalid");
			}

			// Swap value
			int i = 0;
			Queue<T> temp2 = new Queue<T>();

			while (i < index) {
				temp2.enqueue(queue.dequeue());
				++i;
			}
			temp2.enqueue(el);
			queue.dequeue();

			while (!(queue.isEmpty())) {
				temp2.enqueue(queue.dequeue());
			}

			queue.assign(temp2); // deep copy
		}

		if (isEmpty() && index == 0) {
			queue.enqueue(el);
		}

		else if (isEmpty() && index != 0) {
			throw new MyException(
					"Error: Attempt to access element in an empty list");
		}
	}

	/**
	 * Method to test whether a list is empty
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		if (queue != null) {
			return (queue.isEmpty());
		} else
			return false;
	}

	@Override
	/**
	 * Object class toString() method overridden
	 */
	public String toString() {

		String result = queue.toString();
		return result;

	}

}

