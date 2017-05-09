package com.web.liuda.business.service;

import java.util.ArrayList;

import com.web.liuda.business.entity.Hotel;
import com.web.liuda.remote.vo.BusinessInfoVo;
import com.web.liuda.remote.vo.HotelVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface HotelService extends BaseServiceInterFace<Hotel>{
	public XaResult<Hotel> findHotelByBusinessId(Long modelId)throws BusinessException; 
	public XaResult<HotelVo> findHotelById(Long modelId) throws BusinessException;
	public XaResult<Hotel> newSaveOrUpdate(Hotel model,ArrayList<String> list) throws BusinessException;

	//存入商户，返回businessVo
	public XaResult<BusinessInfoVo> newSaveModel(Hotel hotel,String[] arr);
	
	//通过酒店id找到酒店和酒店图片介绍
	public XaResult<HotelVo> newFindOne(Long modelId);
	
	//修改酒店
	public XaResult<BusinessInfoVo> updataHotel(Long modelId,Hotel hotel,String[] arr);
}
