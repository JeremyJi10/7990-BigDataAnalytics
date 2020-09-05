## Part 1, Week 2 - [Queues](https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php) 
### Dequeue
A deque (pronounced “deck”) is a generalization of a stack and a queue that supports adding and removing items from either the front or the back of the data structure, 
this implementation used a **doubly linked list** approach to satisfy the performance requirements.\
\
(***Constant worst-case time** for each deque and iterator operation; **48n + 192 bytes of memory usage** for a deque containing n items at most.*) 
### Randomized queue
A randomized queue is similar to a stack or queue, except that the item removed is chosen uniformly at random among items in the data structure,
this implementation used a **resizing array** approach to satisfy the performance requirements.\
\
(***Constant amortized time** for each randomized queue operation (besides creating an iterator); 
**48n + 192 bytes of memory usage** for a randomized queue containing n items at most;
**constant worst-case time** for* `hasNext()` *and* `next()`*; **linear time** for iterator construction.*)
