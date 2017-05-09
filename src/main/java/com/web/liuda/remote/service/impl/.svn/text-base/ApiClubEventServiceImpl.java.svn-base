package com.web.liuda.remote.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.ClubEvent;
import com.web.liuda.business.entity.ClubMember;
import com.web.liuda.business.entity.Collect;
import com.web.liuda.business.entity.MatchOrder;
import com.web.liuda.business.repository.ClubEventRepository;
import com.web.liuda.business.repository.ClubMemberRepository;
import com.web.liuda.business.repository.CollectRepository;
import com.web.liuda.business.repository.MatchOrderRepository;
import com.web.liuda.remote.service.ApiClubEventService;
import com.web.liuda.remote.vo.ClubEventVo;
import com.web.liuda.remote.vo.MatchOrderDetailVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.excelUtil.ExcelColumn;
import com.web.webstart.base.excelUtil.ExcelHead;
import com.web.webstart.base.excelUtil.ExcelHelper;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APIClubEvent接口实现类
 **/
@Service("ApiClubEventService")
@Transactional(readOnly = false)
public class ApiClubEventServiceImpl extends BaseService<ClubEvent> implements ApiClubEventService{

	@Autowired
	ClubEventRepository clubEventRepository;
	@Autowired
	ClubMemberRepository clubMemberRepository;
	@Autowired
	MatchOrderRepository matchOrderRepository;
	@Autowired
	CollectRepository collectRepository;
	
