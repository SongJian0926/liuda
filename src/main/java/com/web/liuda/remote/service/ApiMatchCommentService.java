package com.web.liuda.remote.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;

import com.web.liuda.business.entity.MatchComment;
import com.web.liuda.remote.vo.MatchCommentVo;


public interface ApiMatchCommentService extends ApiBaseService<MatchCommentVo,MatchComment>{

	/**
	 * 查询赛事评论列表
	 * author:flora.li
	 * time:2016-04-20 14:16:00
	 * @param status
	 * @param id
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	XaResult<Page<MatchCommentVo>> findMatchCommentList(Integer status, Long matchId, Pageable pageable) throws BusinessException ;

	XaResult<MatchCommentVo> praiseModel(Long commentId) throws BusinessException ;
	/**
	 * 赛事活动评论的数量
	 * author:changlu
	 * time:2016-05-12 11:07:00
	 * @param status
	 * @param matchId
	 * @return
	 * @throws BusinessException
	 */
	public XaResult<MatchCommentVo> findMatchCommentNum(Integer status,Long matchId)
			throws BusinessException;
	
}
