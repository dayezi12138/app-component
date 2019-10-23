package com.zh.xfz.mvp.presenter.activity;

import android.app.Dialog;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.android.tu.loadingdialog.LoadingDailog;
import com.zh.xfz.R;
import com.zh.xfz.business.activity.ValidNoteActivity;
import com.zh.xfz.mvp.contract.activity.AccountLoginContract;
import com.zh.xfz.mvp.model.activity.LoginModel;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BasePresenter;

/**
 * author : dayezi
 * data :2019/7/24
 * description:
 */
public class AccountLoginPresenter extends BasePresenter<AccountLoginContract.AccountLoginUI> implements AccountLoginContract.Presenter {
    private LoginModel model;

    @Inject
    public AccountLoginPresenter(LoginModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void validAccount(String account) {
        if (TextUtils.isEmpty(account)) {
            view.get().showMsg("输入账号不能为空");
            return;
        }
//        if (SPUtils.getInstance().contains(USER_INFO) && SPUtils.getInstance().getString(USER_INFO).equals(account)) {
//
////            String userId = SPUtils.getInstance().getString(USER_INFO).split(FLAG_STR)[0];
////            model.getInfo(userId, (ObservableListener.SuccessListener<Data<UserInfo>>) data -> {
////                if (data.getCode() == -1) {
//            Dialog dialog = defaultDialog(model.getMyBaseModel().getMyActivity());
//            dialog.show();
//            IMUtils.connect(SPUtils.getInstance().getString(IM_TOKEN), new IMConnectCallBack() {
//                @SuppressLint("WrongConstant")
//                @Override
//                public void success(String userId) {
//                    dialog.dismiss();
//                    ARouter.getInstance().build(CreateBusinessActivity.AROUTER_PATH).navigation();
//                }
//
//                @Override
//                public void fail(String msg) {
//                    view.get().showMsg(msg);
//                    dialog.dismiss();
//                }
//            });
////                }
////            });
////            return;
//        }
        model.validAccount(account, validAccount -> {
//            if (validAccount.getCode() == 40003) {
//                ARouter.getInstance().build(ValidNoteActivity.AROUTER_PATH).withString(ValidNoteActivity.ACCOUNT_KEY, account)
//                        .withBoolean(ValidNoteActivity.EXIST_ACCOUNT_KEY, false).navigation();
//            } else if (validAccount.getCode() == 0) {
//                if (!validAccount.isFlag())
//                    ARouter.getInstance().build(AddPasswordActivity.AROUTER_PATH).withString(InputPasswordActivity.ACCOUNT_KEY, account).navigation();
//                else
//                    ARouter.getInstance().build(InputPasswordActivity.AROUTER_PATH).withString(ACCOUNT_KEY, account).navigation();
//
//            } else view.get().showMsg(validAccount.getMsg());
            if (validAccount.getCode() == 40003 || validAccount.getCode() == 0) {
                boolean exist = validAccount.getCode() == 40003 ? false : true;
                ARouter.getInstance().build(ValidNoteActivity.AROUTER_PATH).withString(ValidNoteActivity.ACCOUNT_KEY, account)
                        .withBoolean(ValidNoteActivity.EXIST_ACCOUNT_KEY, exist).navigation();
            } else view.get().showMsg(validAccount.getMsg());
        });
    }

    private Dialog defaultDialog(BaseActivity activity) {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(activity)
                .setMessage(activity.getResources().getString(R.string.loading_msg))
                .setCancelable(false)
                .setCancelOutside(false);
        return loadBuilder.create();
    }
}
