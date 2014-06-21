// Carla de Beer
// Akckermann function and Knapsack algorithm
// Date created: 17/02/2014

public class Main {

	public static void main(String[] args) {

		System.out.println("Ackermann function:");

		Ackermann w = new Ackermann();
		int ack;

		ack = w.ackermann(1, 2);
		System.out.println(ack);

		ack = w.ackermann(2, 3);
		System.out.println(ack);

		ack = w.ackermann(3, 4);
		System.out.println(ack);

		// ack = w.ackermann(4, 5); // stackoverflow occurs here
		// System.out.println(ack);

		System.out.println();
		System.out.println("Knapsack algorithm: ");

		Knapsack k = new Knapsack();
		double answer;

		answer = k.start();
		System.out.println(answer);
	}
}
