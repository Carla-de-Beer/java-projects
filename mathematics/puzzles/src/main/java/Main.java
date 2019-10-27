import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    @FunctionalInterface
    interface TextMessage {
        void printMessage(String str);
    }

    @FunctionalInterface
    interface MessageOneParam {
        void printMessage(int n);
    }

    @FunctionalInterface
    interface MessageTwoParams {
        void printMessage(int n, int m);
    }

    @FunctionalInterface
    interface MessageNoParams {
        void printMessage();
    }

    public static void main(String[] args) {

        TextMessage headerText = m -> {
            System.out.println("---------------------------------------------");
            System.out.println(m + ":");
            System.out.println("---------------------------------------------");
        };

        // ---------------------- Armstrong numbers
        headerText.printMessage("Armstrong numbers");
        MessageOneParam armstrongMessage = m -> System.out
                .println("Is " + m + " an Armstrong number? " + Puzzle.isArmstrong(m));

        int n = 153;
        armstrongMessage.printMessage(n);

        n = 99;
        armstrongMessage.printMessage(n);

        n = 371;
        armstrongMessage.printMessage(n);

        // ---------------------- Palindromic numbers
        System.out.println();
        headerText.printMessage("Palindromic numbers");
        MessageOneParam palindromeMessage = m -> System.out
                .println("Is " + m + " an palindromic number? " + Puzzle.isPalindrome(m));
        n = 1551;
        palindromeMessage.printMessage(n);

        n = 1553;
        palindromeMessage.printMessage(n);

        n = 1;
        palindromeMessage.printMessage(n);

        // ---------------------- Fibonacci numbers
        System.out.println();
        headerText.printMessage("Fibonacci numbers");

        MessageOneParam fibMessageIterative = m -> System.out
                .println("Iterative: " + "Fibonacci number " + m + ": " + Puzzle.fibonacciIterative(m));
        MessageOneParam fibMessageRecursive = m -> System.out
                .println("Recursive: " + "Fibonacci number " + m + ": " + Puzzle.fibonacciRecursive(m));
        MessageNoParams fibMessageMemoized = () -> System.out.println("Memoized: ");

        n = 8;
        fibMessageIterative.printMessage(n);
        fibMessageRecursive.printMessage(n);

        n = 40;
        fibMessageRecursive.printMessage(n);

        fibMessageMemoized.printMessage();
        n = 40;
        int[] memo = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            System.out.print(Puzzle.fibonacciMemoization(i, memo) + " ");
            if (i % 5 == 0) {
                System.out.println();
            }
        }

        // ---------------------- Calculate GCD
        System.out.println();
        headerText.printMessage("Calculate GCD");

        MessageTwoParams gcdMessage = (p, q) -> System.out.println("gcd(" + p + ", " + q + "): " + Puzzle.gcd(p, q));
        int a = 24;
        int b = 54;
        gcdMessage.printMessage(a, b);

        a = 48;
        b = 180;
        gcdMessage.printMessage(a, b);

        // ---------------------- Calculate LCM
        System.out.println();
        headerText.printMessage("Calculate LCM");

        MessageTwoParams lcdMessage = (p, q) -> System.out.println("lcm(" + p + ", " + q + "): " + Puzzle.lcm(p, q));
        a = 24;
        b = 54;
        lcdMessage.printMessage(a, b);

        // ---------------------- Calculate a^b
        System.out.println();
        headerText.printMessage("Calculate a^b");

        MessageTwoParams exponentiationMessage = (p, q) -> System.out
                .println(p + "^" + q + ": " + Puzzle.exponentiation(p, q));
        int c = 2;
        int d = 16;
        exponentiationMessage.printMessage(c, d);

        // ---------------------- Sieve of Eratosthenes
        System.out.println();
        headerText.printMessage("Sieve of Eratosthenes");

        n = 55;
        System.out.println("Find the prime numbers <= " + n + ": ");
        List<Integer> primes = Puzzle.sieveOfEratosthenes(n);
        printArrayList(primes);

        // ---------------------- Prime factors
        System.out.println();
        headerText.printMessage("Prime factors");

        n = 533;
        System.out.println("Find the prime factors of " + n + ":");
        List<Integer> primeFactors = Puzzle.getPrimeFactors(n);
        printArrayList(primeFactors);

        // ---------------------- Count occurrences in an array
        System.out.println();
        headerText.printMessage("Count occurrences in an array");

        int[] array3 = {1, 2, 8, 7, 3, 2, 2, 2, 5, 1};
        Map<Integer, Integer> map = Puzzle.calculateFrequency(array3);
        printHashMap(map);

        // ---------------------- Left rotate array
        System.out.println();
        headerText.printMessage("Left rotate array");

        int[] array4 = {1, 2, 3, 4, 5, 6};
        n = 2;
        printArray(array4);
        System.out.println("Rotate from position " + n + ":");
        Puzzle.leftRotateArray(array4, n);
        printArray(array4);

        // ---------------------- Reverse array in place
        System.out.println();
        headerText.printMessage("Reverse array in place");

        int[] array5 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        printArray(array5);
        Puzzle.arrayReverse(array5);
        printArray(array5);

        // ---------------------- Staircase
        System.out.println();
        headerText.printMessage("Staircase");

        n = 5;
        System.out.println("Staircase diagram for " + n + ": ");
        Puzzle.staircase(n);

        // ---------------------- Decimal to binary
        System.out.println();
        headerText.printMessage("Decimal to binary");

        System.out.println(Puzzle.decimalToBinary(529));

        // ---------------------- Binary gap
        System.out.println();
        headerText.printMessage("Largest binary gap");

        System.out.println(Puzzle.binaryGap(529));
    }

    private static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    private static void printArrayList(List<Integer> list) {
        for (Integer integer : list) {
            System.out.print(integer + " ");
        }
        System.out.println();
    }

    private static void printHashMap(Map<Integer, Integer> map) {
        for (HashMap.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
