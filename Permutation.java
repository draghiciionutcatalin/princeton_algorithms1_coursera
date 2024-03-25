/* *****************************************************************************
 *  Name:              Ionut Draghici
 *  Created:           26 February 2024 22:26
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Mandatory argument for permutation size");
        }
        int n = Integer.parseInt(args[0]);
        if (n < 0) {
            throw new IllegalArgumentException("Permutation argument should be greater than 0");
        }

        RandomizedQueue<String> strings = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            strings.enqueue(StdIn.readString());
        }
        
        for (int i = 0; i < n; i++) {
            StdOut.println(strings.dequeue());
        }
    }
}
