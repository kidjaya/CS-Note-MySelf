package 高并发.LockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class Demo10 {

    static class BlockerDemo {
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
        });
        t1.setName("t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            LockSupport.park(new BlockerDemo());
        });
        t2.setName("t2");
        t2.start();
    }
}