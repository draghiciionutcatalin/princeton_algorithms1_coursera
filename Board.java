/* *****************************************************************************
 *  Name:              Ionut Draghici
 *  Created:           29 March 2024 13:45
 **************************************************************************** */

import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;

public class Board {

    private final int[][] tiles;
    private final int n;
    private int zeroRow;
    private int zeroCol;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        n = tiles.length;
        this.tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.tiles[i][j] = tiles[i][j];
                if (tiles[i][j] == 0) {
                    zeroRow = i;
                    zeroCol = j;
                }
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(n).append("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                str.append(String.format("%2d ", tiles[i][j]));
            }
            str.append("\n");
        }
        return str.toString();
    }


    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != i * n + j + 1) {
                    count++;
                }
            }
        }
        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != 0) {
                    int targetRow = (tiles[i][j] - 1) / n;
                    int targetCol = (tiles[i][j] - 1) % n;
                    sum += Math.abs(targetRow - i) + Math.abs(targetCol - j);
                }
            }
        }
        return sum;
    }

    // Is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }
        if (y == null || y.getClass() != this.getClass()) {
            return false;
        }
        Board that = (Board) y;

        return Arrays.deepEquals(this.tiles, that.tiles);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        int blankX, blankY;
        Queue<Board> boards = new Queue<Board>();
        int tmp;
        int i, j;
        for (i = 0; i < n; i++)
            for (j = 0; j < n; j++)
                if (tiles[i][j] == 0) {
                    if (i != 0) {
                        int[][] nb = copyTiles();
                        nb[i][j] = nb[i - 1][j];
                        nb[i - 1][j] = 0;
                        boards.enqueue(new Board(nb));
                    }
                    if (i != n - 1) {
                        int[][] nb = copyTiles();
                        nb[i][j] = nb[i + 1][j];
                        nb[i + 1][j] = 0;
                        boards.enqueue(new Board(nb));
                    }
                    if (j != 0) {
                        int[][] nb = copyTiles();
                        nb[i][j] = nb[i][j - 1];
                        nb[i][j - 1] = 0;
                        boards.enqueue(new Board(nb));
                    }
                    if (j != n - 1) {
                        int[][] nb = copyTiles();
                        nb[i][j] = nb[i][j + 1];
                        nb[i][j + 1] = 0;
                        boards.enqueue(new Board(nb));
                    }
                    return boards;
                }
        return null;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] twinTiles = copyTiles();
        // Find two non-blank tiles to exchange
        int row = 0;
        int col1 = 0;
        int col2 = 1;
        if (twinTiles[row][col1] == 0 || twinTiles[row][col2] == 0) {
            row = 1;
        }
        // Exchange the two tiles
        int temp = twinTiles[row][col1];
        twinTiles[row][col1] = twinTiles[row][col2];
        twinTiles[row][col2] = temp;
        // Create and return the twin board
        return new Board(twinTiles);
    }

    private int[][] copyTiles() {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(tiles[i], 0, copy[i], 0, n);
        }
        return copy;
    }

    // unit testing (not graded)
    public static void main(String[] args) {

    }
}
