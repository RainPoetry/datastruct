package com.cc.algorithm.cache.lru;

/*
 * author:  RainPoetry
 * date:  2019/9/7
 * description:
 *
 *
 *         基于 LinkedHashMap 实现的 LRU Cache
 *
 *
 *          LinkedHashMap 在 HashMap 的 put 方法以后会调用 afterNodeInsertion
 *          afterNodeInsertion 只有在 重写了 removeEldestEntry 方法采用调用，
 *          当 removeEldestEntry 返回 true 的时候，会删除最老的一个节点
 *
 *
 *          LinkedHashMap 在 将数据插入到 哈希表中后，会调用 afterNodeAccess 方法，将插入的节点
 *
 *
 */
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUByLinkedHashMap extends LinkedHashMap<Integer, Integer> {

    private int capacity;

    public LRUByLinkedHashMap(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUByLinkedHashMap map = new LRUByLinkedHashMap(5);
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        map.put(4,4);
        map.put(5,5);
        map.put(6,6);
        System.out.println(map);
        map.put(2,9);
        System.out.println(map);
        map.get(3);
        System.out.println(map);
        map.put(7,7);
        System.out.println(map);

    }
}
