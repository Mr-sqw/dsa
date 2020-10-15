package me.javawold.dsa.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <br>
 * <br>
 * Copyright: Copyright (c) 2015<br>
 * Company: 北京云杉世界信息技术有限公司<br>
 * 
 *
 * @author suqianwen 2020年10月8日
 */
public class StringProblem {

	/**
	 * 344. 反转字符串
编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。

不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。

你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。

 

示例 1：

输入：["h","e","l","l","o"]
输出：["o","l","l","e","h"]
示例 2：

输入：["H","a","n","n","a","h"]
输出：["h","a","n","n","a","H"]
	 *
	 * @param s
	 * @author suqianwen 2020年10月8日
	 */
	public void reverseString(char[] s) {
		if (s == null || s.length == 0) {
			return;
		}

		for (int i = 0, j = s.length - 1; i < j; i++, j--) {
			char c = s[i];
			s[i] = s[j];
			s[j] = c;
		}
    }

	/**
	 * 1002. 查找常用字符
给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。

你可以按任意顺序返回答案。

 

示例 1：

输入：["bella","label","roller"]
输出：["e","l","l"]
示例 2：

输入：["cool","lock","cook"]
输出：["c","o"]
 
	 *
	 * @param A
	 * @return
	 * @author suqianwen 2020年10月14日
	 */
	public List<String> commonChars(String[] A) {
		if (A == null || A.length == 0) {
			return Collections.emptyList();
		}

		int rowLength = A.length;
		int columnLength = 26;
		int[][] countMatrix = new int[rowLength][columnLength];
		for (int i = 0; i < rowLength; i++) {
			char[] chars = A[i].toCharArray();
			for (char c : chars) {
				countMatrix[i][c - 'a']++;// 统计 当前字符在当前字符串中 的出现次数
			}
		}
		/**/
		List<String> list = new ArrayList<>();
		for (int j = 0; j < columnLength; j++) {// 字符a-z
			int minCount = countMatrix[0][j];// 当前字符在所有字符串中出现的最小次数
			for (int i = 1; i < rowLength && minCount != 0; i++) {
				minCount = Math.min(minCount, countMatrix[i][j]);
			}
			//
			if (minCount >= 1) {
				String string = String.valueOf((char) ('a' + j));
				for (int i = 0; i < minCount; i++) {
					list.add(string);
				}
			}
		}
		//
		return list;
	}

	/**
	 * 387. 字符串中的第一个唯一字符
给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。

 

示例：

s = "leetcode"
返回 0

s = "loveleetcode"
返回 2
 

提示：你可以假定该字符串只包含小写字母。
	 *
	 * @param s
	 * @return
	 * @author suqianwen 2020年10月15日
	 */
	public int firstUniqChar(String s) {
		if (s == null || s.length() == 0) {
			return -1;
		}

		// 字符在字符串中的出现次数
		int[] countArr = new int[26];
		// 字符在字符串中第一次出现的下标。
		int[] firstIndexArr = new int[26];
		for (int i = 0; i < firstIndexArr.length; i++) {
			firstIndexArr[i] = -1;
		}
		/**/
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			int index = chars[i] - 'a';

			countArr[index]++;

			if (firstIndexArr[index] != -1) {
				firstIndexArr[index] = i;
			}
		}
		//
		for (int i = 0; i < countArr.length; i++) {
			if (countArr[i] == 1) {
				return firstIndexArr[i];
			}
		}
		return -1;
	}

}
