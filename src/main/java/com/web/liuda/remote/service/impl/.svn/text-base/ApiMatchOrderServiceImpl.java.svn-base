package com.web.liuda.remote.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.constant.channel.SDKClient;
import com.web.liuda.business.entity.ClubEvent;
import com.web.liuda.business.entity.Macth;
import com.web.liuda.business.entity.MatchOrder;
import com.web.liuda.business.entity.MatchOrderDetail;
import com.web.liuda.business.entity.MatchOrderTemp;
import com.web.liuda.business.entity.RefundOrder;
import com.web.liuda.business.repository.ClubEventRepository;
import com.web.liuda.business.repository.MacthRepository;
import com.web.liuda.business.repository.MatchOrderDetailRepository;
import com.web.liuda.business.repository.MatchOrderRepository;
import com.web.liuda.business.repository.MatchOrderTempRepository;
import com.web.liuda.business.repository.RefundOrderRepository;
import com.web.liuda.remote.service.ApiMatchOrderService;
import com.web.liuda.remote.vo.ClubEventVo;
import com.web.liuda.remote.vo.MatchOrderDetailVo;
import com.web.liuda.remote.vo.MatchOrderVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.GetRandomOrderno;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APIMatchOrder接口实现类
 **/
@Service("ApiMatchOrderService")
@Transactional(readOnly = false)
public class ApiMatchOrderServiceImpl extends BaseService<MatchOrder> implements ApiMatchOrderService{

	@Autowired
	MatchOrderRepository matchOrderRepository;
	@Autowired
	ClubEventRepository clubEventRepository;
	@Autowired
	MacthRepository macthRepository;
	@Autowired
	MatchOrderTempRepository matchOrderTempRepository;
	@Autowired
	MatchOrderDetailRepository matchOrderDetailRepository;
	@Autowired
	RefundOrderRepository refundOrderRepository;
	
