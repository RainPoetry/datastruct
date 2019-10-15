package com.cc.algorithm.tree;

/*
 * author:  RainPoetry
 * date:  2019/9/3
 * description:
 *
 *
 *      广度优先遍历优化：
 *
 *          采用前序遍历的方式替换原来的中序遍历方式
 *
 *          采用 List 数据格式替换掉 Map 形式的数据存储方式
 *
 *          解决了 Map 结构数据中的需要重新排序问题
 *
 */

import java.util.ArrayList;
import java.util.List;

public class LevelIteratorV2 extends AbstractTree {

    List<List<Integer>> lists = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return lists;
        search(root, 0);
        return lists;
    }

    public void search(TreeNode node, int dept) {
        if (lists.size() == dept)
            lists.add(new ArrayList<>());

        lists.get(dept).add(node.val);
        if (node.left != null)
            search(node.left, dept + 1);
        if (node.right != null)
            search(node.right, dept + 1);
    }

    public static void main(String[] args) {

        Integer[] arrays = new Integer[]{3, 9, 20, null, null, 15, 7};
        LevelIteratorV2 level = new LevelIteratorV2();
        TreeNode node = level.buildTree(arrays);
        System.out.println(level.levelOrder(node));
    }


}
