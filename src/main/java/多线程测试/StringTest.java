package 多线程测试;

public class StringTest {
    public static void main(String[] args) {
        String a = new String("A");
        String b = "B";
        String c = "A";
        String d = new String("B");
        String e = "A";
        String f = "B";

        System.out.println(a + b == c + b);
        System.out.println(a + b);
        System.out.println(c + b);
    }
}
