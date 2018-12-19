package com.cc.sort;

import java.util.Arrays;

/**
 * User: chenchong
 * Date: 2018/12/11
 * description:  插入排序
 * 		特点：
 * 			交换次数： n
 * 			比较次数：n * (n-1) / 4
 */
public class InsertSort extends AbstractSort{

	public InsertSort(int size) {
		super(size);
	}

	public void sort() {
		int length = datas.length;
		for (int i = 1; i < length ; i++) {
			int temp = datas[i];
			int index = i;
			// 从 index 位置开始，向左依次比较数据大小
			// 如果  数据 > temp , 则数据右移一位
			while(index > 0 && datas[index-1] > temp) {
				// 数据右移
				datas[index] = datas[index-1];
				--index; // 每有一个数据 > temp ， temp 的位置就左移一位
			}
			datas[index] = temp;
		}
	}

	public static void main(String[] args) {
		InsertSort sort = new InsertSort(100);
		sort.sort();
		System.out.println(Arrays.toString(sort.datas));
	}
}
