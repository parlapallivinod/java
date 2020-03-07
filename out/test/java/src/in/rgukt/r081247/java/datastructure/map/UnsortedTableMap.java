/**
 * @author Vinod Parlapalli
 * @since 2020/01/01
 */

package in.rgukt.r081247.java.datastructure.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnsortedTableMap<K, V> extends AbstractMap<K, V> {
    /** Underlying storage for the map of entries. */
    private ArrayList<MapEntry<K, V>> table = new ArrayList<>();

    /** Constructs an initially empty map. */
    public UnsortedTableMap() {
    }

    // private utility
    /** Returns the index of an entry with equal key, or -1 if none found. */
    private int findIndex(K key) {
        int n = table.size();
        for (int j = 0; j < n; j++) {
            if (table.get(j).getKey().equals(key))
                return j;
        }
        return -1;
    }

    /** Returns the number of entries in the map. */
    public int size() {
        return table.size();
    }

    /** Returns the value associated with the specified key (or else null). */
    public V get(K key) {
        int j = findIndex(key);
        if (j == -1)
            return null;
        return table.get(j).getValue();
    }

    /** Associates given value with given key, replacing a previous value (if any). */
    public V put(K key, V value) {
        int j = findIndex(key);
        if (j == -1) {
            table.add(new MapEntry<>(key, value));
            return null;
        } else {
            return table.get(j).setValue(value);
        }

    }

    /** Removes the entry with the specified key (if any) and returns its value. */
    public V remove(K key) {
        int j = findIndex(key);
        int n = size();
        if (j == -1)
            return null;
        V value = table.get(j).getValue();
        if (j != n - 1)
            table.set(j, table.get(n - 1));
        table.remove(n - 1);
        return value;
    }

    // Support for public entrySet method
    private class EntryIterator implements Iterator<Entry<K, V>> {
        private int j = 0;

        public boolean hasNext() {
            return j < table.size();
        }

        public Entry<K, V> next() {
            if (j == table.size())
                throw new NoSuchElementException();
            return table.get(j++);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class EntryIterable implements Iterable<Entry<K, V>> {
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }
    }

    /** Returns an iterable collection of all key-value entries of the map. */
    public Iterable<Entry<K, V>> entrySet() {
        return new EntryIterable();
    }

    public static void main(String[] args) {
        Map<String, Integer> students = new UnsortedTableMap<>();
        Integer ret = students.put("Vinod", 1);
        System.out.println("ret: " + ret);

        ret = students.put("Vinod", 2);
        System.out.println("ret: " + ret);

        ret = students.put("Vinod", 3);
        System.out.println("ret: " + ret);

        Integer size = students.size();
        System.out.println("size: " + size);

        ret = students.put("Harish", 1);
        System.out.println("ret: " + ret);

        size = students.size();
        System.out.println("size: " + size);

        ret = students.put("Harish", 2);
        System.out.println("ret: " + ret);

        ret = students.get("Vinod");
        System.out.println("ret: " + ret);

        ret = students.get("Harish");
        System.out.println("ret: "+ ret);

        ret = students.remove("Vinod");
        System.out.println("ret: " + ret);

        students.put("Vinod", 1);
        students.put("Ram", 3);
        students.put("Sita", 4);
        size = students.size();
        System.out.println("size: " + size);

        ret = students.remove("Sita");
        System.out.println("ret: " + ret);

        ret = students.remove("Ram");
        System.out.println("ret: " + ret);

        ret = students.remove("Vinod");
        System.out.println("ret: " + ret);

        ret = students.remove("Harish");
        System.out.println("ret: " + ret);

        size = students.size();
        System.out.println("size: " + size);

        ret = students.remove("Vinod");
        System.out.println("ret: " + ret);

    }
}
