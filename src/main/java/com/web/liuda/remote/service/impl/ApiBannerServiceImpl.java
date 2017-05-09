package com.web.liuda.remote.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.entity.Banner;
import com.web.liuda.business.repository.BannerRepository;
import com.web.liuda.remote.service.ApiBannerService;
import com.web.liuda.remote.vo.BannerVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APIBanner接口实现类
 **/
@Service("ApiBannerService")
@Transactional(readOnly = false)
public class ApiBannerServiceImpl extends BaseService<Banner> implements ApiBannerService{

	@Autowired
	BannerRepository bannerRepository;
	
	@Override
	public XaResult<BannerVo> findOne(Long tId) throws BusinessException {
		return null;
	}

	@Override
	public XaResult<List<BannerVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		return null;
	}

	@Override
	public XaResult<List<BannerVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Banner> page = bannerRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Banner.class), pageable);
		XaResult<List<BannerVo>> xr = new XaResult<List<BannerVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), BannerVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<BannerVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		return null;
	}

	@Override
	public XaResult<BannerVo> createModel(Banner model)
			throws BusinessException {
		return null;
	}

	@Override
	public XaResult<BannerVo> updateModel(Banner model)
			throws BusinessException {
		return null;
	}

}
