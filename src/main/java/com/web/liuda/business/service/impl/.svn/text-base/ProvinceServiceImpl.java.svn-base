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
import com.web.liuda.business.entity.Province;
import com.web.liuda.business.repository.ProvinceRepository;
import com.web.liuda.business.service.ProvinceService;

@Service("ProvinceService")
@Transactional(readOnly = false)
public class ProvinceServiceImpl extends BaseService<Province> implements ProvinceService {

	@Autowired
	private ProvinceRepository provinceRepository;
	
	/**
	 * 查询单条Province信息
	 * @param tId
	 * @return 返回单个Province对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Province> findOne(Long modelId) throws BusinessException {
		Province obj = new Province();
		if(modelId != 0){
			obj = provinceRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<Province> xr = new XaResult<Province>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的Province数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Province集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Province>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Province> page = provinceRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Province.class), pageable);
		XaResult<Page<Province>> xr = new XaResult<Page<Province>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的Province数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Province集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Province>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Province> page = provinceRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Province.class), pageable);
		XaResult<Page<Province>> xr = new XaResult<Page<Province>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存Province信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Province> saveOrUpdate(Province model) throws BusinessException {
		Province obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = provinceRepository.findOne(model.getId());
		}else{
			obj = new Province();
		}
		obj.setProvinceCode(model.getProvinceCode());
		obj.setProvinceName(model.getProvinceName());
		obj = provinceRepository.save(obj);
		XaResult<Province> xr = new XaResult<Province>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改Province状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回Province对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Province> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<Province> xr = new XaResult<Province>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Province obj = provinceRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = provinceRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<List<Province>> findProvinceNEStatus(Integer status)
			throws BusinessException {
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		List<Province> page = provinceRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Province.class));
		XaResult<List<Province>> xr = new XaResult<List<Province>>();
		xr.setObject(page);
		return xr;
	}

	@Override
	public XaResult<Province> findProvinceByProvinceCode(Integer status, String provinceCode) throws BusinessException {
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		filters.put("provinceCode", new SearchFilter("provinceCode", Operator.EQ, provinceCode));
		Province page = provinceRepository.findOne(DynamicSpecifications
				.bySearchFilter(filters.values(), Province.class));
		XaResult<Province> xr = new XaResult<Province>();
		xr.setObject(page);
		return xr;
	}
	
}
