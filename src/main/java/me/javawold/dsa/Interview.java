package me.javawold.dsa;

public class Interview {

	public static void printRing(int[][] arrs) {
		if (arrs == null || arrs.length == 0) {
			return;
		}

		int startI = 0, startJ = 0, rowLength = arrs.length, colLength = arrs[0].length;
		for (; startI < rowLength && startJ < colLength; startI++, startJ++, rowLength--, colLength--) {
			System.out.print("第" + (startI + 1) + "圈: ");
			int i = startI;// minI
			int j = startJ;// [minJ -> maxJ]
			// top
			for (; j < colLength; j++) {
				System.out.print(arrs[i][j] + "\t");
			}

			//
			i++;// [2ndMinI -> maxI]
			if (i >= rowLength) {// 没有右下左部分
				return;
			}
			// right
			j--;// maxJ
			for (; i < rowLength; i++) {
				System.out.print(arrs[i][j] + "\t");
			}

			// bottom
			i--;// maxI
			j--;// [2ndMaxJ -> minJ]
			for (; j >= startJ; j--) {
				System.out.print(arrs[i][j] + "\t");
			}

			// left
			i--;// [2ndMaxI -> 2ndMinI]
			j++;// minJ
			for (; i > startI; i--) {
				System.out.print(arrs[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public static void printRingForMatrix(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || //
				matrix.length != matrix[0].length// 非矩阵
		) {
			return;
		}

		int startI = 0, startJ = 0, length = matrix.length;
		for (; startI < length; startI++, startJ++, length--) {
			System.out.print("第" + (startI + 1) + "圈: ");
			int i = startI;// minI
			int j = startJ;// [minJ -> maxJ]
			// top
			for (; j < length; j++) {
				System.out.print(matrix[i][j] + "\t");
			}

			// right
			i++;// [2ndMinI -> maxI]
			j--;// maxJ
			for (; i < length; i++) {
				System.out.print(matrix[i][j] + "\t");
			}

			// bottom
			i--;// maxI
			j--;// [2ndMaxJ -> minJ]
			for (; j >= startJ; j--) {
				System.out.print(matrix[i][j] + "\t");
			}

			// left
			i--;// [2ndMaxI -> 2ndMinI]
			j++;// minJ
			for (; i > startI; i--) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// error
		int[][] maxtrix2 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		printRing(maxtrix2);
		System.out.println();

		int[][] maxtrix = { { 1, 2, 3 }, { 8, 9, 4 }, { 7, 6, 5 } };
		printRing(maxtrix);
		System.out.println();

		int[][] maxtrix3 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		printRing(maxtrix3);
		System.out.println();

		int[][] maxtrix4 = { { 1, 2 }, { 3, 4 }, { 5, 6 }, { 7, 8 }, { 9, 10 } };
		printRing(maxtrix4);
		System.out.println();

		int[][] maxtrix5 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		printRing(maxtrix5);
		System.out.println();
	}

}
