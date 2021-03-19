package 高并发.synchronize;

import java.util.concurrent.TimeUnit;

public class Demo1 {

    public volatile static boolean exit = false;

    public static class T extends Thread {
        @Override
        public void run() {
            while (true) {
                //循环处理业务
                if (exit) {
                    break;
                }
            }
        }
    }

    public static void setExit() {
        exit = true;
    }

    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.start();
        TimeUnit.SECONDS.sleep(3);
        setExit();
    }
}