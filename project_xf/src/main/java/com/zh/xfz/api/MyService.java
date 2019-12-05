package com.zh.xfz.api;

import com.zh.xfz.bean.activity.Account;
import com.zh.xfz.bean.activity.GroupInfo;
import com.zh.xfz.bean.activity.GroupListInfo;
import com.zh.xfz.bean.activity.Industry;
import com.zh.xfz.bean.activity.SearchFriend;
import com.zh.xfz.bean.activity.TargetUserInfo;
import com.zh.xfz.bean.activity.UserInfo;
import com.zh.xfz.bean.activity.ValidAccount;
import com.zh.xfz.bean.activity.ValidSmsCode;
import com.zh.xfz.bean.fragment.FriendInfo;
import com.zh.xfz.bean.other.Data;

import java.util.List;
import java.util.Map;

import core.app.zh.com.core.annotation.ApiAnnotation;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * author : dayezi
 * data :2019/7/23
 * description:
 */
public interface MyService {

    @ApiAnnotation("登录接口")
    @FormUrlEncoded
    @POST("User/Login")
    Observable<Data<Account>> login(@Field("account") String userName, @Field("password") String password, @Field("timeStamp") String timeStamp);

    @ApiAnnotation("验证用户接口")
    @FormUrlEncoded
    @POST("User/ExistAccount")
    Observable<ValidAccount> validAccount(@Field("account") String account);

    @ApiAnnotation("获取验证码")
    @FormUrlEncoded
    @POST("Sms/GetCode")
    Observable<ValidSmsCode> getCode(@Field("mobile") String phone, @Field("type") int type);

    @ApiAnnotation("注册")
    @FormUrlEncoded
    @POST("User/Register")
    Observable<Data<Account>> register(@Field("mobile") String phone, @Field("code") String code, @Field("password") String password, @Field("password2") String password2, @Field("timeStamp") String timeStamp);

    @ApiAnnotation("短信登录")
    @FormUrlEncoded
    @POST("User/CodeLogin")
    Observable<Data<Account>> codeLogin(@Field("mobile") String phone, @Field("code") String code, @Field("timeStamp") String timeStamp);

    @ApiAnnotation("获取用户信息")
    @GET("User/GetUserInfo")
    Observable<Data<UserInfo>> getUserInfo(@Query("userid") String phone, @Query("timeStamp") String timeStamp);

    @ApiAnnotation("获取行业类别")
    @GET("tenant/GetIndustry")
    Observable<Data<List<Industry>>> getIndustry();

    @ApiAnnotation("创建公司")
    @POST("Tenant/CreateTenant")
    @FormUrlEncoded
    Observable<Data<Object>> createTenant(@FieldMap Map<String, String> paramMap);

    @ApiAnnotation("存在公司")
    @POST("Tenant/ExistTenantName")
    @FormUrlEncoded
    Observable<Data<Object>> existTenantName(@Field("tenantName") String tenantName, @Field("userid") String userId, @Field("timeStamp") String timeStamp);

    @ApiAnnotation("好友列表")
    @GET("AddressBook/GetUserFriendPageList")
    Observable<Data<List<FriendInfo>>> getUserFriendPageList(@QueryMap Map<String, String> paramMap);

    @ApiAnnotation("新朋友")
    @GET("AddressBook/GetNewFriend")
    Observable<Data<List<FriendInfo>>> getNewFriend(@QueryMap Map<String, String> paramMap);

    @ApiAnnotation("添加好友")
    @FormUrlEncoded
    @POST("AddressBook/ApplyFriend")
    Observable<Data<Object>> applyFriend(@FieldMap Map<String, String> paramMap);

    @ApiAnnotation("同意")
    @FormUrlEncoded
    @POST("AddressBook/FriendOperate")
    Observable<Data<Object>> friendOperate(@FieldMap Map<String, String> paramMap);

    @ApiAnnotation("查找好友")
    @GET("AddressBook/SearchFriend")
    Observable<Data<List<SearchFriend>>> searchFriend(@QueryMap Map<String, String> paramMap);

    @ApiAnnotation("删除好友")
    @FormUrlEncoded
    @POST("AddressBook/FriendDelete")
    Observable<Data<Object>> deleteFriend(@FieldMap Map<String, String> paramMap);

