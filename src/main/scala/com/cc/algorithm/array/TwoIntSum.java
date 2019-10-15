package com.cc.algorithm.array;

/*
 * author:  RainPoetry
 * date:  2019/8/15
 * description:  两数之和
 *
 *  给定数组 nums 和目标值 target,找出和为目标值的两个整数
 *
 *      核心概念：
 *          哈希表的快速查找
 *
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoIntSum {

    private static final int[] nums = new int[]{2, 7, 11, 15};

    private static final int target = 9;

    /**
     *  双重循环：
     *      时间复杂度： O(n^2)
     */
    public static int[] method1() {
        int end = nums.length - 1;
        for (int i = 0; i < end; i++) {
            for (int j = i + 1; j < end; j++) {
                if (nums[i] + nums[j] == target)
                    return new int[]{nums[i], nums[j]};
            }
        }
        return null;
    }


    /**
     *  hash 计算：
     *      时间复杂度：O(n)
     *      缺点： 只有找出一对整数，
     *      如果存在多对整数的话，则没有办法，主要是因为 nums 可能存在重复的整数问题
     */
    public static int[] method2(){
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i< nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                return new int[] {map.get(target-nums[i]),i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        int[] ints = method1();
        System.out.println(Arrays.toString(ints));
    }
}
