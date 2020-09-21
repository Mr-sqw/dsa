package me.javawold.dsa.recursion;

import me.javawold.dsa.tree.TreeNode;
import me.javawold.dsa.tree.TreeUtils;

public class RangeSumBST {

	/**
	 * 给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。

二叉搜索树保证具有唯一的值。

 

示例 1：

输入：root = [10,5,15,3,7,null,18], L = 7, R = 15
输出：32
示例 2：

输入：root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
输出：23

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/range-sum-of-bst
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param root
	 * @param L
	 * @param R
	 * @return
	 * @author suqianwen 2020年9月21日
	 */
    public int rangeSumBST(TreeNode root, int L, int R) {
        rangeSumBSTByMidOrder(root, L, R, new boolean[2]);
        return rangeSumBSTByPreOrder(root, L, R);
    }

    private int rangeSumBSTByPreOrder(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }

        int rangeSum = 0;

        // head
        if (root.val >= L && root.val <= R) {
            rangeSum = root.val;
        }
        // left
        rangeSum += rangeSumBSTByPreOrder(root.left, L, R);
        // right
        rangeSum += rangeSumBSTByPreOrder(root.right, L, R);

        return rangeSum;
    }

    private int rangeSumBSTByMidOrder(TreeNode root, int L, int R, boolean[] found) {// problem
        if (root == null) {
            return 0;
        }
        if (found[1]) {// R found
            return 0;
        }

        int rangeSum = 0;

        // left
        rangeSum += rangeSumBSTByMidOrder(root.left, L, R, found);

        // head
        if (found[1]) {// R found
            return rangeSum;
        }
        if (found[0]) {// L found
            rangeSum += root.val;
        } else { // L+R not found
            if (root.val == L) {// L found
                rangeSum += root.val;
                found[0] = true;
            }
            if (root.val == R) {// R found
                rangeSum += root.val;
                found[1] = true;
            }
        }

        // right
        rangeSum += rangeSumBSTByMidOrder(root.right, L, R, found);

        return rangeSum;
    }

	public static void main(String[] args) {
		Integer[] arr = { 10, 5, 15, 3, 7, null, 18 };
		TreeNode root = TreeUtils.buildFST(arr);
		int sum = new RangeSumBST().rangeSumBST(root, 7, 15);
		System.out.println(sum);
	}

}