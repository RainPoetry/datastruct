package com.cc.algorithm.array

import java.util
import java.util.Arrays

/*
 * author:  RainPoetry
 * date:  2019/8/15
 * description: 
 */

object TwoIntSumScala {

  private val nums = Array[Int](2, 7, 11, 15)

  private val target = 9

  def method1: Array[Int] = {
    val end = nums.length - 1
    for (i <- 0 to end) {
      for (j <- i+1 to end) {
        if (nums(i) + nums(j) == target)
          return Array(nums(i), nums(j))
      }
    }
    Array.empty
  }

  def main(args: Array[String]): Unit = {
    val ints = method1
    System.out.println(ints.mkString(","))
  }

}
