package com.common.sudroid;

import android.widget.TextView;

import com.common.baselib.base.BaseFragment;
import com.common.baselib.net.Contract.TokenContract;
import com.common.baselib.net.Presenter.TokenPre;
import com.common.baselib.net.bean.AccessTokenBean;
import com.common.baselib.utils.Debug;

import butterknife.BindView;


/**
 * Created by Sudroid on 2019/3/25.
 */
public class MainFrag extends BaseFragment<TokenPre> implements TokenContract.View {


    public static MainFrag newInstance(String info) {
        MainFrag fragment = null;
        if (fragment == null) {
            fragment = new MainFrag();
        }
        return fragment;
    }

    @BindView(R.id.text)
    TextView mText;

    @Override
    protected int getLayoutResource() {
        return R.layout.frag_main;
    }

    @Override
    protected void initPresenter() {
        mPresenter.bindView(this);
    }

    @Override
    public void initView() {
        mPresenter.getToken("4", "FHueh83HFHFjjdh883");
    }

    @Override
    public void getTokenResult(AccessTokenBean response) {
        Debug.LogD("1111111");
        mText.setText("token=" + response.getAt());
    }

    @Override
    public void NetEorror() {

    }

}
