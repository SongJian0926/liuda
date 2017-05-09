package com.web.liuda.remote.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.web.liuda.business.entity.Order;
import com.web.liuda.business.entity.OrderDetail;
import com.web.liuda.remote.vo.OrderVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;

public interface ApiOrderService extends ApiBaseService<OrderVo,Order>{
	/**
	 * 查询我的订单（订单类型为酒店和景点的时候使用）
	 * @param nextPage，pageSize，userId，orderSatus，orderType
	 * @return Page<OrderVo>
	 */
	public XaResult<Page<OrderVo>> findListByUserId(
			Integer nextPage,Integer pageSize, Long userId, Integer orderStatus,Integer orderType)
			throws BusinessException ;
	
	/**
	 * 支付成功后处理订单状态
	 * @param orderNo
	 * @return
	 */
	public String modifyOrder(String orderNo,String price,String paymentNo);
    //订单详情
	public XaResult<OrderVo> findOrderDetail(String orderNo,Integer type) throws BusinessException;
	
	/**
	 * 商品下单
	 * @param orders
	 * @return
	 */
	public XaResult<OrderVo> shopSaveOrder(Order order,List<OrderDetail> details,String carIds);
	
	/**
	 * 查询我的订单（订单类型商品的时候使用）
	 * @param nextPage，pageSize，userId，orderSatus，orderType
	 * @return Page<OrderVo>
	 */
	public XaResult<Page<OrderVo>> findShopOrderListByUserId(Integer nextPage,
			Integer pageSize, Long userId, Integer orderStatus,Integer orderType)
			throws BusinessException;
	/*
	 * 确认收货
	 * author：changlu
	 */
	public XaResult<Boolean> receiveConfirmation(String orderNo) throws BusinessException;
	/*
	 * 获取物流
	 * author：changlu
	 */
	public XaResult<OrderVo> getTransport(String orderNo) throws BusinessException ;
}