	/*
	 * 活动详情
	 * author:changlu
	 * time:2016-04-13 14:40:00
	 */
	@Override
	public XaResult<ClubEventVo> findClubEventDetail(Long userId,Long tId,Integer eventStatus) throws BusinessException {
		ClubEvent obj = clubEventRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<ClubEventVo> xr = new XaResult<ClubEventVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),ClubEventVo.class));
			//用户Id不为空时，判断用户是否参加此活动
			if(XaUtil.isNotEmpty(userId)){
				ClubMember clubMember=clubMemberRepository.findByUserIdAndApplyStatusAndStatusNotAndClubId(userId,JConstant.ApplyStatus.CHECKSUCCEED,XaConstant.Status.delete,obj.getClubId());
				//判断用户是否是该俱乐部成员
				if(XaUtil.isNotEmpty(clubMember)){
					xr.getObject().setIsJoin(JConstant.BooleanStatus.TRUE);
				}else{
					xr.getObject().setIsJoin(JConstant.BooleanStatus.FALSE);
				}
				List<MatchOrder> matchOrder=new ArrayList<MatchOrder>();
				matchOrder=matchOrderRepository.findByUserIdAndMatchIdAndTypeAndStatusNot(userId,tId,JConstant.MatchOrderType.EVENTORDER,XaConstant.Status.delete);
				//如果matchOrder不为空，用户已报名
				if(XaUtil.isNotEmpty(matchOrder)){
					xr.getObject().setIsEnroll(JConstant.BooleanStatus.TRUE);
				}else{
					xr.getObject().setIsEnroll(JConstant.BooleanStatus.FALSE);
				}
				Collect collect=collectRepository.findByObjectIdAndUserIdAndTypeAndStatus(tId,userId,JConstant.MatchOrderType.EVENTORDER,XaConstant.Status.valid);
				//判断用户是否已关注该活动
				if(XaUtil.isNotEmpty(collect)){
					xr.getObject().setIsCollect(JConstant.BooleanStatus.TRUE);
					xr.getObject().setCollectId(collect.getId());
				}else{
					xr.getObject().setIsCollect(JConstant.BooleanStatus.FALSE);
				}
			}else{
				xr.getObject().setIsEnroll(JConstant.BooleanStatus.FALSE);
			}
			xr.getObject().setId(obj.getId());
			//根据当前时间判断活动状态,大于报名截止日期且小于活动开始时间,未开始
			if(XaUtil.getToDayStr().compareTo(obj.getDeadline())>0&&obj.getStarttime().compareTo(XaUtil.getToDayStr())>0){
				xr.getObject().setEventStatus(JConstant.EventStatus.NOBEGIN);
				
			}
			//当前时间大于开始日期且小于活动结束时间，进行中
			else if(XaUtil.getToDayStr().compareTo(obj.getStarttime())>0&&obj.getEndtime().compareTo(XaUtil.getToDayStr())>0){
				xr.getObject().setEventStatus(JConstant.EventStatus.EVENTING);
			}
			//当前时间大于活动结束时间，已结束
			else if(XaUtil.getToDayStr().compareTo(obj.getEndtime())>0){
				xr.getObject().setEventStatus(JConstant.EventStatus.EVENTEND);
			}
			//当前时间小于活动报名截止日期，报名中
			else if(XaUtil.getToDayStr().compareTo(obj.getDeadline())<0){
				xr.getObject().setEventStatus(JConstant.EventStatus.ENROLL);
			}
				
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	
	@Override
	public XaResult<ClubEventVo> findOne(Long tId) throws BusinessException {
		ClubEvent obj = clubEventRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<ClubEventVo> xr = new XaResult<ClubEventVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),ClubEventVo.class));
			
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<List<ClubEventVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<ClubEvent> page = clubEventRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), ClubEvent.class), pageable);
		XaResult<List<ClubEventVo>> xr = new XaResult<List<ClubEventVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), ClubEventVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}
	/*
	 * 活动列表
	 * author:changlu
	 * time:2016-04-13 09:40:00
	 */
	@Override
	public XaResult<Page<ClubEventVo>> findClubEventList(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
				throws BusinessException {
		XaResult<Page<ClubEventVo>> xr=new XaResult<Page<ClubEventVo>>();
		StringBuffer sql=new StringBuffer("select cv.id,cv.title,cv.logo,cv.starttime,cv.endtime,cv.deadline,cv.event_status,cv.max_num,cv.price,cv.create_time ");
		sql.append(" from tb_xa_clubevent cv where cv.status="+XaConstant.Status.publish+" and cv.club_id="+Long.valueOf(filterParams.get("EQ_clubId")+"")+"");
		sql.append(" order by cv.create_time desc ");
		StringBuffer countsql=new StringBuffer("select count(*) ");
		countsql.append(" from tb_xa_clubevent cv where cv.status="+XaConstant.Status.publish+" and cv.club_id="+Long.valueOf(filterParams.get("EQ_clubId")+"")+"");
		List<Object[]> objs = this.query(sql.toString(), pageable.getPageNumber()*pageable.getPageSize(), pageable.getPageSize());
		List<Object[]> count = this.query(countsql.toString(), null, null);
		List<ClubEventVo> vos = new ArrayList<ClubEventVo>();
		for(Object[] obj : objs){
			ClubEventVo vo = new ClubEventVo();
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setTitle((String)obj[1]);
			vo.setLogo((String)obj[2]);
			vo.setStarttime((String)obj[3]);
			vo.setEndtime((String)obj[4]);
			vo.setDeadline((String)obj[5]);
			vo.setMaxNum(XaUtil.isEmpty(obj[7])?null:Integer.valueOf(obj[7]+""));
			vo.setPrice(XaUtil.isEmpty(obj[8])?null:Double.valueOf(obj[8]+""));
			vo.setCreateTime((String)obj[9]);
			//报名中
			if(XaUtil.getToDayStr().compareTo((String)obj[5])<0){
				vo.setEventStatus(JConstant.EventStatus.ENROLL);
			}
			//未开始
			else if(XaUtil.getToDayStr().compareTo((String)obj[5])>0&&XaUtil.getToDayStr().compareTo((String)obj[3])<0){
				vo.setEventStatus(JConstant.EventStatus.NOBEGIN);
			}
			//进行中
			else if(XaUtil.getToDayStr().compareTo((String)obj[3])>0&&XaUtil.getToDayStr().compareTo((String)obj[4])<0){
				vo.setEventStatus(JConstant.EventStatus.EVENTING);
			}
			//已结束
			else if(XaUtil.getToDayStr().compareTo((String)obj[4])>0){
				vo.setEventStatus(JConstant.EventStatus.EVENTEND);
			}
			vos.add(vo);
		}
		//分页
		Page<ClubEventVo> page = new MyPage<ClubEventVo>(pageable.getPageNumber(), pageable.getPageSize(), vos, Integer.valueOf(count.get(0) + ""));
		xr.setObject(page);
		return xr;
	}
	/*
	 * web端活动验证列表——web端使用
	 * author:changlu
	 * time:2016-04-28 11:25:00
	 */
	@Override
	public XaResult<Page<ClubEventVo>> findTestClubEventList(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
				throws BusinessException {
		XaResult<Page<ClubEventVo>> xr=new XaResult<Page<ClubEventVo>>();
		StringBuffer sql=new StringBuffer("select cv.id,cv.title,cv.logo,cv.starttime,cv.endtime,cv.deadline,cv.event_status,cv.max_num,cv.price,cv.create_time,cv.club_id ");
		sql.append(" from tb_xa_clubevent cv inner join tb_xa_club c on c.id=cv.club_id ");
		sql.append(" where cv.status="+XaConstant.Status.publish+" and cv.club_id in (select c.id from tb_xa_club c where c.user_id="+Long.valueOf(filterParams.get("EQ_userId")+"")+" and c.status="+XaConstant.Status.valid+" and c.apply_status="+JConstant.ApplyStatus.CHECKSUCCEED+" )");
		sql.append(" and c.user_id="+Long.valueOf(filterParams.get("EQ_userId")+"")+" and cv.status="+XaConstant.Status.publish+" order by cv.create_time desc ");
		StringBuffer countsql=new StringBuffer("select count(*) ");
		countsql.append(" from tb_xa_clubevent cv inner join tb_xa_club c on c.id=cv.club_id where cv.status="+XaConstant.Status.publish+" and cv.club_id in (select c.id from tb_xa_club c where c.user_id="+Long.valueOf(filterParams.get("EQ_userId")+"")+" and c.status="+XaConstant.Status.valid+" and c.apply_status="+JConstant.ApplyStatus.CHECKSUCCEED+" )");
		countsql.append(" and c.user_id="+Long.valueOf(filterParams.get("EQ_userId")+"")+" and cv.status="+XaConstant.Status.publish+" ");
		List<Object[]> objs = this.query(sql.toString(), pageable.getPageNumber()*pageable.getPageSize(), pageable.getPageSize());
		List<Object[]> count = this.query(countsql.toString(), null, null);
		List<ClubEventVo> vos = new ArrayList<ClubEventVo>();
		for(Object[] obj : objs){
			ClubEventVo vo = new ClubEventVo();
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setTitle((String)obj[1]);
			vo.setLogo((String)obj[2]);
			vo.setStarttime((String)obj[3]);
			vo.setEndtime((String)obj[4]);
			vo.setDeadline((String)obj[5]);
			vo.setMaxNum(XaUtil.isEmpty(obj[7])?null:Integer.valueOf(obj[7]+""));
			vo.setPrice(XaUtil.isEmpty(obj[8])?null:Double.valueOf(obj[8]+""));
			vo.setCreateTime((String)obj[9]);
			//报名中
			if(XaUtil.getToDayStr().compareTo((String)obj[5])<0){
				vo.setEventStatus(JConstant.EventStatus.ENROLL);
			}
			//未开始
			else if(XaUtil.getToDayStr().compareTo((String)obj[5])>0&&XaUtil.getToDayStr().compareTo((String)obj[3])<0){
				vo.setEventStatus(JConstant.EventStatus.NOBEGIN);
			}
			//进行中
			else if(XaUtil.getToDayStr().compareTo((String)obj[3])>0&&XaUtil.getToDayStr().compareTo((String)obj[4])<0){
				vo.setEventStatus(JConstant.EventStatus.EVENTING);
			}
			//已结束
			else if(XaUtil.getToDayStr().compareTo((String)obj[4])>0){
				vo.setEventStatus(JConstant.EventStatus.EVENTEND);
			}
			vo.setClubId(XaUtil.isEmpty(obj[10])?null:((BigInteger)obj[10]).longValue());
			vos.add(vo);
		}
		//分页
		Page<ClubEventVo> page = new MyPage<ClubEventVo>(pageable.getPageNumber(), pageable.getPageSize(), vos, Integer.valueOf(count.get(0) + ""));
		xr.setObject(page);
		return xr;
	}
	@Override
	public XaResult<List<ClubEventVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<ClubEvent> page = clubEventRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), ClubEvent.class), pageable);
		XaResult<List<ClubEventVo>> xr = new XaResult<List<ClubEventVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), ClubEventVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<ClubEventVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		XaResult<ClubEventVo> xr = new XaResult<ClubEventVo>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				ClubEvent obj = clubEventRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = clubEventRepository.save(obj);
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj), ClubEventVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	/*
	 * 创建活动
	 * author:changlu
	 * time:2016-04-13 11:40:00
	 */
	@Override
	public XaResult<ClubEventVo> createClubEvent(Long userId,Long clubId,ClubEvent model)
			throws BusinessException {
		XaResult<ClubEventVo> xr = new XaResult<ClubEventVo>();
		//查询该用户是否是该俱乐部的部长
		ClubMember clubMember=clubMemberRepository.findByUserIdAndApplyStatusAndStatusNotAndClubId(userId,JConstant.ApplyStatus.CHECKSUCCEED,XaConstant.Status.delete,clubId);
		if(XaUtil.isNotEmpty(clubMember)&&clubMember.getMemberType()==JConstant.ClubRole.MINISTER){
			ClubEvent obj = clubEventRepository.save(model);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), ClubEventVo.class));
			xr.getObject().setId(obj.getId());
		}else{
			xr.error("只有该俱乐部的部长才能创建活动！");
		}
		return xr;
	}
	
	@Override
	public XaResult<ClubEventVo> createModel(ClubEvent model)
			throws BusinessException {
		XaResult<ClubEventVo> xr = new XaResult<ClubEventVo>();
		ClubEvent obj = clubEventRepository.save(model);
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), ClubEventVo.class));
		xr.getObject().setId(obj.getId());
		return xr;
	}
	/*
	 * 我的活动列表
	 * author:changlu
	 * time:2016-04-19 17:24:00
	 * */
	@Override
	public XaResult<Page<ClubEventVo>> findMyEventList (Long userId,Integer eventStatus,Pageable pageable)
			throws BusinessException {
		String sql="call pro_myEvent(?,?,?,?)";
		List<Object> params=new ArrayList<Object>();
		params.add(eventStatus);
		params.add(userId);
		params.add(pageable.getPageNumber()*pageable.getPageSize());
		params.add(pageable.getPageSize());
		List<Object[]> objs=this.queryCall(sql, params);
		String countsql="call pro_countMyEvent(?,?)";
		List<Object> params1=new ArrayList<Object>();
		params1.add(eventStatus);
		params1.add(userId);
		List<Object[]> count=this.queryCall(countsql, params1);
		List<ClubEventVo> vos=new ArrayList<ClubEventVo>();
		for(Object[] obj:objs){
			ClubEventVo vo=new ClubEventVo();
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setTitle((String)obj[1]);

			//vo.setEventStatus(XaUtil.isEmpty(obj[2])?null:(Integer)obj[2]);
			vo.setCreateTime((String)obj[3]);
			vo.setLogo((String)obj[4]);
			vo.setStarttime((String)obj[5]);
			vo.setEndtime((String)obj[6]);
			vo.setType(XaUtil.isEmpty(obj[7])?null:Integer.valueOf(obj[7]+""));
			vo.setDeadline((String)obj[8]);
			//报名中
			if(XaUtil.getToDayStr().compareTo((String)obj[8])<0){
				vo.setEventStatus(JConstant.EventStatus.ENROLL);
			}
			//未开始
			else if(XaUtil.getToDayStr().compareTo((String)obj[8])>0&&XaUtil.getToDayStr().compareTo((String)obj[5])<0){
				vo.setEventStatus(JConstant.EventStatus.NOBEGIN);
			}
			//进行中
			else if(XaUtil.getToDayStr().compareTo((String)obj[5])>0&&XaUtil.getToDayStr().compareTo((String)obj[6])<0){
				vo.setEventStatus(JConstant.EventStatus.EVENTING);
			}
			//已结束
			else if(XaUtil.getToDayStr().compareTo((String)obj[6])>0){
				vo.setEventStatus(JConstant.EventStatus.EVENTEND);
			}
			vo.setOrderStatus(XaUtil.isEmpty(obj[9])?null:Integer.valueOf(obj[9]+""));
			vo.setMatchOrderId(XaUtil.isEmpty(obj[10])?null:Long.valueOf(obj[10]+""));
			vo.setMemberType(XaUtil.isEmpty(obj[11])?null:Integer.valueOf(obj[11]+""));
			vos.add(vo);
			
		}
		XaResult<Page<ClubEventVo>> xr=new XaResult<Page<ClubEventVo>>();
		Page<ClubEventVo> page = new MyPage<ClubEventVo>(pageable.getPageNumber(), pageable.getPageSize(), vos, Integer.valueOf(count.get(0) + ""));
		xr.setObject(page);
		return xr;
	}
	@Override
	public XaResult<ClubEventVo> updateModel(ClubEvent model)
			throws BusinessException {
		ClubEvent obj = clubEventRepository.findOne(model.getId());
		XaResult<ClubEventVo> xr = new XaResult<ClubEventVo>();
		if (XaUtil.isNotEmpty(obj)) {
			if(XaUtil.isNotEmpty(model.getTitle())){
				obj.setTitle(model.getTitle());
			}
			if(XaUtil.isNotEmpty(model.getLogo())){
				obj.setLogo(model.getLogo());
			}
			if(XaUtil.isNotEmpty(model.getStarttime())){
				obj.setStarttime(model.getStarttime());
			}
			if(XaUtil.isNotEmpty(model.getEndtime())){
				obj.setEndtime(model.getEndtime());
			}
			if(XaUtil.isNotEmpty(model.getPrice())){
				obj.setPrice(model.getPrice());
			}
			if(XaUtil.isNotEmpty(model.getDeadline())){
				obj.setDeadline(model.getDeadline());
			}
			if(XaUtil.isNotEmpty(model.getMaxNum())){
				obj.setMaxNum(model.getMaxNum());
			}
			if(XaUtil.isNotEmpty(model.getContent())){
				obj.setContent(model.getContent());
			}
			if(XaUtil.isNotEmpty(model.getAddress())){
				obj.setAddress(model.getAddress());
			}
			if(XaUtil.isNotEmpty(model.getDisposit())){
				obj.setDisposit(model.getDisposit());
			}
			if(XaUtil.isNotEmpty(model.getMediaPath())){
				obj.setMediaPath(model.getMediaPath());
			}
		
			obj = clubEventRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), ClubEventVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	/*
	 * 活动人员名单(web端)
	 * author:sj
	 * time:2016-04-19 15:00:00
	 */
	@Override
	public XaResult<Page<MatchOrderDetailVo>> findClubEventPersonnelPgae(
			Integer status, Map<String, Object> filterParams, Pageable pageable,Long clubEventId)
			throws BusinessException {
		XaResult<Page<MatchOrderDetailVo>> xr = new XaResult<Page<MatchOrderDetailVo>>();
		
		StringBuffer sql = new StringBuffer("");
		sql.append("select mor.id,mor.real_name,mor.mobile,mor.sex,mor.age,mor.id_card,mor.car_model,mor.car_number,mor.is_valid");
		sql.append(" from tb_xa_matchorder mo INNER JOIN tb_xa_matchorderdetail  mor on mo.id=mor.match_order_id ");
		sql.append(" where mor.status="+XaConstant.Status.valid+" and mo.match_id="+clubEventId+" and mo.type="+JConstant.MatchOrderType.EVENTORDER+" ");
		sql.append(" and mor.id not in (select ro.order_detail_id from tb_xa_refundorder ro where ro.refund_status<>4 and ro.refund_status<>3 and ro.status=1 ) ");
		sql.append(" and mo.id not in (select ro.order_id from tb_xa_refundorder ro where ro.refund_status<>4 and ro.refund_status<>3 and ro.status=1 and ro.order_detail_id is NULL ) ");
		sql.append(" and mo.order_status="+JConstant.MatchOrderStatus.ENROLLSUCCESS+" ");
		StringBuffer sqlCount = new StringBuffer("");
		sqlCount.append("select count(*)");
		sqlCount.append(" from tb_xa_matchorder mo INNER JOIN tb_xa_matchorderdetail mor on mo.id=mor.match_order_id ");
		sqlCount.append(" where mor.status="+XaConstant.Status.valid+" and mo.match_id="+clubEventId+" and mo.type="+JConstant.MatchOrderType.EVENTORDER+" ");
		sqlCount.append(" and mor.id not in (select ro.order_detail_id from tb_xa_refundorder ro where ro.refund_status<>4 and ro.refund_status<>3 and ro.status=1 ) ");
		sqlCount.append(" and mo.id not in (select ro.order_id from tb_xa_refundorder ro where ro.refund_status<>4 and ro.refund_status<>3 and ro.status=1 and ro.order_detail_id is NULL ) ");
		sqlCount.append(" and mo.order_status="+JConstant.MatchOrderStatus.ENROLLSUCCESS+" ");
		List<MatchOrderDetailVo> voList = new ArrayList<MatchOrderDetailVo>();
		List<Object[]> objs = this.query(sql.toString(), pageable.getPageNumber()*pageable.getPageSize(), pageable.getPageSize());
		for(Object[] obj:objs){
			MatchOrderDetailVo vo =new MatchOrderDetailVo();
			vo.setId(Long.parseLong(obj[0]+""));
			vo.setRealName(XaUtil.isNotEmpty((String)obj[1])?(String)obj[1]:"");
			vo.setMobile(XaUtil.isNotEmpty((String)obj[2])?(String)obj[2]:"");
			vo.setSex(XaUtil.isNotEmpty(obj[3])?Integer.parseInt(obj[3]+""):null);
			vo.setAge(XaUtil.isNotEmpty(obj[4])?Integer.parseInt(obj[4]+""):null);
			vo.setIdCard(XaUtil.isNotEmpty((String)obj[5])?(String)obj[5]:"");
			vo.setCarModel(XaUtil.isNotEmpty((String)obj[6])?(String)obj[6]:"");
			vo.setCarNumber(XaUtil.isNotEmpty((String)obj[7])?(String)obj[7]:"");
			vo.setIsValid(XaUtil.isNotEmpty(obj[8])?Integer.parseInt(obj[8]+""):-1);
			voList.add(vo);
		}
		
		Page<MatchOrderDetailVo> page = new MyPage<MatchOrderDetailVo>(pageable.getPageNumber(), pageable.getPageSize(), voList, Integer.parseInt(this.query(sqlCount.toString(), 0, 0).get(0)+""));
		xr.setObject(page);
		return xr;
	}

	//导出数据
	@Transactional(rollbackFor = Exception.class)
	public void exportdata(Map<String, Object> filterParams,HttpServletResponse response,Long clubId) throws BusinessException {	
		//此处构造一个对象的集合
		StringBuffer sql = new StringBuffer("");
		sql.append("select mor.id,mor.real_name,mor.mobile,mor.sex,mor.age,mor.id_card,mor.car_model,mor.car_number,mor.is_valid");
		sql.append(" from tb_xa_matchorder mo INNER JOIN tb_xa_matchorderdetail  mor on mo.id=mor.match_order_id ");
		sql.append(" where mor.status="+XaConstant.Status.valid+" and mo.match_id="+clubId+" and mo.type="+JConstant.MatchOrderType.EVENTORDER+" ");
		sql.append(" and mor.id not in (select ro.order_detail_id from tb_xa_refundorder ro where ro.refund_status<>4 and ro.refund_status<>3 and ro.status=1 ) ");
		sql.append(" and mo.id not in (select ro.order_id from tb_xa_refundorder ro where ro.refund_status<>4 and ro.refund_status<>3 and ro.status=1 and ro.order_detail_id is NULL ) ");
		sql.append(" and mo.order_status="+JConstant.MatchOrderStatus.ENROLLSUCCESS+" ");
		List<MatchOrderDetailVo> voList = new ArrayList<MatchOrderDetailVo>();
		List<Object[]> objs = this.query(sql.toString(), 0, 0);
		for(Object[] obj:objs){
			MatchOrderDetailVo vo =new MatchOrderDetailVo();
			vo.setId(Long.parseLong(obj[0]+""));
			vo.setRealName(XaUtil.isNotEmpty((String)obj[1])?(String)obj[1]:"");
			vo.setMobile(XaUtil.isNotEmpty((String)obj[2])?(String)obj[2]:"");
			vo.setSex(XaUtil.isNotEmpty(obj[3])?Integer.parseInt(obj[3]+""):null);
			vo.setAge(XaUtil.isNotEmpty(obj[4])?Integer.parseInt(obj[4]+""):null);
			vo.setIdCard(XaUtil.isNotEmpty((String)obj[5])?(String)obj[5]:"");
			vo.setCarModel(XaUtil.isNotEmpty((String)obj[6])?(String)obj[6]:"");
			vo.setCarNumber(XaUtil.isNotEmpty((String)obj[7])?(String)obj[7]:"");
			vo.setIsValid(XaUtil.isNotEmpty(obj[8])?Integer.parseInt(obj[8]+""):-1);
			voList.add(vo);
		}
	    //excel结构
	    List<ExcelColumn> excelColumns = new ArrayList<ExcelColumn>();
        excelColumns.add(new ExcelColumn(0, "id", "序号", Cell.CELL_TYPE_STRING));
        excelColumns.add(new ExcelColumn(1, "realName", "姓名", Cell.CELL_TYPE_STRING));
        excelColumns.add(new ExcelColumn(2, "mobile", "手机号", Cell.CELL_TYPE_STRING));
        excelColumns.add(new ExcelColumn(3, "sex", "性别", Cell.CELL_TYPE_STRING));
        excelColumns.add(new ExcelColumn(4, "age", "年龄", Cell.CELL_TYPE_STRING));
        excelColumns.add(new ExcelColumn(5, "idCard", "身份证号", Cell.CELL_TYPE_STRING));
        excelColumns.add(new ExcelColumn(6, "carModel", "车型", Cell.CELL_TYPE_STRING));
        excelColumns.add(new ExcelColumn(7, "carNumber", "车牌号码", Cell.CELL_TYPE_STRING));
        excelColumns.add(new ExcelColumn(8, "isValid", "是否核销", Cell.CELL_TYPE_STRING));
        // 需要特殊转换的单元
	    Map<String,Map> excelColumnsConvertMap = new HashMap<String,Map>();
	    Map<Integer,String> isReceive1 = new HashMap<Integer,String>();
	    isReceive1.put(0,"男");
        isReceive1.put(1,"女");
        excelColumnsConvertMap.put("sex",isReceive1);
	    Map<Integer,String> isReceive = new HashMap<Integer,String>();
	    isReceive.put(0,"未核销");
        isReceive.put(1,"已核销");
        excelColumnsConvertMap.put("isValid",isReceive);
	    
	    //设置头部
	    ExcelHead head = new ExcelHead();
	    head.setRowCount(1); // 模板中头部所占行数
	    head.setColumns(excelColumns);  // 列的定义
	    //执行导出,第一个null是response参数，用来输出到浏览器，第二个null是要导出的数据集合
	    head.setColumnsConvertMap(excelColumnsConvertMap); // 列的转换
	    ExcelHelper.getInstanse().exportExcelFile(response,head,voList);
	    /*XaResult<String> xr = new XaResult<String>();
	    xr.setCode(1);
		//xr.setObject(XaConstant.Code.success+":"+picturePath+"/"+photoFile.getOriginalFilename());
		return xr;*/
	}

}
