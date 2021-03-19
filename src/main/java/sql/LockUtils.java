package sql;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * 工作10年的前阿里P7分享Java、算法、数据库方面的技术干货！坚信用技术改变命运，让家人过上更体面的生活！
 * 喜欢的请关注公众号：路人甲Java
 */
@Slf4j
public class LockUtils {

    //将requestid保存在该变量中
    //ThreadLocal用来存放某个线程的变量
    static ThreadLocal<String> requestIdTL = new ThreadLocal<>();

    /**
     * 获取当前线程requestid
     *
     * @return
     */
    public static String getRequestId() {
        //获取线程requestID
        String requestId = requestIdTL.get();
        if (requestId == null || "".equals(requestId)) {
            //没有的话设置默认值
            requestId = UUID.randomUUID().toString();
            requestIdTL.set(requestId);
        }
        System.out.println("requestId:{"+requestId+"}");
        return requestId;
    }

    /**
     * 获取锁
     *
     * @param lock_key         锁key
     * @param locktimeout(毫秒) 持有锁的有效时间，防止死锁
     * @param gettimeout(毫秒)  获取锁的超时时间，这个时间内获取不到将重试
     * @return
     */
    public static boolean lock(String lock_key, long locktimeout, int gettimeout) throws Exception {
        System.out.println("start");
        //加锁成功与否标志
        boolean lockResult = false;
        //当前线程的请求id
        String request_id = getRequestId();
        //获取系统当前时间
        long starttime = System.currentTimeMillis();
        //通过死循环阻塞，等待变化
        while (true) {
            //获取当前线程的 实例
            LockModel lockModel = LockUtils.get(lock_key);
            //jdk1.7引入Objects静态类操作对象,isNull判断对象是否为空
            if (Objects.isNull(lockModel)) {
                //插入一条记录,重新尝试获取锁
                LockUtils.insert(LockModel.builder().lock_key(lock_key).request_id("").lock_count(0).timeout(0L).version(0).build());
            } else {
                String reqid = lockModel.getRequest_id();
                //如果reqid为空字符，表示锁未被占用
                if ("".equals(reqid)) {
                    //将当前线程id赋予对象
                    lockModel.setRequest_id(request_id);
                    //当前上锁次数设置为1
                    lockModel.setLock_count(1);
                    //设置锁超时时间
                    lockModel.setTimeout(System.currentTimeMillis() + locktimeout);
                    if (LockUtils.update(lockModel) == 1) {
                        //加锁成功与否标志
                        lockResult = true;
                        break;
                    }
                } else if (request_id.equals(reqid)) {//重入锁
                    //如果request_id和表中request_id一样表示锁被当前线程持有者，此时需要加重入锁
                    lockModel.setTimeout(System.currentTimeMillis() + locktimeout);
                    //上锁次数增加1
                    lockModel.setLock_count(lockModel.getLock_count() + 1);
                    if (LockUtils.update(lockModel) == 1) {
                        //加锁成功标志
                        lockResult = true;
                        System.out.println("加锁成功返回了～");
                        break;
                    }
                } else {
                    //锁不是自己的，并且已经超时了，则重置锁，继续重试
                    if (lockModel.getTimeout() < System.currentTimeMillis()) {
                        System.out.println("超时了，重置别人的锁");
                        LockUtils.resetLock(lockModel);
                    } else {
                        //如果未超时，休眠100毫秒，继续重试
                        if (starttime + gettimeout > System.currentTimeMillis()) {
                            System.out.println("还没超时"+System.currentTimeMillis());
                            TimeUnit.MILLISECONDS.sleep(100);
                        } else {
                            break;
                        }
                    }
                }

            }
        }
        System.out.println("end");
        return lockResult;
    }

