package com.web.liuda.remote.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.liuda.business.entity.ClubEvent;
import com.web.liuda.remote.vo.ClubEventVo;
import com.web.liuda.remote.vo.MatchOrderDetailVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;

public interface ApiClubEventService extends ApiBaseService<ClubEventVo,ClubEvent>{
	/*
	 * 活动列表
	 * author:changlu
	 * time:2016-04-13 09:40:00
	 */
	public XaResult<Page<ClubEventVo>> findClubEventList(
			Integer status, Map<String, Object> filterParams, Pageable pageable)throws BusinessException;
	/*
	 * 创建活动
	 * author:changlu
	 * time:2016-04-13 11:40:00
	 */
	public XaResult<ClubEventVo> createClubEvent(Long userId,Long clubId,ClubEvent model)
			throws BusinessException;
	/*
	 * 活动详情
	 * author:changlu
	 * time:2016-04-13 14:40:00
	 */
	public XaResult<ClubEventVo> findClubEventDetail(Long userId,Long tId,Integer eventStatus) throws BusinessException;
	/*
	 * 我的活动
	 * author:changlu
	 * time:2016-04-19 15:00:00
	 */
	public XaResult<Page<ClubEventVo>> findMyEventList (Long userId,Integer eventStatus,Pageable pageable)
			throws BusinessException ;
	/*
	 * 活动人员名单(web端)
	 * author:sj
	 * time:2016-04-19 15:00:00
	 */
	public XaResult<Page<MatchOrderDetailVo>> findClubEventPersonnelPgae (Integer status, Map<String, Object> filterParams, Pageable pageable, Long clubId)
			throws BusinessException ;
	/**
	 * @Title: upload
	 * @Description: 活动人员名单导出(web端)
	 * @param photoFile
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	public void exportdata(Map<String, Object> filterParams,HttpServletResponse response,Long clubId)throws BusinessException;
	/*
	 * web端活动验证列表——web端使用
	 * author:changlu
	 * time:2016-04-28 11:25:00
	 */
	public XaResult<Page<ClubEventVo>> findTestClubEventList(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
				throws BusinessException ;

}
