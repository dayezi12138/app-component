package com.zh.xfz.dagger.module.fragment;

import android.content.ContentResolver;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;

import com.zh.xfz.R;
import com.zh.xfz.bean.fragment.FriendInfo;
import com.zh.xfz.business.activity.GroupActivity;
import com.zh.xfz.business.activity.NewFriendActivity;
import com.zh.xfz.business.fragment.ContactFragment;
import com.zh.xfz.views.sideBar.PinyinComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import core.app.zh.com.core.annotation.FragmentScope;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/9/3
 * description:
 */
@Module
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

    private Uri imageTranslateUri(ContactFragment fragment, int resId) {
        Resources r = fragment.getMyActivity().getResources();
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + r.getResourcePackageName(resId) + "/"
                + r.getResourceTypeName(resId) + "/"
                + r.getResourceEntryName(resId));

        return uri;
    }
}
