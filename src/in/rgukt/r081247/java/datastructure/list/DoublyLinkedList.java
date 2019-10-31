/**
 * @author Vinod Parlapalli
 * @since 2019-08-30
 */

package in.rgukt.r081247.java.datastructure.list;

/**
 * A basic doubly linked list implementation.
 * @param <E>
 */
public class DoublyLinkedList<E> implements Dequeue<E> {

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        System.out.println(list);

        list.addFirst(1);
        System.out.println(list);

        list.addFirst(2);
        System.out.println(list);

        list.addFirst(3);
        System.out.println(list);

        list.addLast(4);
        System.out.println(list);

        list.addLast(5);
        System.out.println(list);

        list.addLast(6);
        System.out.println(list);

        list.removeFirst();
        System.out.println(list);

        list.removeLast();
        System.out.println(list);

        list.removeFirst();
        System.out.println(list);

        list.removeLast();
        System.out.println(list);


        list.removeFirst();
        System.out.println(list);

        list.removeLast();
        System.out.println(list);

        list.removeFirst();
        System.out.println(list);

        list.removeLast();
        System.out.println(list);


    }
    // nested Node class
    private static class Node<E> {
        private E element; // reference to the element stored at this node
        private Node<E> prev; // reference to the previous node in the list
        private Node<E> next; // reference to the subsequent node in the list

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
        public E getElement() {
            return element;
        }
        public Node<E> getPrev() {
            return prev;
        }
        public Node<E> getNext() {
            return next;
        }
        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

    // instance variables of the DoublyLinkedList
    private Node<E> header; // header sentinel
    private Node<E> trailer; // trailer sentinel
    private int size = 0; // number of elements in the list

    /**
     * Constructs a new empty list.
     */
    public DoublyLinkedList() {
        header = new Node<E>(null, null, null); // create header
        trailer = new Node<E>(null, header, null); // trailer is preceded by header
        header.setNext(trailer); // header is followed by trailer
    }

    /**
     * Returns the number of elements in the linked list.
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Tests whether the linked list is empty.
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns (but does not remove) the first element of the list
     * @return
     */
    public E first( ) {
        if (isEmpty())
            return null;
        return header.getNext().getElement(); // first element is beyond header
    }
    /**
     * Returns (but does not remove) the last element of the list
     * @return
     */
    public E last() {
        if (isEmpty())
            return null;
        return trailer.getPrev().getElement(); // last element is before trailer
    }

    /**
     * Adds element e to the front of the list.
     * @param element
     */
    public void addFirst(E element) {
        addBetween(element, header, header.getNext()); // place just after the header
    }

    /**
     * Adds element e to the end of the list.
     * @param element
     */
    public void addLast(E element) {
        addBetween(element, trailer.getPrev(), trailer); // place just before the trailer
    }

    /**
     * Removes and returns the first element of the list
     * @return
     */
    public E removeFirst() {
        if (isEmpty()) // nothing to remove
            return null;
        return remove(header.getNext()); // first element is beyond header
    }

    /**
     * Removes and returns the last element of the list
     * @return
     */
    public E removeLast() {
        if (isEmpty()) // nothing to remove
            return null;
        return remove(trailer.getPrev()); // last element is before trailer
    }

    /**
     * Adds element element to the linked list in between the given nodes.
     * @param element
     * @param predecessor
     * @param successor
     */
    private void addBetween(E element, Node<E> predecessor, Node<E> successor) {
        // Create and link a new node
        Node<E> newest = new Node<>(element, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }

    /**
     * Removes the given node from the list and returns its element.
     */
    private E remove(Node<E> node) {
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getElement();
    }

    /**
     * This method will return string equivalent of this object
     * @return String
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> node = header.getNext();
        while (node != trailer) {
            sb.append(node.getElement().toString() + ", ");
            node = node.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}
