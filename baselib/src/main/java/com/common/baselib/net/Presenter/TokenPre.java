package com.common.baselib.net.Presenter;

import android.annotation.SuppressLint;

import com.common.baselib.net.Contract.TokenContract;
import com.orhanobut.logger.Logger;

/**
 * Created by Sudroid on 2019/3/26.
 */
public class TokenPre extends TokenContract.Presenter {

    @SuppressLint("CheckResult")
    @Override
    public void getToken(String app_id, String secret) {
        Logger.d("getToken --post:" + app_id + "," + secret);

        baseModel.getToken(app_id, secret)
                .compose(getView().bindToLifecycle())
                .subscribe(tokenBean -> {
                    getView().getTokenResult(tokenBean);
                }, throwable -> getView().NetEorror());

    }
}
