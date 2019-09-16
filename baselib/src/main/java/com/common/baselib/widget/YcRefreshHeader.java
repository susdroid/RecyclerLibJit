package com.common.baselib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.baselib.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

import androidx.annotation.NonNull;

/**
 * 自定义刷新控件头部
 * Created by Sudroid on 2018/1/10.
 */
@SuppressLint("NewApi")
public class YcRefreshHeader extends RelativeLayout implements RefreshHeader {
    private TextView mTextView;
    private ImageView mProgressBar;
    private boolean isArrowDown = false;
    private AnimationDrawable animationDrawable;

    public YcRefreshHeader(Context context) {
        super(context);
        this.initView(context, null, 0);
    }

    public YcRefreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView(context, attrs, 0);
    }

    public YcRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        setMinimumHeight(dp2px(context, 80));
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(CENTER_IN_PARENT);
        View headerView = View.inflate(context, R.layout.item_refresh_header, null);
        mProgressBar = (ImageView) headerView.findViewById(R.id.progress);
        mTextView = (TextView) headerView.findViewById(R.id.title);
        addView(headerView, params);
    }

    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) { // 尺寸定义完成
    }

    @Override
    public void onPulling(float percent, int offset, int height, int extendHeight) {

    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }



    @Override
    public void onReleasing(float percent, int offset, int headHeight, int extendHeight) {  // 手指释放之后的持续动画
//        animationDrawable = (AnimationDrawable) mProgressBar.getDrawable();
//        if (animationDrawable.isRunning()) {
//            animationDrawable.stop();
//        }
//        animationDrawable.start();
    }

    @Override
    public void onReleased(RefreshLayout refreshLayout, int height, int extendHeight) {

    }

    @Override
    public void onStartAnimator(RefreshLayout layout, int headHeight, int extendHeight) {
        animationDrawable = (AnimationDrawable) mProgressBar.getDrawable();
        if (animationDrawable.isRunning()) {
            animationDrawable.selectDrawable(0);
            animationDrawable.stop();
        }
        animationDrawable.start();
    }

    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
        animationDrawable = (AnimationDrawable) mProgressBar.getDrawable();
        if (animationDrawable.isRunning()) {
            animationDrawable.selectDrawable(0);
            animationDrawable.stop();
            mProgressBar.setVisibility(INVISIBLE);
        }
        return 100; // 动画结束,延迟多少毫秒之后再收回
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void setPrimaryColors(int... colors) {
        setBackgroundColor(getResources().getColor(R.color.main_bg_color));
    }

    @NonNull
    public View getView() {
        return this;
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) { // 状态改变事件
        switch (newState) {
            case None: // 无状态
                mProgressBar.setVisibility(VISIBLE);
                mTextView.setVisibility(INVISIBLE);
                break;
            case PullDownToRefresh: // 可以下拉状态
                mProgressBar.setVisibility(VISIBLE);
                mTextView.setVisibility(INVISIBLE);
                break;
            case Refreshing: // 刷新中状态

                mProgressBar.setVisibility(VISIBLE);
                mTextView.setVisibility(INVISIBLE);
                break;
            case ReleaseToRefresh:  // 释放就开始刷新状态
                mProgressBar.setVisibility(VISIBLE);
                mTextView.setVisibility(INVISIBLE);
                break;
            case RefreshFinish:
                mProgressBar.setVisibility(INVISIBLE);
                mTextView.setVisibility(VISIBLE);
                break;
        }
    }


    /**
     * dp转px
     */
    private int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
}

