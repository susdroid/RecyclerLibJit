package com.common.baselib.net.api;


import com.common.baselib.net.bean.AccessTokenBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by cyq on 16/7/1.
 */
public interface UrlApi {

    //获取登录token
    @FormUrlEncoded
    @POST("index_wx.php/Auth/accessToken")
    Observable<AccessTokenBean> getToken(
            @Field("app_id") String app_id,
            @Field("secret") String secret);

    /**
     * 首页接口
     */

//    //首页
//    @FormUrlEncoded
//    @POST("index_wx.php/VHome/home_page")
//    Observable<IndexBean> tot(
//            @FieldMap Map<String, String> map);


//
//    //风险测评结果
//    @FormUrlEncoded
//    @POST("index_wx.php/RiskAssess/save")
//    Observable<RiskResultBean> riskResult(
//            @Field("options[]") List<String> idList,
//            @FieldMap Map<String, String> map);

//    //上传头像
//    @Multipart
//    @POST("index_wx.php/App/UpHeadpic")
//    Observable<ResponseBaseBean> upAvatar(
//            @PartMap Map<String, RequestBody> map,
//            @Part MultipartBody.Part file);

}
