package com.web.liuda.business.service;

import java.util.List;

import com.web.liuda.business.entity.User;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface UserService extends BaseServiceInterFace<User>{
	/**
	 * 同意申请为大咖
	 * author:changlu
	 * time:2016-05-10 15:27:00
	 * @param request
	 * @param modelIds
	 * @param bigShot
	 * @return
	 * @throws BusinessException
	 */
	public XaResult<User> checkBigShot(
			String modelIds,Integer bigShot) throws BusinessException;

	public XaResult<List<User>> findUserListStatusNot(Integer status) throws BusinessException;
}
