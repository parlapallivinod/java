/**
 * @author Vinod Parlapalli
 * @since 2019/12/11
 */

package in.rgukt.r081247.java.datastructure.map;

import java.util.Iterator;

public abstract class AbstractMap<K, V> implements Map<K, V> {
    public boolean isEmpty() {
        return size() == 0;
    }

    // nested MapEntry class
    protected static class MapEntry<K, V> implements Entry<K, V> {
        private K key;
        private V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        // public methods of the Entry interface
        @Override
        public K getKey() {
            return key;
        }
        @Override
        public V getValue() {
            return value;
        }

        // utilities not exposed as part of the Entry interface
        protected void setKey(K key) {
            this.key = key;
        }
        protected V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }
    }

    // Support for public keySet method
    private class KeyIterator implements Iterator<K> {
        private Iterator<Entry<K, V>> entries = entrySet().iterator(); // reuse entrySet

        public boolean hasNext() {
            return entries.hasNext();
        }

        public K next() {
            return entries.next().getKey(); // return key!
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class KeyIterable implements Iterable<K> {
        public Iterator<K> iterator() {
            return new KeyIterator();
        }
    }

    public Iterable<K> keySet() {
        return new KeyIterable();
    }

    // Support for public values method
    private class ValueIterator implements Iterator<V> {
        private Iterator<Entry<K, V>> entries = entrySet().iterator(); // reuse entrySet

        @Override
        public boolean hasNext() {
            return entries.hasNext();
        }

        public V next() {
            return entries.next().getValue(); // return value!
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class ValueIterable implements Iterable<V> {
        public Iterator<V> iterator() {
            return new ValueIterator();
        }
    }

    public Iterable<V> values() {
        return new ValueIterable();
    }
}
