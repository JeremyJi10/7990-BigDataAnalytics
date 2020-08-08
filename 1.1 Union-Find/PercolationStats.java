import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final int trials;
    private final double zScore = 1.96;     // 95% confidence interval
    private final double[] simuResults;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException("Both arguments should be positive");
        }

        this.trials = trials;
        simuResults = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation base = new Percolation(n);

            while (!base.percolates()) {
                base.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
            }

            simuResults[i] = (double) base.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(simuResults);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(simuResults);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (zScore * stddev()) / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (zScore * stddev()) / Math.sqrt(trials);
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats exp = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.printf("%-23s = %s\n", "mean", exp.mean());
        System.out.printf("%-23s = %s\n", "stddev", exp.stddev());
        System.out.printf("%-23s = [%s, %s]\n", "95% confidence interval", exp.confidenceLo(), exp.confidenceHi());
    }
}
