package me.javawold.dsa.dp;

public class MaxValuePath {

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

}
