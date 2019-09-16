package com.common.baselib.utils;

import com.common.baselib.BaseApplication;
import com.common.baselib.net.bean.BankListBean;
import com.common.baselib.net.bean.BillListBean;
import com.common.baselib.net.bean.BillStagesBean;
import com.common.baselib.net.bean.MsgCenterBean;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

/**
 * Created by Sudroid on 2019/4/4.
 */
public class TestDataManager {


    @IntDef({PAGE_BILL_LIST, PAGE_BILL_STAGES, PAGE_BORROW_MSG, PAGE_BANK_LIST})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Page {
    }

    public static final int PAGE_BILL_LIST = 0;// 订单列表
    public static final int PAGE_BILL_STAGES = 1;// 分期列表
    public static final int PAGE_BORROW_MSG = 2;// 消息列表
    public static final int PAGE_BANK_LIST = 3;// 银行列表

    private static TestDataManager instance;

    public static synchronized TestDataManager getInstance() {
        if (null == instance) {
            instance = new TestDataManager();
        }
        return instance;
    }

    public Object getModel(@Page int page) {
        Object obj = null;

        if (Debug.getDebugState()) {
            Gson gson = new Gson();

            switch (page) {
                case PAGE_BILL_LIST:
                    obj = gson.fromJson(getJSON("Bill_list.json"), BillListBean.class);
                    break;
                case PAGE_BILL_STAGES:
                    obj = gson.fromJson(getJSON("Bill_stages.json"), BillStagesBean.class);
                    break;
                case PAGE_BORROW_MSG:
                    obj = gson.fromJson(getJSON("Borrow_msg.json"), MsgCenterBean.class);
                    break;
                case PAGE_BANK_LIST:
                    obj = gson.fromJson(getJSON("Mine_bank_list.json"), BankListBean.class);
                    break;
            }
        }

        return obj;
    }

    private String getJSON(String fileName) {
        StringBuilder builder = new StringBuilder();
        try {
            InputStreamReader isr = new InputStreamReader(BaseApplication.getContext().getResources().getAssets().open(fileName), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
            br.close();
            isr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString().trim();
    }
}
