package com.cc.algorithm.linklist;

/*
 * author:  RainPoetry
 * date:  2019/8/17
 * description: 两数相加
 *
 *    给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 *   如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 *           输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 *           输出：7 -> 0 -> 8
 *          原因：342 + 465 = 807
 *
 *
 *
 *   核心概念：
 *         % ： 低位
 *         \ :  进位
 *
 */

public class TwoIntAdd {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private static final ListNode node1;
    private static final ListNode node2;

    static {
        ListNode node1_1 = new ListNode(2);
        ListNode node1_2 = new ListNode(4);
        ListNode node1_3 = new ListNode(3);
        node1_1.next = node1_2;
        node1_2.next = node1_3;
        node1 = node1_1;

        ListNode node2_1 = new ListNode(5);
        ListNode node2_2 = new ListNode(6);
        ListNode node2_3 = new ListNode(8);
        node2_1.next = node2_2;
        node2_2.next = node2_3;
        node2 = node2_1;
    }

    // 个人解法
    // 缺点： 算法中穿插了大量的 if else
    public static ListNode addTwoNode() {
        int carry = 0;
        ListNode first = null;
        ListNode index = null;
        ListNode startNode = node1;
        ListNode startNode2 = node2;
        while (startNode != null || startNode2 != null || carry != 0) {
            ListNode current;
            int s1 = startNode == null ? 0 : startNode.val;
            int s2 = startNode2 == null ? 0 : startNode2.val;
            if (s1 + s2 >= 10) {
                current = new ListNode(s1 + s2 - 10 + carry);
                carry = 1;
            } else {
                current = new ListNode(s1 + s2 + carry);
                carry = 0;
            }
            if (first == null) {
                first = current;
                index = current;
            } else {
                first.next = current;
                first = first.next;
            }
            startNode = startNode == null ? startNode : startNode.next;
            startNode2 = startNode2 == null ? startNode2 : startNode2.next;
        }
        return index;
    }


    // 官方解法
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    public static int add(int a, int b) {
        int carry = a ^ b;
        return 0;
    }

    public static void main(String[] args) {

        ListNode node = addTwoNumbers(node1,node2);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
}
