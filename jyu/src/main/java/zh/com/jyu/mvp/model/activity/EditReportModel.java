package zh.com.jyu.mvp.model.activity;

import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseModel;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.api.MyService;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.business.activity.EditReportActivity;

/**
 * author : dayezi
 * data :2019/6/20
 * description:
 */
public class EditReportModel implements BaseModel<EditReportActivity> {

    private EditReportActivity activity;
    private MyService myService;

    @Inject
    public EditReportModel(EditReportActivity activity, MyService myService) {
        this.activity = activity;
        this.myService = myService;
    }

    @Inject

    @Override
    public EditReportActivity getBean() {
        return activity;
    }


    public void edit(Map<String, Object> param, MyObservable.OnSuccessListener<Data<Object>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, activity).build();
        myService.editReport(param).subscribe(observable);
    }
}
