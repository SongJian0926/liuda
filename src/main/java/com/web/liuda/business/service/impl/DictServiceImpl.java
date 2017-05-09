package com.web.liuda.business.service.impl;

import java.util.ArrayList;
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
import com.web.liuda.business.entity.Dict;
import com.web.liuda.business.repository.DictRepository;
import com.web.liuda.business.service.DictService;

@Service("DictService")
@Transactional(readOnly = false)
public class DictServiceImpl extends BaseService<Dict> implements DictService {

	@Autowired
	private DictRepository dictRepository;
	
	/**
	 * 查询单条Dict信息
	 * @param tId
	 * @return 返回单个Dict对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Dict> findOne(Long modelId) throws BusinessException {
		Dict obj = new Dict();
		if(modelId != 0){
			obj = dictRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<Dict> xr = new XaResult<Dict>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	/**
	 * 查询所有Dict的Name信息
	 * author:changlu
	 * time:2016-05-06 09:57:00
	 * @param dictName
	 * @return true 存在  false 不存在
	 * @throws BusinessException
	 */
	public String findDictName(String dictName,Long id) throws BusinessException{
		
		boolean b=false;
		String json = null;
		List<Object[]> objs=null;
		//新增时,验证频道名是否存在（所有频道名）
		StringBuffer sql=new StringBuffer("select d.id,d.dict_name from tb_xa_dict d where d.`status`="+XaConstant.Status.valid+"");
		//编辑时，验证频道名是否存在（除编辑以外的所有频道名）
		StringBuffer sql1=new StringBuffer("select d.id,d.dict_name from tb_xa_dict d where d.`status`="+XaConstant.Status.valid+" ");
		sql1.append("and d.id<>?");
		List<Object> params=new ArrayList<Object>();
		params.add(id);
		//判断新增还是编辑
		if(XaUtil.isEmpty(id)){
			 objs=this.query(sql.toString(), null, null);
		}else{
			 objs=this.queryParams(sql1.toString(), null, null, params);
		}
		if(XaUtil.isNotEmpty(dictName)){
			for(Object[] obj : objs){
				if(dictName.equals((String)obj[1])){
					b=true;
					break;
				}
			}
			if(b){
				json=  "{\"error\":\"已存在\"}";
			}else{
				json= "{\"ok\":\"\"}";
			}
		}
	   return json;
	}
	
	/**
	 * 分页查询状态非status的Dict数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Dict集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Dict>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Dict> page = dictRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Dict.class), pageable);
		XaResult<Page<Dict>> xr = new XaResult<Page<Dict>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的Dict数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Dict集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Dict>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Dict> page = dictRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Dict.class), pageable);
		XaResult<Page<Dict>> xr = new XaResult<Page<Dict>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存Dict信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Dict> saveOrUpdate(Dict model) throws BusinessException {
		XaResult<Dict> xr = new XaResult<Dict>();
		List<Dict> dicts=new ArrayList<Dict>();
		if(XaUtil.isEmpty(model.getId())){
			 dicts=dictRepository.findByTypeAndStatusAndIdNot(model.getType(), XaConstant.Status.valid,Long.valueOf(0));
		}else{
			dicts=dictRepository.findByTypeAndStatusAndIdNot(model.getType(), XaConstant.Status.valid,model.getId());
		}
	
		if(XaUtil.isNotEmpty(dicts)){
			xr.error("该类型的字典已存在！");
			return xr;
		}
		
		Dict obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = dictRepository.findOne(model.getId());
		}else{
			obj = new Dict();
		}
		obj.setDictName(model.getDictName());
		obj.setType(model.getType());
		obj = dictRepository.save(obj);
		
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改Dict状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回Dict对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Dict> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<Dict> xr = new XaResult<Dict>();
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
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
}
