package com.common.baselib.net.proxy;

import com.common.baselib.net.gson.APIException;
import com.common.baselib.net.interceptor.HttpLoggingInterceptor;
import com.orhanobut.logger.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;


/**
 * Created by cyq on 16/8/15.
 * 参考文章:http://alighters.com/blog/2016/05/02/rxjava-plus-retrofitshi-xian-wang-luo-dai-li/
 * 思路:
 * 在遇到sid过期的时候,调用更新sid的接口,更新后重复调用原来失败的接口.过程对于用户透明
 * 困难:
 * 1.sid全局过期监听,重写convert,发生指定code,抛出自定义异常(gsonConverter)
 * 2.接口异常监听,自定义请求的Proxy (ProxyHandler),在invoke方法监听
 * 3.失败调用跟新接口,重新调用失败接口  retryWhen
 * retryWhen结构体重调用更新sid接口,跟新成功,返回一个新的Observable.方法会自动重新调用失败的方法
 * 4.重新调用失败方法如果不处理参数,完全是失败的那时候的拷贝,所以接口的入参还是旧的sid.此时要修改参数(updateTokenValid)
 */
public class ProxyHandler implements InvocationHandler {

    private Object mObject;
    private String sid, at, uid;
    private long changeSidTime = 0;
    private boolean mIsNeedRefreshSid = false;
    private final long CHANGE_SID_TIME_INTERVAL = 60000;


    public void setObject(Object obj) {
        this.mObject = obj;
    }

