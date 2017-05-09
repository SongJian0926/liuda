package com.web.liuda.business.service;

import javax.servlet.http.HttpServletRequest;

import com.web.liuda.business.entity.Tickets;
import com.web.liuda.remote.vo.TicketsVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface TicketsService extends BaseServiceInterFace<Tickets>{
    
	//web端保存门票(有团购);
	public XaResult<Tickets> saveandUpdate(Tickets model,Long touristId,String imgUrl,HttpServletRequest request,Double groupPrice,String validity) throws BusinessException;
	//web端保存门票(无团购团购);
	public XaResult<Tickets> saveandUpdateNoGroup(Tickets model,Long touristId,String imgUrl,HttpServletRequest request) throws BusinessException;
	//web端查找门票信息
	public XaResult<TicketsVo> findOneTicket(Long modelId) throws BusinessException;
}
