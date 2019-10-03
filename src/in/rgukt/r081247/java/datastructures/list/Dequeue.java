/**
 * @author Vinod Parlapalli
 * @since 2019-08-30
 *
 */

package in.rgukt.r081247.java.datastructures.list;

/**
 * Interface for a double-ended queue: a collection of elements that can be inserted
 * and removed at both ends
 * @param <E>
 */
public interface Dequeue<E> {
    /**
     * Returns the number of elements in the deque.
     * @return
     */
    int size();
    /**
     * Tests whether the deque is empty.
     */
    boolean isEmpty();

    /**
     * Returns, but does not remove, the first element of the deque (null if empty).
     * @return
     */
    E first();
    /**
     * Returns, but does not remove, the last element of the deque (null if empty).
     * @return
     */
    E last();

    /**
     * Inserts an element at the front of the deque.
     * @param e
     */
    void addFirst(E e);

    /**
     * Inserts an element at the back of the deque.
     * @param e
     */
    void addLast(E e);

    /**
     * Removes and returns the first element of the deque (null if empty).
     * @return
     */
    E removeFirst();

    /**
     * Removes and returns the last element of the deque (null if empty)
     * @return
     */
    E removeLast();
}
