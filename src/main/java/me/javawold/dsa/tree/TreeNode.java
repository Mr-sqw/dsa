package me.javawold.dsa.tree;

/**
 * 二叉树节点。TreeNode
 */
public class TreeNode {

	public int val;

	public TreeNode left;

	public TreeNode right;

	/**
	 *
	 * @author suqianwen 2020年9月21日
	 */
	public TreeNode() {

	}

	/**
	 *
	 * @param val
	 * @author suqianwen 2020年9月21日
	 */
	public TreeNode(int val) {
		this.val = val;
	}

	/**
	 *
	 * @param val
	 * @param left
	 * @param right
	 * @author suqianwen 2020年9月21日
	 */
	public TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}

}
