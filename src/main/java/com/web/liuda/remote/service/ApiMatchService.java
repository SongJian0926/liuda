package com.web.liuda.remote.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;

import com.web.liuda.business.entity.Macth;
import com.web.liuda.remote.vo.MacthVo;


public interface ApiMatchService extends ApiBaseService<MacthVo,Macth>{

	/**
	 * 查询赛事列表
	 * author:flora.li
	 * time:2016-04-20 14:16:00
	 * @param status
	 * @param matchType
	 * @param dictItemId
	 * @param startdate
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	XaResult<Page<MacthVo>> findMatchList(Integer status, Integer matchType, String dictItemIds, String startdate, Pageable pageable) throws BusinessException ;
	
	/**
	 * 查询赛事详情
	 * author:flora.li
	 * time:2016-04-27 14:16:00
	 * @param id
	 * @param userId
	 * @return
	 * @throws BusinessException
	 */
	XaResult<MacthVo> findByMatchIdAndUserId(Long id, Long userId) throws BusinessException ;

	XaResult<MacthVo> updateTicketStockNum(Long id, Integer orderNum) throws BusinessException ;

	XaResult<Double> getRealTicketPrice(MacthVo match) throws BusinessException ;
	
}
