package me.javawold.dsa.tree;

import java.util.*;

/**
 * 镜像/对称二叉树
 */
public class SymmetricTree {

    /**
     * 给定一个二叉树，检查它是否是镜像对称的。
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        //自顶向下从第二层开始检查该层对称的两个节点
        //自顶向下先检查最外层的三角形，再检查次外层的三角形...
        return check(root.left, root.right);
    }

    /**
     * @param left  同一层对称的两个节点之left
     * @param right 同一层对称的两个节点之right
     * @return
     */
    private boolean check(TreeNode left, TreeNode right) {
        if (left == null) {
            if (right != null) {
                return false;
            } else {
                //当前层对称的两个节点都为null(镜像)，不再判断其下一层了。
                return true;
            }
        } else {
            if (right == null || right.val != left.val) {
                return false;
            } else {//当前层对称的两个节点都不为null且镜像，判断其下一层两组对称的两个节点是否镜像*/
                return check(left.left, right.right) && check(left.right, right.left);
            }
        }
    }

    /**
     * 给定一个二叉树，检查它是否是镜像对称的。
     *
     * @param root
     * @return
     */
    public boolean isSymmetricByBFS(TreeNode root) {
        if (root == null) return true;

        Deque<TreeNode> queue = new LinkedList<>();//可以添加null元素
        //将根节点的两个子节点紧挨着加入队列；后续向队列中加入节点，也必须保证同一层对称的两个节点紧挨着一起加入队列
        queue.addLast(root.left);
        queue.addLast(root.right);

        while (!queue.isEmpty()) {
            //同时取出同一层对称的两个节点
            TreeNode left = queue.removeFirst();
            TreeNode right = queue.removeFirst();

            if (left == null) {
                if (right != null) {
                    return false;
                } else {
                    //当前层对称的两个节点都为null(镜像)，不再判断其下一层了。
                }
            } else {
                if (right == null || right.val != left.val) {
                    return false;
                } else {//当前层对称的两个节点都不为null且镜像，将其下一层对称的两个节点紧挨在一起加入队列*/
                    queue.addLast(left.left);
                    queue.addLast(right.right);
                    //
                    queue.addLast(left.right);
                    queue.addLast(right.left);
                }
            }
        }

        return true;
    }

    public boolean isSymmetricV2(TreeNode root) {
        if (root == null) return true;

        Deque<TreeNode> queue = new ArrayDeque<>();//不能添加null元素
        queue.addLast(root);
        TreeNode nullNode = new TreeNode();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {//当前层的所有节点
                TreeNode node = queue.removeFirst();
                if (node != nullNode) {
                    list.add(node.val);
                    queue.addLast(node.left != null ? node.left : nullNode);
                    queue.addLast(node.right != null ? node.right : nullNode);
                } else {
                    list.add(null);
                    queue.addLast(nullNode);
                    queue.addLast(nullNode);
                }
            }

            for (int i = 0, j = size - 1; i < j; i++, j--) {
                if (list.get(i) == null) {
                    if (list.get(j) != null) return false;
                } else if (list.get(j) == null || !list.get(j).equals(list.get(i))) {
                    return false;
                }
            }
        }

        return true;
    }

}
