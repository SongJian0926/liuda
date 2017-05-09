package com.web.liuda.business.service;

import java.util.List;

import com.web.liuda.business.entity.Province;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface ProvinceService extends BaseServiceInterFace<Province>{
	public XaResult<List<Province>> findProvinceNEStatus(Integer status) throws BusinessException;

	public XaResult<Province> findProvinceByProvinceCode(Integer status, String provinceCode) throws BusinessException;
}
