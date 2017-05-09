package com.web.liuda.remote.service.impl;

import com.web.liuda.business.entity.VoteLog;
import com.web.liuda.remote.vo.VoteLogVo;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.repository.VoteLogRepository;
import com.web.liuda.remote.service.ApiVoteLogService;
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
 * 类的说明：前端APIVoteLog接口实现类
 **/
@Service("ApiVoteLogService")
@Transactional(readOnly = false)
public class ApiVoteLogServiceImpl extends BaseService<VoteLog> implements ApiVoteLogService{

	@Autowired
	VoteLogRepository voteLogRepository;
	
	@Override
	public XaResult<VoteLogVo> findOne(Long tId) throws BusinessException {
		VoteLog obj = voteLogRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<VoteLogVo> xr = new XaResult<VoteLogVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),VoteLogVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<List<VoteLogVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<VoteLog> page = voteLogRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), VoteLog.class), pageable);
		XaResult<List<VoteLogVo>> xr = new XaResult<List<VoteLogVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), VoteLogVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<List<VoteLogVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<VoteLog> page = voteLogRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), VoteLog.class), pageable);
		XaResult<List<VoteLogVo>> xr = new XaResult<List<VoteLogVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), VoteLogVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<VoteLogVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		XaResult<VoteLogVo> xr = new XaResult<VoteLogVo>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				VoteLog obj = voteLogRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = voteLogRepository.save(obj);
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj), VoteLogVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<VoteLogVo> createModel(VoteLog model)
			throws BusinessException {
		XaResult<VoteLogVo> xr = new XaResult<VoteLogVo>();
		VoteLog obj = voteLogRepository.save(model);
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), VoteLogVo.class));
		xr.getObject().setId(obj.getId());
		return xr;
	}

	@Override
	public XaResult<VoteLogVo> updateModel(VoteLog model)
			throws BusinessException {
		VoteLog obj = voteLogRepository.findOne(model.getId());
		XaResult<VoteLogVo> xr = new XaResult<VoteLogVo>();
		if (XaUtil.isNotEmpty(obj)) {
		obj.setUserId(model.getUserId());
		obj.setVoteId(model.getVoteId());
		obj.setVoteOptionId(model.getVoteOptionId());
			obj = voteLogRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), VoteLogVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

}
