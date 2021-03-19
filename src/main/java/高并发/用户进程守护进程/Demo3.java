package 高并发.用户进程守护进程;

import java.util.concurrent.TimeUnit;

public class Demo3 {

    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();
        t1.setDaemon(true);
    }
}