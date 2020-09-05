## Part 1, Week 1 - [Percolation](https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php) 
## Percolation
Model a percolation system using an n-by-n grid of sites. Each site is either open or blocked. 
A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites. 
We say the system percolates if there is a full site in the bottom row. In other words, a system percolates if we fill all open sites connected to the top row 
and that process fills some open site on the bottom row.

For the insulating/metallic materials example, the open sites correspond to metallic materials, 
so that a system that percolates has a metallic path from top to bottom, with full sites conducting. 
For the porous substance example, the open sites correspond to empty space through which water might flow, 
so that a system that percolates lets water fill open sites, flowing from top to bottom.

### Percolation data type
This implementation imported the weighted quick union algorithm in class 
[WeightedQuickUnionUF](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/WeightedQuickUnionUF.html) to satisfy the performance requirements.

(*The constructor must take time proportional to n^2; all instance methods must take constant time plus a constant number of calls to* `union()` *and* `find()`.)

### PercolationStats
A data type used to perform a series of computational experiments to estimate the percolation threshold (Monte Carlo simulation).
