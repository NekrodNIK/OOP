package org.example;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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

    /**
     * run sort example.
     *
     * @param args CLI args
     */
    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        sort(arr);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        final PrintStream stdout = System.out;

        System.setOut(new PrintStream(myOut));
        System.out.println(Arrays.toString(arr));
        System.setOut(stdout);

        System.out.print(myOut.toString());
    }
}
