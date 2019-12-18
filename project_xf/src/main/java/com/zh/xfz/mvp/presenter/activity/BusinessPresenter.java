//package com.zh.xfz.mvp.presenter.activity;
//
//import com.zh.xfz.bean.activity.Industry;
//import com.zh.xfz.bean.other.Data;
//import com.zh.xfz.mvp.contract.activity.BusinessContract;
//import com.zh.xfz.mvp.model.activity.BusinessModel;
//
//import java.util.List;
//
//import javax.inject.Inject;
//
//import core.app.zh.com.core.base.BasePresenter;
//import core.app.zh.com.core.listener.observable.ObservableListener;
//
///**
// * author : dayezi
// * data :2019/7/29
// * description:
// */
//@Deprecated
//public class BusinessPresenter extends BasePresenter<BusinessContract.BusinessUI> implements BusinessContract.Presenter {
//    private BusinessModel model;
//
//    @Inject
//    public BusinessPresenter(BusinessModel model) {
//        super(model);
//        this.model = model;
//    }
//
//    @Override
//    public void getIndustry() {
//        model.getIndustry((ObservableListener.SuccessListener<Data<List<Industry>>>) listData -> {
//            if (listData.getCode() == 0) {
//                view.get().success(listData.getRes());
//            } else view.get().showMsg(listData.getMsg());
//        });
//    }
//
//    @Override
//    public void createTenant(String tenantName, String industryIDs) {
//        model.createTenant(tenantName, industryIDs, (ObservableListener.SuccessListener<Data<Object>>) data -> {
//            if (data.getCode() == 0) view.get().successSubmit();
//            else view.get().showMsg(data.getMsg());
//
//        });
//
//    }
//
//    @Override
//    public void existTenantName(String tenantName, String industryIDs) {
//        model.existTenantName(tenantName, (ObservableListener.SuccessListener<Data<Object>>) data -> {
//            if (data.getCode() == 0) createTenant(tenantName, industryIDs);
//            else view.get().showMsg(data.getMsg());
//        });
//    }
//}
