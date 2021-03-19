package java基础面试题.集合类;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author kidjaya
 */
public class FailFast {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0 ; i < 10 ; i++ ) {
            list.add(i + "");
        }
        Iterator<String> iterator = list.iterator();
        int i = 0 ;
        while(iterator.hasNext()) {
            if (i == 3) {
                list.remove(3);
            }
            System.out.println(iterator.next());
            i ++;
        }
    }
}
