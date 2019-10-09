package core.app.zh.com.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import core.app.zh.com.core.enu.ObservableEnum;

/**
 * author : dayezi
 * data :2019/7/23
 * description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ObservableState {

    ObservableEnum state();
}
