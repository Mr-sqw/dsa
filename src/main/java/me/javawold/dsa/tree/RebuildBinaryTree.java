package me.javawold.dsa.tree;

/**
 * <br>
 * <br>
 * Copyright: Copyright (c) 2015<br>
 * Company: 北京云杉世界信息技术有限公司<br>
 * 
 *
 * @author suqianwen 2020年9月21日
 */
public class RebuildBinaryTree {
	
	/**
	 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。

 

例如，给出

前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
返回如下的二叉树：

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param preorder
	 * @param inorder
	 * @return
	 * @author suqianwen 2020年9月21日
	 */
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return doBuildTree(preorder, 0, inorder, 0, inorder.length - 1);
	}

	/**
	 * 
	 *
	 * @param preorder     整棵树的前序遍历序列
	 * @param preorderRoot 左/右子树的根节点在其前序遍历序列中的下标
	 * @param inorder      整棵树的中序遍历序列
	 * @param inorderStart 左/右子树的中序遍历序列的开始下标
	 * @param inorderEnd   左/右子树的中序遍历序列的结束下标
	 * @return
	 * @author suqianwen 2020年9月21日
	 */
	private TreeNode doBuildTree(int[] preorder, int preorderRoot, int[] inorder, int inorderStart, int inorderEnd) {
		if (/*
			 * preorderHead == preorder.length// ||
			 */ inorderStart > inorderEnd) {// 子树为空
			return null;
		}

		int rootVal = preorder[preorderRoot];
		TreeNode root = new TreeNode(rootVal);

		/** 找到根节点在中序遍历序列中的位置，将中序遍历序列分为根节点的左右子树的中序遍历序列 */
		int inorderRoot = inorderStart;
		for (; inorderRoot <= inorderEnd; inorderRoot++) {
			if (inorder[inorderRoot] == rootVal) {
				break;
			}
		}

		// 递归求解 左子树
		TreeNode left = doBuildTree(preorder, preorderRoot + 1, inorder, inorderStart, inorderRoot - 1);
		root.left = left;

		// 递归求解 右子树
		// 右子树的根节点在其前序遍历序列中的下标等于原根节点下标 + 左子树的节点数 + 1
		TreeNode right = doBuildTree(preorder, preorderRoot + (inorderRoot - inorderStart) + 1, inorder, inorderRoot + 1,
				inorderEnd);
		root.right = right;

		return root;
	}

}
