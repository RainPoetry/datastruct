package com.cc.struct;

/**
 * User: chenchong
 * Date: 2018/12/10
 * description: 队列
 * 		特点：
 * 			1. 先进先出
 * 			2. 队尾插入、队首删除
 * 			3. 通过指针来完成相关的操作，而不需要去移动数据
 * 		优点：
 * 			插入快、删除快
 * 		缺点：
 * 			只能获取队首和队尾的数据
 */
public class MyQueue<T> {

	T[] data;
	// 队首指针
	private int from;
	// 队尾指针
	private int rear;
	private final int capacity;
	// 当数据量上亿的时候，可能会稍微影响性能
	private int numbers;

	public MyQueue(int size) {
		data = (T[]) new Object[size];
		from = 0;
		rear = -1;
		capacity = size;
	}

	public void insert(T t) {
		if (t == null)
			throw new IllegalArgumentException("传参不能为 null ");
		if (isFull())  // 在 isFull 不用 numbers 来判断可以省略掉 numbers++ 操作
			throw new IllegalStateException("队列满了");
		if (rear == capacity - 1)
			rear = -1;
		data[++rear] = t;
		numbers++;
	}

	public T remove() {
		if (isEmpty())
			throw new IllegalStateException("队列为空");
		T t = data[from++];
		if (from == capacity)
			from = 0;
		numbers--;
		return t;
	}

	public boolean isEmpty() {
		return numbers == 0;
	}


	/**
	 * 通过以下代码可以看出，无法通过 rear + 1 == from 发来判断队列是空的还是满的
	 *
	 * @return
	 */
/*	public boolean isEmptyNoCounts() {
		// 当 from > rear 和 from < rear 的情形
		// from == rear 时， 表示队列只有一个元素，然后删除时，from++
		return (rear + 1 == from || from + capacity -1 == rear );
	}
	public boolean isFullNoCounts() {
		// 当 rear > from 和 rear < from 的情形
		// from > rear ： capacity-from+rear+1 = capacity
		return (capacity - from + rear == capacity );
	}*/
	public boolean isFull() {
		return numbers == capacity;
	}


	public String toString(String seperate) {
		StringBuffer sb = new StringBuffer();
		if (numbers == 0)
			return "";
		if (rear >= from) {
			for (int i = from; i <= rear; i++) {
				sb.append(data[i]);
				if (i != rear)
					sb.append(seperate);
			}
		} else {
			for (int i = from; i < capacity; i++) {
				sb.append(data[i]);
				sb.append(seperate);
			}
			for (int i = 0; i <= rear; i++) {
				sb.append(data[i]);
				if (i != rear)
					sb.append(seperate);
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		MyQueue<Integer> queue = new MyQueue<Integer>(5);
		queue.insert(10);
		queue.insert(2);
		queue.insert(3);
		queue.insert(4);
		System.out.println("移除队首： " + queue.remove());
		System.out.println("移除队首： " + queue.remove());
		System.out.println("移除队首： " + queue.remove());
		queue.insert(5);
		queue.insert(6);
		queue.insert(7);
		System.out.println("移除队首： " + queue.remove());
		System.out.println("移除队首： " + queue.remove());
		System.out.println("移除队首： " + queue.remove());
		System.out.println(queue.toString(",") + " | " + queue.from + "-" + queue.rear);
	}
}
