package com.web.liuda.business.service;

import com.web.liuda.business.entity.Images;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface ImagesService extends BaseServiceInterFace<Images>{

	public  XaResult<Images> saveImagesByObjectId(Long ObjectId,String imgUrls)throws BusinessException;
	
	//通过id删除图片
	public XaResult<Images> deleteImagesByObjectId(Long ObjectId,Integer type)throws BusinessException;
}
