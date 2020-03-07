/**
 * @author Vinod Parlapalli
 * @since 2019/12/11
 */

package in.rgukt.r081247.java.datastructure.map;

public interface Map<K, V> {
    int size();
    boolean isEmpty();
    V get(K key);
    V put(K key, V value);
    V remove(K key);
    Iterable<K> keySet();
    Iterable<V> values();
    Iterable<Entry<K, V>> entrySet();
}
