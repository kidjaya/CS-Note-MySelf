package Array;

import java.util.Arrays;
import java.util.Comparator;

public class A {
    public static void main(String[] args) {
        int[] a = {5,1,4,6,3};
        Arrays.sort(a);
        for (Integer integer : a) {
            System.out.println(integer);
        }
        System.out.println("6");
    }
}
