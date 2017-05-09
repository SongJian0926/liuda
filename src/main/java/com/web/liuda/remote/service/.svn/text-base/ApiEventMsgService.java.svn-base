package com.web.liuda.remote.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.liuda.business.entity.EventMsg;
import com.web.liuda.remote.vo.EventMsgVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;

public interface ApiEventMsgService extends ApiBaseService<EventMsgVo,EventMsg>{
	//俱乐部活动留言列表
	public XaResult<Page<EventMsgVo>> findEventMsgList(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException ;
}
