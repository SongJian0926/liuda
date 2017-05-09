package com.web.liuda.remote.service.impl;

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
import com.web.liuda.business.entity.MatchOrderDetail;
import com.web.liuda.business.entity.MatchOrderTemp;
import com.web.liuda.business.repository.MatchOrderDetailRepository;
import com.web.liuda.business.repository.MatchOrderTempRepository;
import com.web.liuda.remote.service.ApiMatchOrderTempService;
import com.web.liuda.remote.vo.MatchOrderTempVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APIMatchOrderTemp接口实现类
 **/
@Service("ApiMatchOrderTempService")
@Transactional(readOnly = false)
public class ApiMatchOrderTempServiceImpl extends BaseService<MatchOrderTemp> implements ApiMatchOrderTempService{

	@Autowired
	MatchOrderTempRepository matchOrderTempRepository;
	@Autowired
	MatchOrderDetailRepository matchOrderDetailRepository;
	
	@Override
	public XaResult<MatchOrderTempVo> findOne(Long tId) throws BusinessException {
		MatchOrderTemp obj = matchOrderTempRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<MatchOrderTempVo> xr = new XaResult<MatchOrderTempVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),MatchOrderTempVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<List<MatchOrderTempVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<MatchOrderTemp> page = matchOrderTempRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), MatchOrderTemp.class), pageable);
		XaResult<List<MatchOrderTempVo>> xr = new XaResult<List<MatchOrderTempVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), MatchOrderTempVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}
	/*
	 * 临时报名人员信息
	 * author：changlu
	 * time:2016-04-13 15:11:00
	 */
	@Override
	public XaResult<List<MatchOrderTempVo>> findMacthOrderTempList(
			Long userId, Long clubEventId,Integer type)
			throws BusinessException {
		XaResult<List<MatchOrderTempVo>> xr=new XaResult<List<MatchOrderTempVo>>();
		
		List<MatchOrderTemp> matchOrderTemps=matchOrderTempRepository.findByUserIdAndMatchIdAndTypeAndStatusNot(userId,clubEventId,type,XaConstant.Status.delete);
		xr.setObject(JSON.parseArray(JSON.toJSONString(matchOrderTemps), MatchOrderTempVo.class));
		return xr;
	}
	
	@Override
	public XaResult<List<MatchOrderTempVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<MatchOrderTemp> page = matchOrderTempRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), MatchOrderTemp.class), pageable);
		XaResult<List<MatchOrderTempVo>> xr = new XaResult<List<MatchOrderTempVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), MatchOrderTempVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<MatchOrderTempVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		XaResult<MatchOrderTempVo> xr = new XaResult<MatchOrderTempVo>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				MatchOrderTemp obj = matchOrderTempRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = matchOrderTempRepository.save(obj);
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj), MatchOrderTempVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	/**
	 * 保存报名人员信息
	 * author:changlu
	 * time:2016-05-05 09:54:00
	 * 
	 */
	@Override
	public XaResult<MatchOrderTempVo> createModel(MatchOrderTemp model)
			throws BusinessException {
		XaResult<MatchOrderTempVo> xr = new XaResult<MatchOrderTempVo>();
		List<MatchOrderTemp> matchOrderTemps=new ArrayList<MatchOrderTemp>();
		List<MatchOrderDetail> matchOrderDetails=new ArrayList<MatchOrderDetail>();
		if(model.getType()==JConstant.MatchOrderType.EVENTORDER){
			matchOrderTemps=matchOrderTempRepository.findByMobileAndMatchIdAndTypeAndStatus(model.getMobile(),model.getMatchId(),model.getType(),XaConstant.Status.valid);
			matchOrderDetails=matchOrderDetailRepository.findByMobileAndMatchIdAndTypeAndStatus(model.getMobile(),model.getMatchId(),model.getType(),XaConstant.Status.valid);
			if(XaUtil.isNotEmpty(matchOrderTemps)||XaUtil.isNotEmpty(matchOrderDetails)){
				xr.error("该手机号已报名！");
				return xr;
			}
		}
		if(model.getType()==JConstant.MatchOrderType.MATCHORDER){
			matchOrderTemps=matchOrderTempRepository.findByIdCardAndMatchIdAndTypeAndStatus(model.getIdCard(),model.getMatchId(),model.getType(),XaConstant.Status.valid);
			matchOrderDetails=matchOrderDetailRepository.findByIdCardAndMatchIdAndTypeAndStatus(model.getIdCard(),model.getMatchId(),model.getType(),XaConstant.Status.valid);
			if(XaUtil.isNotEmpty(matchOrderTemps)||XaUtil.isNotEmpty(matchOrderDetails)){
				xr.error("该身份证号已报名！");
				return xr;
			}
		}
		
		MatchOrderTemp obj = matchOrderTempRepository.save(model);
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), MatchOrderTempVo.class));
		xr.getObject().setId(obj.getId());
		return xr;
	}
	//修改报名人员信息
	@Override
	public XaResult<MatchOrderTempVo> updateModel(MatchOrderTemp model)
			throws BusinessException {
		MatchOrderTemp obj = matchOrderTempRepository.findOne(model.getId());
		XaResult<MatchOrderTempVo> xr = new XaResult<MatchOrderTempVo>();
		if (XaUtil.isNotEmpty(obj)) {
			//if(XaUtil.isNotEmpty(model.getRealName())){	
				obj.setRealName(model.getRealName());
			//}
			//if(XaUtil.isNotEmpty(model.getMobile())){	
				obj.setMobile(model.getMobile());
			//}
			//if(XaUtil.isNotEmpty(model.getSex())){
				obj.setSex(model.getSex());
			//}
			//if(XaUtil.isNotEmpty(model.getBloodType())){
				obj.setBloodType(model.getBloodType());
			//}
			//if(XaUtil.isNotEmpty(model.getIdCard())){
				obj.setIdCard(model.getIdCard());
			//}
			//if(XaUtil.isNotEmpty(model.getProfileNum())){	
				obj.setProfileNum(model.getProfileNum());
			//}
			//if(XaUtil.isNotEmpty(model.getMatchGroup())){	
				obj.setMatchGroup(model.getMatchGroup());
			//}
			//if(XaUtil.isNotEmpty(model.getCarModel())){
				obj.setCarModel(model.getCarModel());
			//}
			//if(XaUtil.isNotEmpty(model.getCarTeam())){
				obj.setCarTeam(model.getCarTeam());
			//}
			//if(XaUtil.isNotEmpty(model.getEmeMobile())){
				obj.setEmeMobile(model.getEmeMobile());
			//}
			//if(XaUtil.isNotEmpty(model.getExperience())){	
				obj.setExperience(model.getExperience());
			//}
			//if(XaUtil.isNotEmpty(model.getHonor())){
				obj.setHonor(model.getHonor());
			//}
			//if(XaUtil.isNotEmpty(model.getCarNumber())){
				obj.setCarNumber(model.getCarNumber());
			//}
			//if(XaUtil.isNotEmpty(model.getAge())){
				obj.setAge(model.getAge());
			//}
			
			obj = matchOrderTempRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), MatchOrderTempVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

}
