package com.web.webstart.base.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * @Autor: zhangl
 * @times: 2015-4-29下午03:50:33
 * 类的说明：消息推送
 **/
public class Push {
	
	protected static final Logger LOG = LoggerFactory.getLogger(Push.class);

	private static final String appKey ="945cf27c5aae6314e56d5e9d";
	private static final String masterSecret = "649d8ea0405969bd0f207c31";
	
	public static String TITLE = null;
    public static String ALERT = null;
    public static String MSG_CONTENT = null;
    
    @SuppressWarnings("static-access")
	public Push(String title,String alert,String msg_content){
    	this.TITLE = title;
    	this.ALERT = alert;
    	this.MSG_CONTENT = msg_content;
    }
    
	
	public static void sendPush(PushPayload payload) {
		
        JPushClient jpushClient = new JPushClient(masterSecret, appKey);
        
        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        }
	}
	
	/**
	 * 广播通知（通知所有设备）
	 * @return
	 */
	public static PushPayload buildPushObject_all_all_alert() {
	    return PushPayload.alertAll(ALERT);
	}
    
	/**
	 * 构建IOS和ANDRIOD对象
	 * @param model 参数值
	 * @param mobile
	 * @return
	 */
	public static PushPayload buildPushObject_android_and_ios(String model,List<String> mobile) {
		
		Audience aud;
		if(mobile!=null)
		{
			aud =  Audience.newBuilder().addAudienceTarget(AudienceTarget.alias(mobile)).build();
		}
		else
		{
			aud = Audience.all();
		}
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.android_ios())
				.setAudience(aud)
				.setMessage(
						Message.newBuilder().setMsgContent(ALERT)
								.setTitle(TITLE).build())
				.setNotification(
						Notification
								.newBuilder()
								.setAlert(ALERT)
								.addPlatformNotification(AndroidNotification.newBuilder().setTitle(TITLE).addExtra("model", model).build())
								.addPlatformNotification(IosNotification.newBuilder().incrBadge(1).setSound("sound.caf").addExtra("model", model).build())
								.build())
								.setOptions(Options.newBuilder()
				                        .setApnsProduction(true)
				                        .build()).build();
	}

	/**
	 * 构建IOS对象
	 * @param title
	 * @param content
	 * @param type
	 * @param model
	 * @param mobile
	 * @return
	 */
	public static PushPayload buildPushObject_ios(String title,String content,String type,String model,String mobile) {
		
		Audience aud;
		if(mobile!=null)
		{
			aud =  Audience.newBuilder().addAudienceTarget(AudienceTarget.alias(mobile)).build();
		}
		else
		{
			aud = Audience.all();
		}
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.ios())
				.setAudience(aud)
				.setMessage(
						Message.newBuilder().setMsgContent(content)
								.setTitle(title).build())
				.setNotification(
						Notification
								.newBuilder()
								.setAlert(content)
								.addPlatformNotification(IosNotification.newBuilder().incrBadge(1).addExtra("type", type).addExtra("model", model).build())
								.build()).build();
	}
}
