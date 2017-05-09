package com.web.liuda.business.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.liuda.business.entity.MatchOrder;
import com.web.liuda.remote.vo.MatchOrderVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface MatchOrderService extends BaseServiceInterFace<MatchOrder>{

	XaResult<Page<MatchOrderVo>> findMatchOrderVoNEStatusPage(Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException;

}
