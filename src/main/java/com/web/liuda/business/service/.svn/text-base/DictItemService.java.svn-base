package com.web.liuda.business.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.liuda.business.entity.DictItem;
import com.web.liuda.remote.vo.DictVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface DictItemService extends BaseServiceInterFace<DictItem>{
	public XaResult<Page<DictVo>> findList(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException;
	public XaResult<List<DictItem>> findListByType(Integer status, Integer type) throws BusinessException;

}
