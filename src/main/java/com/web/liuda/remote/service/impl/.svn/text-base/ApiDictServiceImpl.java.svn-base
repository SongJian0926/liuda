package com.web.liuda.remote.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.entity.Dict;
import com.web.liuda.business.repository.DictRepository;
import com.web.liuda.remote.service.ApiDictService;
import com.web.liuda.remote.vo.DictItemVo;
import com.web.liuda.remote.vo.DictVo;
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
 * 类的说明：前端APIDict接口实现类
 **/
@Service("ApiDictService")
@Transactional(readOnly = false)
public class ApiDictServiceImpl extends BaseService<Dict> implements ApiDictService{

	@Autowired
	DictRepository dictRepository;
	
	@Override
	public XaResult<DictVo> findOne(Long tId) throws BusinessException {
		Dict obj = dictRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<DictVo> xr = new XaResult<DictVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),DictVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<List<DictVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Dict> page = dictRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Dict.class), pageable);
		XaResult<List<DictVo>> xr = new XaResult<List<DictVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), DictVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}
	/**
	 * @Title: findDictList
	 * @Description: 查询字典列表(攻略标签、赛事标签、俱乐部类型、俱乐部等级)
	 * author：changlu
	 * time:2016-4-8 11:32:00
	 * @return    
	 */
	@Override
	public XaResult<List<DictVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		XaResult<List<DictVo>> xr = new XaResult<List<DictVo>>();
		StringBuffer sql=new StringBuffer("select di.id,di.dict_id,di.dict_name,di.create_time from tb_xa_dictitem di ");
		sql.append("inner join tb_xa_dict d on d.id=di.dict_id where d.status="+XaConstant.Status.valid+" and di.status="+XaConstant.Status.valid+" ");
		sql.append("and d.type=? order by di.sort asc,di.create_time desc");
		List<Object> param=new ArrayList<Object>();
		param.add(filterParams.get("EQ_type"));
		List<Object[]> objs=this.queryParams(sql.toString(), pageable.getPageNumber(), pageable.getPageNumber()*pageable.getPageSize(), param);
		List<DictVo> vos = new ArrayList<DictVo>();
		//攻略标签、赛事标签、俱乐部类型增加 不限
		if(Integer.valueOf(filterParams.get("EQ_type")+"")==1||Integer.valueOf(filterParams.get("EQ_type")+"")==2||Integer.valueOf(filterParams.get("EQ_type")+"")==3){
			DictVo vo=new DictVo();
			DictItemVo dictItemVo=new DictItemVo();
			dictItemVo.setDictName("不限");
			vo.setDictItemVo(dictItemVo);
			vos.add(vo);
		}
		for(Object[] obj : objs){
			DictVo vo=new DictVo();
			/*vo.setId(((BigInteger)obj[1]).longValue());*/
			//字段子表对象
			DictItemVo dictItemVo=new DictItemVo();
			dictItemVo.setId(XaUtil.isEmpty(obj[0]) ? null:((BigInteger)obj[0]).longValue());
			dictItemVo.setDictName((String)obj[2]);
			vo.setDictItemVo(dictItemVo);
			vos.add(vo);
		}
		
		xr.setObject(vos);
		return xr;
	}

	@Override
	public XaResult<DictVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		XaResult<DictVo> xr = new XaResult<DictVo>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Dict obj = dictRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = dictRepository.save(obj);
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj), DictVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<DictVo> createModel(Dict model)
			throws BusinessException {
		XaResult<DictVo> xr = new XaResult<DictVo>();
		Dict obj = dictRepository.save(model);
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), DictVo.class));
		xr.getObject().setId(obj.getId());
		return xr;
	}

	@Override
	public XaResult<DictVo> updateModel(Dict model)
			throws BusinessException {
		Dict obj = dictRepository.findOne(model.getId());
		XaResult<DictVo> xr = new XaResult<DictVo>();
		if (XaUtil.isNotEmpty(obj)) {
		obj.setDictName(model.getDictName());
			obj = dictRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), DictVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

}
