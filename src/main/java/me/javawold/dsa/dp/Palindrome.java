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
		int len;
		if (s == null || (len = s.length()) == 0) {
			return s;
		}

		// 状态定义。dp[i][j]：下标为[i, j]的子串是否为回文串。
		boolean[][] dp = new boolean[len][len];
		// 初始化状态。每个下标处的单个字符 构成的子串一定是回文串。
		for (int i = 0; i < len; i++) {
			dp[i][i] = true;
		}
		/**/
		char[] charArray = s.toCharArray();
		int begin = 0;
		int maxLen = 1;
		for (int j = 1; j < len; j++) {// i: start, j: end
			for (int i = 0; i < j; i++) {// 字符串子串，必有i<=j，左上到右下对角线的上半区域。逐渐向右移动j，i从小到大依次计算完以j结尾的子串是否是回文子串。
				// 状态转移方程。dp[i][j] = (s[i] == s[j]) and dp[i + 1][j - 1]
				if (charArray[i] != charArray[j]) {
					dp[i][j] = false;
				} else {// s[i] == s[j]
					// 如果[i + 1, j - 1]长度小于 2(即该区间只有一个字符或为空)，即 j-1 - (i+1) + 1 < 2 -> j-i < 3，
					// 则 charArray[i, j] 一定是一个回文子串。
					if (j - i < 3) {
						dp[i][j] = true;
					} else {
						dp[i][j] = dp[i + 1][j - 1];
					}
				}

				// 以i开始、j结尾的子串判断完毕，如果dp[i][j] == true，表示子串 s[i..j]是回文，此时记录回文长度和起始位置
				if (dp[i][j] && j - i + 1 > maxLen) {
					maxLen = j - i + 1;
					begin = i;
				}
			}
		}
		return s.substring(begin, begin + maxLen);
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
	 * 647. 回文子串
	 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
	 *
	 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
	 *
	 *
	 *
	 * 示例 1：
	 *
	 * 输入："abc"
	 * 输出：3
	 * 解释：三个回文子串: "a", "b", "c"
	 * 示例 2：
	 *
	 * 输入："aaa"
	 * 输出：6
	 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
	 *
	 * @param s
	 * @return
	 */
	public int countSubstrings(String s) {
		if (s == null || s.length() == 0)
			return 0;

		char[] chars = s.toCharArray();
		// 状态定义。dp[i][j]：下标为[i, j]的子串是否为回文串。
		boolean[][] dp = new boolean[chars.length][chars.length];
		// 初始化状态。每个下标处的单个字符 构成的子串一定是回文串。
		for (int i = 0; i < chars.length; i++) {
			dp[i][i] = true;
		}
		// 状态转移方程。dp[i][j] = (s[i] == s[j]) and dp[i + 1][j - 1]
		for (int end = 1; end < chars.length; end++) {
			for (int start = 0; start < end; start++) {
				if (chars[start] == chars[end]) {
					if ((end - 1) - (start + 1) <= 0) {
						dp[start][end] = true;
					} else if (dp[start + 1][end - 1]) {
						dp[start][end] = true;
					} else {
						dp[start][end] = false;
					}
				} else {
					dp[start][end] = false;
				}
			}
		}
		//
		int count = 0;
		for (int end = 0; end < chars.length; end++) {
			for (int start = 0; start <= end; start++) {
				if (dp[start][end]) count++;
			}
		}
		return count;
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

		int i = 0;
		int j = list.size() - 1;
		for (; i < j; i++, j--) {
			if (list.get(i).val != list.get(j).val) {
				return false;
			}
		}
		return true;
	}

}
