package 高并发.CyclicBarrier;

import java.sql.Time;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author kidjaya
 */
public class Demo06 {
    public static CyclicBarrier barrier = new CyclicBarrier(10);

    //重建规则
    public static boolean rule = false;

    public static class T extends Thread{
        int sleep;
        public T(String name,int sleep){
            super(name);
            this.sleep = sleep;
        }
        @Override
        public void run() {
            long startTime=0,endTime=0;
            try{
                TimeUnit.SECONDS.sleep(sleep);
                startTime = System.currentTimeMillis();
                System.out.println(this.getName()+"到了");
                if (!rule){
                    if (this.getName().equals("员工1")){
                        barrier.await(5,TimeUnit.SECONDS);
                    }else{
                        barrier.await();
                    }
                }else{
                    barrier.await();
                }
            } catch (BrokenBarrierException | InterruptedException | TimeoutException e){
                e.printStackTrace();
            }
            endTime = System.currentTimeMillis();
            System.out.println(this.getName()+",sleep:"+this.sleep+"，等待了"+(endTime-startTime)+"(ms)");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            T t= new T("员工"+i,i);
            t.start();
        }

        //rebuild
        TimeUnit.SECONDS.sleep(15);
        barrier.reset();
        rule=true;
        System.out.println("=====大家都别皮了======");

        for (int i = 1; i <= 10; i++) {
            T t= new T("员工"+i,i);
            t.start();
        }
    }
}