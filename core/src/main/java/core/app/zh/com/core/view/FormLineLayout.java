package core.app.zh.com.core.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;

import core.app.zh.com.core.R;


/**
 * author : dayezi
 * data :2019/5/8
 * description:
 */
public class FormLineLayout extends LinearLayout {
    private TextView leftTextView;

    public FormLineLayout(Context context) {
        this(context, null);
    }

    public FormLineLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public FormLineLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TextView leftTextView = new TextView(context);
        LayoutParams textParam = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        leftTextView.setTextAppearance(context, R.style.order_line_layout_default_right_text_style);
        leftTextView.setGravity(Gravity.CENTER_VERTICAL);
        leftTextView.setMinWidth(SizeUtils.dp2px(80));
        leftTextView.setLayoutParams(textParam);
        this.addView(leftTextView);
        this.leftTextView = leftTextView;

        //获取自定义属性的值
        TypedArray attr = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FormLineLayout, defStyleAttr, 0);
        leftTextView.setText(TextUtils.isEmpty(attr.getString(R.styleable.FormLineLayout_leftTextStr)) ? "" : attr.getString(R.styleable.FormLineLayout_leftTextStr));
        leftTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, attr.getDimension(R.styleable.FormLineLayout_leftTextSize, context.getResources().getDimension(R.dimen.font_large_sp)));
        leftTextView.setTextColor(attr.getColor(R.styleable.FormLineLayout_leftTextColor, context.getResources().getColor(R.color.text1)));
    }

    public TextView getLeftTextView() {
        return leftTextView;
    }


    public void setLeftText(String txt) {
        this.leftTextView.setText(txt);
    }
}
