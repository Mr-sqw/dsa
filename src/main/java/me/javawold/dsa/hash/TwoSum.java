package me.javawold.dsa.hash;

import java.util.HashMap;

public class TwoSum {

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int length;
        if (nums == null || (length = nums.length) == 0) {
            return null;
        }

        HashMap<Integer, Integer> map = new HashMap<>(length);
        map.put(nums[0], 0);
        for (int i = 1; i < length; i++) {
            Integer rightIndex = map.get(target - nums[i]);
            if (rightIndex != null) {
                return new int[]{i, rightIndex};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }

}
