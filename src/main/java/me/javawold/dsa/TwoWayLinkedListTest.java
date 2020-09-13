package me.javawold.dsa;

import java.util.Iterator;
import java.util.Random;

/**
 * 双向链表功能测试
 * 
 * @author mr_sqw
 *
 */
public class TwoWayLinkedListTest {

	public static void main(String[] args) {
		reverseTest(10000);
	}

	/**
	 * 双向链表反转功能测试。测试失败，在System.err上输出"reverse error"。
	 * 
	 * @param numOfTests
	 *            测试次数
	 */
	public static void reverseTest(int numOfTests) {
		if (numOfTests < 1) {
			return;
		}

		Random random = new Random();
		for (int i = 0; i < numOfTests; i++) {
			int size = (int) (random.nextDouble() * 10000) + 1;// 每次测试为链表添加[1, 10000)个节点。
			int[] arr = new int[size];
			TwoWayLinkedList<Integer> list = new TwoWayLinkedList<>();

			for (int j = 0; j < size; j++) {
				int elem = random.nextInt();
				arr[j] = elem;
				list.addLast(elem);
			}

			list.reverse();
			Iterator<Integer> iter = list.iterator();
			int index = size - 1;
			while (iter.next() != arr[index--]) {
				System.err.println("reverse error");
				return;
			}
		}
	}

}
