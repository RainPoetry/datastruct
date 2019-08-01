package com.cc.sourcecode;

/**
 * User: chenchong
 * Date: 2019/1/26
 * description:
 *
 *		特点：
 *			1. 有序
 *			2. 继承自 HashMap
 *
 *
 *		有序的实现（存储单元为 链表时）：
 *			1.  HashMap.Node 的插入通过判断 next 指针是否为空，在队尾完成插入
 *			2. 	LinkedHashMap.Entry 在 HashMap.Node 的基础上增加了 before, after指针，将 Hash 表中的所有的 链表内的数据联系在了一起
 */
public class LinkedHashMap {
}
