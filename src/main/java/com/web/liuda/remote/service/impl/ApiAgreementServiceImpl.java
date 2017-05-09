package com.web.liuda.remote.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.entity.Agreement;
import com.web.liuda.business.repository.AgreementRepository;
import com.web.liuda.remote.service.ApiAgreementService;
import com.web.liuda.remote.vo.AgreementVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APIAgreement接口实现类
 **/
@Service("ApiAgreementService")
@Transactional(readOnly = false)
public class ApiAgreementServiceImpl extends BaseService<Agreement> implements ApiAgreementService{

	@Autowired
	AgreementRepository agreementRepository;
	
	@Override
	public XaResult<AgreementVo> findOne(Long tId) throws BusinessException {
		Agreement obj = agreementRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<AgreementVo> xr = new XaResult<AgreementVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),AgreementVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<List<AgreementVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Agreement> page = agreementRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Agreement.class), pageable);
		XaResult<List<AgreementVo>> xr = new XaResult<List<AgreementVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), AgreementVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<List<AgreementVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Agreement> page = agreementRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Agreement.class), pageable);
		XaResult<List<AgreementVo>> xr = new XaResult<List<AgreementVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), AgreementVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<AgreementVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		XaResult<AgreementVo> xr = new XaResult<AgreementVo>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Agreement obj = agreementRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = agreementRepository.save(obj);
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj), AgreementVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<AgreementVo> createModel(Agreement model)
			throws BusinessException {
		XaResult<AgreementVo> xr = new XaResult<AgreementVo>();
		Agreement obj = agreementRepository.save(model);
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), AgreementVo.class));
		xr.getObject().setId(obj.getId());
		return xr;
	}

	@Override
	public XaResult<AgreementVo> updateModel(Agreement model)
			throws BusinessException {
		Agreement obj = agreementRepository.findOne(model.getId());
		XaResult<AgreementVo> xr = new XaResult<AgreementVo>();
		if (XaUtil.isNotEmpty(obj)) {
		obj.setContent(model.getContent());
		obj.setType(model.getType());
			obj = agreementRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), AgreementVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	//说明文档
	@Override
	public XaResult<AgreementVo> findByType(Integer type) {
		XaResult<AgreementVo> xr = new XaResult<AgreementVo>();
		Agreement vo=agreementRepository.findByTypeAndStatus(type, XaConstant.Status.valid);
		if(XaUtil.isNotEmpty(vo)){
		xr.setObject(JSON.parseObject(JSON.toJSONString(vo), AgreementVo.class));
		}else{
			xr.setMessage("该类型说明文档未配置");
		}
		return xr;
	}

}
