/* *****************************************************************************
 *  Name:              Ionut Draghici
 *  Created:           18 March 2024 17:10
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class StdRandomShuffelCourse {

    private static int[] a;
    private static int swaps = 0;
    private static int compares = 0;

    public static void exchange(int i, int j) {
        int x = a[j];
        a[j] = a[i];
        a[i] = x;
        swaps++;
    }


    public static void main(String[] args) {
        a
                = new int[10]; // { 898, 78987778, 885, 56448, 61, 2, 33, 444, 325, 6575, 5675, 567, 5676756 };
        for (int i = 0; i < 10; i++) {
            a[i] = i;
        }

        System.out.println(Arrays.toString(Arrays.stream(a).toArray()));

        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = StdRandom.uniformInt(i + 1); // a common bug: between 0 and N-1
            // int r = StdRandom.uniformInt(i + 1); // the correct variant: between i and N-1; swap with the unseen ones
            exchange(i, r);
            System.out.println(Arrays.toString(Arrays.stream(a).toArray()));
        }

        System.out.println(Arrays.toString(Arrays.stream(a).toArray()));
    }
}
