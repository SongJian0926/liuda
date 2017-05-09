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
import com.web.liuda.business.entity.ShopRecord;
import com.web.liuda.business.repository.ShopRecordRepository;
import com.web.liuda.business.service.ShopRecordService;

@Service("ShopRecordService")
@Transactional(readOnly = false)
public class ShopRecordServiceImpl extends BaseService<ShopRecord> implements ShopRecordService {

	@Autowired
	private ShopRecordRepository shopRecordRepository;
	
	/**
	 * 查询单条ShopRecord信息
	 * @param tId
	 * @return 返回单个ShopRecord对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<ShopRecord> findOne(Long modelId) throws BusinessException {
		ShopRecord obj = new ShopRecord();
		if(modelId != 0){
			obj = shopRecordRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<ShopRecord> xr = new XaResult<ShopRecord>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的ShopRecord数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象ShopRecord集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<ShopRecord>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<ShopRecord> page = shopRecordRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), ShopRecord.class), pageable);
		XaResult<Page<ShopRecord>> xr = new XaResult<Page<ShopRecord>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的ShopRecord数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象ShopRecord集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<ShopRecord>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<ShopRecord> page = shopRecordRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), ShopRecord.class), pageable);
		XaResult<Page<ShopRecord>> xr = new XaResult<Page<ShopRecord>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存ShopRecord信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<ShopRecord> saveOrUpdate(ShopRecord model) throws BusinessException {
		ShopRecord obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = shopRecordRepository.findOne(model.getId());
		}else{
			obj = new ShopRecord();
		}
		obj.setShopId(model.getShopId());
		obj.setStandardId(model.getStandardId());
		obj.setUserId(model.getUserId());
		obj.setBuyNumber(model.getBuyNumber());
		obj = shopRecordRepository.save(obj);
		XaResult<ShopRecord> xr = new XaResult<ShopRecord>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改ShopRecord状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回ShopRecord对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<ShopRecord> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<ShopRecord> xr = new XaResult<ShopRecord>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				ShopRecord obj = shopRecordRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = shopRecordRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
}
