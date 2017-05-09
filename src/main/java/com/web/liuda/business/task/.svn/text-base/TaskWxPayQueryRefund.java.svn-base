package com.web.liuda.business.task;

import org.springframework.scheduling.annotation.Scheduled;    
import org.springframework.stereotype.Component;  
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.web.liuda.business.service.RefundOrderService;

@Component("taskJob")  
public class TaskWxPayQueryRefund {
	
	@Autowired
	private RefundOrderService refundOrderService;
	
	Logger log = Logger.getLogger(TaskWxPayQueryRefund.class);
	
	@Scheduled(cron = "0 0 3 * * ?")	//每天凌辱3点触发
	public void run() {
		try {
			System.out.println("定时器-微信查询退款记录-开始");
			//微信退款需要每天定时查询是否已经退款成功
			refundOrderService.QueryRefundWxPay();
			System.out.println("定时器-微信查询退款记录-结束");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}

}
