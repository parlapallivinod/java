/**
 * @author Vinod Parlapalli
 * @since 2019/09/07
 */

package in.rgukt.r081247.java.datastructure.list;

public interface Position<E> {
    /**
     * Returns the element stored at this position.
     * @return the stored element
     * @throws IllegalStateException if position no longer valid
     */
    E getElement() throws IllegalStateException;
}