    /**
     * 释放锁
     *
     * @param lock_key
     * @throws Exception
     */
    public static void unlock(String lock_key) throws Exception {
        //获取当前线程requestId
        String requestId = getRequestId();
        LockModel lockModel = LockUtils.get(lock_key);
        //当前线程requestId和库中request_id一致 && lock_count>0，表示可以释放锁
        if (Objects.nonNull(lockModel) && requestId.equals(lockModel.getRequest_id()) && lockModel.getLock_count() > 0) {
            if (lockModel.getLock_count() == 1) {
                //锁重入了，所以要等到count等于1才可以重置锁
                System.out.println("锁已释放");
                resetLock(lockModel);
            } else {
                System.out.println("当前上锁次数："+lockModel.getLock_count());

                //释放每一个重入锁
                lockModel.setLock_count(lockModel.getLock_count() - 1);
                LockUtils.update(lockModel);
            }
        }
    }

    /**
     * 重置锁
     *
     * @param lockModel
     * @return
     * @throws Exception
     */
    public static int resetLock(LockModel lockModel) throws Exception {
        lockModel.setRequest_id("");
        lockModel.setLock_count(0);
        lockModel.setTimeout(0L);
        return LockUtils.update(lockModel);
    }

    /**
     * 更新lockModel信息，内部采用乐观锁来更新
     *
     * @param lockModel
     * @return
     * @throws Exception
     */
    public static int update(LockModel lockModel) throws Exception {
        return exec(conn -> {
            String sql = "UPDATE t_lock SET request_id = ?,lock_count = ?,timeout = ?,version = version + 1 WHERE lock_key = ? AND  version = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            int colIndex = 1;
            ps.setString(colIndex++, lockModel.getRequest_id());
            ps.setInt(colIndex++, lockModel.getLock_count());
            ps.setLong(colIndex++, lockModel.getTimeout());
            ps.setString(colIndex++, lockModel.getLock_key());
            ps.setInt(colIndex++, lockModel.getVersion());
            return ps.executeUpdate();
        });
    }

    public static LockModel get(String lock_key) throws Exception {
        return exec(conn -> {
            String sql = "select * from t_lock t WHERE t.lock_key=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            int colIndex = 1;
            ps.setString(colIndex++, lock_key);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return LockModel.builder().
                        lock_key(lock_key).
                        request_id(rs.getString("request_id")).
                        lock_count(rs.getInt("lock_count")).
                        timeout(rs.getLong("timeout")).
                        version(rs.getInt("version")).build();
            }
            return null;
        });
    }

    public static int insert(LockModel lockModel) throws Exception {
        return exec(conn -> {
            String sql = "insert into t_lock (lock_key, request_id, lock_count, timeout, version) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            int colIndex = 1;
            ps.setString(colIndex++, lockModel.getLock_key());
            ps.setString(colIndex++, lockModel.getRequest_id());
            ps.setInt(colIndex++, lockModel.getLock_count());
            ps.setLong(colIndex++, lockModel.getTimeout());
            ps.setInt(colIndex++, lockModel.getVersion());
            return ps.executeUpdate();
        });
    }

    public static <T> T exec(SqlExec<T> sqlExec) throws Exception {
        Connection conn = getConn();
        try {
            return sqlExec.exec(conn);
        } finally {
            closeConn(conn);
        }
    }

    @FunctionalInterface
    public interface SqlExec<T> {
        T exec(Connection conn) throws Exception;
    }

    @Getter
    @Setter
    @Builder
    public static class LockModel {
        private String lock_key;
        private String request_id;
        private Integer lock_count;
        private Long timeout;
        private Integer version;
    }

    private static final String url = "jdbc:mysql://localhost:3306/javacode2018?useSSL=false";        //数据库地址
    private static final String username = "root";        //数据库用户名
    private static final String password = "aaa791654109";        //数据库密码
    private static final String driver = "com.mysql.jdbc.Driver";        //mysql驱动

    /**
     * 连接数据库
     *
     * @return
     */
    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(driver);  //加载数据库驱动
            try {
                conn = DriverManager.getConnection(url, username, password);  //连接数据库
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭数据库链接
     *
     * @return
     */
    public static void closeConn(Connection conn) {
        if (conn != null) {
            try {
                conn.close();  //关闭数据库链接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}