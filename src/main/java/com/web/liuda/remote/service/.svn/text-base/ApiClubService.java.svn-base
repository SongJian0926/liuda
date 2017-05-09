package com.web.liuda.remote.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.liuda.business.entity.Club;
import com.web.liuda.remote.vo.ClubVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;

public interface ApiClubService extends ApiBaseService<ClubVo,Club>{
	/**
	 * 查询俱乐部列表
	 * author:changlu
	 * time:2016-04-11 14:20:00
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	public XaResult<Page<ClubVo>> findClubList(
			Integer status, Map<String, Object> filterParams,Long type,String areaCode, Pageable pageable,Double lng, Double lat,String title)
			throws BusinessException;
	/**
	 * 查询推荐俱乐部列表
	 * author:changlu
	 * time:2016-04-13 14:20:00
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	public XaResult<List<ClubVo>> findRecomendClub(Integer status, Map<String, Object> filterParams)
			throws BusinessException;
	/**
	 * 俱乐部详情
	 * author:changlu
	 * time:2016-04-15 12:20:00
	 */
	public XaResult<ClubVo> findClubById(Long userId,Long tId) throws BusinessException ;
	/*
	 * 我的俱乐部
	 * author:changlu
	 * time:2016-04-19 10:14:00
	 */
	public XaResult<List<ClubVo>> findMyClubList(Long userId) throws BusinessException;
	/**
	 * web端俱乐部详情
	 * author:changlu
	 * time:2016-04-26 19:20:00
	 */
	public XaResult<ClubVo> findClubDetailById(Long tId,Long userId) throws BusinessException;
	/**
	 * 我创建的俱乐部
	 * author:changlu
	 * time:2016-05-30 12:20:00
	 */
	public XaResult<Page<ClubVo>> findMyCreateClubList(Long userId,Pageable pageable) throws BusinessException;
	/**
	 * 我加入的俱乐部
	 * author:changlu
	 * time:2016-05-30 12:20:00
	 */
	public XaResult<Page<ClubVo>> findMyJoinClubList(Long userId,Pageable pageable) throws BusinessException;
}
