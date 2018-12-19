package com.cc.struct;

/**
 * User: chenchong
 * Date: 2018/12/10
 * description: 队列 (优化版)
 */
public class MyQueueNoCounts<T> {

	T[] data;
	// 队首指针
	private int from;
	// 队尾指针
	private int rear;
	private final int capacity;

	public MyQueueNoCounts(int size) {
		from = 0;
		rear = -1;
		capacity = size + 1;
		data = (T[]) new Object[size];
	}

	// insert、remove 不变

	public boolean isEmpty() {
		// from > rear 和 rear > from
		return (rear + 1 == from || from + capacity - 1 == rear);
	}

	public boolean isFull() {
		// from > rear: capacity - from + rear +1 = capacity-1 （实际的数据大小比配置的少1）
		// rear > from : rear - from + 1 = capacity-1
		return (rear + 2 == from || from + capacity - 2 == rear);
	}
}
