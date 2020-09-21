package me.javawold.dsa.recursion;

/**
 * <br>
 * <br>
 * Copyright: Copyright (c) 2015<br>
 * Company: 北京云杉世界信息技术有限公司<br>
 * 
 *
 * @author suqianwen 2020年9月21日
 */
public class Step {

	/**
	 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
	 *
	 * @param n
	 * @return
	 * @author suqianwen 2020年9月21日
	 */
	public int numWays(int n) {
		if (n <= 0) {
			return 0;
		}

		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}

		return numWays(n - 1)// 最后一步要么从n-1级台阶一次跨一步
				+ numWays(n - 2)// 要么从n-2级台阶一次跨两步
		;
	}

}
