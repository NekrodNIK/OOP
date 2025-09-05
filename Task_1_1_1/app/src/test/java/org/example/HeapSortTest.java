package org.example;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class HeapSortTest {
    @Test
    public void testSimple() {
        int[] arr = {5, 4, 3, 2, 1};
        HeapSort.sort(arr);

        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, arr);
    }
}
