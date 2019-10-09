package core.app.zh.com.core.aop;

import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import core.app.zh.com.core.annotation.ObservableState;
import core.app.zh.com.core.enu.ObservableEnum;
import core.app.zh.com.core.listener.OptionObservableInterceptorListener;

/**
 * author : dayezi
 * data :2019/7/23
 * description:
 */
@Aspect
public class ObservableAspect {

    @Pointcut("execution(@core.app.zh.com.core.annotation.ObservableState  * *(..))")
    public void pointObservable() {
    }

    @Around("pointObservable()")
    public void before(ProceedingJoinPoint joinPoint) {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            ObservableState observableState = signature.getMethod().getAnnotation(ObservableState.class);
            if (observableState != null && observableState.state() == ObservableEnum.SUCCESS && joinPoint.getThis() instanceof OptionObservableInterceptorListener) {
                OptionObservableInterceptorListener interceptorListener = (OptionObservableInterceptorListener) joinPoint.getThis();
                boolean result = interceptorListener.observableInterceptor() == null || !interceptorListener.observableInterceptor().handleInterceptor(joinPoint.getArgs()[0]);
                if (result) joinPoint.proceed();
            }
        } catch (Throwable throwable) {
            ToastUtils.showShort(throwable.getMessage());
        }

    }

    @After("pointObservable()")
    public void after(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        ObservableState observableState = signature.getMethod().getAnnotation(ObservableState.class);
        if (observableState != null && observableState.state() == ObservableEnum.FAIL) {
            Throwable ex = (Throwable) joinPoint.getArgs()[0];
            ToastUtils.showShort(TextUtils.isEmpty(ex.getMessage()) ? "未知错误" : ex.getMessage());
        }
    }
}
