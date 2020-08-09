import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private final WeightedQuickUnionUF grid;    // private field of the grid only with virtual ceil (index: 0)
    private final WeightedQuickUnionUF gridWithGround;    // grid with virtual ceil and ground (index: n * n + 1)
    private final int sitesTotal;
    private final int n;  // number of sites in one row/column
    private boolean[] siteStatus;   // default values are all false representing "not opened"
    private int sitesOpened = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Int argument should be positive");
        }

        this.n = n;
        sitesTotal = n * n + 1; // with virtual ceil
        grid = new WeightedQuickUnionUF(sitesTotal);
        gridWithGround = new WeightedQuickUnionUF(sitesTotal + 1);
        siteStatus = new boolean[sitesTotal];
    }

    private void checkIndices(int row, int col) {
        if (row < 1 || col < 1 || row > this.n || col > this.n) {
            throw new IllegalArgumentException(String.format("row or column out of bound, 1 - %d", this.n));
        }
    }

    private void connectNeighbors(String direction, int row, int col) {
        int[] neighbors = {1, -1};
        int neiRow = row;   // initialize row and col of a neighbor with those of the center site
        int neiCol = col;

        for (int i = 0; i < 2; i++) {
            if ("horizontal".equals(direction)) {
                neiCol = col + neighbors[i];
            } else if ("vertical".equals(direction)) {
                neiRow = row + neighbors[i];
            }

            try {
                if (isOpen(neiRow, neiCol)) {
                    int siteNum = this.n * (row - 1) + col;
                    int neighborNum = this.n * (neiRow - 1) + neiCol;
                    grid.union(siteNum, neighborNum);
                    gridWithGround.union(siteNum, neighborNum);
                }
            } catch (IllegalArgumentException e) {
                // no union conducted
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkIndices(row, col);
        int siteNum = this.n * (row - 1) + col;

        if (!isOpen(row, col)) {
            siteStatus[siteNum] = true;

            connectNeighbors("horizontal", row, col);
            connectNeighbors("vertical", row, col);

            // check if this site is of the first/last row
            if (row == 1) {
                grid.union(siteNum, 0);
                gridWithGround.union(siteNum, 0);
            }
            if (row == this.n) {
                gridWithGround.union(siteNum, sitesTotal);
            }

            // update sitesOpened
            sitesOpened += 1;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkIndices(row, col);
        return siteStatus[this.n * (row - 1) + col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkIndices(row, col);
        return grid.find(0) == grid.find(this.n * (row - 1) + col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() { return sitesOpened; }

    // does the system percolate?
    public boolean percolates() { return gridWithGround.find(0) == gridWithGround.find(sitesTotal); }
}
