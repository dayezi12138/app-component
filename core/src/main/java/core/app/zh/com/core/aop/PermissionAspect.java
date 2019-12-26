package core.app.zh.com.core.aop;


import com.blankj.utilcode.util.LogUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import core.app.zh.com.core.annotation.NeedPermission;
import core.app.zh.com.core.listener.GetActivityListener;
import io.reactivex.functions.Consumer;

/**
 * @auther create by Administrator
 * DATE:2019/3/27 0027 08
 */
@Aspect
public class PermissionAspect {
    private final static String TAG = PermissionAspect.class.getSimpleName();

    @Pointcut("execution(@core.app.zh.com.core.annotation.NeedPermission  * *(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public void AroundJoinPoint(final ProceedingJoinPoint joinPoint) {
        LogUtils.e("===执行===");
        if (joinPoint.getThis() instanceof GetActivityListener) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            NeedPermission permission = signature.getMethod().getAnnotation(NeedPermission.class);
            if (permission != null && permission.value().length != 0) {
                GetActivityListener activity = (GetActivityListener) joinPoint.getThis();
                RxPermissions rxPermissions = new RxPermissions(activity.getMyActivity());
                rxPermissions.request(permission.value()).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        boolean next = aBoolean || (!aBoolean && permission.next());
                        if (next) {
                            try {
                                joinPoint.proceed();
                            } catch (Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        }
                    }

                });
            }
        }
    }
}
