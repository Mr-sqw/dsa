package me.javawold.dsa.dp;

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

}
