package com.cc.sourcecode;

import sun.misc.Unsafe;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * User: chenchong
 * Date: 2019/1/22
 * description:
 * 		特点：
 *			1. 基于链表实现的无界队列
 *			2. 线程安全
 *			3. FIFO, 队尾插入，队首取出
 *			4. 不支持 null 元素
 *			5. 内部实现了非阻塞算法：一个线程的失败或者挂起不应该影响其他线程的失败或挂起的算法。
 *
 * 		优点：
 * 			1. 采用 CAS 实现并发操作
 * 			2. 不存在 ABA 问题
 *
 *		缺点：
 *			1. size() 的时间复杂度为 O(n)
 */
public class MyConcurrentLinkedQueue {
	public static void main(String[] args){
		ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
		queue.add(null);
	}
}
