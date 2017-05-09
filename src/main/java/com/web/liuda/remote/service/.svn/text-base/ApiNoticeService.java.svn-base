package com.web.liuda.remote.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.liuda.business.entity.Notice;
import com.web.liuda.remote.vo.NoticeVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;

public interface ApiNoticeService extends ApiBaseService<NoticeVo,Notice>{
	/*
	 * 新闻报道列表
	 * author：changlu
	 * time:2016-04-26 14:43:00
	 */
	public XaResult<Page<NoticeVo>> findNoticeListEQStatus(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException;
}
