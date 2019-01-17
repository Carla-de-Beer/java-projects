import java.util.ArrayList;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) {

		int n = 153;

		System.out.println("--------------------------------------------");
		System.out.println("Armstrong numbers:");
		System.out.println("--------------------------------------------");
		System.out.print("Is " + n + " an Armstrong number? ");
		System.out.println(Puzzle.isArmstrong(n));

		n = 99;
		System.out.print("Is " + n + " an Armstrong number? ");
		System.out.println(Puzzle.isArmstrong(n));

		n = 371;
		System.out.print("Is " + n + " an Armstrong number? ");
		System.out.println(Puzzle.isArmstrong(n));

		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("Palindromic numbers:");
		System.out.println("--------------------------------------------");
		n = 1551;
		System.out.print("Is " + n + " an palindromic number? ");
		System.out.println(Puzzle.isPalindrome(n));

		n = 1553;
		System.out.print("Is " + n + " an palindromic number? ");
		System.out.println(Puzzle.isPalindrome(n));

		n = 1;
		System.out.print("Is " + n + " an palindromic number? ");
		System.out.println(Puzzle.isPalindrome(n));

		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("Fibonacci numbers (iterative):");
		System.out.println("--------------------------------------------");
		n = 8;
		System.out.print("Fibonacci number " + n + ": ");
		System.out.println(Puzzle.fibonacciIterative(n));

		System.out.println("Fibonacci numbers (recursive):");
		n = 8;
		System.out.print("Fibonacci number " + n + ": ");
		System.out.println(Puzzle.fibonacciRecursive(n));

		System.out.println("Fibonacci numbers (recursive):");
		n = 40;
		System.out.print("Fibonacci number " + n + ": ");
		System.out.println(Puzzle.fibonacciRecursive(n));

		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("Sieve of Eratosthenes:");
		System.out.println("--------------------------------------------");
		n = 55;
		System.out.println("Find the prime numbers <= " + n + ": ");
		ArrayList<Integer> primes = Puzzle.sieveOfEratosthenes(n);
		printArrayList(primes);

		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("Prime factors:");
		System.out.println("--------------------------------------------");
		n = 533;
		System.out.println("Find the prime factors of " + n + ":");
		ArrayList<Integer> primeFactors = Puzzle.getPrimeFactors(n);
		printArrayList(primeFactors);

		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("Count occurrences in an array:");
		System.out.println("--------------------------------------------");
		int[] array3 = { 1, 2, 8, 7, 3, 2, 2, 2, 5, 1 };
		HashMap<Integer, Integer> map = Puzzle.calculateFrequency(array3);
		printHashMap(map);

		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("Left rotate array:");
		System.out.println("--------------------------------------------");
		int[] array4 = { 1, 2, 3, 4, 5, 6 };
		printArray(array4);
		Puzzle.leftRotateArray(array4, 2);
		printArray(array4);

		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("Calculate GCD:");
		System.out.println("--------------------------------------------");
		int a = 24;
		int b = 54;
		System.out.println("gcd(" + a + ", " + b + "): " + Puzzle.gcd(a, b));

		a = 48;
		b = 180;
		System.out.println("gcd(" + a + ", " + b + "): " + Puzzle.gcd(a, b));

		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("Reverse array in place:");
		System.out.println("--------------------------------------------");
		int[] array5 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		printArray(array5);
		Puzzle.arrayReverse(array5);
		printArray(array5);

		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("Staircase:");
		System.out.println("--------------------------------------------");
		n = 5;
		System.out.println("Staircase diagram for " + n + ": ");
		Puzzle.staircase(n);
	}

	private static void printArray(int[] array) {
		for (int i = 0; i < array.length; ++i) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	private static void printArrayList(ArrayList<Integer> list) {
		for (int i = 0; i < list.size(); ++i) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println();
	}

	private static void printHashMap(HashMap<Integer, Integer> map) {
		for (HashMap.Entry<Integer, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}

}
