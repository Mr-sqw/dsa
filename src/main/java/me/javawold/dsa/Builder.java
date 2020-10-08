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
	 * 构建带环的链表
	 *
	 * @param arr
	 * @param startEndIndexes [startIndex1, endIndex1,startIndex2, endIndex2, ...]
	 * @return
	 * @author suqianwen 2020年10月8日
	 */
	public static ListNode buildCycleLinkedList(int[] arr, int... startEndIndexes) {
		if (arr == null || arr.length == 0 //
				|| startEndIndexes == null || startEndIndexes.length == 0 || (startEndIndexes.length & 1) == 1)
			return null;

		ListNode root = new ListNode(arr[0]);
		ListNode prev = root;
		//
		ListNode[] nodes = new ListNode[arr.length];
		nodes[0] = root;
		for (int i = 1; i < arr.length; i++) {
			ListNode curr = new ListNode(arr[i]);
			prev.next = curr;
			prev = curr;

			nodes[i] = curr;
		}
		//
		for (int i = 0; i < startEndIndexes.length; i += 2) {
			nodes[startEndIndexes[i]].next = nodes[startEndIndexes[i + 1]];
		}
		//
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
