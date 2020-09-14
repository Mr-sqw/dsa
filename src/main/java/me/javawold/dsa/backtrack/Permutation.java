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
     * @param selectedSet 寻找一个全排列时的已选择列表。根据已选择列表，可相应地求出可选择列表
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

    public static void main(String[] args) {
        String[] strings = new Permutation().permutation("123");
        Arrays.stream(strings).forEach(str -> System.out.println(str));
    }

}
