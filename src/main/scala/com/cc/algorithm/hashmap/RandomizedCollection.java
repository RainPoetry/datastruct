package com.cc.algorithm.hashmap;

/*
 * author:  RainPoetry
 * date:  2019/8/23
 * description:
 *
 *      要求：
 *          设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
 *
 *          注意: 允许出现重复元素。
 *
 *          insert(val)：向集合中插入元素 val。
 *          remove(val)：当 val 存在时，从集合中移除一个 val。
 *          getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
 *
 *
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class RandomizedCollection {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final Random random = new Random();

    int[] values;

    int valueSize;

    // 默认容量大小： 16
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    Node[] table;

    int size;

    int threshold;

    final float loadFactor = 0.75f;

    class Node {
        Node next;
        Node prev;
        int val;
        int index;

        Node(int val, Node next, Node prev) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * Initialize your data structure here.
     */
    public RandomizedCollection() {
        table = new Node[DEFAULT_INITIAL_CAPACITY];
        values = new int[DEFAULT_INITIAL_CAPACITY];
        threshold = (int) (DEFAULT_INITIAL_CAPACITY * loadFactor);
        size = 0;
        valueSize = 0;
    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (size++ > threshold)
            resize();
        int position = hash(val) & (table.length - 1);
        Node p;
        if ((p = table[position]) == null) {
            p = new Node(val, null, null);
            table[position] = p;
            afterInsert(p);
            return true;
        } else {
            Node newNode = new Node(val, p, null);
            p.prev = newNode;
            while (p != null) {
                if (p.val == val)
                    return false;
                else
                    p = p.next;
            }
            afterInsert(newNode);
            return true;
        }
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified element.
     */
    public boolean remove(int val) {
        int position = hash(val) & (table.length - 1);
        Node p;
        if ((p = table[position]) == null) {
            return false;
        } else {
            while (p != null) {
                if (p.val == val) {
                    Node before = p.prev;
                    Node after = p.next;
                    if (p.prev != null)
                        before.next = after;
                    if (after != null)
                        after.prev = before;
                    size--;
                    afterRemove(p);
                    return true;
                }
                p = p.next;
            }
            return false;
        }
    }

    public void afterInsert(Node node) {
        values[++valueSize] = node.val;
    }

    public void afterRemove(Node node) {

    }

    public void afterResize(int newCapacity) {
        values = Arrays.copyOf(values, newCapacity);
    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        if (size == 0)
            return -1;
//        int index = (int) (Math.random() + 1);
//        String value = values[index];
//        while (value == null) {
//            value = values[index++ % size];
//        }
//        return Integer.parseInt(value);
        return 0;
    }

    void resize() {
        Node[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int newCap = oldCap << 1;
        Node[] newTab = new Node[newCap];
        table = newTab;
        // 数组数据迁移
        for (int j = 0; j < oldCap; ++j) {
            Node e;
            if ((e = oldTab[j]) != null) {
                oldTab[j] = null;
                Node loHead = null, loTail = null;
                Node hiHead = null, hiTail = null;
                Node next;
                do {
                    next = e.next;
                    if ((hash(e.val) & oldCap) == 0) {
                        if (loTail == null)
                            loHead = e;
                        else
                            loTail.next = e;
                        loTail = e;
                    } else {
                        if (hiTail == null)
                            hiHead = e;
                        else
                            hiTail.next = e;
                        hiTail = e;
                    }
                } while ((e = next) != null);
                if (loTail != null) {
                    loTail.next = null;
                    newTab[j] = loHead;
                }
                if (hiTail != null) {
                    hiTail.next = null;
                    newTab[j + oldCap] = hiHead;
                }
            }
        }
        afterResize(newCap);
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public static void main(String[] args) {
        RandomizedCollection collection = new RandomizedCollection();
        boolean insert = collection.insert(1);
        collection.insert(1);
        collection.remove(1);
        System.out.println(insert);
        int get = collection.getRandom();
        System.out.println(get);
    }

    static void test() {
        // 初始化一个空的集合。
        RandomizedCollection collection = new RandomizedCollection();

        // 向集合中插入 1 。返回 true 表示集合不包含 1 。
        collection.insert(1);

        // 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
        collection.insert(1);

        // 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
        collection.insert(2);

        // getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
        collection.getRandom();

        // 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
        collection.remove(1);

        // getRandom 应有相同概率返回 1 和 2 。
        collection.getRandom();
    }
}
