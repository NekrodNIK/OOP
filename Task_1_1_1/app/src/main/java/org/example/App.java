package org.example;

import java.util.Arrays;
import java.util.PriorityQueue;

/** App. */
public class App {
    /**
     * heap sort impl.
     *
     * @param arr Sortable array
     */
    public static void sort(int[] arr) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();

        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = queue.poll();
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
