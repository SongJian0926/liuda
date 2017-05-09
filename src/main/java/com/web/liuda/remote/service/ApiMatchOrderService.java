package com.web.liuda.remote.service;

import com.web.liuda.business.entity.MatchOrder;
import com.web.liuda.remote.vo.MatchOrderVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;

public interface ApiMatchOrderService extends ApiBaseService<MatchOrderVo,MatchOrder>{
	//赛事、活动下单
	public XaResult<MatchOrderVo> createMatchOrder(String matchOrderTempIds,MatchOrder model)
			throws BusinessException ;
	//支付成功之后调用修改订单状态的方法
	public String modifyOrder1(String orderNo, String price, String paymentNo) ;
	/**
	 * @Title: findMatchOrderById
	 * @Description: 赛事、活动订单详情
	 * @param modelId
	 * author:changlu
	 * time:2016-04-20 15:07:00
	 * @return    
	 */
	public XaResult<MatchOrderVo> findMatchOrderDetailById(Long tId,Integer type) throws BusinessException ;
	/**
	 * 支付时修改支付方式
	 * author:changlu
	 * time:2016-06-16 15:07:00
	 * @return    
	 */
	public XaResult<MatchOrderVo> updatePayType(MatchOrder model) throws BusinessException;
}
