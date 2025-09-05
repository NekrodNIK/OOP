package org.example;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

/** Test for heap sorting algorithm */
public class HeapSortTest {
    /** Test for positive items. */
    @Test
    public void testPositive() {
        int[] arr = {5, 4, 7, 2, 1};
        HeapSort.sort(arr);

        int[] expected = {1, 2, 4, 5, 7};
        assertArrayEquals(expected, arr);
    }

    /** Test for negative items. */
    @Test
    public void testNegative() {
        int[] arr = {-2, -1, -4, -8, -5};
        HeapSort.sort(arr);

        int[] expected = {-8, -5, -4, -2, -1};
        assertArrayEquals(expected, arr);
    }

    /** Test for empty items. */
    @Test
    public void testEmpty() {
        int[] arr = new int[0];
        assertDoesNotThrow(() -> HeapSort.sort(arr));
        assertArrayEquals(new int[0], arr);
    }

    /** Test for single item. */
    @Test
    public void testSingle() {
        int[] arr = {2};
        HeapSort.sort(arr);
        assertArrayEquals(new int[] {2}, arr);
    }

    /** Test for alredy sorted arr. */
    @Test
    public void testSorted() {
        int[] sorted = {1, 2, 3, 4, 5};
        HeapSort.sort(sorted);
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, sorted);
    }

    /** Test for reverse sorted arr. */
    @Test
    public void testReverseOrderSorted() {
        int[] reversed = {5, 4, 3, 2, 1};
        HeapSort.sort(reversed);
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, reversed);
    }

    /** Test for dublicated items. */
    @Test
    public void testWithEqualItems() {
        int[] arr = {2, 3, 1, 3, 4, 2};
        HeapSort.sort(arr);
        assertArrayEquals(new int[] {1, 2, 2, 3, 3, 4}, arr);
    }

    /** Randomized items test. */
    @Test
    public void testRandom() {
        Random random = new Random();

        int[] arr = new int[100000];
        for (int i = 0; i < 100000; i++) {
            arr[i] = random.nextInt();
        }

        int[] copy = arr.clone();

        HeapSort.sort(arr);
        Arrays.sort(copy);

        assertArrayEquals(copy, arr);
    }
}
