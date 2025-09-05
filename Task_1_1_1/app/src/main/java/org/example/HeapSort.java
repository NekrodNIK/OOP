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
            siftDown(arr, i, arr.length);
        }

        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            siftDown(arr, 0, i);
        }
    }

    /**
     * Sifting down an item in the heap.
     *
     * @param arr input array
     * @param i start index
     * @param n number of item
     */
    private static void siftDown(int[] arr, int i, int n) {
        while (left(i) < n) {
            int l = left(i);
            int r = l + 1;
            int j = (r < n && arr[r] > arr[l]) ? r : l;

            if (arr[i] > arr[j]) {
                break;
            }

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
}
