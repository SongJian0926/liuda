package com.web.liuda.business.constant;

import org.springframework.stereotype.Component;

import com.web.liuda.business.entity.OrderDetail;
import com.web.webstart.base.service.impl.BaseService;

@Component
public class SystemScheduled extends BaseService<OrderDetail>{
	
	/*@Scheduled(cron="0/60 * * * * ?")
	public void getGoldPrice() throws Exception{
		String sql = "call pro_backstock()";
		this.queryCall(sql, null);
	}*/
	
}
