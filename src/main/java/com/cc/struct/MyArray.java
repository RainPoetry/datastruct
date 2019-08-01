package com.cc.struct;

/**
 * User: chenchong
 * Date: 2018/12/8
 * description: 数组
 * 		优点：
 * 			插入快
 * 		缺点：
 * 			删除、查找慢
 * 			(删除操作会导致删除节点的后续数据前移)
 *
 * 		特点：
 * 			1. 分配的物理地址在空间上是连续的
 */
public class MyArray<T> {

	T[] a;

	public MyArray(int size) {
		a = (T[]) new Object[size];
	}


	public static void main(String[] args) {
		MyArray<Integer> arrays = new MyArray(10);
		arrays.insert(0, 1);
		arrays.insert(1, 22);
		arrays.insert(1, 22);
		arrays.insert(2, 44);
		System.out.println(arrays.toString(","));
		arrays.delete(22);
		System.out.println(arrays.toString(","));
	}

	public void insert(int index, T o) {
		a[index] = o;
	}

	public T get(int index) {
		return a[index];
	}

	public void delete(T o) {
		int position = -1;
		// 元素检索
		for (int i = 0; i < a.length; i++) {
			if (a[i] == o) {
				position = i;
				break;
			}
		}
		if (position == -1)
			return;
		// 指定元素的后面的数据前移
		for (int i = position; i < a.length - 1; i++) {
			a[i] = a[i + 1];
		}
	}

	public String toString(String seperate) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < a.length; i++) {
			sb.append(a[i] == null ? "" : a[i]);
			if (i != a.length - 1)
				sb.append(seperate);
		}
		return sb.toString();
	}
}