    @ApiAnnotation("修改好友备注")
    @FormUrlEncoded
    @POST("AddressBook/UpdateFriendName")
    Observable<Data<Object>> updateFriend(@FieldMap Map<String, String> paramMap);

    @ApiAnnotation("获取群列表")
    @GET("AddressBook/GetGroupList")
    Observable<Data<List<GroupListInfo>>> getGroupList(@QueryMap Map<String, String> paramMap);

    @ApiAnnotation("创建群组")
    @FormUrlEncoded
    @POST("AddressBook/CreateGroup")
    Observable<Data<Object>> createGroup(@FieldMap Map<String, String> paramMap);

    @ApiAnnotation("添加群员")
    @FormUrlEncoded
    @POST("AddressBook/InviteGroupMember")
    Observable<Data<Object>> inviteGroupMember(@FieldMap Map<String, String> paramMap);

    @ApiAnnotation("获取群组人员")
    @GET("AddressBook/GetGroupMemberList")
    Observable<Data<List<GroupInfo>>> getGroupMemberList(@QueryMap Map<String, String> paramMap);

    @ApiAnnotation("修改密码")
    @FormUrlEncoded
    @POST("User/UpdatePassWord")
    Observable<Data<Object>> updatePassWord(@FieldMap Map<String, String> paramMap);

    @ApiAnnotation("获取群组详细信息")
    @GET("AddressBook/GetGroupInfo")
    Observable<Data<GroupListInfo>> getGroupInfo(@QueryMap Map<String, String> paramMap);

    @ApiAnnotation("解散群")
    @FormUrlEncoded
    @POST("AddressBook/DismissGroup")
    Observable<Data<Object>> dismissGroup(@FieldMap Map<String, String> paramMap);

    @ApiAnnotation("退出群组")
    @FormUrlEncoded
    @POST("AddressBook/QuitGroup")
    Observable<Data<Object>> quitGroup(@FieldMap Map<String, String> paramMap);

    @ApiAnnotation("修改群名称")
    @FormUrlEncoded
    @POST("AddressBook/UpdateGroupName")
    Observable<Data<Object>> updateGroupName(@FieldMap Map<String, String> paramMap);

    @ApiAnnotation("获取群员详细信息")
    @GET("AddressBook/GetGroupMemberInfo")
    Observable<Data<GroupInfo>> getGroupMemberInfo(@QueryMap Map<String, String> paramMap);

    @ApiAnnotation("获取好友详细信息")
    @GET("User/GetTargetUserInfo")
    Observable<Data<TargetUserInfo>> getTargetUserInfo(@QueryMap Map<String, String> paramMap);

    @ApiAnnotation("找回密码")
    @POST("User/ForgetPassWord")
    @FormUrlEncoded
    Observable<Data<Object>> forgetPassWord(@FieldMap Map<String, String> paramMap);

    @ApiAnnotation("修改群员备注")
    @POST("AddressBook/UpdateGroupMemberName")
    @FormUrlEncoded
    Observable<Data<Object>> updateGroupMemberName(@FieldMap Map<String, String> paramMap);

    @ApiAnnotation("转让管理员")
    @POST("AddressBook/TransferGroupAdministrator")
    @FormUrlEncoded
    Observable<Data<Object>> transferGroupAdministrator(@FieldMap Map<String, String> paramMap);

    @ApiAnnotation("修改用户信息")
    @POST("User/UpdateUserInfo")
    @FormUrlEncoded
    Observable<Data<Object>> updatePersonName(@FieldMap Map<String, String> paramMap);

    @ApiAnnotation("登录后绑定微信")
    @POST("User/BindWxOpenID")
    @FormUrlEncoded
    Observable<Data<Object>> bindWxOpenID(@FieldMap Map<String, String> paramMap);

    @ApiAnnotation("微信检查并登录")
    @POST("User/WxCheckAndLogin")
    @FormUrlEncoded
    Observable<Data<Object>> wxCheckAndLogin(@FieldMap Map<String, String> paramMap);
    @ApiAnnotation("解除微信绑定")
    @POST("User/RelieveWXBind")
    @FormUrlEncoded
    Observable<Data<Object>> relieveWXBind(@FieldMap Map<String, String> paramMap);
}