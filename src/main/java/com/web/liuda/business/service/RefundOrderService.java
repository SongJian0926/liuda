package com.web.liuda.business.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.liuda.business.entity.RefundOrder;
import com.web.liuda.remote.vo.RefundOrderVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface RefundOrderService extends BaseServiceInterFace<RefundOrder>{

	XaResult<Page<RefundOrderVo>> findRefundOrderVoNEStatusPage(Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException;

	XaResult<String> applyRefundOrder(String rootpath, String refundIds, Integer refundStatus) throws BusinessException;

	void QueryRefundWxPay() throws Exception;

}
