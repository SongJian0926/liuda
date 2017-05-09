package com.web.liuda.remote.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.entity.FeedBack;
import com.web.liuda.business.repository.FeedBackRepository;
import com.web.liuda.remote.service.ApiFeedBackService;
import com.web.liuda.remote.vo.FeedBackVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APIFeedBack接口实现类
 **/
@Service("ApiFeedBackService")
@Transactional(readOnly = false)
public class ApiFeedBackServiceImpl extends BaseService<FeedBack> implements ApiFeedBackService{

	@Autowired
	FeedBackRepository feedBackRepository;
	
	@Override
	public XaResult<FeedBackVo> findOne(Long tId) throws BusinessException {
		FeedBack obj = feedBackRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<FeedBackVo> xr = new XaResult<FeedBackVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),FeedBackVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<List<FeedBackVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<FeedBack> page = feedBackRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), FeedBack.class), pageable);
		XaResult<List<FeedBackVo>> xr = new XaResult<List<FeedBackVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), FeedBackVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}
	//查询反馈意见列表
	@Override
	public XaResult<Page<FeedBackVo>> findPageNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		if (status == null) {
			status = XaConstant.Status.delete;
		}
		Page<FeedBack> feedBacks = feedBackRepository.findByUserIdAndStatusNot(Long.valueOf(filterParams.get("EQ_userId").toString()),status,pageable);
		//将查询的结果转为List<MessageVo>
		List<FeedBackVo> vos=JSON.parseArray(JSON.toJSONString(feedBacks.getContent()), FeedBackVo.class);
		//统计记录条数
		Integer count=feedBackRepository.getCountPage(Long.valueOf(filterParams.get("EQ_userId").toString()));
		Page<FeedBackVo> pages=new MyPage<FeedBackVo>(pageable.getPageNumber(),pageable.getPageSize(),vos,count);
		XaResult<Page<FeedBackVo>> xr = new XaResult<Page<FeedBackVo>>();
		xr.setObject(pages);
		return xr;
	}
	@Override
	public XaResult<List<FeedBackVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<FeedBack> page = feedBackRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), FeedBack.class), pageable);
		XaResult<List<FeedBackVo>> xr = new XaResult<List<FeedBackVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), FeedBackVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<FeedBackVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		XaResult<FeedBackVo> xr = new XaResult<FeedBackVo>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				FeedBack obj = feedBackRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = feedBackRepository.save(obj);
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj), FeedBackVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<FeedBackVo> createModel(FeedBack model)
			throws BusinessException {
		XaResult<FeedBackVo> xr = new XaResult<FeedBackVo>();
		FeedBack obj = feedBackRepository.save(model);
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), FeedBackVo.class));
		xr.getObject().setId(obj.getId());
		return xr;
	}

	@Override
	public XaResult<FeedBackVo> updateModel(FeedBack model)
			throws BusinessException {
		FeedBack obj = feedBackRepository.findOne(model.getId());
		XaResult<FeedBackVo> xr = new XaResult<FeedBackVo>();
		if (XaUtil.isNotEmpty(obj)) {
		obj.setUserId(model.getUserId());
		obj.setContent(model.getContent());
			obj = feedBackRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), FeedBackVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

}
