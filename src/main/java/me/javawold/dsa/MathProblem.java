package me.javawold.dsa;

public class MathProblem {

    /**
     * 69. x 的平方根
     * 实现 int sqrt(int x) 函数。
     *
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     *
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     *
     * 示例 1:
     *
     * 输入: 4
     * 输出: 2
     * 示例 2:
     *
     * 输入: 8
     * 输出: 2
     * 说明: 8 的平方根是 2.82842...,
     *      由于返回类型是整数，小数部分将被舍去。
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        int sqrt = 1;
        for (; ; ) {
            int quotient = x / sqrt;// 商
            if (quotient == x) {
                return sqrt;
            } else if (quotient < x) {
                sqrt++;
            } else {
                return sqrt - 1;
            }
        }
    }

    public int mySqrtV2(int x) {
        int sqrt = 1;
        for (; ; ) {
            int square = sqrt * sqrt;// square：平法
            if (square == x) {
                return sqrt;
            } else if (square < x) {
                sqrt++;
            } else {
                return sqrt - 1;
            }
        }
    }

    /**
     * 169. 多数元素
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     *
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     *
     *
     * 示例 1:
     *
     * 输入: [3,2,3]
     * 输出: 3
     * 示例 2:
     *
     * 输入: [2,2,1,1,1,2,2]
     * 输出: 2
     *
     * @param nums
     * @return
     */
    //摩尔投票法：
    public int majorityElement(int[] nums) {
        int candidate_num = nums[0];//首先选举“自己”，“我”作为候选人，支持票数+1
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == candidate_num) {//支持“我”，我的支持票数+1
                count++;
            } else {//反对“我”，我的支持票数-1
                count--;

                if (count == 0) {//“我”的净支持票数为0，更换候选人。最后的候选人为胜者(不管这个人的净支持票数是否大于0)。
                    candidate_num = nums[i];
                    count = 1;
                }
            }
        }

        return candidate_num;
    }

    /**
     * 快排partition，轴点下标在“中间”位置的元素就是 多数元素
     * @param nums
     * @return
     */
    public int majorityElementByQuickSort(int[] nums) {
        int mid = 0;//
        return nums[mid];
    }

}
