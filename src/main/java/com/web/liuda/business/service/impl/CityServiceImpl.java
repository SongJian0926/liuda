package com.web.liuda.business.service.impl;

import java.util.HashMap;
import java.util.List;
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
import com.web.liuda.business.entity.City;
import com.web.liuda.business.repository.CityRepository;
import com.web.liuda.business.service.CityService;

@Service("CityService")
@Transactional(readOnly = false)
public class CityServiceImpl extends BaseService<City> implements CityService {

	@Autowired
	private CityRepository cityRepository;
	
	/**
	 * 查询单条City信息
	 * @param tId
	 * @return 返回单个City对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<City> findOne(Long modelId) throws BusinessException {
		City obj = new City();
		if(modelId != 0){
			obj = cityRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<City> xr = new XaResult<City>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的City数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象City集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<City>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<City> page = cityRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), City.class), pageable);
		XaResult<Page<City>> xr = new XaResult<Page<City>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的City数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象City集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<City>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<City> page = cityRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), City.class), pageable);
		XaResult<Page<City>> xr = new XaResult<Page<City>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存City信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<City> saveOrUpdate(City model) throws BusinessException {
		City obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = cityRepository.findOne(model.getId());
		}else{
			obj = new City();
		}
		obj.setProvinceCode(model.getProvinceCode());
		obj.setCityCode(model.getCityCode());
		obj.setCityName(model.getCityName());
		obj = cityRepository.save(obj);
		XaResult<City> xr = new XaResult<City>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改City状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回City对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<City> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<City> xr = new XaResult<City>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				City obj = cityRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = cityRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<List<City>> findCityByProvinceCode(Integer status,
			String provinceCode) throws BusinessException {
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		filters.put("provinceCode", new SearchFilter("provinceCode", Operator.EQ, provinceCode));
		List<City> page = cityRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), City.class));
		XaResult<List<City>> xr = new XaResult<List<City>>();
		xr.setObject(page);
		return xr;
	}

	@Override
	public XaResult<City> findCityByCityCode(Integer status,
			String cityCode) throws BusinessException {
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		filters.put("cityCode", new SearchFilter("cityCode", Operator.EQ, cityCode));
		City page = cityRepository.findOne(DynamicSpecifications
				.bySearchFilter(filters.values(), City.class));
		XaResult<City> xr = new XaResult<City>();
		xr.setObject(page);
		return xr;
	}
	
}
