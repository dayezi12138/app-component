package core.app.zh.com.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import core.app.zh.com.core.listener.LoadingOptionListener;

/**
 * author : dayezi
 * data :2019/12/19
 * description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Loading {
    LoadingOptionListener.LoadingOperation value();
}
