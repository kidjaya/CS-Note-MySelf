package 多线程测试;

/**
 * @author kidjaya
 */
public class dealThread {
    private static Object A = new Object();
    private static Object B = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (A){
                System.out.println("get A");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("waiting B");
                synchronized (B){
                    System.out.println("get B");
                }
            }
        },"线程1").start();

        new Thread(()->{
            synchronized (B){
                System.out.println("get B");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("waiting A");
                synchronized (A){
                    System.out.println("get A");
                }
            }
        },"线程2").start();
    }

}
