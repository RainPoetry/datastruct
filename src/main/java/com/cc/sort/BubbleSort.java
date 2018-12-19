package com.cc.sort;

import java.util.Arrays;

/**
 * User: chenchong
 * Date: 2018/12/11
 * description:  冒泡排序
 * 		特点：
 * 			交换次数： n * (n-1) / 4 (平均)
 * 			比较次数： n * (n-1) / 2
 *
 */
public class BubbleSort extends AbstractSort{

	public BubbleSort(int size) {
		super(size);
	}

	public void sort() {
		int length = datas.length;
		//  总计 n-1 次比较
		for (int k = 0; k < length - 1; k++) {
			// 每循环一次，得到最大值，放到数组的最右边
			for (int i = 0; i < length - 1 - k; i++) {
				// 任何数据两两之间进行比较和交换，这就导致了很多不必要的交换操作
				// （因为算法的最终目的是将最大的数据放入到数组的最后边）
				if (datas[i] > datas[i + 1])
					swap(i, i + 1);
			}
		}
	}

	public static void main(String[] args) {
		BubbleSort sort = new BubbleSort(30);
		sort.sort();
		System.out.println(Arrays.toString(sort.datas));
	}
}
