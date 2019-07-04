package zh.com.jyu.business.fragment.leader;

import android.support.annotation.NonNull;

import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.base.BasePresenter;
import zh.com.jyu.R;

/**
 * author : dayezi
 * data :2019/6/12
 * description:
 */
public class UnStartFragment extends BaseFragment {


    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.fragment_start_state;
    }

    @Override
    public void init() {
    }
}
