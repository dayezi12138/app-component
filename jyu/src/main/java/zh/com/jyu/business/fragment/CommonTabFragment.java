package zh.com.jyu.business.fragment;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextPaint;
import android.util.TypedValue;
import android.widget.TextView;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseFragment;
import zh.com.jyu.R;
import zh.com.jyu.views.NoScrollerViewPager;

/**
 * author : dayezi
 * data :2019/6/12
 * description:
 */
public abstract class CommonTabFragment extends BaseFragment implements ViewPager.OnPageChangeListener, TabLayout.OnTabSelectedListener {

    @BindView(R.id.view_pager)
    NoScrollerViewPager viewPager;

    @BindView(R.id.mytab)
    TabLayout myTabLayout;

    private int maxTextSize = 16;
    private int minTextSize = 14;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void init() {
        this.getMyActivity().setStatusBarColor(myTabLayout, getResources().getColor(R.color.colorPrimary));
        viewPager.setScroll(true);
        viewPager.setAdapter(adapter());
        myTabLayout.setupWithViewPager(viewPager, true);
        viewPager.setOffscreenPageLimit(adapter().getCount());
        viewPager.setCurrentItem(0);
        initTabItem();
        viewPager.addOnPageChangeListener(this);
        myTabLayout.addOnTabSelectedListener(this);
    }

    private void setMax(TabLayout.Tab tab) {
        tab.getCustomView().findViewById(R.id.tab_text).setSelected(true);
        viewPager.setCurrentItem(tab.getPosition());
        AppCompatTextView textView = tab.getCustomView().findViewById(R.id.tab_text);
        textView.setWidth(getTextWidth(textView.getText().toString(), true));
        textView.setTextColor(getResources().getColor(R.color.white));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, maxTextSize);
    }

    private void setMin(TabLayout.Tab tab) {
        tab.getCustomView().findViewById(R.id.tab_text).setSelected(false);
        AppCompatTextView textView = tab.getCustomView().findViewById(R.id.tab_text);
        textView.setWidth(getTextWidth(textView.getText().toString(), false));
        textView.setTextColor(getResources().getColor(R.color.txtbg));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, minTextSize);
    }

    private int getTextWidth(String text, boolean isMax) {
        TextView tv = new TextView(getActivity());
        int size = minTextSize;
        if (isMax) size = maxTextSize;
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        TextPaint textPaint = tv.getPaint();
        return (int) textPaint.measureText(text);
    }


    protected void initTabItem() {
        for (int i = 0; i < myTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = myTabLayout.getTabAt(i);
            assert tab != null;
            tab.setCustomView(R.layout.tab_item);//给tab自定义样式
            assert tab.getCustomView() != null;
            AppCompatTextView textView = tab.getCustomView().findViewById(R.id.tab_text);
            textView.setText(titles()[i]);
            if (i == 0) {
                setMax(tab);
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        onTabSelected(position);
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    public void onTabSelected(int position) {

    }

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

    public abstract String[] titles();

    public abstract FragmentPagerAdapter adapter();
}
