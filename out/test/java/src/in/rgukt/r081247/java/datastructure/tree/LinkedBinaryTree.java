/**
 * @author Vinod Parlapalli
 * Created on 2019/09/29
 */
package in.rgukt.r081247.java.datastructure.tree;

import java.util.*;

/**
 * Concrete implementation of a binary tree using a node-based, linked structure.
 * @param <E>
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    public static void main(String[] args) {
        LinkedBinaryTree<String> fib = new LinkedBinaryTree<>();

        Position<String> fib0 = fib.addRoot("f(4)");

        Position<String> fib1 = fib.addLeft(fib0, "f(3)");
        Position<String> fib2 = fib.addRight(fib0, "f(2)");

        Position<String> fib3 = fib.addLeft(fib1, "f(2)");
        Position<String> fib4 = fib.addRight(fib1, "f(1)");
        Position<String> fib5 = fib.addLeft(fib2, "f(1)");
        Position<String> fib6 = fib.addRight(fib2, "f(0)");

        Position<String> fib7 = fib.addLeft(fib3, "f(1)");
        Position<String> fib8 = fib.addRight(fib3, "f(0)");

        fib.print(fib.validate(fib0), 0);
        fib.printBFS(fib.validate(fib0));
    }

    /** nested Node class */
    protected static class Node<E> implements Position<E> {
        private E element; // an element stored at this node
        private Node<E> parent; // a reference to the parent node (if any)
        private Node<E> left; // a reference to the left child (if any)
        private Node<E> right; // a reference to the right child (if any)

        /** Constructs a node with given element and neighbors. */
        public Node(E element, Node<E> parent, Node<E> left, Node<E> right) {
            this.element = element;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        @Override
        public E getElement(){
            return element;
        }
        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }
    }

    /** Factory function to create a new node storing element e. */
    protected Node<E> createNode(E element, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(element, parent, left, right);
    }

    // LinkedBinaryTree instance variables
    protected Node<E> root = null; // root of the tree
    private int size; // number of nodes in the tree

    // constructor
    public LinkedBinaryTree() {
    }

    /**
     * nonpublic utility
     * Validates the position and returns it as a node.
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (! (p instanceof Node))
            throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    /** Returns the Position of p's left child (or null if no child exists). */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getLeft();
    }

    /** Returns the Position of p's right child (or null if no child exists). */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getParent();
    }

    /** Returns the root Position of the tree (or null if tree is empty). */
    @Override
    public Position<E> root() {
        return root;
    }

    /** Returns the Position of p's parent (or null if p is root). */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getParent();
    }

    /** Returns the number of nodes in the tree. */
    @Override
    public int size() {
        return size;
    }

    /** Places element e at the root of an empty tree and returns its new Position. */
    public Position<E> addRoot(E element) throws IllegalStateException {
        if (!isEmpty())
            throw new IllegalStateException("Tree is not empty");
        root = createNode(element, null, null, null);
        size = 1;
        return root;
    }

    /** Creates a new left child of Position p storing element e; returns its Position. */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getLeft() != null)
            throw new IllegalArgumentException("p already has a left child");
        Node<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    /** Creates a new right child of Position p storing element e; returns its Position. */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getRight() != null)
            throw new IllegalArgumentException("p already has a right child");
        Node<E> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    /** Places the element at Position p with e and returns the replaced element. */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    /** Attaches tree t1 and t2 as left and right subtrees of external p. */
    public void attach(Position<E> p,
                       LinkedBinaryTree<E> t1,
                       LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (isInternal(node))
            throw new IllegalArgumentException("p must be a leaf");
        if (! t1.isEmpty()) {
            node.setLeft(t1.root);
            t1.root.setParent(node);
            t1.root = null;
            t1.size = 0;
        }

        if (! t2.isEmpty()) {
            node.setRight(t2.root);
            t2.root.setParent(node);
            t2.root = null;
            t2.size = 0;
        }
    }

    /** Removes the node at Position p and replaces it with its child, if any. */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (numChildren(p) == 2)
            throw new IllegalArgumentException("p has two children");
        Node<E> child = (node.getLeft() != null) ? node.getLeft() : node.getRight();
        if (child != null)
            child.setParent(node.getParent());
        if (node == root) {
            root = child;
        } else {
            Node<E> parent = node.getParent();
            if (parent.getLeft() == node)
                parent.setLeft(child);
            else
                parent.setRight(child);
        }

        size--;
        E temp = node.getElement();
        node.setElement(null);
        node.setParent(node);
        node.setLeft(null);
        node.setRight(null);
        return temp;
    }

    /** This class adapts the iteration produced by positions() to return elements. */
    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = positions().iterator();

        @Override
        public boolean hasNext() {
            return posIterator.hasNext();
        }

        @Override
        public E next() {
            return posIterator.next().getElement();
        }

        @Override
        public void remove() {
            posIterator.remove();
        }
    }

    /** Returns an iterator of the elements stored in the tree. */
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    /** Overrides positions to make inorder the default order for binary tree. */
    @Override
    public Iterable<Position<E>> positions() {
        return preorder();
    }


    public void print(Node<E> node, int level) {
        if (node == null)
            return;

        print(node.getRight(), level + 1);

        for (int i = 0; i <= level; i++) {
            System.out.print("\t");
        }
        System.out.println(node.getElement());

        print(node.getLeft(), level + 1);

    }

  

    public void printBFS(Node<E> node) {
        Deque<Node<E>> parents = new LinkedList<Node<E>>();
        parents.add(node);

        Deque<Node<E>> children = new LinkedList<Node<E>>();
        long height = -1;
        while (! parents.isEmpty()) {
            height++;
            for (Node<E> n : parents) {
                long prevElements = 0;
                System.out.printf("%s", n.getElement());
                if (n.getRight() != null)
                    children.add(n.getRight());
                if (n.getLeft() != null)
                    children.add(n.getLeft());
            }
            System.out.println("");
            parents = children;
            children = new LinkedList<>();
        }
        System.out.println("Tree height: " + height);
    }
}
