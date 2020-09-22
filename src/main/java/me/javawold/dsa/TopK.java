package me.javawold.dsa;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

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
			/* 从右向左找，直到找到一个小于等于轴点的位置 */
			while (i < j && nums[j] > pivot) {
				j--;
			}
			//
			if (i < j) {
				nums[i] = nums[j];// 填坑

				i++;
			}

			/* 从左向右找，直到找到一个大于轴点的位置 */
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

	/**
	 *给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
	 *
	 *  
	 *
	 * 示例 1:
	 *
	 * 输入: nums = [1,1,1,2,2,3], k = 2
	 * 输出: [1,2]
	 * 示例 2:
	 *
	 * 输入: nums = [1], k = 1
	 * 输出: [1]
	 *  
	 *
	 * 提示：
	 *
	 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
	 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
	 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
	 * 你可以按任意顺序返回答案。
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 * @param nums
	 * @param k
	 * @return
	 */
	public int[] topKFrequent(int[] nums, int k) {
		if (nums == null || nums.length == 0 || k <= 0 || k > nums.length)
			return null;

		Map<Integer, Integer> freq = new HashMap<>();//key: num, value: freq
		for (int num : nums) {
			freq.merge(num, 1, (oldValue, value) -> oldValue + value);
		}
		/**/
		Map.Entry<Integer, Integer>[] entries = freq.entrySet().toArray(new Map.Entry[freq.size()]);
		Comparator<Map.Entry<Integer, Integer>> comparator = (o1, o2) -> o1.getValue() - o2.getValue();
		findKthLargestByQuickSortPartition(entries, comparator, 0, entries.length - 1, entries.length - k);
		/**/
		int[] result = new int[k];
		for (int i = 0, j = entries.length - k; i < k; i++, j++) {
			result[i] = entries[j].getKey();
		}
		return result;
	}

	public static <T /*extends Comparable<T>*/> void findKthLargestByQuickSortPartition(T[] array, Comparator<T> comparator, int startIndex, int endIndex, int kIndex) {
		int pivotIndex = partition(array, comparator, startIndex, endIndex);
		if (kIndex == pivotIndex) {
			return;
		} else if (kIndex < pivotIndex) {// 从轴点的左半部分找
			findKthLargestByQuickSortPartition(array, comparator, startIndex, pivotIndex - 1, kIndex);
		} else {// kIndex > pivotIndex，从轴点的右半部分找
			findKthLargestByQuickSortPartition(array, comparator, pivotIndex + 1, endIndex, kIndex);
		}
	}

	/**
	 * @param array
	 * @param startIndex
	 * @param endIndex
	 * @param <T>
	 * @return 返回：轴点下标。轴点左边都是小于等于轴点的数；轴点右边都是大于轴点的数
	 */
	public static <T /*extends Comparable<T>*/> int partition(T[] array, Comparator<T> comparator, int startIndex, int endIndex) {
		if (startIndex > endIndex) {
			return -1;
		}

		if (startIndex == endIndex) {
			return startIndex;
		}

		T pivot = array[startIndex];// 轴点
		int i = startIndex;
		int j = endIndex;

		while (i < j) {
			/* 从右向左找，直到找到一个小于等于轴点的位置 */
			while (i < j && comparator.compare(array[j], pivot) > 0) {
				j--;
			}
			//
			if (i < j) {
				array[i] = array[j];// 填坑

				i++;
			}

			/* 从左向右找，直到找到一个大于轴点的位置 */
			while (i < j && comparator.compare(array[i], pivot) <= 0) {
				i++;
			}
			//
			if (i < j) {
				array[j] = array[i];// 填坑

				j--;
			}
		}

		//
		array[i] = pivot;// 填最后一个坑。

		return i;
	}

	/**
	 * @param array
	 * @param startIndex
	 * @param endIndex
	 * @param kIndex
	 * @param <T>
	 */
	public static <T extends Comparable<T>> void findKthLargestByQuickSortPartition(T[] array, int startIndex, int endIndex, int kIndex) {
		int pivotIndex = partition(array, startIndex, endIndex);
		if (kIndex == pivotIndex) {
			return;
		} else if (kIndex < pivotIndex) {// 从轴点的左半部分找
			findKthLargestByQuickSortPartition(array, startIndex, pivotIndex - 1, kIndex);
		} else {// kIndex > pivotIndex，从轴点的右半部分找
			findKthLargestByQuickSortPartition(array, pivotIndex + 1, endIndex, kIndex);
		}
	}

	/**
	 * @param array
	 * @param startIndex
	 * @param endIndex
	 * @param <T>
	 * @return 返回：轴点下标。轴点左边都是小于等于轴点的数；轴点右边都是大于轴点的数
	 */
	public static <T extends Comparable<T>> int partition(T[] array, int startIndex, int endIndex) {
		if (startIndex > endIndex) {
			return -1;
		}

		if (startIndex == endIndex) {
			return startIndex;
		}

		T pivot = array[startIndex];// 轴点
		int i = startIndex;
		int j = endIndex;

		while (i < j) {
			/* 从右向左找，直到找到一个小于等于轴点的位置 */
			while (i < j && array[j].compareTo(pivot) > 0) {
				j--;
			}
			//
			if (i < j) {
				array[i] = array[j];// 填坑

				i++;
			}

			/* 从左向右找，直到找到一个大于轴点的位置 */
			while (i < j && array[i].compareTo(pivot) <= 0) {
				i++;
			}
			//
			if (i < j) {
				array[j] = array[i];// 填坑

				j--;
			}
		}

		//
		array[i] = pivot;// 填最后一个坑。

		return i;
	}

}
