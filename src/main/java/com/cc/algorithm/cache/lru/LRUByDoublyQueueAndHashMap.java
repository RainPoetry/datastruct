package com.cc.algorithm.cache.lru;

/*
 * author:  RainPoetry
 * date:  2019/9/7
 * description:
 *
 *          仿 LinkedHashMap，采用 双端链表 + HashMap 实现 LRU Cache。
 *
 *
 *          实现起来比 HashMap 要简单，不需要考虑扩容的问题
 *
 */

public class LRUByDoublyQueueAndHashMap<K, V> {

    int[] values;

    int valueSize;

    Node[] table;

    Node head;

    Node tail;

    class Node<K, V> {
        Node next;
        K key;
        V value;

        Node before;
        Node after;

        Node(K key, V value, Node next) {
            this.next = next;
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Initialize your data structure here.
     */
    public LRUByDoublyQueueAndHashMap(int capacity) {
        table = new Node[capacity];
    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public void insert(K k, V v) {
        int position = hash(k) & (table.length - 1);
        Node p;
        if ((p = table[position]) == null) {
            p = new Node(k, v, null);
            table[position] = p;
            afterInsert(p);
        } else {
            while (p != null) {
               if (p.key == k) {
                    p.value = v;
                    moveToTail(p);
                    return;
               }
               p = p.next;
            }
            p.next = new Node(k,v,null);
            afterInsert(p.next);
        }
    }

    public void moveToTail(Node node) {

    }

    public int get(K k) {
        return -1;
    }

    public void afterInsert(Node node) {

    }

    public void afterRemove(Node node) {

    }


    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public static void main(String[] args) {

    }
}
