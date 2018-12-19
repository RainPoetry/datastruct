package com.cc.struct;

/**
 * User: chenchong
 * Date: 2018/12/19
 * description:	哈希表
 *
 * 		特点：
 * 			1. 根据数据的 hash 值来计算数据的存储位置： position = hashCode % arraySize
 * 			2. 负载因子 = 数据项个数 / 数据大小(容量)
 * 			3. 负载因子越大，hash 表越满，越容易导致冲突， 性能也就越低
 *
 * 		优点：
 * 			1. 插入、删除的时间复杂度接近于 O(1)
 *			2. 查询快
 *
 * 		缺点：
 * 			1. 当负载因子过大的时候，需要扩容，重新 hash 并移动数据，性能影响很大
 * 			2. 数据是无序的
 *
 *
 * 		解决 Hash 冲突：(分配 2 倍数据大小的空间)
 * 			1. 开放地址法
 * 				特点： 每次以一定规律向前探测可以存储的位置
 * 				a. 线性探测
 * 				b. 二次探测
 * 				c. 再哈希法
 *
 * 		 	2. 链地址法 (荐)
 * 		 		特点： hash表的单元格可以是：数组、链表、树
 * 		 		比较：
 *
 *
 */
public class MyHashTable {

	public static void main(String[] args){
		// s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
		// s[0] 表示该字符的 ASCII 编码
		int code = "s".hashCode();
	}
}
