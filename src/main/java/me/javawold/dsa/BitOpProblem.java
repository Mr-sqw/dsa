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

    public static void main(String[] args){
        new BitOpProblem().addBinary("11","1");
    }

}
