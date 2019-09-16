package com.common.baselib.base;

import android.os.Bundle;
import android.view.View;

import com.common.baselib.R;
import com.common.baselib.utils.statusbar.StatusBarUtils;
import com.common.baselib.utils.statusbar.SystemBarHelper;

import androidx.appcompat.app.ActionBar;
import butterknife.ButterKnife;

/**
 * Created by Sudroid on 2017/3/22.
 */

public class BaseSimpleActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);

        if (mToolbar != null && mTitleView != null) {
            setSupportActionBar(mToolbar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                actionBar.setDisplayHomeAsUpEnabled(false);
                actionBar.setDisplayShowTitleEnabled(false);
            }
            if (enableLineShow()){
                if (mLine!=null){
                    mLine.setVisibility(View.VISIBLE);
                }
            }
        }
        SystemBarHelper.setHeightAndPadding(this, mToolbar);
        StatusBarUtils.setTranslucentStatus(this);
        //状态栏字体颜色-黑色
        StatusBarUtils.setStatusBarDarkTheme(this, true);

        setLeftIcon(R.mipmap.ic_back_black);
    }

    //控制左滑页面关闭页面
    public boolean enableLineShow() {
        return true;
    }

}
