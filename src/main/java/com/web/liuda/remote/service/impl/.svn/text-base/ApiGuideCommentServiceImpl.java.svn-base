package com.web.liuda.remote.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.entity.GuideComment;
import com.web.liuda.business.entity.User;
import com.web.liuda.business.repository.GuideCommentRepository;
import com.web.liuda.business.repository.UserRepository;
import com.web.liuda.remote.service.ApiGuideCommentService;
import com.web.liuda.remote.vo.GuideCommentVo;
import com.web.liuda.remote.vo.UserVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APIGuideComment接口实现类
 **/
@Service("ApiGuideCommentService")
@Transactional(readOnly = false)
public class ApiGuideCommentServiceImpl extends BaseService<GuideComment> implements ApiGuideCommentService{

	@Autowired
	GuideCommentRepository guideCommentRepository;
	@Autowired
	UserRepository userRepository;
	@Override
	public XaResult<GuideCommentVo> findOne(Long tId) throws BusinessException {
		GuideComment obj = guideCommentRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<GuideCommentVo> xr = new XaResult<GuideCommentVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),GuideCommentVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	//攻略评论列表
	public XaResult<Page<GuideCommentVo>> findGuideContentList(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		XaResult<Page<GuideCommentVo>> xr=new XaResult<Page<GuideCommentVo>>();
		StringBuffer sql=new StringBuffer("select gc.id,gc.content,gc.create_time,gc.user_id,u.user_name,u.photo from tb_xa_guidecomment gc left join tb_xa_user u on u.id=gc.user_id ");
		sql.append("where gc.guide_id="+Long.valueOf(filterParams.get("EQ_guideId")+"")+" and gc.status="+XaConstant.Status.valid+" ");
		sql.append(" order by gc.create_time desc ");
		List<Object[]> objs=this.query(sql.toString(), pageable.getPageNumber()*pageable.getPageSize(),pageable.getPageSize());
		List<GuideCommentVo> vos=new ArrayList<GuideCommentVo>();
		StringBuffer countsql=new StringBuffer("select count(*) from tb_xa_guidecomment gc left join tb_xa_user u on u.id=gc.user_id ");
		countsql.append("where gc.guide_id="+Long.valueOf(filterParams.get("EQ_guideId")+"")+" and gc.status="+XaConstant.Status.valid+" ");
		List<Object[]>  count=this.query(countsql.toString(),null,null);
		for(Object[] obj:objs){
			GuideCommentVo vo=new GuideCommentVo();
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setContent((String)obj[1]);
			vo.setCreateTime((String)obj[2]);
			vo.setUserId(XaUtil.isEmpty(obj[3])?null:((BigInteger)obj[3]).longValue());
			UserVo userVo=new UserVo();
			userVo.setUserName((String)obj[4]);
			userVo.setPhoto((String)obj[5]);
			vo.setUserVo(userVo);
			vos.add(vo);
		}
		//分页
		Page<GuideCommentVo> page = new MyPage<GuideCommentVo>(pageable.getPageNumber(), pageable.getPageSize(), vos, Integer.valueOf(count.get(0) + ""));
		xr.setObject(page);
		return xr;
	}
	@Override
	public XaResult<List<GuideCommentVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<GuideComment> page = guideCommentRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), GuideComment.class), pageable);
		XaResult<List<GuideCommentVo>> xr = new XaResult<List<GuideCommentVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), GuideCommentVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<List<GuideCommentVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<GuideComment> page = guideCommentRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), GuideComment.class), pageable);
		XaResult<List<GuideCommentVo>> xr = new XaResult<List<GuideCommentVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), GuideCommentVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<GuideCommentVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		XaResult<GuideCommentVo> xr = new XaResult<GuideCommentVo>();
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
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj), GuideCommentVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<GuideCommentVo> createModel(GuideComment model)
			throws BusinessException {
		XaResult<GuideCommentVo> xr = new XaResult<GuideCommentVo>();
		GuideComment obj = guideCommentRepository.save(model);
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), GuideCommentVo.class));
		xr.getObject().setId(obj.getId());
		User user=userRepository.findByIdAndStatus(model.getUserId(),XaConstant.Status.valid);
		UserVo userVo=new UserVo();
		userVo.setUserName(user.getUserName());
		userVo.setPhoto(user.getPhoto());
		xr.getObject().setUserVo(userVo);
		return xr;
	}

	@Override
	public XaResult<GuideCommentVo> updateModel(GuideComment model)
			throws BusinessException {
		GuideComment obj = guideCommentRepository.findOne(model.getId());
		XaResult<GuideCommentVo> xr = new XaResult<GuideCommentVo>();
		if (XaUtil.isNotEmpty(obj)) {
		obj.setUserId(model.getUserId());
		obj.setContent(model.getContent());
		obj.setGuideId(model.getGuideId());
			obj = guideCommentRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), GuideCommentVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

}
