package com.common.baselib.net.base;

import com.common.baselib.utils.Preconditions;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by Sudroid on 2018/12/12.
 */
public abstract class BasePresenter<V extends BaseView> implements IPresenter<V> {
    /**
     * 由于Presenter 经常性的持有Activity 的强引用，如果在一些请求结束之前Activity 被销毁了，
     * Activity对象将无法被回收，此时就会发生内存泄露。
     * 这里我们使用虚引用和泛型来对MVP中的内存泄漏问题进行改良。
     */
    protected Reference<V> mView;
    protected BaseModel baseModel;

    protected V getView() {
        return mView.get();
    }


    public void bindView(V view) {
        Preconditions.checkNotNull(view, "%s cannot be null", BaseView.class.getName());

        baseModel = BaseModel.getInstance();
        attachView(view);
    }

    @Override
    public void attachView(V view) {
        this.mView = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }
}
