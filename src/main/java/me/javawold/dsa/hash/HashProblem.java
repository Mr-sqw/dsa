package me.javawold.dsa.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * <br>
 * <br>
 * Copyright: Copyright (c) 2015<br>
 * Company: 北京云杉世界信息技术有限公司<br>
 * 
 *
 * @author suqianwen 2020年10月23日
 */
public class HashProblem {

	/**
	 * 217. 存在重复元素
给定一个整数数组，判断是否存在重复元素。

如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。

 

示例 1:

输入: [1,2,3,1]
输出: true
示例 2:

输入: [1,2,3,4]
输出: false
示例 3:

输入: [1,1,1,3,3,4,3,2,4,2]
输出: true
	 *
	 * @param nums
	 * @return
	 * @author suqianwen 2020年10月23日
	 */
	public boolean containsDuplicate(int[] nums) {
		if (nums == null || nums.length == 0) {
			return false;
		}

		Set<Integer> set = new HashSet<>();
		for (int num : nums) {
			if (!set.add(num)) {
				return true;
			}
		}
		return false;
	}

}
