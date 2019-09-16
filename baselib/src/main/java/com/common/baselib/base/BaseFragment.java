package com.common.baselib.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.baselib.R;
import com.common.baselib.net.base.BasePresenter;
import com.common.baselib.utils.TUtil;
import com.common.baselib.view.loadview.load.LoadViewHelper;
import com.trello.rxlifecycle3.components.support.RxFragment;

import androidx.appcompat.app.AlertDialog;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.internal.CustomAdapt;

public abstract class BaseFragment<P extends BasePresenter> extends RxFragment implements CustomAdapt {

    private View view;
    private TextView mess;
    private Dialog loadingDialog;
    public Activity mActivity;

    public int pageIndex = 1;
    public int pageSize = 10;
    public int refereshTime = 800;

    protected View rootView;
    protected View loadingView;
    protected View footerView;
    protected LoadViewHelper helper;

    protected boolean isFirstEnter = true;  //全局是否开启自动刷新
    protected boolean isNoDataNext = false;  //默认下页有数据

    protected Unbinder mUnbinder;
    protected P mPresenter;

    private boolean isViewPrepared; // 标识fragment视图已经初始化完毕
    private boolean hasFetchData; // 标识已经触发过懒加载数据

    /**
     * 初始化View
     */
    public abstract void initView();

    /**
     * 获取布局文件
     */
    protected abstract int getLayoutResource();

    /**
     * 初始化presenter
     */
    protected abstract void initPresenter();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        AutoSize.autoConvertDensity(getActivity(), 667, true);
        if (rootView == null) {
            rootView = paramLayoutInflater.inflate(getLayoutResource(), paramViewGroup, false);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        mPresenter = TUtil.getT(this, 0);
        initPresenter();
        mUnbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewPrepared = true;
        lazyFetchDataIfPrepared();
        initView();// 此处调用初始化View方法
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    /**
     * 进度提示
     */
    public void showLoading() {
        showLoading("");
    }

    public void showLoading(String title) {
        showLoading(title, true);
    }

    public void showLoading(String title, boolean canCancel) {
        if (getActivity() != null && !getActivity().isFinishing()) {
            if (loadingDialog == null) {
                loadingDialog = new AlertDialog.Builder(getActivity(), R.style.add_dialog).create();
                view = getActivity().getLayoutInflater().inflate(R.layout.dialog_loading, null);
                loadingDialog.show();
                loadingDialog.setCanceledOnTouchOutside(false);
                loadingDialog.setCancelable(canCancel);
                loadingDialog.setContentView(view);
                loadingDialog.setOnCancelListener(dialog -> loadingDialog = null);
                mess = (TextView) view.findViewById(R.id.load_title);
            }
            if (!title.equals("")) {
                mess.setText(title);
                mess.setVisibility(View.VISIBLE);
            } else {
                mess.setVisibility(View.GONE);
            }

        }
    }

    public void hideLoading() {
        if (loadingDialog != null) {
            if (loadingDialog.isShowing())
                loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    protected void startActivityForResult(Class<?> cls, Bundle bundle,
                                          int requestCode) {
        Intent intent = new Intent();
        BaseFragmentActivity activity = (BaseFragmentActivity) getActivity();
        intent.setClass(activity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        BaseFragmentActivity activity = (BaseFragmentActivity) getActivity();
        intent.setClass(activity, cls);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        activity.startActivity(intent);
    }

    /**
     * 通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls) {
        Intent intent = new Intent();
        BaseFragmentActivity activity = (BaseFragmentActivity) getActivity();
        if (activity != null) {
            intent.setClass(activity, cls);
            activity.startActivity(intent);
        }
    }

    @Override
    public boolean isBaseOnWidth() {
        return true;
    }

    @Override
    public float getSizeInDp() {
        return 667;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyFetchDataIfPrepared();
        }
    }

    private void lazyFetchDataIfPrepared() {
        // 用户可见fragment && 没有加载过数据 && 视图已经准备完毕
        if (getUserVisibleHint() && !hasFetchData && isViewPrepared) {
            hasFetchData = true;
            lazyFetchData();
        }

    }

    /**
     * 懒加载的方式获取数据，仅在满足fragment可见和视图已经准备好的时候调用一次
     */
    protected void lazyFetchData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }

        hasFetchData = false;
        isViewPrepared = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}
