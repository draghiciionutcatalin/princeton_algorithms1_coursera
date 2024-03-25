/* *****************************************************************************
 *  Name:              Ionut Draghici
 *  Created:           18 March 2024 16:18
 **************************************************************************** */

import java.util.Arrays;

public class ShellSortCourse implements Comparable<Integer> {

    private static Comparable[] a;
    private static int swaps = 0;
    private static int compares = 0;

    public static boolean less(Comparable v, Comparable w) {
        compares++;
       /* if (v == null || w == null) return false;
        if (v.compareTo(w) == -1) return true;
        return false;*/
        return v.compareTo(w) < 0;
    }

    public static void exchange(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
        swaps++;
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        a = new Comparable[] {
                898, 78987778, 885, 56448, 61, 2, 33, 444, 325, 6575, 5675, 567, 5676756
        };

        System.out.println(Arrays.toString(Arrays.stream(a).toArray()));

        int n = a.length;
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, ...
        }

        while (h >= 1) { // h-sort the array.
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exchange(a, j, j - h);
                    System.out.println(Arrays.toString(Arrays.stream(a).toArray()));
                }
            }
            h = h / 3;
            System.out.print(Arrays.toString(Arrays.stream(a).toArray()));
            System.out.println("   after h/3");
        }

        System.out.println(Arrays.toString(Arrays.stream(a).toArray()));

    }

    public int compareTo(Integer o) {
        if (o == null) {
            throw new IllegalArgumentException("NPE");
        }
        if (this.equals(o)) {
            return 0;
        }
        return -1;
    }
}
