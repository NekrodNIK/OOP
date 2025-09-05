package org.example;

/** The heap sorting algorithm. */
public class HeapSort {
    /**
     * The entry point.
     *
     * @param arr input array
     */
    public static void sort(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            siftDown(arr, arr.length, i);
        }

        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, i, 0);
            siftDown(arr, i, 0);
        }
    }

    /**
     * Sifting down an item in the heap.
     *
     * @param arr input array
     * @param n number of item
     * @param i start index
     */
    private static void siftDown(int[] arr, int n, int i) {
        for (; ; ) {
            int j = i;
            int l = left(i);
            int r = right(i);

            if (l < n && arr[l] > arr[i]) j = l;
            if (r < n && arr[r] > arr[i]) j = r;

            if (j == i) break;

            swap(arr, i, j);
            i = j;
        }
    }

    /**
     * Swap the two items.
     *
     * @param arr input array
     * @param i first item
     * @param j second item
     */
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * Get the left child index.
     *
     * @param i parent index
     * @return left child index
     */
    private static int left(int i) {
        return (i + 1) * 2 - 1;
    }

    /**
     * Get the right child index.
     *
     * @param i parent index
     * @return right child index
     */
    private static int right(int i) {
        return (i + 1) * 2;
    }
}
