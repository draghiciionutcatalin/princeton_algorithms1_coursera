/* *****************************************************************************
 *  Name:              Ionut Draghici
 *  Created:           24 March 2024 16:34
 **************************************************************************** */

public class UnorderedMaxPQCourse<Key extends Comparable<Key>> {

    private Key[] pq; // pq[i] = ith element on pq
    private int N; // number of elements on pq

    public UnorderedMaxPQCourse(int capacity) {
        pq = (Key[]) new Comparable[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key x) {
        pq[N++] = x;
    }

    public Key delMax() {
        int max = 0;
        for (int i = 1; i < N; i++)
            if (less(max, i)) max = i;
        exchange(pq, max, N - 1);
        return pq[--N];
    }

    public static boolean less(Comparable v, Comparable w) {
        if (v == null || w == null) return false;
        if (v.compareTo(w) == -1) return true;
        return false;
    }

    public void exchange(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {

    }
}
