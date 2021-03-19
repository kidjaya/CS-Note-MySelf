package java基础面试题.线程的三种创建方式;

/**
 * @author kidjaya
 */
public class TestRunnable {
    public static void main(String[] args) {
        testRunnable();
    }

    public static void testRunnable(){
        MyRunnable r1 = new MyRunnable();
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r1,"my thread 2");
        t1.start();
        t2.start();

    }
}

class MyRunnable implements Runnable{

    public void run() {
        System.out.println("通过实现Runnable，线程号:" + Thread.currentThread().getName());
    }
}
