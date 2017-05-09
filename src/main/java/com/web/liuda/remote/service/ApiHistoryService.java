package com.web.liuda.remote.service;

import org.springframework.data.domain.Page;

import com.web.liuda.business.entity.History;
import com.web.liuda.remote.vo.HistoryVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;

public interface ApiHistoryService extends ApiBaseService<HistoryVo,History>{
	
	/**
	 * 收藏列表
	 * @param nextPage
	 * @param pageSize
	 * @param type
	 * @param userId
	 * @return
	 */
	public XaResult<Page<HistoryVo>> findHistorys(Integer nextPage, Integer pageSize, Integer type, Long userId,Double lng,Double lat);

	/**
	 * 取消收藏
	 * author:changlu
	 * time:2016-04-25 16:13:00
	 * @return
	 */
	public XaResult<HistoryVo> cancelHistory(String modelIds,
			Integer status) throws BusinessException ;
}
