package zh.com.jyu.mvp.model.activity;

import com.blankj.utilcode.util.SPUtils;

import java.util.List;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseModel;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.api.MyService;
import zh.com.jyu.bean.activity.CraftDetailBean;
import zh.com.jyu.bean.activity.UserListBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.business.activity.CraftDetailActivity;
import zh.com.jyu.constans.SpKeyConstant;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
public class CraftDetailModel implements BaseModel<CraftDetailActivity> {
    private CraftDetailActivity activity;
    private MyService myService;

    @Inject
    public CraftDetailModel(CraftDetailActivity activity, MyService myService) {
        this.activity = activity;
        this.myService = myService;
    }

    @Override
    public CraftDetailActivity getBean() {
        return activity;
    }

    public void getProduceCraftsReceipt(int id, MyObservable.OnSuccessListener<Data<CraftDetailBean>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, activity).build();
        myService.getProduceCraftsReceipt(id).subscribe(observable);
    }

    public void produceCraftsReceiptOperation(int status, int craftsReceiptID, MyObservable.OnSuccessListener<Data<Object>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, activity).build();
        switch (status) {
            case 0:
                myService.produceCraftsReceiptCanceled(craftsReceiptID, SPUtils.getInstance().getInt(SpKeyConstant.LOGIN_UID_KEY)).subscribe(observable);
                break;
            case 1:
                myService.produceCraftsReceiptProcessed(craftsReceiptID, SPUtils.getInstance().getInt(SpKeyConstant.LOGIN_UID_KEY)).subscribe(observable);
                break;
            case 2:
                myService.produceCraftsReceiptBacktrack(craftsReceiptID).subscribe(observable);
                break;

        }
    }

    public void userList(MyObservable.OnSuccessListener<Data<List<UserListBean>>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, activity).build();
        myService.getUserUnder(SPUtils.getInstance().getInt(SpKeyConstant.LOGIN_UID_KEY)).subscribe(observable);

    }
}
