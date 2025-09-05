package org.example;

import java.util.Arrays;

/** A class containing the entry point. */
public class App {
    /**
     * The entry point.
     *
     * @param args CLI arguments
     */
    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        HeapSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
