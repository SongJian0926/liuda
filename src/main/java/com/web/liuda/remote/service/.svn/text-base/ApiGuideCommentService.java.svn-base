package com.web.liuda.remote.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.liuda.business.entity.GuideComment;
import com.web.liuda.remote.vo.GuideCommentVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;

public interface ApiGuideCommentService extends ApiBaseService<GuideCommentVo,GuideComment>{
	//攻略评论列表
	public XaResult<Page<GuideCommentVo>> findGuideContentList(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException;
}
