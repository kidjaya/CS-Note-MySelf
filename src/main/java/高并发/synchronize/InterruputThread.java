package 高并发.synchronize;

import java.util.concurrent.TimeUnit;

public class InterruputThread {
    public static void main(String[] args){
        Thread t1=new Thread(){

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
        t1.start();
        t1.interrupt();


        /**
         * 输出结果:
            线程中断
            已跳出循环,线程中断!
         */
    }
}