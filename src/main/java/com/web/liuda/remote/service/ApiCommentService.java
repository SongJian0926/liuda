package com.web.liuda.remote.service;

import org.springframework.data.domain.Page;

import com.web.liuda.business.entity.Comment;
import com.web.liuda.remote.vo.CommentVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;

public interface ApiCommentService extends ApiBaseService<CommentVo,Comment>{

	public XaResult<CommentVo> createModel(Comment model, String imageurl)
			throws BusinessException;
	
	/**
	 * 评论列表
	 * @param objectId
	 * @param userId
	 * @param type
	 * @return
	 */
	public XaResult<Page<CommentVo>> findCommentList(Integer nextPage,Integer pageSize,Long objectId,Long userId,Integer type);
	
}
