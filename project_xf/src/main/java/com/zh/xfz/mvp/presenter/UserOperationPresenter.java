package com.zh.xfz.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.android.tu.loadingdialog.LoadingDailog;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.zh.xfz.R;
import com.zh.xfz.bean.activity.Account;
import com.zh.xfz.bean.activity.FileResponseData;
import com.zh.xfz.bean.activity.TargetUserInfo;
import com.zh.xfz.business.activity.BusinessListActivity;
import com.zh.xfz.business.activity.CreateBusinessActivity;
import com.zh.xfz.business.activity.MainActivity;
import com.zh.xfz.business.fragment.BusinessListFragment;
import com.zh.xfz.mvp.contract.activity.UserOperationContract;
import com.zh.xfz.mvp.contract.activity.ValidNoteContract;
import com.zh.xfz.mvp.model.UserOperationModel;
import com.zh.xfz.utils.AndroidUtils;
import com.zh.xfz.utils.IM.IMConnectCallBack;
import com.zh.xfz.utils.IM.IMUtils;
import com.zh.xfz.utils.LoginUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.base.BaseView;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;
import okhttp3.ResponseBody;
import q.rorbin.badgeview.Badge;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.zh.xfz.constans.Constans.IM_TOKEN;

/**
 * author : dayezi
 * data :2019/9/25
 * description:
 */
public class UserOperationPresenter extends BasePresenter<BaseView> implements UserOperationContract.Presenter {
    private UserOperationModel model;


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
                if (data.getCode() == 0) {
                    TargetUserInfo targetUserInfo = data.getRes();
                    Uri uri = TextUtils.isEmpty(targetUserInfo.getUserIcon()) ? imageTranslateUri(R.drawable.rc_default_portrait) : Uri.parse(targetUserInfo.getUserIcon());
                    RongIM.getInstance().refreshUserInfoCache(new UserInfo(userId, targetUserInfo.getMobile(), uri));
                }
            });
            return new UserInfo(userId, "", imageTranslateUri(R.drawable.rc_default_portrait));//根据 userId 去你的用户系统里查询对应的用户信息返回给融云 SDK。
        }, false);
    }

    private Uri imageTranslateUri(int resId) {
        Resources r = model.getMyBaseModel().getMyActivity().getResources();
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + r.getResourcePackageName(resId) + "/"
                + r.getResourceTypeName(resId) + "/"
                + r.getResourceEntryName(resId));

        return uri;
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
                        if (view.get() instanceof ValidNoteContract.ValidNoteUI)
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

    @Override
    public void updatePersonName(String chineseName, String icon) {
        Map<String, String> params = new HashMap<>();
        params.put("userid", LoginUtils.getUserId());
        params.put("timeStamp", AndroidUtils.getUUID());
        params.put("chineseName", chineseName);
        params.put("icon", icon);
        model.updatePersonName(params, data -> {
            if (data.getCode() == 0) {
                LoginUtils.ACCOUNT.setChineseName(chineseName);
                LoginUtils.ACCOUNT.setUserIcon(icon);
                LoginUtils.saveLoginInfo(LoginUtils.ACCOUNT);
                if (view.get() instanceof UserOperationContract.UpdatePersonNameUI) {
                    UserOperationContract.UpdatePersonNameUI ui = (UserOperationContract.UpdatePersonNameUI) view.get();
                    ui.successData();
                } else {
                    view.get().showMsg("上传头像完成");
                    model.getMyBaseModel().getMyActivity().finish();
                }
            } else {
                view.get().showMsg(data.getMsg());
            }
        });
    }

    @Override
    public void bindWX(String code) {
        model.bindWxOpenID(code, data -> {
            if (data.getCode() == 0 && view.get() instanceof UserOperationContract.AccountSecurityUI) {
                UserOperationContract.AccountSecurityUI accountSecurityUI = (UserOperationContract.AccountSecurityUI) view.get();
                accountSecurityUI.success();
            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void wxCheckAndLogin(String code) {
        model.wxCheckAndLogin(code, data -> {
            if (data.getCode() == 40097) bindWX(code);
            else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void relieveWXBind() {
        model.relieveWXBind(data -> {
            if (data.getCode() == 0 && view.get() instanceof UserOperationContract.AccountSecurityUI) {
                UserOperationContract.AccountSecurityUI accountSecurityUI = (UserOperationContract.AccountSecurityUI) view.get();
                accountSecurityUI.relieve();
            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void updateMobile(String mobile, String code) {
        model.updateMobile(mobile, code, data -> {
            if (data.getCode() == 0 && view.get() instanceof UserOperationContract.UpdateMobileUI) {
                UserOperationContract.UpdateMobileUI updateMobileUI = (UserOperationContract.UpdateMobileUI) view.get();
                updateMobileUI.updateMobileSuccess();
            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void wxRegister(Map<String, String> params) {
        model.wxRegister(params, data -> {
            if (data.getCode() == 0) {
                LoginUtils.ACCOUNT = data.getRes();
                LoginUtils.saveLoginInfo(data.getRes());
                if (data.getRes().getTenant() != null && !data.getRes().getTenant().isEmpty())
                    ARouter.getInstance().build(MainActivity.AROUTER_PATH).navigation();
                else
                    ARouter.getInstance().build(BusinessListActivity.AROUTER_PATH).withString(BusinessListActivity.FRAGMENT_CLASS_KEY, BusinessListFragment.class.getName()).navigation();
                model.getMyBaseModel().getMyActivity().finish();
            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void uploadImg(File file) {
        model.uploadImg(file, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    FileResponseData data = new Gson().fromJson(response.body().string(), FileResponseData.class);
                    if (data.getCode() == 0) {
                        updatePersonName(StringUtils.isEmpty(LoginUtils.ACCOUNT.getChineseName()) ? "" : LoginUtils.ACCOUNT.getChineseName(), data.getOriginalUrl());
                    } else model.getMyBaseModel().getBaseView().showMsg("文件上传失败");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.get().showMsg(t.getMessage());
            }
        });
    }

    @Override
    public void wxLogin(String code) {
        Dialog dialog = defaultDialog();
        dialog.show();
        model.wxLogin(code, accountData -> {
            if (accountData.getCode() == 0) connectIM(accountData.getRes(), dialog);
            else {
                view.get().showMsg(accountData.getMsg());
                dialog.dismiss();
            }
        });
    }

    private void connectIM(Account account, Dialog dialog) {
        IMUtils.connect(account.getToken(), new IMConnectCallBack() {
            @Override
            public void success(String userid) {
                LoginUtils.ACCOUNT = account;
                LoginUtils.saveLoginInfo(account);
                ARouter.getInstance().build(MainActivity.AROUTER_PATH).navigation();
                dialog.dismiss();
                model.getMyBaseModel().getMyActivity().finish();
            }

            @Override
            public void fail(String msg) {
                ToastUtils.showShort(msg);
                dialog.dismiss();
            }
        });
    }

    private Dialog defaultDialog() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(model.getMyBaseModel().getMyActivity())
                .setMessage("登录中...")
                .setCancelable(false)
                .setCancelOutside(false);
        return loadBuilder.create();
    }
}
