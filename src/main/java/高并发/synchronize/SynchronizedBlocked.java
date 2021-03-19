package 高并发.synchronize;

import java.util.concurrent.TimeUnit;

public class SynchronizedBlocked implements Runnable{

    public synchronized void f() {
        System.out.println("Trying to call f()");
        while(true) // Never releases lock
        {
            /*
            Thread.yield() 方法，使当前线程由执行状态，变成为就绪状态，让出cpu时间，
            在下一个线程执行时候，此线程有可能被执行，也有可能没有被执行
             */
            /*
            sleep 和 yield 都不会释放 <锁资源>
             */
            Thread.yield();
        }
    }

    /**
     * 在构造器中创建新线程并启动获取对象锁
     */
    public SynchronizedBlocked() {
        //该线程已持有当前实例锁
        new Thread() {
            @Override
            public void run() {
                f(); // Lock acquired by this thread
            }
        }.start();
    }
    @Override
    public void run() {
        //中断判断
        while (true) {
            if (Thread.interrupted()) {
                System.out.println("中断线程!!");
                break;
            } else {
                System.out.println(1);
                f();
                System.out.println(2);
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        SynchronizedBlocked sync = new SynchronizedBlocked();
        Thread t = new Thread(sync);
        //启动后调用f()方法,无法获取当前实例锁处于等待状态
        t.start();
        TimeUnit.SECONDS.sleep(1);
        //中断线程,无法生效
        t.interrupt();
    }
}