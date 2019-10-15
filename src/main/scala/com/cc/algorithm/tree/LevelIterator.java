package com.cc.algorithm.tree;

/*
 * author:  RainPoetry
 * date:  2019/8/30
 * description:
 *
 *          二叉树层次遍历(广度优先遍历)
 *
 *          耗时：86ms, 击败 5.71% 的用户
 *          内存消耗：37.2 MB, 击败 42.16% 的用户
 */

import java.util.*;
import java.util.stream.Collectors;

public class LevelIterator extends AbstractTree {

//    有序 Map
//    NavigableMap<Integer, List<Integer>> datas = new TreeMap<Integer, List<Integer>>();

//    List<List<Integer>> datas = new ArrayList<>(10);

    Map<Integer, List<Integer>> datas = new HashMap<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        int level = 0;
        TreeNode current = root;
        midSearch(root, level);
        List<Map.Entry<Integer, List<Integer>>> lists = new ArrayList<>(datas.entrySet());
        // 应该是这个排序影响了性能
        lists.sort((a, b) -> a.getKey() - b.getKey());
        return lists.stream().map(entry->entry.getValue())
                .collect(Collectors.toList());
    }

    public void midSearch(TreeNode node, int level) {
        if (node == null)
            return;
        midSearch(node.left, level + 1);
        if (node != null)
            addNode(node, level);
        midSearch(node.right, level + 1);
    }

    public void addNode(TreeNode node, int level) {
        List<Integer> list;
        if ((list = datas.get(level)) == null) {
            list = new ArrayList<>();
            datas.put(level, list);
        }
        list.add(node.val);
    }

    public static void main(String[] args) {

        Integer[] arrays = new Integer[]{3, 9, 20, null, null, 15, 7};
        LevelIterator level = new LevelIterator();
        TreeNode node = level.buildTree(arrays);
        System.out.println(level.levelOrder(node));
    }


}


