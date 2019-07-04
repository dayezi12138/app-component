package com.zh.annatation.loading;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author : dayezi
 * data :2019/7/1
 * description:
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface LoadingPoint {
}
