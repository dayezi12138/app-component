package zh.com.jyu.api;

import java.util.List;
import java.util.Map;

import core.app.zh.com.core.annotation.ApiAnnotation;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import zh.com.jyu.bean.activity.AddReportParam;
import zh.com.jyu.bean.activity.CraftBean;
import zh.com.jyu.bean.activity.CraftDetailBean;
import zh.com.jyu.bean.activity.GoodListBean;
import zh.com.jyu.bean.activity.GoodsDetail;
import zh.com.jyu.bean.activity.GroupBean;
import zh.com.jyu.bean.activity.LoginBean;
import zh.com.jyu.bean.activity.OrderDetailBean;
import zh.com.jyu.bean.activity.PickingBean;
import zh.com.jyu.bean.activity.RelationOrderBean;
import zh.com.jyu.bean.activity.ReportListBean;
import zh.com.jyu.bean.activity.UserList;
import zh.com.jyu.bean.activity.UserListBean;
import zh.com.jyu.bean.fragment.BulletinBoard;
import zh.com.jyu.bean.fragment.PlanBean;
import zh.com.jyu.bean.other.Data;

/**
 * author : dayezi
 * data :2019/6/11
 * description:
 */
public interface MyService {

    @ApiAnnotation("登录接口")
    @FormUrlEncoded
    @POST("User/Login")
    Observable<Data<LoginBean>> login(@Field("userName") String userName, @Field("password") String password);


    @ApiAnnotation("计划列表")
    @GET("ProduceReceipt/GetPlanPager")
    Observable<Data<List<PlanBean>>> getPlanPager(@QueryMap Map<String, Object> map);

    @ApiAnnotation("计划详情")
    @GET("ProduceReceipt/GetStockOutInfo")
    Observable<Data<PickingBean>> getStockOutInfo(@Query("ProduceReceiptID") int id);

    @ApiAnnotation("获取某个计划下生产货品单")
    @GET("ProduceReceipt/GetProduceGoodsReceiptList")
    Observable<Data<List<GoodListBean>>> getProduceGoodsReceiptList(@Query("planId") int id);

    @ApiAnnotation("获取生产单明细")
    @GET("ProduceReceipt/GetProduceGoodsReceipt")
    Observable<Data<GoodsDetail>> getProduceGoodsReceipt(@Query("id") int id);

    @ApiAnnotation("获取某个生产货品单下的工艺单列表")
    @GET("ProduceReceipt/GetProduceCraftsReceiptList")
    Observable<Data<List<CraftBean>>> getProduceCraftsReceiptList(@QueryMap Map<String, Object> map);


    @ApiAnnotation("获取工艺单明细")
    @GET("ProduceReceipt/GetProduceCraftsReceiptListPager")
    Observable<Data<List<CraftBean>>> getProduceCraftsReceiptListPager(@QueryMap Map<String, Object> map);

    @ApiAnnotation("获取工艺单明细")
    @GET("ProduceReceipt/GetProduceCraftsReceipt")
    Observable<Data<CraftDetailBean>> getProduceCraftsReceipt(@Query("id") int id);


    @ApiAnnotation("获取某个生产货品单下的工艺单列表")
    @GET("ProduceReceipt/GetTradeList")
    Observable<Data<List<RelationOrderBean>>> getTradeList(@QueryMap Map<String, Object> map);

    @ApiAnnotation("工艺单强制完成")
    @GET("ProduceReceipt/ProduceCraftsReceiptCanceled")
    Observable<Data<Object>> produceCraftsReceiptCanceled(@Query("craftsReceiptID") int craftsReceiptID, @Query("UserID") int userId);


    @ApiAnnotation("工艺单完成")
    @GET("ProduceReceipt/ProduceCraftsReceiptProcessed")
    Observable<Data<Object>> produceCraftsReceiptProcessed(@Query("craftsReceiptID") int craftsReceiptID, @Query("UserID") int userId);

    @ApiAnnotation("工艺单退回")
    @GET("ProduceReceipt/ProduceCraftsReceiptBacktrack")
    Observable<Data<Object>> produceCraftsReceiptBacktrack(@Query("craftsReceiptID") int craftsReceiptID);

    @ApiAnnotation("获取汇报列表")
    @GET("ProduceReceipt/GetReportList")
    Observable<Data<ReportListBean>> getReportList(@QueryMap Map<String, Object> map);

    @ApiAnnotation("选择客户")
    @GET("ProduceReceipt/GetCrewUnder")
    Observable<Data<List<UserListBean>>> getUserUnder(@Query("UserID") int userId);

    @ApiAnnotation("根据主任ID获取管辖人员")
    @GET("ProduceReceipt/GetUserUnder")
    Observable<Data<List<UserList.MembersBean>>> getUserUnder1(@QueryMap Map<String, Object> map);

    @ApiAnnotation("新增汇报")
    @POST("ProduceReceipt/AddReport")
    Observable<Data<Object>> addReport(@Body AddReportParam addReportParam);

    @ApiAnnotation("编辑汇报")
    @FormUrlEncoded
    @POST("ProduceReceipt/EditReport")
    Observable<Data<Object>> editReport(@FieldMap Map<String, Object> map);

    @ApiAnnotation("删除汇报")
    @FormUrlEncoded
    @POST("ProduceReceipt/DelReport")
    Observable<Data<Object>> delReport(@Field("ReportID") int reportID, @Field("userId") int userId);


    @ApiAnnotation("选择客户")
    @GET("ProduceReceipt/GetDailyBoard")
    Observable<Data<List<BulletinBoard>>> getDailyBoard(@Query("keyword") String keyword);

    @ApiAnnotation("根据班组ID获取班组下成员")
    @GET("ProduceReceipt/GetCrewsMember")
    Observable<Data<List<UserList>>> getCrewsMember(@QueryMap Map<String, Object> map);

    @ApiAnnotation("根据主任ID获取管辖班组")
    @GET("ProduceReceipt/GetMyCrews")
    Observable<Data<List<GroupBean>>> getMyCrews(@QueryMap Map<String, Object> map);

    @ApiAnnotation("根据所选人员添加一个班组到主任名下")
    @FormUrlEncoded
    @POST("ProduceReceipt/AddCrew")
    Observable<Data<Object>> addCrew(@Field("UserID") int userId, @Field("StaffIDs") String staffIDs);

    @ApiAnnotation("修改班组")
    @FormUrlEncoded
    @POST("ProduceReceipt/EditCrew")
    Observable<Data<Object>> editCrew(@FieldMap Map<String, Object> map);

    @ApiAnnotation("修改班组")
    @FormUrlEncoded
    @POST("ProduceReceipt/DelCrew")
    Observable<Data<Object>> delCrew(@Field("CrewID") int crewID);

    @ApiAnnotation("订单详情")
    @GET("Trade/GetTradeDetail")
    Observable<Data<OrderDetailBean>> getTradeDetail(@Query("tradeId") int tradeId);

    @ApiAnnotation("订单详情")
    @GET("ProduceReceipt/GetProduceGoodsReceiptPageList")
    Observable<Data<List<GoodListBean>>> getProduceGoodsReceiptPageList(@QueryMap Map<String, Object> map);
}