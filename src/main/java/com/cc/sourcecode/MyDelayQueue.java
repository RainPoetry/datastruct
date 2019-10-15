package com.cc.sourcecode;

import java.util.concurrent.TimeUnit;

/**
 * User: chenchong
 * Date: 2019/1/17
 * description:		DelayQueue 源码分析
 * <p>
 * 特点：
 * 1. 无界队列
 * 2. 基于 PriorityQueue 实现的 (实际上就是对 PriorityQueue 的 API 简单的封装了一层)
 * 3. 通过 ReentrantLock 对 PriorityQueue 的 poll、peek 等操作进行加锁 实现线程安全
 * 4. 当 DelayQueue First 不为 空， 且 delay <= 0 ，poll(5, TimeUnit.SECONDS) 不会等待阻塞
 */
public abstract class MyDelayQueue<E> {

    // first = PriorityQueue.peek() - 获取优先级队列的第一个元素， 没有则为 null
    // 如果 first 为 null , 且 timeout <= 0 , 则返回 null     ( 从这一步开始循环)
    // 如果 first == null , 且 timeout > 0 , 则阻塞 timeout
    // 如果 first != null , 且 delay <=0 , 则返回 first   (只有这条路线是出路，其余的要么重新进入循环，要么返回null)
    //  如果 first != null , 且 delay >0  ，且 timeout <=0 , 则 返回 null
    // 如果 first !=null，且 timeout < delay ，  则阻塞 timeout ， 然后重新开始循环
    // 如果 first !=null，且 timeout > delay ，  则阻塞 delay ， 然后重新开始循环
    abstract E poll(long timeout, TimeUnit unit) throws InterruptedException;
}
