package com.web.liuda.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.MatchOrder;
import com.web.liuda.business.entity.MatchOrderDetail;
import com.web.liuda.business.entity.RefundOrder;
import com.web.liuda.business.repository.ClubEventRepository;
import com.web.liuda.business.repository.MacthRepository;
import com.web.liuda.business.repository.MatchOrderDetailRepository;
import com.web.liuda.business.repository.MatchOrderRepository;
import com.web.liuda.business.repository.RefundOrderRepository;
import com.web.liuda.business.service.RefundOrderService;
import com.web.liuda.remote.vo.MatchOrderDetailVo;
import com.web.liuda.remote.vo.MatchOrderVo;
import com.web.liuda.remote.vo.RefundOrderVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.GetRandomOrderno;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wxpay.PayReqRefundData;
import com.wxpay.PayService;
import com.wxpay.common.Configure;
import com.wxpay.common.Signature;
import com.wxpay.common.XMLParser;

@Service("RefundOrderService")
@Transactional(readOnly = false)
public class RefundOrderServiceImpl extends BaseService<RefundOrder> implements RefundOrderService {

	@Autowired
	private RefundOrderRepository refundOrderRepository;
	@Autowired
	private MatchOrderRepository matchOrderRepository;
	@Autowired
	private MatchOrderDetailRepository matchOrderDetailRepository;
	@Autowired
	private MacthRepository macthRepository;
	@Autowired
	private ClubEventRepository clubEventRepository;
	
