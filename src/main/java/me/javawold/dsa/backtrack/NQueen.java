package me.javawold.dsa.backtrack;

import me.javawold.dsa.Utils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * n皇后问题<br>
 * <br>
 * Copyright: Copyright (c) 2015<br>
 * Company: 北京云杉世界信息技术有限公司<br>
 * 
 *
 * @author suqianwen 2020年9月14日
 */
public class NQueen {

	/**
	 * 设计一种算法，打印 N 皇后在 N × N
	 * 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。
	 *
	 * @param n
	 * @return
	 * @author suqianwen 2020年9月14日
	 */
	public List<List<String>> solveNQueens(int n) {
		if (n <= 0) {
			return Collections.emptyList();
		}

		List<List<String>> lists = new ArrayList<>();
		// Map<Integer, Integer> rowMap = new HashMap<>();// key: row, value: column
		Map<Integer, Integer> columnMap = new HashMap<>();// key: column, value: row
		doSolveNQueens(n, /* rowMap, */columnMap, lists);

		return lists;
	}

	private void doSolveNQueens(int n, /* Map<Integer, Integer> rowMap, */ Map<Integer, Integer> columnMap,
			List<List<String>> lists) {
		if (n == columnMap.size()) {
			List<char[]> list = new ArrayList<>(n);
			for (int i = 0; i < n; i++) {
				char[] array = Utils.newArray(n, '.');
				list.add(array);
			}
			//
			columnMap.forEach((column, row) -> {
				list.get(row)[column] = 'Q';
			});
			//
			lists.add(list.stream().map(String::new).collect(Collectors.toList()));

			return;
		}

		for (int i = 0; i < n; i++) {// 当前层可选择列表
			// 矩阵“左上”下标为(0, 0)
			Integer row = /* rowMap */columnMap.size();// 第几层
			Integer column = i;// 第几列
			if (isValid(/* rowMap, */columnMap, n, row, column)// 当前层可选择 且 合法
			) {
				// 选择：每层选一个
				// rowMap.put(row, column);
				columnMap.put(column, row);

				// 递归调用：从下一层继续选择
				doSolveNQueens(n, /* rowMap, */columnMap, lists);

				// 取消选择
				// rowMap.remove(row);
				columnMap.remove(column);
			}
		}
	}

	private boolean isValid(/* Map<Integer, Integer> rowMap, */Map<Integer, Integer> columnMap, int n, Integer row,
			Integer column) {
		/* 同一行。从最高层到最底层，逐层深入，所以一定不会是同一行。 */
//		if (rowMap.containsKey(row)) {
//			return false;
//		}

		/* 同一列 */
		if (columnMap.containsKey(column)) {
			return false;
		}

		/* 对角线 */
		/* 左上 */
		int i = row - 1;
		int j = column - 1;
		Integer selectedRow;
		while (i >= 0 && j >= 0) {
			if ((selectedRow = columnMap.get(j)) != null && selectedRow == i) {
				return false;
			}

			i--;
			j--;
		}

		/* 右下 */
		i = row + 1;
		j = column + 1;
		while (i < n && j < n) {
			if ((selectedRow = columnMap.get(j)) != null && selectedRow == i) {
				return false;
			}

			i++;
			j++;
		}

		/* 右上 */
		i = row - 1;
		j = column + 1;
		while (i >= 0 && j < n) {
			if ((selectedRow = columnMap.get(j)) != null && selectedRow == i) {
				return false;
			}

			i--;
			j++;
		}

		/* 左下 */
		i = row + 1;
		j = column - 1;
		while (i < n && j >= 0) {
			if ((selectedRow = columnMap.get(j)) != null && selectedRow == i) {
				return false;
			}

			i++;
			j--;
		}

		return true;
	}

	/**
	 * 52. N皇后 II
	 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
	 *
	 *
	 *
	 * 上图为 8 皇后问题的一种解法。
	 *
	 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
	 *
	 * 示例:
	 *
	 * 输入: 4
	 * 输出: 2
	 * 解释: 4 皇后问题存在如下两个不同的解法。
	 * [
	 *  [".Q..",  // 解法 1
	 *   "...Q",
	 *   "Q...",
	 *   "..Q."],
	 *
	 *  ["..Q.",  // 解法 2
	 *   "Q...",
	 *   "...Q",
	 *   ".Q.."]
	 * ]
	 *
	 *
	 * 提示：
	 *
	 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或 N-1 步，可进可退。（引用自 百度百科 - 皇后 ）
	 *
	 * @param n
	 * @return
	 */
	public int totalNQueens(int n) {
		if (n <= 0) {
			return 0;
		}

		AtomicInteger count = new AtomicInteger();
		// Map<Integer, Integer> rowMap = new HashMap<>();// key: row, value: column
		Map<Integer, Integer> columnMap = new HashMap<>();// key: column, value: row
		doSolveNQueens(n, /* rowMap, */columnMap, count);

		return count.get();
	}

	private void doSolveNQueens(int n, /* Map<Integer, Integer> rowMap, */ Map<Integer, Integer> columnMap,
								AtomicInteger count) {
		if (n == columnMap.size()) {
			count.incrementAndGet();

			return;
		}

		for (int i = 0; i < n; i++) {// 当前层可选择列表
			// 矩阵“左上”下标为(0, 0)
			Integer row = /* rowMap */columnMap.size();// 第几层
			Integer column = i;// 第几列
			if (isValid(/* rowMap, */columnMap, n, row, column)// 当前层可选择 且 合法
					) {
				// 选择：每层选一个
				// rowMap.put(row, column);
				columnMap.put(column, row);

				// 递归调用：从下一层继续选择
				doSolveNQueens(n, /* rowMap, */columnMap, count);

				// 取消选择
				// rowMap.remove(row);
				columnMap.remove(column);
			}
		}
	}

	public static void main(String[] args) {
		List<List<String>> lists = new NQueen().solveNQueens(4);
		lists.forEach(list -> {
			System.err.println();
			list.forEach(System.err::println);
		});
	}

}
