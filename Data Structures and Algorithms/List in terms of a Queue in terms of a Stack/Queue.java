// Carla de Beer
// List class implemented in terms of a Queue implemented in terms of a Stack
// No native Java libraries were used in this project
// Date created: 15/02/2014

public class Queue<T> {

	private Stack<T> stack;

	/**
	 * Default constructor: instantiates stack object
	 */
	public Queue() {

		stack = new Stack<T>();
	}

	/**
	 * Copy constructor
	 * 
	 * @param other
	 */
	public Queue(final Queue<T> other) {
		stack = new Stack<T>();
		(this.stack).assign((other.stack)); // deep copy
	}

	/**
	 * Assignment method: simulates the effect of the "operator=" function in
	 * C++ (makes allowance for deep copying)
	 * 
	 * @param other
	 * @return this
	 */
	public Queue<T> assign(final Queue<T> other) {
		if (this != other) {
			while (!this.isEmpty()) {
				stack.pop();
			}

			(this.stack) = (other.stack).clone();
		}
		return this;
	}

	/**
	 * getCopy() method: simulates the effect of the "operator=" function in C++
	 * (makes allowance for deep copying)
	 * 
	 * @return temp
	 */
	public Queue<T> clone() {
		Queue<T> temp = new Queue<T>();
		temp.stack = stack;
		return temp;
	}

	/**
	 * Method to enqueue an element value to the front of the queue
	 * 
	 * @param el
	 */
	public void enqueue(final T el) {
		if (!isEmpty()) {
			Stack<T> temp = new Stack<T>();
			while (!(stack.isEmpty())) {

				temp.push(stack.pop());
			}

			stack.push(el);
			while (!(temp.isEmpty())) {
				stack.push(temp.pop());
			}
		} else {
			stack.push(el);
		}
	}

	public T dequeue() throws MyException {
		if (this.isEmpty()) {
			throw new MyException(
					"Error: Attempt to access element in an empty queue");
		}
		return stack.pop();
	}

	/**
	 * Method to allow for increasing the priority of an element value in the
	 * list
	 * 
	 * @param el
	 */
	public void increasePriority(final T el) throws MyException {

		if (!(stack.isEmpty()) && (stack.peek()) != el) {
			Stack<T> temp1 = new Stack<T>();
			Stack<T> temp2 = new Stack<T>();
			Stack<T> temp3 = new Stack<T>();
			Stack<T> temp4 = new Stack<T>();
			Stack<T> temp5 = new Stack<T>(stack);

			while (!(stack.isEmpty()) && stack.peek() != el) {
				temp1.push(stack.pop());
			}

			if (!(stack.isEmpty())) // if stack is empty then number isn't
									// inside list

			{
				temp2.push(temp1.pop());
				stack.pop(); // elminate el from stack

				while (!(stack.isEmpty())) {
					temp3.push(stack.pop());
				}

				while (!(temp3.isEmpty())) // reverse
				{
					temp4.push(temp3.pop());
				}

				temp4.push(temp2.pop());
				temp4.push(el);

				while (!(temp1.isEmpty())) // reverse
				{
					temp4.push(temp1.pop());
				}

				(this.stack).assign(temp4); // deep copy
			}

			else
				stack.assign(temp5); // inefficient but works
		}

	}

	/**
	 * Method to test whether a queue is empty
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		if (stack != null) {
			return (stack.isEmpty());
		} else
			return false;
	}

	@Override
	/**
	 * Object class toString() method overridden
	 */
	public String toString() {

		String result = stack.toString();
		return result;

	}

}

