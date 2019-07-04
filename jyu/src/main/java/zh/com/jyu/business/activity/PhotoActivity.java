package zh.com.jyu.business.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.api.ToolBarInject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BasePresenter;
import uk.co.senab.photoview.PhotoView;
import zh.com.jyu.R;

/**
 * author : dayezi
 * data :2019/6/17
 * description:
 */
@Route(path = PhotoActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true)
@ToolbarTitle(title = "图片查看")
public class PhotoActivity extends BaseActivity {

    public static final String AROUTER_PATH = "/plan/PhotoActivity/";
    public static final String PHOTO_URL_KEY = "url";

    @BindView(R.id.photo_iv)
    PhotoView photoView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Autowired(name = PhotoActivity.PHOTO_URL_KEY)
    String url;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_photo;
    }

    @Override
    public void init() {
        ToolBarInject.inject(this, toolbar);
        Glide.with(this).load(url).into(photoView);
    }

    @Override
    public boolean addToolbar() {
        return true;
    }
}
