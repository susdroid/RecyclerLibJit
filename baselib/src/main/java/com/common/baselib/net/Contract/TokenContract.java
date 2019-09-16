package com.common.baselib.net.Contract;

import com.common.baselib.net.base.BasePresenter;
import com.common.baselib.net.base.BaseView;
import com.common.baselib.net.bean.AccessTokenBean;

/**
 * Created by Sudroid on 2019/3/26.
 */
public interface TokenContract {

    interface View extends BaseView {

        void getTokenResult(AccessTokenBean response);

        void NetEorror();
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void getToken(String app_id, String secret);
    }
}
