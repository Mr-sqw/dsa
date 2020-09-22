package me.javawold.dsa.dp;

import me.javawold.dsa.linkedlist.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 回文字符串问题<br>
 * <br>
 * Copyright: Copyright (c) 2015<br>
 * Company: 北京云杉世界信息技术有限公司<br>
 * 
 *
 * @author suqianwen 2020年9月20日
 */
public class Palindrome {
	
	/**
	 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：
输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。

示例 2：
输入: "cbbd"
输出: "bb"

https://leetcode-cn.com/problems/longest-palindromic-substring/
	 *
	 * @param s
	 * @return
	 * @author suqianwen 2020年9月20日
	 */
	public String longestPalindrome(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}

		// 状态定义。dpTable[i][j]：下标为[i, j]的子串是否为回文串。
		boolean[][] dpTable = new boolean[s.length()][s.length()];
		// 初始化状态。每个下标处的单个字符子串一定是回文串。
		for (int i = 0; i < dpTable.length; i++) {
			dpTable[i][i] = true;
		}
		// 状态转移方程。
		char[] charArr = s.toCharArray();
		int longestPalindromeLength = 1;
		int longestPalindromeStartIndex = 0;
		int longestPalindromeEndIndex = 0;
		for (int i = 0; i < dpTable.length; i++) {
			for (int j = i + 1; j < dpTable.length; j++) {// 左上到右下对角线的上半区域。

			}
		}

		return s.substring(longestPalindromeStartIndex, longestPalindromeEndIndex + 1);
	}
	
	/**
	 * 中心扩散法
	 *
	 * @param s
	 * @return
	 * @author suqianwen 2020年9月21日
	 */
	public String longestPalindromeV2(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		
		char[] charArr = s.toCharArray();
		int longestPalindromeLength = 1;
		int longestPalindromeStartIndex = 0;
		int longestPalindromeEndIndex = 0;
		for (int i = 0; i < charArr.length; i++) {
			
		}
		
		
		return s.substring(longestPalindromeStartIndex, longestPalindromeEndIndex + 1);
	}

	/**
	 * 请判断一个链表是否为回文链表。
	 *
	 * 示例 1:
	 *
	 * 输入: 1->2
	 * 输出: false
	 * 示例 2:
	 *
	 * 输入: 1->2->2->1
	 * 输出: true
	 * 进阶：
	 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 * @param head
	 * @return
	 */
	public boolean isPalindrome(ListNode head) {
		if (head == null) return true;

		List<ListNode> list = new ArrayList<>();
		do {
			list.add(head);
			head = head.next;
		} while (head != null);

		int i = 0, j = list.size() - 1;
		for (; i < j; i++, j--) {
			if (list.get(i).val != list.get(j).val) {
				return false;
			}
		}
		return true;
	}

}
