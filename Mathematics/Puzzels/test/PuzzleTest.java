import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 9, 153, 370, 371, 407})
    void shouldReturnTrueForArmstrongNumbers(int number) {
        assertTrue(Puzzle.isArmstrong(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 11, 372, 999})
    void shouldReturnFalseForNonArmstrongNumbers(int number) {
        assertFalse(Puzzle.isArmstrong(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 9, 11, 55, 252, 1551, 2222, 2552})
    void shouldReturnTrueForPalindromes(int number) {
        assertTrue(Puzzle.isPalindrome(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {12, 21, 122, 1553})
    void shouldReturnFalseForNonPalindromes(int number) {
        assertFalse(Puzzle.isPalindrome(number));
    }

    @Test
    void shouldProvideCorrectFibonacciCount() {
        assertEquals(21, Puzzle.fibonacciRecursive(8));
        assertEquals(21, Puzzle.fibonacciIterative(8));
    }

    @Test
    void gcd() {
        assertEquals(24, Puzzle.gcd(24, 48));
        assertEquals(6, Puzzle.gcd(24, 54));
        assertEquals(12, Puzzle.gcd(48, 180));
    }

    @Test
    void lcm() {
        assertEquals(216, Puzzle.lcm(24, 54));
    }

    @Test
    void exponentiation() {
        assertEquals(65536, 216, Puzzle.exponentiation(2, 16));
    }

    @Test
    void sieveOfEratosthenes() {
        List<Integer> result = new ArrayList<>();
        result.add(2);
        result.add(3);
        result.add(5);
        result.add(7);
        result.add(11);
        result.add(13);
        result.add(17);
        result.add(19);
        result.add(23);
        result.add(29);
        result.add(31);
        result.add(37);
        result.add(41);
        result.add(43);
        result.add(47);
        result.add(53);

        assertEquals(result, Puzzle.sieveOfEratosthenes(55));
    }

    @Test
    void getPrimeFactors() {
        List<Integer> result = new ArrayList<>();
        result.add(13);
        result.add(41);

        assertEquals(result, Puzzle.getPrimeFactors(533));
    }

    @Test
    void calculateFrequency() {
        Map<Integer, Integer> result = new HashMap<>();
        result.put(1, 1);
        result.put(2, 3);
        result.put(3, 0);
        result.put(5, 0);
        result.put(7, 0);
        result.put(8, 0);
        int[] array = {1, 2, 8, 7, 3, 2, 2, 2, 5, 1};
        assertEquals(result, Puzzle.calculateFrequency(array));
    }

    @Test
    void leftRotateArray() {
        int[] result = {3, 4, 5, 6, 1, 2};
        int[] array = {1, 2, 3, 4, 5, 6};
        Puzzle.leftRotateArray(array, 2);
        assertArrayEquals(result, array);
    }

    @Test
    void arrayReverse() {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] result = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        Puzzle.arrayReverse(array);
        assertArrayEquals(result, array);
    }

    @Test
    void decimalToBinary() {
        String result = "1000010001";
        assertEquals(result, Puzzle.decimalToBinary(529));
    }

    @Test
    void binaryGap() {
        assertEquals(4, Puzzle.binaryGap(529));
    }
}