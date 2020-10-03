package me.javawold.dsa.tree;

import java.util.concurrent.atomic.AtomicInteger;

public class BBSTProblem {

    /**
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     *
     * 本题中，一棵高度平衡二叉树定义为：
     *
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
     *
     * 示例 1:
     *
     * 给定二叉树 [3,9,20,null,null,15,7]
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回 true 。
     *
     * 示例 2:
     *
     * 给定二叉树 [1,2,2,3,3,null,null,4,4]
     *
     *        1
     *       / \
     *      2   2
     *     / \
     *    3   3
     *   / \
     *  4   4
     * 返回 false 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/balanced-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        AtomicInteger height = new AtomicInteger(0);
        return validateBBST(root, height);
    }

    public boolean validateBBST(TreeNode root, AtomicInteger height) {
        if (root == null) {
            height.set(0);
            return true;
        }

        //验证左子树
        if (!validateBBST(root.left, height)) {
            return false;
        }
        int leftHeight = height.get();
        //验证右子树
        if (!validateBBST(root.right, height)) {
            return false;
        }
        int rightHeight = height.get();
        /**/
        //计算当前树高
        height.set(1 + Math.max(leftHeight, rightHeight));
        //当前树是否是BBST
        return Math.abs(leftHeight - rightHeight) <= 1;
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(height(root.left), height(root.right));
    }

}
