package com.web.liuda.business.service;

import com.web.liuda.business.entity.Tourist;
import com.web.liuda.remote.vo.BusinessInfoVo;
import com.web.liuda.remote.vo.TouristVo;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface TouristService extends BaseServiceInterFace<Tourist>{
	//返回BusinessInfoVo实体
	public XaResult<BusinessInfoVo> newSaveOrUpdate(Tourist t,String[] arr);
	
	//通过商户ID查找景点实体
	public XaResult<Tourist> findTouistByBusinessId(Long model);
	
	//通过景点Id查找景点实体
	public XaResult<TouristVo> newFindOne(Long model);
	
	//修改景点
	public XaResult<BusinessInfoVo> updataTouist(Long id,Tourist tourist,String[] arr);
}
