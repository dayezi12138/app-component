package zh.com.jyu.business.fragment;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.zh.api.DefaultOptionListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.view.MyPopupWindow;
import zh.com.jyu.R;
import zh.com.jyu.business.adapter.StringAdapter;

/**
 * author : dayezi
 * data :2019/6/11
 * description:
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    TextView title;

    @Inject
    DefaultOptionListener defaultOptionListener;

    MyPopupWindow popupWindow;

    @Inject
    StringAdapter stringAdapter;

    @Inject
    String[] titles;

    @Inject
    List<Fragment> fragmentList;


    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.fragment_leader;
    }

    @Override
    public void init() {
        RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(getContext()).inflate(R.layout.recycler_, null);
        recyclerView.setBackgroundResource(R.color.white);
        popupWindow = new MyPopupWindow.Builder(recyclerView, getMyActivity()).width(ConvertUtils.dp2px(80)).height(ConvertUtils.dp2px(120)).build();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));  //线性布局
        recyclerView.setAdapter(stringAdapter);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.tool_bar_custom_title, null);
        title = view.findViewById(R.id.title1);
        title.setText(titles[0]);
        Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER;
        view.setLayoutParams(layoutParams);
        toolbar.addView(view);
        toolbar.setBackgroundColor(getResources().getColor(defaultOptionListener.defaultToolbarOption().getToolbarBackgroundColorId()));
        getMyActivity().setStatusBarColor(toolbar, getResources().getColor(defaultOptionListener.defaultToolbarOption().getToolbarBackgroundColorId()));
        title.setOnClickListener(v -> {
            if (!popupWindow.isShowing()) popupWindow.showAsDropDown(title, 0, 0, Gravity.BOTTOM);
        });
        stringAdapter.setOnItemClickListener((adapter, view1, position) -> {
            title.setText(titles[position]);
            HomeFragment.this.position = position;
            popupWindow.dismiss();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            for (int i = 0; i < fragmentList.size(); i++) {
                if (i != position) transaction.hide(fragmentList.get(i));
            }
            transaction.show(fragmentList.get(position));
            transaction.commit();

        });
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        for (Fragment fragment : fragmentList) {
            transaction.add(R.id.fragment_1, fragment);
            transaction.hide(fragment);
        }
        transaction.show(fragmentList.get(0));
        transaction.commit();
    }

    private int position = 0;

    public Fragment getFragment() {
        return fragmentList.get(position);
    }

}
