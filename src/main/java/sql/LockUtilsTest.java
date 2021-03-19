package sql;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


import static sql.LockUtils.lock;
import static sql.LockUtils.unlock;

/**
 * 工作10年的前阿里P7分享Java、算法、数据库方面的技术干货！坚信用技术改变命运，让家人过上更体面的生活！
 * 喜欢的请关注公众号：路人甲Java
 */
@Slf4j
public class LockUtilsTest {

    //测试重复获取和重复释放
    @Test
    public void test1() throws Exception {
        String lock_key = "key1";
        for (int i = 0; i < 10; i++) {
            lock(lock_key, 10000L, 1000);
        }
        for (int i = 0; i < 10; i++) {
            unlock(lock_key);
        }
    }

    //获取之后不释放，超时之后被thread1获取
    @Test
    public void test2() throws Exception {
        String lock_key = "key2";
        boolean res = lock(lock_key, 5000L, 1000);

        if (res){
            System.out.println("上锁成功");
        }

        Thread thread1 = new Thread(() -> {
            try {
                try {
                    boolean res1 = lock(lock_key, 5000L, 7000);
                    if (res1){
                        System.out.println("上锁成功");
                    }
                } finally {
                    unlock(lock_key);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread1.setName("thread1");
        thread1.start();
        thread1.join();
    }
}