	/**
	 * 查询单条RefundOrder信息
	 * @param tId
	 * @return 返回单个RefundOrder对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<RefundOrder> findOne(Long modelId) throws BusinessException {
		RefundOrder obj = new RefundOrder();
		if(modelId != 0){
			obj = refundOrderRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<RefundOrder> xr = new XaResult<RefundOrder>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的RefundOrder数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象RefundOrder集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<RefundOrder>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<RefundOrder> page = refundOrderRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), RefundOrder.class), pageable);
		XaResult<Page<RefundOrder>> xr = new XaResult<Page<RefundOrder>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的RefundOrder数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象RefundOrder集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<RefundOrder>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<RefundOrder> page = refundOrderRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), RefundOrder.class), pageable);
		XaResult<Page<RefundOrder>> xr = new XaResult<Page<RefundOrder>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存RefundOrder信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<RefundOrder> saveOrUpdate(RefundOrder model) throws BusinessException {
		RefundOrder obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = refundOrderRepository.findOne(model.getId());
		}else{
			obj = new RefundOrder();
		}
		obj.setOrderId(model.getOrderId());
		obj.setOrderDetailId(model.getOrderDetailId());
		obj.setRefundAmt(model.getRefundAmt());
		obj.setRefundDate(model.getRefundDate());
		obj.setRefundApplyDate(model.getRefundApplyDate());
		obj.setRefundStatus(model.getRefundStatus());
		obj.setMemo(model.getMemo());
		obj.setReason(model.getReason());
		obj = refundOrderRepository.save(obj);
		XaResult<RefundOrder> xr = new XaResult<RefundOrder>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改RefundOrder状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回RefundOrder对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<RefundOrder> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<RefundOrder> xr = new XaResult<RefundOrder>();
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
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<Page<RefundOrderVo>> findRefundOrderVoNEStatusPage(Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {

		XaResult<Page<RefundOrderVo>> xrvo = new XaResult<Page<RefundOrderVo>>();
		String starttime = "0000-00-00";
		String endtime = "9999-99-99";
		Integer refundStatus = 0;
		
		if(XaUtil.isNotEmpty(filterParams.get("GTE_createTime"))){
			starttime = filterParams.get("GTE_createTime").toString();
		}
		if(XaUtil.isNotEmpty(filterParams.get("LTE_createTime"))){
			endtime = filterParams.get("LTE_createTime").toString();
		}
		if(XaUtil.isNotEmpty(filterParams.get("EQ_refundStatus"))){
			refundStatus = Integer.parseInt(filterParams.get("EQ_refundStatus").toString());
		}
		
		// r.id,r.refundNo,r.refundAmt,r.refundStatus,r.refundApplyDate,r.createTime,r.reason,o.payType,d.realName,d.mobile
		Page<Object[]> objlst = refundOrderRepository.findRefundByTimeAndRefundStatus(starttime,endtime,refundStatus,refundStatus,pageable);
		
		
		List<RefundOrderVo> vos = new ArrayList<RefundOrderVo>();
		for(Object[] ro : objlst)
		{
			RefundOrderVo rvo = new RefundOrderVo();
			rvo.setId(Long.parseLong(ro[0].toString()));
			rvo.setRefundNo(ro[1].toString());
			rvo.setRefundAmt(Double.valueOf(ro[2].toString()));
			rvo.setRefundStatus(Integer.parseInt(ro[3].toString()));
			rvo.setRefundApplyDate(ro[4]==null?"":ro[4].toString());
			rvo.setCreateTime(ro[5].toString());
			rvo.setReason(ro[6]==null?"":ro[6].toString());
			MatchOrderVo mvo = new MatchOrderVo();
			mvo.setPayType(Integer.parseInt(ro[7].toString()));
			mvo.setType(Integer.parseInt(ro[10].toString()));
			rvo.setMatchOrderVo(mvo);
			MatchOrderDetailVo dvo = new MatchOrderDetailVo();
			dvo.setRealName(ro[8]==null?"":ro[8].toString());
			dvo.setMobile(ro[9]==null?"":ro[9].toString());
			rvo.setMatchOrderDetailVo(dvo);
			if(mvo.getType().equals(JConstant.MatchOrderType.MATCHORDER))
			{
				rvo.setMatchTitle(macthRepository.findOne(Long.parseLong(ro[11].toString())).getTitle());
			}
			else
			{
				rvo.setMatchTitle(clubEventRepository.findOne(Long.parseLong(ro[11].toString())).getTitle());
			}
			vos.add(rvo);
		}
		Page<RefundOrderVo> page= new MyPage<RefundOrderVo>(pageable.getPageNumber(),pageable.getPageSize(), vos, Integer.parseInt(String.valueOf(objlst.getTotalElements())));
		xrvo.setObject(page);
		
		
//		XaResult<Page<RefundOrder>> xr = this.findListNEStatusByFilter(status, filterParams, pageable);
//		xrvo.setCode(xr.getCode());
//		xrvo.setMessage(xr.getMessage());
//		if(xr.getObject() instanceof Page)
//		{
//			List<RefundOrderVo> vos = new ArrayList<RefundOrderVo>();
//			for(RefundOrder ro : xr.getObject())
//			{
//				vos.add(JSON.parseObject(JSON.toJSONString(ro),RefundOrderVo.class));
//			}
//			Page<RefundOrderVo> page= new MyPage<RefundOrderVo>(nextPage.intValue(),pageSize.intValue(), vos, xr.getObject().getNumberOfElements());
//			xrvo.setObject(page);
//		}
		return xrvo;
	}

	@Override
	public XaResult<String> applyRefundOrder(String rootpath, String refundIds, Integer refundStatus) throws BusinessException {
		
		XaResult<String> xr = new XaResult<String>();
		if(XaUtil.isNotEmpty(refundIds)){
			String[] rids = refundIds.split(",");
			List<RefundOrder> alipayvos = new ArrayList<RefundOrder>();
			String batchno = XaUtil.getToDayStrAsFileName()+GetRandomOrderno.getRandomString(10);
			for(String rid : rids){
				RefundOrder obj=refundOrderRepository.findByIdAndStatusNot(Long.parseLong(rid), XaConstant.Status.delete);
				if (XaUtil.isNotEmpty(obj)) {
						if(!obj.getRefundStatus().equals(JConstant.RefundStatus.APPLYREFUND))
						{
							continue;
						}
					  if(refundStatus.equals(JConstant.RefundStatus.REFUNDFAIL))//如果是审核不通过，则直接修改就行
					  {
						  obj.setRefundStatus(refundStatus);
						  obj.setRefundDate(XaUtil.getToDayStr());
						  obj = refundOrderRepository.save(obj);
					  }
					  else if(refundStatus.equals(JConstant.RefundStatus.REFUNDING))
					  {
						  MatchOrder marchOrder = matchOrderRepository.findOne(obj.getOrderId());
						  //如果是微信
						  if(marchOrder.getPayType().equals(JConstant.PayType.WXPAY))
						  {
							  boolean b = false;
							  //直接退款
							  try {
								  b = RefundWXPAY(marchOrder.getOnlineAmt(), marchOrder.getOrderNo(), obj);
							  } catch (Exception e) {
								  b = false;
							  }
							  if(!b)
							  {
								  xr.error("该笔订单退款失败:"+obj.getRefundNo()+"，中止退款");
								  return xr;
							  }
							  obj.setRefundStatus(refundStatus);
							  obj.setRefundDate(XaUtil.getToDayStr());
							  obj = refundOrderRepository.save(obj);
							  //修改状态
							  MatchOrderDetail marchOrderDetail = matchOrderDetailRepository.findOne(obj.getOrderDetailId());
							  marchOrderDetail.setIsRefund(JConstant.BooleanStatus.TRUE);
							  matchOrderDetailRepository.save(marchOrderDetail);
						  }
						  else if(marchOrder.getPayType().equals(JConstant.PayType.ALIPAY))//如果是支付宝
						  {
							  alipayvos.add(obj);
						  }
						  else
						  {
							  //暂不处理
						  }
					  }
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
		    }
			if(alipayvos.size()>0)
			{
				//处理支付宝退款
				String html = RefundALIPAY(rootpath, batchno, alipayvos);
				if(html.equals(""))
				{
					xr.error("支付宝退款失败");
				}
				for(RefundOrder ro : alipayvos)
				{
					ro.setBatchNo(batchno);
					ro.setRefundStatus(JConstant.RefundStatus.REFUNDING);
					ro.setRefundDate(XaUtil.getToDayStr());
					ro = refundOrderRepository.save(ro);
					//修改状态
					MatchOrderDetail marchOrderDetail = matchOrderDetailRepository.findOne(ro.getOrderDetailId());
					marchOrderDetail.setIsRefund(JConstant.BooleanStatus.TRUE);
					matchOrderDetailRepository.save(marchOrderDetail);
				}
				xr.setObject(html);
			}
		}
		return xr;
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
    	data.setTotal_fee((int)(onlineAmt*100));
    	data.setRefund_fee((int)(ro.getRefundAmt()*100));    	
    	//data.setTotal_fee(1);
    	//data.setRefund_fee(1);
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

	private String RefundALIPAY(String rootpath ,String batchno, List<RefundOrder> alipayvos) {
		com.alipay.PayReqRefundData data = new com.alipay.PayReqRefundData();
		data.setBatch_no(batchno);
		data.setBatch_num(String.valueOf(alipayvos.size()));
		data.setNotify_url(rootpath+"/liuda/api/alipay/notifyRefund"); 
		String detail_data = "";
		for(RefundOrder ro : alipayvos)
		{
			String paymentno = matchOrderRepository.findOne(ro.getOrderId()).getPaymentNo();
			if(paymentno==null) continue;
			detail_data += paymentno;
			detail_data += "^"+ro.getRefundAmt()+"^"+"#";
		}
		if(detail_data.equals(""))
		{
			return "";
		}
		//格式（支付宝交易号^退款金额^备注），多笔请用#隔开
		data.setDetail_data(detail_data.substring(0, detail_data.length()-1));
		
		String html = "";
		try {
			html = com.alipay.PayService.refund(data);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return html;
	}

	@Override
	public void QueryRefundWxPay() throws Exception {
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		filters.put("status", new SearchFilter("status", Operator.NE, XaConstant.Status.delete));
		filters.put("refundStatus", new SearchFilter("refundStatus", Operator.EQ, JConstant.RefundStatus.REFUNDING));
		
		//查询所有状态为退款中的订单
		List<RefundOrder> mlst = refundOrderRepository.findAll(DynamicSpecifications.bySearchFilter(filters.values(), RefundOrder.class));
		System.out.println(mlst.size());
		for(RefundOrder ro : mlst)
		{
			if(!matchOrderRepository.findOne(ro.getOrderId()).getPayType().equals(JConstant.PayType.WXPAY))
			{
				continue;
			}
			String nonce_str = XaUtil.generateRandomCharAndNumber(16);
			PayReqRefundData data = new PayReqRefundData();
			data.setAppid(Configure.getAppID());
	    	data.setMch_id(Configure.getMchID());
	    	data.setNonce_str(nonce_str);
	    	//data.setOut_trade_no(mo.getOrderNo());
	    	data.setOut_refund_no(ro.getRefundNo());
//	    	data.setTotal_fee((int)(onlineAmt*1));
//	    	data.setRefund_fee((int)(ro.getRefundAmt()*1));  
	    	data.setSign(Signature.getSign(data));
	    	//请求数据获取结果
	    	String str = PayService.refundquery(data);
	    	System.out.println(str);
	    	Map<String,Object> map = XMLParser.getMapFromXML_WxRefund(str);
	    	//验证是否成功
	    	if("SUCCESS".equals(map.get("result_code")) && "SUCCESS".equals(map.get("return_code"))){
	    		//成功后修改状态
	    		ro.setRefundStatus(JConstant.RefundStatus.REFUNDSUCCESS);
	    		refundOrderRepository.save(ro);
	    	}
		}
	}
	
	
}
