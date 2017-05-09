package com.web.webstart.base.util;
//获取16位随机数
public class GetRandomOrderno {

	private static int getRandom(int count) {
	    return (int) Math.round(Math.random() * (count));
	}
	private static String string = "0123456789";  
	
	public static String getRandomString(int length){
	    StringBuffer sb = new StringBuffer();
	    int len = string.length();
	    for (int i = 0; i < length; i++) {
	        sb.append(string.charAt(getRandom(len-1)));
	    }
	    return sb.toString();
	}
}
