package com.web.liuda.remote.service;

import java.util.List;

import com.web.liuda.business.entity.MatchOrderTemp;
import com.web.liuda.remote.vo.MatchOrderTempVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;

public interface ApiMatchOrderTempService extends ApiBaseService<MatchOrderTempVo,MatchOrderTemp>{
	//临时报名人员信息
	public XaResult<List<MatchOrderTempVo>> findMacthOrderTempList(
			Long userId, Long clubEventId,Integer type)
			throws BusinessException ;
}
