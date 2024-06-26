/* *****************************************************************************
 *  Name:              Ionut Draghici
 *  Created:           29 March 2024 13:50
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solver {

    private static class SearchNode implements Comparable<SearchNode> {
        Board board;
        int manhattan;
        int moves = 0;
        SearchNode previousNode;
        int priority;

        public SearchNode(Board board, SearchNode previousNode) {
            if (board == null) throw new IllegalArgumentException();
            this.board = board;
            this.manhattan = board.manhattan();
            if (previousNode != null) {
                this.moves = previousNode.moves + 1;
                this.previousNode = previousNode;
            }
            this.priority = this.moves + this.manhattan;
        }

        @Override
        public int compareTo(SearchNode sn) {
            if (this.priority < sn.priority) return -1;
            else if (this.priority > sn.priority) return 1;
            else return 0;
        }


    }


    // SearchNode curr2;
    private SearchNode curr;

    private boolean solvable = false;
    private List<Board> dequer = new ArrayList<>();

    public Solver(Board initial) {
        MinPQ<SearchNode> pq = new MinPQ<>();
        MinPQ<SearchNode> pqI = new MinPQ<>();
        curr = new SearchNode(initial, null);
        SearchNode twin = new SearchNode(initial.twin(), null);
        pq.insert(curr);
        pqI.insert(twin);

        while (!curr.board.isGoal() && !twin.board.isGoal()) {
            curr = pq.delMin();
            twin = pqI.delMin();

            for (Board nb : curr.board.neighbors()) {
                if (curr.previousNode == null || !nb.equals(curr.previousNode.board)) {
                    pq.insert(new SearchNode(nb, curr));
                }
            }
            for (Board nb : twin.board.neighbors()) {
                if (twin.previousNode == null || !nb.equals(twin.previousNode.board))
                    pqI.insert(new SearchNode(nb, twin));
            }

        }
        if (curr.board.isGoal()) {
            solvable = true;
        }
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable()) StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board
    public int moves() {
        if (isSolvable()) return curr.moves;
        else return -1;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        SearchNode temp = new SearchNode(curr.board, curr.previousNode);
        if (isSolvable()) {
            while (temp != null) {
                dequer.add(temp.board);
                temp = temp.previousNode;
            }
            Collections.reverse(dequer);
            return dequer;
        }
        else return null;
    }

}