package me.javawold.dsa.linkedlist;

public class LinkedListProblem {

    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
     *
     *  
     *
     * 示例：
     *
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param head1
     * @param head2
     * @return
     */
    public ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }

        if (head2 == null) {
            return head1;
        }

        ListNode newHead = null;
        if (head1.val <= head2.val) {
            newHead = new ListNode(head1.val);
            head1 = head1.next;
        } else {
            newHead = new ListNode(head2.val);
            head2 = head2.next;
        }
        //
        ListNode newCurrNode = newHead;
        ListNode tmp;
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                tmp = new ListNode(head1.val);
                head1 = head1.next;
            } else {
                tmp = new ListNode(head2.val);
                head2 = head2.next;
            }

            newCurrNode.next = tmp;
            //
            newCurrNode = tmp;
        }
        //
        innerMerge(head1, newCurrNode);
        //
        innerMerge(head2, newCurrNode);

        return newHead;
    }

    private void innerMerge(ListNode node, ListNode prev) {
        while (node != null) {
            ListNode tmp = new ListNode(node.val);
            prev.next = tmp;

            prev = tmp;

            node = node.next;
        }
    }

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     *
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     *
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * 示例：
     *
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-two-numbers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }

        int bitSum = l1.val + l2.val;
        ListNode head = new ListNode(bitSum % 10);
        ListNode node = head;
        while ((l1 = l1.next) != null && (l2 = l2.next) != null) {
            bitSum = l1.val + l2.val + bitSum / 10;
            ListNode tmpNode = new ListNode(bitSum % 10);
            node.next = tmpNode;
            node = tmpNode;
        }
        if (l1 == null) {
            l2 = l2.next;
        }
        while (l1 != null) {
            bitSum = l1.val + bitSum / 10;
            ListNode tmpNode = new ListNode(bitSum % 10);
            node.next = tmpNode;
            node = tmpNode;
            l1 = l1.next;
        }
        while (l2 != null) {
            bitSum = l2.val + bitSum / 10;
            ListNode tmpNode = new ListNode(bitSum % 10);
            node.next = tmpNode;
            node = tmpNode;
            l2 = l2.next;
        }
        /**/
        if (bitSum >= 10) {
            node.next = new ListNode(1);
        }
        return head;
    }

}
