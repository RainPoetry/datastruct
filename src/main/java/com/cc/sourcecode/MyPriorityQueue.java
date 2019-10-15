package com.cc.sourcecode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * User: chenchong
 * Date: 2019/1/18
 * description: 优先级队列
 *
 * 		实现方式： 二叉堆
 * 		PriorityQueue 是以满二叉树为模型对数据进行操作的
 * 			数据实际的存储方式是数组
 *
 * 		数据的插入： 上浮 (current 和 parent 间的比较)
 * 		数据的删除： 下沉 (current 和 children 间的比较)
 *
 * 		数组的队首的优先级总是最高(最低)的
 *
 * 		数组数据的迁移的实现和 ArrayList 一致
 * 			旧数组数据 -> 新的数组：
 * 				Arrays.copyOf(elementData, newCapacity);
 *
 * 			数组内部数据移动(新增、删除操作)：
 * 				System.arraycopy(this.elementData, var1 + 1, this.elementData, var1, var3);
 */
public class MyPriorityQueue<E> {

	// 默认容量
	private static final int DEFAULT_CAPACITY = 16;

	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	// 队列
	private Object[] queue;

	private int size = 0;

	private final Comparator<? super E> comparator;

	public MyPriorityQueue() {
		this(DEFAULT_CAPACITY,null);
	}

	public MyPriorityQueue(int capacity, Comparator comparator) {
		this.comparator = comparator;
		this.queue = new Object[capacity];
	}

	// 扩容计算
	private void grow(int minCapacity) {
		int oldCapacity = queue.length;
		int newCapacity = oldCapacity + oldCapacity >> 1;
		if (newCapacity > MAX_ARRAY_SIZE)
			newCapacity = hugeCapacity(minCapacity);
		queue = Arrays.copyOf(queue,newCapacity);
	}

	private static int hugeCapacity(int minCapacity) {
		if (minCapacity < 0) // overflow
			throw new OutOfMemoryError();
		return (minCapacity > MAX_ARRAY_SIZE) ?
				Integer.MAX_VALUE :
				MAX_ARRAY_SIZE;
	}

	// 插入
	public boolean offer(E e) {
		if (e == null)
			throw new NullPointerException();
		int i = size;
		if (i >= queue.length)
			grow(i + 1);
		size = i + 1;
		if (i == 0)
			queue[0] = e;
		else
			siftUp(i, e);
		return true;
	}

	// 弹出
	public E poll() {
		if (size == 0)
			return null;
		int s = --size;
		E result = (E) queue[0];
		E x = (E) queue[s];
		queue[s] = null;
		if (s != 0)
			siftDown(0, x);
		return result;
	}

	public E peek() {
		return (size == 0) ? null : (E) queue[0];
	}

	// 下沉
	// k 数组的第一个位置
	// x 数组的最后一个元素
	private void siftDown(int k, E x) {
		Comparable<? super E> key = (Comparable<? super E>)x;
		int half = size >>> 1;        // loop while a non-leaf
		while (k < half) {
			int child = (k << 1) + 1; // assume left child is least
			Object c = queue[child];
			int right = child + 1;
			// right 存在 ? 获取子节点中较小的那个 : left 节点
			if (right < size &&
					((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)
				c = queue[child = right];
			// current < child ? break : 下沉
			if (key.compareTo((E) c) <= 0)
				break;
			queue[k] = c;
			k = child;
		}
		queue[k] = key;
	}

	// 上浮
	// k , 数组的最后一个位置
	// x 待插入的数据
	private void siftUp(int k, E x) {
		Comparable<? super E> key = (Comparable<? super E>) x;
		while (k > 0) {
			int parent = (k - 1) >>> 1;	// parent index
			Object e = queue[parent];
			// current > parent ? break : 上浮
			if (key.compareTo((E) e) >= 0)
				break;
			// parent 放到 k 处
			queue[k] = e;
			// k 指向 原来 parent 的位置
			k = parent;
		}
		queue[k] = key;
	}
}
