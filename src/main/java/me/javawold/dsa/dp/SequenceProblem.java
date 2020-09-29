package me.javawold.dsa.dp;

import java.util.stream.IntStream;

/**
 * <br>
 * <br>
 * Copyright: Copyright (c) 2015<br>
 * Company: 北京云杉世界信息技术有限公司<br>
 * 
 *
 * @author suqianwen 2020年9月29日
 */
public class SequenceProblem {

	/**
	 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。

示例:

输入: [10,9,2,5,3,7,101,18]
输出: 4 
解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
说明:

可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
你算法的时间复杂度应该为 O(n2) 。
进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param nums
	 * @return
	 * @author suqianwen 2020年9月29日
	 */
	public int lengthOfLIS(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int[] dp = new int[nums.length];/* 状态定义。dp[i]：以i结尾的最长子序列长度 */
		for (int i = 0; i < nums.length; i++) {
			/* 初始状态： */
			dp[i] = 1;
			/* 状态转移方程：所有 位于i前面 且 值小于i的j，以j结尾的最长子序列长度+1 中的最大值 */
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i]) {
					dp[i] = Math.max(dp[j] + 1, dp[i]);
				}
			}
		}

		return IntStream.of(dp).max().orElse(dp[0]);
	}

	/**
	 * 时间复杂度：O(n*logn)
	 *
	 * @param nums
	 * @return
	 * @author suqianwen 2020年9月29日
	 */
	public int lengthOfLISV2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		// tail 数组的定义：长度为 i + 1 的上升子序列 的末尾最小是几。辅助数组
		int[] tail = new int[nums.length];
		// 遍历第 1 个数，直接放在有序数组 tail 的开头
		tail[0] = nums[0];
		// end 表示有序数组 tail 的最后一个已经赋值元素的索引。最长子序列 的长度
		int end = 0;

		for (int i = 1; i < nums.length; i++) {
			// 【逻辑 1】比 tail 数组实际有效的末尾的那个元素还大，说明递增序列变长了，长度增长了1。
			if (nums[i] > tail[end]) {
				// 直接添加在那个元素的后面，所以 end 先加 1
				end++;
				tail[end] = nums[i];
			} else {
				// 使用二分查找法，在有序数组 tail 中
				// 找到第 1 个大于等于 nums[i] 的元素，尝试让那个元素更小
				int left = 0;
				int right = end;
				while (left < right) {
					// 选左中位数不是偶然，而是有原因的，原因请见 LeetCode 第 35 题解
					// int mid = left + (right - left) / 2;
					int mid = left + ((right - left) >>> 1);
					if (tail[mid] < nums[i]) {
						// 中位数肯定不是要找的数，把它写在分支的前面
						left = mid + 1;
					} else {
						right = mid;
					}
				}
				// 走到这里是因为 【逻辑 1】 的反面，因此一定能找到第 1 个大于等于 nums[i] 的元素
				// 因此，无需再单独判断
				tail[left] = nums[i];
			}
		}
		// 此时 end 是有序数组 tail 最后一个元素的索引
		// 题目要求返回的是长度，因此 +1 后返回
		end++;
		return end;
	}

}
