package com.web.liuda.business.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.liuda.business.entity.MatchOrderDetail;
import com.web.liuda.remote.vo.MatchOrderDetailVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface MatchOrderDetailService extends BaseServiceInterFace<MatchOrderDetail>{

	void fileDownloadMatchOrder(Map<String, Object> filterParams, HttpServletResponse response)throws BusinessException;

	void fileDownloadEventOrder(Map<String, Object> filterParams, HttpServletResponse response)throws BusinessException;

	XaResult<MatchOrderDetail> updateRredeemCode(Long matchId, String redeemCode)throws BusinessException;

	XaResult<Page<MatchOrderDetail>> findMatchOrderDetailByMatchIdAndTypePage(Integer status, Map<String, Object> filterParams, Pageable pageable)throws BusinessException;

	XaResult<Page<MatchOrderDetailVo>> findMatchOrderDetailVoNEStatusPage(Integer status, Map<String, Object> filterParams, Pageable pageable)throws BusinessException;

}
