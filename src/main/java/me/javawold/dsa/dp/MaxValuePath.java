package me.javawold.dsa.dp;

public class MaxValuePath {

	/**
	 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。

示例:

输入:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 7
解释: 因为路径 1→3→1→1→1 的总和最小。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-path-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param grid
	 * @return
	 * @author suqianwen 2020年9月22日
	 */
	public int minPathSum(int[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}

		// 状态定义。dpTable[i][j]：从[0 ,0]到[i, j]的最小路径和。
		int rowLength = grid.length;
		int columnLength = grid[0].length;
		int[][] dpTable = new int[rowLength][columnLength];
		/* 初始化状态。 */
		//
		dpTable[0][0] = grid[0][0];
		// 第一行。只能向右移动
		for (int j = 1; j < columnLength; j++) {
			dpTable[0][j] = dpTable[0][j - 1] + grid[0][j];
		}
		// 第一列。只能向下移动
		for (int i = 1; i < rowLength; i++) {
			dpTable[i][0] = dpTable[i - 1][0] + grid[i][0];
		}

		// 状态转移方程：dpTable[i][j] = Math.min(dpTable[i-1][j], dpTable[i][j-1]) +
		// grid[i][j]
		for (int i = 1; i < rowLength; i++) {
			for (int j = 1; j < columnLength; j++) {
				dpTable[i][j] = Math.min(dpTable[i - 1][j], dpTable[i][j - 1]) + grid[i][j];
			}
		}

		return dpTable[rowLength - 1][columnLength - 1];
	}

    // 以(row,col)为起点到最底层节点的最大价值路径
    public int maxValuePath(int[][] matrix, int row, int col) {
        int rowLength = matrix.length;
        if (row == rowLength - 1) {// 最底层
            return matrix[row][col];
        }

        int[][] maxValueMatrix = new2dIntArray(rowLength, matrix[0].length, -1);
        if (matrix[row][col] >= 0) {
            return maxValueMatrix[row][col];
        }

        int maxValue = matrix[row][col] + Math.max(//
                maxValuePath(matrix, row + 1, col)// 向左下
                , maxValuePath(matrix, row + 1, col + 1)// 向右下
        );
        maxValueMatrix[row][col] = maxValue;
        return maxValue;
    }

    public static int[][] new2dIntArray(int rowLength, int colLength, int initValue) {
        int[][] matrix = new int[rowLength][colLength];
        if (initValue != 0) {

        }

        return matrix;
    }

	public static void main(String[] args) {
		int[][] grid = { { 1, 2, 5 }, { 3, 2, 1 } };
		int minSum = new MaxValuePath().minPathSum(grid);
		System.out.println(minSum);
	}

}
