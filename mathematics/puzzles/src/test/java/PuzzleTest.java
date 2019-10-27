import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleTest {

    @Nested
    @DisplayName("Maths")
    @Tag("Maths")
    class Maths {

        @ParameterizedTest(name = "{index}: {0} is an Armstrong number")
        @DisplayName("When determining whether true Armstrong are Armstrong numbers")
        @ValueSource(ints = {0, 1, 9, 153, 370, 371, 407})
        void shouldReturnTrueForArmstrongNumbers(int number) {
            assertTrue(Puzzle.isArmstrong(number), "correctly mark the given numbers as Armstrong numbers.");
        }

        @ParameterizedTest(name = "{index}: {0} is not an Armstrong number")
        @DisplayName("When determining whether non-Armstrong are Armstrong numbers")
        @ValueSource(ints = {10, 11, 372, 999})
        void shouldReturnFalseForNonArmstrongNumbers(int number) {
            assertFalse(Puzzle.isArmstrong(number), "correctly mark the given numbers as not being Armstrong numbers.");
        }

        @ParameterizedTest(name = "{index}: {0} is a palindrome number")
        @DisplayName("When determining whether true palindrome numbers are palindromes")
        @ValueSource(ints = {0, 1, 9, 11, 55, 252, 1551, 2222, 2552})
        void shouldReturnTrueForPalindromeNumbers(int number) {
            assertTrue(Puzzle.isPalindrome(number), "correctly mark the given numbers as palindromes.");
        }

        @ParameterizedTest(name = "{index}: {0} is not a palindrome number")
        @DisplayName("When determining whether true palindrome numbers are palindromes")
        @ValueSource(ints = {10, 12, 34, 45, 50, 201, 1559, 2232, 2522})
        void shouldReturnFalseForNonPalindromeNumbers(int number) {
            assertFalse(Puzzle.isPalindrome(number), "correctly mark the given numbers as not being palindromes.");
        }

        @ParameterizedTest(name = "{index}: {0}")
        @DisplayName("When calculating gcd")
        @ValueSource(ints = {12, 21, 122, 1553})
        void shouldReturnFalseForNonPalindromes(int number) {
            assertFalse(Puzzle.isPalindrome(number), "correctly produce the result.");
        }

        @Test
        @DisplayName("When calculating the number of Fibonacci values for a given number")
        void shouldProvideCorrectFibonacciCount() {
            assertEquals(21, Puzzle.fibonacciRecursive(8), "correctly produce the result.");
            assertEquals(21, Puzzle.fibonacciIterative(8), "correctly produce the result.");
        }

        @Test
        @DisplayName("When calculating gcd")
        void gcd() {
            assertAll(
                    () -> assertEquals(6, Puzzle.gcd(24, 54), "correctly produce the divisors for even input values."),
                    () -> assertEquals(12, Puzzle.gcd(48, 180), "correctly produce the divisors for even input values."),
                    () -> assertEquals(3, Puzzle.gcd(999, 39), "correctly produce the divisors for uneven input values.")
            );
        }

        @Test
        @DisplayName("When calculating lcm")
        void lcm() {
            assertEquals(216, Puzzle.lcm(24, 54), "correctly produce the result.");
        }

        @Test
        @DisplayName("When calculating exponentiation")
        void exponentiation() {
            assertEquals(65536, 216, Puzzle.exponentiation(2, 16), "correctly produce the result.");
        }

        @Test
        @DisplayName("When calculating the Sieve of Eratosthenes")
        void sieveOfEratosthenes() {
            List<Integer> result = new ArrayList<>(Arrays.asList(
                    2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53
            ));
            assertEquals(result, Puzzle.sieveOfEratosthenes(55), "correctly produce the result.");
        }

        @Test
        @DisplayName("When calculating prime factors")
        void getPrimeFactors() {
            List<Integer> result = new ArrayList<>(Arrays.asList(13, 41));
            assertEquals(result, Puzzle.getPrimeFactors(533), "correctly produce the result.");
        }

        @Test
        @DisplayName("When calculating number frequencies")
        void calculateFrequency() {
            Map<Integer, Integer> result = Map.ofEntries(
                    Map.entry(1, 1),
                    Map.entry(2, 3),
                    Map.entry(3, 0),
                    Map.entry(5, 0),
                    Map.entry(7, 0),
                    Map.entry(8, 0)
            );

            int[] array = {1, 2, 8, 7, 3, 2, 2, 2, 5, 1};
            assertEquals(result, Puzzle.calculateFrequency(array), "correctly produce the result.");
        }

    }

    @Nested
    @DisplayName("Data structures")
    @Tag("Data-structures")
    class DataStructures {

        @Test
        @DisplayName("When left rotating an array")
        void leftRotateArray() {
            int[] result = {3, 4, 5, 6, 1, 2};
            int[] array = {1, 2, 3, 4, 5, 6};
            Puzzle.leftRotateArray(array, 2);
            assertArrayEquals(result, array, "correctly produce the result.");
        }

        @Test
        @DisplayName("When reversing an array")
        void arrayReverse() {
            int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
            int[] result = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
            Puzzle.arrayReverse(array);
            assertArrayEquals(result, array, "correctly produce the result.");
        }
    }

    @Nested
    @DisplayName("Binary arithmetic")
    @Tag("Binary-arithmetic")
    class BinaryArithmetic {

        @Test
        @DisplayName("When converting a decimal number to a binary value")
        void decimalToBinary() {
            String result = "1000010001";
            assertEquals(result, Puzzle.decimalToBinary(529), "correctly produce the result.");
        }

        @Test
        @DisplayName("When calculating a binary gap")
        void binaryGap() {
            assertEquals(4, Puzzle.binaryGap(529), "correctly produce the largest gap value.");
        }
    }
}