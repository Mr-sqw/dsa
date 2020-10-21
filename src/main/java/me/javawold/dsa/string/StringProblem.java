package me.javawold.dsa.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
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

			if (firstIndexArr[index] == -1) {
				firstIndexArr[index] = i;
			}
		}
		//
		long minFirstIndex = Long.MAX_VALUE;
		for (int i = 0; i < countArr.length; i++) {
			if (countArr[i] == 1) {// 不重复
				if (firstIndexArr[i] == 0) {
					return 0;
				}
				minFirstIndex = Math.min(minFirstIndex, firstIndexArr[i]);
			}
		}
		return minFirstIndex == Long.MAX_VALUE ? -1 : (int) minFirstIndex;
	}

	/**
	 * 844. 比较含退格的字符串
给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。

注意：如果对空文本输入退格字符，文本继续为空。

 

示例 1：

输入：S = "ab#c", T = "ad#c"
输出：true
解释：S 和 T 都会变成 “ac”。
示例 2：

输入：S = "ab##", T = "c#d#"
输出：true
解释：S 和 T 都会变成 “”。
示例 3：

输入：S = "a##c", T = "#a#c"
输出：true
解释：S 和 T 都会变成 “c”。
示例 4：

输入：S = "a#c", T = "b"
输出：false
解释：S 会变成 “c”，但 T 仍然是 “b”。
 

提示：

1 <= S.length <= 200
1 <= T.length <= 200
S 和 T 只含有小写字母以及字符 '#'。
 

进阶：

你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
 
	 *
	 * @param S
	 * @param T
	 * @return
	 * @author suqianwen 2020年10月19日
	 */
	public boolean backspaceCompare(String S, String T) {
		if (S == null) {
			return T == null;
		} else { // S != null
			if (T == null) {
				return false;
			}

			if (T.equals(S)) {
				return true;
			}

			char[] arr1 = S.toCharArray();
			char[] arr2 = T.toCharArray();
			int i = arr1.length - 1;
			int j = arr2.length - 1;
			int goBackStep1 = 0;
			int goBackStep2 = 0;
			while (i >= 0 && j >= 0) {
				/**/
				for (; i >= 0; i--) {
					if (arr1[i] == '#') {
						goBackStep1++;
					} else if (goBackStep1 > 0) {
						goBackStep1--;
					} else {
						break;
					}
				}
				/**/
				for (; j >= 0; j--) {
					if (arr2[j] == '#') {
						goBackStep2++;
					} else if (goBackStep2 > 0) {
						goBackStep2--;
					} else {
						break;
					}
				}
				/* arr1[i] != '#' && goBackStep1 == 0 && arr2[j] != '#' && goBackStep2 == 0 */
				if (i >= 0) {
					if (j < 0 || arr1[i] != arr2[j]) {
						return false;
					}
				} else if (j >= 0) {
					return false;
				}

				i--;
				j--;
			}
			/**/
			for (; i >= 0; i--) {
				if (arr1[i] == '#') {
					goBackStep1++;
				} else if (goBackStep1 > 0) {
					goBackStep1--;
				} else {
					break;
				}
			}
			/**/
			for (; j >= 0; j--) {
				if (arr2[j] == '#') {
					goBackStep2++;
				} else if (goBackStep2 > 0) {
					goBackStep2--;
				} else {
					break;
				}
			}

			return i < 0 && j < 0;
		}
	}

	public boolean backspaceCompareV2(String S, String T) {
		if (S == null) {
			return T == null;
		} else { // S != null
			if (T == null) {
				return false;
			}

			if (T.equals(S)) {
				return true;
			}

			//
			Deque<Character> stack1 = new LinkedList<>();
			pushValidCharToStack(S.toCharArray(), stack1);
			//
			Deque<Character> stack2 = new LinkedList<>();
			pushValidCharToStack(T.toCharArray(), stack2);

			/**/
			if (stack1.size() != stack2.size()) {
				return false;
			}
			//
			Iterator<Character> iterator1 = stack1.iterator();
			Iterator<Character> iterator2 = stack2.iterator();
			while (iterator1.hasNext()) {
				if (!iterator1.next().equals(iterator2.next())) {
					return false;
				}
			}
			return true;
		}
	}

	private void pushValidCharToStack(char[] arr, Deque<Character> stack) {
		for (char c : arr) {
			if (c == '#') {
				if (!stack.isEmpty()) {
					stack.pop();
				}
			} else {
				stack.push(c);
			}
		}
	}

	/**
	 * 925. 长按键入
你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。

你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。

 

示例 1：

输入：name = "alex", typed = "aaleex"
输出：true
解释：'alex' 中的 'a' 和 'e' 被长按。
示例 2：

输入：name = "saeed", typed = "ssaaedd"
输出：false
解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
示例 3：

输入：name = "leelee", typed = "lleeelee"
输出：true
示例 4：

输入：name = "laiden", typed = "laiden"
输出：true
解释：长按名字中的字符并不是必要的。
 

提示：

name.length <= 1000
typed.length <= 1000
name 和 typed 的字符都是小写字母。
	 *
	 * @param name
	 * @param typed
	 * @return
	 * @author suqianwen 2020年10月21日
	 */
	public boolean isLongPressedName(String name, String typed) {
		if (name == null) {
			return typed == null;
		} else if (typed == null || name.length() > typed.length()) {
			return false;
		}

		int i = 1;
		char[] nameChars = name.toCharArray();
		int j = 1;
		char[] typedChars = typed.toCharArray();
		for (; i < nameChars.length && j < typedChars.length; i++, j++) {
			int nameCount = 1;// name中连续的重复的字符的个数
			int typedCount = 1;// typed中连续的重复的字符的个数

			/* 统计name中连续的重复的字符的个数 */
			for (; i < nameChars.length; i++) {
				if (nameChars[i] == nameChars[i - 1]) {
					nameCount++;
				} else {
					break;
				}
			}
			/* 统计typed中连续的重复的字符的个数 */
			for (; j < typedChars.length; j++) {
				if (typedChars[j] == typedChars[j - 1]) {
					typedCount++;
				} else {
					break;
				}
			}

			if (i == nameChars.length) {// name的末尾字符重复
				return j == typedChars.length && nameChars[i - 1] == typedChars[j - 1] && nameCount <= typedCount;
			} else if (j == typedChars.length || nameChars[i - 1] != typedChars[j - 1] || nameCount > typedCount) {
				return false;
			}
		}
		/**/
		if (i == nameChars.length) {// name的末尾字符不重复
			for (; j <= typedChars.length; j++) {
				if (typedChars[j - 1] != nameChars[i - 1]) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		new StringProblem().//firstUniqChar("leetcode");
			//backspaceCompare("ab##", "c#d#");
			//backspaceCompare("bxj##tw", "bxj###tw");
			//backspaceCompareV2("ab#c", "ad#c");
		    //isLongPressedName("alex", "aaleex");
		    //isLongPressedName("leelee",	"lleeelee");
			//isLongPressedName("vtkgn", "vttkgnn");
			isLongPressedName("alex", "aaleelx");
	}

}
