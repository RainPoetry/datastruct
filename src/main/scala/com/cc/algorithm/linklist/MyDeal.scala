package com.cc.algorithm.linklist

/*
 * author:  RainPoetry
 * date:  2019/8/19
 * description: 
 */

object MyDeal {

  def main(args: Array[String]): Unit = {

    val data = Seq("aaa","bbb","ccc")

    data.foldLeft("ccc"){
      case a => {
        println(a)
        a._1
      }
    }
  }

}