    @Override
    public Object invoke(Object proxy, final Method method, final Object[] args) throws Throwable {
        return Observable.just(true).flatMap(o -> {
            try {
                updateTokenValid(method, args);
                return (Observable<?>) method.invoke(mObject, args);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return Observable.just(new APIException(-100000, "ProxyHandler-try-出异常"));
        }).retryWhen(throwableObservable ->
                throwableObservable.flatMap((Function<Throwable, ObservableSource<?>>) throwable -> {
                    APIException e = (APIException) throwable;
                    if (e.getCode() == -100010001) {
                        return updateSid(throwable);
                    } else {
                        return Observable.error(throwable);
                    }
                }));
    }

    /**
     * 更新sid 根据条件判断,调用更新接口,返回新sid
     *
     * @param throwable
     * @return
     */
    private Observable<?> updateSid(Throwable throwable) {
//        synchronized (this) {
//            if (new Date().getTime() - changeSidTime < CHANGE_SID_TIME_INTERVAL) {
//                mIsNeedRefreshSid = true;
//                return Observable.error(throwable);
//            } else {
//                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> {
//                    if (!message.contains("OK")) {
//                        Logger.e(message);
//                    }
//                });
//
//
//                OkHttpClient client = new OkHttpClient.Builder()
//                        .addInterceptor(httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.CYQ))
//                        .retryOnConnectionFailure(true)
//                        .connectTimeout(10, TimeUnit.SECONDS)
//                        .build();
//
//                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl(Constants.ONLINE.DEPOSIT_STATUS ? ProjectConfig.t_preurl : ProjectConfig.preUrl + "index_wx.php/Auth/sid/")
//                        .client(client)
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                        .build();
//
//                UrlApi apiService = retrofit.create(UrlApi.class);
//
//                Logger.e("更新前的sid---->" + Constants.ONLINE.SID);
//
//                //uid
//                if (Utility.isEmpty(PrefHelper.getUid(YcjfApplication.mContext))) {
//                    uid = Constants.ONLINE.UID;
//                } else {
//                    uid = PrefHelper.getUid(YcjfApplication.mContext);
//                }
//                //at
//                if (Utility.isEmpty(PrefHelper.getAccessToken(YcjfApplication.mContext))) {
//                    at = Constants.ONLINE.AT;
//                } else {
//                    at = PrefHelper.getAccessToken(YcjfApplication.mContext);
//                }
//                //sid
//                if (Utility.isEmpty(PrefHelper.getSid(YcjfApplication.mContext))) {
//                    sid = Constants.ONLINE.SID;
//                } else {
//                    sid = PrefHelper.getSid(YcjfApplication.mContext);
//                }
//                apiService.upDateSID(uid, at, sid)
//                        .subscribe(sidBean -> {
//                            KLog.e(sidBean);
//                            PrefHelper.setSid(YcjfApplication.mContext, sidBean.getSid());
//                            Constants.ONLINE.SID = sidBean.getSid();
//                            mIsNeedRefreshSid = true;
//                        }, throwable1 -> {
//                            KLog.e(throwable1);
//                            mIsNeedRefreshSid = false;
//                        });
//            }
//
//
//            changeSidTime = new Date().getTime();
//            Logger.e("更新后的sid---->" + Constants.ONLINE.SID);
//            if (mIsNeedRefreshSid) {
                return Observable.just(true);
//            } else {
//                return Observable.error(throwable);
//            }
//        }
    }


    /**
     * 更新sid 根据条件判断,调用更新接口,返回新sid
     *
     * @param throwable
     * @return
     */
//    private Observable<?> updateAt(Throwable throwable) {
//        synchronized (this) {
//            if (new Date().getTime() - changeSidTime < CHANGE_SID_TIME_INTERVAL) {
//                mIsNeedRefreshSid = true;
//                return Observable.error(throwable);
//            } else {
//                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> {
//                    if (!message.contains("OK")) {
//                        Logger.e(message);
//                    }
//                });
//
//
//                OkHttpClient client = new OkHttpClient.Builder()
//                        .addInterceptor(httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.CYQ))
//                        .retryOnConnectionFailure(true)
//                        .connectTimeout(10, TimeUnit.SECONDS)
//                        .build();
//
//                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl(Constants.ONLINE.DEPOSIT_STATUS ? ProjectConfig.t_preurl : ProjectConfig.preUrl + "index_wx.php/Auth/accessToken/")
//                        .client(client)
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                        .build();
//
//                UrlApi apiService = retrofit.create(UrlApi.class);
//
//                Logger.e("更新前的at---->" + Constants.ONLINE.AT);
//
//
//                apiService.getToken(ProjectConfig.appid, ProjectConfig.secret)
//                        .subscribe(accessTokenBean -> {
//                            KLog.e(accessTokenBean);
//                            PrefHelper.setAccessToken(YcjfApplication.mContext, accessTokenBean.getAt());
//                            Constants.ONLINE.AT = accessTokenBean.getAt();
//                        }, throwable1 -> KLog.e(throwable1));
//            }
//
//
//            changeSidTime = new Date().getTime();
//            Logger.e("更新后的at---->" + Constants.ONLINE.AT);
//            if (mIsNeedRefreshSid) {
//                return Observable.just(true);
//            } else {
//                return Observable.error(throwable);
//            }
//        }
//    }

    /**
     * 跟新sid入参,根据新sid,在调用的时候替换成新sid.
     *
     * @param method
     * @param args
     */
    private void updateTokenValid(Method method, Object[] args) {
//        if (mIsNeedRefreshSid && !TextUtils.isEmpty(Constants.ONLINE.SID)) {
//            Annotation[][] annotationsArray = method.getParameterAnnotations();
//            Annotation[] annotations = null;
//            Annotation annotation = null;
//            if (annotationsArray != null && annotationsArray.length > 0) {
//                for (int i = 0; i < annotationsArray.length; i++) {
//                    annotations = annotationsArray[i];
//                    for (int j = 0; j < annotations.length; j++) {
//                        annotation = annotations[j];
//                        if (annotation instanceof Field) {
//                            if ("sid".equals(((Field) annotation).value())) {
//                                args[i] = PrefHelper.getSid(YcjfApplication.mContext);
//                            }
//                        }
//                    }
//                }
//            }
//        }
    }

}
