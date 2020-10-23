package me.javawold.dsa;

public class BitOpProblem {

    /**
     * 67. 二进制求和
     * 给你两个二进制字符串，返回它们的和（用二进制表示）。
     *
     * 输入为 非空 字符串且只包含数字 1 和 0。
     *
     *
     *
     * 示例 1:
     *
     * 输入: a = "11", b = "1"
     * 输出: "100"
     * 示例 2:
     *
     * 输入: a = "1010", b = "1011"
     * 输出: "10101"
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        if (a == null || a.length() == 0)
            return b;

        if (b == null || b.length() == 0)
            return a;

        char[] charsA = a.toCharArray();
        char[] charsB = b.toCharArray();
        int i = charsA.length - 1;
        int j = charsB.length - 1;
        StringBuilder sb = new StringBuilder();
        int sum = 0;
        while (i >= 0 && j >= 0) {
            //sum = (int) charsA[i] + (int) charsB[j] + sum / 2;//sum/2：进位
            sum = sum / 2 + (charsA[i] == '1' ? 1 : 0) + (charsB[j] == '1' ? 1 : 0);//sum/2：进位
            sb.append(sum % 2);

            i--;
            j--;
        }
        while (i >= 0) {
            sum = (charsA[i] == '1' ? 1 : 0) + sum / 2;//sum/2：进位
            sb.append(sum % 2);

            i--;
        }
        while (j >= 0) {
            sum = (charsB[j] == '1' ? 1 : 0) + sum / 2;//sum/2：进位
            sb.append(sum % 2);

            j--;
        }
        //
        if (sum >= 2) {
            sb.append('1');
        }

        return sb.reverse().toString();
    }

    /**
     * 461. 汉明距离
两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。

给出两个整数 x 和 y，计算它们之间的汉明距离。

注意：
0 ≤ x, y < 231.

示例:

输入: x = 1, y = 4

输出: 2

解释:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑

上面的箭头指出了对应二进制位不同的位置。
     *
     * @param x
     * @param y
     * @return
     * @author suqianwen 2020年10月7日
     */
	public int hammingDistance(int x, int y) {
		int distance = 0;
		int power2 = 1;
		for (int i = 0; i < 31; i++, power2 <<= 1) {// 找出第1位 到 第31位 不同
			int bitX = x & power2;
			int bitY = y & power2;
			if (bitX != bitY) {
				distance++;
			}
		}
		if ((x < 0 && y > 0) || (x > 0 && y < 0)) {// 一正一负，最高位/第32位 不同
			distance++;
		}
		return distance;
	}

	/**
	 * 191. 位1的个数
编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。

 

示例 1：

输入：00000000000000000000000000001011
输出：3
解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
示例 2：

输入：00000000000000000000000010000000
输出：1
解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
示例 3：

输入：11111111111111111111111111111101
输出：31
解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
 

提示：

请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
 

进阶:
如果多次调用这个函数，你将如何优化你的算法？
	 *
	 * @param n
	 * @return
	 * @author suqianwen 2020年10月23日
	 */
	public int hammingWeight(int n) {
		if (n == 0) {
			return 0;
		}

		int count = 0;
		while (n != 0) {
			int bit = n & 1;// 取最低位数字
			if (bit == 1) {
				count++;
			}

			//
			n = n >>> 1;// 无符号右移
		}
		return count;
	}

    public static void main(String[] args){
        new BitOpProblem().//addBinary("11","1");
        	hammingWeight(-3);
    }

}
