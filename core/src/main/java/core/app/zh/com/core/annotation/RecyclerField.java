package core.app.zh.com.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author : dayezi
 * data :2019/7/8
 * description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RecyclerField {
//    Class<? extends LayoutManagerListener> layoutManagerProvider();

//    boolean isView();
}