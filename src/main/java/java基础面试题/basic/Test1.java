package java基础面试题.basic;


public class Test1 {
    public static void main(String[] args) {
        new C();
    }
}

class C{
    C(){
        System.out.println("构造方法调用");
    }
    {
        System.out.println("代码块调用");
    }
    static {
        System.out.println("静态代码块调用");
    }
}


