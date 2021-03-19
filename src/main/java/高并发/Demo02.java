package 高并发;

import java.util.concurrent.TimeUnit;

public class Demo02 {
    static volatile boolean isStop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (isStop) {
                        System.out.println("我要退出了!");
                        break;
                    }
                }
            }
        };
        thread1.setName("thread1");
        thread1.start();
        TimeUnit.SECONDS.sleep(1);
        isStop = true;
    }
}
