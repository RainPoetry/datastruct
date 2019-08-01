package com.cc.utils;

import java.util.Arrays;
import java.util.Random;

/**
 * User: chenchong
 * Date: 2019/1/18
 * description:
 */
public class Generate {

	public static int[] randInt(int size, int bound) {
		int[] data = new int[size];
		Random random = new Random();
		for(int i=0; i < size ;) {
			data[i++] = random.nextInt(bound);
		}
		return data;
	}

	public static void main(String[] args){
		 

	}
}
