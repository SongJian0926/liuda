package com.web.liuda.remote.service.impl;

import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.RefundOrder;
import com.web.liuda.remote.vo.RefundOrderVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.repository.RefundOrderRepository;
import com.web.liuda.remote.service.ApiRefundOrderService;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APIRefundOrder接口实现类
 **/
@Service("ApiRefundOrderService")
@Transactional(readOnly = false)
public class ApiRefundOrderServiceImpl extends BaseService<RefundOrder> implements ApiRefundOrderService{

	@Autowired
	RefundOrderRepository refundOrderRepository;
	
	@Override
	public XaResult<RefundOrderVo> findOne(Long tId) throws BusinessException {
		RefundOrder obj = refundOrderRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<RefundOrderVo> xr = new XaResult<RefundOrderVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),RefundOrderVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<List<RefundOrderVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<RefundOrder> page = refundOrderRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), RefundOrder.class), pageable);
		XaResult<List<RefundOrderVo>> xr = new XaResult<List<RefundOrderVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), RefundOrderVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<List<RefundOrderVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<RefundOrder> page = refundOrderRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), RefundOrder.class), pageable);
		XaResult<List<RefundOrderVo>> xr = new XaResult<List<RefundOrderVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), RefundOrderVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<RefundOrderVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		XaResult<RefundOrderVo> xr = new XaResult<RefundOrderVo>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				RefundOrder obj = refundOrderRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = refundOrderRepository.save(obj);
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj), RefundOrderVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<RefundOrderVo> createModel(RefundOrder model)
			throws BusinessException {
		XaResult<RefundOrderVo> xr = new XaResult<RefundOrderVo>();
		RefundOrder obj = refundOrderRepository.save(model);
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), RefundOrderVo.class));
		xr.getObject().setId(obj.getId());
		return xr;
	}

	@Override
	public XaResult<RefundOrderVo> updateModel(RefundOrder model)
			throws BusinessException {
		RefundOrder obj = refundOrderRepository.findOne(model.getId());
		XaResult<RefundOrderVo> xr = new XaResult<RefundOrderVo>();
		if (XaUtil.isNotEmpty(obj)) {
		obj.setOrderId(model.getOrderId());
		obj.setOrderDetailId(model.getOrderDetailId());
		obj.setRefundAmt(model.getRefundAmt());
		obj.setRefundDate(model.getRefundDate());
		obj.setRefundApplyDate(model.getRefundApplyDate());
		obj.setRefundStatus(model.getRefundStatus());
		obj.setMemo(model.getMemo());
		obj.setReason(model.getReason());
			obj = refundOrderRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), RefundOrderVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<RefundOrderVo> findRefundOrderByOrderDetailId(Integer status, Long orderDetailId) throws BusinessException {
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		if(XaUtil.isNotEmpty(status))
		{
			filters.put("status", new SearchFilter("status", Operator.EQ, status));
		}
		if(XaUtil.isNotEmpty(status))
		{
			filters.put("refundStatus", new SearchFilter("refundStatus", Operator.NE, JConstant.RefundStatus.VALID));
		}
		if(XaUtil.isNotEmpty(orderDetailId))
		{
			filters.put("orderDetailId", new SearchFilter("orderDetailId", Operator.EQ, orderDetailId));
		}
		RefundOrder obj = refundOrderRepository.findOne(DynamicSpecifications.bySearchFilter(filters.values(), RefundOrder.class));
		XaResult<RefundOrderVo> xr = new XaResult<RefundOrderVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),RefundOrderVo.class));
			xr.getObject().setId(obj.getId());
		} 
		else
		{
			xr.setObject(null);
		}
		return xr;
		
	}

	@Override
	public void refundSuccess(String batch_no) throws BusinessException {
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		filters.put("batchNo", new SearchFilter("batchNo", Operator.EQ, batch_no));
		List<RefundOrder> lst = refundOrderRepository.findAll(DynamicSpecifications.bySearchFilter(filters.values(), RefundOrder.class));
		for(RefundOrder ro : lst)
		{
			if(ro.getRefundStatus().equals(JConstant.RefundStatus.REFUNDING))
			{
				ro.setRefundStatus(JConstant.RefundStatus.REFUNDSUCCESS);
				ro.setRefundDate(XaUtil.getToDayStr());
				refundOrderRepository.save(ro);
			}
		}
	}

}
