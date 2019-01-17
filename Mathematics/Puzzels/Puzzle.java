import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Puzzle {

	public static boolean isArmstrong(int n) {
		int cubeSum = 0;
		int mod = 0;
		int incoming = n;

		while (n > 0) {
			mod = n % 10;
			cubeSum += mod * mod * mod;
			n /= 10;
		}

		if (cubeSum == incoming) {
			return true;
		} else
			return false;
	}

	public static boolean isPalindrome(int n) {
		int temp = n;
		int sum = 0;
		int mod = 0;

		while (n > 0) {
			mod = n % 10;
			sum = (sum * 10) + mod;
			n = n / 10;
		}

		if (sum == temp) {
			return true;
		}
		return false;
	}

	public static int fibonacciIterative(int n) {
		int prev = 1;
		int prevPrev = 1;
		int fib = 1;

		if (n == 1) {
			return 1;
		} else if (n == 2) {
			return 1;
		} else {
			for (int i = 0; i < n - 2; ++i) {
				fib = prev + prevPrev;
				prevPrev = prev;
				prev = fib;
			}
		}
		return fib;
	}

	public static int fibonacciRecursive(int n) {
		int fib = 0;
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			fib = fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
		}
		return fib;
	}

	public static int gcd(int a, int b) {
		if (a == 0) {
			return b;
		}
		return gcd(b % a, a);
	}

	public static ArrayList<Integer> sieveOfEratosthenes(int n) {
		ArrayList<Integer> primeNumbers = new ArrayList<Integer>();
		boolean[] primes = new boolean[n + 1];
		Arrays.fill(primes, true);

		for (int i = 2; i * i <= n; ++i) {
			if (primes[i]) {
				for (int j = i * 2; j <= n; j += i) {
					primes[j] = false;
				}
			}
		}

		for (int i = 2; i <= n; ++i) {
			if (primes[i]) {
				primeNumbers.add(i);
			}
		}

		return primeNumbers;
	}

	public static ArrayList<Integer> getPrimeFactors(long n) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 2; i <= n; ++i) {
			while (n % i == 0 && n > 0) {
				list.add(i);
				n = n / i;
			}
		}
		return list;
	}

	public static HashMap<Integer, Integer> calculateFrequency(int[] array) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int len = array.length;
		int count = 0;

		for (int i = 0; i < len; ++i) {
			for (int j = i + 1; j < len; ++j) {
				if (array[i] == array[j] && !map.containsKey(array[i])) {
					++count;
				}
			}
			if (!map.containsKey(array[i])) {
				map.put(array[i], count);
			}
			count = 0;
		}

		return map;
	}

	public static void leftRotateArray(int[] array, int n) {
		int first = 0;
		int len = array.length;

		for (int i = 0; i < n; ++i) {
			first = array[0];
			for (int j = 0; j < len - 1; ++j) {
				array[j] = array[j + 1];
			}
			array[len - 1] = first;
		}
	}

	public static void arrayReverse(int[] array) {
		int len = array.length;
		int temp = 0;
		for (int i = 0; i < len / 2; ++i) {
			temp = array[i];
			array[i] = array[len - 1 - i];
			array[len - 1 - i] = temp;
		}

	}

	public static void staircase(int n) {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (j < n - (i + 1)) {
					System.out.print(" ");
				} else
					System.out.print("#");
			}
			System.out.println();
		}
	}

}
