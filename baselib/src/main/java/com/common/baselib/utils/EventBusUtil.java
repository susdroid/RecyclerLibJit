package com.common.baselib.utils;


import com.common.baselib.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * @author MR.su
 * @create 2018/12/24
 * @Describe EventBus 的工具类
 */
public class EventBusUtil {
	public static void register(Object subscriber) {
		EventBus.getDefault().register(subscriber);
	}
	
	public static void unregister(Object subscriber) {
		EventBus.getDefault().unregister(subscriber);
	}
	
	public static void sendEvent(MessageEvent event) {
		EventBus.getDefault().post(event);
	}
	
	public static void sendStickyEvent(MessageEvent event) {
		EventBus.getDefault().postSticky(event);
	}
	
	
}
