package core.app.zh.com.core.aop;


import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zh.api.loading.LoadingInJect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import core.app.zh.com.core.annotation.NeedPermission;
import core.app.zh.com.core.listener.GetActivityListener;
import core.app.zh.com.core.listener.LoadingListener;
import core.app.zh.com.core.utils.LoadingDialogUtils;
import io.reactivex.functions.Consumer;

/**
 * @auther create by Administrator
 * DATE:2019/3/27 0027 08
 */
@Aspect
public class MyAspect {
    private final static String TAG = MyAspect.class.getSimpleName();

    @Pointcut("execution(@core.app.zh.com.core.annotation.NeedPermission  * *(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public void AroundJoinPoint(final ProceedingJoinPoint joinPoint) {
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

    //    @Pointcut("execution (* zh.com.jyu.business..*.*(..))")
//    public void onCreatePointCut() {
//    }
//
//    @After("onCreatePointCut()")
//    public void onCreateAfter() {
//    }

    @Pointcut("execution(@core.app.zh.com.core.annotation.LoadingShow  * *(..))")
    public void pointcutLoadingShow() {
    }

    @Pointcut("execution(@core.app.zh.com.core.annotation.LoadingHide  * *(..))")
    public void pointcutLoadingHide() {
    }

    @Before("pointcutLoadingShow()")
    public void BeforePoint(JoinPoint joinPoint) {
        boolean valid = LoadingInJect.valided(joinPoint.getThis());
        if (!valid) return;
        if (joinPoint.getThis() instanceof LoadingListener && joinPoint.getThis() instanceof GetActivityListener) {
            GetActivityListener activity = (GetActivityListener) joinPoint.getThis();
            activity.getMyActivity().getContentView().setVisibility(View.GONE);
            LoadingListener loadingListener = (LoadingListener) joinPoint.getThis();
            loadingListener.showLoading();
        } else if (joinPoint.getThis() instanceof GetActivityListener) {
            GetActivityListener activity = (GetActivityListener) joinPoint.getThis();
            Dialog dialog = LoadingDialogUtils.getDialog((Context) activity);
            dialog.show();
        }
    }

    @After("pointcutLoadingHide()")
    public void afterPoint(JoinPoint joinPoint) {
        if (joinPoint.getThis() instanceof LoadingListener && joinPoint.getThis() instanceof GetActivityListener) {
            LoadingListener loadingListener = (LoadingListener) joinPoint.getThis();
            if (!loadingListener.handlerContentView()) {
                GetActivityListener activity = (GetActivityListener) joinPoint.getThis();
                activity.getMyActivity().getContentView().setVisibility(View.VISIBLE);
            }
            loadingListener.hideLoading();
        } else if (joinPoint.getThis() instanceof GetActivityListener) {
            GetActivityListener activity = (GetActivityListener) joinPoint.getThis();
            Dialog dialog = LoadingDialogUtils.getDialog((Context) activity);
            dialog.hide();
        }
    }
}
