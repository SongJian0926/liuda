package com.web.liuda.business.constant.channel;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.web.liuda.business.constant.channel.httpclient.SDKHttpClient;

/**
 * 
 * @项目名称：EmayClientForHttpV1.0 
 * @类描述：  
 * @创建人：HL.W  
 * @创建时间：2015-9-9 下午5:29:46  
 * @修改人：HL.W  
 * @修改时间：2015-9-9 下午5:29:46  
 * @修改备注：
 */
public class SDKClient {

	public static String sn = "8SDK-EMY-6699-RERST";// 软件序列号,请通过亿美销售人员获取
	public static String key = "087073";// 序列号首次激活时自己设定
	public static String password = "087073";// 密码,请通过亿美销售人员获取
	public static String baseUrl = "http://hprpt2.eucp.b2m.cn:8080/sdkproxy/";
	public static String StartMenu(String mobile,Long code) {
		String param = "";
		String url = baseUrl + "regist.action";
		param = "cdkey=" + sn + "&password=" + password;
		String ret = SDKHttpClient.registAndLogout(url, param);
		String message = "【蹓跶蹓跶】您好，你的验证码是：" + code+"【5分钟失效】";
		try {
			message = URLEncoder.encode(message, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		param = "cdkey=" + sn + "&password=" + key + "&phone=" + mobile + "&message=" + message + "&addserial=&seqid=666888";
		url = baseUrl + "sendsms.action";
		ret = SDKHttpClient.sendSMS(url, param);
		return ret;
	}
	public static String sendContent(String mobile,String content) {
		String param = "";
		String url = baseUrl + "regist.action";
		param = "cdkey=" + sn + "&password=" + password;
		String ret = SDKHttpClient.registAndLogout(url, param);
		try {
			content = URLEncoder.encode(content, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		param = "cdkey=" + sn + "&password=" + key + "&phone=" + mobile + "&message=" + content + "&addserial=&seqid=666888";
		url = baseUrl + "sendsms.action";
		ret = SDKHttpClient.sendSMS(url, param);
		return ret;
	}
}
