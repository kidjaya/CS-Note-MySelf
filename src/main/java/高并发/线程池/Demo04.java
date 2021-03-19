package 高并发.线程池;

import com.sun.jmx.snmp.tasks.ThreadService;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author kidjaya
 */
public class Demo04 {
    static AtomicInteger threadNum = new AtomicInteger(1);

    public static void main(String[] args) {
        ThreadPoolExecutor service = new ThreadPoolExecutor(5,5,60L, TimeUnit.SECONDS
        ,new ArrayBlockingQueue<Runnable>(10),r -> {
            Thread thread = new Thread(r);
            thread.setName("自定义线程"+threadNum.getAndIncrement());
            return thread;
        });
        for (int i = 0; i < 5; i++) {
            String task = "任务"+i;
            service.execute(()->{
                System.out.println(Thread.currentThread().getName()+"处理"+task);
            });
        }

        service.shutdown();
    }
}
