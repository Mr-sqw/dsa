package me.javawold.dsa.dp;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MinCoins {

	/**
	 * 最少货币数量。 给定不同面额的硬币 coins 和一个总金额
	 * amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
	 * 
	 *  
	 * 
	 * 示例 1:
	 * 
	 * 输入: coins = [1, 2, 5], amount = 11
	 * 输出: 3 
	 * 解释: 11 = 5 + 5 + 1 
	 * 
	 * 示例 2:
	 * 
	 * 输入: coins = [2], amount = 3 
	 * 输出: -1
	 * 
	 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/coin-change
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param coins  币值数组。升序有序。如[1,5,10,100]
	 * @param amount
	 * @return
	 * @author suqianwen 2020年8月12日
	 */
	public int minCurrencyNum(int[] coins, int amount) {
		int[] dp = new int[amount + 1];// 状态定义。dp[i]：需要找i元时的最小货币数量
		/* 初始状态。 */
		dp[0] = 0;
		for (int i = 1; i <= amount; i++) {
			dp[i] = -1;
		}

		/* 状态转移方程。dp[i] = Math.min(dp[i-coins[0]],...dp[i-coins[j]]) + 1 */
		for (int i = 1; i <= amount; i++) {
			for (int coin : coins) {
				int i1 = i - coin;// i1：选用币值为coin时，剩下需要凑的钱数。
				if (i1 >= 0 && dp[i1] != -1) {// 剩下需要凑的钱数大于等于0；且已经求得剩下需要凑的钱数时需要的最小货币数量
					int minCurrencyNumUsingCoin = dp[i1] + 1;// 使用当前币值的最小货币数量
					if (dp[i] == -1 //
							|| minCurrencyNumUsingCoin < dp[i]// 使用当前币值时 比 使用其他币值时，最小货币数量要小
					) {
						dp[i] = minCurrencyNumUsingCoin;
					}
				} //
				else {
					// 当前币值大于需要找的钱数，什么也不做
				}
			}
		}

		return dp[amount];
	}

	@Test(dataProvider = "currencyDataProvider")
	public void minCurrencyNumTest(int[] currencyUnitArr, int aim) {
		int minCurrencyNum = minCurrencyNum(currencyUnitArr, aim);
		System.out.println("aim=" + aim + ", minCurrencyNum=" + minCurrencyNum);
	}

	@DataProvider(name = "currencyDataProvider")
	public Object[][] currencyDataProvider() {
		int[] currencyUnitArr = { 2 };
		int[] currencyUnitArr1 = { 1, 2, 5 };
		int[] currencyUnitArr2 = { 1, 2, 5, 10 };
		return new Object[][] { new Object[] { currencyUnitArr, 3 }, //
				new Object[] { currencyUnitArr1, 1 }, //
				new Object[] { currencyUnitArr1, 2 }, //
				new Object[] { currencyUnitArr1, 3 }, //
				new Object[] { currencyUnitArr1, 4 }, //
				new Object[] { currencyUnitArr1, 5 }, //
				new Object[] { currencyUnitArr1, 6 }, //
				new Object[] { currencyUnitArr1, 7 }, //
				new Object[] { currencyUnitArr1, 8 }, //
				new Object[] { currencyUnitArr1, 9 }, //
				new Object[] { currencyUnitArr1, 10 }, //
				new Object[] { currencyUnitArr1, 12 }, //
				new Object[] { currencyUnitArr1, 14 }, //
				new Object[] { currencyUnitArr1, 18 }, //
				new Object[] { currencyUnitArr1, 25 }, //

				new Object[] { currencyUnitArr2, 100 },//
		};
	}

}
