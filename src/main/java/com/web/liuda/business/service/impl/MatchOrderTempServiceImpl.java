package com.web.liuda.business.service.impl;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.web.liuda.business.entity.MatchOrderTemp;
import com.web.liuda.business.repository.MatchOrderTempRepository;
import com.web.liuda.business.service.MatchOrderTempService;

@Service("MatchOrderTempService")
@Transactional(readOnly = false)
public class MatchOrderTempServiceImpl extends BaseService<MatchOrderTemp> implements MatchOrderTempService {

	@Autowired
	private MatchOrderTempRepository matchOrderTempRepository;
	
	/**
	 * 查询单条MatchOrderTemp信息
	 * @param tId
	 * @return 返回单个MatchOrderTemp对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<MatchOrderTemp> findOne(Long modelId) throws BusinessException {
		MatchOrderTemp obj = new MatchOrderTemp();
		if(modelId != 0){
			obj = matchOrderTempRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<MatchOrderTemp> xr = new XaResult<MatchOrderTemp>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的MatchOrderTemp数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象MatchOrderTemp集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<MatchOrderTemp>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<MatchOrderTemp> page = matchOrderTempRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), MatchOrderTemp.class), pageable);
		XaResult<Page<MatchOrderTemp>> xr = new XaResult<Page<MatchOrderTemp>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的MatchOrderTemp数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象MatchOrderTemp集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<MatchOrderTemp>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<MatchOrderTemp> page = matchOrderTempRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), MatchOrderTemp.class), pageable);
		XaResult<Page<MatchOrderTemp>> xr = new XaResult<Page<MatchOrderTemp>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存MatchOrderTemp信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<MatchOrderTemp> saveOrUpdate(MatchOrderTemp model) throws BusinessException {
		MatchOrderTemp obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = matchOrderTempRepository.findOne(model.getId());
		}else{
			obj = new MatchOrderTemp();
		}
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
		obj.setCarNumber(model.getCarNumber());
		obj.setAge(model.getAge());
		obj.setType(model.getType());
		obj = matchOrderTempRepository.save(obj);
		XaResult<MatchOrderTemp> xr = new XaResult<MatchOrderTemp>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改MatchOrderTemp状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回MatchOrderTemp对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<MatchOrderTemp> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<MatchOrderTemp> xr = new XaResult<MatchOrderTemp>();
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
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
}
