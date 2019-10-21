package com.cc.sourcecode;

/*
 * author:  RainPoetry
 * date:  2019/10/21
 * description: CopyOnWriteArrayList 源码
 *
 *     内部数据结构： 数组
 *
 *      适合场景：
 *          多读少写
 *
 */

public abstract class MyCopyOnWriteArrayList<E> {


    // 数据存储结构
    private transient volatile Object[] array;

    /*
     *  获取  array
     *  对 array 进行复制一份，得到新的 newArray： Arrays.copyOf
     *  在 newArray 中添加新的数据
     *  array 指向 newArray
     *  整个过程采用 ReentrantLock 进行加锁保护
     *
     */
    public abstract boolean add(E e);


    /*
     *   无锁
     *   直接读取 array 中 index 位置的数据
     */
    public abstract E get(int index);
}
