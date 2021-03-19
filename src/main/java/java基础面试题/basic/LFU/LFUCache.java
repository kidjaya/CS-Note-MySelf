package java基础面试题.basic.LFU;

import java.util.HashMap;
import java.util.LinkedHashSet;

class LFUCache {
    HashMap<Integer,Integer> k2v;
    HashMap<Integer,Integer> k2f;
    HashMap<Integer, LinkedHashSet<Integer>> f2k;
    public static final int DEFAULT_CAPACITY = 2;
    int minFreq;
    int cap;

    public LFUCache(int capacity) {
        if (capacity <= 0){
            capacity = DEFAULT_CAPACITY;
        }
        k2f = new HashMap<>();
        k2v = new HashMap<>();
        f2k = new HashMap<>();
        this.cap = capacity;
        this.minFreq = 0;
    }
    
    public int get(int key) {
        if(!k2v.containsKey(key)){
            return -1;
        }
        increaseFreq(key);
        return k2v.get(key);

    }
    
    public void put(int key, int value) {
        if (k2v.containsKey(key)){
            k2v.put(key,value);
            increaseFreq(key);
            return;
        }
        if(this.cap <= k2v.size()){
            removeMinFreqKey();
        }
        k2v.put(key,value);
        k2f.put(key,1);
        f2k.putIfAbsent(1,new LinkedHashSet<>());
        f2k.get(1).add(key);
        this.minFreq = 1;
    }

    public void removeMinFreqKey(){
        //需要更新三个表
        LinkedHashSet<Integer> keyList = f2k.get(this.minFreq);
        int firstMiniFreq = keyList.iterator().next();
        keyList.remove(firstMiniFreq);
        if (keyList.isEmpty()){
            f2k.remove(this.minFreq);
        }
        k2v.remove(firstMiniFreq);
        k2f.remove(firstMiniFreq);
    }

    public void increaseFreq(int key){
        //需要修改key to Frequents 和 Frequent to keys
        int freq = k2f.get(key);
        k2f.put(key,freq+1);
        f2k.get(freq).remove(key);
        f2k.putIfAbsent(freq+1,new LinkedHashSet<>());
        f2k.get(freq+1).add(key);
        if (f2k.get(freq).isEmpty()){
            f2k.remove(freq);
            if (freq == this.minFreq){
                this.minFreq++;
            }
        }
    }
}