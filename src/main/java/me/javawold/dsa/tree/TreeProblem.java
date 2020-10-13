package me.javawold.dsa.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import me.javawold.dsa.Builder;

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

	/**
	 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
	 *
	 *  
	 *
	 * 例如：
	 *
	 * 输入: 原始二叉搜索树:
	 *               5
	 *             /   \
	 *            2     13
	 *
	 * 输出: 转换为累加树:
	 *              18
	 *             /   \
	 *           20     13
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/convert-bst-to-greater-tree
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 * @param root
	 * @return
	 */
	public TreeNode convertBST(TreeNode root) {
		if (root == null)
			return null;

		doConvertBST(root, new AtomicInteger(0));
		return root;
	}

	/**
	 * 反序中序遍历。先右节点，再根节点，最后左节点，不断计算累加和。
	 *
	 * @param root
	 * @param sum
	 * @author suqianwen 2020年9月23日
	 */
	private void doConvertBST(TreeNode root, AtomicInteger sum) {
		if (root == null)
			return;

		// 1.先右节点
		doConvertBST(root.right, sum);

		// 2.再根节点
		/* 每个节点的值 是 原来的节点值 + 所有大于它的节点值之和(转换后的右子树的根节点值)， */
		int val = sum.addAndGet(root.val);
		root.val = val;

		// 3.最后左节点
		doConvertBST(root.left, sum);
	}

	/**
	 * 543. 二叉树的直径
	 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。

 

示例 :
给定二叉树

          1
         / \
        2   3
       / \     
      4   5    
返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。

 

注意：两结点之间的路径长度是以它们之间边的数目表示。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/diameter-of-binary-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param root
	 * @return
	 * @author suqianwen 2020年9月23日
	 */
	public int diameterOfBinaryTree(TreeNode root) {
		if (root == null) {
			return 0;
		}

		AtomicInteger maxNodeCount = new AtomicInteger(0);// 最长直径所在路径的节点数
		doDiameterOfBinaryTree(root, maxNodeCount);

		return maxNodeCount.get() - 1;
	}

	private int doDiameterOfBinaryTree(TreeNode root, AtomicInteger maxNodeCount) {
		if (root == null) {
			return 0;
		}

		// 左子树的最长路径的节点数
		int leftMaxNodeCount = doDiameterOfBinaryTree(root.left, maxNodeCount);
		int rightMaxNodeCount = doDiameterOfBinaryTree(root.right, maxNodeCount);

		// 当前子树最长直径所在路径：树根节点连接的左子树的最长路径 + 右子树的最长路径
		int nodeCount = leftMaxNodeCount + rightMaxNodeCount + 1;
		maxNodeCount.set(Math.max(maxNodeCount.get(), nodeCount));

		// 返回该子树的最长路径的节点数
		return Math.max(leftMaxNodeCount, rightMaxNodeCount) + 1;
	}

	/**
	 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。

你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。

示例 1:

输入:
	Tree 1                     Tree 2
          1                         2
         / \                       / \
        3   2                     1   3
       /                           \   \
      5                             4   7
输出:
合并后的树:
	     3
	    / \
	   4   5
	  / \   \
	 5   4   7
注意: 合并必须从两个树的根节点开始。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/merge-two-binary-trees
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param t1
	 * @param t2
	 * @return
	 * @author suqianwen 2020年9月29日
	 */
	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		if (t1 == null) {
			return t2;
		}

		if (t2 == null) {
			return t1;
		}

		TreeNode newNode = new TreeNode(t1.val + t2.val);
		newNode.left = mergeTrees(t1.left, t2.left);
		newNode.right = mergeTrees(t1.right, t2.right);
		return newNode;
	}

	/**
	 * 给定两个二叉树，编写一个函数来检验它们是否相同。
	 *
	 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
	 *
	 * 示例 1:
	 *
	 * 输入:       1         1
	 *           / \       / \
	 *          2   3     2   3
	 *
	 *         [1,2,3],   [1,2,3]
	 *
	 * 输出: true
	 * 示例 2:
	 *
	 * 输入:      1          1
	 *           /           \
	 *          2             2
	 *
	 *         [1,2],     [1,null,2]
	 *
	 * 输出: false
	 * 示例 3:
	 *
	 * 输入:       1         1
	 *           / \       / \
	 *          2   1     1   2
	 *
	 *         [1,2,1],   [1,1,2]
	 *
	 * 输出: false
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/same-tree
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null) {
			if (q != null) {
				return false;
			} else {
				return true;
			}
		} else {
			if (q == null) {
				return false;
			} else if (q.val != p.val) {
				return false;
			} else {
				return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
			}
		}
	}

    /**
     * 114. 二叉树展开为链表
     * 给定一个二叉树，原地将它展开为一个单链表。
     *
     *
     *
     * 例如，给定二叉树
     *
     *     1
     *    / \
     *   2   5
     *  / \   \
     * 3   4   6
     * 将其展开为：
     *
     * 1
     *  \
     *   2
     *    \
     *     3
     *      \
     *       4
     *        \
     *         5
     *          \
     *           6
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        List<TreeNode> list = new LinkedList<>();
        doFlatten(root, list);
        /**/
        for (int i = 0; i < list.size() - 1; i++) {
            TreeNode node = list.get(i);
            node.left = null;
            node.right = list.get(i + 1);
        }
        //
        list.get(list.size() - 1).left = null;
        list.get(list.size() - 1).right = null;
    }

    private void doFlatten(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }

        //先根节点
        list.add(root);
        //再左子树
        doFlatten(root.left, list);
        //最后右子树
        doFlatten(root.right, list);
    }

	public static void main(String[] args) {
		Integer[] arr = { 2, 3, null, 1 };
		TreeNode root = Builder.buildFST(arr);
		new TreeProblem().diameterOfBinaryTree(root);
	}

}
