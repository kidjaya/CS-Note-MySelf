package java基础面试题.basic.注解;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

public class TheClass {

  @MyAnnotation(name = "someName", age = 20, score = {1,2,3})
  public String myField = null;

  public static void main(String[] args) {
    TheClass c = new TheClass();
    Class clazz = c.getClass();
    try {
      Field field = clazz.getDeclaredField("myField");
      Annotation[] annotations = field.getAnnotations();
      for (Annotation annotation : annotations) {
        if (annotation instanceof MyAnnotation){
          MyAnnotation myAnnotation = (MyAnnotation)  annotation;
          System.out.println(myAnnotation.age());
          System.out.println(myAnnotation.name());
          System.out.println(Arrays.toString(myAnnotation.score()));
        }
      }
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    }
  }
}