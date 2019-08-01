package com.cc.sort;

import java.util.Arrays;

/**
 * User: chenchong
 * Date: 2018/12/11
 * description: 快速排序
 *
 * 		时间复杂度： O(n * logN)
 *
 *
 * 		缺点：
 * 			当迭代层数过大时，会导致堆栈溢出
 */
public class QuickSort extends AbstractSort{

	public QuickSort(int size) {
		super(size);
	}

	public void sort() {
		sort(0, datas.length - 1);
	}


	public void sort(int start, int end) {
		if (start >= end)
			return;
		int pivot = datas[end];
		int partition = partition(start, end, pivot);
		sort(start, partition - 1);
		sort(partition +1, end);
	}


	// 数据划分
	// 高配版
	public int partition(int from, int to, int pivot) {
		int start =  from -1;
		int end = to;
		while (true) {
			// 从左边开始, 找到 > pivot 的数据项
			while (datas[++start] < pivot);
			// 从右边开始， 找到 < pivot 的数据项
			while (end > 0 && datas[--end] > pivot);
			// 当 start = end 的时候，此时一定是 datas[start] > pivot 的最小值
			if (start >= end)
				break;
			else
				swap(start, end);
		}
		// 将 pivot 放到 < pivot 和 > pivot 两批数据的中间
		swap(start, to);
		return start;
	}

	// 数据划分
	// 低配版
	/*public void partition(int start, int end, int pivot) {
		while (start <= end) {
			if (datas[start] < pivot && datas[end] > pivot) {
				start++;
				end--;
			} else if (datas[start] < pivot && datas[end] < pivot) {
				start++;
			} else if (datas[start] > pivot && datas[end] > pivot) {
				end--;
			} else {
				swap(start, end);
				start++;
				end--;
			}
		}
	}
*/

	public static void main(String[] args) {
		QuickSort sort = new QuickSort(100);
		sort.sort();
		System.out.println(Arrays.toString(sort.datas));
	}
}
