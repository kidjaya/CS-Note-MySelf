package JVM.虚拟机栈;

/**
 * @author kidjaya
 */
public class StackErrorTest {
    private static int count = 1;
    public static void main(String[] args) {
        System.out.println(count++);
        main(args);
    }
}