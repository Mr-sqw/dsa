package me.javawold.dsa.codeM2018;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
小胖参加了人生中最重要的比赛——MedoC资格赛。MedoC的资格赛由m轮构成，使用常见的“加权标准分”的规则。每位选手需要参加所有的m轮的比赛。
在一轮中，能取得的分数为自己的成绩除掉最高分的成绩。每个选手的总分为每一轮获得的分数乘上这一轮比赛占得比重。
如果在某一轮比赛中所有人获得了零分，那么所有选手在这一轮获得的分数都为0分。
比如说，资格赛一共3轮，三轮的权重分别为30%, 30%, 40%。在第一轮中，小胖获得了300分，最高分也为300分。在第二轮中，小胖获得了0分，最高分也为0分。
在第三轮中，小胖获得了150分，最高分为300分，那么小胖的总分为(300/300)*30%+0*30%+(150/300)*40%=0.5。
一共有n位选手参加了比赛，其中成绩最高的k位选手能够晋级到初赛。如果有多人在分数线上同分，那么系统会随机在这些同分的人中选取，选满k个晋级为止。
小胖现在知道了每个选手每场比赛的成绩，但是由于他的疏忽，其中的某个人某场比赛的成绩消失了。所以更多人出线的情况变得未知起来。现在只知道成绩一定是0到C之间的一个整数（包含0和C）。
小胖想知道对于每个人的出线情况是怎么样的，也就是一定能出线，一定不能出线还是有可能出线。
输入描述:
第一行四个正整数n,m,k,C (m <= 6, k <= n <= 500, C <= 500)。
接下来一行m个整数w1, w2, ..., wm，表示每场比赛的权重，第i场比赛的权重为wi/(w1+w2+...+wm)，保证wi >= 0且1 <= w1 + w2 + ... + wm <= 1000。
接下来n行每行m个整数，第i个整数表示这个选手在第i场比赛中获得的成绩。如果这个数字为-1表示这个数据丢失，保证恰好有一个-1。
输出描述:
n行每行输出一个1到3之间的整数。1表示一定出线，2表示一定不出线，3表示可能出线。
示例1
输入
4 2 2 100
1 1
100 99
70 70
40 -1
100 39
输出
1
3
3
2
 * @author mr_sqw
 *
 */
public class Qualification {
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		short n = scanner.nextShort();// n位选手
		byte m = scanner.nextByte();// m轮比赛
		short k = scanner.nextShort();// 成绩最高的k位选手能够晋级到初赛
		short c = scanner.nextShort();// 成绩一定是0到C之间的一个整数（包含0和C）

		short[] weightArr = new short[m];
		short weight, totalWeight = 0;
		for (int i = 0; i < m; i++) {
			weight = scanner.nextShort();
			weightArr[i] = weight;
			totalWeight += weight;
		}
		double[] wtArr = new double[m];
		for (int i = 0; i < m; i++) {
			wtArr[i] = weightArr[i] / totalWeight;
		}

		short[][] score2dArr = new short[n][m];// 数字为-1表示这个数据丢失，保证恰好有一个-1。
		short[] maxScoreArr = new short[m];
		int lossI = 0, lossJ = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				short score = scanner.nextShort();

				if (score == -1) {
					lossI = i;
					lossJ = j;
				}

				if (score > maxScoreArr[j]) {
					maxScoreArr[j] = score;
				}

				score2dArr[i][j] = score;
			}
		}
		scanner.close();

		for (int j = 0; j < m; j++) {
			if (/* j!= lossJ && */maxScoreArr[j] == 0) {
				maxScoreArr[j] = 1;
			}
		}

		double[] weightScoreArr = new double[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (j != lossJ) {
					weightScoreArr[i] += score2dArr[i][j] / maxScoreArr[j] * wtArr[j];
				}
			}
		}

		// 1、score2dArr[lossI][lossJ] <= maxScoreArr[lossJ]：除lossI，其他选手的加权分数始终保持最大；weightScoreArr[lossI]与score2dArr[lossI][lossJ]成正比关系，lossI选手的加权分数范围为：maxWeightScoreArr[lossI], maxWeightScoreArr[lossI]+wtArr[lossJ]。
		double[] maxWeightScoreArr = Arrays.copyOf(weightScoreArr, n);
		for (int i = 0; i < n; i++) {
			if (i != lossI) {
				maxWeightScoreArr[i] += score2dArr[i][lossJ] / maxScoreArr[lossJ] * wtArr[lossJ];
			}
		}
		// maxWeightScoreArr[lossI];//score2dArr[lossI][lossJ] == 0，lossI加权分数最小
		maxWeightScoreArr[lossI] += wtArr[lossJ];//score2dArr[lossI][lossJ] == maxScoreArr[lossJ]，lossI加权分数最大
		
		//2、maxScoreArr[lossJ] < score2dArr[lossI][lossJ] <= c：除lossI，其他选手的加权分数最小，且随着score2dArr[lossI][lossJ]递增，加权分数递减；lossI选手的加权分数始终保持最大，为maxWeightScoreArr[lossI]+wtArr[lossJ]。
		double[] minWeightScoreArr = Arrays.copyOf(weightScoreArr, n);//
		for (int i = 0; i < n; i++) {
			if (i != lossI) {
				minWeightScoreArr[i] += score2dArr[i][lossJ] / c * wtArr[lossJ];
			}
		}
		// minWeightScoreArr[lossI] += wtArr[lossJ];//score2dArr[lossI][lossJ] == c，lossI加权分数最大
		// minWeightScoreArr[lossI];//score2dArr[lossI][lossJ] == 0，lossI加权分数最小
		
		
	}

}
