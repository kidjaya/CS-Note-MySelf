package java基础面试题.线程的三种创建方式;

/**
 * @author kidjaya
 * 方式一：通过继承Thread类的方式，实现它的run方法
 */
public class TestThread {
    public static void main(String[] args) {
        testMyThread();
    }

    public static void testMyThread(){
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();

        t1.start();
        t2.start();
    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("通过继承Thread，线程号:"+currentThread().getName());
    }
}
