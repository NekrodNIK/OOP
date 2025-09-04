package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    public void simpleTest() {
        int arr[] = {9, 8, 7, 6, 5, 4, 3};
        int expected[] = {3, 4, 5, 6, 7, 8, 9};
        App.sort(arr);
        assertArrayEquals(arr, expected);
    }
}
