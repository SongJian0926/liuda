package com.web.liuda.business.service.impl;

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
import com.web.liuda.business.entity.GuideTag;
import com.web.liuda.business.repository.GuideTagRepository;
import com.web.liuda.business.service.GuideTagService;

@Service("GuideTagService")
@Transactional(readOnly = false)
public class GuideTagServiceImpl extends BaseService<GuideTag> implements GuideTagService {

	@Autowired
	private GuideTagRepository guideTagRepository;
	
	/**
	 * 查询单条GuideTag信息
	 * @param tId
	 * @return 返回单个GuideTag对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<GuideTag> findOne(Long modelId) throws BusinessException {
		GuideTag obj = new GuideTag();
		if(modelId != 0){
			obj = guideTagRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<GuideTag> xr = new XaResult<GuideTag>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的GuideTag数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象GuideTag集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<GuideTag>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<GuideTag> page = guideTagRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), GuideTag.class), pageable);
		XaResult<Page<GuideTag>> xr = new XaResult<Page<GuideTag>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的GuideTag数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象GuideTag集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<GuideTag>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<GuideTag> page = guideTagRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), GuideTag.class), pageable);
		XaResult<Page<GuideTag>> xr = new XaResult<Page<GuideTag>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存GuideTag信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<GuideTag> saveOrUpdate(GuideTag model) throws BusinessException {
		GuideTag obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = guideTagRepository.findOne(model.getId());
		}else{
			obj = new GuideTag();
		}
		obj.setGuideId(model.getGuideId());
		obj.setDictItemId(model.getDictItemId());
		obj = guideTagRepository.save(obj);
		XaResult<GuideTag> xr = new XaResult<GuideTag>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改GuideTag状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回GuideTag对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<GuideTag> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<GuideTag> xr = new XaResult<GuideTag>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				GuideTag obj = guideTagRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = guideTagRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
}
