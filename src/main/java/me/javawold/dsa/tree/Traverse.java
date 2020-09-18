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
    public void traverse(NTreeNode root) {
        if (root == null) {
            return;
        }

        System.out.println(root.val);

        if (root.children != null) {
            for (NTreeNode child : root.children) {
                traverse(child);
            }
        }
    }

}
