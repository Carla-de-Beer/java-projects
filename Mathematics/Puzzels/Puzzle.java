import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Puzzle {

	// O(n)
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

	// O(n)
	public static boolean isPalindrome(int n) {
		int temp = n;
		int sum = 0;
		int mod = 0;

		while (n > 0) {
			mod = n % 10;
			sum = (sum * 10) + mod;
			n /= 10;
		}

		if (sum == temp) {
			return true;
		}
		return false;
	}

	// O(n)
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

	// O(2^n). Since each leaf will take O(1) to compute, this is closer to O(1.6^n)
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

	// O(n)
	public static int fibonacciMemoization(int n, int[] memo) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else if (memo[n] > 0) {
			return memo[n];
		}

		memo[n] = fibonacciMemoization(n - 1, memo) + fibonacciMemoization(n - 2, memo);
		return memo[n];
	}

	// O(log(n))
	public static int gcd(int a, int b) {
		if (a == 0) {
			return b;
		}
		return gcd(b % a, a);
	}

	// O(log(n))
	public static int lcm(int a, int b) {
		int gcd = gcd(a, b);
		if (gcd != 0) {
			return Math.abs(a * b) / gcd;
		}
		return 0;
	}

	// O(b)
	public static long exponentiation(int a, int b) {
		if (b < 0) {
			return 0;
		} else if (b == 0) {
			return 1;
		}
		return a * exponentiation(a, b - 1);
	}

	// O(n*log(log(n)))
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

	// O(n * m)
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

	// O(n * m)
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

	// O(n^2)
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

	// O(n)
	public static void arrayReverse(int[] array) {
		int len = array.length;
		int temp = 0;
		for (int i = 0; i < len / 2; ++i) {
			temp = array[i];
			array[i] = array[len - 1 - i];
			array[len - 1 - i] = temp;
		}

	}

	// O(n^2)
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
