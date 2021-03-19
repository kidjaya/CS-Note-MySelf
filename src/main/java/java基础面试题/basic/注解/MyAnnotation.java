package java基础面试题.basic.注解;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * @author kidjaya
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD,ElementType.PARAMETER})
public @interface MyAnnotation{
    String name();
    int age() default 18;
    int[] score();
}
