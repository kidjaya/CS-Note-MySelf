package java基础面试题.basic.注解;

import java.lang.annotation.Repeatable;

/**
 * @author kidjaya
 */
@Repeatable(Schedules.class)
public @interface Schedule {
    public String time() default "morning";
}
