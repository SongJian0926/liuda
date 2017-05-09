package com.web.liuda.business.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.liuda.business.entity.DictItem;
import com.web.liuda.business.repository.DictItemRepository;
import com.web.liuda.business.service.DictItemService;
import com.web.liuda.remote.vo.DictItemVo;
import com.web.liuda.remote.vo.DictVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

@Service("DictItemService")
@Transactional(readOnly = false)
public class DictItemServiceImpl extends BaseService<DictItem> implements DictItemService {

	@Autowired
	private DictItemRepository dictItemRepository;
	
	/**
	 * 查询单条DictItem信息
	 * @param tId
	 * @return 返回单个DictItem对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<DictItem> findOne(Long modelId) throws BusinessException {
		DictItem obj = new DictItem();
		if(modelId != 0){
			obj = dictItemRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<DictItem> xr = new XaResult<DictItem>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的DictItem数据
	 * author：changlu
	 * time:2016-5-6 11:32:00
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象DictItem集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<DictVo>> findList(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		StringBuffer sql=new StringBuffer("select di.id,di.dict_name dictItemName,di.sort,di.dict_id,d.dict_name dictName");
		sql.append(" from tb_xa_dictitem di inner join tb_xa_dict d on d.id=di.dict_id ");
		sql.append(" where d.status="+XaConstant.Status.valid+" and di.status="+XaConstant.Status.valid+"");
		
		StringBuffer countsql=new StringBuffer("select count(*) ");
		countsql.append(" from tb_xa_dictitem di inner join tb_xa_dict d on d.id=di.dict_id ");
		countsql.append(" where d.status="+XaConstant.Status.valid+" and di.status="+XaConstant.Status.valid+"");
		List<Object> params=new ArrayList<Object>();
		if(XaUtil.isNotEmpty(filterParams)&&XaUtil.isNotEmpty(filterParams.get("EQ_dictName"))&&XaUtil.isNotEmpty(filterParams.get("EQ_dictId"))){
			sql.append(" and di.dict_name like ? and d.id=? ");
			countsql.append(" and di.dict_name like ? and d.id=? ");
			params.add("%"+filterParams.get("EQ_dictName")+"%");
			params.add(filterParams.get("EQ_dictId"));
		}else if(XaUtil.isNotEmpty(filterParams)&&XaUtil.isNotEmpty(filterParams.get("EQ_dictName"))&&XaUtil.isEmpty(filterParams.get("EQ_dictId"))){
			sql.append(" and di.dict_name like ? ");
			countsql.append(" and di.dict_name like ? ");
			params.add("%"+filterParams.get("EQ_dictName")+"%");
		}else if(XaUtil.isNotEmpty(filterParams)&&XaUtil.isEmpty(filterParams.get("EQ_dictName"))&&XaUtil.isNotEmpty(filterParams.get("EQ_dictId"))){
			sql.append(" and d.id=? ");
			countsql.append(" and d.id=? ");
			params.add(filterParams.get("EQ_dictId"));
		}
		sql.append(" order by di.create_time desc ");
		
		List<Object[]> objs = this.queryParams(sql.toString(), pageable.getPageNumber()*pageable.getPageSize(),pageable.getPageSize(),params);
		List<DictVo> vos=new ArrayList<DictVo>();
		List<Object[]> count = this.queryParams(countsql.toString(), null,null,params);
		for(Object[] obj:objs){
			DictVo vo=new DictVo();
			DictItemVo dictItem=new DictItemVo();
			dictItem.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			dictItem.setDictName((String)obj[1]);
			dictItem.setSort(XaUtil.isEmpty(obj[2])?null:Integer.valueOf(obj[2]+""));
			dictItem.setDictId(XaUtil.isEmpty(obj[3])?null:((BigInteger)obj[3]).longValue());
			vo.setDictItemVo(dictItem);
			vo.setDictName((String)obj[4]);
			vos.add(vo);
		}
		XaResult<Page<DictVo>> xr = new XaResult<Page<DictVo>>();
		Page<DictVo> page = new MyPage<DictVo>(pageable.getPageNumber(), pageable.getPageSize(), vos, Integer.valueOf(count.get(0) + ""));
		xr.setObject(page);
		/*Page<DictItem> page = null;
		if(XaUtil.isNotEmpty(filterParams)&&XaUtil.isNotEmpty(filterParams.get("EQ_dictName"))&&XaUtil.isNotEmpty(filterParams.get("EQ_dictId"))){
			page = dictItemRepository.findDictItemByDictNameAndDictId(filterParams.get("EQ_dictName").toString(),filterParams.get("EQ_dictId").toString(),XaConstant.Status.valid,pageable);
		}
		else if(XaUtil.isNotEmpty(filterParams)&&XaUtil.isNotEmpty(filterParams.get("EQ_dictName"))&&XaUtil.isEmpty(filterParams.get("EQ_dictId"))){		
			page = dictItemRepository.findDictItemByDictName(filterParams.get("EQ_dictName").toString(),XaConstant.Status.valid,pageable);
		}
		else if(XaUtil.isNotEmpty(filterParams)&&XaUtil.isNotEmpty(filterParams.get("EQ_dictId"))&&XaUtil.isEmpty(filterParams.get("EQ_dictName"))){
			page = dictItemRepository.findDictItemByDictId(filterParams.get("EQ_dictId").toString(),XaConstant.Status.valid,pageable);
		}else{
			page = dictItemRepository.findDictItem(XaConstant.Status.valid,pageable);
		}*/
		return xr;
	/*	
		
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		
		Page<DictItem> page = dictItemRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), DictItem.class), pageable);*/
		
		
	}

	/**
	 * 分页查询状态status的DictItem数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象DictItem集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<DictItem>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<DictItem> page = dictItemRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), DictItem.class), pageable);
		XaResult<Page<DictItem>> xr = new XaResult<Page<DictItem>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存DictItem信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<DictItem> saveOrUpdate(DictItem model) throws BusinessException {
		XaResult<DictItem> xr = new XaResult<DictItem>();
		
		
		DictItem obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			List<DictItem> dictItems=dictItemRepository.findByDictIdAndDictNameAndStatusAndIdNot(model.getDictId(),model.getDictName(),XaConstant.Status.valid,model.getId());
			if(XaUtil.isNotEmpty(dictItems)){
				xr.error("该字典的"+model.getDictName()+"选项已存在！");
				return xr;
			}
			List<DictItem> dictItems1=dictItemRepository.findByDictIdAndSortAndStatusAndIdNot(model.getDictId(),model.getSort(),XaConstant.Status.valid,model.getId());
			if(XaUtil.isNotEmpty(dictItems1)){
				xr.error("该字典的排序"+model.getSort()+"已存在！");
				return xr;
			}
			obj = dictItemRepository.findOne(model.getId());
		}else{
			List<DictItem> dictItems=dictItemRepository.findByDictIdAndDictNameAndStatusAndIdNot(model.getDictId(),model.getDictName(),XaConstant.Status.valid,Long.valueOf(0));
			if(XaUtil.isNotEmpty(dictItems)){
				xr.error("该字典的"+model.getDictName()+"选项已存在！");
				return xr;
			}
			List<DictItem> dictItems1=dictItemRepository.findByDictIdAndSortAndStatusAndIdNot(model.getDictId(),model.getSort(),XaConstant.Status.valid,Long.valueOf(0));
			if(XaUtil.isNotEmpty(dictItems1)){
				xr.error("该字典的排序"+model.getSort()+"已存在！");
				return xr;
			}
			obj = new DictItem();
		}
		obj.setDictId(model.getDictId());
		obj.setDictName(model.getDictName());
		obj.setSort(model.getSort());
		obj = dictItemRepository.save(obj);
		
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改DictItem状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回DictItem对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<DictItem> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<DictItem> xr = new XaResult<DictItem>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				DictItem obj = dictItemRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = dictItemRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<Page<DictItem>> findListNEStatusByFilter(Integer status,
			Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XaResult<List<DictItem>> findListByType(Integer status, Integer type) throws BusinessException {
		//Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
//		filters.put("status", new SearchFilter("status", Operator.NE, status));
//		filters.put("dictId", new SearchFilter("dictId", Operator.EQ, type));
//		Sort sort = new Sort(new Order(Direction.ASC, "sort"));
//		List<DictItem> page = dictItemRepository.findAll(DynamicSpecifications
//				.bySearchFilter(filters.values(), DictItem.class),sort);
//		XaResult<List<DictItem>> xr = new XaResult<List<DictItem>>();
//		xr.setObject(page);
//		return xr;
		
		XaResult<List<DictItem>> xr = new XaResult<List<DictItem>>();
		StringBuffer sql=new StringBuffer("select di.id,di.dict_id,di.dict_name,di.create_time from tb_xa_dictitem di ");
		sql.append("inner join tb_xa_dict d on d.id=di.dict_id where d.status="+XaConstant.Status.valid+" and di.status="+XaConstant.Status.valid+" ");
		sql.append("and d.type=? order by di.create_time desc");
		List<Object> param=new ArrayList<Object>();
		param.add(type);
		List<Object[]> objs = this.queryCall(sql.toString(), param);
		List<DictItem> vos = new ArrayList<DictItem>();
		
		for(Object[] obj : objs){
			DictItem vo=new DictItem();
			vo.setId(XaUtil.isEmpty(obj[0]) ? null:((BigInteger)obj[0]).longValue());
			vo.setDictName((String)obj[2]);
			vos.add(vo);
		}
		
		xr.setObject(vos);
		return xr;
	}

	
}
