package me.javawold.dsa.dp;

/**
 * <br>
 * <br>
 * Copyright: Copyright (c) 2015<br>
 * Company: 北京云杉世界信息技术有限公司<br>
 * 
 *
 * @author suqianwen 2020年9月19日
 */
public class MaxSubArraySum {

	/**
	 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
	 * 
	 * 要求时间复杂度为O(n)。
	 * 
	 * 示例1:
	 * 
	 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]                                                                                                                                                    
	 * 输出: 6 
	 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
	 * 
	 * @param nums
	 * @return
	 * @author suqianwen 2020年9月19日
	 */
	public int maxSubArraySum(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int maxSum = Integer.MIN_VALUE;// 基于动态规划版解法，由于是求最大连续子数组和，所以不需要O(n)的空间复杂度来存储每一个以nums[i]结尾的最大连续子数组和。
		int sum = 0;// 累加和
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];

			if (sum < 0) {// sum小于0，再继续累加，只会使累加和小于下一个加数；所以置累加和为0，重新开始累加。
				maxSum = Math.max(maxSum, sum);// 每一次累加，都统计当前为止的最大累加和

				sum = 0;// 置累加和为0，重新开始累加
			} else {// 每一次累加，都统计当前为止的最大累加和
				maxSum = Math.max(maxSum, sum);
			}
		}

		return maxSum;
	}

	public int maxSubArraySumByDP(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int[] dp = new int[nums.length];// 状态定义。dp[i]: 以nums[i]结尾的最大连续子数组和。
		dp[0] = nums[0];// 初始状态
		int maxSum = dp[0];
		for (int i = 1; i < dp.length; i++) {
			// 状态转移方程：如果dp[i-1]<=0，dp[i]=nums[i]；以nums[i-1]结尾的最大连续子数组和小于等于0，计算dp[i]时，加上dp[i-1]只会使累加和小于nums[i]，故不加dp[i-1]。
			// 如果dp[i-1]>0，dp[i]=nums[i]+dp[i-1]
			if (dp[i - 1] <= 0) {
				dp[i] = nums[i];
			} else {
				dp[i] = nums[i] + dp[i - 1];
			}

			maxSum = Math.max(dp[i], maxSum);
		}

		return maxSum;
	}

}
