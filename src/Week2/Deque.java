package Week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;

    private class Node
    {
        Item item;
        Node next, before;
    }

    public Deque() {
    }

    public boolean isEmpty() {
        if (first == null && last == null) return true;
        else return false;
    }
    public int size() {
        int count = 0;
        Node var = first;
        while (var != null)
        {
            count++;
            var = var.next;
        }
        return count;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if (oldFirst != null) oldFirst.before = first;
        if (last == null) last = first;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.before = oldLast;
        if (oldLast != null)  oldLast.next = last;
        if (first == null) first = last;

    }
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        if (first == last) {
            first = null;
            last = null;
        }
        else {
            first = first.next;
        }
        return item;
    }
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        if (last == first) {
            last = null;
            first = null;
        }
        else {
            last = last.before;
            last.next = null;
        }
        return item;
    }
    public Iterator<Item> iterator() {

        return new Iterator<Item>() {
            Node firstTemp = first;

            @Override
            public boolean hasNext() {
                return firstTemp != null;
            }

            @Override
            public Item next() {
                if (firstTemp == null) throw new NoSuchElementException();
                Item item = firstTemp.item;
                firstTemp = firstTemp.next;
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

}
