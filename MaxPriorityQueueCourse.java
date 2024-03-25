/* *****************************************************************************
 *  Name:              Ionut Draghici
 *  Created:           25 March 2024 19:52
 **************************************************************************** */

import java.util.NoSuchElementException;

public class MaxPriorityQueueCourse<Key extends Comparable<Key>> {

    private Key[] pq; // binary heap of keys
    private int n;    // number of elements in the priority queue

    // Constructor to initialize the priority queue with a given capacity
    public MaxPriorityQueueCourse(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1]; // Index 0 is not used
        n = 0;
    }

    // Returns true if the priority queue is empty, false otherwise
    public boolean isEmpty() {
        return n == 0;
    }

    // Returns the number of elements in the priority queue
    public int size() {
        return n;
    }

    // Inserts a key into the priority queue
    public void insert(Key key) {
        if (n == pq.length - 1) {
            resize(2 * pq.length);
        }
        pq[++n] = key;
        swim(n); // Restore heap order
    }

    // Removes and returns the maximum key from the priority queue
    public Key delMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        Key max = pq[1];
        swap(1, n--);
        sink(1); // Restore heap order
        pq[n + 1] = null; // Avoid loitering
        if (n > 0 && n == (pq.length - 1) / 4) {
            resize(pq.length / 2);
        }
        return max;
    }

    // Helper method to restore the heap order by moving a key up the heap
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            swap(k / 2, k);
            k = k / 2;
        }
    }

    // Helper method to restore the heap order by moving a key down the heap
    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    // Helper method to check if the key at index i is less than the key at index j
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    // Helper method to swap keys at indices i and j
    private void swap(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    // Helper method to resize the array to the given capacity
    private void resize(int capacity) {
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    // Main method for testing
    public static void main(String[] args) {
        MaxPriorityQueueCourse<Integer> pq = new MaxPriorityQueueCourse<>(5);
        pq.insert(5);
        pq.insert(3);
        pq.insert(9);
        pq.insert(2);
        pq.insert(7);

        System.out.println("Size of priority queue: " + pq.size());
        while (!pq.isEmpty()) {
            System.out.print(pq.delMax() + " ");
        }
        System.out.println();
    }
}
