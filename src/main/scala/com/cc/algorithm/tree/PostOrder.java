package com.cc.algorithm.tree;

/*
 * author:  RainPoetry
 * date:  2019/9/4
 * description:
 *
 *      后序遍历
 *          分别采用 递归 和 迭代 的方式来进行实现
 *
 *
 *          平时中，尽可能的使用迭代，因此，递归的深度达到一定级别会产生 StackOverFlow 异常
 *
 *
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class PostOrder extends AbstractTree {


    // 递归 - StackOverFlow
    public List<Integer> recursive(TreeNode root) {
        return search(root);
    }

    public List<Integer> search(TreeNode node) {
        List<Integer> lists = new ArrayList<>();
        if (node == null)
            return lists;
        lists.addAll(search(node.left));
        lists.addAll(search(node.right));
        lists.add(node.val);
        return lists;
    }

    // 迭代 - Best
    public List<Integer> iteration(TreeNode root) {
        LinkedList<TreeNode> nodes = new LinkedList<>();
        LinkedList<Integer> lists = new LinkedList<>();

        // 链尾插入
        nodes.add(root);

        while (!nodes.isEmpty()) {
            // 从最后一个开始取
            TreeNode node = nodes.pollLast();
            lists.addFirst(node.val);

            if (node.left != null)
                nodes.add(node.left);

            if (node.right!=null)
                nodes.add(node.right);
        }
        return lists;
    }

    public static void main(String[] args) {
        Integer[] arrays = new Integer[]{3, 9, 20, null, null, 15, 7};
        PostOrder order = new PostOrder();
        TreeNode node = order.buildTree(arrays);
        System.out.println(order.iteration(node));
    }
}
