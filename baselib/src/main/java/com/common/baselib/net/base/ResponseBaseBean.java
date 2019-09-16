package com.common.baselib.net.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResponseBaseBean {

	private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
	// 返回状态 -1失败 1成功
	private int r = -1;
	// 具体的返回信息
	private String msg = "";

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public String getMsg() {

		// 过滤html标签
		if (msg.contains("<")) {
			Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			Matcher m_html = p_html.matcher(msg);
			msg = m_html.replaceAll("");
		}
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
