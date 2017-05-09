package com.web.liuda.remote.service.impl;

import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.MatchOrder;
import com.web.liuda.business.entity.MatchOrderDetail;
import com.web.liuda.business.entity.RefundOrder;
import com.web.liuda.remote.vo.MatchOrderDetailVo;
import com.web.liuda.remote.vo.MatchOrderVo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.repository.MatchOrderDetailRepository;
import com.web.liuda.business.repository.MatchOrderRepository;
import com.web.liuda.business.repository.RefundOrderRepository;
import com.web.liuda.remote.service.ApiMatchOrderDetailService;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.GetRandomOrderno;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wxpay.PayReqRefundData;
import com.wxpay.PayService;
import com.wxpay.common.Configure;
import com.wxpay.common.Signature;
import com.wxpay.common.XMLParser;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APIMatchOrderDetail接口实现类
 **/
@Service("ApiMatchOrderDetailService")
@Transactional(readOnly = false)
public class ApiMatchOrderDetailServiceImpl extends BaseService<MatchOrderDetail> implements ApiMatchOrderDetailService{

	@Autowired
	MatchOrderDetailRepository matchOrderDetailRepository;
	@Autowired
	RefundOrderRepository refundOrderRepository;
	@Autowired
	MatchOrderRepository matchOrderRepository;
	
	/*
	 * 兑换码是否存在
	 * author：changlu
	 * time:2016-04-26 11:35:00
	 */
	@Override
	public XaResult<MatchOrderDetailVo> findOrder(Long userId,String redeemCode,Long clubEventId) throws BusinessException {
		
		XaResult<MatchOrderDetailVo> xr = new XaResult<MatchOrderDetailVo>();
		MatchOrderDetail obj = matchOrderDetailRepository.findByRedeemCodeAndMatchIdAndTypeAndStatus(redeemCode,clubEventId,JConstant.MatchOrderType.EVENTORDER,XaConstant.Status.valid);
		if(XaUtil.isNotEmpty(obj)){
			if(obj.getIsValid().equals(JConstant.BooleanStatus.FALSE+"")){
				obj.setIsValid(JConstant.BooleanStatus.TRUE+"");
				matchOrderDetailRepository.save(obj);
			}else if(obj.getIsValid().equals(JConstant.BooleanStatus.TRUE+"")){
				xr.error("已核销");
				return xr;
			}
		}else{
			xr.error("验证失败，兑换码不存在！");
			return xr;
		}
		return xr;
	}
	
