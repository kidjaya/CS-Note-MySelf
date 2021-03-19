package java基础面试题.Integer;

/**
 * @author kidjaya
 */
public class Tes {
    public static void main(String[] args) {
        Integer a = new Integer(1);
        Integer b = a;
        Integer c = new Integer(1);
        System.out.println(a.equals(c));
    }
}
