package 高并发.CallableFuture;

import java.util.concurrent.*;

public class Demo9 {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    FutureTask<Integer> futureTask = new FutureTask<Integer>(()->{
      System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName()+",start!");
      TimeUnit.SECONDS.sleep(5);
      System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName()+",end!");
      return 10;
    });
    System.out.println(System.currentTimeMillis()+","+Thread.currentThread().getName());
    new Thread(futureTask).start();
    System.out.println(System.currentTimeMillis()+","+Thread.currentThread().getName());
    System.out.println(System.currentTimeMillis()+","+Thread.currentThread().getName()+",结果:"+futureTask.get());
  }
}

