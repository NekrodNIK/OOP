package org.example;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        HeapSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
