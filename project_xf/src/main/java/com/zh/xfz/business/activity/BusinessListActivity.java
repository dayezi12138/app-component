package com.zh.xfz.business.activity;

import android.support.v4.app.Fragment;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.zh.xfz.R;

import java.util.ArrayList;
import java.util.List;

import core.app.zh.com.core.base.MyFragmentActivity;
import core.app.zh.com.core.bean.FragmentInfo;

/**
 * author : dayezi
 * data :2019/12/9
 * description:
 */
@Route(path = BusinessListActivity.AROUTER_PATH)
public class BusinessListActivity extends MyFragmentActivity {
    public final static String AROUTER_PATH = "/login/CompanyActivity/";
    public static final String FRAGMENT_CLASS_KEY = "fragment.class.type";

    @Autowired(name = FRAGMENT_CLASS_KEY, required = true)
    String className;

    @Override
    public List<FragmentInfo> fragmentList() {
        if (StringUtils.isEmpty(className)) return null;
        List<FragmentInfo> fragmentInfos = new ArrayList<>();
        try {
            Object object = Class.forName(className).newInstance();
            if (object instanceof Fragment) {
                FragmentInfo info = new FragmentInfo();
                info.setFragmentLayoutId(R.id.first_fragment_id);
                info.setFragment((Fragment) object);
                info.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                fragmentInfos.add(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e(e.getMessage());
        }
        return fragmentInfos;
    }
}
