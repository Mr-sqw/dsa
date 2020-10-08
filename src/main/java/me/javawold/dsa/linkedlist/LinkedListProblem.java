package me.javawold.dsa.linkedlist;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：

给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.
说明：

给定的 n 保证是有效的。

进阶：

你能尝试使用一趟扫描实现吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     * @param n
     * @return
     * @author suqianwen 2020年9月22日
     */
	public ListNode removeNthFromEnd(ListNode head, int n) {
		if (head == null || n <= 0) {
			return head;
		}

		List<ListNode> list = new ArrayList<>();
		do {
			list.add(head);
			head = head.next;
		} while (head != null);

		if (list.size() < n) {
			return list.get(0);
		} else if (list.size() == n) {
			list.get(0).next = null;
			return list.size() > 1 ? list.get(1) : null;
		} else {// list.size() > n ->
			// 0 =< list.size()-n-1 < list.size();
			// 2 =< list.size()-n+1 <= list.size()
			list.get(list.size() - n - 1).next = (n > 1) ? list.get(list.size() - n + 1) : null;
			return list.get(0);
		}
	}

	public ListNode removeNthFromEndByFastSlowPointer(ListNode head, int n) {
		if (head == null || n <= 0) {
			return head;
		}

		ListNode fast = head;
		//
		ListNode slow = head;// 慢指针，倒数第n个节点。
		ListNode slowPrev = null;

		/* 快指针先向前移动n步 */
		int step = 0;
		while (step != n && fast != null) {
			step++;
			fast = fast.next;
		}
		/* 然后快慢指针一起向前移动，直到到达链表尾部 */
		while (fast != null) {//
			fast = fast.next;

			slowPrev = slow;
			slow = slow.next;
		}
		
		if (slowPrev != null) {
			slowPrev.next = slow.next;
		}

		return slow == head/* 是否删除头节点 */ ? slow.next : head;
	}

	/**
	 * 反转一个单链表。

示例:

输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
进阶:
你可以迭代或递归地反转链表。你能否用两种方法解决这道题？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/reverse-linked-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param head
	 * @return
	 * @author suqianwen 2020年9月22日
	 */
	public ListNode reverseList(ListNode head) {
		ListNode prev = null;
		ListNode curr = head;

		while (curr != null) {
			ListNode next = curr.next;
			
			curr.next = prev;//反转
			
			prev = curr;
			curr = next;
		}

		return prev;
	}

    /**
     * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
     *
     * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
     *
     * 示例 1:
     *
     * 输入: 1->2->3->4->5->NULL
     * 输出: 1->3->5->2->4->NULL
     * 示例 2:
     *
     * 输入: 2->1->3->5->6->4->7->NULL
     * 输出: 2->3->6->7->1->5->4->NULL
     * 说明:
     *
     * 应当保持奇数节点和偶数节点的相对顺序。
     * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/odd-even-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     */
    public ListNode oddEvenList(ListNode head) {
        return null;
    }

    public void oddEvenListV2(ListNode head) {
        if (head == null) {
            return;
        }

        ListNode oddHead = null;//奇数
        ListNode oddCurr = null;
        ListNode evenHead = null;//偶数
        ListNode evenCurr = null;
        ListNode curr = head;
        /**/
        while (curr != null) {
            if (curr.val % 2 == 1) {//奇数
                if (oddHead == null) {
                    oddHead = curr;
                    oddCurr =curr;
                } else {
                    oddCurr.next = curr;
                    oddCurr = curr;
                }
            } else {//奇数
                if (evenHead == null) {
                    evenHead = curr;
                    evenCurr = curr;
                } else {
                    evenCurr.next = curr;
                    evenCurr = curr;
                }
            }

            curr = curr.next;
        }


        if (head.val % 2 == 1) {
            oddCurr.next = evenHead;
        } else {
            evenCurr.next = oddHead;
        }
    }

    /**
     * 92. 反转链表 II
     * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
     *
     * 说明:
     * 1 ≤ m ≤ n ≤ 链表长度。
     *
     * 示例:
     *
     * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
     * 输出: 1->4->3->2->5->NULL
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m >= n)
            return head;

        ListNode prev = null;
        ListNode curr = head;
        ListNode tailBeforeReverse = null;
        ListNode tailAfterReverse = null;
        int i = 1;
        do {
            if (i < m) {
                /*向后遍历*/
                prev = curr;
                curr = curr.next;
            } else if (i == m) {
                tailBeforeReverse = prev;//!可能为null
                tailAfterReverse = curr;

                /*向后遍历*/
                prev = curr;
                curr = curr.next;
            } else if (i > m && i < n) {//反转
                ListNode next = curr.next;
                curr.next = prev;//反转

                /*向后遍历*/
                prev = curr;
                curr = next;
            } else if (i == n) {//curr：反转后的head
                ListNode next = curr.next;
                curr.next = prev;//反转

                //反转后的tail 的next 指向 当前的next。
                tailAfterReverse.next = next;

                //无需再向后遍历，直接返回。
                if (tailBeforeReverse == null) {//从第一个开始反转(m == 1)，直接返回 反转后的head
                    return curr;
                } else {//不是从第一个开始反转(m == 1)
                    //反转前的tail 的next 指向 反转后的head。
                    tailBeforeReverse.next = curr;
                    return head;
                }
            }
