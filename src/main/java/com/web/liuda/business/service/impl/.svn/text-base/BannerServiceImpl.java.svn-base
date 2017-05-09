package com.web.liuda.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.liuda.business.entity.Banner;
import com.web.liuda.business.repository.BannerRepository;
import com.web.liuda.business.service.BannerService;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

@Service("BannerService")
@Transactional(readOnly = false)
public class BannerServiceImpl extends BaseService<Banner> implements BannerService {

	@Autowired
	private BannerRepository bannerRepository;
	
	/**
	 * 查询单条Banner信息
	 * @param tId
	 * @return 返回单个Banner对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Banner> findOne(Long modelId) throws BusinessException {
		Banner obj = new Banner();
		if(modelId != 0){
			obj = bannerRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<Banner> xr = new XaResult<Banner>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<List<Banner>> findBannerSort(
			Integer status, Long id) throws BusinessException {
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		List<Banner> vos=bannerRepository.findByIdNotAndStatus(id,status);
		XaResult<List<Banner>> xr = new XaResult<List<Banner>>();
		xr.setObject(vos);
		return xr;
	}
	
	public Banner findSortBySort(Integer sort) {
		return bannerRepository.findBySortAndStatusNot(sort,XaConstant.Status.delete);
	}
	
	/**
	 * 分页查询状态非status的Banner数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Banner集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Banner>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Banner> page = bannerRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Banner.class), pageable);
		XaResult<Page<Banner>> xr = new XaResult<Page<Banner>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的Banner数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Banner集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Banner>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Banner> page = bannerRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Banner.class), pageable);
		XaResult<Page<Banner>> xr = new XaResult<Page<Banner>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存Banner信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Banner> saveOrUpdate(Banner model) throws BusinessException {
		Banner obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = bannerRepository.findOne(model.getId());
		}else{
			obj = new Banner();
		}
		obj.setPicurl(model.getPicurl());
		obj.setLinkurl(model.getLinkurl());
		obj.setSort(model.getSort());
		obj = bannerRepository.save(obj);
		XaResult<Banner> xr = new XaResult<Banner>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改Banner状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回Banner对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Banner> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<Banner> xr = new XaResult<Banner>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Banner obj = bannerRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = bannerRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
}
