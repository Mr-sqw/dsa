package me.javawold.dsa.bfs;

import me.javawold.dsa.tree.TreeNode;

import java.util.*;

public class LevelTraverse {

    /**
     * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return Collections.emptyList();

        List<List<Integer>> lists = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>(size);
            lists.add(list);
            for (int i = 0; i < size; i++) {//当前层的所有节点
                TreeNode node = queue.removeFirst();
                list.add(node.val);

                //向队列中加入当前层节点的子节点，构成下一层节点。
                if (node.left != null) queue.addLast(node.left);
                if (node.right != null) queue.addLast(node.right);
            }
        }

        Collections.reverse(lists);
        return lists;
    }

    /**
     * 给定一个二叉树，检查它是否是镜像对称的。
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
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
                    //当前层对称的两个节点都为null(对称)，不再判断其下一层了。
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
