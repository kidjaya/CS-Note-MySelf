package JVM.堆;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kidjaya
 */
public class OOMTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        while(true) {
            list.add(999999999);
        }
    }
}