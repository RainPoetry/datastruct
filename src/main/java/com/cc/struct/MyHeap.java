package com.cc.struct;

import com.cc.utils.Generate;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * User: chenchong
 * Date: 2019/1/5
 * description:	堆
 * <p>
 * 		特点：
 * 			1. 完全二叉树
 * 			2. 基于数组、链表
 * 			3. 弱序（和二叉搜索树相比）
 * 			4. 上浮下沉
 * 			5. 对于索引节点 X	 :
 * 				父节点 ： (X-1) / 2
 * 				子节点： 2 * X + 1  、 2 * X + 2
 * 			6. 数组是无序的，但是队首的优先级总是最高(最低)
 *
 * 		优点：
 * 			1. 插入和删除的时间复杂度为 O(logN)
 * 			(有序数组插入的时间复杂度为 O(logN)， 删除最大(最小)数据的时间复杂度为 O(1))
 *
 * 			2.
 *
 * 		缺点：
 * 			1. 查找困难
 * 			2. 不支持按序遍历
 * 			3. 数组是无序的
 */
public class MyHeap<E> {

	private static final int DEFAULT_CAPACITY = 16;
	private Object[] queue;
	private int size = 0;

	public MyHeap() {
		this(DEFAULT_CAPACITY);
	}

	public MyHeap(int capacity) {
		queue = new Object[capacity];
	}

	public void grow() {
		int oldCapacity = queue.length;
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		queue = Arrays.copyOf(queue, newCapacity);

	}

	public boolean add(E e) {
		int i = size;
		if (i >= queue.length)
			grow();
		size = i + 1;
		if (i == 0)
			queue[0] = e;
		else
			siftUp(i,e);
		return true;
	}

	// 上浮
	public void siftUp(int i, E e) {
		Comparable<? super E> comparable = (Comparable<? super E>)e;
		while (i > 0) {
			int parent = (i - 1) >>> 1;	// parent Index
			E p = (E)queue[parent];
			// current > parent ? break : 上浮
			if (comparable.compareTo(p) >= 0)
				break;
			queue[i] = p;
			i = parent;
		}
		queue[i] = e;
	}

	// 弹出队首
	public E poll() {
		if (size == 0)
			return null;
		int s = --size;
		// 获取队首
		E result =(E) queue[0];
		// 队尾移至队首
		E x = (E) queue[s];
		queue[s] = null;
		if (s != 0)
			siftDown(0, x);
		return result;
	}

	// 下沉
	public void siftDown(int index, E e) {
		Comparable<? super E> comparable = (Comparable<? super E>)e;
		int half = size >> 1;
		while (index < half) {		 // loop while a non-leaf (当为叶子节点时跳出循环)
			int c = (index << 1) + 1;
			int right = c + 1;
			E child = (E)queue[c];
			// right 存在， 获取子节点中较小的那个
			if (right < size &&
					((Comparable<? super E>) child).compareTo((E)queue[right]) > 0)
				child = (E)queue[c = right];
			// current < child ? break : 下沉
			if (comparable.compareTo(child) <= 0)
				break;
			queue[index] = child;
			index = c;
		}
		queue[index] = e;
	}

	public String toString() {
		return Arrays.toString(queue);
	}

	public static void main(String[] args) {
	    MyHeap<Integer> heap = new MyHeap<>();
		PriorityQueue<Integer> p = new PriorityQueue<>();
		for(int a : Generate.randInt(20,1000)) {
			heap.add(a);
			p.add(a);
		}
		System.out.println(heap.toString());
		System.out.println(p);
		while (heap.size-- >0)
			System.out.println(p.poll());

	}
}
