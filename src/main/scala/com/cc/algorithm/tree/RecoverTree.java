package com.cc.algorithm.tree;

/*
 * author:  RainPoetry
 * date:  2019/8/29
 * description:
 *
 *      还原二叉树
 *
 *           输入: [3,1,4,null,null,2]
 *
 *            3
 *           / \
 *          1   4
 *             /
 *            2
 *
 *          输出: [2,1,4,null,null,3]
 *
 *            2
 *           / \
 *          1   4
 *             /
 *            3
 *
 *       解法一：
 *          获取二叉树的所有值，然后对值进行排序，最后按照中序遍历的方式，将值设置到对应的节点
 *
 *      解法二：
 *
 *
 */

import java.util.ArrayList;
import java.util.List;

public class RecoverTree extends AbstractTree{

    TreeNode firstNode = null;
    TreeNode secondNode = null;
    TreeNode preNode = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {

        in_order(root);
        int tmp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = tmp;
    }

    private void in_order(TreeNode root) {
        if (root == null) return;
        in_order(root.left);
        if (firstNode == null && preNode.val > root.val) firstNode = preNode;
        if (firstNode != null && preNode.val > root.val) secondNode = root;
        preNode = root;
        in_order(root.right);
    }

    public static void main(String[] args) {
        Integer[] arrays = new Integer[]{3, 1, 4, null, null, 2};
        RecoverTree tree = new RecoverTree();
        TreeNode node = tree.buildTree(arrays);
        tree.printTree(node);
    }


    public void printTree(TreeNode node) {
        System.out.println(iterateTree(node).toString());
    }

    public List<String> iterateTree(TreeNode node) {
        List<String> lists = new ArrayList<>();
        if (node == null) {
            return new ArrayList<>();
        }
        lists.addAll(iterateTree(node.left));
        if (node != null)
            lists.add(node.val + "");
        else
            lists.add("null");
        lists.addAll(iterateTree(node.right));
        return lists;
    }

}


