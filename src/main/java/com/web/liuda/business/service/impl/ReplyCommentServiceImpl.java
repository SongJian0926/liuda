package com.web.liuda.business.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.liuda.business.entity.ReplyComment;
import com.web.liuda.business.repository.ImagesRepository;
import com.web.liuda.business.repository.ReplyCommentRepository;
import com.web.liuda.business.service.ReplyCommentService;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

@Service("ReplyCommentService")
@Transactional(readOnly = false)
public class ReplyCommentServiceImpl extends BaseService<ReplyComment> implements ReplyCommentService {

	@Autowired
	private ReplyCommentRepository replyCommentRepository;
	@Autowired
	private ImagesRepository imagesRepository;
	
	/**
	 * 查询单条ReplyComment信息
	 * @param tId
	 * @return 返回单个ReplyComment对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<ReplyComment> findOne(Long modelId) throws BusinessException {
		ReplyComment obj = new ReplyComment();
		if(modelId != 0){
			obj = replyCommentRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<ReplyComment> xr = new XaResult<ReplyComment>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的ReplyComment数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象ReplyComment集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<ReplyComment>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<ReplyComment> page = replyCommentRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), ReplyComment.class), pageable);
		XaResult<Page<ReplyComment>> xr = new XaResult<Page<ReplyComment>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的ReplyComment数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象ReplyComment集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<ReplyComment>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<ReplyComment> page = replyCommentRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), ReplyComment.class), pageable);
		XaResult<Page<ReplyComment>> xr = new XaResult<Page<ReplyComment>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存ReplyComment信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<ReplyComment> newSaveOrUpdate(ReplyComment model,String list) throws BusinessException {
		ReplyComment obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = replyCommentRepository.findOne(model.getId());
		}else{
			obj = new ReplyComment();
		}
		
		obj.setCommentId(model.getCommentId());
		obj.setUserId(model.getUserId());
		obj.setBusinessUserId(model.getBusinessUserId());
		obj.setReplyComment(model.getReplyComment());
		obj = replyCommentRepository.save(obj);
	
		if(XaUtil.isNotEmpty(obj)){
			/*if(XaUtil.isNotEmpty(list)){
				//回复图片不为空
				String[] pics=list.replaceAll("，", ",").split(",");
				//获取当前时间
				String now=XaUtil.getToDayStr();
				StringBuffer insertSql=new StringBuffer("insert into tb_xa_images (create_time,object_id,picurl,sort,type) values");				
				for(String l : pics){
					Images la = new Images();
					la.setObjectId((obj.getId()).longValue());
					la.setPicurl(l);
					la.setType(JConstant.ImageType.REPLY);
					insertSql.append("('"+now+"',"+(obj.getId()).longValue()+",'"+l+"',0,"+JConstant.ImageType.REPLY+"),");
				}
				int idx = insertSql.toString().lastIndexOf(",");
				//去除最后一个逗号
				String str1 = insertSql.toString().substring(0,idx);
				this.insert(str1);
			}*/
		}
		XaResult<ReplyComment> xr = new XaResult<ReplyComment>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改ReplyComment状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回ReplyComment对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<ReplyComment> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<ReplyComment> xr = new XaResult<ReplyComment>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				ReplyComment obj = replyCommentRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = replyCommentRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<ReplyComment> saveOrUpdate(ReplyComment t)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
