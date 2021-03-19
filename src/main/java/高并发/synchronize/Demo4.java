package 高并发.synchronize;

import java.util.concurrent.TimeUnit;

public class Demo4 {
    static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println(this.getName() + " start");
                while (true) {
                    synchronized (this) {
                        if (flag) {
                            break;
                        }
                    }
                }
                System.out.println(this.getName() + " exit");
            }
        };
        t1.setName("t1");
        t1.start();
        Thread t2 = new Thread() {
            @Override
            public void run() {
                System.out.println(this.getName() + " start");
                synchronized (this) {
                    while (true) {
                        if (flag) {
                            break;
                        }
                    }
                }
                System.out.println(this.getName() + " exit");
            }
        };
        t2.setName("t2");
        t2.start();
        TimeUnit.SECONDS.sleep(2);
        flag = true;
    }
}