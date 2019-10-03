/**
 * @author Vinod Parlapalli
 * created on 2019/09/21
 */
package in.rgukt.r081247.java.datastructures.tree;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {

    public Position<E> sibling(Position<E> p) throws IllegalArgumentException {
        Position<E> parent = parent(p);
        if (parent == null)
            return null;
        if (p == left(parent))
            return right(parent);
        else
            return left(parent);
    }

    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        List<Position<E>> snapshot = new ArrayList<>(2);
        if (left(p) != null)
            snapshot.add(left(p));
        if (right(p) != null)
            snapshot.add(right(p));
        return snapshot;
    }

    public int numChildren(Position<E> p) throws IllegalArgumentException {
        int count = 0;
        if (left(p) != null)
            count++;
        if (right(p) != null)
            count++;
        return count;
    }

    private void inorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        if (left(p) != null)
            inorderSubtree(left(p), snapshot);
        snapshot.add(p);
        if (right(p) != null)
            inorderSubtree(right(p), snapshot);
    }

    public Iterable<Position<E>> inorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty())
            inorderSubtree(root(), snapshot);
        return snapshot;
    }

    public Iterable<Position<E>> positions() {
        return inorder();
    }
}
