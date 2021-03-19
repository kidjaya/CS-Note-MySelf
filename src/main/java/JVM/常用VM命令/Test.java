package JVM.常用VM命令;

public class Test {


    public static void main(String[] args) {
        new A();
        new B();
    }
}

class A{
    private String a;

    private B b = new B();
}

class B{
    private String b;

    private A a = new A();
}
