package com.web.liuda.business.service;

import com.web.liuda.business.entity.Macth;
import com.web.liuda.remote.vo.GuessVo;
import com.web.liuda.remote.vo.MacthVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface MacthService extends BaseServiceInterFace<Macth>{

	XaResult<Macth> upAndDownMatch(String matchIds, Integer status) throws BusinessException;

	XaResult<MacthVo> findMacthById(Long modelId) throws BusinessException;
	
	XaResult<Macth> saveOrUpdate(Macth match,GuessVo guessVo) throws BusinessException;

}
