package in.rgukt.r081247.java.datastructure.other;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRU<T, U> {

    public static void main(String[] args) {
        LRU<String, Integer> lru = new LRU<>(4);
        System.out.println(lru);
        lru.put("a", 1);
        lru.put("b", 1);
        lru.put("c", 1);
        lru.put("d", 1);
        System.out.println(lru);
        lru.put("e", 1);
        System.out.println(lru);
        System.out.println(lru.get("c"));
        System.out.println(lru);
        System.out.println(lru.put("b", 2));
        System.out.println(lru);
        System.out.println(lru.get("z"));
        System.out.println(lru);

    }

    private int capacity;
    private Map<T, U> map = new LinkedHashMap<>();

    public LRU(int capacity) {
        this.capacity = capacity;
    }

    public U get(T key) {
        if (map.containsKey(key)) {
            U value = map.remove(key);
            map.put(key, value);
            return value;
        } else {
            return null;
        }
    }

    public U put(T key, U value) {
        if (map.containsKey(key)) {
            U oldValue = map.remove(key);
            map.put(key, value);
            return oldValue;
        } else {
            if (map.size() < capacity)
                return map.put(key, value);
            else {
                map.remove(map.entrySet().iterator().next().getKey());
                return map.put(key, value);
            }
        }
    }

    public U delete(T key) {
        return map.remove(key);
    }

    public String toString() {
        return map.toString() + ", capacity: " + capacity;
    }
}