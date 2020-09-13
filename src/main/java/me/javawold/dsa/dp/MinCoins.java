package me.javawold.dsa.dp;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MinCoins {
    /**
     * 最少货币数量
     *
     * @param currencyUnitArr 币值数组。升序有序。如[1,5,10,100]
     * @param aim
     * @return
     * @author suqianwen 2020年8月12日
     */
    public int minCurrencyNum(int[] currencyUnitArr, int aim) {
        int[] dp = new int[aim + 1];// dp[i]：需要找i元时的最小货币数量
        dp[0] = 0;
        for (int i = 1; i <= aim; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= aim; i++) {
            for (int currencyUnit : currencyUnitArr) {
                int i1 = i - currencyUnit;// i1：
                if (i1 >= 0) {// i1>=0：已经求得需要找i1元时需要的最小货币数量
                    int minCurrencyNumUsingCurrencyUnit = dp[i1] + 1;// 使用当前币值的最小货币数量
                    int minCurrencyNumNotUsingCurrencyUnit = dp[i];// 不使用当前币值的最小货币数量，初始时为无穷大。
                    if (minCurrencyNumUsingCurrencyUnit < minCurrencyNumNotUsingCurrencyUnit) {
                        dp[i] = minCurrencyNumUsingCurrencyUnit;
                    }
                } //
                else {
                    // 当前币值大于需要找的钱数，什么也不做
                }
            }
        }

        return dp[aim];
    }

    @Test(dataProvider = "currencyDataProvider")
    public void minCurrencyNumTest(int[] currencyUnitArr, int aim) {
        int minCurrencyNum = minCurrencyNum(currencyUnitArr, aim);
        System.out.println("aim=" + aim + ", minCurrencyNum=" + minCurrencyNum);
    }

    @DataProvider(name = "currencyDataProvider")
    public Object[][] currencyDataProvider() {
        int[] currencyUnitArr1 = { 1, 2, 5 };
        int[] currencyUnitArr2 = { 1, 2, 5, 10 };
        return new Object[][] { new Object[] { currencyUnitArr1, 1 }, //
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
