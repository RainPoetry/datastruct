package com.cc.sourcecode;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * User: chenchong
 * Date: 2019/1/26
 * description:
 * <p>
 *      特点：
 *          1. 有序
 *          2. 继承自 HashMap
 * <p>
 * <p>
 *      有序的实现（存储单元为 链表时）：
 *      1.  HashMap.Node 的插入通过判断 next 指针是否为空，在队尾完成插入
 *      2. 	LinkedHashMap.Entry 在 HashMap.Node 的基础上增加了 before, after指针，将 Hash 表中的所有的 链表内的数据联系在了一起
 */
public abstract class MyLinkedHashMap<K, V> extends MyHashMap<K, V> {

    /**
     * The head (eldest) of the doubly linked list.
     *      队首，用于数据的有序访问，oldest 数据的删除
     */
    transient MyLinkedHashMap.Entry<K,V> head;

    /**
     * The tail (youngest) of the doubly linked list.
     *      队尾，用于节点的插入
     */
    transient MyLinkedHashMap.Entry<K,V> tail;


    // 在 HashMap.Node 的结构上，加上 2 个指针，用于将所有的是数据串成一个双端链表
    static abstract class Entry<K, V> extends MyHashMap.Node<K, V> {
        Entry<K, V> before, after;
    }

    // 重写 HashMap 的 newNode 方法
    // 在生成 Entry 对象的同时，将 Entry 节点插入的链表队尾
    abstract Entry<K,V> newNode(int hash, K key, V value, MyHashMap.Node<K, V> e);

    // 重写  afterNodeAccess 方法
    // 只有当 accessOrder = true 是，才会起作用
    // 当 Entry 的值被新的值替换的时候，或者该节点被访问 时， 将改节点移动到链尾
    void afterNodeAccess(Node<K,V> e) {} // move node to last


    // 当为 true 的时候，会调用 HashMap.remove(key)
    // 其中 key 是 head 节点对应的值
    // LinkedHahsMap 默认始终为 false,也就是不删除数据
    // 如果需要定容的话，那么要重写这个方法
    abstract boolean removeEldestEntry();
}
