package java基础面试题.basic.Enum;

import java.util.EnumMap;
import java.util.EnumSet;

public class TestEnum {
    public static void main(String[] args) {
        System.out.println(Food.JapaneseFood.rice);
        System.out.println(ChineseFood.jiaozi.ordinal());
    }
}

interface Food{
    enum JapaneseFood{
        rice,fish
    }
}
enum ChineseFood{
    noddles,jiaozi
}

