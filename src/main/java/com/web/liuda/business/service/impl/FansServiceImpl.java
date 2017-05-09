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
import com.web.liuda.business.entity.Fans;
import com.web.liuda.business.repository.FansRepository;
import com.web.liuda.business.service.FansService;

@Service("FansService")
@Transactional(readOnly = false)
public class FansServiceImpl extends BaseService<Fans> implements FansService {

	@Autowired
	private FansRepository fansRepository;
	
	/**
	 * 查询单条Fans信息
	 * @param tId
	 * @return 返回单个Fans对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Fans> findOne(Long modelId) throws BusinessException {
		Fans obj = new Fans();
		if(modelId != 0){
			obj = fansRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<Fans> xr = new XaResult<Fans>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的Fans数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Fans集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Fans>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Fans> page = fansRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Fans.class), pageable);
		XaResult<Page<Fans>> xr = new XaResult<Page<Fans>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的Fans数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Fans集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Fans>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Fans> page = fansRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Fans.class), pageable);
		XaResult<Page<Fans>> xr = new XaResult<Page<Fans>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存Fans信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Fans> saveOrUpdate(Fans model) throws BusinessException {
		Fans obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = fansRepository.findOne(model.getId());
		}else{
			obj = new Fans();
		}
		obj.setUserId(model.getUserId());
		obj.setFansId(model.getFansId());
		obj = fansRepository.save(obj);
		XaResult<Fans> xr = new XaResult<Fans>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改Fans状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回Fans对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Fans> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<Fans> xr = new XaResult<Fans>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Fans obj = fansRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = fansRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
}
