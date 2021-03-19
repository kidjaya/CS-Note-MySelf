package 高并发;

import java.util.concurrent.TimeUnit;

public class Demo06 {


    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    //休眠100秒
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        this.interrupt();
                        e.printStackTrace();
                    }
                    if (this.isInterrupted()) {
                        System.out.println("我要退出了!");
                        break;
                    }
                }
            }
        };
        thread1.setName("thread1");
        thread1.start();
        TimeUnit.SECONDS.sleep(1);
        thread1.interrupt();
    }
}
