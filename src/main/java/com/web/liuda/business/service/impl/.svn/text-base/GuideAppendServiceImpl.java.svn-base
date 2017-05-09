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
import com.web.liuda.business.entity.GuideAppend;
import com.web.liuda.business.repository.GuideAppendRepository;
import com.web.liuda.business.service.GuideAppendService;

@Service("GuideAppendService")
@Transactional(readOnly = false)
public class GuideAppendServiceImpl extends BaseService<GuideAppend> implements GuideAppendService {

	@Autowired
	private GuideAppendRepository guideAppendRepository;
	
	/**
	 * 查询单条GuideAppend信息
	 * @param tId
	 * @return 返回单个GuideAppend对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<GuideAppend> findOne(Long modelId) throws BusinessException {
		GuideAppend obj = new GuideAppend();
		if(modelId != 0){
			obj = guideAppendRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<GuideAppend> xr = new XaResult<GuideAppend>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的GuideAppend数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象GuideAppend集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<GuideAppend>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<GuideAppend> page = guideAppendRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), GuideAppend.class), pageable);
		XaResult<Page<GuideAppend>> xr = new XaResult<Page<GuideAppend>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的GuideAppend数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象GuideAppend集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<GuideAppend>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<GuideAppend> page = guideAppendRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), GuideAppend.class), pageable);
		XaResult<Page<GuideAppend>> xr = new XaResult<Page<GuideAppend>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存GuideAppend信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<GuideAppend> saveOrUpdate(GuideAppend model) throws BusinessException {
		GuideAppend obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = guideAppendRepository.findOne(model.getId());
		}else{
			obj = new GuideAppend();
		}
		obj.setGuideId(model.getGuideId());
		obj.setContent(model.getContent());
		obj.setMediaPath(model.getMediaPath());
		obj = guideAppendRepository.save(obj);
		XaResult<GuideAppend> xr = new XaResult<GuideAppend>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改GuideAppend状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回GuideAppend对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<GuideAppend> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<GuideAppend> xr = new XaResult<GuideAppend>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				GuideAppend obj = guideAppendRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = guideAppendRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
}
