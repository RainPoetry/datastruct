package com.cc.sourcecode;

/*
 * User: chenchong
 * Date: 2019/8/1
 * description:
 *
 * 		ConcurrentHashMap 源码解析
 */

import java.util.concurrent.ConcurrentHashMap;

public abstract class MyConcurrentHashMap<K,V> {

	static final int HASH_BITS = 0x7fffffff; // usable bits of normal node hash

	private static final sun.misc.Unsafe U = sun.misc.Unsafe.getUnsafe();


	// 计算 HashCode
	static final int spread(String key) {
		int h = key.hashCode();
		return (h ^ (h >>> 16)) & HASH_BITS;
	}


	/*
	 * 1. 根据 key 计算 hashcode
	 * 2. 根据 hashcode 获取数据所在的单元格 ： U.getObjectVolatile(tab, ((long)i << ASHIFT) + ABASE);
	 * 3. 单元格
	 * 		没有数据：
	 * 			采用 CAS，插入数据：
	 * 				U.compareAndSwapObject(tab, ((long)i << ASHIFT) + ABASE, c, v);
	 * 		有数据：
	 * 			对第一个节点采用 synchronized	加锁，然后从链表的首节点依次遍历，
	 * 				如果不存在 节点的 HashNode = 传入的 hashcode 则在链表队尾插入数据，然后 break，最后返回 null
	 * 				如果存在   节点的 HashNode = 传入的 hashcode 则 break，最后返回该节点的数据
	 */
	abstract V put(K key,V value);


}
