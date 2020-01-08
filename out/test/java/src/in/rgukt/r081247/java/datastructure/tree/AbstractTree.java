/**
 * @author Vinod Parlapalli
 * created on 2019/09/21
 */
package in.rgukt.r081247.java.datastructure.tree;

import java.util.ArrayList;
import java.util.List;

/** An abstract base class providing some functionality of the Tree interface. */
public abstract class AbstractTree<E> implements Tree<E> {
    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }
    public boolean isExternal(Position<E> p) {
        return numChildren(p) == 0;
    }
    public boolean isRoot(Position<E> p) {
        return p == root();
    }
    public boolean isEmpty() {
        return size() == 0;
    }

    /** Returns the number of levels separating Position p from the root. */
    public int depth(Position<E> p) {
        if (p == root())
            return 0;
        else
            return 1 + depth(parent(p));
    }

    /** Returns the height of the tree. */
    public int height(Position<E> p) {
        int h = 0;
        for (Position<E> c: children(p))
            h = Math.max(h, 1 + height(c));
        return h;
    }

    /** Adds positions of the subtree rooted at Position p to the given snapshot. */
    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        snapshot.add(p); // for preorder, we add position p before exploring subtrees
        for (Position<E> c: children(p))
            preorderSubtree(c, snapshot);
    }

    /** Returns an iterable collection of positions of the tree, reported in preorder. */
    public Iterable<Position<E>> preorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty())
            preorderSubtree(root(), snapshot); // fill the snapshot recursively
        return snapshot;
    }
}
