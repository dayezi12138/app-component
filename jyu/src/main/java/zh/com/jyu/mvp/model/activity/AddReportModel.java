package zh.com.jyu.mvp.model.activity;

import com.blankj.utilcode.util.SPUtils;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseModel;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.api.MyService;
import zh.com.jyu.bean.activity.AddReportParam;
import zh.com.jyu.bean.activity.UserListBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.business.activity.AddReportActivity;
import zh.com.jyu.constans.SpKeyConstant;

/**
 * author : dayezi
 * data :2019/6/17
 * description:
 */
public class AddReportModel implements BaseModel<AddReportActivity> {
    private AddReportActivity activity;
    private MyService myService;

    @Inject
    public AddReportModel(AddReportActivity activity, MyService myService) {
        this.activity = activity;
        this.myService = myService;
    }

    @Override
    public AddReportActivity getBean() {
        return activity;
    }

    public void userList(MyObservable.OnSuccessListener<Data<List<UserListBean>>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, activity).build();
        myService.getUserUnder(SPUtils.getInstance().getInt(SpKeyConstant.LOGIN_UID_KEY)).subscribe(observable);

    }

    public void submit(AddReportParam addReportParam, MyObservable.OnSuccessListener<Data<Object>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, activity).build();
        myService.addReport(addReportParam).subscribe(observable);
    }

    public void edit(Map<String, Object> map, MyObservable.OnSuccessListener<Data<Object>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, activity).build();
        myService.editReport(map).subscribe(observable);
    }
}
