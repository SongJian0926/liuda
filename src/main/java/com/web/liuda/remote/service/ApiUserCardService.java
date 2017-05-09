package com.web.liuda.remote.service;

import java.util.List;

import com.web.liuda.business.entity.UserCard;
import com.web.liuda.remote.vo.UserCardVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;

public interface ApiUserCardService extends ApiBaseService<UserCardVo,UserCard>{
	/*
	 * 银行卡列表
	 * author:changlu
	 * time:2016-04-26 10:13:00
	 */
	public XaResult<List<UserCardVo>> findUserCardList(Long userId) throws BusinessException;
	
	//修改银行卡
	public XaResult<String> updataUserCard(Long userId,Long id,UserCard model);
}
