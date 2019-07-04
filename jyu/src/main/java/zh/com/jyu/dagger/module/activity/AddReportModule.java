package zh.com.jyu.dagger.module.activity;

import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechSynthesizer;

import core.app.zh.com.core.annotation.ActivityScope;
import dagger.Module;
import dagger.Provides;
import zh.com.jyu.business.activity.AddReportActivity;
import zh.com.jyu.business.adapter.UserAdapter;

/**
 * author : dayezi
 * data :2019/6/17
 * description:
 */
@Module
public class AddReportModule {
    @ActivityScope
    @Provides
    public MaterialDialog dialog(AddReportActivity activity, UserAdapter adapter) {
        return new MaterialDialog.Builder(activity).title("选择用户").adapter(adapter, new LinearLayoutManager(activity)).build();
    }

    @ActivityScope
    @Provides
    public UserAdapter adapter() {
        return new UserAdapter();
    }

    @ActivityScope
    @Provides
    public MaterialDialog.Builder deleteDialog(AddReportActivity activity) {
        return new MaterialDialog.Builder(activity).title("提示").content("是否删除该条数据?").positiveText("确定");
    }

    @ActivityScope
    @Provides
    public SpeechSynthesizer speechSynthesizer(AddReportActivity activity) {
        SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer(activity, i -> {

        });
        // 清空参数
        mTts.setParameter(SpeechConstant.PARAMS, null);
        // 根据合成引擎设置相应参数
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        //支持实时音频返回，仅在synthesizeToUri条件下支持
        //mTts.setParameter(SpeechConstant.TTS_DATA_NOTIFY, "1");
        // 设置在线合成发音人
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");
        //设置合成语速
        mTts.setParameter(SpeechConstant.SPEED, "50");
        //设置合成音调
        mTts.setParameter(SpeechConstant.PITCH, "50");
        //设置合成音量
        mTts.setParameter(SpeechConstant.VOLUME, "50");
        //设置播放器音频流类型
        mTts.setParameter(SpeechConstant.STREAM_TYPE, "3");
        // 设置播放合成音频打断音乐播放，默认为true
        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        mTts.setParameter(SpeechConstant.AUDIO_FORMAT, "pcm");
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/tts.pcm");
        return mTts;
    }

}
