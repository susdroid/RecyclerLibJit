package com.common.baselib.net.bean;

import com.common.baselib.net.base.ResponseBaseBean;

import java.util.List;

/**
 * Created by Sudroid on 2017/10/17.
 */

public class MsgCenterBean extends ResponseBaseBean {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * title : 恭喜您获得额度
         * time : 2018-02-25 15:09:30
         * content : 恭喜您通过评估，额度提升3000.00元，感谢您的……
         * sign : 1
         */

        private String title;
        private String time;
        private String content;
        private int sign;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getSign() {
            return sign;
        }

        public void setSign(int sign) {
            this.sign = sign;
        }
    }
}
