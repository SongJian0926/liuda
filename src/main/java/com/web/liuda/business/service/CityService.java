package com.web.liuda.business.service;

import java.util.List;

import com.web.liuda.business.entity.City;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface CityService extends BaseServiceInterFace<City>{
	
	public XaResult<List<City>> findCityByProvinceCode(Integer status, String provinceCode) throws BusinessException;
	
	public XaResult<City> findCityByCityCode(Integer status, String cityCode) throws BusinessException;
}
