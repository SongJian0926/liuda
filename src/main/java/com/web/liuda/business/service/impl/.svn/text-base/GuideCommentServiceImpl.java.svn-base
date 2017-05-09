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
import com.web.liuda.business.entity.GuideComment;
import com.web.liuda.business.repository.GuideCommentRepository;
import com.web.liuda.business.service.GuideCommentService;

@Service("GuideCommentService")
@Transactional(readOnly = false)
public class GuideCommentServiceImpl extends BaseService<GuideComment> implements GuideCommentService {

	@Autowired
	private GuideCommentRepository guideCommentRepository;
	
	/**
	 * 查询单条GuideComment信息
	 * @param tId
	 * @return 返回单个GuideComment对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<GuideComment> findOne(Long modelId) throws BusinessException {
		GuideComment obj = new GuideComment();
		if(modelId != 0){
			obj = guideCommentRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<GuideComment> xr = new XaResult<GuideComment>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的GuideComment数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象GuideComment集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<GuideComment>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<GuideComment> page = guideCommentRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), GuideComment.class), pageable);
		XaResult<Page<GuideComment>> xr = new XaResult<Page<GuideComment>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的GuideComment数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象GuideComment集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<GuideComment>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<GuideComment> page = guideCommentRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), GuideComment.class), pageable);
		XaResult<Page<GuideComment>> xr = new XaResult<Page<GuideComment>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存GuideComment信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<GuideComment> saveOrUpdate(GuideComment model) throws BusinessException {
		GuideComment obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = guideCommentRepository.findOne(model.getId());
		}else{
			obj = new GuideComment();
		}
		obj.setUserId(model.getUserId());
		obj.setContent(model.getContent());
		obj.setGuideId(model.getGuideId());
		obj = guideCommentRepository.save(obj);
		XaResult<GuideComment> xr = new XaResult<GuideComment>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改GuideComment状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回GuideComment对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<GuideComment> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<GuideComment> xr = new XaResult<GuideComment>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				GuideComment obj = guideCommentRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = guideCommentRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
}
