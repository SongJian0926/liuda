package com.web.liuda.business.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.liuda.business.entity.Order;
import com.web.liuda.remote.vo.OrderVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface OrderService extends BaseServiceInterFace<Order>{
	
	/**
	 * 查询订单详情
	 * @param id
	 * @param orderType
	 * @return
	 */
	public XaResult<OrderVo> findOne(Long id,Integer orderType);
	/**
	 * pc端查订单管理的列表展示
	 * author：changlu
	 * time:2016-01-14 13:40:00
	 * @param businessId 
	 * @param businessType
	 * @param filterParams
	 * @param pageable
	 * @return
	 */
	public XaResult<Page<OrderVo>> findOrderListByFilter(
			Map<String, Object> filterParams, Pageable pageable) throws BusinessException;
	/**
	 * pc端校验兑换码
	 * author：changlu
	 * time:2016-01-14 16:40:00
	 * @param businessId 
	 * @param businessType		
	 * @param orderNo
	 * @param orderStatus
	 * @return
	 */
	public XaResult<List<OrderVo>> findOrder(Long businessId,Integer businessType,
			String orderNo, Integer orderStatus) throws BusinessException;
	/**
	 * pc端校验兑换码
	 * author：changlu
	 * time:2016-01-15 15:40:00
	 * @param id 
	 * @param objectName
	 * @param  businessType,		
	 * @return
	 */
	public XaResult<List<OrderVo>> findOrderDetailById(Integer businessType,String orderNo) throws BusinessException;

	
	
	/**
	 * 后台校验码管理
	 * author：songjian
	 * time:2016-01-15 15:40:00
	 * @param pageable 
	 * @param filterParams	
	 * @return
	 */
	public XaResult<Page<OrderVo>> findExchangeNEStatusPage(Pageable pageable,
			Map<String, Object> filterParams)throws BusinessException;
	/**
	 * @Title: updateOrder
	 * @Description: 发货之后修改订单
	 * @param model
	 * @return    
	 */
	public XaResult<Boolean> updateOrder(String[] modelId,String address,String sentData )throws BusinessException;
	/**
	 * 后台结算管理列表
	 * author：changlu
	 * time:2016-01-25 15:23:00
	 * @param id 
	 * @param filterParams
	 * @param  pageable,		
	 * @return
	 */
	public XaResult<Page<OrderVo>> findAccountList(
			Map<String, Object> filterParams, Pageable pageable) throws BusinessException;
	//导出
	public void exportdata(Map<String, Object> filterParams,HttpServletResponse response) throws BusinessException;
	//导出赛事门票
	public void exportdataMatchTickets(Map<String, Object> filterParams,HttpServletResponse response) throws BusinessException;
	//兑换赛事门票
	public XaResult<Order> updateRredeemCode(Long matchId, String redeemCode) throws BusinessException;
	
}
