package com.web.liuda.remote.service;

import com.web.liuda.business.entity.RefundOrder;
import com.web.liuda.remote.vo.RefundOrderVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;

public interface ApiRefundOrderService extends ApiBaseService<RefundOrderVo,RefundOrder>{

	XaResult<RefundOrderVo> findRefundOrderByOrderDetailId(Integer status, Long orderDetailId)throws BusinessException ;

	void refundSuccess(String batch_no)throws BusinessException ;

}
