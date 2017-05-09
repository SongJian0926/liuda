package com.web.liuda.business.service;

import java.util.List;

import com.web.liuda.business.entity.Area;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface AreaService extends BaseServiceInterFace<Area>{

	public XaResult<List<Area>> findAreaByCityCode(Integer status, String cityCode) throws BusinessException;
	
	public XaResult<Area> findAreaByAreaCode(Integer status, String areaCode) throws BusinessException;

	public String getAreaName(Long areaId) throws BusinessException;
}
