package java基础面试题.集合类;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author kidjaya
 */
public class FailFastTest {
     public static List<String> list = new ArrayList<>();
 
     private static class MyThread1 extends Thread {
           @Override
           public void run() {
                Iterator<String> iterator = list.iterator();
                while(iterator.hasNext()) {
                     String s = iterator.next();
                     System.out.println(this.getName() + ":" + s);
                     try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                super.run();
           }
     }
 
     private static class MyThread2 extends Thread {
           int i = 0;
           @Override
           public void run() {
                while (i < 10) {
                     System.out.println("thread2:" + i);
                     if (i == 2) {
                           list.remove(i);
                     }
                     try {
                         Thread.sleep(1000);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                     i ++;
                }
           }
     }
 
     public static void main(String[] args) {
           for(int i = 0 ; i < 10;i++){
            list.add(i+"");
        }
           MyThread1 thread1 = new MyThread1();
           MyThread2 thread2 = new MyThread2();
           thread1.setName("thread1");
           thread2.setName("thread2");
           thread1.start();
           thread2.start();
     }
}