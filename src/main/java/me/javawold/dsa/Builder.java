package me.javawold.dsa;

import me.javawold.dsa.linkedlist.ListNode;

public class Builder {

    /**
     *
     * @param arr
     * @return
     */
    public static ListNode buildLinkedList(int[] arr) {
        if (arr == null || arr.length == 0)
            return null;

        ListNode root = new ListNode(arr[0]);
        ListNode prev = root;
        for (int i = 1; i < arr.length; i++) {
            ListNode curr = new ListNode(arr[i]);
            prev.next = curr;
            prev = curr;
        }
        return root;
    }

}
