package java基础面试题.basic.注解;

/**
 * @author kidjaya
 */
public class Test {
    public static void main(String[] args) {
        //1表示负数
        System.out.println((1&1)>0);
    }
    @Schedule
    @Schedule(time = "afternoon")
    void test(){

    }
}
