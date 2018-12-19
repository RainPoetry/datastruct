package com.cc.sort;

import java.util.Arrays;

/**
 * User: chenchong
 * Date: 2018/12/11
 * description: 选择排序
 * 		特点：
 * 			交换次数： n
 * 			比较次数： n * (n-1) / 2
 */
public class SelectionSort extends AbstractSort{

	public SelectionSort(int size) {
		super(size);
	}

	public void sort() {
		int length = datas.length;
		//  总计 n-1 次比较
		for (int k = 0; k < length - 1; k++) {
			int index = k;
			// 获取 <k,length> 范围内最小的数据的索引
			for (int i = k + 1; i < length ; i++) {
				if (datas[i] < datas[index])
					index = i;
			}
			// 将最小数据放在 k 索引下
			swap(k, index);
		}
	}

	public static void main(String[] args) {
		SelectionSort sort = new SelectionSort(20);
		sort.sort();
		System.out.println(Arrays.toString(sort.datas));
	}
}
