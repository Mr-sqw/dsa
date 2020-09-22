package me.javawold.dsa;

import java.util.HashMap;
import java.util.Map;

/**
 * hashmap + 双端链表
 */
public class LRUCache {

    /**
     * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
     *
     * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
     * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
     *
     *  
     *
     * 进阶:
     *
     * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
     *
     *  
     *
     * 示例:
     *
     * LRUCache cache = new LRUCache( 2 /* 缓存容量 */ /*);
     *
             *cache.put(1,1);
     *cache.put(2,2);
     *cache.get(1);       // 返回  1
     *cache.put(3,3);    // 该操作会使得关键字 2 作废
     *cache.get(2);       // 返回 -1 (未找到)
     *cache.put(4,4);    // 该操作会使得关键字 1 作废
     *cache.get(1);       // 返回 -1 (未找到)
     *cache.get(3);       // 返回  3
     *cache.get(4);       // 返回  4
     *
             *来源：力扣（LeetCode）
            *链接：https://leetcode-cn.com/problems/lru-cache
            *著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    Map<Integer, DoubleEndedList.Node> map;

    DoubleEndedList doubleEndedList;

    int capacity;

    public LRUCache(int capacity) {
        this.map = new HashMap<>(capacity);
        this.doubleEndedList = new DoubleEndedList();
        this.capacity = capacity;
    }

    public int get(int key) {
        DoubleEndedList.Node node = this.map.get(key);
        if (node == null) {
            return -1;
        } else {
            put(node.key, node.val);//将刚访问的key加入队尾
            return node.val;
        }
    }

    public void put(int key, int value) {
        DoubleEndedList.Node node = this.map.get(key);
        if (node == null) {//新节点
            if (this.map.size() == this.capacity) {
                //超过容量，移除双端队列队头元素
                DoubleEndedList.Node head = this.doubleEndedList.dequeue();
                this.map.remove(head.key);
            }

            /*将新节点加入队尾*/
            node = new DoubleEndedList.Node(key, value);
            this.map.put(key, node);
            this.doubleEndedList.enqueue(node);
        } else {//老节点
            node.val = value;
            /*移除老节点，将老节点重新加入队尾*/
            this.doubleEndedList.remove(node);

            this.map.put(key, node);
            this.doubleEndedList.enqueue(node);//将老节点重新加入队尾
        }
    }

}

/**
 * 双端链表
 */
class DoubleEndedList {

    private Node head;

    private Node tail;

    /**
     * 入队
     *
     * @param node
     */
    public void enqueue(Node node) {
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }

    /**
     * 出队
     *
     * @return
     */
    public Node dequeue() {
        if (this.head == null) {
            return null;
        }

        Node head = this.head;

        if (head.next != null) {
            head.next.prev = null;
        }
        this.head = head.next;

        return head;
    }

    /**
     * @param node
     */
    public void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    static class Node {

        int key;

        int val;

        Node prev;

        Node next;

        Node() {

        }

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

        Node(int key, int val, Node prev, Node next) {
            this(key, val);
            this.prev = prev;
            this.next = next;
        }

    }

}
