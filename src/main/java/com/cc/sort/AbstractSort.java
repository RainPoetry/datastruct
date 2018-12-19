package com.cc.sort;

import java.util.Random;

/**
 * User: chenchong
 * Date: 2018/12/13
 * description:
 */
public abstract class AbstractSort {

	protected  final int[] datas ;

	public AbstractSort(int size) {
		datas = new int[size];
		Random random = new Random();
		for(int i=0; i < size ;) {
			datas[i++] = random.nextInt(150);
		}
	}

	public abstract void sort();

	protected void swap(int a, int b) {
		int temp = datas[a];
		datas[a] = datas[b];
		datas[b] = temp;
	}
}
