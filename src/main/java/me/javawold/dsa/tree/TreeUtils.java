package me.javawold.dsa.tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <br>
 * <br>
 * Copyright: Copyright (c) 2015<br>
 * Company: 北京云杉世界信息技术有限公司<br>
 * 
 *
 * @author suqianwen 2020年9月21日
 */
public class TreeUtils {

	private TreeUtils() {

	}

	/**
	 * 构建完全二叉树(full binary tree)
	 *
	 * @param arr
	 * @return
	 * @author suqianwen 2020年9月21日
	 */
	public static TreeNode buildFST(Integer[] arr) {
		if (arr == null || arr.length == 0) {
			return null;
		}

		int i = 0;
		TreeNode root = new TreeNode(arr[i++]);
		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int j = 0; j < size; j++) {// 当前层
				TreeNode parent = queue.remove();

				//
				if (i < arr.length) {
					TreeNode left = new TreeNode(arr[i++]);
					parent.left = left;
					queue.add(left);
				} else {
					return root;
				}

				//
				if (i < arr.length) {
					TreeNode right = new TreeNode(arr[i++]);
					parent.right = right;
					queue.add(right);
				} else {
					return root;
				}
			}
		}

		return root;
	}

}
