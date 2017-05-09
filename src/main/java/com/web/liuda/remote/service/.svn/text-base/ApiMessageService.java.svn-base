package com.web.liuda.remote.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.liuda.business.entity.Message;
import com.web.liuda.remote.vo.MessageVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;

public interface ApiMessageService extends ApiBaseService<MessageVo,Message>{

	public XaResult<Page<MessageVo>> findPageNEStatusByFilter(Integer status,
			Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException;
	/**
	 * 消息详情
	 * author:changlu
	 * time:2016-05-03 16:23:00
	 * @param tId
	 * @param userId
	 * @return
	 * @throws BusinessException
	 */
	public XaResult<MessageVo> findOneMessage(Long tId,Long userId) throws BusinessException;

	/**
	 * 查询是否有新消息
	 * author:changlu
	 * time:2016-05-03 17:27:00
	 * @param userId
	 * @return
	 * @throws BusinessException
	 */
	public XaResult<MessageVo> findNewMessage(Long userId)
			throws BusinessException;
}
