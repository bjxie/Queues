import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    // takes an integer k as a command-line argument;
    // reads a sequence of strings from standard input using StdIn.readString();
    // prints exactly k of them, uniformly at random.
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        try {
            int k = Integer.parseInt(args[0]);
            while (!StdIn.isEmpty()) {
                String r = StdIn.readString();
                queue.enqueue(r);
            }

            for (int j = 0; j < k; j++) {
                System.out.println(queue.dequeue());
            }
        } catch (NumberFormatException e) {
            String r = StdIn.readString();
            System.out.print(r);
        }
    }
}

