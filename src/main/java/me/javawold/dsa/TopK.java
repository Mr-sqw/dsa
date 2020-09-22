package me.javawold.dsa;

/**
 * <br>
 * <br>
 * Copyright: Copyright (c) 2015<br>
 * Company: 北京云杉世界信息技术有限公司<br>
 * 
 *
 * @author suqianwen 2020年9月22日
 */
public class TopK {
	
	/**
	 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:

输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
示例 2:

输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
说明:

你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param nums
	 * @param k
	 * @return
	 * @author suqianwen 2020年9月22日
	 */
	public int findKthLargest(int[] nums, int k) {
		if (nums == null || nums.length == 0 || k < 1 || k > nums.length) {
			return -1;
		}

		return findKthLargestByQuickSortPartition(nums, 0, nums.length - 1, nums.length - k);
	}

	public int findKthLargestByQuickSortPartition(int[] nums, int startIndex, int endIndex, int kIndex) {
		int pivotIndex = partition(nums, startIndex, endIndex);
		if (kIndex == pivotIndex) {
			return nums[kIndex];
		} else if (kIndex < pivotIndex) {// 从轴点的左半部分找
			return findKthLargestByQuickSortPartition(nums, startIndex, pivotIndex - 1, kIndex);
		} else {// kIndex > pivotIndex，从轴点的右半部分找
			return findKthLargestByQuickSortPartition(nums, pivotIndex + 1, endIndex, kIndex);
		}
	}

	/**
	 * 
	 *
	 * @param nums
	 * @param startIndex
	 * @param endIndex
	 * @return 返回：轴点下标。轴点左边都是小于等于轴点的数；轴点右边都是大于轴点的数
	 * @author suqianwen 2020年9月22日
	 */
	public int partition(int[] nums, int startIndex, int endIndex) {
		if (startIndex > endIndex) {
			return -1;
		}

		if (startIndex == endIndex) {
			return startIndex;
		}

		int pivot = nums[startIndex];// 轴点
		int i = startIndex;
		int j = endIndex;

		while (i < j) {
			/* 从右向左找 */
			while (i < j && nums[j] > pivot) {
				j--;
			}
			//
			if (i < j) {
				nums[i] = nums[j];// 填坑

				i++;
			}

			/* 从左向右找 */
			while (i < j && nums[i] <= pivot) {
				i++;
			}
			//
			if (i < j) {
				nums[j] = nums[i];// 填坑

				j--;
			}
		}

		//
		nums[i] = pivot;// 填最后一个坑。

		return i;
	}

}
