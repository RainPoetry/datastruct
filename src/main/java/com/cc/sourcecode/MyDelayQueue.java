package com.cc.sourcecode;

/**
 * User: chenchong
 * Date: 2019/1/17
 * description:		DelayQueue 源码分析
 *
 * 		特点：
 * 			1. 无界队列
 * 			2. 基于 PriorityQueue 实现的 (实际上就是对 PriorityQueue 的 API 简单的封装了一层)
 * 			3. 通过 ReentrantLock 对 PriorityQueue 的 poll、peek 等操作进行加锁 实现线程安全
 * 			4. 当 DelayQueue First 不为 空， 且 delay <= 0 ，poll(5, TimeUnit.SECONDS) 不会等待阻塞
 */
public class MyDelayQueue {


}
