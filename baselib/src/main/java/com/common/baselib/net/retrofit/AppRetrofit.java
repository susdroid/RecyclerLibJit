package com.common.baselib.net.retrofit;

import com.common.baselib.BuildConfig;
import com.common.baselib.constants.ProjectConfig;
import com.common.baselib.net.api.UrlApi;
import com.common.baselib.net.gson.GsonConverterFactory;
import com.common.baselib.net.interceptor.HttpLoggingInterceptor;
import com.common.baselib.net.proxy.ApiServiceProxy;
import com.common.baselib.net.proxy.ProxyHandler;
import com.common.baselib.net.retrofitManager.RetrofitUrlManager;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by cyq on 16/7/1.
 */
public class AppRetrofit {

    private UrlApi apiService;
    private static Retrofit retrofit;

    protected UrlApi getApiService() {
        if (apiService == null) {
            //方法1
//            apiService = getRetrofit().create(UrlApi.class);
            //方法2:走自定义代理
            ProxyHandler proxyHandler = new ProxyHandler();
            ApiServiceProxy apiServiceProxy = new ApiServiceProxy(getRetrofit(), proxyHandler);
            apiService = apiServiceProxy.getProxy(UrlApi.class);
        }
        return apiService;
    }

    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> {
                if (!message.contains("OK")) {
                    if (BuildConfig.DEBUG) {
                        if (message.contains("r") && message.contains("msg")) {
                            Logger.json(message);
                        } else {
                            Logger.i(message);
                        }
                    } else {
                        Logger.e(message);
                    }
                }
            });

            OkHttpClient client = RetrofitUrlManager.getInstance().with(new OkHttpClient.Builder())
                    .addInterceptor(httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.CYQ))
                    .readTimeout(20, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .build();


            retrofit = new Retrofit.Builder()
                    .baseUrl(ProjectConfig.preUrl)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
