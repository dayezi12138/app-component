package zh.com.jyu.mvp.presenter.activity;

import com.blankj.utilcode.util.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.bean.activity.AddReportParam;
import zh.com.jyu.bean.activity.UserListBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.constans.SpKeyConstant;
import zh.com.jyu.mvp.contract.activity.AddReportContract;
import zh.com.jyu.mvp.model.activity.AddReportModel;

/**
 * author : dayezi
 * data :2019/6/17
 * description:
 */
public class AddReportPresenter extends BasePresenter<AddReportContract.AddReportUI> implements AddReportContract.Presenter {

    private AddReportModel model;

    @Inject
    public AddReportPresenter(AddReportModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void selectUser() {
        model.userList(new MyObservable.OnSuccessListener<Data<List<UserListBean>>>() {
            @Override
            public void onSuccess(Data<List<UserListBean>> listData) {
                if (listData.isState()) view.get().successUserList(listData.getRes());
                else view.get().showMsg(listData.getMsg());
            }

            @Override
            public void onFail(String msg) {
                view.get().showMsg(msg);
            }
        });
    }

    @Override
    public void submit(AddReportParam addReportParam) {
        model.submit(addReportParam, new MyObservable.OnSuccessListener<Data<Object>>() {
            @Override
            public void onSuccess(Data<Object> objectData) {
                if (objectData.isState()) view.get().submitSuccess();
                else view.get().tipShowMsg(objectData.getMsg());
            }

            @Override
            public void onFail(String msg) {
                view.get().tipShowMsg(msg);
            }
        });
    }

    @Override
    public void eidt(int reportID, int reportedCount, String remark) {
        Map<String, Object> params = new HashMap<>();
        params.put("ReportID", reportID);
        params.put("ReportedCount", reportedCount);
        params.put("Remark", remark);
        params.put("userId", SPUtils.getInstance().getInt(SpKeyConstant.LOGIN_UID_KEY));
        this.model.edit(params, new MyObservable.OnSuccessListener<Data<Object>>() {
            @Override
            public void onSuccess(Data<Object> objectData) {
                if (objectData.isState()) view.get().submitSuccess();
                else view.get().tipShowMsg(objectData.getMsg());
            }

            @Override
            public void onFail(String msg) {
                view.get().tipShowMsg(msg);
            }
        });
    }
}
