package me.javawold.dsa.sort;

import me.javawold.dsa.Builder;
import me.javawold.dsa.linkedlist.ListNode;

public class SortProblem {

    /**
     * 148. 排序链表
     * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
     *
     * 示例 1:
     *
     * 输入: 4->2->1->3
     * 输出: 1->2->3->4
     * 示例 2:
     *
     * 输入: -1->5->3->4->0
     * 输出: -1->0->3->4->5
     *
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode fast = head;
        ListNode slow = head.next;
        while (slow != null && slow.next != null) {
            fast = fast.next;
            slow = slow.next.next;
        }

        //
        ListNode left = head;//[head, fast]
        ListNode right = fast.next;//(fast, ]
        fast.next = null;//!劈成左右两部分
        //排序左半部分
        left = sortList(left);
        //排序右半部分
        right = sortList(right);

        /*合并*/
        ListNode newHead = null;
        ListNode newCurr = null;
        while (left != null && right != null) {
            if (left.val < right.val) {
                if (newHead == null) {
                    newHead = left;
                    newCurr = left;
                } else {
                    newCurr.next = left;
                    newCurr = left;
                }

                left = left.next;
            } else {
                if (newHead == null) {
                    newHead = right;
                    newCurr = right;
                } else {
                    newCurr.next = right;
                    newCurr = right;
                }

                right = right.next;
            }
        }
        //
        if (left != null) {
            if (newHead == null) {
                newHead = left;
                // newCurr = left;
            } else {
                newCurr.next = left;
            }
        }
        //
        if (right != null) {
            if (newHead == null) {
                newHead = right;
                // newCurr = right;
            } else {
                newCurr.next = right;
            }
        }

        return newHead;//返回合并后的链表
    }

    public static void main(String[] args) {
        ListNode root = Builder.buildLinkedList(new int[]{4, 2, 1, 3});
        new SortProblem().sortList(root);
    }

}
