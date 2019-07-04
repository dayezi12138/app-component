package zh.com.jyu.mvp.presenter.activity;

import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.bean.activity.ReportListBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.mvp.contract.activity.ReportContract;
import zh.com.jyu.mvp.model.activity.ReportModel;

/**
 * author : dayezi
 * data :2019/6/17
 * description:
 */
public class ReportPresenter extends BaseListPresenter<ReportListBean.ReportsBean> implements ReportContract.Presenter {
    private ReportModel model;

    @Inject
    public ReportPresenter(ReportModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void requestData(Map<String, Object> params) {
        model.requestData1(params, new MyObservable.OnSuccessListener<Data<ReportListBean>>() {
            @Override
            public void onSuccess(Data<ReportListBean> listData) {
                if (listData.isState()) {
                    model.setReportListBean(listData.getRes());
                    view.get().success(listData.getRes().getReports());
                } else view.get().showMsg(listData.getMsg());
            }

            @Override
            public void onFail(String msg) {
                view.get().showMsg(msg);
            }
        });
    }


    public ReportListBean getReportListBean() {
        return model.getReportListBean();
    }

    @Override
    public void delete(int reportId, int position) {
        model.delete(reportId, new MyObservable.OnSuccessListener<Data<Object>>() {
            @Override
            public void onSuccess(Data<Object> objectData) {
                if (objectData.isState()) {
                    model.getBean().myBaseAdapter().remove(position);
                } else view.get().showMsg(objectData.getMsg());
            }

            @Override
            public void onFail(String msg) {
                view.get().showMsg(msg);
            }
        });
    }
}
