package me.javawold.dsa.random;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 洗牌算法
 */
public class Shuffle {

    /**
     * 经典洗牌算法：每次从未处理的数据中随机取出一个数字，然后把该数字放在数组的尾部，即数组尾部存放的是已经处理过的数字。
     *
     * @param list
     * @param k
     * @author suqianwen 2020年8月13日
     */
    public void Knuth_Durstenfeld_Shuffle(List<Integer> list, int k) {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        int n = list.size();
        for (int i = 0; i < k; i++) {
            int m = random.nextInt(n);// [0,n)
            n--;
            swap(list, m, n);// 随机选中的数和尾部的数交换。
        }

        /* 此时，list中的后k个数是list中随机等概率取出的k个元素 */
    }

    /***
     * 蓄水池抽样
     * 从N个元素中随机等概率取出k个元素，N长度未知。它能够在o（n）时间内对n个数据进行等概率随机抽取。如果数据集合的量特别大或者还在增长（相当于未知数据集合总量），该算法依然可以等概率抽样
     *
     * @param list 可动态向列表尾部追加元素
     * @param k
     * @author suqianwen 2020年8月13日
     */
    public void reservoirSampling(List<Integer> list, int k) {
        Random random = new Random();

        for (int i = k; i < list.size()/* 支持动态向list尾部追加元素 */; i++) {
            int m = random.nextInt(i);// [0,i)
            if (m < k) {
                swap(list, m, i);
            }
        }
        /* 此时，list中的前k个数是list中随机等概率取出的k个元素 */
    }

    public static <E> void swap(List<E> list, int index1, int index2) {
        E e = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, e);
    }

}
