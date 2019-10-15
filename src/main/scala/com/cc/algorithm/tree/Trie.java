package com.cc.algorithm.tree;

/*
 * author:  RainPoetry
 * date:  2019/8/21
 * description:
 *
 *      前缀树(字典树)
 *
 *      用途：
 *          1. google 搜索中的自动补全
 *          2. 拼写检查
 *          3. IP 路由(最长前缀匹配)
 *          4. 打字预测
 *
 *
 *      示例：
 *          Trie trie = new Trie();
 *
 *          trie.insert("apple");
 *          trie.search("apple");   // 返回 true
 *          trie.search("app");     // 返回 false
 *          trie.startsWith("app"); // 返回 true
 *          trie.insert("app");
 *          trie.search("app");     // 返回 true
 *
 *
 *
 */

public class Trie {

    private final TreeNode root;

    private class TreeNode {
        char c;
        TreeNode father;
        TreeNode[] childs;

        TreeNode(char c, TreeNode father) {
            this.c = c;
            this.father = father;
            this.childs = new TreeNode[26];
        }

        boolean isEnd = false;
    }

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        this.root = new TreeNode('*', null);
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        char[] chars = word.toCharArray();
        TreeNode father = root;
        for (char c : chars) {
            int position = position(c);
            TreeNode node = father.childs[position];
            if (node == null) {
                node = new TreeNode(c, father);
                father.childs[position] = node;
            }
            father = node;
        }
        father.isEnd = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        char[] chars = word.toCharArray();
        TreeNode father = root;
        for (char c : chars) {
            int position = position(c);
            TreeNode current = father.childs[position];
            if (current == null)
                return false;
            father = current;
        }
        return true && father.isEnd;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        char[] chars = prefix.toCharArray();
        TreeNode father = root;
        for (char c : chars) {
            int position = position(c);
            TreeNode current = father.childs[position];
            if (current == null)
                return false;
            father = current;
        }
        return true;
    }

    public int position(char c) {
        return hashCode(c) % 26;
    }

    public int hashCode(char c) {
        return Character.hashCode(c);
    }


    public static void main(String[] args) {

        Trie trie = new Trie();
        trie.insert("apple");

        System.out.println(trie.search("apple"));// 返回 true
        System.out.println(trie.search("app")); // false
        System.out.println(trie.startsWith("app"));  // 返回 true

        trie.insert("app");
        System.out.println(trie.search("app"));   // 返回 true

    }
}
