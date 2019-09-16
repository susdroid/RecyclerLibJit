package com.common.baselib.net.base;


import io.reactivex.ObservableTransformer;

/**
 * Created by Sudroid on 2018/12/12.
 */
public interface BaseView {

    <T> ObservableTransformer<T, T> bindToLifecycle();
}
