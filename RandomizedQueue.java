/* *****************************************************************************
 *  Name:              Ionut Draghici
 *  Created:           26 February 2024 22:26
 **************************************************************************** */

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] rQueue;
    private int n = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        rQueue = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add a null argument.");
        }
        if (n == rQueue.length) {
            resize(2 * rQueue.length);
        }
        rQueue[n++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (n == 0) {
            throw new NoSuchElementException("The queue is empty.");
        }
        int idx = StdRandom.uniformInt(n);
        Item item = rQueue[idx];
        if (idx < n - 1) {
            rQueue[idx] = rQueue[n - 1];
        }
        rQueue[--n] = null;
        if (n > 0 && n == rQueue.length / 4) {
            resize(rQueue.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (n == 0) {
            throw new NoSuchElementException("The queue is empty.");
        }
        int idx = StdRandom.uniformInt(n);
        Item item = rQueue[idx];
        return item;
    }

    private class RQIterator implements Iterator<Item> {

        private int count = 0;
        private final int[] indices = new int[n];

        public RQIterator() {
            for (int i = 0; i < n; i++) {
                indices[i] = i;
            }
            StdRandom.shuffle(indices);
        }

        public boolean hasNext() {
            return count < n;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There are no items.");
            }
            return rQueue[indices[count++]];
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove is not supported.");
        }
    }

    private void resize(int capacity) {
        Object[] copy = new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = rQueue[i];
        }
        rQueue = (Item[]) copy;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RQIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        for (int i = 0; i < 10; i++) {
            rq.enqueue(i);
        }

        Insertion.sort(rq.rQueue);
        StdOut.println(rq.size());
        StdOut.println(rq.isEmpty());
        StdOut.println(rq.sample());
        StdOut.println(rq.sample());
        StdOut.println(rq.sample());
        StdOut.println(rq.dequeue());
        StdOut.println(rq.dequeue());
        StdOut.println(rq.dequeue());
        for (int i : rq) {
            StdOut.print(i + " ");
        }
    }
}
