/**
 * @author Vinod Parlapalli
 * created on 2019/09/29
 */
package in.rgukt.r081247.java.datastructures.tree;

/** An interface for a binary tree, in which each node has at most two children. */
public interface BinaryTree<E> extends Tree<E> {
    /** Returns the Position of p's left child (or null if no child exists). */
    Position<E> left(Position<E> p) throws IllegalArgumentException;
    /** Returns the Position of p's right child (or null if no child exists). */
    Position<E> right(Position<E> p) throws IllegalArgumentException;
    /** Returns the Position of p's sibling (or null if no sibling **/
    Position<E> sibling(Position<E> p) throws IllegalArgumentException;
}
