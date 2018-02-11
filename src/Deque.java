import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private Node reverseFirst, reverseLast;
    private Node iter;

    private class Node
    {
        public Item item;
        Node nextFirst, nextLast;
        Node next;

        @Override
        public String toString() {
            return (String) item;
        }
    }

    public Deque() {
    }

    public boolean isEmpty() {
        return first == null && last == null;
    }
    public int size() {
        int count = 0;
        Node var = iter;
        while(var != null)
        {
            count++;
            var = var.next;
        }
        return count;
    }
    public void addFirst(Item item) {
        if(item == null) throw new IllegalArgumentException();
        Node oldFirst = first;
        first = new Node();
        iter = first;
        first.item = item;
        first.nextFirst = oldFirst;
        if(first.nextFirst == null) {
            first.next = reverseLast;
            reverseFirst = first;
        }
        else {
            first.next = first.nextFirst;
        }
    }
    public void addLast(Item item) {
        if(item == null) throw new IllegalArgumentException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.nextLast = oldLast;
        if(last.nextLast == null) {
            reverseLast = last;
            if(reverseFirst != null) {
                reverseFirst.next = reverseLast;
            }
            else {
                iter = reverseLast;
            }
        }
        else {
            Node temp = last.nextLast;
            temp.next = last;
        }
    }
    public Item removeFirst() {
        if(isEmpty()) throw new NoSuchElementException();
        if(first == null) {
            return null;
        }
        else {
            Item item = first.item;
            first = first.nextFirst;
            iter = first;
            if (first == null) {
                reverseFirst = null;
                iter = reverseLast;
            }
            return item;
        }
    }
    public Item removeLast() {
        if(isEmpty()) throw new NoSuchElementException();
        if(last == null) {
            return null;
        }
        else {
            Item item = last.item;
            last = last.nextLast;

            if (last != null) {
                last.next = null;
            } else {
                reverseLast = null;
                reverseFirst.next = null;
            }
            return item;
        }
    }
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            Node firstTemp = iter;

            @Override
            public boolean hasNext() {
                return firstTemp != null;
            }

            @Override
            public Item next() {
                if(firstTemp == null) throw new NoSuchElementException();
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

    public void check() {

        Node tempLast = iter;
        while (tempLast != null) {
            System.out.println(tempLast);
            tempLast = tempLast.next;
        }

    }
    public static void main(String[] args) {
      /*  Deque<String> deque = new Deque<>();;
        deque.addFirst("A");
        deque.addFirst("B");
        deque.removeFirst();
        deque.addLast("C");
        deque.addLast("D");
        deque.addLast("E");
        deque.removeLast();
        for(String s : deque) {
            System.out.println(s);
        }
        System.out.println(deque.size());
*/
    }
}
