import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        if (args.length != 1) { throw new IllegalArgumentException("Single integer arguments required"); }
        RandomizedQueue<String> client = new RandomizedQueue<>();
        int num = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            client.enqueue(StdIn.readString());
        }

        for (int i = 0; i < num; i++) {
            StdOut.println(client.dequeue());
        }
    }
}
