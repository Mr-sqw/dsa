package me.javawold.dsa;

import me.javawold.dsa.linkedlist.ListNode;

import java.util.ArrayList;
import java.util.List;

public class TwoSumProblem {

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
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
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
        if (l1 == null) {//!
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
        if (bitSum >= 10) {//有进位
            node.next = new ListNode(1);
        }
        return head;
    }

    /**
     * 66. 加一
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     *
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     *
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     *
     * 示例 1:
     *
     * 输入: [1,2,3]
     * 输出: [1,2,4]
     * 解释: 输入数组表示数字 123。
     * 示例 2:
     *
     * 输入: [4,3,2,1]
     * 输出: [4,3,2,2]
     * 解释: 输入数组表示数字 4321。
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0)
            return digits;

        List<Integer> list = new ArrayList<>(digits.length);
        int sum = digits[digits.length - 1] + 1;
        list.add(sum % 10);
        //
        for (int i = digits.length - 2; i >= 0; i--) {
            sum = digits[i] + sum / 10;//sum/10：进位
            list.add(sum % 10);
        }
        if (sum >= 10) {
            list.add(1);
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(list.size() - 1 - i);
        }
        return result;
    }

}


