package com.common.baselib.net.bean;

import com.common.baselib.net.base.ResponseBaseBean;

import java.util.List;

public class BillListBean extends ResponseBaseBean {


	private List<ListBean> list;

	public List<ListBean> getList() {
		return list;
	}

	public void setList(List<ListBean> list) {
		this.list = list;
	}

	public static class ListBean {
		/**
		 * money : 3000.00
		 * time : 2019/2/20
		 * status : 1
		 * limit_date : 3
		 * repay_status : 1
		 * repay_money : 1089.75
		 * repay_time : 3月20日
		 */

		private String money;
		private String time;
		private int status;
		private String limit_date;
		private int repay_status;
		private String repay_money;
		private String repay_time;

		public String getMoney() {
			return money;
		}

		public void setMoney(String money) {
			this.money = money;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getLimit_date() {
			return limit_date;
		}

		public void setLimit_date(String limit_date) {
			this.limit_date = limit_date;
		}

		public int getRepay_status() {
			return repay_status;
		}

		public void setRepay_status(int repay_status) {
			this.repay_status = repay_status;
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
