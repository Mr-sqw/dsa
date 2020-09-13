package me.javawold.dsa.tree;

/**
 * 树的遍历
 */
public class Traverse {

    /**
     * 遍历 / “先序”遍历
     *
     * @param root
     */
    public void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.println(root.val);

        if (root.children != null) {
            for (TreeNode child : root.children) {
                traverse(child);
            }
        }
    }

}
