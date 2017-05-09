package com.web.liuda.business.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.liuda.business.entity.Standard;
import com.web.liuda.business.repository.StandardRepository;
import com.web.liuda.business.service.StandardService;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.XaResult;

@Service("StandardService")
@Transactional(readOnly = false)
public class StandardServicelmpl extends BaseService<Standard> implements StandardService{

	@Autowired
	private StandardRepository standaedRepository;
	
	
	@Override
	public XaResult<Standard> saveOrUpdate(Standard t) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public XaResult<Standard> findOne(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XaResult<Page<Standard>> findListNEStatusByFilter(Integer status,
			Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XaResult<Page<Standard>> findListEQStatusByFilter(Integer status,
			Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public XaResult<Standard> multiOperate(String ids, Integer status)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
