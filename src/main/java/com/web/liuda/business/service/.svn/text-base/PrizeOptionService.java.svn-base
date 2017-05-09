package com.web.liuda.business.service;

import java.util.List;

import com.web.liuda.business.entity.PrizeOption;
import com.web.liuda.remote.vo.PrizeOptionVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface PrizeOptionService extends BaseServiceInterFace<PrizeOption>{

	XaResult<List<PrizeOptionVo>> findByMacthIdAndNotStatus(Long matchId, Integer status) throws BusinessException ;

	XaResult<List<PrizeOptionVo>> setPrizeOptionResult(Long matchId) throws BusinessException ;

	XaResult<List<PrizeOptionVo>> publishPrize(Long matchId) throws BusinessException ;

}
