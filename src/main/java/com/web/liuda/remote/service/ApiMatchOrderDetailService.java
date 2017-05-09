package com.web.liuda.remote.service;

import com.web.liuda.business.entity.MatchOrderDetail;
import com.web.liuda.remote.vo.MatchOrderDetailVo;
import com.web.liuda.remote.vo.MatchOrderVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;

public interface ApiMatchOrderDetailService extends ApiBaseService<MatchOrderDetailVo,MatchOrderDetail>{

	/*
	 * 核销兑换码
	 * author：changlu
	 * time:2016-04-26 11:35:00
	 */
	public XaResult<MatchOrderDetailVo> findOrder(Long userId,String redeemCode,Long clubEventId) throws BusinessException  ;
	
	/*
	 * 取消订单
	 * author：flora.li
	 * time:2016-04-26 13:18:00
	 */
	XaResult<MatchOrderDetailVo> cancelMatchOrderDetail(MatchOrderVo ordervo, Long matchOrderDetailId,String reason) throws BusinessException;
}
