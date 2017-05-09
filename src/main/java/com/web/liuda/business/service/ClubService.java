package com.web.liuda.business.service;

import com.web.liuda.business.entity.Club;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface ClubService extends BaseServiceInterFace<Club>{

	XaResult<Club> applyClub(String clubIds,Integer status) throws BusinessException;

	XaResult<Club> recommendClub(String clubIds, Integer status) throws BusinessException;

}
