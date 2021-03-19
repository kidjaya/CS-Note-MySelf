package java基础面试题.basic.LRU;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author kidjaya
 */
public class LRUCache<K,V> {
    private int cap;
    private LinkedHashMap<K,V> cache = new LinkedHashMap<>();
    private static final int DEFAULT_CAPACITY = 2;
    List<Integer> list = new ArrayList<>();

    public LRUCache(int cap) {
        if (cap <= 0){
            cap = DEFAULT_CAPACITY;
        }
        this.cap = cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public V get(K key){
        if (cache.containsKey(key)){
            makeRecently(key);
            return cache.get(key);
        }
        return null;
    }

    public void put(K key,V value){
        if (cache.containsKey(key)){
            cache.put(key,value);
            makeRecently(key);
            return;
        }

        if(cache.size() >= cap){
            K head = cache.keySet().iterator().next();
            cache.remove(head);
        }
        cache.put(key,value);
    }

    private void makeRecently(K key){
        if (cache.containsKey(key)){
            V value = cache.get(key);
            cache.remove(key);
            cache.put(key,value);
        }
    }
}
