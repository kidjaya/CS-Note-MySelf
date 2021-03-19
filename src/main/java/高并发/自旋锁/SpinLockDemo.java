package 高并发.自旋锁;
 
import java.util.concurrent.atomic.AtomicReference;
 
/**
 * 本实例演示下线程的自旋锁的实现
 */
public class SpinLockDemo {
    private static AtomicReference<Thread> atomicReference=new AtomicReference<>();
    public void lock(){
        Thread thread=Thread.currentThread();
        while(!atomicReference.compareAndSet(null,thread)){
            System.out.println(thread.getName()+"自旋锁获取失败，重新获取中");
        }
    }
    public void unlock(){
        Thread thread=Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
    }
 
    public static void main(String[] args) {
        SpinLockDemo spinLockDemo=new SpinLockDemo();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"尝试获取自旋锁");
            spinLockDemo.lock();
            System.out.println(Thread.currentThread().getName()+"获取自旋锁成功");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unlock();
            System.out.println(Thread.currentThread().getName()+"释放自旋锁");
        },"test2").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"尝试获取自旋锁");
            spinLockDemo.lock();
            System.out.println(Thread.currentThread().getName()+"获取自旋锁成功");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unlock();
            System.out.println(Thread.currentThread().getName()+"释放自旋锁");
        },"test1").start();
 

    }
}