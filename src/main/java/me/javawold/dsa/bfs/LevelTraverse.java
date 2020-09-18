package me.javawold.dsa.bfs;

import me.javawold.dsa.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

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
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.removeFirst();
                list.add(node.val);

                if (node.left != null) queue.addLast(node.left);
                if (node.right != null) queue.addLast(node.right);
            }
        }

        Collections.reverse(lists);
        return lists;
    }


}
