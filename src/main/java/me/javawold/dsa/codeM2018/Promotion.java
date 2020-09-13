package me.javawold.dsa.codeM2018;

import java.util.Scanner;

/**
 * 题目描述
美团在吃喝玩乐等很多方面都给大家提供了便利。最近又增加了一项新业务：小象生鲜。这是新零售超市，你既可以在线下超市门店选购生鲜食品，也可以在手机App上下单，最快30分钟就配送到家。
新店开张免不了大优惠。我们要在小象生鲜超市里采购n个物品，每个物品价格为ai，有一些物品可以选择八折优惠（称为特价优惠）。
有m种满减优惠方式，满减优惠方式只有在所有物品都不选择特价优惠时才能使用，且最多只可以选择最多一款。
每种满减优惠描述为(bi,ci)，即满bi减ci（当消费>=bi时优惠ci）。
求要买齐这n个物品（必须一单买齐），至少需要多少钱（保留两位小数）。
输入描述:
第一行，两个整数n,m。
接下来n行，每行一个正整数ai，以及一个0/1表示是否可以选择特价优惠（1表示可以）。
接下来m行，每行两个正整数bi,ci，描述一款满减优惠。

1 <= n,m <=10
1 <= ai <= 100
1 <= ci < bi <= 1000
输出描述:
一行一个实数，表示至少需要消耗的钱数（保留恰好两位小数）。

示例1
输入
2 1
6 1
10 1
12 2
输出
12.80

示例2
输入
2 2
6 1
10 1
5 1
16 6
输出
10.00
 */
public class Promotion {

	public static void main(String[] args) {
		//System.out.format("%.2f",10.5556d);
		Scanner scanner = new Scanner(System.in);
		byte n = scanner.nextByte();
		byte m = scanner.nextByte();

		byte price, is20Discount ;
		double sumOf20Discount = 0;
		short sumOfNoDiscount = 0;
		for (int i = 0; i < n; i++) {
			price = scanner.nextByte();
			is20Discount = scanner.nextByte();
			if (is20Discount == 1) {
				sumOf20Discount += price * 0.2;
			} 
	
			sumOfNoDiscount += price;
		}

		short full, minus;
		short maxMinus = 0;
		for (int i = 0; i < m; i++) {
			full = scanner.nextShort();
			minus = scanner.nextShort();
			if (maxMinus < minus //最大满减
					&& sumOfNoDiscount >= full//满足满减
							) {
				maxMinus = minus;
			}
		}
		scanner.close();

		System.out.format("%.2f", sumOfNoDiscount - Math.max(sumOf20Discount, maxMinus));
	}
	
	public static void main1(String[] args) {
		Scanner scanner = new Scanner(System.in);
		byte n = scanner.nextByte();
		byte m = scanner.nextByte();

		byte[] priceArr = new byte[n];
		byte[] is20DiscountArr = new byte[n];
		for (int i = 0; i < n; i++) {
			priceArr[i] = scanner.nextByte();
			is20DiscountArr[i] = scanner.nextByte();
		}

		short[] fullArr = new short[m];
		short[] minusArr = new short[m];
		for (int i = 0; i < m; i++) {
			fullArr[i] = scanner.nextShort();
			minusArr[i] = scanner.nextShort();
		}
		scanner.close();

		System.out.format("%.2f", promotion2(n, m, priceArr, is20DiscountArr, fullArr, minusArr));
	}
	
	public static double promotion1(int n,int m,byte[] priceArr,byte[] is20DiscountArr,short[] fullArr,short[] minusArr) {
		double sumOf20Discount = 0;
		short sumOfNoDiscount = 0;
		for (int i = 0; i < n; i++) {
			if (is20DiscountArr[i] == 1) {
				sumOf20Discount += priceArr[i] * 0.8;
			} else {
				sumOf20Discount += priceArr[i];
			}

			sumOfNoDiscount += priceArr[i];
		}

		short sumOfMinus = sumOfNoDiscount;
		short maxFull = 0;
		for (int i = 0; i < m; i++) {
			if (maxFull < fullArr[i] && sumOfNoDiscount >= fullArr[i]) {
				sumOfMinus = (short) (sumOfNoDiscount - minusArr[i]);
				maxFull = fullArr[i];
			}
		}

		return  Math.min(sumOf20Discount, sumOfMinus);
	}
	
	public static double promotion2(int n,int m,byte[] priceArr,byte[] is20DiscountArr,short[] fullArr,short[] minusArr) {
		double sumOf20Discount = 0;
		short sumOfNoDiscount = 0;
		for (int i = 0; i < n; i++) {
			if (is20DiscountArr[i] == 1) {
				sumOf20Discount += priceArr[i] * 0.2;
			} 

			sumOfNoDiscount += priceArr[i];
		}

		short maxMinus = 0;
		for (int i = 0; i < m; i++) {
			if (maxMinus < minusArr[i] //最大满减
					&& sumOfNoDiscount >= fullArr[i] //满足满减
							) {
				maxMinus = minusArr[i];
			}
		}

		return sumOfNoDiscount - Math.max(sumOf20Discount, maxMinus);
	}
	
	
	
	

}
