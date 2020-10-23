package me.javawold.dsa.backtrack;

import java.util.*;

/**
 * 子集问题
 */
public class SubsetProblem {

    /**
     * 78. 子集
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     *
     * 说明：解集不能包含重复的子集。
     *
     * 示例:
     *
     * 输入: nums = [1,2,3]
     * 输出:
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0)
            return Collections.emptyList();

        List<List<Integer>> subsets = new ArrayList<>();
        Set<Integer> subset = new HashSet<>();
        backtrack(nums, 0, subset, subsets);

        return subsets;
    }

    private void backtrack(int[] nums, int start, Set<Integer> subset, List<List<Integer>> subsets) {
        subsets.add(new ArrayList<>(subset));

        for (int i = start; i < nums.length; i++) {
            //选择
            subset.add(nums[i]);

            //
            backtrack(nums, i + 1, subset, subsets);

            //取消选择
            subset.remove(nums[i]);
        }
    }

	public static void main(String[] args) {
		new SubsetProblem().subsets(new int[] { 1, 2, 3 });
	}

}
