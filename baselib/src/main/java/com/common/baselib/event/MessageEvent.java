package com.common.baselib.event;

/**
 * @author MR.su
 * @create 2018/12/24
 * @Describe EventBus的bean类
 */
public class MessageEvent<T> {
	private int code;
	private T data;
	
	public MessageEvent(int code) {
		this.code = code;
	}
	
	public MessageEvent(int code, T data) {
		this.code = code;
		this.data = data;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
}
