/* *****************************************************************************
 *  Name:              Ionut Draghici
 *  Created:           14 February 2024 16:59
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationMy {

    private boolean[][] map;
    private int size;
    private int openSites;
    private WeightedQuickUnionUF ufFull;
    private WeightedQuickUnionUF wqfGrid;
    private int gridSquared;
    private int virtualTop;
    private int virtualBottom;

    // creates n-by-n grid, with all sites initially blocked
    public PercolationMy(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Map size must be greater than 0");
        }

        this.map = new boolean[n][n];
        this.size = n;
        this.openSites = 0;

        this.ufFull = new WeightedQuickUnionUF(n * n + 2);

        // connect top and bottom sites
        for (int i = 1; i <= size; i++) {
            ufFull.union(0, i);
            ufFull.union(n * n + 1, (n - 1) * n + i);
        }
    }

    // Opens the site (row, col), only if it is not open already.
    public void open(int row, int col) {
        validateIndices(row, col);

        if (!map[row - 1][col - 1]) {
            map[row - 1][col - 1] = true;
            openSites++;

            int currentSite = siteIndex(row, col);

            // Connect to adjacent open sites
            // connectToAdjacent(row, col, row - 1, col);
            // connectToAdjacent(row, col, row + 1, col);
            // connectToAdjacent(row, col, row, col - 1);
            // connectToAdjacent(row, col, row, col + 1);

            connectIfOpen(row - 1, col, currentSite);
            connectIfOpen(row + 1, col, currentSite);
            connectIfOpen(row, col - 1, currentSite);
            connectIfOpen(row, col + 1, currentSite);
        }
    }

    private void validateIndices(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IllegalArgumentException("Indices out of bounds");
        }
    }

    private int siteIndex(int row, int col) {
        return (row - 1) * size + col;
    }

    // Private void connectToAdjacent(int row1, int col1, int row2, int col2) {
    //     if (isValidSite(row2, col2) && isOpen(row2, col2)) {
    //         uf.union(getIndex(row1, col1), getIndex(row2, col2));
    //     }
    // }

    private void connectIfOpen(int row, int col, int currentSite) {
        if (row >= 1 && row <= size && col >= 1 && col <= size && isOpen(row, col)) {
            ufFull.union(currentSite, siteIndex(row, col));
        }
    }

    private int getIndex(int row, int col) {
        return (row - 1) * size + col;
    }

    // private boolean isValidSite(int row, int col) {
    //     return row >= 1 && row <= size && col >= 1 && col <= size;
    // }

    // It is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateIndices(row, col);
        return map[row - 1][col - 1];
    }

    // It is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateIndices(row, col);
        return (ufFull.find(0) == ufFull.find(getIndex(row, col)));
    }

    // returns the amount of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return (ufFull.find(0) == ufFull.find(size * size + 1));
    }
}