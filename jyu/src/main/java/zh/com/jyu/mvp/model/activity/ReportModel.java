package zh.com.jyu.mvp.model.activity;

import com.blankj.utilcode.util.SPUtils;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.api.MyService;
import zh.com.jyu.bean.activity.ReportListBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.business.activity.ReportActivity;
import zh.com.jyu.constans.SpKeyConstant;

/**
 * author : dayezi
 * data :2019/6/17
 * description:
 */
public class ReportModel extends BaseListModel<ReportListBean.ReportsBean, ReportActivity> {
    private ReportActivity activity;
    private MyService myService;
    private ReportListBean reportListBean;

    @Inject
    public ReportModel(ReportActivity activity, MyService myService) {
        this.activity = activity;
        this.myService = myService;
//        this.dialog = dialog;
    }

    @Override
    public void requestData(Map<String, Object> params, MyObservable.OnSuccessListener<Data<List<ReportListBean.ReportsBean>>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, activity).build();
        myService.getReportList(params).subscribe(observable);
    }

    public void requestData1(Map<String, Object> params, MyObservable.OnSuccessListener<Data<ReportListBean>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, activity).build();
        myService.getReportList(params).subscribe(observable);
    }

    public void delete(int reportId, MyObservable.OnSuccessListener<Data<Object>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, activity).build();
        myService.delReport(reportId, SPUtils.getInstance().getInt(SpKeyConstant.LOGIN_UID_KEY)).subscribe(observable);
    }

    @Override
    public ReportActivity getBean() {
        return activity;
    }

    public ReportListBean getReportListBean() {
        return reportListBean;
    }

    public void setReportListBean(ReportListBean reportListBean) {
        this.reportListBean = reportListBean;
    }
}
