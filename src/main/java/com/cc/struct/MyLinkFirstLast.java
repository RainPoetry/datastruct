package com.cc.struct;

/**
 * User: chenchong
 * Date: 2018/12/11
 * description: 双端链表
 * 		特点：
 * 			1. 表头、表尾插入，表尾删除，表头开始查询
 * 			2. first 和  last 只是对表头和表尾节点的引用，不是数据的存储节点
 * 			3. 利用 first 和 last 完成链表的操作
 * 		优点：
 * 			1. 允许动态扩容
 * 			2. 插入快、删除快
 * 		缺点：
 * 			1. 查找慢（要么从队首，要么从队尾开始查找）
 */
public class MyLinkFirstLast<T> {

	// first: 指向第一个节点的引用
	private Node<T> first;
	// last 指向最后一个节点的引用
	private Node<T> last;

	public MyLinkFirstLast() {
		first = null;
		last = null;
	}

	// 表尾删除
	public void removeTail() {
		if (isEmpty())
			throw new IllegalStateException("链表为空");

	}

	//表头插入
	public void addHead(T t) {
		Node<T> node = new Node<>(t);
		if (isEmpty())
			last = node;        // last --> new Node
		node.next = first;        // new Node --> old First
		first = node;            // first --> new Node
	}

	// 表尾插入
	public void addTail(T t) {
		Node<T> node = new Node<>(t);
		if (isEmpty())
			first = node;        // first --> new Node
		else
			last.next = node;   // old last --> new Node
		last = node;            // last --> new Node
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

	// 表头删除
	public T removeHead() {
		if (isEmpty())
			throw new IllegalStateException("链表为空");
		if (first.next == null)
			last = null;        // last --> null
		T t = first.data;
		first = first.next;        // first --> next Node;
		return t;
	}

	public boolean isEmpty() {
		return first == null;
	}

	private static class Node<T> {
		T data;
		Node<T> next;

		public Node(T data) {
			this.data = data;
		}
	}

	public static void main(String[] args) {
		MyLinkFirstLast<Integer> link = new MyLinkFirstLast<>();
		link.addHead(789);
		link.addTail(1);
		link.addTail(2);
		link.addHead(777);
		System.out.println(link.toString(","));
		System.out.println("remove data: " + link.removeHead());
		System.out.println(link.toString(","));
	}
}
