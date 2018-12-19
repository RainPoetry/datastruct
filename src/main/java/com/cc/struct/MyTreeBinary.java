package com.cc.struct;

import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * User: chenchong
 * Date: 2018/12/14
 * description: 二叉树
 * 		特点：
 * 			1. 数据总是在叶子节点进行插入
 * 			2. 从根节点开始访问
 * 			3. 数据的比较次数和树的层数成正比
 * 			4. 树的遍历没有其他的操作快
 * 			5. 如果允许 key 值重复，会导致程序更加复杂，可以在插入的时候限制 key 值
 * 			6. 允许的话，树的删除实现通过一个 boolean 来控制，而不是真正的进行删除操作(这样第5点就能很轻松解决了)
 * 		优点：
 * 			1. 插入快 （和层数有关）
 * 			2. 查找快（相当于二分查找）
 * 			3. 删除快 (实现复杂)
 * 		缺点：
 * 			1. 如果允许 key 值重复，实现起来比较麻烦
 * 			2. 如果插入数据是有序的，会导致二叉树非平衡，使插入、查找、删除性能严重下降
 * 			  (最坏的结果，树的形状是一条斜线，变成了链表)
 */
public class MyTreeBinary<T> {

	private Node<T> root;

	// 查找节点
	public Node<T> find(int key) {
		return finds(key)[0];
	}

	// 节点 和 父节点
	private Node<T>[] finds(int key) {
		Node<T> parent = root;
		Node<T> current = root;
		if (root == null) {
			return new Node[]{current,parent};
		}
		while (current.key != key) {
			parent = current;
			if (key > current.key)
				current = current.right;
			else
				current = current.left;
			if (current == null)
				break;
		}
		return new Node[]{current,parent};
	}

	// 删除
	public boolean delete(int key) {
		if (root == null)
			return false;
		Node<T>[] finds = finds(key);
		Node<T> current = finds[0];
		Node<T> parent = finds[1];
		boolean isLeft = current.key < parent.key ? true : false;
		if (current.left == null && current.right == null) {	// no child
			if (current == root)
				root = null;
			else if (isLeft)
				parent.left = null;
			else
				parent.right = null;
			return true;
		} else if (current.left == null || current.right == null) {	// one child
			Node<T> currentSon = current.left != null ? current.left : current.right;
			if (current == root)
				root = currentSon;
			else if (isLeft)
				parent.left = currentSon;
			else
				parent.right = currentSon;
			return true;
		} else {												//  two childs
			Node<T> successor = SuccessorNode(current);			// 获取后继节点
		 	if (current == root)
		 		root = successor;
		 	else if (isLeft)
		 		parent.left = successor;
		 	else
		 		parent.right = successor;
		 	successor.left = current.left;
			return true;
		}
	}

	// 查找后继节点
	// 如果后继节点是右孙子节点, 更新节点的右子树节点的指向关系
	private Node<T> SuccessorNode(Node<T> node) {
		if (node == null || node.right == null)
			return null;
		Node<T> parent = node;
		Node<T> successor = node.right;
		while (successor.left != null) {	// 遍历获取后继节点
			parent = successor;
			successor = successor.left;
		}
		if (successor != node.right) {		// 后继节点是删除节点的右孙子节点 ?
			parent.left = successor.right;
			successor.right = node.right;
		}
		return successor;
	}

	// 插入
	public void insert(T data, int key) {
		Node node = new Node(data, key);
		if (root == null) {
			this.root = node;
			return;
		}
		Node<T> current = root;
		while (true) {
			if (key < current.key) {  // go left ?
				if (current.left == null) {
					current.left = node;
					return;
				} else {
					current = current.left;
				}
			} else {                // go right
				if (current.right == null) {
					current.right = node;
					return;
				} else {
					current = current.right;
				}
			}
		}
	}

	// 木有左子节点
	public Node<T> minNode() {
		if (root == null)
			return null;
		Node current = root;
		while (current.left != null)
			current = current.left;
		return current;
	}

	// 木有右子节点
	public Node<T> maxNode() {
		if (root == null)
			return null;
		Node<T> current = root;
		while (current.right != null)
			current = current.right;
		return current;
	}

	// 中序遍历
	public void midSearch(Node node, List<Node> list) {
		if (node != null) {
			midSearch(node.left, list);    // 遍历左子树
			list.add(node);					// 访问节点
			midSearch(node.right, list);    // 遍历右子树
		}
	}

	public void displayTree() {
		Stack globalStack =  new Stack();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		String seperate = "-------------------------------------";
		for(int i = 0; i < 4 ;i++)
			System.out.print(seperate);
		System.out.println();
		while (!isRowEmpty) {
			Stack localStack = new Stack();
			isRowEmpty = true;
			for (int i = 0; i < nBlanks; i++)
				System.out.print("  ");
			while (!globalStack.isEmpty()) {
				Node tmp = (Node) globalStack.pop();
				if (tmp != null) {
					System.out.print(tmp.data);
					localStack.push(tmp.left);
					localStack.push(tmp.right);
					if (tmp.left !=null || tmp.right !=null)
						isRowEmpty = false;
				} else {
					System.out.print("null");
					localStack.push(null);
					localStack.push(null);
				}
				for (int i=0; i < nBlanks * 2 - 2; i++)
					System.out.print("  ");
			}
			System.out.println();
			System.out.println();
			nBlanks /= 2;
			while (!localStack.isEmpty())
				globalStack.push(localStack.pop());
		}
		for(int i = 0; i < 4; i++)
			System.out.print(seperate);
		System.out.println();
	}

	public static void main(String[] args) {
		MyTreeBinary<Integer> tree = new MyTreeBinary<>();
		tree.insert(8, 8);
		tree.insert(7, 7);
		tree.insert(19,19);
		tree.insert(17, 17);
		tree.insert(29, 29);
		tree.insert(18,18);
//		System.out.println(tree.toString());
		tree.displayTree();
		tree.delete(17);
		tree.displayTree();
	}

	private class Node<T> {
		final T data;
		final int key;
		Node left;
		Node right;

		Node(T t, int key) {
			this.data = t;
			this.key = key;
		}

		void setLeft(Node node) {
			this.left = node;
		}

		void setRight(Node node) {
			this.right = node;
		}
	}
}
