package zh.com.jyu.business.activity;

import android.support.annotation.NonNull;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;
import zh.com.jyu.R;

/**
 * author : dayezi
 * data :2019/8/14
 * description:
 */
@Route(path = HtmlActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true)
@ToolbarTitle(title = "生产任务单")
public class HtmlActivity extends BaseActivity {
    public static final String AROUTER_PATH = "/plan/HtmlActivity/";
    public static final String PID_KEY = "PID_KEY";

    @BindView(R.id.content)
    WebView webView;

    @Autowired(name = PID_KEY)
    String pid;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_html;
    }

    @Override
    public void init() {
        String url = "http://120.26.41.167:7080/Produce/In/board/ProducePlanForApp.aspx?pid=" + pid;
        webView.loadUrl(url);
        WebSettings webSettings = webView.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setTextZoom(50);
    }
}
