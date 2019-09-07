package com.cc.sourcecode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * User: chenchong
 * Date: 2018/12/19
 * description:	HashMap 源码分析
 * 		特点：
 * 			1. 允许 key 、value 为 null
 * 			2. 数据无序
 * 			3. 不是线程安全 (Collections.synchronizedMap 可以使 HashMap 线程安全)
 * 			4. get 、 put 操作效率很高
 * 			5. fail-fast 机制： 在 迭代过程中，如果有其他的线程修改了 HashMap, 则抛出 ConcurrentModificationException
 * 								( 迭代器本身进行修改时允许的 )
 * 		链表和红黑树相互切换：
 * 		哈希表的单元格内的元素个数 > 8 时， 链表 -> 红黑树
 * 		哈希表的单元格内的元素个数 < 6 时， 红黑树 -> 链表
 *
 * 		下面是哈希表中单元格的元素个数出现概率的分析：(泊松分布)
 * 		单元格的元素个数		发生概率
 * 			0:    0.60653066
 * 			1:    0.30326533
 * 			2:    0.07581633
 * 			3:    0.01263606
 * 			4:    0.00157952
 * 			5:    0.00015795
 * 			6:    0.00001316
 * 			7:    0.00000094
 * 			8:    0.00000006
 *
 * 		因此， 单元格 > 8 的可能性很小 , 也就是，我们使用的 HashMap，绝大多数情况的单元格都是以链表来存储
 *
 * 		影响 HashMap 性能：(尽量增大 initialCapacity ， 减小 loadFactor , 以减少 hash 冲突)
 * 			1. 	initialCapacity 	HashMap 的容量大小
 * 				(设置太高，影响迭代性能; 设置太低，导致频繁扩容)
 * 				配置	 initialCapacity 会直接影响 threshold 的值
 * 			2. 	loadFactor			负载因子
 *				( 数据达到何时进行扩容 . 设置高了，影响查询效率; 设置低了，资源浪费，数据量的临界点低了)
 *
 *		重构优化点：
 *			1. 数据的存储是单链表
 *				a. 每次查询都需要遍历一遍链表，判断是否存在key，不存在在最后插入数据
 *				b. 可以把单链表改成双端有序链表。
 * 			2. 采用 二叉树 代替 链表 ?
 * 				a. 二叉树和红黑树之间的结构转换效率比链表高
 * 				b. 二叉树的查找效率 >= 链表
 * 			3.  数据的扩容：
 * 				a. 在 HashMap 中，数据的扩容是重新分配一个 2 倍的数组空间，然后把原来数据的数据全部迁移过来
 * 				b. 在实际的数据迁移过程中，可能只有少部分数据的地址空间发生改变，大部分数据的地址空间是不变的，因此
 * 					可以新建一个和原来的地址空间等大的数组，只把需要迁移的那部分数据迁移过去，然后只需维护这些数组就可以了。
 */
public abstract class MyHashMap<K,V> {

	// 最大容量 ：10 亿
	static final int MAXIMUM_CAPACITY = 1 << 30;

	// 默认容量大小： 16
	static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

	// 默认负载因子大小： 0.75
	static final float DEFAULT_LOAD_FACTOR = 0.75f;

	// 链表 -> 红黑树 的阀值
	static final int TREEIFY_THRESHOLD = 8;

	// 红黑树 -> 链表 的阀值
	static final int UNTREEIFY_THRESHOLD = 6;

	// 哈希表的容量大小
	MyHashMap.Node<K,V>[] table;

	// 容量大小
	int size;

	// 判断是否需要扩容的阀值
	// threshold = loadFactor * size
	int threshold;

	// 链表节点
	static abstract class Node<K,V> implements Map.Entry<K,V> {}


	// 红黑树
	static final class TreeNode<K,V>  {

	}

