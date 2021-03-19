package 高并发.Unsafe;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Demo7 {

    static Unsafe unsafe;
    //静态属性
    private static Object v1 = 1;
    //实例属性
    private Object v2;

    static {
        //获取Unsafe对象
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws NoSuchFieldException {
        Field v1Field = Demo7.class.getDeclaredField("v1");
        Field v2Field = Demo7.class.getDeclaredField("v2");

        System.out.println(unsafe.staticFieldOffset(v1Field));
        System.out.println(unsafe.objectFieldOffset(v2Field));

        System.out.println(unsafe.getInt(v1,unsafe.staticFieldOffset(v1Field)));

    }
}