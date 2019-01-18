package com.cc.sourcecode;

import java.util.Arrays;

/**
 * User: chenchong
 * Date: 2018/12/20
 * description:	ArrayList 源码分析
 *
 * 		ArrayList 的所有操作，实际上都是基于数组实现的
 *
 * 		扩容：
 * 			newCapacity = oldCapacity + (oldCapacity >> 1);
 * 			elementData = Arrays.copyOf(elementData, newCapacity);
 *
 * 		旧数组数据 -> 新的数组：
 * 			Arrays.copyOf(elementData, newCapacity);
 *
 * 		数组内部数据移动(新增、删除操作)：
 * 			System.arraycopy(this.elementData, var1 + 1, this.elementData, var1, var3);
 */
public class MyArrayList {

	// 数据的存储方式
	Object[] elementData;

	/**
	 * 扩容处理
	 *
	 * @param minCapacity
	 */
	private void grow(int minCapacity) {
		// overflow-conscious code
		int oldCapacity = elementData.length;
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		if (newCapacity - minCapacity < 0)
			newCapacity = minCapacity;
//		if (newCapacity - MAX_ARRAY_SIZE > 0)
//			newCapacity = hugeCapacity(minCapacity);
		// minCapacity is usually close to size, so this is a win:
		elementData = Arrays.copyOf(elementData, newCapacity);
	}
}
