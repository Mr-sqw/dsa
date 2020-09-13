package me.javawold.dsa;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 双向链表
 * 
 * @author mr_sqw
 *
 * @param <T>
 */
// @NotThreadSafe
public class TwoWayLinkedList<T> {

	private Node<T> first;

	private Node<T> last;

	/**
	 * 反转
	 */
	public void reverse() {
		Node<T> currNode = first;
		Node<T> prev = null;
		Node<T> next = null;

		while (currNode != null) {
			next = currNode.next;

			currNode.next = prev;
			currNode.prev = next;

			prev = currNode;
			currNode = next;
		}

		first = prev;
	}

	public void addLast(T e) {
		Node<T> l = last;
		Node<T> newNode = new Node<>(e, l, null);
		last = newNode;
		if (l == null) {
			first = newNode;
		} else {
			l.next = newNode;
		}
	}

	public T removeLast() {
		Node<T> l = last;
		if (l == null) {
			throw new NoSuchElementException();
		}
		T data = l.data;
		Node<T> prev = l.prev;
		l.data = null;
		l.prev = null;
		last = prev;
		if (prev == null)
			first = null;
		else
			prev.next = null;

		return data;
	}

	public Iterator<T> iterator() {
		return new MyIterator();
	}

	private static class Node<T> {

		T data;

		Node<T> prev;

		Node<T> next;

		public Node(T data, Node<T> prev, Node<T> next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}

	}

	private class MyIterator implements Iterator<T> {

		private Node<T> currNode = first;

		@Override
		public boolean hasNext() {
			return currNode != null;
		}

		@Override
		public T next() {
			if (currNode == null) {
				throw new NoSuchElementException();
			}
			T data = currNode.data;
			currNode = currNode.next;

			return data;
		}

	}

}
