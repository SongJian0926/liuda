package com.web.liuda.remote.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.liuda.business.entity.Guide;
import com.web.liuda.business.entity.GuideAppend;
import com.web.liuda.remote.vo.GuideVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;

public interface ApiGuideService extends ApiBaseService<GuideVo,Guide>{
	
	/**
	 * 查询攻略列表
	 * author:changlu
	 * time:2016-04-08 15:20:00
	 * @param status
	 * @param dictItemId
	 * @param type
	 * @param pageable
	 * @param title
	 * @return
	 * @throws BusinessException
	 */
	public XaResult<Page<GuideVo>> findGuideList(
			Integer status, Long dictItemId,Integer type, String title,Pageable pageable)
			throws BusinessException;
	
	/**
	 * 我的攻略列表
	 * author:changlu
	 * time:2016-04-19 11:22:00
	 * @param status
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	public XaResult<Page<GuideVo>> findMyGuideList(
			Long userId, Pageable pageable)
			throws BusinessException ;
	/**
	 * 创建攻略、追加攻略
	 * author:changlu
	 * time:2016-04-19 11:52:00
	 * @param status
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	public XaResult<GuideVo> createGuide(Guide model,GuideAppend guideAppend)
			throws BusinessException ;
	/**
	 * 增加浏览量
	 * author:changlu
	 * time:2016-04-20 11:52:00
	 * @param status
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	public XaResult<GuideVo> addPageview(Long id)
			throws BusinessException;
	/**
	 * @Title: ApplyCompetitiv
	 * @Description: 申请为精品 
	 * @param modelId
	 * author:sj
	 * @return    
	 */
	public XaResult<String> ApplyCompetitiv(Long userId,Long id)
			throws BusinessException;
}