	@Override
	public XaResult<MatchOrderDetailVo> findOne(Long tId) throws BusinessException {
		MatchOrderDetail obj = matchOrderDetailRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<MatchOrderDetailVo> xr = new XaResult<MatchOrderDetailVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),MatchOrderDetailVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<List<MatchOrderDetailVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<MatchOrderDetail> page = matchOrderDetailRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), MatchOrderDetail.class), pageable);
		XaResult<List<MatchOrderDetailVo>> xr = new XaResult<List<MatchOrderDetailVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), MatchOrderDetailVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<List<MatchOrderDetailVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<MatchOrderDetail> page = matchOrderDetailRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), MatchOrderDetail.class), pageable);
		XaResult<List<MatchOrderDetailVo>> xr = new XaResult<List<MatchOrderDetailVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), MatchOrderDetailVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<MatchOrderDetailVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		XaResult<MatchOrderDetailVo> xr = new XaResult<MatchOrderDetailVo>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				MatchOrderDetail obj = matchOrderDetailRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = matchOrderDetailRepository.save(obj);
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj), MatchOrderDetailVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<MatchOrderDetailVo> createModel(MatchOrderDetail model)
			throws BusinessException {
		XaResult<MatchOrderDetailVo> xr = new XaResult<MatchOrderDetailVo>();
		MatchOrderDetail obj = matchOrderDetailRepository.save(model);
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), MatchOrderDetailVo.class));
		xr.getObject().setId(obj.getId());
		return xr;
	}

	@Override
	public XaResult<MatchOrderDetailVo> updateModel(MatchOrderDetail model)
			throws BusinessException {
		MatchOrderDetail obj = matchOrderDetailRepository.findOne(model.getId());
		XaResult<MatchOrderDetailVo> xr = new XaResult<MatchOrderDetailVo>();
		if (XaUtil.isNotEmpty(obj)) {
		obj.setRealName(model.getRealName());
		obj.setMobile(model.getMobile());
		obj.setSex(model.getSex());
		obj.setBloodType(model.getBloodType());
		obj.setIdCard(model.getIdCard());
		obj.setProfileNum(model.getProfileNum());
		obj.setMatchGroup(model.getMatchGroup());
		obj.setCarModel(model.getCarModel());
		obj.setCarTeam(model.getCarTeam());
		obj.setEmeMobile(model.getEmeMobile());
		obj.setExperience(model.getExperience());
		obj.setHonor(model.getHonor());
		obj.setSubAmt(model.getSubAmt());
		obj.setMatchId(model.getMatchId());
		obj.setMatchOrderId(model.getMatchOrderId());
		obj.setCarNumber(model.getCarNumber());
		obj.setAge(model.getAge());
		obj.setIsValid(model.getIsValid());
			obj = matchOrderDetailRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), MatchOrderDetailVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<MatchOrderDetailVo> cancelMatchOrderDetail(MatchOrderVo ordervo, Long matchOrderDetailId, String reason) throws BusinessException {
		MatchOrderDetail obj = matchOrderDetailRepository.findOne(matchOrderDetailId);
		XaResult<MatchOrderDetailVo> xr = new XaResult<MatchOrderDetailVo>();
		if (XaUtil.isNotEmpty(obj)) {
			
			MatchOrder matchOrder = matchOrderRepository.findOne(obj.getMatchOrderId());
			
			RefundOrder ro = new RefundOrder();
			ro.setOrderId(obj.getMatchOrderId());
			ro.setOrderDetailId(obj.getId());
			ro.setRefundNo(GetRandomOrderno.getRandomString(16));//XaUtil.getToDayStrAsFileName()+GetRandomOrderno.getRandomString(10)
			ro.setReason(reason);
			ro.setRefundAmt(obj.getSubAmt());

			obj.setIsRefund(JConstant.BooleanStatus.FALSE);
			matchOrderDetailRepository.save(obj);
			//退款动作
			if(obj.getType().equals(JConstant.MatchOrderType.EVENTORDER) && !matchOrder.getPayType().equals(JConstant.PayType.ALIPAY))//支付宝的话，不允许直接退
			{
				boolean isrefund = false;
				//根据不同的支付平台执行退款动作(不需要释放库存)
				try {
					isrefund = this.refundOrder(ordervo.getOnlineAmt(), ordervo.getOrderNo(),ordervo.getPayType(),ro);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(isrefund)
				{
					obj.setIsRefund(JConstant.BooleanStatus.TRUE);
					matchOrderDetailRepository.save(obj);
					ro.setRefundStatus(JConstant.RefundStatus.REFUNDING);
				}
				else
				{
					ro.setRefundStatus(JConstant.RefundStatus.APPLYREFUND);
				}
				ro = refundOrderRepository.save(ro);
			}
			else
			{
				ro.setRefundStatus(JConstant.RefundStatus.APPLYREFUND);
				ro = refundOrderRepository.save(ro);
			}
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), MatchOrderDetailVo.class));
			xr.getObject().setEnrollStatus(ro.getRefundStatus());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		
		return xr;
	}
	
	private boolean refundOrder(Double onlineAmt, String orderNo, Integer payType, RefundOrder ro) throws Exception {
		//根据不同的支付平台执行退款动作(不需要释放库存)
		switch(payType)
		{
			case JConstant.PayType.WXPAY://微信
				return RefundWXPAY(onlineAmt, orderNo, ro);
			case JConstant.PayType.ALIPAY://支付宝
//				return RefundALIPAY(onlineAmt, orderNo, ro);
				break;
			case JConstant.PayType.UNIONPAY://银联
				break;
		}
		return false;
	}
	
	private boolean RefundWXPAY(Double onlineAmt, String orderNo, RefundOrder ro) throws Exception
	{
		String nonce_str = XaUtil.generateRandomCharAndNumber(16);
		PayReqRefundData data = new PayReqRefundData();
		data.setAppid(Configure.getAppID());
    	data.setMch_id(Configure.getMchID());
    	data.setNonce_str(nonce_str);
    	data.setOut_trade_no(orderNo);
    	data.setOut_refund_no(ro.getRefundNo());
//    	data.setTotal_fee((int)(onlineAmt*1));
//    	data.setRefund_fee((int)(ro.getRefundAmt()*1));    	
    	data.setTotal_fee((int)(onlineAmt*100));
    	data.setRefund_fee((int)(ro.getRefundAmt()*100));   
//    	data.setTotal_fee(1);
//    	data.setRefund_fee(1);
    	data.setOp_user_id(Configure.getMchID());
    	data.setSign(Signature.getSign(data));
    	//请求数据获取结果
    	String str = PayService.refund(data);
    	System.out.println(str);
    	Map<String,Object> map = XMLParser.getMapFromXML_WxRefund(str);
    	//验证是否成功
    	if("SUCCESS".equals(map.get("result_code")) && "SUCCESS".equals(map.get("return_code"))){
    		//重新生成签名后返回给APP
    		return true;
    	}
    	return false;
	}
//	private boolean RefundALIPAY(Double onlineAmt, String orderNo, RefundOrder ro) throws Exception
//	{
//		com.alipay.PayReqRefundData data = new com.alipay.PayReqRefundData();
//		data.setApp_id(AlipayConfig.app_id);
//		data.setMethod("alipay.trade.refund");
//		data.setCharset("utf-8");
//		data.setSign_type(AlipayConfig.sign_type);
//		data.setTimestamp(XaUtil.getToDayStr());
//		data.setVersion("1.0");
//    	data.setOut_trade_no(orderNo);
//    	data.setRefund_amount("0.01");
////    	data.setRefund_amount((BigDecimal)(ro.getRefundAmt()*1));   
//    	data.setOut_request_no(ro.getRefundNo()); 	
//    	
//    	//请求数据获取结果
//    	String str = com.alipay.PayService.refund(data);
//    	System.out.println("返回参数:"+str);
//    	
//    	Map<String, Object> map = XMLParser.getMapFromXML(str);
//		if(map!=null && map.get("is_success")!=null && map.get("is_success").equals("T"))
//		{
//			return true;
//		}
//		return false;
//    	
////    	Map<String,Object> map = XMLParser.getMapFromXML(str);
////    	//验证是否成功
////    	if("SUCCESS".equals(map.get("result_code")) && "SUCCESS".equals(map.get("return_code"))){
////    		//重新生成签名后返回给APP
////    		return true;
////    	}
////    	return false;
//	}
	
}
