package core.app.zh.com.core.aop;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tbruyelle.rxpermissions2.RxPermissions;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Field;

import core.app.zh.com.core.annotation.NeedPermission;
import core.app.zh.com.core.annotation.RecyclerParam;
import core.app.zh.com.core.base.MyBaseAdapter;
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
//        LogUtils.e("2222");
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


    @Pointcut("execution(@core.app.zh.com.core.annotation.RecyclerInit  * *(..))")
    public void recyclerInitPoint() {
    }

    @Before("recyclerInitPoint()")
    public void BeforeRecyclerInitPoint(JoinPoint joinPoint) throws IllegalAccessException {
//        SwipeRefreshLayout swipeRefreshLayout = null;
        RecyclerView recycler = null;
        MyBaseAdapter adapter = null;
        RecyclerView.LayoutManager layoutManager = null;
        Field[] fields = joinPoint.getThis().getClass().getDeclaredFields();
        for (Field field : fields) {
            RecyclerParam param = field.getAnnotation(RecyclerParam.class);
            if (param == null) continue;
            if (field.get(joinPoint.getThis()) instanceof RecyclerView) {
                recycler = (RecyclerView) field.get(joinPoint.getThis());
                continue;
            }
            if (field.get(joinPoint.getThis()) instanceof MyBaseAdapter) {
                adapter = (MyBaseAdapter) field.get(joinPoint.getThis());
                continue;
            }
            if (field.get(joinPoint.getThis()) instanceof RecyclerView.LayoutManager) {
                layoutManager = (RecyclerView.LayoutManager) field.get(joinPoint.getThis());
                continue;
            }
        }
        if (recycler != null && joinPoint.getThis() instanceof GetActivityListener) {
            GetActivityListener activity = (GetActivityListener) joinPoint.getThis();
            recycler.setLayoutManager(layoutManager == null ? new LinearLayoutManager(activity.getMyActivity()) : layoutManager);  //线性布局
            if (adapter != null) recycler.setAdapter(adapter);
        }
    }
}
