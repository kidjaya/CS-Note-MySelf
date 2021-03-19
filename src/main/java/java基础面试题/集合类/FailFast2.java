package java基础面试题.集合类;

import java.util.*;

/**
 * @author kidjaya
 */
public class FailFast2 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0 ; i < 10 ; i ++ ) {
            map.put(i+"", i+"");
        }
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            if (i == 3) {
                map.remove(3+"");
            }
            Map.Entry<String, String> entry = it.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
            i++;
        }
    }
}
