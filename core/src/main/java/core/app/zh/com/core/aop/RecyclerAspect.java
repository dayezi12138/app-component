package core.app.zh.com.core.aop;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import core.app.zh.com.core.R;
import core.app.zh.com.core.annotation.RecyclerField;
import core.app.zh.com.core.base.MyBaseAdapter;

/**
 * author : dayezi
 * data :2019/10/9
 * description:
 */
@Aspect
public class RecyclerAspect {

    @Pointcut("execution(@core.app.zh.com.core.annotation.RecyclerOption  * *(..))")
    public void pointcutRecyclerOption() {
    }

    @After("pointcutRecyclerOption()")
    public void afterPoint(JoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        try {
            RecyclerView.ItemDecoration itemDecoration = null;
            RecyclerView mRecyclerView = null;
            MyBaseAdapter adapter = null;
            RecyclerView.LayoutManager layoutManager = null;
            SwipeRefreshLayout swipeRefreshLayout = null;
            Object obj = joinPoint.getThis();
            Field[] fields = joinPoint.getThis().getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                for (Annotation annotation : field.getDeclaredAnnotations()) {
                    if (annotation instanceof RecyclerField) {
                        Object data = field.get(obj);
                        if (field.get(obj) instanceof RecyclerView)
                            mRecyclerView = (RecyclerView) data;
                        else if (field.get(obj) instanceof MyBaseAdapter)
                            adapter = (MyBaseAdapter) data;
                        else if (field.get(obj) instanceof RecyclerView.LayoutManager)
                            layoutManager = (RecyclerView.LayoutManager) data;
                        else if (field.get(obj) instanceof RecyclerView.ItemDecoration)
                            itemDecoration = (RecyclerView.ItemDecoration) data;
                        else if (field.get(obj) instanceof SwipeRefreshLayout)
                            swipeRefreshLayout = (SwipeRefreshLayout) data;
                    }
                }
            }
            if (mRecyclerView != null && adapter != null && layoutManager != null) {
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setAdapter(adapter);
                if (itemDecoration != null) mRecyclerView.addItemDecoration(itemDecoration);
                if (swipeRefreshLayout != null && joinPoint.getThis() instanceof SwipeRefreshLayout.OnRefreshListener && joinPoint.getThis() instanceof BaseQuickAdapter.RequestLoadMoreListener) {
                    LogUtils.e("---这里执行了---");
                    adapter.setOnLoadMoreListener((BaseQuickAdapter.RequestLoadMoreListener) joinPoint.getThis(), mRecyclerView);
                    swipeRefreshLayout.setOnRefreshListener((SwipeRefreshLayout.OnRefreshListener) joinPoint.getThis());
                    adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
                    adapter.setEmptyView(R.layout.empty_view);
                }
//                if (joinPoint.getThis() instanceof BaseQuickAdapter.RequestLoadMoreListener) {
//                    adapter.setOnLoadMoreListener((BaseQuickAdapter.RequestLoadMoreListener) joinPoint.getThis(), mRecyclerView);
//                }
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        LogUtils.e("执行完耗时: " + (startTime - System.currentTimeMillis()));
    }
}
