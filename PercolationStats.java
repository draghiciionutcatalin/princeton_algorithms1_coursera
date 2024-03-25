import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95_CONST = 1.96;
    private int trials;
    private double[] thresholds;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int mapSize, int trials) {
        if (mapSize <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Map size and trials must be greater than 0");
        }

        this.trials = trials;
        this.thresholds = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(mapSize);
            while (!percolation.percolates()) {
                int row = StdRandom.uniformInt(1, mapSize + 1);
                int col = StdRandom.uniformInt(1, mapSize + 1);
                percolation.open(row, col);

                double threshold = (double) percolation.numberOfOpenSites() / (mapSize * mapSize);
                thresholds[i] = threshold;
            }
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONFIDENCE_95_CONST * stddev() / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONFIDENCE_95_CONST * stddev() / Math.sqrt(trials));
    }

    // test client (see below)
    public static void main(String[] args) {
        int mapSize = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(mapSize, trials);

        String confidence = "[" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]";

        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = " + confidence);
    }
}
