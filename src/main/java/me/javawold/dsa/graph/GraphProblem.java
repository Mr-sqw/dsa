package me.javawold.dsa.graph;

public class GraphProblem {

    /**
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     *
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
     *
     * 此外，你可以假设该网格的四条边均被水包围。
     *
     *  
     *
     * 示例 1:
     *
     * 输入:
     * [
     * ['1','1','1','1','0'],
     * ['1','1','0','1','0'],
     * ['1','1','0','0','0'],
     * ['0','0','0','0','0']
     * ]
     * 输出: 1
     * 示例 2:
     *
     * 输入:
     * [
     * ['1','1','0','0','0'],
     * ['1','1','0','0','0'],
     * ['0','0','1','0','0'],
     * ['0','0','0','1','1']
     * ]
     * 输出: 3
     * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/number-of-islands
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param grid
     * @return
     */
	public int numIslands(char[][] grid) {
		if (grid == null || grid.length == 0)
			return 0;

		int rowLength = grid.length;
		int columnLength = grid[0].length;
		boolean[][] visited = new boolean[rowLength][];
		for (int i = 0; i < rowLength; i++) {
			visited[i] = new boolean[columnLength];
		}

		int num = 0;
		while (true) {
			/* 1.找出未访问且是陆地的坐标 */
			int i = 0;// 顶点下标
			int j = 0;
			label: for (; i < rowLength; i++) {
				for (; j < columnLength; j++) {
					if (!visited[i][j]) {
						if (grid[i][j] == '1') {
							break label;
						}

						visited[i][j] = true;
					}
				}
			}

			/* 2.以1中找出的坐标为基准，进行dfs，找到一份连通图 */
			dfs(i, rowLength, columnLength, grid, visited);

			num++;

			/* 访问完整个坐标系，结束 */
			if (i == rowLength && j == columnLength) {
				return num;
			}
		}
	}

	public void dfs(int rowIndex, int rowLength, int columnLength, char[][] grid, boolean[][] visited) {
		if (rowIndex < 0 || rowIndex >= rowLength) {
			return;
		}

		for (int j = 0; j < columnLength; j++) {
			if (!visited[rowIndex][j]) {

				visited[rowIndex][j] = true;

				if (grid[rowIndex][j] == '1') {
					dfs(j, rowLength, columnLength, grid, visited);
				}
			}
		}
	}

}
