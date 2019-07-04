package zh.com.jyu.business.activity;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ScreenUtils;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BasePresenter;
import zh.com.jyu.R;
import zh.com.jyu.constans.CommonConstants;
import zh.com.jyu.listener.LoginStateListener;

/**
 * author : dayezi
 * data :2019/6/4
 * description:
 */
public class SplashActivity extends BaseActivity {
//    // 语音合成对象
//    private SpeechSynthesizer mTts;
//    // 引擎类型
//    private String mEngineType = SpeechConstant.TYPE_CLOUD;


    @Inject
    LoginStateListener loginStateListener;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_splash;
    }

    @Override
    public void init() {
//        // 初始化合成对象
//        mTts = SpeechSynthesizer.createSynthesizer(this, i -> {
//
//        });
//        setParam();
//        findViewById(R.id.ly).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String test = "这是一段测试代码";
//                int code = mTts.startSpeaking(test, new SynthesizerListener() {
//                    @Override
//                    public void onSpeakBegin() {
//
//                    }
//
//                    @Override
//                    public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
//                        LogUtils.e(percent);
//                    }
//
//                    @Override
//                    public void onSpeakPaused() {
//
//                    }
//
//                    @Override
//                    public void onSpeakResumed() {
//
//                    }
//
//                    @Override
//                    public void onSpeakProgress(int i, int i1, int i2) {
//
//                    }
//
//                    @Override
//                    public void onCompleted(SpeechError speechError) {
//
//                    }
//
//                    @Override
//                    public void onEvent(int i, int i1, int i2, Bundle bundle) {
//
//                    }
//                });
//            }
//        });

        new Handler().postDelayed(() -> {
            loginStateListener.start();
            finish();
        }, CommonConstants.DELAY_TIME);
    }

    @Override
    public View beforeInit(LayoutInflater inflater, ViewGroup container) {
        ScreenUtils.setFullScreen(this);
        return super.beforeInit(inflater, container);
    }

//    /**
//     * 参数设置
//     *
//     * @return
//     */
//    private void setParam() {
//        // 清空参数
//        mTts.setParameter(SpeechConstant.PARAMS, null);
//        // 根据合成引擎设置相应参数
//        if (mEngineType.equals(SpeechConstant.TYPE_CLOUD)) {
//            mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
//            //支持实时音频返回，仅在synthesizeToUri条件下支持
//            //mTts.setParameter(SpeechConstant.TTS_DATA_NOTIFY, "1");
//            // 设置在线合成发音人
//            mTts.setParameter(SpeechConstant.VOICE_NAME, voicer);
//            //设置合成语速
//            mTts.setParameter(SpeechConstant.SPEED, "50");
//            //设置合成音调
//            mTts.setParameter(SpeechConstant.PITCH, "50");
//            //设置合成音量
//            mTts.setParameter(SpeechConstant.VOLUME, "50");
//        } else {
//            mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_LOCAL);
//            mTts.setParameter(SpeechConstant.VOICE_NAME, "");
//
//        }
//        //设置播放器音频流类型
//        mTts.setParameter(SpeechConstant.STREAM_TYPE, "3");
//        // 设置播放合成音频打断音乐播放，默认为true
//        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");
//
//        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
//        mTts.setParameter(SpeechConstant.AUDIO_FORMAT, "pcm");
//        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/tts.pcm");
//    }
//
//    // 默认发音人
//    private String voicer = "xiaoyan";
}
