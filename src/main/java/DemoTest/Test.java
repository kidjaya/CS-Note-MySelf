package DemoTest;

import java基础面试题.basic.TestInterfacec.A;
import org.omg.CORBA_2_3.portable.InputStream;

import java.util.HashMap;
import java.util.List;

/**
 * @author kidjaya
 */
public class Test {
    public static void main(String[] args) {
        Integer[] a = {1,2,3};
        String[] b = {"aaa","bbb","ccc"};
        printArray(a);
        printArray(b);
    }

    public static <E> void printArray(E[] array){
        for(E item : array){
            System.out.printf("%s ",item);
            System.out.println();
        }
    }


}



