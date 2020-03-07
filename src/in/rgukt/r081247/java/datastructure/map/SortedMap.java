/**
 * @author Vinod Parlapalli
 * @since 2020-03-07
 */

package in.rgukt.r081247.java.datastructure.map;

public interface SortedMap<K, V> extends Map<K, V> {
    /**
     * Returns the entry having the least key (or null if map is empty).
     * @return
     */
    Entry<K, V> firstEntry();

    /**
     * Returns the entry having the greatest key (or null if map is empty).
     * @return
     */
    Entry<K, V> lastEntry();

    /**
     * Returns the entry with least key greater than or equal to given key
     * (or null if no such key exists).
     * @param key
     * @return
     * @throws IllegalArgumentException
     */
    Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException;

    /**
     * Returns the entry with greatest key less than or equal to given key
     * (or null if no such key exists).
     * @param key
     * @return
     * @throws IllegalArgumentException
     */
    Entry<K, V> floorEntry(K key) throws IllegalArgumentException;

    /**
     * Returns the entry with greatest key strictly less than given key
     * (or null if no such key exists).
     * @param key
     * @return
     * @throws IllegalArgumentException
     */
    Entry<K, V> lowerEntry(K key) throws IllegalArgumentException;

    /**
     * Returns the entry with least key strictly greater than given key
     * (or null if no such key exists).
     * @param key
     * @return
     * @throws IllegalArgumentException
     */
    Entry<K, V> higherEntry(K key) throws IllegalArgumentException;

    /**
     * Returns an iterable containing all keys in the range from
     *  fromKey inclusive to toKey exclusive
     * @param fromKey
     * @param toKey
     * @return
     * @throws IllegalArgumentException
     */
    Iterable<Entry<K, V>> subMap(K fromKey, K toKey) throws IllegalArgumentException;
}
