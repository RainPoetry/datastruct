package com.cc.algorithm.tree;

/*
 * author:  RainPoetry
 * date:  2019/8/30
 * description:
 *
 *         工具类
 */

public class AbstractTree {

    protected class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 通过数组生成二叉树
    public TreeNode buildTree(Integer[] datas) {
        if (datas == null || datas.length == 0 || datas[0] == null)
            return null;
        TreeNode[] nodes = new TreeNode[datas.length];
        for (int i = 0; i < datas.length; i++) {
            if (datas[i] == null)
                continue;
            TreeNode current;
            if ((current = nodes[i]) == null) {
                current = new TreeNode(datas[i]);
                nodes[i] = current;
            }
            if (i * 2 + 1 < datas.length && datas[i * 2 + 1] != null) {
                current.left = new TreeNode(datas[i * 2 + 1]);
                nodes[i * 2 + 1] = current.left;
            }
            if (i * 2 + 2 < datas.length && datas[i * 2 + 2] != null) {
                current.right = new TreeNode(datas[i * 2 + 2]);
                nodes[i * 2 + 2] = current.right;
            }
        }
        return nodes[0];
    }
}
