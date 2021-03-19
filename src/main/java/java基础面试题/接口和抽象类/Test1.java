package java基础面试题.接口和抽象类;

/**
 * @author kidjaya
 */
public interface Test1 extends Test2,Test3 {

    public void run();

    @Override
    void say();

    @Override
    void look();
}
