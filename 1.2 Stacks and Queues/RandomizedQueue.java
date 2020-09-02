import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;
import java.util.Iterator;


public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] randQueue;
    private int size = 0;
    private int capacity = 1;

    // construct an empty randomized queue
    public RandomizedQueue() { randQueue = (Item[]) new Object[capacity]; }

    // is the randomized queue empty?
    public boolean isEmpty() { return size == 0; }

    // return the number of items on the randomized queue
    public int size() { return this.size; }

    // private method for resizing
    private void resize() {
        Item[] newRandQueue = (Item[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            newRandQueue[i] = randQueue[i];
        }

        randQueue = newRandQueue;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("single argument required");
        }

        // resize the array when it's full
        if (size == capacity) {
            capacity = capacity * 2;
            resize();
        }

        randQueue[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) { throw new NoSuchElementException("randomized queue is empty"); }

        // resize the array when size reaches one quarter of capacity
        if (size == capacity / 4) {
            capacity = capacity / 2;
            resize();
        }

        int index = StdRandom.uniform(size);
        Item removedItem = randQueue[index];

        // replace the removed item with the last item and delete last item
        randQueue[index] = randQueue[--size];
        randQueue[size] = null;
        return removedItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) { throw new NoSuchElementException("randomized queue is empty"); }
        int index = StdRandom.uniform(size);
        return randQueue[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() { return new RandomIterator(); }

    private class RandomIterator implements Iterator<Item> {
        private int[] orders = new int[size];
        private int current = 0;

        public RandomIterator() {
            for (int i = 0; i < size; i++) {
                orders[i] = i;
            }
            // shuffle all elements in orders to iterate items in random order
            StdRandom.shuffle(orders);
        }

        public boolean hasNext() { return current != size; }
        public void remove() { throw new UnsupportedOperationException("Not supported"); }
        public Item next() {
            if (current == size) { throw new NoSuchElementException("no item remaining"); }
            return randQueue[orders[current++]];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> tRQ = new RandomizedQueue<>();
        StdOut.println(tRQ.isEmpty());

        for (int i = 0; i < args.length; i++) {
            tRQ.enqueue(args[i]);
        }

        StdOut.println(tRQ.dequeue());
        StdOut.println(tRQ.size());
        StdOut.println(tRQ.sample());

        for (String s: tRQ) {
            StdOut.println(s);
        }
    }
}
