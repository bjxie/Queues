import java.util.Iterator;
import java.util.NoSuchElementException;
// double-ended queue that is a generalization of a stack and a queue
// supports adding and removing items from either the front or the back of the data structure.

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size = 0;

    private class Node {
        Item val;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node newFirst = new Node();
        newFirst.val = item;
        if (isEmpty()) {
            first = newFirst;
            last = newFirst;
        } else {
            newFirst.next = first;
            first.prev = newFirst;
            first = newFirst;
        }
        size++;
    }


    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node newLast = new Node();
        newLast.val = item;
        if (isEmpty()) {
            first = newLast;
            last = newLast;
        } else {
            newLast.prev = last;
            last.next = newLast;
            last = newLast;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item firstItem;
        if (first == last) {
            firstItem = first.val;
            first = first.next;
            last = first;
        } else {
            firstItem = first.val;
            first = first.next;
            first.prev = null;
        }
        size--;
        return firstItem;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item lastItem;
        if (first == last) {
            lastItem = last.val;
            last = last.next;
            first = last;
        } else {
            lastItem = last.val;
            last.prev.next = null;
            last = last.prev;
        }
        size--;
        return lastItem;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item res = current.val;
            current = current.next;
            return res;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    // test client
    public static void main(String[] args) {
        Deque<Object> test = new Deque<>();
        System.out.println("is empty?: " + test.isEmpty());
        test.addFirst(3);
        System.out.println("first val: " + test.first.val);
        System.out.println("is empty?: " + test.isEmpty());
        System.out.println("list size: " + test.size());
        System.out.println("first val:  " + test.first.val);
        System.out.println("last val:  " + test.last.val);

        System.out.println("last val: " + test.removeLast());
        System.out.println("list size: " + test.size());

    }

}
