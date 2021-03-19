package java基础面试题.basic;

public class shapes{
    public static void main(String[] args) {
        Class<round> rclass=round.class;
        Class<? super round> sclass= rclass.getSuperclass();
//        Class<shapes> sclass=rclass.getSuperclass();
        //我们明知道，round的基类就是shapes，但是却不能直接声明 Class < shapes >，必须使用特殊语法
        // Class < ? super round >
        Class<?> obj=int.class;
        obj=double.class;
        obj=shapes.class;

        Class<? extends Number> obj2=int.class;
        obj=Number.class;
        obj=double.class;

        Class obj1=int.class;
        Class<Integer> obj3=int.class;
        obj1=double.class;
//        obj3=double.class;
// 这一行代码是非法的，obj3不能改指向别的类
    }
}
class round extends shapes{

}
