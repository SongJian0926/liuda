package com.web.liuda.business.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.liuda.business.entity.FeedBack;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface FeedBackService extends BaseServiceInterFace<FeedBack>{
	//查询反馈列表
	public XaResult<Page<FeedBack>> findList(
			Integer status,Map<String, Object> filterParams,Pageable pageable) throws BusinessException ;
    //导出
	public void exportdata(Map<String, Object> filterParams,HttpServletResponse response) throws BusinessException;
	//查找某个用户的反馈信息
	public XaResult<FeedBack> findOneUser(Long modelId,String createTime) throws BusinessException;
}
