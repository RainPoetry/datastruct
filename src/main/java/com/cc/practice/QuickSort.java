package com.cc.practice;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * User: chenchong
 * Date: 2018/12/14
 * description:  快速排序的具体实现
 */
public abstract class QuickSort<T> {

	protected T[] list;

	public T[] asArray() {
		return (T[])this.list;
	}

	public List<T> toList() {
		return Arrays.asList((T[])list);
	}

	public QuickSort(Class<?> c, List<T> list) {
		this.list = (T[])Array.newInstance(c,list.size());
		System.arraycopy(list.toArray(),0,this.list,0,list.size());
	}

	public QuickSort(T[] list) {
		this.list = list;
	}

	// 升序
	public void ascending() {
		sort( true);
	}

	// 降序
	public void descending() {
		sort(false);
	}

	private void sort(boolean reverse) {
		sort(0, list.length - 1, reverse);
	}

	// 具体实现
	private void sort(int from, int to, boolean reverse) {
		if (from >= to)
			return;
		int middle = partition(from, to, list[to], reverse);
		sort(from, middle - 1, reverse);
		sort(middle + 1, to, reverse);
	}

	// 支持正向、逆向排序
	private int partition(int from, int to, T pivot, boolean reverse) {
		int start = from - 1;
		int end = to;
		while (true) {
			// list[++start] > pivot ; break
			while (start < to && compare(list[++start], pivot) ^ reverse);
			// compare(list[--end] < pivot ; break
			while (end > from && compare(pivot, list[--end]) ^ reverse);
			if (start >= end)
				break;
			else
				swap(start, end);
		}
		swap(start, to);
		return start;
	}

	private void swap(int a, int b) {
		T temp = list[a];
		list[a] = list[b];
		list[b] = temp;
	}

	//  target > pivot ? true : false
	protected abstract boolean compare(T target, T pivot);

}
