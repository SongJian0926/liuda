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
import com.web.liuda.business.entity.EventReview;
import com.web.liuda.business.repository.EventReviewRepository;
import com.web.liuda.business.service.EventReviewService;

@Service("EventReviewService")
@Transactional(readOnly = false)
public class EventReviewServiceImpl extends BaseService<EventReview> implements EventReviewService {

	@Autowired
	private EventReviewRepository eventReviewRepository;
	
	/**
	 * 查询单条EventReview信息
	 * @param tId
	 * @return 返回单个EventReview对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<EventReview> findOne(Long modelId) throws BusinessException {
		EventReview obj = new EventReview();
		if(modelId != 0){
			obj = eventReviewRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<EventReview> xr = new XaResult<EventReview>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的EventReview数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象EventReview集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<EventReview>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<EventReview> page = eventReviewRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), EventReview.class), pageable);
		XaResult<Page<EventReview>> xr = new XaResult<Page<EventReview>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的EventReview数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象EventReview集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<EventReview>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<EventReview> page = eventReviewRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), EventReview.class), pageable);
		XaResult<Page<EventReview>> xr = new XaResult<Page<EventReview>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存EventReview信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<EventReview> saveOrUpdate(EventReview model) throws BusinessException {
		EventReview obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = eventReviewRepository.findOne(model.getId());
		}else{
			obj = new EventReview();
		}
		obj.setTitle(model.getTitle());
		obj.setContent(model.getContent());
		obj.setMediaPath(model.getMediaPath());
		obj.setUserId(model.getUserId());
		obj.setEventId(model.getEventId());
		obj = eventReviewRepository.save(obj);
		XaResult<EventReview> xr = new XaResult<EventReview>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改EventReview状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回EventReview对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<EventReview> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<EventReview> xr = new XaResult<EventReview>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				EventReview obj = eventReviewRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = eventReviewRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
}
