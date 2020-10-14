package me.javawold.dsa.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 树的遍历
 */
public class Traverse {

    /**
     * 遍历 / “先序”遍历
     *
     * @param root
     */
    public void traverse(NTreeNode root) {
        if (root == null) {
            return;
        }

        System.out.println(root.val);

        if (root.children != null) {
            for (NTreeNode child : root.children) {
                traverse(child);
            }
        }
    }

    /**
     * 94. 二叉树的中序遍历
给定一个二叉树，返回它的中序 遍历。

示例:

输入: [1,null,2,3]
   1
    \
     2
    /
   3

输出: [1,3,2]
进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     *
     * @param root
     * @return
     * @author suqianwen 2020年10月14日
     */
	public List<Integer> inorderTraversal(TreeNode root) {
		if (root == null) {
			return Collections.emptyList();
		}

		List<Integer> list = new ArrayList<>();
		Deque<TreeNode> stack = new LinkedList<>();
		TreeNode node = root;//
		//
		while (node != null || !stack.isEmpty()) {
			/* 将以node为根节点的子树 的 最左分支节点入栈 */
			// 左
			while (node != null) {
				stack.push(node);

				node = node.left;
			}

			// 最左节点(或 ...节点)出栈，并访问
			// 根
			node = stack.pop();
			list.add(node.val);

			// 右
			node = node.right;
		}

		return list;
	}

}
