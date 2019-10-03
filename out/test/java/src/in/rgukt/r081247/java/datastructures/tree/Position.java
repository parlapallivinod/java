/**
 * @author Vinod Parlapalli
 * created on 2019/09/21
 */

package in.rgukt.r081247.java.datastructures.tree;

/**
 * Returns the element stored at this position.
 * @return the stored element
 * @throws IllegalStateException if position no longer valid
 */
public interface Position<E> {
    E getElement() throws IllegalStateException;
}
