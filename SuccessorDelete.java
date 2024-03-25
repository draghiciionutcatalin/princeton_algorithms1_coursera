/* *****************************************************************************
 *  Name:              Ionut Draghici
 *  Created:           14 February 2024 13:58
 **************************************************************************** */

import java.util.Arrays;

public class SuccessorDelete {

    // Given a set of n integers S={0,1,...,n−1} and a sequence of requests of the following form:
    // Remove x from S
    // Find the successor of x: the smallest y in S such that y≥x
    // design a data type so that all operations (except construction) take logarithmic time or better in the worst case.

    private int[] arraySorted;

    public SuccessorDelete(int n) {
        arraySorted = new int[n];
    }

    public void populateArray() {
        for (int i = 0; i < arraySorted.length; i++) {
            arraySorted[i] = Math.toIntExact(Math.round(Math.random() * 100.0d));
        }
        Arrays.sort(arraySorted);
    }

    public int find(int x) {
        for (int i = 0; i < arraySorted.length; i++) {
            if (arraySorted[i] == x) {
                return i;
            }
        }
        return -1;
    }

    public void remove(int x) {
        int[] aux = new int[arraySorted.length - 1];
        for (int i = 0, k = 0; i < arraySorted.length; i++) {
            if (arraySorted[i] != x) {
                aux[k] = arraySorted[i];

                k++;
            }
        }
        arraySorted = aux;
    }

    private void findSuccessor(int x) {
        int index = find(x);
        if (index != -1 && index < arraySorted.length - 1) {
            System.out.println(arraySorted[index + 1]);
        }
        else {
            System.out.println("No successor found");
        }
    }

    public void printArray() {
        System.out.println(Arrays.toString(arraySorted));
    }

    public static void main(String[] args) {
        SuccessorDelete sd = new SuccessorDelete(10);
        sd.populateArray();
        sd.printArray();
        sd.arraySorted[1] = 4;
        sd.printArray();
        sd.findSuccessor(4);
        sd.remove(4);
        sd.printArray();
    }
}
