/* *****************************************************************************
 *  Name:              Ionut Draghici
 *  Created:           20 March 2024 10:13
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class QuickSortCourse { // good for primitives

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;

        while (true) {
            while (less(a[++i], a[lo])) {
                if (i == hi) break;
            }
            while (less(a[lo], a[--j])) {
                if (j == lo) break;
            }

            if (i >= j) break;

            exchange(a, i, j);
            System.out.println(Arrays.toString(Arrays.stream(a).toArray()));
        }

        exchange(a, lo, j);
        System.out.println(Arrays.toString(Arrays.stream(a).toArray()));
        return j;
    }

    private static void sort(Comparable[] a) {
        StdRandom.shuffle(
                a); // used to increase performance, the quicksort is less performant for sorted asc or desc arrays
        sort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(Arrays.stream(a).toArray()));
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        // improvement 1
        // if (hi <= lo + CUTOFF - 1) { // CUTOFF to insertion sort for ~10 items
        //    Insertion.sort(a, lo, hi);
        //    return;
        //}

        // improvement 2
        // int m = medianOf3(a, lo, lo + (hi-lo)/2, hi);
        // swap(a, lo, m);

        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        System.out.println(Arrays.toString(Arrays.stream(a).toArray()));
        sort(a, j + 1, hi);
        System.out.println(Arrays.toString(Arrays.stream(a).toArray()));
    }

    public static boolean less(Comparable v, Comparable w) {
        if (v == null || w == null) return false;
        if (v.compareTo(w) == -1) return true;
        return false;
    }

    public static void exchange(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        Comparable[] a = new Comparable[] {
                898, 78987778, 885, 56448, 61, 2, 33, 444, 325, 6575, 5675, 567, 5676756
        };

        System.out.println(Arrays.toString(Arrays.stream(a).toArray()));

        QuickSortCourse q = new QuickSortCourse();
        q.sort(a);

        // sort


        System.out.println(Arrays.toString(Arrays.stream(a).toArray()));
        // System.out.println("Swaps=" + swaps + " Compares=" + compares);

    }
}
