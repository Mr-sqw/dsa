package me.javawold.dsa.math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <br>
 * <br>
 * Copyright: Copyright (c) 2015<br>
 * Company: 北京云杉世界信息技术有限公司<br>
 * 
 *
 * @author suqianwen 2020年10月15日
 */
public class MathCollectionProblem {
	
	/**
	 * 350. 两个数组的交集 II
给定两个数组，编写一个函数来计算它们的交集。

 

示例 1：

输入：nums1 = [1,2,2,1], nums2 = [2,2]
输出：[2,2]
示例 2:

输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出：[4,9]
 

说明：

输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
我们可以不考虑输出结果的顺序。
进阶：

如果给定的数组已经排好序呢？你将如何优化你的算法？
如果 nums1 的大小比 nums2 小很多，哪种方法更优？
如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
	 *
	 * @param nums1
	 * @param nums2
	 * @return
	 * @author suqianwen 2020年10月15日
	 */
	public int[] intersect(int[] nums1, int[] nums2) {
		if (nums1 == null || nums1.length == 0//
				|| nums2 == null || nums2.length == 0) {
			return new int[0];
		}

		// key: num, value: freq
		Map<Integer, Integer> freqMap = new HashMap<>(nums1.length);
		for (int n1 : nums1) {
			freqMap.merge(n1, 1, (oldValue, newValue) -> newValue + oldValue);
		}

		int[] result = new int[Math.min(nums1.length, nums2.length)];
		int i = 0;
		for (int n2 : nums2) {
			Integer freq = freqMap.get(n2);
			if (freq != null && freq >= 1) {
				result[i++] = n2;
				freqMap.put(n2, freq - 1);
			}
		}
		if (i == 0) {
			return new int[0];
		} else if (i == result.length) {
			return result;
		} else {
			return Arrays.copyOf(result, i);
		}
	}

	public static void main(String[] args) {
		int[] nums1 = { 4, 9, 5 };
		int[] nums2 = { 9, 4, 9, 8, 4 };
		new MathCollectionProblem().intersect(nums1, nums2);
	}

}
