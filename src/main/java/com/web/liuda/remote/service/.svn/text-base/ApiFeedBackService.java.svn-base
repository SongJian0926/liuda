package com.web.liuda.remote.service;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.liuda.business.entity.FeedBack;
import com.web.liuda.remote.vo.FeedBackVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;


public interface ApiFeedBackService extends ApiBaseService<FeedBackVo,FeedBack>{
	//查询反馈意见列表
	public XaResult<Page<FeedBackVo>> findPageNEStatusByFilter(Integer status,
			Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException;
	
}
