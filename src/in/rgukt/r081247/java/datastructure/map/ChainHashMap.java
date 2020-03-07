/**
 * @author Vinod Parlapalli
 * @since 2020/02/11
 */

package in.rgukt.r081247.java.datastructure.map;

import java.util.ArrayList;

public class ChainHashMap<K, V> extends AbstractHashMap<K, V> {
    // a fixed capacity array of UnsortedTableMaps that serve as buckets
    private UnsortedTableMap<K, V>[] table; // initialized within createTable

    public ChainHashMap() {
        super();
    }

    public ChainHashMap(int capacity) {
        super(capacity);
    }

    public ChainHashMap(int capacity, int prime) {
        super(capacity, prime);
    }

    /** Creates an empty table having length equal to current capacity. */
    @Override
    protected void createTable() {
        table = (UnsortedTableMap<K, V>[]) new UnsortedTableMap[capacity];
    }

    /** Returns value associated with key key in bucket with hash value hash, or else null. */
    @Override
    protected V bucketGet(int hash, K key) {
        UnsortedTableMap<K, V> bucket = table[hash];
        if (bucket == null)
            return null;
        return bucket.get(key);
    }

    /** Associates key key with value value in bucket with hash value hash; returns old value. */
    @Override
    protected V bucketPut(int hash, K key, V value) {
        UnsortedTableMap<K, V> bucket = table[hash];
        if (bucket == null)
            bucket = table[hash] = new UnsortedTableMap<>();
        int oldSize = bucket.size();
        V answer = bucket.put(key, value);
        n += (bucket.size() - oldSize); // size may have increased
        return answer;
    }

    /** Removes entry having key key from bucket with hash value hash (if any). */
    @Override
    protected V bucketRemove(int hash, K key) {
        UnsortedTableMap<K, V> bucket = table[hash];
        if (bucket == null)
            return null;
        int oldSize = bucket.size();
        V answer = bucket.remove(key);
        n -= (oldSize - bucket.size()); // size may have decreased
        return answer;
    }

    /** Returns an iterable collection of all key-value entries of the map. */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        for (int h = 0; h < capacity; h++) {
            if (table[h] != null) {
                for (Entry<K, V> entry: table[h].entrySet()) {
                    buffer.add(entry);
                }
            }
        }
        return buffer;
    }
}
