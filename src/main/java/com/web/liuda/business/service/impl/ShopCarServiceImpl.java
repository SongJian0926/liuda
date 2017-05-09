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
import com.web.liuda.business.entity.ShopCar;
import com.web.liuda.business.repository.ShopCarRepository;
import com.web.liuda.business.service.ShopCarService;

@Service("ShopCarService")
@Transactional(readOnly = false)
public class ShopCarServiceImpl extends BaseService<ShopCar> implements ShopCarService {

	@Autowired
	private ShopCarRepository shopCarRepository;
	
	/**
	 * 查询单条ShopCar信息
	 * @param tId
	 * @return 返回单个ShopCar对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<ShopCar> findOne(Long modelId) throws BusinessException {
		ShopCar obj = new ShopCar();
		if(modelId != 0){
			obj = shopCarRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<ShopCar> xr = new XaResult<ShopCar>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的ShopCar数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象ShopCar集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<ShopCar>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<ShopCar> page = shopCarRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), ShopCar.class), pageable);
		XaResult<Page<ShopCar>> xr = new XaResult<Page<ShopCar>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的ShopCar数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象ShopCar集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<ShopCar>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<ShopCar> page = shopCarRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), ShopCar.class), pageable);
		XaResult<Page<ShopCar>> xr = new XaResult<Page<ShopCar>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存ShopCar信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<ShopCar> saveOrUpdate(ShopCar model) throws BusinessException {
		ShopCar obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = shopCarRepository.findOne(model.getId());
		}else{
			obj = new ShopCar();
		}
		obj.setShopId(model.getShopId());
		obj.setStandardId(model.getStandardId());
		obj.setUserId(model.getUserId());
		obj.setShopNumber(model.getShopNumber());
		obj = shopCarRepository.save(obj);
		XaResult<ShopCar> xr = new XaResult<ShopCar>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改ShopCar状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回ShopCar对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<ShopCar> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<ShopCar> xr = new XaResult<ShopCar>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				ShopCar obj = shopCarRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = shopCarRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
}
