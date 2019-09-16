package com.common.baselib.net.bean;

import com.common.baselib.net.base.ResponseBaseBean;

public class AccessTokenBean extends ResponseBaseBean {

	// AccessToken
	private String at = "";
	private String sid = "";

	public String getAt() {
		return at;
	}

	public void setAt(String at) {
		this.at = at;
	}

}
