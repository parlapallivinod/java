package in.rgukt.r081247.java.datastructure.priorityqueue;

import in.rgukt.r081247.java.datastructure.list.LinkedPositionalList;
import in.rgukt.r081247.java.datastructure.list.PositionalList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** An implementation of a priority queue using an array-based heap. */
public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    /** Primary collection of priority queue entries */
    protected List<Entry<K, V>> heap = new ArrayList<>();

    /** Creates an empty priority queue based on the natural ordering of its keys. */
    public HeapPriorityQueue() {
        super();
    }
    /** Creates an empty priority queue using the given comparator to order keys. */
    public HeapPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    /** Creates a priority queue initialized with the given key-value pairs. */
    public HeapPriorityQueue(K[] keys, V[] values) {
        super();
        for (int j = 0; j < Math.min(keys.length, values.length); j++) {
            heap.add(new PQEntry<>(keys[j], values[j]));
        }
        heapify();
    }

    /** Performs a bottom-up construction of the heap in linear time. */
    protected void heapify() {
        int startIndex = parent(size() - 1);
        for (int j = startIndex; j >= 0; j--) {
            downheap(j);
        }
    }
    // protected utilities
    protected int parent(int j) {
        return (j - 1) / 2;
    }
    protected int left(int j) {
        return j * 2 + 1;
    }
    protected int right(int j) {
        return j * 2 + 2;
    }
    protected boolean hasLeft(int j) {
        return left(j) < heap.size();
    }
    protected boolean hasRight(int j) {
        return right(j) < heap.size();
    }

    /** Exchanges the entries at indices i and j of the array list. */
    protected void swap(int i, int j) {
        Entry<K, V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /** Moves the entry at index j higher, if necessary, to restore the heap property. */
    protected void upheap(int j) {
        while (j > 0) { // continue until reaching root (or break statement)
            int p = parent(j);
            if (compare(heap.get(j), heap.get(p)) >= 0) // heap property verified
                break;
            swap(j, p);
            j = p; // continue from the parent's location.
        }
    }

    /** Moves the entry at index j lower, if necessary, to restore the heap property. */
    protected void downheap(int j) {
        while (hasLeft(j)) { // continue to bottom (or break statement)
            int leftIndex = left(j);
            int smallChildIndex = leftIndex; // although right may be smaller

            if (hasRight(j)) {
                int rightIndex = right(j);
                if (compare(heap.get(rightIndex), heap.get(leftIndex)) < 0) {
                    smallChildIndex = rightIndex; // right child is smaller
                }
            }

            if (compare(heap.get(smallChildIndex), heap.get(j)) >= 0)
                break;

            swap(j, smallChildIndex);
            j = smallChildIndex;
        }
    }

    // public methods
    /** Returns the number of items in the priority queue. */
    public int size() {
        return heap.size();
    }

    /** Returns (but does not remove) an entry with minimal key (if any). */
    public Entry<K, V> min() {
        if (heap.isEmpty())
            return null;
        return heap.get(0);
    }

    /** Inserts a key-value pair and returns the entry created. */
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key); // auxiliary key-checking method (could throw exception)
        Entry<K, V> newest = new PQEntry<>(key, value);
        heap.add(newest); // add to the end of the list
        upheap(heap.size() - 1); // upheap newly added entry
        return newest;
    }

    /** Removes and returns an entry with minimal key (if any). */
    public Entry<K, V> removeMin() {
        if (heap.isEmpty())
            return null;
        Entry<K, V> answer = heap.get(0);
        swap(0, heap.size() - 1); // put minimum item at the end
        heap.remove(heap.size() - 1); // and remove it from the list;
        downheap(0); // then fix new root
        return answer;
    }

    @Override
    public String toString() {
        return "HPQ{" +
                "Q=" + heap +
                '}';
    }

    /** Sorts sequence s, using initially empty priority queue p to produce the order. */
    public static <E> void pqSort(PositionalList<E> s, PriorityQueue<E, ?> p) {
        int n = s.size();
        for (int j = 0; j < n; j++) {
            E element = s.remove(s.first());
            p.insert(element, null);
        }
        for (int j = 0; j < n; j++) {
            E element = p.removeMin().getKey();
            s.addLast(element);
        }
    }

    public static void main(String[] args) {

        /** Test 1
        PriorityQueue<Integer, String> pq = new HeapPriorityQueue<>();
        System.out.println(pq);

        System.out.println(pq);
        pq.insert(19940404, "Harish");

        System.out.println(pq);
        pq.insert(19931212, "Giri");

        System.out.println(pq);
        pq.insert(19930811, "Vinod");

        System.out.println(pq);
        pq.insert(19931016, "Hari");

        System.out.println(pq);
        pq.insert(19920101, "Gopi");

        System.out.println(pq);
        pq.insert(19740101, "Jaya");

        System.out.println(pq);
        pq.insert(19710101, "Sreenu");

        System.out.println(pq);
        pq.insert(19500101, "Venky");
        System.out.println(pq);

        pq.removeMin();
        System.out.println(pq);

        pq.removeMin();
        System.out.println(pq);

        pq.removeMin();
        System.out.println(pq);

        pq.removeMin();
        System.out.println(pq);

        pq.removeMin();
        System.out.println(pq);

        pq.removeMin();
        System.out.println(pq);

        pq.removeMin();
        System.out.println(pq);

        pq.removeMin();
        System.out.println(pq);
        */

        /** Test 2
        PriorityQueue<Integer, Integer> pq = new HeapPriorityQueue<>(
                new Integer[] {7, 6, 5, 4, 3, 2, 1}, new Integer[] {7, 6, 5, 4, 3, 2, 1});
        System.out.println(pq);
        */

        /** Test 3
        PositionalList<Integer> pl = new LinkedPositionalList<>();
        for (int i = 10; i >= 0; i--) {
            pl.addLast(i);
        }
        System.out.println(pl);
        PriorityQueue<Integer, ?> pq = new HeapPriorityQueue<>();
        HeapPriorityQueue.pqSort(pl, pq);
        System.out.println(pl);
        */

    }
}