	// 分配或扩容 table 的存储空间, 设置 threshold
	// 如果 table 有数据时，进行数据迁移
	//  数据迁移(以数组中的单元格为操作单元)：
	//		将 hashcode & oldCap == 0 (新增比特位是否为0)的数据用链表连接起来，放入到原来数组的 index 位置
	//		将 hashcode & oldCap == 1 (新增比特位是否为1)的数据用链表连接起来，放入到原来数组的 index+oldCap 位置
	// 		这就相当于只有 hashcode & oldCap == 1 数据放入了新增的地址里面
	//	1.8 对 resize 方法已经重写了，在并发情况下，不会导死循环的产生，但是容易发生另一个问题，就是数据丢失，
	//  1.8 在对 table 按照索引进行遍历的时候，会将单元格里原来链表的数据拆分为两个链表然后存储到 index 和 index+oldCap 单元格内，
	//  在这之后，当原来 index 处的单元格新增数据时，新增数据不会插入到即将发生迁移的链表中，从而导致数据丢失
	//  数据迁移指针：
	// 			总共 4 个指针，2  个高位数据指针， 2 个低位数据指针 ， 分别用于原空间 和 新增空间的地址迁移
	//			那 2 个指针 = 头指针(指向链表顶部) + 尾指针(指向链尾)
	//			尾指针 通过 next 将新数据追加在链表的末尾
	//			头指针 存储在 hash 表中 , 用于 hash 表的访问
	abstract  MyHashMap.Node<K,V>[] resize();

	// 1. 计算 key 的 hash 值 (数据 hashcode 的 高 16位与低 16 位取余)
	// 2. resize() ?
	// 3. 获取数据的存储单元格： index = (size-1) & hashCode
	// 4.1  如果单元格为 null ,  则新建一个 Node
	// 4.2  如果单元格中的数据的 hashCode = 传进来的 hashcode, 则获取该 Node
	// 4.3  如果单元格的数据结构是树， 则插入 ，返回该 Node
	// 4.3  如果单元格的数据结构是链表， 则判断是否变成树 ， 然后返回该 Node
	public abstract V put(K key, V value);


	/* 保证函数返回值是大于等于给定参数initialCapacity最小的2的幂次方的数值。
	* 1. 因为计算数据的索引 ： index = (size-1) & hashCode
	* 		当 size 是 2 的幂次方的时候，与 hashcode 的与运算结果更为均匀（也就是数据分布更加均匀）
	* 2. 在扩容 HashMap 的时候，数据的存储地址不虚要重新计算，
	* 		如果 hashcode & 扩容前的size == 0 ( 数据的 hashcode 在新增的 bit 位 为 0 )
	* 			那么，数据的存储地址不需要改变
	* 		如果 hashcode & 扩容前的size == 1 ( 数据的 hashcode 在新增的 bit 位 为 1 )
	* 			那么，数据的存储地址 = 数据的原来地址 + 扩容前的size
	* 			并且迁移到扩容区域的数据所在的单元格都是同一个原来单元格的数据
	*/
	static final int tableSizeFor(int cap) {
		int n = cap - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
	}

	/* 计算 hashCode
	*  将 hash 值和高 16 位进行异或计算得到新的 hashCode
	*  如果只是单纯的 hashCode & （size-1） 很容易造成 hash 冲突 (因为只有数据的低位才参与了计算，高位没有参与计算)
	*  (当数据的 hashCode > size 的时候，所有的值都堆积在最后一个区域，没有做到均匀分布)
	 *  缺点：
	* 		当 key 的 hashcode > 2^16 且存储空间比较小的时候, 会造成哈希表的最后一个单元格频繁发生 hash 冲突
	*		(概率微乎其微)
	*
	*
	*/
	static final int hash(Object key) {
		int h;
		return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	}

	// * s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
	// string 类型的 hashcode 计算方式
	//
	public int hashCode(char[] value) {
		int h = 0;
		if (value.length > 0) {
			char val[] = value;
			for (int i = 0; i < value.length; i++) {
				h = 31 * h + val[i];
			}
		}
		return h;
	}


	public static void main(String[] args){
		System.out.println(hash("1"));
	}
}
