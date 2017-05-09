package com.web.liuda.business.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.liuda.business.entity.Dictionary;
import com.web.liuda.business.repository.DictionaryRepository;
import com.web.liuda.business.service.DictionaryService;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.web.webstart.base.util.SearchFilter.Operator;


@Service("DictionaryService")
@Transactional(readOnly = false)
public class DictionaryServiceImpl extends BaseService<Dictionary> implements DictionaryService
{

  @Autowired
  private DictionaryRepository dictionaryRepository;
  /**
	 * 查询单条Cooperation信息
	 * @param tId
	 * @return 返回单个Dictionary对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Dictionary> findOne(Long modelId) throws BusinessException {
		Dictionary obj= new Dictionary();
		if(modelId != 0)
		{
			obj =  dictionaryRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		 XaResult<Dictionary> xr = new  XaResult<Dictionary>();
		 if(XaUtil.isNotEmpty(obj))
		 {
			 xr.setObject(obj);
		 }
		 return xr;
	}

	/**
	 * 分页查询状态非status的Advertisement数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Dictionary集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Dictionary>> findListNEStatusByFilter(Integer status,
			Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null)
		{
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		
		Page<Dictionary> page = dictionaryRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Dictionary.class), pageable);
		XaResult<Page<Dictionary>> xr=new XaResult<Page<Dictionary>>();
		xr.setObject(page);
		return   xr;
	}
	/**
	 * 保存Dictionary信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Dictionary> saveOrUpdate(Dictionary model) throws BusinessException {
		Dictionary obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = dictionaryRepository.findOne(model.getId());
		}else{
			obj = new Dictionary();
		}
		obj.setName(model.getName());
		obj.setType(model.getType());
		obj = dictionaryRepository.save(obj);
		XaResult<Dictionary> xr = new XaResult<Dictionary>();
		xr.setObject(obj);
		return xr;
	}
	
	/**
	 * 修改Dictionary状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回Dictionary对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Dictionary> multiOperate(String modelIds, Integer status)
			throws BusinessException {
		XaResult<Dictionary> xr = new XaResult<Dictionary>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Dictionary obj = dictionaryRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = dictionaryRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
	
	
	
	
	
	
	@Override
	public XaResult<Page<Dictionary>> findListEQStatusByFilter(Integer status,
			Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}


}
