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
import com.web.liuda.business.entity.History;
import com.web.liuda.business.repository.HistoryRepository;
import com.web.liuda.business.service.HistoryService;

@Service("HistoryService")
@Transactional(readOnly = false)
public class HistoryServiceImpl extends BaseService<History> implements HistoryService {

	@Autowired
	private HistoryRepository historyRepository;
	
	/**
	 * 查询单条History信息
	 * @param tId
	 * @return 返回单个History对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<History> findOne(Long modelId) throws BusinessException {
		History obj = new History();
		if(modelId != 0){
			obj = historyRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<History> xr = new XaResult<History>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的History数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象History集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<History>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<History> page = historyRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), History.class), pageable);
		XaResult<Page<History>> xr = new XaResult<Page<History>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的History数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象History集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<History>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<History> page = historyRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), History.class), pageable);
		XaResult<Page<History>> xr = new XaResult<Page<History>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存History信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<History> saveOrUpdate(History model) throws BusinessException {
		History obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = historyRepository.findOne(model.getId());
		}else{
			obj = new History();
		}
		obj.setUserId(model.getUserId());
		obj.setObjectId(model.getObjectId());
		obj.setType(model.getType());
		obj = historyRepository.save(obj);
		XaResult<History> xr = new XaResult<History>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改History状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回History对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<History> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<History> xr = new XaResult<History>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				History obj = historyRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = historyRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
}
