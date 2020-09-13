package me.javawold.dsa;

/**
 * @author Mr_sqw
 * @created 2020年8月29日 - 上午11:33:39
 */
public class InverseNum {

	/**
	 * 求数组中的逆序对个数
	 * 
	 * @param arr
	 * @return
	 */
	public static int inverseNum(int[] arr) {
		return inverseNumByMergeSort(arr, new int[arr.length], 0, arr.length - 1);
	}

	public static int inverseNumByMergeSort(int[] arr, int[] tmpArr, int from, int to) {
		if (from == to) {
			return 0;
		}

		int mid = (to - from) / 2 + from;// from < to
		int leftInverseNum = inverseNumByMergeSort(arr, tmpArr, from, mid);
		int rightInverseNum = inverseNumByMergeSort(arr, tmpArr, mid + 1, to);

		return leftInverseNum + rightInverseNum + merge(arr, tmpArr, from, to, mid);
	}

	private static int merge(int[] arr, int[] tmpArr, int from, int to, int mid) {
		int inverseNum = 0;
		int i = from;// 左半部分起始位置
		int j = mid + 1;// 右半部分起始位置
		int k = from;
		while (i <= mid && j <= to) {
			if (arr[i] <= arr[j]) {
				tmpArr[k++] = arr[i];
				i++;
			} else {// arr[i] > arr[j]
				inverseNum += (mid - i + 1);// arr[i...mid]与arr[j]构成逆序对
				tmpArr[k++] = arr[j];
				j++;
			}
		}
		while (i <= mid) {
			tmpArr[k++] = arr[i++];
			// 右半部分已合并结束，剩下的左半部分的所有元素arr[i...mid] 与
			// 右半部分的所有元素arr[mid+1...to]构成逆序对，但是这些逆序对已计算过。
			// inverseNum += (to - mid);
		}
		while (j <= to) {// 左半部分已合并结束
			tmpArr[k++] = arr[j++];
		}
		//
		System.arraycopy(tmpArr, from, arr, from, to - from + 1);

		return inverseNum;
	}

	public static int inverseNumByInsertionSort(int[] arr, int from, int to) {
		return 0;
	}

	public static void main(String[] args) {
		int[] arr = { 2, 3, 8, 6, 1 };
		int inverseNum = inverseNum(arr);
		System.out.println(inverseNum);
	}

}
