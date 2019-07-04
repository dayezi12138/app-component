package zh.com.jyu.utils;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextPaint;
import android.util.TypedValue;
import android.widget.TextView;

import zh.com.jyu.R;
import zh.com.jyu.views.NoScrollerViewPager;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
public class MyTabHelper {
    private static final int maxTextSize = 16;
    private static final int minTextSize = 14;
    private String[] titles;
    private FragmentPagerAdapter adapter;
    private Context context;
    private NoScrollerViewPager viewPager;

    public MyTabHelper(Context context, String[] titles, FragmentPagerAdapter adapter) {
        this.titles = titles;
        this.adapter = adapter;
        this.context = context;
    }

    public void init(NoScrollerViewPager viewPager, TabLayout myTabLayout) {
        this.viewPager = viewPager;
        viewPager.setScroll(false);
        viewPager.setAdapter(adapter);
        myTabLayout.setupWithViewPager(viewPager, true);
        viewPager.setOffscreenPageLimit(adapter.getCount());
        initTabItem(myTabLayout);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        myTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setMax(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                setMin(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {


            }
        });
    }

    private void initTabItem(TabLayout myTabLayout) {
        for (int i = 0; i < myTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = myTabLayout.getTabAt(i);
            assert tab != null;
            tab.setCustomView(R.layout.tab_item);//给tab自定义样式
            assert tab.getCustomView() != null;
            AppCompatTextView textView = tab.getCustomView().findViewById(R.id.tab_text);
            textView.setText(titles[i]);
            if (i == 0) {
                setMax(tab);
            }
        }
    }

    private void setMax(TabLayout.Tab tab) {
        tab.getCustomView().findViewById(R.id.tab_text).setSelected(true);
        viewPager.setCurrentItem(tab.getPosition());
        AppCompatTextView textView = tab.getCustomView().findViewById(R.id.tab_text);
        textView.setWidth(getTextWidth(textView.getText().toString(), true));
        textView.setTextColor(context.getResources().getColor(R.color.white));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, maxTextSize);
    }

    private void setMin(TabLayout.Tab tab) {
        tab.getCustomView().findViewById(R.id.tab_text).setSelected(false);
        AppCompatTextView textView = tab.getCustomView().findViewById(R.id.tab_text);
        textView.setWidth(getTextWidth(textView.getText().toString(), false));
        textView.setTextColor(context.getResources().getColor(R.color.txtbg));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, minTextSize);
    }

    private int getTextWidth(String text, boolean isMax) {
        TextView tv = new TextView(context);
        int size = minTextSize;
        if (isMax) size = maxTextSize;
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        TextPaint textPaint = tv.getPaint();
        return (int) textPaint.measureText(text);
    }

}
