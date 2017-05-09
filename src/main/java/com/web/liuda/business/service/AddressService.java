package com.web.liuda.business.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.web.liuda.business.entity.Address;
import com.web.liuda.remote.vo.AddressVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface AddressService extends BaseServiceInterFace<Address>{
	//查询地址列表
	public XaResult<Page<AddressVo>> findAddressList(
			Integer nextPage,Integer pageSize, Map<String, Object> filterParams) throws BusinessException;
   //查询地址详情
	public XaResult<AddressVo> findAddressDetail(Long id) throws BusinessException;
}
