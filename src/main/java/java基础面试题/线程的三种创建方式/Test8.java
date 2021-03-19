package java基础面试题.线程的三种创建方式;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author kidjaya
 */
public class Test8 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for(int i=0;i<5;i++){
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName()+"执行中");
            });
        }
        System.out.println("线程任务开始执行");
        executorService.shutdown();
    }
}