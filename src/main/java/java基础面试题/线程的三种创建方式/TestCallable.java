package java基础面试题.线程的三种创建方式;

import java.util.concurrent.*;

/**
 * @author kidjaya
 */
public class TestCallable {
    public static void main(String[] args) {
        try {
            testCallable();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static void testCallable() throws ExecutionException, InterruptedException, TimeoutException {
        Callable callable = new MyCallable();
        FutureTask task = new FutureTask(callable);
        new Thread(task).start();
        System.out.println(task.get());
        //等待线程执行结束
        Thread.sleep(10);
        System.out.println(task.get(100L, TimeUnit.MILLISECONDS));
    }
}

class MyCallable implements Callable {

    public Object call() throws Exception {
        System.out.println("通过实现Callable，线程号:" + Thread.currentThread().getName());
        return 10;
    }
}

