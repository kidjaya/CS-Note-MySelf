package 高并发;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Demo01 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                System.out.println();
                System.out.println("start");
                boolean flag = true;
                while (flag) {
                    ;
                }
                System.out.println("end");
            }
        };
        thread1.setName("thread1");
        thread1.start();
        //当前线程休眠1秒
        TimeUnit.SECONDS.sleep(1);
        //关闭线程thread1
        thread1.stop();
        //输出线程thread1的状态
        System.out.println("{}"+thread1.getState());
        //当前线程休眠1秒
        TimeUnit.SECONDS.sleep(1);
        //输出线程thread1的状态
        System.out.println("{}"+thread1.getState());
    }
}