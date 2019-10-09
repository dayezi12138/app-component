package com.zh.xfz.mvp.presenter.activity;

import com.zh.xfz.mvp.contract.activity.FriendContract;
import com.zh.xfz.mvp.model.activity.FriendModel;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;

/**
 * author : dayezi
 * data :2019/9/24
 * description:
 */
public class FriendPresenter extends BasePresenter<FriendContract.FriendUI> implements FriendContract.Presenter {
    FriendModel model;

    @Inject
    public FriendPresenter(FriendModel model) {
        super(model);
        this.model = model;
    }


    private void request() {
        model.getUserFriendPageList(data -> {
            if (data.getCode() == 0) {
                view.get().successFriends(data.getRes(), model.getPageIndex() == 0, data.getRes().size() == model.getPageSize());
            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void refresh() {
        this.model.setPageIndex(0);
        request();
    }

    @Override
    public void loadMore() {
        int pageIndex = this.model.getPageIndex() + 1;
        this.model.setPageIndex(pageIndex);
        request();
    }

//    @Override
//    public void addGroup(Set<Integer> select, List<FriendInfo> mDateList) {
//        List<String> idList = new ArrayList<>();
//        StringBuilder name = new StringBuilder();
//        for (Integer position : select) {
//            idList.add(String.valueOf(mDateList.get(position).getID()));
//            name.append(mDateList.get(position)).append(FLAG_STR);
//        }
//        String ids = StringUtils.join(idList.toArray(), ",");
//        model.createGroup(ids, name.substring(0, name.length()), data -> {
//            if (data.getCode() == 0) view.get().createGroupSuccess();
//            else view.get().showMsg(data.getMsg());
//        });
//    }
}
