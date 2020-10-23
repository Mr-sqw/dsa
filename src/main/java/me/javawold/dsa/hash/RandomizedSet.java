package me.javawold.dsa.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 380. 常数时间插入、删除和获取随机元素
设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构。

insert(val)：当元素 val 不存在时，向集合中插入该项。
remove(val)：元素 val 存在时，从集合中移除该项。
getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
示例 :

// 初始化一个空的集合。
RandomizedSet randomSet = new RandomizedSet();

// 向集合中插入 1 。返回 true 表示 1 被成功地插入。
randomSet.insert(1);

// 返回 false ，表示集合中不存在 2 。
randomSet.remove(2);

// 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
randomSet.insert(2);

// getRandom 应随机返回 1 或 2 。
randomSet.getRandom();

// 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
randomSet.remove(1);

// 2 已在集合中，所以返回 false 。
randomSet.insert(2);

// 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
randomSet.getRandom();<br>
 * <br>
 * Copyright: Copyright (c) 2015<br>
 * Company: 北京云杉世界信息技术有限公司<br>
 * 
 *
 * @author suqianwen 2020年10月23日
 */
public class RandomizedSet {

	/**
	 * key：key, value: index(key在keys中的下标)
	 */
	private Map<Integer, Integer> keyIndexMap;

	private List<Integer> keys;

	private Random random;

	/** Initialize your data structure here. */
	public RandomizedSet() {
		keyIndexMap = new HashMap<>();
		keys = new ArrayList<>();
		random = new Random();
	}

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
	public boolean insert(int val) {
		Integer index = keyIndexMap.putIfAbsent(val, keys.size());
		if (index != null) {// 已存在
			return false;
		}

		keys.add(val);
		return true;
	}

    /** Removes a value from the set. Returns true if the set contained the specified element. */
	public boolean remove(int val) {
		Integer index = keyIndexMap.remove(val);
		if (index == null) {// 不存在
			return false;
		}

		/* 将尾部元素 放到index处；再删除尾部元素 */
		int tailIndex = keys.size() - 1;
		if (index == tailIndex) {
			keys.remove(tailIndex);
		} else {
			Integer tailKey = keys.get(tailIndex);
			keyIndexMap.put(tailKey, index);
			keys.set(index, tailKey);
			//
			keys.remove(tailIndex);
		}
		return true;
	}

    /** Get a random element from the set. */
	public int getRandom() {
		if (keys.isEmpty()) {
			throw new IllegalStateException();
		}
		return keys.get(random.nextInt(keys.size()));
	}

	public static void main(String[] args) {
		RandomizedSet set = new RandomizedSet();
		set.insert(1);
		set.remove(2);
		set.insert(2);
		int val = set.getRandom();
		set.remove(1);
		set.insert(2);
		val = set.getRandom();
		System.out.println(val);
	}

}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */