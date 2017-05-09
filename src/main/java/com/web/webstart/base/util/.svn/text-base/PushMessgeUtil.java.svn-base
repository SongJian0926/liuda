package com.web.webstart.base.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Autor: zhangl
 * @Times: 2015-8-19下午02:10:39
 * 类的说明：
 **/
public class PushMessgeUtil{
	
	@SuppressWarnings("static-access")
	public static void pushMessage(String content , List<String> emps){
		List<String> emps1 = new ArrayList<String>();
		for(String emp : emps){
			emps1.add(emp);
		}
		//推送
		Push push = new Push("您有一条新的提醒" , content , "您有一条新的提醒") ;
		push.sendPush(push.buildPushObject_android_and_ios("",emps1));
	}
	
	public static void main(String[] args) {
		
	}
	
}
