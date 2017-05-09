package com.web.liuda.remote.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;

import com.web.liuda.business.entity.MatchLive;
import com.web.liuda.remote.vo.MatchLiveVo;


public interface ApiMatchLiveService extends ApiBaseService<MatchLiveVo,MatchLive>{

	/**
	 * 查询赛事直播列表
	 * author:flora.li
	 * time:2016-04-20 14:16:00
	 * @param status
	 * @param id
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	XaResult<Page<MatchLiveVo>> findMatchLiveList(Integer status, Long id, Pageable pageable) throws BusinessException ;
	
}
