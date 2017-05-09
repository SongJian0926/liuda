package com.web.liuda.business.service;

import com.web.liuda.business.entity.MyBank;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface MyBankService extends BaseServiceInterFace<MyBank>{

	//验证银行卡是否存在
	public XaResult<MyBank> findByBusinessId(Long id)throws BusinessException;
	
	//根据商户id删除银行卡
	public XaResult<MyBank> updataBankCard(Long id)throws BusinessException;
}
