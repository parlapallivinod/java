package in.rgukt.r081247.java.datastructure.priorityqueue;

public interface AdaptablePriorityQueue<K, V> {
    public void remove(Entry<K, V> entry) throws IllegalArgumentException;
    public void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException;
    public void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException;
}
