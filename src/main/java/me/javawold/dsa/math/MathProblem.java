package me.javawold.dsa.math;

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

		/* 原地哈希：将 (已经出现的元素值-1) 作为下标，该下标对应的元素值标记为 -1*元素值 ，即表示：该下标+1 的元素值已经出现过。 */
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

	/**
	 * 287. 寻找重复数
给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。

示例 1:

输入: [1,3,4,2,2]
输出: 2
示例 2:

输入: [3,1,3,4,2]
输出: 3
说明：

不能更改原数组（假设数组是只读的）。
只能使用额外的 O(1) 的空间。
时间复杂度小于 O(n2) 。
数组中只有一个重复的数字，但它可能不止重复出现一次。
	 *
	 * @param nums
	 * @return
	 * @author suqianwen 2020年10月9日
	 */
	// 二分查找：
	public int findDuplicate(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int left = 1;
		int right = nums.length - 1;
		while (left < right) {
			int mid = left + (right - left) / 2;
			int count = 0;
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] <= mid) {
					count++;
				}
			}

			if (count == mid) {// 如果小于等于mid的数的个数 等于 mid。则重复的数的范围一定在[mid+1, right](右半部分)
				left = mid + 1;
			} else if (count > mid) {// 如果小于等于mid的数的个数 大于 mid。则重复的数的范围一定在[left, mid](左半部分)
				right = mid;
			} else {// 如果小于等于mid的数的个数 小于 mid。则重复的数的范围一定在[mid+1, right](右半部分)
				left = mid + 1;
			}
		}

		return left;
    }

	// 错误, why ?
	public int findDuplicateV11(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int left = 1;
		int right = nums.length - 1;
		while (left < right) {
			int mid = left + (right - left) / 2;
			int count = 0;
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] < mid) {
					count++;
				}
			}
			if (count < mid) {// 如果小于mid的数的个数 小于 mid。则重复的数的范围一定在[mid, right]
				left = mid;
			} else {// 如果小于mid的数的个数 大于等于 mid。则重复的数的范围一定在[left,mid-1]
				right = mid - 1;
			}
		}

		return left;
	}

	// 原地哈希：修改了原数组
	public int findDuplicateV2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		for (int i = 0; i < nums.length; i++) {
			int index = Math.abs(nums[i]) - 1;// 当前nums[i]对应的index
			if (nums[index] < 0) {
				return index + 1;// index第二次出现，此时index+1(或Math.abs(nums[i]))即为重复的数
			} else {
				nums[index] = nums[index] * -1;// 标记第一次出现的index的nums[index] 为 -nums[index]。
			}
		}
		return 0;
	}

	/**
	 * 581. 最短无序连续子数组
	 * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
	 *
	 * 你找到的子数组应是最短的，请输出它的长度。
	 *
	 * 示例 1:
	 *
	 * 输入: [2, 6, 4, 8, 10, 9, 15]
	 * 输出: 5
	 * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
	 * 说明 :
	 *
	 * 输入的数组长度范围在 [1, 10,000]。
	 * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
	 *
	 * @param nums
	 * @return
	 */
	public int findUnsortedSubarray(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		// 左半部分 中间无序部分 右半部分
		/**/
		int min = nums[nums.length - 1]; // 从右到左i位置以后的最小值。
		int begin = 0;// 该位置一定与后面某些元素逆序，并且再往前，不会再有元素与后面元素逆序了。即该位置是 中间无序部分的最左下标。
		for (int i = nums.length - 2; i >= 0; i--) {
			if (nums[i] <= min) {
				min = nums[i];// 从右到左，如果我是当前子数组的最小值，则我一定不会与后面的元素逆序。如果从某一位置开始，我[一直]是当前子数组的最小值；则从当前位置到后面部分一直是升序排序的(即为“右半部分”)。
			} else {
				begin = i;// 从右到左，如果我不是当前子数组的最小值，则当前位置一定与后面某些元素逆序。
			}
		}
		/**/
		int max = nums[0];// 从左到右i位置以前的最大值。
		int end = -1;// 该位置一定与前面某些元素逆序，并且再往后，不会再有元素与前面元素逆序了。即该位置是 中间无序部分的最右下标。
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] >= max) {
				max = nums[i];// 从左到右，如果我是当前子数组的最大值，则我一定不会与前面的元素逆序。如果从某一位置开始，我[一直]是当前子数组的最大值；则从当前位置到后面部分一直是升序排序的(即为“右半部分”)。
			} else {
				end = i;// 从左到右，如果我不是当前子数组的最大值，则当前位置一定与前面某些元素逆序。
			}
		}
		/**/
		return end - begin + 1;
	}

	/**
	 * 118. 杨辉三角
给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。



在杨辉三角中，每个数是它左上方和右上方的数的和。

示例:

输入: 5
输出:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
	 *
	 * @param numRows
	 * @return
	 * @author suqianwen 2020年10月23日
	 */
	public List<List<Integer>> generate(int numRows) {
		if (numRows == 0) {
			return Collections.emptyList();
		}

		List<List<Integer>> result = new ArrayList<>(numRows);
		for (int i = 1; i <= numRows; i++) {
			List<Integer> currRows = new ArrayList<>(i);
			result.add(currRows);

			int j = 1;
			if (j++ <= i) {
				currRows.add(1);
			}
			//
			int num = i - 2;
			if (num >= 1) {// 从第三行起进行累加
				List<Integer> lastRows = result.get(num);
				for (int k = 0; k < num; k++) {
					currRows.add(lastRows.get(k) + lastRows.get(k + 1));
				}
				j += num;
			}
			//
			if (j <= i) {
				currRows.add(1);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] nums = // {4,3,2,7,8,2,3,1};
				//{ 3, 1, 3, 4, 2 };
				//{2,2,2,2,2};
				{2, 6, 4, 8, 10, 9, 15};
		new MathProblem().// findDisappearedNumbers(nums);
				//findDuplicate(nums);
				//findUnsortedSubarray(nums);
		        generate(5);
	}

}
