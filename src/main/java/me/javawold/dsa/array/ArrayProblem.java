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

	/**
	 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
	 *
	 *  
	 *
	 * 说明:
	 *
	 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
	 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
	 *  
	 * 示例:
	 *
	 * 输入:
	 * nums1 = [1,2,3,0,0,0], m = 3
	 * nums2 = [2,5,6],       n = 3
	 *
	 * 输出: [1,2,2,3,5,6]
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * 从小到大归并
	 */
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		if (n==0) return;

		int[] nums1Copy = new int[m];
		System.arraycopy(nums1,0,nums1Copy,0,m);
		//
		int i = 0;
		int j = 0;
		int k = 0;
		while(i<nums1Copy.length && j< nums2.length){
			if(nums1Copy[i]<nums2[j]){
				nums1[k++] = nums1Copy[i++];
			}else{
				nums1[k++] = nums2[j++];
			}
		}
		while(i<nums1Copy.length){
			nums1[k++] = nums1Copy[i++];
		}
		while(j< nums2.length){
			nums1[k++] = nums2[j++];
		}
	}

	/**
	 * 从大到小归并
	 * @param nums1
	 * @param m
	 * @param nums2
	 * @param n
	 */
	public void mergeV2(int[] nums1, int m, int[] nums2, int n) {
		if (n==0) return;

		//
		/*int i = nums1.length-1;
		while(i>=0 && nums1[i]==0){i--;}*/
		int i = m-1;
		//
		int j = n-1/*nums2.length-1*/;
		int k = m+n-1;
		while(i>=0 && j>=0){
			if(nums1[i]>nums2[j]){
				nums1[k--] = nums1[i--];
			}else{
				nums1[k--] = nums2[j--];
			}
		}
//		while(i>=0){
//			nums1[k--] = nums1[i--];
//		}
		while(j>=0){
			nums1[k--] = nums2[j--];
		}
	}

	public int[] merge(int[] arr1, int[] arr2){
		if(arr1 == null || arr1.length == 0){
			return arr2;
		}

		if(arr2 == null || arr2.length == 0){
			return arr1;
		}

		int[] returnArr = new int[arr1.length+ arr2.length];
		int i = 0;
		int j = 0;
		int k = 0;
		while(i<arr1.length && j< arr2.length){
			if(arr1[i]<arr2[j]){
				returnArr[k++] = arr1[i++];
			}else{
				returnArr[k++] = arr2[j++];
			}
		}
		while(i<arr1.length){
			returnArr[k++] = arr1[i++];
		}
		while(j< arr2.length){
			returnArr[k++] = arr2[j++];
		}
		return returnArr;
	}

	/**
	 * 977. 有序数组的平方
	 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
	 *
	 *
	 *
	 * 示例 1：
	 *
	 * 输入：[-4,-1,0,3,10]
	 * 输出：[0,1,9,16,100]
	 * 示例 2：
	 *
	 * 输入：[-7,-3,2,3,11]
	 * 输出：[4,9,9,49,121]
	 *
	 *
	 * 提示：
	 *
	 * 1 <= A.length <= 10000
	 * -10000 <= A[i] <= 10000
	 * A 已按非递减顺序排序。
	 *
	 * @param A
	 * @return
	 */
	public int[] sortedSquares(int[] A) {
		if (A == null || A.length == 0)
			return null;


		int i = 0;//大于等于0的元素的下标
		for (; i < A.length; i++) {
			if (A[i] >= 0) {
				break;
			}
		}
		int j = i - 1;//小于0的元素的下标
		int k = 0;
		int[] result = new int[A.length];
		while (i < A.length && j >= 0) {
			if (A[i] > -A[j]) {
				result[k++] = A[j] * A[j];
				j--;
			} else {
				result[k++] = A[i] * A[i];
				i++;
			}
		}
		//
		while (i < A.length) {
			result[k++] = A[i] * A[i];
			i++;
		}
		//
		while (j >= 0) {
			result[k++] = A[j] * A[j];
			j--;
		}
		return result;
	}

}
