package zh.com.jyu.mvp.presenter.activity;

import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.mvp.contract.activity.EditReportContract;
import zh.com.jyu.mvp.model.activity.EditReportModel;

/**
 * author : dayezi
 * data :2019/6/20
 * description:
 */
public class EditReportPresenter extends BasePresenter<EditReportContract.EditReportUI> implements EditReportContract.Presenter {
    private EditReportModel model;

    @Inject
    public EditReportPresenter(EditReportModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void edit(Map<String, Object> params) {
        this.model.edit(params, new MyObservable.OnSuccessListener<Data<Object>>() {
            @Override
            public void onSuccess(Data<Object> objectData) {
                if (objectData.isState()) {
                    view.get().showMsg("提交成功");
                    model.getBean().finish();
                } else
                    view.get().showMsg(objectData.getMsg());
            }

            @Override
            public void onFail(String msg) {
                view.get().showMsg(msg);
            }
        });
    }
}
