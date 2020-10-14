package me.javawold.dsa.concurrency;

/**
 * <br>
 * <br>
 * Copyright: Copyright (c) 2015<br>
 * Company: 北京云杉世界信息技术有限公司<br>
 * 
 *
 * @author suqianwen 2020年10月14日
 */
public class MyArrayBlockingQueue<E> {

	private final Object[] elems;

	private int size;

	private Object lock;

	public MyArrayBlockingQueue(int capacity) {
		this.elems = new Object[capacity];
		this.lock = new Object();
	}

	public void put(E elem) {
		synchronized (lock) {
			while (size == elems.length) {
				try {
					lock.wait();// 队列已满，入队线程阻塞等待
				} catch (InterruptedException e) {
//					Thread.currentThread().interrupt();// 恢复中断标记
//					return;// 返回，响应中断

					// 继续判断谓词/条件是否满足，不响应中断
				}
			}

			elems[size++] = elem;

			// 队列不是空；唤醒阻塞等待出队的线程
			if (size == 1) {
				lock.notifyAll();
			}
		}
	}

	public E take() {
		synchronized (lock) {
			while (size == 0) {
				try {
					lock.wait();// 队列为空，出队线程阻塞等待
				} catch (InterruptedException e) {
//					Thread.currentThread().interrupt();// 恢复中断标记
//					return;// 返回，响应中断

					// 继续判断谓词/条件是否满足，不响应中断
				}
			}

			@SuppressWarnings("unchecked")
			E elem = (E) elems[--size];

			// 队列不是满；唤醒阻塞等待入队的线程
			if (size == elems.length - 1) {
				lock.notifyAll();
			}

			return elem;
		}

	}

}
