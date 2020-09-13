package me.javawold.dsa.codeM2018;

import java.util.Scanner;

/**
 * 题目描述
美团外卖日订单已经超过2000万，背后有一个非常复杂的智能调度系统。
我们考虑一个简化的情形，有n个外卖小哥要去 n 家商店取货，第 i 个外卖小哥到达商店 j 需要时间 e[i][j] 。
现在有 m 对外卖小哥和商店的合作关系。假定每个外卖小哥只能取到一个货物，每个商店只需要一位外卖小哥取货。
询问最少多少时间，能有 k 位外卖小哥到达 k 个商店取到货物？对于每个 k ，都输出一个数表示最少使用时间，如果无解输出 -1。
输入描述:
第一行输入两个整数 n , m （1 <= n <= 1000 , 1 <= m <= 100000）。
接下来 m 行，每行输入 3 个整数 i , j , e[i][j] (1 <= i, j <= n , 0 <= e[i][j] <= 10^9)，定义如题所述。
注：本题测试用例较多，请耐心等待判题结果，也可以去排行榜刷新查看自己的提交结果。
输出描述:
输出一行n个整数，第 i 个整数，表示当 k=i 时，需要的最少时间，如果无解输出-1，结尾无空格。
示例1
输入
3 7
1 3 5
2 3 2
3 1 7
1 2 0
2 3 2
3 2 0
2 1 5
输出
0 2 5
 * @author mr_sqw
 *
 */
public class Takeout {
	
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
		short n = scanner.nextShort();
		short m = scanner.nextShort();

		short i, j;
		int[][] costMatrix = new int[n+1][n+1];
		for (int row = 0; row < m; row++) {
			i = scanner.nextShort();
			j = scanner.nextShort();
			costMatrix[i][j] = scanner.nextInt();
		}
		scanner.close();

		
    }

}
