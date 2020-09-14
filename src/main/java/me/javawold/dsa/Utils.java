package me.javawold.dsa;

/**
 * <br>
 * <br>
 * Copyright: Copyright (c) 2015<br>
 * Company: 北京云杉世界信息技术有限公司<br>
 * 
 *
 * @author suqianwen 2020年9月14日
 */
public class Utils {

	private Utils() {

	}

	/**
	 * 
	 *
	 * @param length
	 * @param initChar
	 * @return
	 * @author suqianwen 2020年9月14日
	 */
	public static char[] newArray(int length, char initChar) {
		char[] array = new char[length];
		for (int i = 0; i < length; i++) {
			array[i] = initChar;
		}
		return array;
	}

	/**
	 * 
	 *
	 * @param row
	 * @param column
	 * @return
	 * @author suqianwen 2020年9月14日
	 */
	public static char[][] new2dArray(int row, int column) {
		char[][] matrix = new char[row][column];
		for (int i = 0; i < row; i++) {
			matrix[i] = new char[column];
		}
		return matrix;
	}

	/**
	 * 
	 *
	 * @param row
	 * @param column
	 * @param initChar
	 * @return
	 * @author suqianwen 2020年9月14日
	 */
	public static char[][] new2dArray(int row, int column, char initChar) {
		char[][] matrix = new2dArray(row, column);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				matrix[i][j] = initChar;
			}
		}
		return matrix;
	}

}
