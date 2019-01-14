// Carla de Beer
// List class implemented in terms of a Queue implemented in terms of a Stack
// No native Java libraries were used in this project
// Date created: 15/02/2014

public class Main {

	public static void main(String[] args) {

		System.out.println("STACK (1)");
		System.out.println();

		Stack<Integer> stack1 = new Stack<Integer>();
		Stack<Integer> stack2 = new Stack<Integer>();
		Stack<Integer> stack3 = new Stack<Integer>();
		Stack<Integer> stack4 = new Stack<Integer>();
		Stack<Integer> emptyStack = new Stack<Integer>();

		stack1.push(5);
		stack1.push(3);
		stack1.push(7);
		stack1.push(1);

		stack4.push(35);
		stack4.push(34);
		stack4.push(7);
		stack4.push(33);

		System.out.println("Stack1 = " + stack1);
		System.out.println("Stack2 = " + stack2);
		System.out.println("Stack3 = " + stack3);

		if (stack1.isEmpty())
			System.out.println("Stack1 is empty.");
		else
			System.out.println("Stack1 is not empty.");

		if (stack3.isEmpty())
			System.out.println("Stack3 is empty.");
		else
			System.out.println("Stack3 is not empty.");

		System.out.println("Try to pop an empty stack:");
		try {
			stack3.pop();
			emptyStack.pop();

		} catch (MyException e) {
			System.out.println(e.getMessage());
		}
		stack2.push(1);
		stack2.push(3);
		stack2.push(7);
		stack2.push(4);

		System.out.println("Stack1 = " + stack1);
		System.out.println("Stack4 = " + stack4);
		System.out.println("Stack3 = " + stack3);
		stack2.assign(stack1);
		stack2.assign(stack4);
		stack2.assign(stack1);
		System.out.println("Stack2 = " + stack2);
		System.out.println("Peeking into Stack4: " + stack4.peek());
		try {
			System.out.println("Peeking into Stack4: " + stack3.peek());
		} catch (MyException e) {
			System.out.println(e.getMessage());
		}

		stack2.pop();
		stack2.pop();
		stack2.pop();
		try {
			stack2.pop();
		} catch (MyException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Stack2 popped = " + stack2);

		System.out.println("Stack4 = " + stack4);
		Stack<Integer> stack6 = stack4.clone();
		System.out.println("stack6 = " + stack6);
		System.out.println();
		System.out.println();

		System.out.println("------------------------------------------------");
		System.out.println();
		System.out.println("QUEUE (2)");
		System.out.println();

		Queue<Integer> queue1 = new Queue<Integer>();
		Queue<Integer> queue2 = new Queue<Integer>();

		queue1.enqueue(4);
		queue1.enqueue(3);
		queue1.enqueue(1);
		queue1.enqueue(2);

		System.out.println("Queue1 = " + queue1);

		queue1.increasePriority(1);
		System.out.println("Queue1 increase priority (1) = " + queue1);
		queue1.increasePriority(4);
		System.out.println("Queue1 increase priority (4) = " + queue1);
		queue1.increasePriority(2);
		System.out.println("Queue1 increase priority (2) = " + queue1);
		queue1.increasePriority(2);
		System.out.println("Queue1 increase priority (2) = " + queue1);
		queue1.increasePriority(2);
		System.out.println("Queue1 increase priority (2) = " + queue1);
		queue1.increasePriority(17);
		System.out.println("Queue1 increase priority (17) = " + queue1);
		queue1.increasePriority(4);
		System.out.println("Queue1 increase priority (4) = " + queue1);

		System.out.println("Attempt to dequeue empty queue2:");

		try {
			queue2.dequeue();

		} catch (MyException e) {
			System.out.println(e.getMessage());
		}

		Queue<Integer> queue3 = new Queue<Integer>(queue1);
		System.out.println("Queue2 = " + queue2);
		System.out.println("Queue3 = " + queue3);

		if (queue1.isEmpty())
			System.out.println("Queue1 is empty.");
		else
			System.out.println("Queue1 is not empty.");

		if (queue2.isEmpty())
			System.out.println("Queue2 is empty.");
		else
			System.out.println("Queue2 is not empty.");
		System.out.println("Queue1 = " + queue1);

		queue3.assign(queue2);
		System.out.println("Queue3 = Queue2 = " + queue3);
		System.out.println("ASSIGNING");
		queue3.assign(queue1);
		System.out.println("Queue3 = Queue1 = " + queue3);

		System.out.println("Queue3 = " + queue3);
		Queue<Integer> queue6 = queue3.clone();
		System.out.println("Queue6 = " + queue6);
		System.out.println();
		System.out.println();

		System.out.println("------------------------------------------------");
		System.out.println();

		System.out.println("LIST (3)");
		System.out.println();

		List<Integer> list1 = new List<Integer>();
		List<Integer> list2 = new List<Integer>();
		List<Integer> list4 = new List<Integer>();
		List<Integer> list5 = new List<Integer>();

		list1.addToFront(5);
		list1.addToFront(6);
		list1.addToFront(1);
		list1.addToFront(7);
		list1.addToBack(9);

		System.out.println("List1 = " + list1);
		list2.assign(list5);
		list2.assign(list1);
		System.out.println("List2 = " + list2);

		list2.addToBack(34);
		list2.addToFront(0);

		List<Integer> list3 = new List<Integer>(list2);
		System.out.println("List3 = " + list3);

		System.out.println("List3 element at index 3 = " + list3.get(3));
		System.out.println("List3 element at index 0 = " + list3.get(0));
		System.out.println("List3 element at index 5 = " + list3.get(5));
		System.out.println("Attempt to access element out of bounds: ");
		try {
			System.out.println("List3 element at index 3 = " + list3.get(33));
		} catch (MyException e) {
			System.out.println(e.getMessage());
		}

		if (list1.isEmpty())
			System.out.println("List1 is empty");
		else
			System.out.println("List1 is not empty.");
		if (list4.isEmpty())
			System.out.println("List4 is empty");
		else
			System.out.println("List4 is not empty.");

		System.out.println("List3 = " + list3);
		System.out.println("List3 insert element 11 at index 5: ");
		list3.insertAtIndex(5, 11);
		System.out.println("List3 = " + list3);
		System.out.println("List3 delete element at index 0: ");
		list3.deleteAtIndex(0);
		System.out.println("List3 = " + list3);

		try {
			list4.deleteAtIndex(4);
		} catch (MyException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("List4 set index 4 to 44: ");
		list3.set(4, 44);
		System.out.println("List3 = " + list3);

		try {
			list4.set(4, 6);
		} catch (MyException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("List2 = " + list2);
		List<Integer> list6 = list3.clone();
		System.out.println("List6 = " + list6);

	}
}

