package com.common.baselib.net.bean;

import com.common.baselib.net.base.ResponseBaseBean;

import java.util.List;

public class BillStagesBean extends ResponseBaseBean {


    private List<ListBean> rlist;
    private List<ListBean> list;

    public List<ListBean> getRlist() {
        return rlist;
    }

    public void setRlist(List<ListBean> rlist) {
        this.rlist = rlist;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * status : 0
         * stages : 已还清
         * period : 第1/3期
         * repay_money : 1089.75
         * repay_time : 2019-4-20
         */

        private int status;
        private String stages;
        private String period;
        private String repay_money;
        private String repay_time;

        private String stauts_timeout;
        private int type;

        public ListBean(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public String getStauts_timeout() {
            return stauts_timeout;
        }

        public void setStauts_timeout(String stauts_timeout) {
            this.stauts_timeout = stauts_timeout;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStages() {
            return stages;
        }

        public void setStages(String stages) {
            this.stages = stages;
        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public String getRepay_money() {
            return repay_money;
        }

        public void setRepay_money(String repay_money) {
            this.repay_money = repay_money;
        }

        public String getRepay_time() {
            return repay_time;
        }

        public void setRepay_time(String repay_time) {
            this.repay_time = repay_time;
        }
    }
}
