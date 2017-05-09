package com.web.liuda.business.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.liuda.business.entity.Comment;
import com.web.liuda.remote.vo.CommentVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface CommentService extends BaseServiceInterFace<Comment>{
	public XaResult<Page<CommentVo>> findPageNEStatusByFilter(
			 Map<String, Object> filterParams, Pageable pageable) throws BusinessException ;
	/*
	 * 分页查询评论管理列表
	 * author:changlu
	 * time:2016-01-11 16:58:00
	 * params:businessId 商家Id
	 * params:businessType 商家类型
	 * params:filterParams 查询条件
	 * params:pageable 分页参数
	 */
	public XaResult<Page<CommentVo>> findCommentByFilter(Long businessId, Integer businessType,
			Map<String, Object> filterParams, Pageable pageable) throws BusinessException ;
}
