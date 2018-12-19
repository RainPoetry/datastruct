package com.cc.practice;

import java.util.*;

/**
 * User: chenchong
 * Date: 2018/12/14
 * description:  基于 Map 的 快速排序
 */
public class MapQuickSort extends QuickSort<Map<String, String>> {

	private final String field;

	public MapQuickSort(List<Map<String, String>> list, String field) {
		super(Map.class,list);
		this.field = field;
	}

	public MapQuickSort(Map<String,String>[] list, String field) {
		super(list);
		this.field = field;
	}

	@Override
	protected boolean compare(Map<String, String> target, Map<String, String> pivot) {
		String targetEntity = target.get(field);
		if (targetEntity == null)
			return false;
		String pivotEntity = pivot.get(field);
		if (pivotEntity == null)
			return true;
		return Integer.parseInt(targetEntity) > Integer.parseInt(pivotEntity) ? true  : false;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(field +"排序: " );
		int count = 0 ;
		for(Map<String, String> map : list) {
			String entity = map.get(field);
			if (entity == null)
				sb.append("null");
			else
				sb.append(entity);
			if (++count < list.length)
				sb.append(",");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		List<Map<String,String>> list = new ArrayList<>();
		for(int i=0; i<100; i++) {
			Map<String, String> map = new HashMap<>();
			map.put("key", new Random().nextInt(200)+"");
			if(i%10 == 0)
				map.put("key", null);
			list.add(map);
		}
		MapQuickSort sort = new MapQuickSort(list, "key");
		// 升序
		sort.ascending();
		System.out.println(sort.toString());

	}

	public void array() {
		Map<String,String>[] list = new HashMap[100];
		for(int i=0; i<100; i++) {
			Map<String, String> map = new HashMap<>();
			map.put("key", new Random().nextInt(200)+"");
			list[i] = map;
		}
		MapQuickSort sort = new MapQuickSort(list, "key");
		// 升序
		sort.ascending();
		System.out.println(sort.toList());
	}

}
