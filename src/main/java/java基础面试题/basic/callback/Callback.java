package java基础面试题.basic.callback;

/**
 * 回调是指A调用B做事，B做完告诉A，A期间可以做别的事
 * 有一个方法B做完题目后告诉A时使用的方法
 * 提供这个接口让B回调
 * @author kidjaya
 */
public interface Callback {
    void tellAnswer(int answer);
}
