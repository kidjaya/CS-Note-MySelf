package 高并发.Unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Demo8 {

  static Unsafe unsafe;

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

  static class C1 {
    private static int count;

    static {
      count = 10;
      System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "，C1 static init.");
    }
  }

  static class C2 {
    private static int count;

    static {
      count = 11;
      System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "，C2 static init.");
    }
  }

  public static void main(String[] args) throws NoSuchFieldException {
    //判断C1类是需要需要初始化，如果已经初始化了，会返回false，如果此类没有被初始化过，返回true
    if (unsafe.shouldBeInitialized(C1.class)) {
      System.out.println("C1需要进行初始化");
      //对C1进行初始化
      unsafe.ensureClassInitialized(C1.class);
    }

    System.out.println(C2.count);
    System.out.println(unsafe.shouldBeInitialized(C1.class));
  }
}