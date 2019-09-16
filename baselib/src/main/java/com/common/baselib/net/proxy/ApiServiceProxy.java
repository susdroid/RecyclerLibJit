package com.common.baselib.net.proxy;

import java.lang.reflect.Proxy;

import retrofit2.Retrofit;

/**
 * Created by cyq on 16/8/15.
 */
public class ApiServiceProxy {

   private Retrofit mRetrofit;

    private  ProxyHandler mProxyHandler;

    public ApiServiceProxy(Retrofit retrofit, ProxyHandler proxyHandler) {
        mRetrofit = retrofit;
        mProxyHandler = proxyHandler;
    }

    public <T> T getProxy(Class<T> tClass) {
        T t = mRetrofit.create(tClass);
        mProxyHandler.setObject(t);
        return (T) Proxy.newProxyInstance(tClass.getClassLoader(), new Class<?>[] { tClass }, mProxyHandler);
    }

}
