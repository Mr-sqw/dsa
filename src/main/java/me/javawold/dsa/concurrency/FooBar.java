package me.javawold.dsa.concurrency;

/**
 * @author Mr_sqw
 * @created 2020年8月22日 - 上午1:32:27
 */
public class FooBar {
	
	private int n;

    private Object lock = new Object();

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        
        for (int i = 0; i < n; ) {
            synchronized(lock){//先做再发通知(通知别人做)，再等待收到别人的通知再做...
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                i++;
                lock.notifyAll();
                lock.wait();
             }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        
        for (int i = 0; i < n;) {
             synchronized(lock){//先等待收到别人的通知再做，再发通知(通知别人做)；再等待收到...
                 lock.wait();
                 // printBar.run() outputs "bar". Do not change or remove this line.
                 printBar.run();
                  i++;
                 lock.notifyAll();
             }

        }
    }

}
