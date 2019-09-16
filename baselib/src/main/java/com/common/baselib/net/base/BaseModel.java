package com.common.baselib.net.base;


import com.common.baselib.net.bean.AccessTokenBean;
import com.common.baselib.net.retrofit.AppRetrofit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sudroid on 2018/5/31.
 */
public class BaseModel extends AppRetrofit {
    private BaseModel() {
    }
    private static final BaseModel instance = new BaseModel();

    //这里提供了一个供外部访问本class的静态方法，可以直接访问
    public static BaseModel getInstance() {
        return instance;
    }
    //获取登录token
    public Observable<AccessTokenBean> getToken(String app_id, String secret) {
        return getApiService().getToken(app_id, secret)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 首页接口
     */

//    //首页
//    public Observable<IndexBean> tot(Map<String, String> map) {
//        return getApiService().tot(map)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }


//    //上传头像
//    public Observable<ResponseBaseBean> upAvatar(Map<String, RequestBody> map, MultipartBody.Part file) {
//        return getApiService().upAvatar(map, file)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }


    /**
     * 登录-注册 接口
     */

    //登录
//    public Observable<LoginBean> login(String at, String username, String password) {
//        return getApiService().getSD(at, username, ProjectConfig.os, ProjectConfig.version)
//                .subscribeOn(Schedulers.io())
//                .flatMap(new Func1<CDBean, Observable<LoginBean>>() {
//                    @Override
//                    public Observable<LoginBean> call(CDBean cdBean) {
//                        String sid = cdBean.getSid();
//                        Constants.ONLINE.SID = sid;
//                        KLog.e("sid--2-->" + sid);
//                        String pasmd5 = MD5Util.MD5(MD5Util.MD5(MD5Util.MD5(password) + cdBean.getSalt()) + cdBean.getCd());
//                        return getApiService().login(at, sid, username, pasmd5, ProjectConfig.os, ProjectConfig.version)
//                                .subscribeOn(Schedulers.io());
//                    }
//
//                }).observeOn(AndroidSchedulers.mainThread());
//    }

}
