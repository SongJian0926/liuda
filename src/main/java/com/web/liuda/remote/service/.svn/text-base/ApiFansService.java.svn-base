package com.web.liuda.remote.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.liuda.business.entity.Fans;
import com.web.liuda.remote.vo.FansVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;

public interface ApiFansService extends ApiBaseService<FansVo,Fans>{
	//关注我的人
	public XaResult<Page<FansVo>> findMyFansList(
			Integer status,Long userId,Long clubId, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException;
	//我的好友
	public XaResult<FansVo> findMyFrindList(
			Integer status,Long userId)
			throws BusinessException;
	//加为我的好友
	public XaResult<FansVo> addFrinding(Long userId, Long frindId)
			throws BusinessException;
	//我的粉丝
	public XaResult<Page<FansVo>> findMyFansList(Integer status,Long userId,Pageable pageable);
}
