package com.web.liuda.remote.service.impl;

import com.web.liuda.business.entity.Collect;
import com.web.liuda.remote.vo.CollectVo;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.repository.CollectRepository;
import com.web.liuda.remote.service.ApiCollectService;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APICollect接口实现类
 **/
@Service("ApiCollectService")
@Transactional(readOnly = false)
public class ApiCollectServiceImpl extends BaseService<Collect> implements ApiCollectService{

	@Autowired
	CollectRepository collectRepository;
	
	@Override
	public XaResult<CollectVo> findOne(Long tId) throws BusinessException {
		Collect obj = collectRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<CollectVo> xr = new XaResult<CollectVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),CollectVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<List<CollectVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Collect> page = collectRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Collect.class), pageable);
		XaResult<List<CollectVo>> xr = new XaResult<List<CollectVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), CollectVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<List<CollectVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Collect> page = collectRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Collect.class), pageable);
		XaResult<List<CollectVo>> xr = new XaResult<List<CollectVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), CollectVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}
	/* 取消关注
	 * author:changlu
	 * time:2016-04-19 14:24:00
	 * */
	@Override
	public XaResult<CollectVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		XaResult<CollectVo> xr = new XaResult<CollectVo>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Collect obj = collectRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = collectRepository.save(obj);
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj), CollectVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
	/* 关注
	 * author:changlu
	 * time:2016-04-19 14:24:00
	 * */
	@Override
	public XaResult<CollectVo> createModel(Collect model)
			throws BusinessException {
		XaResult<CollectVo> xr = new XaResult<CollectVo>();
		//判断是否关注过
		Collect collect=collectRepository.findByObjectIdAndUserIdAndTypeAndStatus(model.getObjectId(),model.getUserId(),model.getType(), XaConstant.Status.valid);
		if(XaUtil.isNotEmpty(collect)){
			xr.error("已收藏");
			return xr;
		}
		Collect obj = collectRepository.save(model);
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), CollectVo.class));
		xr.getObject().setId(obj.getId());
		return xr;
	}

	@Override
	public XaResult<CollectVo> updateModel(Collect model)
			throws BusinessException {
		Collect obj = collectRepository.findOne(model.getId());
		XaResult<CollectVo> xr = new XaResult<CollectVo>();
		if (XaUtil.isNotEmpty(obj)) {
		obj.setObjectId(model.getObjectId());
		obj.setType(model.getType());
		obj.setUserId(model.getUserId());
			obj = collectRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), CollectVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

}
