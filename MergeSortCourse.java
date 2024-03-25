/* *****************************************************************************
 *  Name:              Ionut Draghici
 *  Created:           18 March 2024 17:47
 **************************************************************************** */

public class MergeSortCourse { // good for objects

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    public static boolean less(Comparable v, Comparable w) {
        if (v == null || w == null) return false;
        if (v.compareTo(w) == -1) return true;
        return false;
    }

    public static void merge(Comparable[] a, Comparable[] aux, int low, int mid, int hig) {
        assert isSorted(a, low, mid); // precondition: a[lo..mid] sorted
        assert isSorted(a, mid + 1, hig); // precondition: a[mid+1..hi] sorted
        for (int k = low; k <= hig; k++) {
            aux[k] = a[k];
        }
        int i = low, j = mid + 1;
        for (int k = low; k <= hig; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hig) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
        assert isSorted(a, low, hig); // postcondition: a[lo..hi] sorted
    }

    private static void sort(Comparable[] a, Comparable[] aux, int low, int hig) {
        if (hig <= low) return; // insert CUTOFF here  low + CUTOFF - 1 then insert sort
        int mid = low + (hig - low) / 2;
        sort(a, aux, low, mid);
        sort(a, aux, mid + 1, hig);
        if (!less(a[mid + 1], a[mid]))
            return; // this will stop the merge if both half are already sorted
        merge(a, aux, low, mid, hig);
    }

    // This is Bottom-up Mergesort
    private static void sortBU(Comparable[] a, Comparable[] aux, int low, int hig) {
        int n = a.length;
        aux = new Comparable[n];
        for (int sz = 0; sz < n; sz = sz + sz) {
            for (int lo = 0; lo < n - sz; lo += sz + sz) {
                // merge(a, low, lo+sz-1, Math.min(low+sz+sz-1, n-1));
            }

        }
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSorted(Comparable[] a, int start, int end) {
        for (int i = start; i < end; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
