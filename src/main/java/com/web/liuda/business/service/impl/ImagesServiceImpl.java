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
import com.web.liuda.business.entity.Images;
import com.web.liuda.business.repository.ImagesRepository;
import com.web.liuda.business.service.ImagesService;

@Service("ImagesService")
@Transactional(readOnly = false)
public class ImagesServiceImpl extends BaseService<Images> implements ImagesService {

	@Autowired
	private ImagesRepository imagesRepository;
	
	/**
	 * 查询单条Images信息
	 * @param tId
	 * @return 返回单个Images对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Images> findOne(Long modelId) throws BusinessException {
		Images obj = new Images();
		if(modelId != 0){
			obj = imagesRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<Images> xr = new XaResult<Images>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的Images数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Images集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Images>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Images> page = imagesRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Images.class), pageable);
		XaResult<Page<Images>> xr = new XaResult<Page<Images>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的Images数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Images集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Images>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Images> page = imagesRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Images.class), pageable);
		XaResult<Page<Images>> xr = new XaResult<Page<Images>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存Images信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Images> saveOrUpdate(Images model) throws BusinessException {
		Images obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = imagesRepository.findOne(model.getId());
		}else{
			obj = new Images();
		}
		obj.setPicurl(model.getPicurl());
		obj.setType(model.getType());
		obj.setSort(model.getSort());
		obj.setObjectId(model.getObjectId());
		obj = imagesRepository.save(obj);
		XaResult<Images> xr = new XaResult<Images>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改Images状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回Images对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Images> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<Images> xr = new XaResult<Images>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Images obj = imagesRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = imagesRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Images> saveImagesByObjectId(Long objectId, String imgUrls)
			throws BusinessException {
		XaResult<Images> xr=new XaResult<Images>();
		Images images=null;
		String arr[]=imgUrls.split(",");
		String sql="select * from tb_xa_images i where i.`status`<>3 and i.object_id=?";
		List<Object> params=new ArrayList<Object>();
		params.add(objectId);
		List<Object[]> objs=this.queryParams(sql, null, null, params);
		System.out.println(arr[0]);
		if(objs.size()>0){
			
		}else{
			for(String sub:arr){
				images=new Images();
				images.setObjectId(objectId);
				images.setPicurl(sub);
				images.setType(4);
				images.setStatus(XaConstant.Status.publish);
				imagesRepository.save(images);
			}
		}
		return xr;
	}

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Images> deleteImagesByObjectId(Long ObjectId,Integer type)
			throws BusinessException {
		XaResult<Images> xr=new XaResult<Images>();
		String sql="delete from tb_xa_images  where object_id="+ObjectId+" and type="+type+"";
		this.insert(sql);
		return xr;
	}
	
}
