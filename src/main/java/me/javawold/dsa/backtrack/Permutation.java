package me.javawold.dsa.backtrack;

import java.util.*;

/**
 * 排列。
 * 回溯算法：暴力穷举出决策树的所有分支；
 * 1.不断从可/未选择列表中选出元素，加入已选择列表，直到选择完一个分支；
 * 2.然后反向逐个取消选择[从已选择列表中移除]，再不断从 已选择列表(此时需要排除刚刚取消的上一个元素)中选出元素，...
 */
public class Permutation {

    /**
     * 输入一个字符串，打印出该字符串中字符的所有排列。
     *
     * @param s
     * @return
     */
    public String[] permutation(String s) {
        if (s == null || s.length() == 0) {
            return new String[0];
        }

        List<String> returnList = new ArrayList<>();
        doPermutation(s.toCharArray(), new LinkedHashSet<>(s.length()), returnList);
        return returnList.toArray(new String[returnList.size()]);
    }

    /**
     * 隐式利用方法栈的DFS
     *
     * @param arr
     * @param selectedSet 寻找一个全排列时的[上层]已选择列表。根据已选择列表，可相应地求出可选择列表
     * @param returnList
     */
	private void doPermutation(char[] arr, LinkedHashSet<Integer> selectedSet, List<String> returnList) {
		if (selectedSet.size() == arr.length) {/* 一个完整的排列选择完毕，添加到返回列表。 结束条件：可选择列表为空。到达最底层，决策树的一个分支遍历完毕 */
			StringBuilder sb = new StringBuilder();
			selectedSet.forEach(index -> sb.append(arr[index]));
			returnList.add(sb.toString());

			return;// 到达最底层，决策树的一个分支遍历完毕。返回到上一层/倒数第二层(回溯): doPermutation 或 整个决策树遍历完毕。
		}

		for (int i = 0; i < arr.length; i++) {
			if (selectedSet.add(i)) {// 1.从当前层“剩下”的可选择列表中 选择 一个元素/节点来组成排列。 递归调用之前做选择。

				// 2.继续从 以当前层以 当前选择的元素/节点 为根的下一层的“剩下”的可选择列表中选择一个元素来组成排列。 递归调用
				doPermutation(arr, selectedSet, returnList);

				// 3.1.当前层以当前选择的元素为根的所有分支遍历完毕，继续以当前层其他可选择节点为根寻找分支。
				// 3.2.同时取消选择 当前层当前选择的元素。以便当前层遍历完毕，返回到上层(回溯)时，上层可以使用到这个元素。 递归调用之后撤销选择。
				// 取消的一定是当前层已经选择过的 及 当前层下面层已经选择过的，不能取消还没有选择的 及 当前层上面层已经选择过的。
				selectedSet.remove(i);
			} else {// 该元素已被选择，继续遍历，从“剩下”的可选择列表中选择一个元素来组成排列

			}
		} // 当前层的可选择列表中的所有元素/节点 遍历完毕，返回到上一层(回溯): doPermutation 或 整个决策树遍历完毕。
	}

    public void doPermutationByDFS(char[] arr) {
        Set<String> returnSet = new HashSet<>();
        LinkedHashSet<Integer> selectedSet = new LinkedHashSet<>(arr.length);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            stack.clear();
            stack.addFirst(i);
            selectedSet.add(i);

            while (!stack.isEmpty()) {
                /*从“剩下”的可选择列表中 选择 一个元素来组成排列*/
                for (int j = 0; j < arr.length; j++) {
                    if (selectedSet.add(j)) {
                        stack.push(i);
                    }
                }

                if (stack.size() == arr.length) {
                    /*一个完整的排列选择完毕，添加到返回列表。*/
                    StringBuilder sb = new StringBuilder();
                    Iterator<Integer> iterator = stack.descendingIterator();
                    while (iterator.hasNext()) {
                        sb.append(arr[iterator.next()]);
                    }
                    returnSet.add(sb.toString());

                    stack.pop();
                }

            }

        }
    }

	/**
	 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
	 *
	 * @param nums
	 * @return
	 * @author suqianwen 2020年9月19日
	 */
	public List<List<Integer>> permute(int[] nums) {
		if (nums == null || nums.length == 0) {
			return Collections.emptyList();
		}

		List<List<Integer>> lists = new ArrayList<>();
		Set<Integer> set = new LinkedHashSet<>();
		doPermute(nums, set, lists);
		return lists;
	}

	private void doPermute(int[] nums, Set<Integer> set, List<List<Integer>> lists) {
		if (set.size() == nums.length) {
			List<Integer> list = new ArrayList<>(set);
			lists.add(list);

			return;
		}

		for (int num : nums) {
			if (set.add(num)) {// 选择

				doPermute(nums, set, lists);

				set.remove(num);
			}
		}
	}

	/**
	 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
	 *
	 * @param nums
	 * @return
	 * @author suqianwen 2020年9月19日
	 */
	public List<List<Integer>> permuteUnique(int[] nums) {
		if (nums == null || nums.length == 0) {
			return Collections.emptyList();
		}

		List<List<Integer>> lists = new ArrayList<>();
		Set<Integer> indexSet = new LinkedHashSet<>();
		doPermuteUnique(nums, indexSet, lists);
		return lists;
	}

	private void doPermuteUnique(int[] nums, Set<Integer> indexSet, List<List<Integer>> lists) {
		if (indexSet.size() == nums.length) {
			List<Integer> list = new ArrayList<>(nums.length);
			indexSet.forEach(index -> list.add(nums[index]));
			lists.add(list);

			return;
		}

		// 当前层已经选择的数字，重复数字不能多次选择
		Set<Integer> currLevelNumSet = new HashSet<>(nums.length - indexSet.size());
		for (int i = 0; i < nums.length; i++) {
			if (indexSet.add(i)// 选择/剪枝：过滤掉上层已经选择的；本层已经选择的直接通过for循环就已经过滤了，但是没有过滤掉重复数字。
			) {

				if (currLevelNumSet.add(nums[i])) { // 选择/剪枝：过滤掉当前层已经选择的 重复数字，不用再继续深入下层，因为当前层前面的兄弟节点已经做过选择了。
					doPermuteUnique(nums, indexSet, lists);
				}

				indexSet.remove(i);
			}
		}
	}

    public static void main(String[] args) {
		int[] nums = { 1, 1, 2 };
		List<List<Integer>> lists = new Permutation().permuteUnique(nums);
		lists.forEach(System.out::println);

		String[] strings = new Permutation().permutation("123");
		Arrays.stream(strings).forEach(System.out::println);
    }

}
