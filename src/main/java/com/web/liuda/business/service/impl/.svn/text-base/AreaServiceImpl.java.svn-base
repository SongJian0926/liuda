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
import com.web.liuda.business.entity.Area;
import com.web.liuda.business.entity.City;
import com.web.liuda.business.entity.Province;
import com.web.liuda.business.repository.AreaRepository;
import com.web.liuda.business.service.AreaService;
import com.web.liuda.business.service.CityService;
import com.web.liuda.business.service.ProvinceService;

@Service("AreaService")
@Transactional(readOnly = false)
public class AreaServiceImpl extends BaseService<Area> implements AreaService {

	@Autowired
	private AreaRepository areaRepository;
	@Autowired
	private CityService cityService;
	@Autowired
	private ProvinceService provinceService;
	
	/**
	 * 查询单条Area信息
	 * @param tId
	 * @return 返回单个Area对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Area> findOne(Long modelId) throws BusinessException {
		Area obj = new Area();
		if(modelId != 0){
			obj = areaRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<Area> xr = new XaResult<Area>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的Area数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Area集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Area>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Area> page = areaRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Area.class), pageable);
		XaResult<Page<Area>> xr = new XaResult<Page<Area>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的Area数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Area集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Area>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Area> page = areaRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Area.class), pageable);
		XaResult<Page<Area>> xr = new XaResult<Page<Area>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存Area信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Area> saveOrUpdate(Area model) throws BusinessException {
		Area obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = areaRepository.findOne(model.getId());
		}else{
			obj = new Area();
		}
		obj.setCityCode(model.getCityCode());
		obj.setAreaCode(model.getAreaCode());
		obj.setAreaName(model.getAreaName());
		obj = areaRepository.save(obj);
		XaResult<Area> xr = new XaResult<Area>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改Area状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回Area对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Area> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<Area> xr = new XaResult<Area>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Area obj = areaRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = areaRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<List<Area>> findAreaByCityCode(Integer status, String cityCode) throws BusinessException {
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		filters.put("cityCode", new SearchFilter("cityCode", Operator.EQ, cityCode));
		List<Area> page = areaRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Area.class));
		XaResult<List<Area>> xr = new XaResult<List<Area>>();
		xr.setObject(page);
		return xr;
	}

	@Override
	public XaResult<Area> findAreaByAreaCode(Integer status, String areaCode) throws BusinessException {
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		filters.put("areaCode", new SearchFilter("areaCode", Operator.EQ, areaCode));
		Area page = areaRepository.findOne(DynamicSpecifications
				.bySearchFilter(filters.values(), Area.class));
		XaResult<Area> xr = new XaResult<Area>();
		xr.setObject(page);
		return xr;
	}

	@Override
	public String getAreaName(Long areaId) throws BusinessException {
		if(areaId==null) return null;
		XaResult<Area> xrarea = this.findOne(areaId);
		if(xrarea.getCode()==XaConstant.Code.success && xrarea.getObject() instanceof Area)
		{
			Area area = xrarea.getObject();
			String areaName = area.getAreaName();
			XaResult<City> xrcity = cityService.findCityByCityCode(null,area.getCityCode());
			if(xrcity.getCode()==XaConstant.Code.success && xrcity.getObject() instanceof City)
			{
				City city = xrcity.getObject();
				areaName = city.getCityName()+areaName;
				XaResult<Province> xaprovince = provinceService.findProvinceByProvinceCode(null,city.getProvinceCode());
				if(xaprovince.getCode()==XaConstant.Code.success && xaprovince.getObject() instanceof Province)
				{
					Province province = xaprovince.getObject();
					areaName = province.getProvinceName()+areaName;
					return areaName;
				}
			}
		}
		return "";
	}
	
}
