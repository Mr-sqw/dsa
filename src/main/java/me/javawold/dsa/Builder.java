package me.javawold.dsa;

import me.javawold.dsa.linkedlist.ListNode;
import me.javawold.dsa.tree.TreeNode;
import me.javawold.dsa.tree.TreeUtils;

public class Builder {

	private Builder() {

	}

	/**
	 *
	 * @param arr
	 * @return
	 */
	public static ListNode buildLinkedList(int[] arr) {
		if (arr == null || arr.length == 0)
			return null;

		ListNode root = new ListNode(arr[0]);
		ListNode prev = root;
		for (int i = 1; i < arr.length; i++) {
			ListNode curr = new ListNode(arr[i]);
			prev.next = curr;
			prev = curr;
		}
		return root;
	}

	/**
	 * 构建完全二叉树(full binary tree)
	 *
	 * @param arr
	 * @return
	 * @author suqianwen 2020年9月21日
	 */
	public static TreeNode buildFST(Integer[] arr) {
		return TreeUtils.buildFST(arr);
	}

}
