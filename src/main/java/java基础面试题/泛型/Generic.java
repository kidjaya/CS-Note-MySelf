package java基础面试题.泛型;

import com.sun.tools.javac.util.Log;

import java.util.logging.Logger;

/**
 * @author kidjaya
 */
public class Generic<T>{
    //此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型
    //在实例化泛型类时，必须指定T的具体类型
    //key这个成员变量的类型为T,T的类型由外部指定
    private T key;

    public Generic(T key) { //泛型构造方法形参key的类型也为T，T的类型由外部指定
        this.key = key;
    }

    public T getKey(){ //泛型方法getKey的返回值类型为T，T的类型由外部指定
        return key;
    }

    public static void showKeyValue(Generic<Number> obj){
        System.out.println("泛型测试"+"key value is " + obj.getKey());
    }

    public static void main(String[] args) {
        Generic<Integer> gInteger = new Generic<Integer>(123);
        Generic<Number> gNumber = new Generic<Number>(456);



    }
}