//            else {// i> n
//                /*无需再向后遍历*/
//                prev = curr;
//                curr = curr.next;
//            }

            i++;
        } while (curr != null);

        i--;//获取实际的节点数。
        if (i <= m) {//链表节点数小于等于m，无需反转
            return head;
        } else/* if (i< n)*/ {//链表节点数 大于m小于n。prev：反转后的head(链表的尾节点(prev))
            //反转后的tail 的next 指向 null。
            tailAfterReverse.next = null;

            if (tailBeforeReverse == null) {//从第一个开始反转(m == 1)，直接返回 反转后的head(链表的尾节点(prev))
                return prev;
            } else {//不是从第一个开始反转(m == 1)
                //反转前的tail 的next 指向 反转后的head(链表的尾节点(prev))。
                tailBeforeReverse.next = prev;
                return head;
            }
        }
    }

    /**
     * 160. 相交链表
编写一个程序，找到两个单链表相交的起始节点。

如下面的两个链表：



在节点 c1 开始相交。

 

示例 1：



输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Reference of the node with value = 8
输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 

示例 2：



输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
输出：Reference of the node with value = 2
输入解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 

示例 3：



输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
输出：null
输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
解释：这两个链表不相交，因此返回 null。
 

注意：

如果两个链表没有交点，返回 null.
在返回结果后，两个链表仍须保持原有的结构。
可假定整个链表结构中没有循环。
程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
通过次数155,060提交次数273,672
     *
     * @param headA
     * @param headB
     * @return
     * @author suqianwen 2020年10月7日
     */
    // 若相交，链表A： a+c, 链表B : b+c. a+c+b+c = b+c+a+c 。则会在公共处c起点相遇。若不相交，a +b = b+a 。因此相遇处是NULL
    // 走到尽头见不到你，于是走过你来时的路，等到相遇时才发现，你也走过我来时的路。
    // 我先走我的路，再走你的路，你先走你的路，再走我的路，这样咱俩走的路程就一样了，速度一样，那么肯定在咱俩两条路的交叉口相遇，并一起走向终点。
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null) {
			return null;
		}

		ListNode nodeA = headA;
		ListNode nodeB = headB;
		while (nodeA != nodeB) {
			nodeA = (nodeA == null) ? headB : nodeA.next;
			nodeB = (nodeB == null) ? headA : nodeB.next;
		}
		return nodeA;
    }

    /**
     * 141. 环形链表
给定一个链表，判断链表中是否有环。

如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。

如果链表中存在环，则返回 true 。 否则，返回 false 。

 

进阶：

你能用 O(1)（即，常量）内存解决此问题吗？

 

示例 1：



输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。
示例 2：



输入：head = [1,2], pos = 0
输出：true
解释：链表中有一个环，其尾部连接到第一个节点。
示例 3：



输入：head = [1], pos = -1
输出：false
解释：链表中没有环。
 

提示：

链表中节点的数目范围是 [0, 104]
-105 <= Node.val <= 105
pos 为 -1 或者链表中的一个 有效索引 。
     *
     * @param head
     * @return
     * @author suqianwen 2020年10月8日
     */
	public boolean hasCycle(ListNode head) {
		if (head == null) {
			return false;
		}

		ListNode fast = head.next;
		ListNode slow = head;
		while (fast != null && fast.next != null) {
			/* 如果链表有环，则快慢指针一直在环内移动，并且某个时候，快指针一定会赶上慢指针 */
			if (fast == slow) {
				return true;
			}
			fast = fast.next.next;
			slow = slow.next;
		}
		return fast == slow;// 如果链表无环，则快指针提前到达尾节点，结束
	}

}
