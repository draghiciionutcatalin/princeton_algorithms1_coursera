/* *****************************************************************************
 *  Name:              Ionut Draghici
 *  Created:           28 March 2024 18:46
 **************************************************************************** */

public class HeapSortCourse {
    public static void sort(Comparable[] a) {
        int N = a.length;
        // this will order the heap array with the maximum value at the root
        for (int k = N / 2; k >= 1; k--) {
            sink(a, k, N);
        }

        // while there are elements in the array, swap the root(max) with the last element, sink the new root, decrease the array size (not null for the last value)
        while (N > 1) {
            exch(a, 1, N);
            sink(a, 1, --N);
        }
    }

    private static void sink(Comparable[] a, int k, int N) { /* as before */ }

    private static boolean less(Comparable[] a, int i, int j) { /* as before */ }

    private static void exch(Comparable[] a, int i, int j) { /* as before */ }


    public static void main(String[] args) {

    }
}
