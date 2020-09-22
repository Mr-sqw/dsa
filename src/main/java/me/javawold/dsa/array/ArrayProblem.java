package me.javawold.dsa.array;

/**
 * <br>
 * <br>
 * Copyright: Copyright (c) 2015<br>
 * Company: 北京云杉世界信息技术有限公司<br>
 * 
 *
 * @author suqianwen 2020年9月22日
 */
public class ArrayProblem {

	/**
	 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

示例:

输入: [0,1,0,3,12]
输出: [1,3,12,0,0]
说明:

必须在原数组上操作，不能拷贝额外的数组。
尽量减少操作次数。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/move-zeroes
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param nums
	 * @author suqianwen 2020年9月22日
	 */
	public void moveZeroes(int[] nums) {
		int length;
		if (nums == null || (length = nums.length) == 0) {
			return;
		}

		int j = removeElement_1(nums, 0);
		for (int i = j; i < length; i++) {
			nums[i] = 0;
		}
	}

	public int removeElement_1(int[] nums, int val) {
		int length;
		if (nums == null || (length = nums.length) == 0) {
			return 0;
		}

		int i = 0, j = 0;
		for (; i < length; i++) {
			if (nums[i] != val) {
				nums[j++] = nums[i];
			}
		}
		return j;
	}

}
