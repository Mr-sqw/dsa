package me.javawold.dsa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    /**
     * 136. 只出现一次的数字
给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:

输入: [2,2,1]
输出: 1
示例 2:

输入: [4,1,2,1,2]
输出: 4
     *
     * @param nums
     * @return
     * @author suqianwen 2020年10月7日
     */
	public int singleNumber(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int num = nums[0];
		for (int i = 1; i < nums.length; i++) {
			num ^= nums[i];// 异或运算
		}
		return num;
	}

	/**
	 * 448. 找到所有数组中消失的数字
给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。

找到所有在 [1, n] 范围之间没有出现在数组中的数字。

您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。

示例:

输入:
[4,3,2,7,8,2,3,1]

输出:
[5,6]
	 *
	 * @param nums
	 * @return
	 * @author suqianwen 2020年10月7日
	 */
	public List<Integer> findDisappearedNumbers(int[] nums) {
		if (nums == null || nums.length == 0) {
			return Collections.emptyList();
		}

		/* 将 (已经出现的元素值-1) 作为下标，该下标对应的元素值标记为 -1*元素值 ，即表示：该下标+1 的元素值已经出现过。 */
		for (int i = 0; i < nums.length; i++) {
			int index = Math.abs(nums[i]) - 1;
			if (nums[index] > 0) {
				nums[index] = nums[index] * (-1);// 标记
			}
		}
		//
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0) {// 当前位置没有被标记，则 下标+1 的元素值没有出现过
				result.add(i + 1);
			}
		}
		return result;
    }
	
	public static void main(String[] args) {
		int[] nums = {4,3,2,7,8,2,3,1};
		new MathProblem().findDisappearedNumbers(nums );
	}

}
