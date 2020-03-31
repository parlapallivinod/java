/**
 * @author Vinod Parlapalli
 * @since 2020-02-16
 */

package in.rgukt.r081247.java.datastructure.map;

import in.rgukt.r081247.java.datastructure.priorityqueue.DefaultComparator;

import java.util.Comparator;

public abstract class AbstractSortedMap<K, V>
        extends AbstractMap<K, V> implements SortedMap<K, V>{

    /** The comparator defining the ordering of keys in the map. */
    private Comparator<K> comp;

    /**
     * Initializes the comparator for the map.
     * @param comp
     */
    protected AbstractSortedMap(Comparator<K> comp) {
        this.comp = comp;
    }

    /** Initializes the map with a default comparator. */
    protected AbstractSortedMap() {
        this(new DefaultComparator<K>()); // default comparator uses natural ordering
    }

    /** Method for comparing two entries according to key */
    protected int compare(Entry<K, V> a, Entry<K, V> b) {
        return comp.compare(a.getKey(), b.getKey());
    }

    /** Method for comparing a key and an entry's key */
    protected int compare(K a, Entry<K, V> b) {
        return comp.compare(a, b.getKey());
    }

    /** Method for comparing a key and an entry's key */
    protected  int compare(Entry<K, V> a, K b) {
        return comp.compare(a.getKey(), b);
    }

    /** Method for comparing two keys */
    protected int compare(K a, K b) {
        return comp.compare(a, b);
    }

    /** Determines whether a key is valid. */
    protected  boolean checkKey(K key) throws IllegalArgumentException {
        try {
            return (comp.compare(key, key) == 0); // see if key can be compared to itself
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Incompatible key");
        }
    }
}