	@Override
	public XaResult<MatchOrderVo> findOne(Long tId) throws BusinessException {
		MatchOrder obj = matchOrderRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<MatchOrderVo> xr = new XaResult<MatchOrderVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),MatchOrderVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	/**
	 * @Title: findMatchOrderById
	 * @Description: 赛事、活动订单详情
	 * @param modelId
	 * author:changlu
	 * time:2016-04-20 15:07:00
	 * @return    
	 */
	public XaResult<MatchOrderVo> findMatchOrderDetailById(Long tId,Integer type) throws BusinessException {
		StringBuffer sql=new StringBuffer("");
		//俱乐部活动
		if(XaUtil.isNotEmpty(type)&&type==JConstant.MatchOrderType.EVENTORDER){
			sql.append("SELECT e.id,e.title,e.logo,e.starttime,e.endtime,o.id orderId,o.order_no,o.create_time,o.total_amt,o.online_amt,o.order_status,e.address,e.area_code,o.type,e.deadline,o.is_full,md.is_valid ");
			sql.append(" from tb_xa_matchorder o inner join tb_xa_clubevent e on e.id=o.match_id ");
			sql.append(" left join tb_xa_matchorderdetail md on md.match_order_id=o.id ");
			sql.append(" where o.status="+XaConstant.Status.valid+" and o.type="+JConstant.MatchOrderType.EVENTORDER+" and o.id="+tId+" ");
		}
		//赛事活动
		if(XaUtil.isNotEmpty(type)&&type==JConstant.MatchOrderType.MATCHORDER){
			sql.append("SELECT m.id,m.title,m.logo,m.startdate,m.enddate,o.id orderId,o.order_no,o.create_time,o.total_amt,o.online_amt,o.order_status,m.address,m.area_code,o.type,m.match_status,m.deadline,o.is_full,md.is_valid ");
			sql.append(" from tb_xa_matchorder o inner join tb_xa_macth m on m.id=o.match_id ");
			sql.append(" left join tb_xa_matchorderdetail md on md.match_order_id=o.id ");
			sql.append(" where o.status="+XaConstant.Status.valid+" and o.type="+JConstant.MatchOrderType.MATCHORDER+" and o.id="+tId+" ");
		}
		
		List<Object[]> objs = this.query(sql.toString(), null,null);
		MatchOrderVo vo = new MatchOrderVo();
		for(Object[] obj : objs){
			ClubEventVo clubEventVo=new ClubEventVo();
			clubEventVo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			clubEventVo.setTitle((String)obj[1]);
			clubEventVo.setLogo((String)obj[2]);
			clubEventVo.setStarttime((String)obj[3]);
			clubEventVo.setEndtime((String)obj[4]);
			//if(XaUtil.isNotEmpty(type)&&type==JConstant.MatchOrderType.EVENTORDER){
				
			//}
			
			clubEventVo.setAddress((String)obj[11]);
			clubEventVo.setAreaCode((String)obj[12]);
			if(XaUtil.isNotEmpty(obj[13])&&Integer.valueOf(obj[13]+"")==JConstant.MatchOrderType.EVENTORDER){
				//报名中
				if(XaUtil.getToDayStr().compareTo((String)obj[14])<0){
					clubEventVo.setEventStatus(JConstant.EventStatus.ENROLL);
				}
				//未开始
				else if(XaUtil.getToDayStr().compareTo((String)obj[14])>0&&XaUtil.getToDayStr().compareTo((String)obj[3])<0){
					clubEventVo.setEventStatus(JConstant.EventStatus.NOBEGIN);
				}
				//进行中
				else if(XaUtil.getToDayStr().compareTo((String)obj[3])>0&&XaUtil.getToDayStr().compareTo((String)obj[4])<0){
					clubEventVo.setEventStatus(JConstant.EventStatus.EVENTING);
				}
				//已结束
				else if(XaUtil.getToDayStr().compareTo((String)obj[4])>0){
					clubEventVo.setEventStatus(JConstant.EventStatus.EVENTEND);
				}
				clubEventVo.setDeadline((String)obj[14]);
				vo.setIsFull((XaUtil.isEmpty(obj[15])?null:Integer.valueOf(obj[15]+"")));
				
			}else{
				clubEventVo.setEventStatus(XaUtil.isEmpty(obj[14])?null:Integer.valueOf(obj[14]+""));	
				clubEventVo.setDeadline((String)obj[15]);
				vo.setIsFull((XaUtil.isEmpty(obj[16])?null:Integer.valueOf(obj[16]+"")));
			}
			
			vo.setClubEventVo(clubEventVo);
			vo.setId(XaUtil.isEmpty(obj[5])?null:((BigInteger)obj[5]).longValue());
			vo.setOrderNo((String)obj[6]);
			vo.setCreateTime((String)obj[7]);
			vo.setTotalAmt(XaUtil.isEmpty(obj[8])?null:Double.valueOf(obj[8]+""));
			vo.setOnlineAmt(XaUtil.isEmpty(obj[9])?null:Double.valueOf(obj[9]+""));
			vo.setOrderStatus(XaUtil.isEmpty(obj[10])?null:(Integer)obj[10]);
			vo.setType(XaUtil.isEmpty(obj[13])?null:Integer.valueOf(obj[13]+""));
			vo.setMatchOrderDetailVos(findMacthOrderDetailList(tId));
			
		}
		XaResult<MatchOrderVo> xr = new XaResult<MatchOrderVo>();
		xr.setObject(vo);
		return xr;
	}
	/*报名人员信息
	*author:changlu
	*time:2016-04-19 16:05:00
	*/
	public List<MatchOrderDetailVo> findMacthOrderDetailList(
			Long matchId)
			throws BusinessException {
		/*StringBuffer sql=new StringBuffer("select d.id,d.real_name,d.mobile,d. from tb_xa_macthorderdetail d");
		sql.append(" where status=1 and ");*/
		List<MatchOrderDetailVo> vos=new ArrayList<MatchOrderDetailVo>();
		
		List<MatchOrderDetail> matchOrderDetails=matchOrderDetailRepository.findByMatchOrderIdAndStatus(matchId,XaConstant.Status.valid);
		for(MatchOrderDetail md:matchOrderDetails){
			MatchOrderDetailVo vo=new MatchOrderDetailVo();
			RefundOrder refundOrder=refundOrderRepository.findByOrderDetailIdAndStatus(md.getId(),XaConstant.Status.valid);
			if(XaUtil.isNotEmpty(refundOrder)){
				vo.setEnrollStatus(XaUtil.isEmpty(refundOrder.getRefundStatus())?null:refundOrder.getRefundStatus());
			}else{
				vo.setEnrollStatus(JConstant.RefundStatus.VALID);
			}
			vo.setId(XaUtil.isEmpty(md.getId())?null:md.getId());
			vo.setCreateTime(md.getCreateTime());
			vo.setRealName(md.getRealName());
			vo.setMobile(md.getMobile());
			vo.setAge(md.getAge());
			vo.setBloodType(md.getBloodType());
			vo.setCarModel(md.getCarModel());
			vo.setCarNumber(md.getCarNumber());
			vo.setCarTeam(md.getCarTeam());
			vo.setEmeMobile(md.getEmeMobile());
			vo.setExperience(md.getExperience());
			vo.setHonor(md.getHonor());
			vo.setIdCard(md.getIdCard());
			vo.setIsValid(XaUtil.isNotEmpty(md.getIsValid())?Integer.parseInt(md.getIsValid()):-1);
			vo.setMatchGroup(md.getMatchGroup());
			vo.setMatchId(md.getMatchId());
			vo.setMatchOrderId(md.getMatchOrderId());
			vo.setProfileNum(md.getProfileNum());
			vo.setRedeemCode(md.getRedeemCode());
			vo.setSex(md.getSex());
			vo.setSubAmt(md.getSubAmt());
			vos.add(vo);
		}
		
		//vos=JSON.parseArray(JSON.toJSONString(matchOrderDetails), MatchOrderDetailVo.class);
		/*for(MatchOrderDetailVo md:vos){
			RefundOrder refundOrder=refundOrderRepository.findByOrderDetailIdAndStatus(md.get(i).getId(),XaConstant.Status.valid);
			if(XaUtil.isNotEmpty(refundOrder)){
				md.setEnrollStatus(XaUtil.isEmpty(refundOrder.getRefundStatus())?null:refundOrder.getRefundStatus());
			}
			
		}*/
		return vos;
	}
	@Override
	public XaResult<List<MatchOrderVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<MatchOrder> page = matchOrderRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), MatchOrder.class), pageable);
		XaResult<List<MatchOrderVo>> xr = new XaResult<List<MatchOrderVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), MatchOrderVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<List<MatchOrderVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<MatchOrder> page = matchOrderRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), MatchOrder.class), pageable);
		XaResult<List<MatchOrderVo>> xr = new XaResult<List<MatchOrderVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), MatchOrderVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<MatchOrderVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		XaResult<MatchOrderVo> xr = new XaResult<MatchOrderVo>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				MatchOrder obj = matchOrderRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = matchOrderRepository.save(obj);
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj), MatchOrderVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	/**
	 * 支付时修改支付方式
	 * author:changlu
	 * time:2016-06-16 15:07:00
	 * @return    
	 */
	@Override
	public XaResult<MatchOrderVo> updatePayType(MatchOrder model) throws BusinessException {
		XaResult<MatchOrderVo> xr = new XaResult<MatchOrderVo>();
		MatchOrder matchOrder=matchOrderRepository.findByOrderNoAndStatusNot(model.getOrderNo(), XaConstant.Status.delete);
		if(XaUtil.isNotEmpty(matchOrder)){
			matchOrder.setPayType(model.getPayType());
			matchOrderRepository.save(matchOrder);
			xr.setObject(JSON.parseObject(JSON.toJSONString(matchOrder), MatchOrderVo.class));
		}
		
		return xr;
	}
	@Override
	public XaResult<MatchOrderVo> createMatchOrder(String matchOrderTempIds,MatchOrder model)
			throws BusinessException {
		XaResult<MatchOrderVo> xr=new XaResult<MatchOrderVo>();
		/*List<MatchOrder> matchOrder=matchOrderRepository.findByMatchIdAndUserIdAndTypeAndOrderStatusNotAndStatusNot(model.getMatchId(),model.getUserId(),model.getType(),JConstant.MatchOrderStatus.ORDERFAILURE,XaConstant.Status.delete);
		if(XaUtil.isNotEmpty(matchOrder)){
			xr.error("用户已报名！");
			return xr;
		}*/
		int enrollNumber=matchOrderRepository.findByMatchIdAndTypeAndOrderStatusNotAndStatusNot(model.getMatchId(),model.getType());
		//俱乐部活动
		ClubEvent clubEvent = null;
		if(model.getType()==JConstant.MatchOrderType.EVENTORDER){
			clubEvent=clubEventRepository.findByIdAndStatusNot(model.getMatchId(),XaConstant.Status.delete);
			if(XaUtil.getToDayStr().compareTo(clubEvent.getDeadline())>0){
				xr.error("对不起，报名已结束！");
				return xr;
			}
			if(clubEvent.getMaxNum()-enrollNumber<model.getOrderNumber()){
				xr.error("对不起，报名人数已满，不能报名！");
				return xr;
			}
			
		}
		//赛事
		if(model.getType()==JConstant.MatchOrderType.MATCHORDER){
			Macth macth=macthRepository.findByIdAndStatusNot(model.getMatchId(),XaConstant.Status.delete);
			if(XaUtil.getToDayStr().compareTo(macth.getDeadline())>0){
				xr.error("对不起，报名已结束！");
				return xr;
			}
			if(macth.getMaxNum()-enrollNumber<model.getOrderNumber()){
				xr.error("对不起，报名人数已满，不能报名！");
				return xr;
			}
		}
		String[] tempIds1=matchOrderTempIds.split(",");
		//从临时表中获取的数据插入到订单详细表中
		for(String tempId:tempIds1){
			MatchOrderTemp matchOrderTemp= matchOrderTempRepository.findByIdAndStatusNot(Long.valueOf(tempId),XaConstant.Status.delete);
			if(XaUtil.isEmpty(matchOrderTemp)){
				xr.error("已报名!");
				return xr;
			}
		}
		MatchOrder obj = matchOrderRepository.save(model);
		//如果下单成功，保存订单明细信息
		if(XaUtil.isNotEmpty(obj)){
			
			String[] tempIds=matchOrderTempIds.split(",");
			//从临时表中获取的数据插入到订单详细表中
			for(String tempId:tempIds){
				MatchOrderTemp matchOrderTemp= matchOrderTempRepository.findByIdAndStatusNot(Long.valueOf(tempId),XaConstant.Status.delete);
				/*sql.append("insert into tb_xa_matchorderdetail(create_time,age,blood_type,car_number,car_team,eme_mobile,experience,honor,id_card,match_group,mobile,profile_num,real_name,sex,match_id,redeem_code,match_order_id,sub_amt,is_valid) values ");
				MatchOrderTemp matchOrderTemp= matchOrderTempRepository.findByIdAndStatusNot(Long.valueOf(tempId),XaConstant.Status.delete);
				System.out.println(matchOrderTemp.getAge());*/
				/*Integer age=XaUtil.isEmpty(matchOrderTemp.getAge())?null:matchOrderTemp.getAge();
				String carNumber=XaUtil.isEmpty(matchOrderTemp.getCarNumber())?null:matchOrderTemp.getBloodType();
				String bloodtype=XaUtil.isEmpty(matchOrderTemp.getBloodType())?null:matchOrderTemp.getCarNumber();
				String carTeam=XaUtil.isEmpty(matchOrderTemp.getCarTeam())?null:matchOrderTemp.getCarTeam();
				String emeMobile=XaUtil.isEmpty(matchOrderTemp.getEmeMobile())?null:matchOrderTemp.getEmeMobile();
				
				String experience=XaUtil.isEmpty(matchOrderTemp.getExperience())?null:matchOrderTemp.getExperience();
				String honor=XaUtil.isEmpty(matchOrderTemp.getHonor())?null:matchOrderTemp.getHonor();
				String idCard=XaUtil.isEmpty(matchOrderTemp.getIdCard())?null:matchOrderTemp.getIdCard();
				String matchGroup=XaUtil.isEmpty(matchOrderTemp.getMatchGroup())?null:matchOrderTemp.getMatchGroup();
				
				
				String mobile=XaUtil.isEmpty(matchOrderTemp.getMobile())?null:matchOrderTemp.getMobile();
				String profileNum=XaUtil.isEmpty(matchOrderTemp.getProfileNum())?null:matchOrderTemp.getProfileNum();
				String realName=XaUtil.isEmpty(matchOrderTemp.getRealName())?null:matchOrderTemp.getRealName();
				Integer sex=XaUtil.isEmpty(matchOrderTemp.getSex())?null:matchOrderTemp.getSex();
				Long matchId=XaUtil.isEmpty(matchOrderTemp.getMatchId())?null:matchOrderTemp.getMatchId();*/
				if(XaUtil.isNotEmpty(matchOrderTemp)){
					MatchOrderDetail matchorderdetail=new MatchOrderDetail();
					matchorderdetail.setAge(XaUtil.isEmpty(matchOrderTemp.getAge())?null:matchOrderTemp.getAge());
					matchorderdetail.setCarModel(XaUtil.isEmpty(matchOrderTemp.getCarModel())?null:matchOrderTemp.getCarModel());
					matchorderdetail.setCarNumber(XaUtil.isEmpty(matchOrderTemp.getCarNumber())?null:matchOrderTemp.getCarNumber());
					matchorderdetail.setBloodType(XaUtil.isEmpty(matchOrderTemp.getBloodType())?null:matchOrderTemp.getBloodType());
					matchorderdetail.setCarTeam(XaUtil.isEmpty(matchOrderTemp.getCarTeam())?null:matchOrderTemp.getCarTeam());
					matchorderdetail.setEmeMobile(XaUtil.isEmpty(matchOrderTemp.getEmeMobile())?null:matchOrderTemp.getEmeMobile());
					
					matchorderdetail.setExperience(XaUtil.isEmpty(matchOrderTemp.getExperience())?null:matchOrderTemp.getExperience());
					matchorderdetail.setHonor(XaUtil.isEmpty(matchOrderTemp.getHonor())?null:matchOrderTemp.getHonor());
					matchorderdetail.setIdCard(XaUtil.isEmpty(matchOrderTemp.getIdCard())?null:matchOrderTemp.getIdCard());
					matchorderdetail.setMatchGroup(XaUtil.isEmpty(matchOrderTemp.getMatchGroup())?null:matchOrderTemp.getMatchGroup());
					
					matchorderdetail.setMobile(XaUtil.isEmpty(matchOrderTemp.getMobile())?null:matchOrderTemp.getMobile());
					matchorderdetail.setProfileNum(XaUtil.isEmpty(matchOrderTemp.getProfileNum())?null:matchOrderTemp.getProfileNum());
					matchorderdetail.setRealName(XaUtil.isEmpty(matchOrderTemp.getRealName())?null:matchOrderTemp.getRealName());
					matchorderdetail.setSex(XaUtil.isEmpty(matchOrderTemp.getSex())?null:matchOrderTemp.getSex());
					matchorderdetail.setMatchId(XaUtil.isEmpty(matchOrderTemp.getMatchId())?null:matchOrderTemp.getMatchId());
					matchorderdetail.setRedeemCode(GetRandomOrderno.getRandomString(16));
					matchorderdetail.setMatchOrderId(obj.getId());
					matchorderdetail.setSubAmt(model.getOnlineAmt()/model.getOrderNumber());
					matchorderdetail.setIsValid(JConstant.BooleanStatus.FALSE+"");
					matchorderdetail.setType(model.getType());
					matchorderdetail.setIsRefund(JConstant.BooleanStatus.FALSE);
					matchOrderDetailRepository.save(matchorderdetail);
				}else{
					xr.error("下单失败，已报名！");
					return xr;
				}
				
				
				/*sql.append("('"+dateStr+"',"+age+",'"+bloodtype+"','"+carNumber+"','"+carTeam+"','"+emeMobile+"',");
				sql.append("'"+experience+"','"+honor+"','"+idCard+"','"+matchGroup+"','"+mobile+"',");
				sql.append("'"+profileNum+"','"+realName+"',"+sex+","+matchId+",'"+GetRandomOrderno.getRandomString(16)+"',"+obj.getId()+","+model.getOnlineAmt()/model.getOrderNumber()+","+JConstant.BooleanStatus.FALSE+"),");*/
			}
			
			//this.insert(sql.toString().substring(0, sql.length()-1));
			//插入成功之后，清除临时表
			String deleteSql="update tb_xa_matchordertemp set status=3 where user_id="+model.getUserId()+" and match_id="+model.getMatchId()+" and type="+model.getType()+"";
			this.insert(deleteSql);
		}else{
			xr.error("下单失败!");
			return xr;
		}
		MatchOrderVo matchOrderVo = JSON.parseObject(JSON.toJSONString(obj), MatchOrderVo.class);
		if(clubEvent!=null) 
		{
			matchOrderVo.setClubEventVo(JSON.parseObject(JSON.toJSONString(clubEvent), ClubEventVo.class));
		}
		xr.setObject(matchOrderVo);
		xr.getObject().setId(obj.getId());
		return xr;
	}
	
	//支付成功之后调用修改订单状态的方法
	public String modifyOrder1(String orderNo, String price,String paymentNo) {
		try{
			MatchOrder order = matchOrderRepository.findByOrderNoAndStatusNot(orderNo,
					XaConstant.Status.delete);
			if (XaUtil.isNotEmpty(order) && order.getOrderStatus() == JConstant.MatchOrderStatus.ENROLLSUCCESS) {
				return "0001"; // 0001代表已经支付过
			}
			if (XaUtil.isNotEmpty(order) && order.getOrderStatus() == JConstant.MatchOrderStatus.UNPAY) {
				List<MatchOrderDetail> orderDetails = matchOrderDetailRepository.findByMatchOrderIdAndStatusNot(order.getId(),
						XaConstant.Status.delete);
				order.setOrderStatus(JConstant.MatchOrderStatus.ENROLLSUCCESS);
				order.setPaymentNo(paymentNo);
				matchOrderRepository.save(order);
				// 发送短信通知
				if(order.getType() == JConstant.MatchOrderType.EVENTORDER){
					String sql = "SELECT e.title,e.area_code,e.address,e.starttime,e.endtime FROM tb_xa_clubevent e where e.id="+order.getMatchId()+"";
					List<Object[]> objs = this.query(sql.toString(), null, null);
					if (XaUtil.isNotEmpty(objs) && objs.size() == 1) {
						/*StringBuffer content = new StringBuffer();
						content.append("【蹓跶蹓跶】您好，您要参加的活动已经报名成功！活动名称：");
						content.append(objs.get(0)[0]).append("，活动地点：").append(objs.get(0)[1]).append("，"+objs.get(0)[2]);
						content.append("活动时间为：").append(objs.get(0)[3]).append("至").append(objs.get(0)[4]);
						content.append("，您的兑换码是：");*/
						for(MatchOrderDetail orderDetail:orderDetails){
							StringBuffer content = new StringBuffer();
							content.append("【蹓跶蹓跶】您好，您要参加的活动已经报名成功！活动名称：");
							content.append(objs.get(0)[0]).append("，活动地点：").append(objs.get(0)[1]).append(objs.get(0)[2]);
							content.append("，活动时间为：").append(objs.get(0)[3]).append("至").append(objs.get(0)[4]);
							content.append("，您的兑换码是：");
							//content.append(orderDetail.getRedeemCode());
							//StringBuffer ncontent = content;
							content.append(orderDetail.getRedeemCode());
							SDKClient.sendContent(orderDetail.getMobile(), content.toString());
						}
						//SDKClient.sendContent(order.getMobile(), content.toString());
					}
				}else if(order.getType() == JConstant.MatchOrderType.MATCHORDER){
					String sql = "SELECT m.title,m.area_code,m.address,m.startdate,m.enddate FROM tb_xa_macth m where m.id="+order.getMatchId()+"";
					List<Object[]> objs = this.query(sql.toString(), null, null);
					if (XaUtil.isNotEmpty(objs) && objs.size() == 1) {
						
						for(MatchOrderDetail orderDetail:orderDetails){
							StringBuffer content = new StringBuffer();
							content.append("【蹓跶蹓跶】您好，您的参加的赛事报名成功！赛事名称：");
							content.append(objs.get(0)[0]).append("，地点：").append(objs.get(0)[1]).append(objs.get(0)[2]);
							content.append("，赛事时间为：").append(objs.get(0)[3]).append("至").append(objs.get(0)[4]);
							content.append("，您的兑换码是：");
							//content.append(orderDetail.getRedeemCode()+";");
							//StringBuffer ncontent = content;
							content.append(orderDetail.getRedeemCode());
							SDKClient.sendContent(orderDetail.getMobile(), content.toString());
						}
						//SDKClient.sendContent(order.getMobile(), content.toString());
					}
				}
				return "0000"; // 支付成功后状态修改成功
			}
		}catch(Exception e){
			return "0002"; // 支付成功后状态修改失败
		}
		return null;
	}

	@Override
	public XaResult<MatchOrderVo> createModel(MatchOrder model)
			throws BusinessException {
		XaResult<MatchOrderVo> xr = new XaResult<MatchOrderVo>();
		MatchOrder obj = matchOrderRepository.save(model);
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), MatchOrderVo.class));
		xr.getObject().setId(obj.getId());
		return xr;
	}

	@Override
	public XaResult<MatchOrderVo> updateModel(MatchOrder model)
			throws BusinessException {
		MatchOrder obj = matchOrderRepository.findOne(model.getId());
		XaResult<MatchOrderVo> xr = new XaResult<MatchOrderVo>();
		if (XaUtil.isNotEmpty(obj)) {
		obj.setUserId(model.getUserId());
		obj.setMatchId(model.getMatchId());
		obj.setOrderNumber(model.getOrderNumber());
		obj.setOrderStatus(model.getOrderStatus());
		obj.setTotalAmt(model.getTotalAmt());
		obj.setRefundAmt(model.getRefundAmt());
		obj.setPayType(model.getPayType());
		obj.setTradeNum(model.getTradeNum());
		obj.setIsFull(model.getIsFull());
		obj.setOfflineAmt(model.getOfflineAmt());
		obj.setOnlineAmt(model.getOnlineAmt());
		obj.setType(model.getType());
			obj = matchOrderRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), MatchOrderVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

}
