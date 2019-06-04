package core.app.zh.com.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @auther create by Administrator
 * DATE:2019/3/27 0027 08
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NeedPermission {

    String[] value() default {};

    boolean next() default true;//权限未赋予时，是否继续
}
