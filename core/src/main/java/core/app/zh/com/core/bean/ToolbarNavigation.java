package core.app.zh.com.core.bean;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import core.app.zh.com.core.R;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.bean.i.IPunish;

/**
 * toolbar 返回键设置
 *
 * @auther create by Administrator
 * DATE:2019/3/26 0026 11
 */@Deprecated
public class ToolbarNavigation implements IPunish<ToolbarNavigation> {
    private int NavigationIconId = R.drawable.ic_back;
    private String text;
    private boolean visibleNavigation;
    private View.OnClickListener listener;
    private int textColor = Color.WHITE;

    public int getNavigationIconId() {
        return NavigationIconId;
    }

    public void setNavigationIconId(int navigationIconId) {
        NavigationIconId = navigationIconId;
    }

    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isVisibleNavigation() {
        return visibleNavigation;
    }

    public void setVisibleNavigation(boolean visibleNavigation) {
        this.visibleNavigation = visibleNavigation;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    @Override
    public void punish(final BaseActivity activity, Toolbar toolbar, ToolbarNavigation navigation) {
        if (visibleNavigation) toolbar.setNavigationIcon(NavigationIconId);
        if (navigation.getListener() != null)
            toolbar.setNavigationOnClickListener(navigation.getListener());
        else toolbar.setNavigationOnClickListener(v -> activity.finish());
        if (!TextUtils.isEmpty(navigation.getText())) toolbar.setSubtitle(navigation.getText());
        toolbar.setSubtitleTextColor(textColor);
    }
}
