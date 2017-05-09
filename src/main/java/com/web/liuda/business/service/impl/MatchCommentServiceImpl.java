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
import com.web.liuda.business.entity.MatchComment;
import com.web.liuda.business.repository.MatchCommentRepository;
import com.web.liuda.business.service.MatchCommentService;

@Service("MatchCommentService")
@Transactional(readOnly = false)
public class MatchCommentServiceImpl extends BaseService<MatchComment> implements MatchCommentService {

	@Autowired
	private MatchCommentRepository matchCommentRepository;
	
	/**
	 * 查询单条MatchComment信息
	 * @param tId
	 * @return 返回单个MatchComment对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<MatchComment> findOne(Long modelId) throws BusinessException {
		MatchComment obj = new MatchComment();
		if(modelId != 0){
			obj = matchCommentRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<MatchComment> xr = new XaResult<MatchComment>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的MatchComment数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象MatchComment集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<MatchComment>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<MatchComment> page = matchCommentRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), MatchComment.class), pageable);
		XaResult<Page<MatchComment>> xr = new XaResult<Page<MatchComment>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的MatchComment数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象MatchComment集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<MatchComment>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<MatchComment> page = matchCommentRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), MatchComment.class), pageable);
		XaResult<Page<MatchComment>> xr = new XaResult<Page<MatchComment>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存MatchComment信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<MatchComment> saveOrUpdate(MatchComment model) throws BusinessException {
		MatchComment obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = matchCommentRepository.findOne(model.getId());
		}else{
			obj = new MatchComment();
		}
		obj.setUserId(model.getUserId());
		obj.setContent(model.getContent());
		obj.setMatchId(model.getMatchId());
		obj.setCommentId(model.getCommentId());
		obj.setPraiseNum(model.getPraiseNum());
		obj = matchCommentRepository.save(obj);
		XaResult<MatchComment> xr = new XaResult<MatchComment>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改MatchComment状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回MatchComment对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<MatchComment> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<MatchComment> xr = new XaResult<MatchComment>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				MatchComment obj = matchCommentRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = matchCommentRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
}
