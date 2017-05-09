package com.web.liuda.remote.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.liuda.business.entity.EventReview;
import com.web.liuda.remote.vo.EventReviewVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;

public interface ApiEventReviewService extends ApiBaseService<EventReviewVo,EventReview>{
	//俱乐部活动回顾列表
	public XaResult<Page<EventReviewVo>> findEventReviewList(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException;
}
