package me.javawold.dsa.bfs;

import me.javawold.dsa.linkedlist.ListNode;
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
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
     *
     *  
     *
     * 示例：
     * 二叉树：[3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其层次遍历结果：
     *
     * [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
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

        return lists;
    }

    /**
     * 103. 二叉树的锯齿形层次遍历
给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

例如：
给定二叉树 [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回锯齿形层次遍历如下：

[
  [3],
  [20,9],
  [15,7]
]
     *
     * @param root
     * @return
     * @author suqianwen 2020年10月23日
     */
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		if (root == null)
			return Collections.emptyList();

		List<List<Integer>> lists = new ArrayList<>();
		Deque<TreeNode> queue = new ArrayDeque<>();
		queue.addLast(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			List<Integer> list = new ArrayList<>(size);
			lists.add(list);
			for (int i = 0; i < size; i++) {// 当前层的所有节点
				TreeNode node = queue.removeFirst();

				list.add(node.val);

				// 向队列中加入当前层节点的子节点，构成下一层节点。
				if (node.left != null)
					queue.addLast(node.left);
				if (node.right != null)
					queue.addLast(node.right);
			}
		}

		for (int i = 1; i < lists.size(); i += 2) {
			List<Integer> list = lists.get(i);
			for (int j = 0, k = list.size() - 1; j < k; j++, k--) {
				int temp = list.get(j);
				list.set(j, list.get(k));
				list.set(k, temp);
			}
		}
		//
		return lists;
	}

    /**
     * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。
     *
     *  
     *
     * 示例：
     *
     * 输入：[1,2,3,4,5,null,7,8]
     *
     *         1
     *        /  \
     *       2    3
     *      / \    \
     *     4   5    7
     *    /
     *   8
     *
     * 输出：[[1],[2,3],[4,5,7],[8]]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/list-of-depth-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root
     * @return
     */
    public ListNode[] listOfDepth(TreeNode root) {
        if (root == null){
            return null;
        }

        List<ListNode> list = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            ListNode head = null;
            int size = queue.size();
            for (int i = 0; i < size; i++) {//当前层的所有节点
                TreeNode node = queue.removeFirst();

                if (head == null){
                    head = new ListNode(node.val);
                    list.add(head);
                }else{
                    head.next = new ListNode(node.val);
                    head = head.next;
                }

                //向队列中加入当前层节点的子节点，构成下一层节点。
                if (node.left != null) queue.addLast(node.left);
                if (node.right != null) queue.addLast(node.right);
            }
        }

        return list.toArray(new ListNode[list.size()]);
    }

}
