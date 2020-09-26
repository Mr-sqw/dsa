package me.javawold.dsa.tree.bst;

import me.javawold.dsa.tree.TreeNode;

import java.util.concurrent.atomic.AtomicLong;

public class BSTProblem {

    /**
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     *
     * 假设一个二叉搜索树具有如下特征：
     *
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 解题方案：
     *  1.https://leetcode-cn.com/problems/validate-binary-search-tree/solution/yi-zhang-tu-rang-ni-ming-bai-shang-xia-jie-zui-da-/
     *  2.bst的中序遍历序列是升序序列
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        return doValidBST(root, null, null);
    }

    /**
     *
     * @param root 子树的root
     * @param leftMax 子树的root的父节点的父节点
     * @param rightMin 子树的root的父节点
     * @return
     */
    public boolean doValidBST(TreeNode root, TreeNode leftMax, TreeNode rightMin) {
        if (root == null) return true;
        if (leftMax != null && root.val >= leftMax.val) return false;
        if (rightMin != null && root.val <= rightMin.val) return false;
        return doValidBST(root.left, root, rightMin) //当前节点的值是其左子树的值的上界（最大值）。左子树的值都小于父节点的值，大于...
                && doValidBST(root.right, leftMax, root)//当前节点的值是其右子树的值的下界（最小值）。右子树的值都大于于父节点的值，小于父节点的父节点的值
                ;
    }

    public boolean isValidBSTByMidOrder(TreeNode root) {
        if (root == null) return true;

        return doValidBSTByMidOrder(root, new AtomicLong(Long.MIN_VALUE));
    }

    public boolean doValidBSTByMidOrder(TreeNode root, AtomicLong last) {
        if (root == null) return true;

        if (!doValidBSTByMidOrder(root.left, last)) {
            return false;
        }

        if (root.val <= last.get()) {
            return false;
        }
        last.set(root.val);

        return doValidBSTByMidOrder(root.right, last);
    }

    public boolean isValidBSTV2(TreeNode root) {//不正确
        if (root == null) return true;
        if (root.left != null && root.val <= root.left.val) return false;
        if (root.right != null && root.val >= root.right.val) return false;
        return isValidBSTV2(root.left) && isValidBSTV2(root.right);
    }

}
