/* *****************************************************************************
 *  Name:              Ionut Draghici
 *  Created:           26 February 2024 22:05
 **************************************************************************** */

import edu.princeton.cs.algs4.Stack;

public class StackMax {

    private Stack<Double> doubles;
    private Stack<Double> max;

    public StackMax() {
        doubles = new Stack<>();
        max = new Stack<>();
    }

    public Double pop() {
        if (doubles.isEmpty()) {
            throw new RuntimeException("empty stack");
        }
        double popped = doubles.pop();
        if (popped == max.peek()) {
            max.pop();
        }

        return popped;
    }

    public void push(double value) {
        doubles.push(value);
        if (max.isEmpty() || (value >= max.peek())) {
            max.push(value);
        }
    }

    public double getMax() {
        if (max.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        return max.peek();
    }

    public static void main(String[] args) {
        StackMax stackMax = new StackMax();
        stackMax.push(1.3);
        stackMax.push(1.6);
        stackMax.push(1.8);
        stackMax.push(3.0);
        stackMax.push(5.0);
        System.out.println("Max: " + stackMax.getMax());

        stackMax.push(2.0);
        stackMax.push(7.0);
        System.out.println("Max: " + stackMax.getMax());

        stackMax.pop();
        System.out.println("Max: " + stackMax.getMax());

        stackMax.pop();
        System.out.println("Max: " + stackMax.getMax());
    }
}
