package 高并发.Condition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阻塞队列实现生产者消费者模式~
 */
public class test {
    public static void main(String[] args) {
        BlockQueue<Integer> blockQueue = new BlockQueue<>(2);
        for (int i = 0; i < 10; i++) {
            int data = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        blockQueue.enQueue(data);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Integer data = blockQueue.Dequeue();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}

class BlockQueue<T>{
    int size;

    public BlockQueue() {
        this(2);
    }

    public BlockQueue(int size) {
        this.size = size;
    }

    ReentrantLock lock = new ReentrantLock();
    LinkedList<T> list = new LinkedList<>();

    Condition notFull = lock.newCondition();
    Condition notEmpty = lock.newCondition();

    public void enQueue(T e) throws InterruptedException{
        lock.lock();
        try {
            while (list.size() == size){
                notFull.await();
            }
            //加入队尾
            list.add(e);
            System.out.println("入队"+e);
            //通知出队
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    public T Dequeue() throws InterruptedException{
        T t;
        lock.lock();
        try {
            while (list.size() == 0){
                notEmpty.await();
            }
            //出队:移除链表首元素
            t = list.removeFirst();
            System.out.println("出队"+t);
            notFull.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }

}
