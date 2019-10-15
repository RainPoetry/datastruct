package com.cc.algorithm.tree;

/*
 * author:  RainPoetry
 * date:  2019/8/30
 * description:
 *
 *         二叉树的最大深度
 */

import java.util.Iterator;

public class MaxDepth extends AbstractTree{

    public static void main(String[] args) {
        Integer[] arrays = new Integer[]{3, 1, 4, null, null, 2};
        MaxDepth depth = new MaxDepth();
        TreeNode node = depth.buildTree(arrays);
        System.out.println(max(node));
    }


    public static int max(TreeNode node) {
        return node == null ? 0 : Math.max(max(node.left), max(node.right)) + 1;
    }
}
