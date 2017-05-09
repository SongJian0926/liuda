package com.web.liuda.test.service;

import java.io.IOException;
import java.util.Properties;

import com.web.webstart.base.entity.MailSenderInfo;
import com.web.webstart.base.util.MD5Util;
import com.web.webstart.base.util.SimpleMailSender;

public class Test {
	
	public static void main(String[] args) {
		/*List<ShopCarVo> vos = new ArrayList<ShopCarVo>();
		ShopCarVo vo = new ShopCarVo();
		vo.setId(1l);
		vo.setShopNumber(2);
		vos.add(vo);
		ShopCarVo vo1 = new ShopCarVo();
		vo1.setId(2l);
		vo1.setShopNumber(3);
		vos.add(vo1);
		System.out.println(JSON.toJSONString(vos));
		System.out.println(HttpURLConnectionUtil.getLocation("西安市长安区北麓环山生态旅游带"));*/
		Properties pro = new Properties();
    	try {
    		pro.load(Test.class.getClassLoader()
    				.getResourceAsStream("email.properties"));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost(pro.getProperty("email.host"));
		mailInfo.setMailServerPort(pro.getProperty("email.port"));
		mailInfo.setValidate(true);
		mailInfo.setUserName(pro.getProperty("email.fromAddress"));
		mailInfo.setPassword(pro.getProperty("email.password"));// 您的邮箱密码
		mailInfo.setFromAddress(pro.getProperty("email.fromAddress"));
		mailInfo.setToAddress("460703017@qq.com");	//415052415@qq.com
		mailInfo.setSubject("【蹓跶蹓跶】绑定邮箱验证");
		String linkurl = "http://www.baidu.com/activeEmail?email=172810573@qq.com&syscode=" + MD5Util.getMD5String("123456");
		/*if(id != null){
			linkurl += "&id=" + id;
		}*/
		String contents = "请点击下面链接进行验证激活，<a '" + linkurl + "'>点击此处</a>";
		mailInfo.setContent(contents);
		// 这个类主要来发送邮件
		try{
			SimpleMailSender sms = new SimpleMailSender();
			sms.sendHtmlMail(mailInfo);// 发送文体格式
			//更新用户表邮箱验证信息
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("邮件发送失败");
		}
	}

}
