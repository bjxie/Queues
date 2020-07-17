import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

// creates a randomized queue where items can be deleted at uniform random
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size = 0;

    private class Node {
        Item val;
        Node next;
        Node prev;
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        first = null;
        last = null;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return first == null;
    }


    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node newLast = new Node();
        newLast.val = item;
        if (isEmpty()) {
            first = newLast;
            last = newLast;
        } else {
            last.next = newLast;
            newLast.prev = last;
            last = newLast;
        }
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int rand = StdRandom.uniform(size()) + 1;
        int org = 1;
        Node track = first;
        Item res;
        if (rand == 1) { // if deleting the first item
            if (first == last) { //return the first value and free the nodes for garbage collector
                res = track.val;
                first = first.next;
                last = first;
            } else { // return the first value and set the initial first to null
                res = track.val;
                first = first.next;
                first.prev = null;
            }
        } else {
            while (org < rand) {
                track = track.next; // move the tracking node until it reaches the node to remove
                org++;
            }
            // get the value and remove the node for garbage collector
            res = track.val;
            track.prev.next = track.next;
            if (track.next == null) {
                last = track.prev;
            } else {
                track.next.prev = track.prev;
            }
        }
        size--;
        return res;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int rand = StdRandom.uniform(size()) + 1;
        int org = 2;
        Node track = first;
        Item res;
        if (rand == 1) {
            res = track.val;
        } else {
            while (org < rand) {
                track = track.next;
                org++;
            }
            res = track.next.val;
        }
        return res;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomListIterator();
    }

    private class RandomListIterator implements Iterator<Item> {
        int[] ranArr;
        int arrPos = 0;

        private RandomListIterator() {
            ranArr = new int[size()];
            for (int i = 0; i < ranArr.length; i++) {
                ranArr[i] = i + 1;
            }
            StdRandom.shuffle(ranArr);
        }


        public boolean hasNext() {
            return arrPos < ranArr.length;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node track = first;
            for (int i = 1; i < ranArr[arrPos]; i++) {
                track = track.next;
            }
            arrPos++;
            return track.val;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        int n = 5;
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);
        for (int a : queue) {
            for (int b : queue)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }
    }

}
