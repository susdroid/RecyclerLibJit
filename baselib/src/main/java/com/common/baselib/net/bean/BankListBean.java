package com.common.baselib.net.bean;

import com.common.baselib.net.base.ResponseBaseBean;

import java.util.List;

public class BankListBean extends ResponseBaseBean {

	private List<ListBean> list;

	public List<ListBean> getList() {
		return list;
	}

	public void setList(List<ListBean> list) {
		this.list = list;
	}

	public static class ListBean {
		/**
		 * bank_name : 中国工商银行
		 * status : 1
		 * relieve_status : 1
		 * card_code : 6214 **** 4488 40
		 * name : 张婧文
		 */

		private String bank_name;
		private int status;
		private int relieve_status;
		private String card_code;
		private String name;

		public String getBank_name() {
			return bank_name;
		}

		public void setBank_name(String bank_name) {
			this.bank_name = bank_name;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public int getRelieve_status() {
			return relieve_status;
		}

		public void setRelieve_status(int relieve_status) {
			this.relieve_status = relieve_status;
		}

		public String getCard_code() {
			return card_code;
		}

		public void setCard_code(String card_code) {
			this.card_code = card_code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
