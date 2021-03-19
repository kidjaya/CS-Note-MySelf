package java基础面试题.basic.PoductorAndComsumer;

public class Test1 {
    public volatile static Object o = new Object();
    public volatile static int flag = 0;

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (o){
                while(true){
                    if (flag == 0){
                        try{
                            Thread.sleep(2000);
                            System.out.println("等待消费");
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("开始生产");
                    System.out.println("通知消费");
                    flag = 0;
                    o.notify();
                }
            }
        }).start();
        new Thread(()->{
            synchronized (o){
                while(true){
                    if (flag == 1){
                        try{
                            Thread.sleep(2000);
                            System.out.println("等待生产");
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("开始消费");
                    System.out.println("通知生产");
                    flag = 1;
                    o.notify();
                }
            }
        }).start();

    }
}
