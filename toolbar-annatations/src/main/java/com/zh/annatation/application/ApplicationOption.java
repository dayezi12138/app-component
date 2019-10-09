package com.zh.annatation.application;

import com.zh.applicationOption.OptionInApplicationService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author : dayezi
 * data :2019/8/2
 * description:
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface ApplicationOption {
    Class<? extends OptionInApplicationService> option();
}
