/**
 * @author Vinod Parlapalli
 * @since 2019/09/08
 */

package in.rgukt.r081247.java.datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of a positional list stored as a doubly linked list.
 * @param <E>
 */
public class LinkedPositionalList<E> implements PositionalList<E> {

    /**
     * nested Node class
     */
    private static class Node<E> implements Position<E> {
        private E element; // reference to the element stored at this node
        private Node<E> prev; // reference to the previous node in the list
        private Node<E> next;; // reference to the subsequent node in the list

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public E getElement() throws IllegalStateException {
            if (this.next == null) // convention for defunct node
                throw new IllegalStateException("Position no longer valid");
            return this.element;
        }
        public Node<E> getPrev() {
            return this.prev;
        }
        public Node<E> getNext() {
            return this.next;
        }
        public void setElement(E element) {
            this.element = element;
        }
        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

    // instance variables of the LinkedPositionalList
    private Node<E> header; // header sentinel
    private Node<E> trailer; // trailer sentinel
    private int size = 0; // number of elements in the list

    /** Constructs a new empty list. */
    public LinkedPositionalList() {
        header = new Node<>(null, null, null); // create header
        trailer = new Node<>(null, header, null); // trailer is preceded by header
        header.setNext(trailer); // header is followed by trailer
    }

    // private utilities
    /** Validates the position and returns it as a node.*/
    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (! (p instanceof Node))
            throw new IllegalArgumentException("Invalid p");
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getNext() == null) // convention for defunct node
            throw new IllegalArgumentException("p is no longer in the list");
        return node;
    }

    /** Returns the given node as a Position(or null, if it is a sentinel).*/
    private Position<E> position(Node<E> node) {
        if (node == header || node == trailer)
            return null; // do not expose user to the sentinels
        return node;
    }

    // public accessor methods
    //** Returns the number of elements in the linked list. */
    @Override
    public int size() {
        return size;
    }

    /** Tests whether the linked list is empty. */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the first Position in the linked list ( or null, if empty). */
    @Override
    public Position<E> first() {
        return position(header.getNext());
    }

    /** Returns the last Position in the linked list (or null, if empty). */
    @Override
    public Position<E> last() {
        return position(trailer.getPrev());
    }

    /** Returns the Position immediately before Position p (or null, if p is first). */
    @Override
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getPrev());
    }

    /** Returns the Position immediately after Position p (or null, if p is last). */
    @Override
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getNext());
    }

    // private utilities
    /** Adds element e to the linked list between the given nodes. */
    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
        Node<E> newest = new Node<>(e, pred, succ);
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;
    }

    // public update methods
    /** Inserts element e at the front of the linked list and returns its new Position. */
    @Override
    public Position<E> addFirst(E e) {
        return addBetween(e, header, header.getNext());
    }

    /** Inserts element e at the back of the linked list and returns its new Position. */
    @Override
    public Position<E> addLast(E e) {
        return addBetween(e, trailer.getPrev(), trailer);
    }

    /** Inserts element e immediately before Position p, and returns its new Position. */
    @Override
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }

    /** Inserts element e immediately after Position p, and returns its new Position. */
    @Override
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    /** Replaces the element stored at Position p and returns the replaced element. */
    @Override
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }

    /** Removes the element stored at Position p and returns it (invalidating p). */
    @Override
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> pred = node.getPrev();
        Node<E> succ = node.getNext();
        pred.setNext(succ);
        succ.setPrev(pred);
        size--;
        E answer = node.getElement();
        node.setPrev(null);
        node.setNext(null);
        node.setElement(null);
        return answer;
    }

    /** nested PositionIterator class */
    private class PositionalIterator implements Iterator<Position<E>> {
        private Position<E> cursor = first(); // position of the next element to report
        private Position<E> recent = null; // position of last reported element

        /** Tests whether the iterator has a next object. */
        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        /** Returns the next position in the iterator. */
        @Override
        public Position<E> next() throws NoSuchElementException {
            if (cursor == null)
                throw new NoSuchElementException("Nothing left");
            recent = cursor; // element at this position might later be removed
            cursor = after(cursor);
            return recent;
        }

        /** Removes the element returned by most recent call to next. */
        @Override
        public void remove() throws IllegalStateException {
            if (recent == null)
                throw new IllegalStateException("Nothing to remove");
            LinkedPositionalList.this.remove(recent); // remove from outer list
            recent = null; // do not allow remove again until next is called
        }
    }

    /** nested PositionIterable class */
    private class PositionIterable implements Iterable<Position<E>> {
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionalIterator();
        }
    }

    /** Returns an iterable representation of the list's positions. */
    public Iterable<Position<E>> positions() {
        return new PositionIterable();
    }

    // nested ElementIterator class
    /** This class adapts the iteration produced by positions() to return elements. */
    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = new PositionalIterator();

        @Override
        public boolean hasNext() {
            return posIterator.hasNext();
        }

        @Override
        public E next() {
            return posIterator.next().getElement(); // return element
        }

        @Override
        public void remove() {
            posIterator.remove();
        }
    }

    /** Returns an iterator of the elements stored in the list. */
    public Iterator<E> iterator() {
        return new ElementIterator();
    }
}
