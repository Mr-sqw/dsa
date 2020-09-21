package me.javawold.dsa.linkedlist;

public class MergeTwoLists {

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

}
