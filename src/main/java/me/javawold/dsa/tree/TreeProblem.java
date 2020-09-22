package me.javawold.dsa.tree;

/**
 * <br>
 * <br>
 * Copyright: Copyright (c) 2015<br>
 * Company: 北京云杉世界信息技术有限公司<br>
 * 
 *
 * @author suqianwen 2020年9月22日
 */
public class TreeProblem {

	/**
	 * 翻转一棵二叉树。

示例：

输入：

     4
   /   \
  2     7
 / \   / \
1   3 6   9
输出：

     4
   /   \
  7     2
 / \   / \
9   6 3   1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/invert-binary-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 *递归：自顶向下
	 * @param root
	 * @return
	 * @author suqianwen 2020年9月22日
	 */
	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return root;
		}

		TreeNode left = invertTree(root.left);
		TreeNode right = invertTree(root.right);
		root.left = right;
		root.right = left;

		return root;
	}

	/**
	 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]



 

示例 1:

输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出: 3
解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
示例 2:

输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出: 5
解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 

说明:

所有节点的值都是唯一的。
p、q 为不同节点且均存在于给定的二叉树中。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 * @author suqianwen 2020年9月22日
	 */
	public TreeNode lowestCommonAncestorV2(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null //
		// || root == p || root == q// 在当前子树的根节点中找到了p或q，直接返回
		) {
			return root;
		}

		boolean findPorQ = root == p || root == q;// 在当前子树的根节点中找到了p或q
		TreeNode left = lowestCommonAncestorV2(root.left, p, q);
		TreeNode right = lowestCommonAncestorV2(root.right, p, q);

		if (findPorQ) {// 在当前子树的根节点中找到了p或q
			if (left != null || right != null) {// 在当前子树的左子树或右子树中发现了p或q，则当前节点就是最近公共祖先
				return root;
			}
			return root;// 向上层调用返回在当前子树的根节点中找到了p或q
		} else {/**/
			if (left != null && right != null) {/* 在当前子树的左子树和右子树中分别发现了p和q，则当前节点就是最近公共祖先 */
				return root;
			}
			return left != null ? left : right;// 向上层调用返回在当前子树的左子树或右子树中找到了p或q
		}
	}

	/**
	 * 最近公共祖先节点不为节点本身
	 *
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 * @author suqianwen 2020年9月22日
	 */
	public TreeNode lowestCommonAncestorV1(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == p || root == q// 在当前子树的根节点中找到了p或q，直接返回
		) {
			return root;
		}

		TreeNode left = lowestCommonAncestorV2(root.left, p, q);
		TreeNode right = lowestCommonAncestorV2(root.right, p, q);

		if (left != null && right != null) {/* 在当前子树的左子树和右子树中分别发现了p和q，则当前节点就是最近公共祖先 */
			return root;
		}
		return left != null ? left : right;// 向上层调用返回在当前子树的左子树或右子树中找到了p或q
	}

}
