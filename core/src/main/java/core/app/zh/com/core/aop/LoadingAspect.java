package core.app.zh.com.core.aop;

import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import core.app.zh.com.core.annotation.Loading;
import core.app.zh.com.core.listener.LayoutInitListener;
import core.app.zh.com.core.listener.LoadingOptionListener;

/**
 * author : dayezi
 * data :2019/7/23
 * description:
 */
@Aspect
public class LoadingAspect {

    @Pointcut("execution(@core.app.zh.com.core.annotation.Loading  * *(..))")
    public void pointcutLoading() {
    }

    @Pointcut("execution(@core.app.zh.com.core.annotation.Loading  * *(..))")
    public void pointcutLoadingHide() {
    }

    @After("pointcutLoading()")
    public void afterLoading(JoinPoint joinPoint) {
        if (joinPoint.getThis() instanceof LayoutInitListener && joinPoint.getThis() instanceof LoadingOptionListener) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Loading loading = signature.getMethod().getAnnotation(Loading.class);
            if (loading.value() != LoadingOptionListener.LoadingOperation.INIT_VIEW) return;
            LayoutInitListener layoutInitListener = (LayoutInitListener) joinPoint.getThis();
            LoadingOptionListener loadingOptionListener = (LoadingOptionListener) joinPoint.getThis();
            if (layoutInitListener.myContentView() != null && loadingOptionListener.getLoadingView() != null) {
                ViewGroup viewGroup = layoutInitListener.myContentView();
                viewGroup.addView(loadingOptionListener.getLoadingView());
            }
        }
    }

    @Before("pointcutLoadingHide()")
    public void showLoading(JoinPoint joinPoint) {
        LogUtils.e("test_show");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Loading loading = signature.getMethod().getAnnotation(Loading.class);
        LogUtils.e(loading.value());
        if (loading.value().equals(LoadingOptionListener.LoadingOperation.INIT_VIEW)) return;
        if (joinPoint.getThis() instanceof LayoutInitListener && joinPoint.getThis() instanceof LoadingOptionListener) {
            LogUtils.e("test_true");
            LayoutInitListener layoutInitListener = (LayoutInitListener) joinPoint.getThis();
            LoadingOptionListener loadingOptionListener = (LoadingOptionListener) joinPoint.getThis();
            if (layoutInitListener.myContentView() != null && loadingOptionListener.getLoadingView() != null) {
                switch (loading.value()) {
                    case HIDE:
                        loadingOptionListener.hideLoading();
                        break;
                    case SHOW:
                        loadingOptionListener.showLoading();
                        break;
                    case ERROR:
                        loadingOptionListener.showError();
                        break;
                    case NO_NET_WORK_ERROR:
                        loadingOptionListener.showNoNetWork();
                        break;
                    case EMPTY:
                        loadingOptionListener.showEmptyView();
                        break;
                }
            } else LogUtils.e("test_s_true_1");
        } else LogUtils.e("test_s_true_2");

    }
}
