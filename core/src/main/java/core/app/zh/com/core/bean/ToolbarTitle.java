package core.app.zh.com.core.bean;

import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;

import core.app.zh.com.core.R;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.bean.i.IPunish;

/**
 * @auther create by Administrator
 * DATE:2019/3/26 0026 11
 */
public class ToolbarTitle implements IPunish<ToolbarTitle>{
    private String text = "";
    private int textSize =18;
    private int textColor = Color.BLACK;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    @Override
    public void punish(BaseActivity activity, Toolbar toolbar, ToolbarTitle bean) {
        AppCompatTextView textView = toolbar.findViewById(R.id.title);
        textView.setText(bean.getText());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,bean.getTextSize());
        textView.setTextColor(bean.getTextColor());
    }
}
