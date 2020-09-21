package me.javawold.dsa;

/**
 * <br>
 * <br>
 * Copyright: Copyright (c) 2015<br>
 * Company: 北京云杉世界信息技术有限公司<br>
 * 
 *
 * @author suqianwen 2020年9月21日
 */
public class Fibonacci {

	/**
	 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：

F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param n
	 * @return
	 * @author suqianwen 2020年9月21日
	 */
	public int fibByRecursion(int n) {
		/* 终止条件 */
		if (n == 0) {
			return 0;
		}
		//
		if (n == 1) {
			return 1;
		}

		/* 基于子问题的解 递归求解 当前问题 */
		return fibByRecursion(n - 1) + fibByRecursion(n - 2);
	}

	/**
	 * 自底向上 记忆化搜索。
	 *
	 * @param n
	 * @return
	 * @author suqianwen 2020年9月21日
	 */
	public int fibByMemorySearch(int n) {
		if (n == 0) {
			return 0;
		}
		//
		if (n == 1) {
			return 1;
		}

		int fibOfn1 = 1;
		int fibOfn2 = 0;
		int fibOfn = 0;
		for (int i = 2; i <= n; i++) {
			fibOfn = fibOfn1 + fibOfn2;
			fibOfn2 = fibOfn1;
			fibOfn1 = fibOfn;
		}
		return fibOfn;
	}

}
