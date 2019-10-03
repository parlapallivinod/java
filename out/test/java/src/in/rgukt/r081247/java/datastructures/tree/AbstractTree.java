/**
 * @author Vinod Parlapalli
 * created on 2019/09/21
 */
package in.rgukt.r081247.java.datastructures.tree;

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

    public int depth(Position<E> p) {
        if (p == root())
            return 0;
        else
            return 1 + depth(parent(p));
    }

    public int height(Position<E> p) {
        int h = 0;
        for (Position<E> c: children(p))
            h = Math.max(h, 1 + height(c));
        return h;
    }

    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        snapshot.add(p);
        for (Position<E> c: children(p))
            preorderSubtree(c, snapshot);
    }

    public Iterable<Position<E>> preorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty())
            preorderSubtree(root(), snapshot);
        return snapshot;
    }
}
