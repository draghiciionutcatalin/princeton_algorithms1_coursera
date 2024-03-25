import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/* *****************************************************************************
 *  Name:              Ionut Draghici
 *  Created:           26 February 2024 22:26
 **************************************************************************** */

public class RandomWord {
    public static void main(String[] args) {

        String champion = null;
        int count = 0;

        while (!StdIn.isEmpty()) {
            String eachWord = StdIn.readString();
            count++;

            if (Math.random() < 1.0 / count) {
                champion = eachWord;
            }
        }

        StdOut.println(champion);
    }
}