package Test;

import java.util.Arrays;

/**
 * @author kidjaya
 */
public class Test2 {
    public static void main(String[] args) {
//        Test2 a = new Test2();
//        //普通的重载一般就是同名方法不同参数。
//        //这里我们来讨论当同名方法只有一个参数时的情况。
//        //此时会调用char参数的方法。
//        //当没有char参数的方法。会调用int类型的方法，如果没有int就调用long
//        //即存在一个调用顺序char -> int -> long ->double -> ..。
//        //当没有基本类型对应的方法时，先自动装箱，调用包装类方法。
//        //如果没有包装类方法，则调用包装类实现的接口的方法。
//        //最后再调用持有多个参数的char...方法。
//        a.eat('a');
//        a.eat('a','c','b');

//        System.out.println(firstUniqChar("leetcode"));

        System.out.println('l'-'a');
    }
    public void eat(short i) {
        System.out.println("short");
    }
    public void eat(int i) {
        System.out.println("int");
    }
    public void eat(double i) {
        System.out.println("double");
    }
    public void eat(long i) {
        System.out.println("long");
    }
    public void eat(Character c) {
        System.out.println("Character");
    }
    public void eat(Comparable c) {
        System.out.println("Comparable");
    }
    public void eat(char ... c) {
        System.out.println(Arrays.toString(c));
        System.out.println("...");

        String s = Integer.toString(123);
        char[] chars = s.toCharArray();
        Integer integer = (int) chars[0];
    }

    public void eat(char i) {
        System.out.println("char");
    }

    public static int firstUniqChar(String s) {
        int[] count = new int[128];
        for(char c : s.toCharArray()){
            int temp = count[c - 'a'];
            count[c - 'a'] = temp + 1;
        }

        for(int i = 0;i<count.length;i++){
            if(count[i] == 1) return i;
        }

        return -1;
    }
}
