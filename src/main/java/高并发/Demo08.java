package 高并发;

/**
 * <b>description</b>： <br>
 * <b>time</b>：2019/7/12 17:18 <br>
 * <b>author</b>：微信公众号：路人甲Java，专注于java技术分享（带你玩转 爬虫、分布式事务、异步消息服务、任务调度、分库分表、大数据等），喜欢请关注！
 */
public class Demo08 {
    static Object object = new Object();

    public static class T1 extends Thread {
        public T1(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (object) {
                System.out.println("in " + this.getName());
                Thread.currentThread().suspend();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1("t1");
        t1.start();
        Thread.sleep(100);
        T1 t2 = new T1("t2");
        t2.start();
        t1.resume();
        t2.resume();
        t1.join();
        t2.join();
    }
}
