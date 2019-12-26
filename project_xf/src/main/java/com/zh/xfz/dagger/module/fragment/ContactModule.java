package com.zh.xfz.dagger.module.fragment;

import android.content.ContentResolver;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zh.xfz.R;
import com.zh.xfz.bean.fragment.FriendInfo;
import com.zh.xfz.business.activity.FriendDetailActivity;
import com.zh.xfz.business.activity.GroupActivity;
import com.zh.xfz.business.activity.NewFriendActivity;
import com.zh.xfz.business.adapter.ContactSearchAdapter;
import com.zh.xfz.business.fragment.ContactFragment;
import com.zh.xfz.dagger.module.CommonFragmentModule;
import com.zh.xfz.views.sideBar.PinyinComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import core.app.zh.com.core.annotation.FragmentScope;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.view.ClearEditTextView;
import core.app.zh.com.core.view.MyPopupWindow;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/9/3
 * description:
 */
@Module(includes = CommonFragmentModule.class)
public class ContactModule {
    @FragmentScope
    @Provides
    public LinearLayoutManager layoutManager(ContactFragment fragment) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(fragment.getContext());
        return layoutManager;
    }

    @FragmentScope
    @Provides
    public Comparator comparator() {
        return new PinyinComparator();
    }

    @FragmentScope
    @Provides
    public BaseFragment baseFragment(ContactFragment fragment) {
        return fragment;
    }

    @FragmentScope
    @Provides
    public BaseActivity activity(ContactFragment fragment) {
        return fragment.getMyActivity();
    }

    @FragmentScope
    @Provides
    public List<FriendInfo> additionalItem(ContactFragment fragment) {
        List<FriendInfo> friendInfos = new ArrayList<>();
        FriendInfo newFriendItem = new FriendInfo();
        newFriendItem.setName(fragment.getResources().getString(R.string.fragment_friend_new_str));
        newFriendItem.setPath(NewFriendActivity.AROUTER_PATH);
        newFriendItem.setLetters("☆");
        newFriendItem.setUserIcon(imageTranslateUri(fragment, R.drawable.ic_add_friend_1).toString());
        FriendInfo groupItem = new FriendInfo();
        groupItem.setName(fragment.getResources().getString(R.string.fragment_friend_group_str));
        groupItem.setPath(GroupActivity.AROUTER_PATH);
        groupItem.setLetters("☆");
        groupItem.setUserIcon(imageTranslateUri(fragment, R.drawable.ic_group_).toString());
        friendInfos.add(groupItem);
        friendInfos.add(newFriendItem);
        return friendInfos;
    }

    @FragmentScope
    @Provides
    public MyPopupWindow popupWindow(ContactFragment fragment, ContactSearchAdapter searchFriendAdapter) {
        View view = LayoutInflater.from(fragment.getActivity()).inflate(R.layout.view_search_contact, null);
        RecyclerView mRecyclerView = view.findViewById(R.id.recycler);
        ClearEditTextView clearEditTextView = view.findViewById(R.id.clear_tv);
        clearEditTextView.setmClearDrawableRecourse(R.drawable.ic_clear_no_stroger_grey);
        clearEditTextView.addTextChangedListener(fragment);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(fragment.getActivity()));
        mRecyclerView.setAdapter(searchFriendAdapter);
        MyPopupWindow popupWindow = new MyPopupWindow.Builder(view, fragment.getActivity())
                .animationStyle(R.style.pop_animation_)
                .build();
        searchFriendAdapter.setOnItemClickListener((adapter, view1, position) -> {
            ARouter.getInstance().build(FriendDetailActivity.AROUTER_PATH).withSerializable(FriendDetailActivity.FRIEND_DETAIL_KEY, searchFriendAdapter.getData().get(position)).navigation();
            clear(clearEditTextView, popupWindow, searchFriendAdapter);
        });
        clearEditTextView.setOnClickImageListener(() -> {
            clearEditTextView.setText("");
            searchFriendAdapter.getData().clear();
            searchFriendAdapter.notifyDataSetChanged();
        });
        view.findViewById(R.id.cancel_btn).setOnClickListener(v -> clear(clearEditTextView, popupWindow, searchFriendAdapter));
        popupWindow.setOnDismissListener(() -> {
            WindowManager.LayoutParams lp = fragment.getMyActivity().getWindow().getAttributes();
            lp.alpha = 1f;
            fragment.getMyActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            fragment.getMyActivity().getWindow().setAttributes(lp);
            clear(clearEditTextView, popupWindow, searchFriendAdapter);
        });
        return popupWindow;
    }

    private void clear(ClearEditTextView clearEditTextView, MyPopupWindow myPopupWindow, ContactSearchAdapter searchFriendAdapter) {
        searchFriendAdapter.getData().clear();
        searchFriendAdapter.notifyDataSetChanged();
        clearEditTextView.setText("");
        if (myPopupWindow.isShowing()) myPopupWindow.dismiss();
    }

    @FragmentScope
    @Provides
    public ContactSearchAdapter contactSearchAdapter() {
        ContactSearchAdapter adapter = new ContactSearchAdapter();
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        return adapter;
    }

    private Uri imageTranslateUri(ContactFragment fragment, int resId) {
        Resources r = fragment.getMyActivity().getResources();
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + r.getResourcePackageName(resId) + "/"
                + r.getResourceTypeName(resId) + "/"
                + r.getResourceEntryName(resId));

        return uri;
    }
}
