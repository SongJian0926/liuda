package com.web.liuda.remote.service.impl;

import com.web.liuda.business.entity.VoteOption;
import com.web.liuda.remote.vo.VoteOptionVo;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.repository.VoteOptionRepository;
import com.web.liuda.remote.service.ApiVoteOptionService;
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
 * 类的说明：前端APIVoteOption接口实现类
 **/
@Service("ApiVoteOptionService")
@Transactional(readOnly = false)
public class ApiVoteOptionServiceImpl extends BaseService<VoteOption> implements ApiVoteOptionService{

	@Autowired
	VoteOptionRepository voteOptionRepository;
	
	@Override
	public XaResult<VoteOptionVo> findOne(Long tId) throws BusinessException {
		VoteOption obj = voteOptionRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<VoteOptionVo> xr = new XaResult<VoteOptionVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),VoteOptionVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<List<VoteOptionVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<VoteOption> page = voteOptionRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), VoteOption.class), pageable);
		XaResult<List<VoteOptionVo>> xr = new XaResult<List<VoteOptionVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), VoteOptionVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<List<VoteOptionVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<VoteOption> page = voteOptionRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), VoteOption.class), pageable);
		XaResult<List<VoteOptionVo>> xr = new XaResult<List<VoteOptionVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), VoteOptionVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<VoteOptionVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		XaResult<VoteOptionVo> xr = new XaResult<VoteOptionVo>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				VoteOption obj = voteOptionRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = voteOptionRepository.save(obj);
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj), VoteOptionVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<VoteOptionVo> createModel(VoteOption model)
			throws BusinessException {
		XaResult<VoteOptionVo> xr = new XaResult<VoteOptionVo>();
		VoteOption obj = voteOptionRepository.save(model);
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), VoteOptionVo.class));
		xr.getObject().setId(obj.getId());
		return xr;
	}

	@Override
	public XaResult<VoteOptionVo> updateModel(VoteOption model)
			throws BusinessException {
		VoteOption obj = voteOptionRepository.findOne(model.getId());
		XaResult<VoteOptionVo> xr = new XaResult<VoteOptionVo>();
		if (XaUtil.isNotEmpty(obj)) {
		obj.setOptionName(model.getOptionName());
		obj.setVoteId(model.getVoteId());
		obj.setNum(model.getNum());
			obj = voteOptionRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), VoteOptionVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

}
