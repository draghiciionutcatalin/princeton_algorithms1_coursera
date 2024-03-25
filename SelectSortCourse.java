/* *****************************************************************************
 *  Name:              Ionut Draghici
 *  Created:           15 March 2024 17:21
 **************************************************************************** */

import java.util.Arrays;

public class SelectSortCourse {

    private static int[] a;
    private static int swaps = 0;
    private static int compares = 0;

    public static boolean less(Comparable v, Comparable w) {
        compares++;
        if (v == null || w == null) return false;
        if (v.compareTo(w) == -1) return true;
        return false;
    }

    public static void exchange(int i, int j) {
        int x = a[j];
        a[j] = a[i];
        a[i] = x;
        swaps++;
    }

    public static void main(String[] args) {

        a = new int[] { 898, 78987778, 885, 56448, 61, 2, 33, 444, 325, 6575, 5675, 567, 5676756 };

        System.out.println(Arrays.toString(Arrays.stream(a).toArray()));

        // sort
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exchange(i, min);
            System.out.println(Arrays.toString(Arrays.stream(a).toArray()));
        }

        System.out.println(Arrays.toString(Arrays.stream(a).toArray()));
        System.out.println("Swaps=" + swaps + " Compares=" + compares);
    }
}
