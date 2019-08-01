package com.cc.struct;

/**
 * User: chenchong
 * Date: 2018/12/11
 * description: 双向链表
 * 		特点：
 * 			1. 表头、表尾 进行 插入和删除
 * 			2. 表头、表尾 都可以进行查找
 * 			3. first 和  last 只是对表头和表尾节点的引用，不是数据的存储节点
 * 			4. 利用 first、last 完成链表的操作
 * 		优点：
 * 			1. 插入快、删除快
 * 		缺点：
 * 			1. 查找慢、
 *
 *
 * 		特点：
 * 			1. 内存空间不是连续的，通过指针完成寻址
 */
public class MyLinkDoubly<T> {

	// first: 指向第一个节点的引用
	private Node<T> first;
	// last 指向最后一个节点的引用
	private Node<T> last;

	public MyLinkDoubly() {
		this.first = null;
		this.last = null;
	}

	// first.prev ---> Node
	// Node.next ----> first
	public void addHead(T t) {
		Node<T> node = new Node<>(t);
		if (isEmpty())
			last = node;            // New Node <--- last
		else
			first.prev = node;        // New Node <--- old first
		node.next = first;            // New Node ---> old first
		first = node;                // first  ---> New Node
	}

	// node.next ---> last
	// last.prev ----> node
	public void addTail(T t) {
		Node<T> node = new Node<>(t);
		if (isEmpty())
			first = node;            // first ---> New Node
		else
			last.next = node;        // old Last ---> New Node
		node.prev = last;            // Old Last <---- New Node
		last = node;                // last ----> New Node
	}


	public T removeHead() {
		if (isEmpty())
			throw new IllegalStateException("链表为空");
		T t = first.data;
		if (first.next == null)
			last = null;
		else
			first.next.prev = null;
		first = first.next;
		return t;
	}

	public T removeTail() {
		if (isEmpty())
			throw new IllegalStateException("链表为空");
		T t = first.data;
		if (first.next == null)
			first = null;
		else
			last.prev.next = null;
		last = last.prev;
		return t;
	}


	public T getHead() {
		return first.data;
	}

	public T getTail() {
		return last.data;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public String toString(String seperate) {
		StringBuffer sb = new StringBuffer();
		if (isEmpty())
			return "";
		Node next = first;
		while (next != null) {
			sb.append(next.data);
			if (next.next != null)
				sb.append(seperate);
			next = next.next;
		}
		return sb.toString();
	}

	private static class Node<T> {
		T data;
		Node<T> prev;
		Node<T> next;

		public Node(T t) {
			this.data = t;
		}
	}

	public static void main(String[] args) {
		MyLinkDoubly<Integer> link = new MyLinkDoubly();
		link.addTail(1);
		link.addTail(2);
		link.addTail(3);
		link.addTail(4);
		link.addTail(5);
		link.removeTail();
		link.removeHead();
		System.out.println(link.toString(","));
	}
}
