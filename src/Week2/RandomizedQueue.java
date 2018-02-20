package Week2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node first, last;

    private class Node {
        Item item;
        Node next;
    }

    public RandomizedQueue() {
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        int count = 0;
        Node iter = first;
        while (iter != null) {
            count++;
            iter = iter.next;
        }
        return count;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) first = last;
        else oldLast.next = last;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
            Item item = first.item;
            first = first.next;
            if (isEmpty()) last = null;
            return item;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int random = StdRandom.uniform(size());
        if (random == 0) random++;
        Node node = first;
        int count = 0;
        while (node != null) {
            count++;
            if (count == random) return node.item;
            else node = node.next;
        }
       return null;
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            Node iter = first;

            @Override
            public boolean hasNext() {
                return iter != null;
            }

            @Override
            public Item next() {
                if (iter == null) throw new NoSuchElementException();
                Item item = iter.item;
                iter = iter.next;
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
