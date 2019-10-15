package com.cc.struct;

/*
 * author:  RainPoetry
 * date:  2019/10/10
 * description:  跳表
 *
 *
 *
 */

public class SkipList<T> {

    // 最高层数
    private final int MAX_LEVEL;

    // 当前层数
    private int level;

    // 表头
    private SkipListNode<T> head;

    // 表尾
    private SkipListNode<T> NIL;

    // 生成 randomLevel 用到的概率值
    private final double p;

    // 论文里给出的最佳概率值
    private static final double OPTIMAL_P = 0.25;

    public SkipList() {
        this(OPTIMAL_P, (int) Math.ceil(Math.log(Integer.MAX_VALUE) / Math.log(1 / OPTIMAL_P)) - 1);
    }

    public SkipList(double probability, int maxLevel) {
        p = probability;
        MAX_LEVEL = maxLevel;

        level = 1;
        head = new SkipListNode<T>(Integer.MIN_VALUE, null, maxLevel);
        NIL = new SkipListNode<T>(Integer.MAX_VALUE, null, maxLevel);
        for (int i = head.forward.length - 1; i >= 0; i--) {
            head.forward[i] = NIL;
        }
    }

    class SkipListNode<T> {
        int key;
        T value;
        SkipListNode[] forward;

        public SkipListNode(int key, T value, int level) {
            this.key = key;
            this.value = value;
            this.forward = new SkipListNode[level];
        }
    }

    public T get(int searchKey) {
        SkipListNode<T> curNode =head;

        for (int i = level; i > 0; i--) {
            while (curNode.forward[i].key < searchKey) {
                curNode = curNode.forward[i];
            }
        }

        if (curNode.key == searchKey) {
            return curNode.value;
        } else {
            return null;
        }
    }

    public void put(int searchKey, T newValue) {
        SkipListNode<T>[] update = new SkipListNode[MAX_LEVEL];
        SkipListNode<T> curNode = head;

        for (int i = level - 1; i >= 0; i--) {
            while (curNode.forward[i].key < searchKey) {
                curNode = curNode.forward[i];
            }
            // curNode.key < searchKey <= curNode.forward[i].key
            update[i] = curNode;
        }

        curNode = curNode.forward[0];

        if (curNode.key == searchKey) {
            curNode.value = newValue;
        } else {
            int lvl = randomLevel();

            if (level < lvl) {
                for (int i = level; i < lvl; i++) {
                    update[i] = head;
                }
                level = lvl;
            }

            SkipListNode<T> newNode = new SkipListNode<T>(searchKey, newValue, lvl);

            for (int i = 0; i < lvl; i++) {
                newNode.forward[i] = update[i].forward[i];
                update[i].forward[i] = newNode;
            }
        }
    }

    public void delete(int searchKey) {
        SkipListNode<T>[] update = new SkipListNode[MAX_LEVEL];
        SkipListNode<T> curNode = head;

        for (int i = level - 1; i >= 0; i--) {
            while (curNode.forward[i].key < searchKey) {
                curNode = curNode.forward[i];
            }
            // curNode.key < searchKey <= curNode.forward[i].key
            update[i] = curNode;
        }

        curNode = curNode.forward[0];

        if (curNode.key == searchKey) {
            for (int i = 0; i < level; i++) {
                if (update[i].forward[i] != curNode) {
                    break;
                }
                update[i].forward[i] = curNode.forward[i];
            }

            while (level > 0 && head.forward[level - 1] == NIL) {
                level--;
            }
        }
    }

    private int randomLevel() {
        int lvl = 1;
        while (lvl < MAX_LEVEL && Math.random() < p) {
            lvl++;
        }
        return lvl;
    }

    public void print() {
        for (int i = level - 1; i >= 0; i--) {
            SkipListNode<T> curNode = head.forward[i];
            while (curNode != NIL) {
                System.out.print(curNode.key + "->");
                curNode = curNode.forward[i];
            }
            System.out.println("NIL");
        }
    }


    public static void main(String[] args) {
        SkipList list = new SkipList();
        list.put(123,"sasas");
        list.put(2,"hello");
        System.out.println(list.get(123));
        list.print();
    }
}
