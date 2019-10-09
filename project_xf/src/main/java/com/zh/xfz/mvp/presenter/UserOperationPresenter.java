package com.zh.xfz.mvp.presenter;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zh.xfz.bean.activity.TargetUserInfo;
import com.zh.xfz.business.activity.CreateBusinessActivity;
import com.zh.xfz.business.activity.MainActivity;
import com.zh.xfz.mvp.contract.activity.UserOperationContract;
import com.zh.xfz.mvp.contract.activity.ValidNoteContract;
import com.zh.xfz.mvp.model.UserOperationModel;
import com.zh.xfz.utils.AndroidUtils;
import com.zh.xfz.utils.IM.IMConnectCallBack;
import com.zh.xfz.utils.IM.IMUtils;
import com.zh.xfz.utils.LoginUtils;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.base.BaseView;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;
import q.rorbin.badgeview.Badge;

import static com.zh.xfz.constans.Constans.IM_TOKEN;

/**
 * author : dayezi
 * data :2019/9/25
 * description:
 */
public class UserOperationPresenter extends BasePresenter<BaseView> implements UserOperationContract.Presenter {
    private UserOperationModel model;
//    @Inject
//    RongIMClient.OnReceiveMessageListener onReceiveMessageListener;

    @Inject
    public UserOperationPresenter(UserOperationModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void getUserInfo(String account) {
        model.getUserInfo(account, data -> {
            if (data.getCode() == -1) {
                if (view.get() instanceof UserOperationContract.UserOperationUI)
                    ((UserOperationContract.UserOperationUI) view.get()).userInfoSuccess(data.getRes());
            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void updatePassWord(String oldPassword, String newPassword1, String newPassword2) {
        model.updatePassWord(oldPassword, newPassword1, newPassword2, data -> {
            if (data.getCode() == 0) {
                if (view.get() instanceof UserOperationContract.AddPasswordUI) {
                    ((UserOperationContract.AddPasswordUI) view.get()).success();
                } else {
                    view.get().showMsg("修改成功");
                    model.getMyBaseModel().getMyActivity().finish();
                }
            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void refreshUserInfo() {
        RongIM.setUserInfoProvider(userId -> {
            model.getTargetUserInfo(userId, data -> {
                if (data.getCode() == -1) {
                    TargetUserInfo targetUserInfo = data.getRes();
                    Uri uri = TextUtils.isEmpty(targetUserInfo.getUserIcon()) ? Uri.EMPTY : Uri.parse(targetUserInfo.getUserIcon());
                    RongIM.getInstance().refreshUserInfoCache(new UserInfo(userId, targetUserInfo.getMobile(), uri));
                }
            });
            return new UserInfo(userId, "", Uri.EMPTY);//根据 userId 去你的用户系统里查询对应的用户信息返回给融云 SDK。
        }, false);
    }


    @Override
    public void initRongIM(Badge badge) {
//        new Thread(() -> {
//            RongIM.getInstance().addUnReadMessageCountChangedObserver(i -> badge.setBadgeNumber(i), Conversation.ConversationType.values());
//            RongIM.setOnReceiveMessageListener(onReceiveMessageListener);
//        }).start();
//        Observable.create(emitter -> {
//            RongIM.getInstance().addUnReadMessageCountChangedObserver(i -> badge.setBadgeNumber(i), Conversation.ConversationType.values());
//            RongIM.setOnReceiveMessageListener(onReceiveMessageListener);
//            emitter.onNext(1);
//        }).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe();
    }

    @Override
    public void getTargetUserInfo(String targetId) {
        model.getTargetUserInfo(targetId, data -> {
            if (data.getCode() == -1 && view.get() instanceof UserOperationContract.TargetUserInfoUI)
                ((UserOperationContract.TargetUserInfoUI) view.get()).targetUserInfoSuccess(data.getRes());
            else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void register(String mobile, String code) {
        model.register(mobile, code, data -> {
            if (data.getCode() == 0)
                IMUtils.connect(data.getRes().getToken(), new IMConnectCallBack() {
                    @Override
                    public void success(String userId) {
                        if (view.get() instanceof UserOperationContract.TargetUserInfoUI)
                            ((ValidNoteContract.ValidNoteUI) view.get()).registerSuccess(data.getRes(), userId);
                    }

                    @Override
                    public void fail(String msg) {
                        view.get().showMsg(msg);
                    }
                });
            else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void getCode(String phone, boolean isRegister) {
        model.getCode(phone, !isRegister ? 1 : 2, validSmsCode -> {
            if (validSmsCode.getCode() == 0) {
                ToastUtils.showShort(validSmsCode.getMsg());
                if (view.get() instanceof ValidNoteContract.ValidNoteUI)
                    ((ValidNoteContract.ValidNoteUI) view.get()).success();
            } else view.get().showMsg(validSmsCode.getMsg());

        });
    }

    @Override
    public void login(String account, String password) {
        model.login(account, password, data -> {
            LoginUtils.clearLoginInfo();
            LoginUtils.saveLoginInfo(data.getRes());
            IMUtils.connect(SPUtils.getInstance().getString(IM_TOKEN), new IMConnectCallBack() {
                @SuppressLint("WrongConstant")
                @Override
                public void success(String userId) {
                    if (data.getRes().getTenant().isEmpty())
                        ARouter.getInstance().build(CreateBusinessActivity.AROUTER_PATH).navigation();
                    else ARouter.getInstance().build(MainActivity.AROUTER_PATH).navigation();
                }

                @Override
                public void fail(String msg) {
                    view.get().showMsg(msg);
                }
            });
        });
    }

    @Override
    public void forgetPassWord(String account, String newpass1, String newpass2, String code) {
        if (!newpass1.equals(newpass2)) {
            view.get().showMsg("两次输入不一致");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("mobile", account);
        params.put("newpass1", newpass1);
        params.put("newpass2", newpass2);
        params.put("code", code);
        params.put("timeStamp", AndroidUtils.getUUID());
        model.forgetPassWord(params, data -> {
            if (data.getCode() == 0) {
                login(account, newpass1);
                view.get().showMsg("修改成功");
            }
        });
    }


}
