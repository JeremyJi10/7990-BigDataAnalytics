import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;
import java.util.Iterator;


public class Deque<Item> implements Iterable<Item> {

    private int size = 0;
    private Node deque;
    private Node last;

    private class Node {
        Item item;
        Node prev;
        Node next;
    }

    // construct an empty deque
    public Deque() { deque = new Node(); }

    // is the deque empty?
    public boolean isEmpty() { return size == 0; }

    // return the number of items on the deque
    public int size() { return this.size; }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) { throw new IllegalArgumentException("single argument required"); }

        if (size == 0) {
            deque.item = item;
            last = deque;
        } else {
            Node newFirst = new Node();
            newFirst.item = item;
            newFirst.next = deque;
            deque.prev = newFirst;
            deque = newFirst;
        }

        size += 1;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) { throw new IllegalArgumentException("single argument required"); }

        if (size == 0) {
            deque.item = item;
            last = deque;
        } else {
            Node newLast = new Node();
            newLast.item = item;
            newLast.prev = last;
            last.next = newLast;
            last = newLast;
        }

        size += 1;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("no item remaining");
        }

        Item removed = deque.item;

        if (size == 1) {
            deque.item = null;
            last = null;
        } else {
            deque = deque.next;
            deque.prev = null;
        }

        size -= 1;
        return removed;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) { throw new NoSuchElementException("no item remaining"); }

        Item removed = last.item;

        if (size == 1) {
            deque.item = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }

        size -= 1;
        return removed;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() { return new ListIterator(); }

    private class ListIterator implements Iterator<Item> {
        private Node current = deque;

        public boolean hasNext() {
            // special check: 'Node current' isn't null when deque is empty (Node@address)
            if (size == 0) {
                return false;
            } else {
                return current != null;
            }
        }
        public void remove() { throw new UnsupportedOperationException("Not supported"); }
        public Item next() {
            if (current == null) { throw new NoSuchElementException("no item remaining"); }

            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> dequeT = new Deque<String>();
        StdOut.println(dequeT.isEmpty());
        for (int i = 0; i < args.length; i++) {
            dequeT.addFirst(args[i]);
        }

        dequeT.removeFirst();
        dequeT.addLast("Last");
        dequeT.removeLast();

        StdOut.println(dequeT.size);

        for (String s : dequeT) {
            StdOut.println(s);
        }
    }
}
