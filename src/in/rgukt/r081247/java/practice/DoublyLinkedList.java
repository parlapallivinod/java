package in.rgukt.r081247.java.practice;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class DoublyLinkedList<E> implements Iterable<E>{

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

        Iterator<Integer> itr = list.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

    }

    private static class Node<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

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

    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;

    public DoublyLinkedList() {
        header = new Node<E>(null, null, null);
        trailer = new Node<E>(null, header, null);
        header.setNext(trailer);
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public E first( ) {
        if (isEmpty())
            return null;
        return header.getNext().getElement();
    }
    public E last() {
        if (isEmpty())
            return null;
        return trailer.getPrev().getElement();
    }
    public void addFirst(E element) {
        addBetween(element, header, header.getNext());
    }
    public void addLast(E element) {
        addBetween(element, trailer.getPrev(), trailer);
    }
    public E removeFirst() {
        if (isEmpty())
            return null;
        return remove(header.getNext());
    }
    public E removeLast() {
        if (isEmpty())
            return null;
        return remove(trailer.getPrev());
    }
    private void addBetween(E element, Node<E> predecessor, Node<E> successor) {
        Node<E> newest = new Node<>(element, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }
    private E remove(Node<E> node) {
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getElement();
    }

    public String toString() {
        String str = "[";
        Node<E> node = header.getNext();
        while (node != trailer) {
            str = str + node.getElement().toString() + ", ";
            node = node.getNext();
        }
        str = str + "]";
        return str;
    }


    @Override
    public Iterator<E> iterator() {
        return new LinkedIterator<>(this);
    }

    @Override
    public void forEach(Consumer<? super E> consumer) {

    }

    @Override
    public Spliterator<E> spliterator() {
        return null;
    }


    private static class LinkedIterator<T> implements Iterator<T> {
        Node<T> header;
        Node<T> trailer;
        Node<T> node;
        public LinkedIterator(DoublyLinkedList list) {
            this.header = this.node = list.header;
            this.trailer = list.trailer;
        }
        @Override
        public boolean hasNext() {
            return node.getNext() != trailer;
        }

        @Override
        public T next() {
            node = node.getNext();
            return node.getElement();
        }

        @Override
        public void remove() {

        }

        @Override
        public void forEachRemaining(Consumer<? super T> consumer) {

        }
    }

}
