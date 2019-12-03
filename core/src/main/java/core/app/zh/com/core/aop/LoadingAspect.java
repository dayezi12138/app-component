package core.app.zh.com.core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * author : dayezi
 * data :2019/7/23
 * description:
 */
@Aspect
public class LoadingAspect {

    @Pointcut("execution(@core.app.zh.com.core.annotation.LoadingShow  * *(..))")
    public void pointcutLoadingShow() {
    }

    @Pointcut("execution(@core.app.zh.com.core.annotation.LoadingHide  * *(..))")
    public void pointcutLoadingHide() {
    }

    @Before("pointcutLoadingShow()")
    public void BeforeShowPoint(JoinPoint joinPoint) {
//        if (joinPoint.getThis() instanceof LayoutInitListener) {
//            LayoutInitListener layoutInitListener = (LayoutInitListener) joinPoint.getThis();
//            layoutInitListener.multipleStatusView().showLoading();
//        }
//        if (joinPoint.getThis() instanceof BaseActivity) {
//            BaseActivity activity = (BaseActivity) joinPoint.getThis();
//            LoadingProvider provider = activity.getProvider();
//            provider.showLoading();
//        }
//        boolean valid = LoadingInJect.valided(joinPoint.getThis());
//        if (!valid) return;
//        if (joinPoint.getThis() instanceof StatusViewListener && joinPoint.getThis() instanceof LayoutInitListener) {
//            LayoutInitListener activity = (LayoutInitListener) joinPoint.getThis();
//            activity.myContentView().setVisibility(View.GONE);
//            StatusViewListener loadingListener = (StatusViewListener) joinPoint.getThis();
//            loadingListener.showLoading();
//        } else if (joinPoint.getThis() instanceof GetActivityListener) {
//            GetActivityListener activity = (GetActivityListener) joinPoint.getThis();
//            Dialog dialog = LoadingDialogUtils.getDialog((Context) activity);
//            dialog.show();
//        }
    }

    @Before("pointcutLoadingHide()")
    public void BeforeHidePoint(JoinPoint joinPoint) {
//        if (joinPoint.getThis() instanceof LayoutInitListener) {
//            LayoutInitListener layoutInitListener = (LayoutInitListener) joinPoint.getThis();
//            layoutInitListener.multipleStatusView().showContent();
//        }
//        if (joinPoint.getThis() instanceof BaseActivity) {
//            BaseActivity activity = (BaseActivity) joinPoint.getThis();
//            LoadingProvider provider = activity.getProvider();
//            provider.hideLoading();
//        }
    }
//
//    @After("pointcutLoadingHide()")
//    public void afterPoint(JoinPoint joinPoint) {
//        boolean valid = LoadingInJect.valided(joinPoint.getThis());
//        if (!valid) return;
//        if (joinPoint.getThis() instanceof StatusViewListener && joinPoint.getThis() instanceof LayoutInitListener) {
//            StatusViewListener loadingListener = (StatusViewListener) joinPoint.getThis();
//            if (!loadingListener.handlerContentView()) {
//                LayoutInitListener activity = (LayoutInitListener) joinPoint.getThis();
//                activity.myContentView().setVisibility(View.VISIBLE);
//            }
//            loadingListener.hideLoading();
//        } else if (joinPoint.getThis() instanceof GetActivityListener) {
//            GetActivityListener activity = (GetActivityListener) joinPoint.getThis();
//            Dialog dialog = LoadingDialogUtils.getDialog((Context) activity);
//            dialog.hide();
//        }
//    }
}
