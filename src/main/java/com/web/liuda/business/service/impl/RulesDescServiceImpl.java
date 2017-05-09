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
import com.web.liuda.business.entity.RulesDesc;
import com.web.liuda.business.repository.RulesDescRepository;
import com.web.liuda.business.service.RulesDescService;

@Service("RulesDescService")
@Transactional(readOnly = false)
public class RulesDescServiceImpl extends BaseService<RulesDesc> implements RulesDescService {

	@Autowired
	private RulesDescRepository rulesDescRepository;
	
	/**
	 * 查询单条RulesDesc信息
	 * @param tId
	 * @return 返回单个RulesDesc对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<RulesDesc> findOne(Long modelId) throws BusinessException {
		RulesDesc obj = new RulesDesc();
		if(modelId != 0){
			obj = rulesDescRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<RulesDesc> xr = new XaResult<RulesDesc>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的RulesDesc数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象RulesDesc集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<RulesDesc>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<RulesDesc> page = rulesDescRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), RulesDesc.class), pageable);
		XaResult<Page<RulesDesc>> xr = new XaResult<Page<RulesDesc>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的RulesDesc数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象RulesDesc集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<RulesDesc>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<RulesDesc> page = rulesDescRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), RulesDesc.class), pageable);
		XaResult<Page<RulesDesc>> xr = new XaResult<Page<RulesDesc>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存RulesDesc信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<RulesDesc> saveOrUpdate(RulesDesc model) throws BusinessException {
		RulesDesc obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = rulesDescRepository.findOne(model.getId());
		}else{
			obj = new RulesDesc();
		}
		obj.setContent(model.getContent());
		obj.setType(model.getType());
		obj.setNeedPush(model.getNeedPush());
		obj = rulesDescRepository.save(obj);
		XaResult<RulesDesc> xr = new XaResult<RulesDesc>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改RulesDesc状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回RulesDesc对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<RulesDesc> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<RulesDesc> xr = new XaResult<RulesDesc>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				RulesDesc obj = rulesDescRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = rulesDescRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
}
