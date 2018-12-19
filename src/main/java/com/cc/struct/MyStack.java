package com.cc.struct;

/**
 * User: chenchong
 * Date: 2018/12/8
 * description: 栈
 * 		特点：
 * 			1. 后进先出
 * 			2. 栈顶插入、栈顶删除、栈顶读取
 * 		优点：
 * 			插入、删除快
 * 		缺点：
 * 			只能获取底层节点，不支持查找
 */
public class MyStack {

	private final int max;
	private final long[] data;
	private int position;

	public MyStack(int size) {
		max = size;
		data = new long[size];
		position = -1;
	}

	public void push(long unit) {
		// position =  position +1,
		// data[position]
		data[++position] = unit;
	}

	public long peek() {
		// data[position]
		// position = position -1
		return data[position--];
	}

	public long top() {
		return data[position];
	}

	public static void main(String[] args) {
		MyStack stack = new MyStack(10);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		System.out.println("栈顶： " + stack.top());
		System.out.println("弹出一个元素：" + stack.peek() + " , 栈顶： " + stack.top());
	}
}
