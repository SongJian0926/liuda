package com.web.liuda.remote.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.entity.MatchComment;
import com.web.liuda.business.repository.MatchCommentRepository;
import com.web.liuda.remote.service.ApiMatchCommentService;
import com.web.liuda.remote.vo.MatchCommentVo;
import com.web.liuda.remote.vo.UserVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.web.webstart.base.util.SearchFilter.Operator;

/**
 * @Autor: flora.li
 * @times: 2016-04-20 14:16:00
 * 类的说明：前端APIMatchComment接口实现类
 **/
@Service("ApiMatchCommentService")
@Transactional(readOnly = false)
public class ApiMatchCommentServiceImpl extends BaseService<MatchComment> implements ApiMatchCommentService{

	@Autowired
	MatchCommentRepository matchCommentRepository;

	/**
	 * 查询赛事列表
	 * author:flora.li
	 * time:2016-04-20 14:16:00
	 * @param id
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public XaResult<Page<MatchCommentVo>> findMatchCommentList(Integer status, Long matchId, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		if(XaUtil.isNotEmpty(status))
		{
			filters.put("status", new SearchFilter("status", Operator.EQ, status));
		}
		if(XaUtil.isNotEmpty(matchId))
		{
			filters.put("matchId", new SearchFilter("matchId", Operator.EQ, matchId));
		}
//		Page<MatchComment> page = matchCommentRepository.findAll(DynamicSpecifications.bySearchFilter(filters.values(), MatchComment.class), pageable);
//		List<MatchComment> pagecount = matchCommentRepository.findAll(DynamicSpecifications.bySearchFilter(filters.values(), MatchComment.class));
		Page<Object[]> page = matchCommentRepository.findByMatchId(status, matchId, pageable);
		List<Object[]> pagecount = matchCommentRepository.findByMatchId(status, matchId);
		XaResult<Page<MatchCommentVo>> xr = new XaResult<Page<MatchCommentVo>>();
		
		List<MatchCommentVo> vos = new ArrayList<MatchCommentVo>();
		Map<Long,MatchCommentVo> hvos = new HashMap<Long,MatchCommentVo>();
		List<Long> commentIds = new ArrayList<Long>();
		for(Object[] obj : page.getContent()){
			//MatchCommentVo vo = JSON.parseObject(JSON.toJSONString(obj),MatchCommentVo.class);
			MatchCommentVo vo = new MatchCommentVo();
			vo.setId(Long.parseLong(obj[0].toString()));
			vo.setCreateTime(obj[1]==null?null:obj[1].toString());
			vo.setUserId(Long.parseLong(obj[2].toString()));
			vo.setContent(obj[3]==null?null:obj[3].toString());
			vo.setMatchId(Long.parseLong(obj[4].toString()));
			vo.setCommentId(null);
			vo.setPraiseNum(obj[6]==null?null:Integer.parseInt(obj[6].toString()));
			UserVo userVo = new UserVo();
			userVo.setId(Long.parseLong(obj[2].toString()));
			userVo.setUserName(obj[7]==null?null:obj[7].toString());
			userVo.setPhoto(obj[8]==null?null:obj[8].toString());
			vo.setUserVo(userVo);
			if(vo.getCommentId()==null)
			{
				vo.setReplyCommentList(new ArrayList<MatchCommentVo>());
				vos.add(vo);
				hvos.put(vo.getId(), vo);
			}
			commentIds.add(vo.getId());
		}
		if(commentIds.size()>0)
		{
			List<Object[]> pageReply = matchCommentRepository.findRepayByMatchId(status, matchId, commentIds);
			for(Object[] obj : pageReply){
				//MatchCommentVo vo = JSON.parseObject(JSON.toJSONString(obj),MatchCommentVo.class);
				MatchCommentVo vo = new MatchCommentVo();
				vo.setId(Long.parseLong(obj[0].toString()));
				vo.setCreateTime(obj[1]==null?null:obj[1].toString());
				vo.setUserId(Long.parseLong(obj[2].toString()));
				vo.setContent(obj[3]==null?null:obj[3].toString());
				vo.setMatchId(Long.parseLong(obj[4].toString()));
				vo.setCommentId(Long.parseLong(obj[5].toString()));
				vo.setPraiseNum(obj[6]==null?null:Integer.parseInt(obj[6].toString()));
				UserVo userVo = new UserVo();
				userVo.setId(Long.parseLong(obj[2].toString()));
				userVo.setUserName(obj[7]==null?null:obj[7].toString());
				userVo.setPhoto(obj[8]==null?null:obj[8].toString());
				vo.setUserVo(userVo);
				if(vo.getCommentId()!=null && hvos.containsKey(vo.getCommentId()))
				{
					hvos.get(vo.getCommentId()).getReplyCommentList().add(vo);
				}
			}
		}
		
		Page<MatchCommentVo> pagevo = new MyPage<MatchCommentVo>(pageable.getPageNumber(), pageable.getPageSize(), vos, pagecount.size());
		xr.setObject(pagevo);
		return xr;
	}
	
	/**
	 * 赛事活动评论的数量
	 * author:changlu
	 * time:2016-05-12 11:07:00
	 * @param status
	 * @param matchId
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public XaResult<MatchCommentVo> findMatchCommentNum(Integer status,Long matchId)
			throws BusinessException {
		String sql="select count(*) from tb_xa_matchcomment where match_id=? and status=? ";
		List<Object> params=new ArrayList<Object>();
		params.add(matchId);
		params.add(status);
		List<Object[]> count=this.queryParams(sql, null, null, params);
		MatchCommentVo vo=new MatchCommentVo();
		vo.setCommentNum(Integer.valueOf(count.get(0)+""));
		XaResult<MatchCommentVo> xr=new  XaResult<MatchCommentVo>();
		xr.setObject(vo);
		return xr;
	}
	@Override
	public XaResult<MatchCommentVo> praiseModel(Long commentId)
			throws BusinessException {
		MatchComment obj = matchCommentRepository.findByIdAndStatusNot(commentId,XaConstant.Status.delete);
		XaResult<MatchCommentVo> xr = new XaResult<MatchCommentVo>();
		if (XaUtil.isNotEmpty(obj)) {
			obj.setPraiseNum(obj.getPraiseNum()+1);
			obj = matchCommentRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),MatchCommentVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<MatchCommentVo> findOne(Long id) throws BusinessException {
		MatchComment obj = matchCommentRepository.findByIdAndStatusNot(id,XaConstant.Status.delete);
		XaResult<MatchCommentVo> xr = new XaResult<MatchCommentVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),MatchCommentVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<List<MatchCommentVo>> findListNEStatusByFilter(Integer status,
			Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		throw new BusinessException("该方法未实现");
	}

	@Override
	public XaResult<List<MatchCommentVo>> findListEQStatusByFilter(Integer status,
			Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		throw new BusinessException("该方法未实现");
	}

	@Override
	public XaResult<MatchCommentVo> createModel(MatchComment model)
			throws BusinessException {
		XaResult<MatchCommentVo> xr = new XaResult<MatchCommentVo>();
		MatchComment obj = matchCommentRepository.save(model);
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), MatchCommentVo.class));
		xr.getObject().setId(obj.getId());
		return xr;
	}

	@Override
	public XaResult<MatchCommentVo> updateModel(MatchComment model)
			throws BusinessException {
		throw new BusinessException("该方法未实现");
	}

	@Override
	public XaResult<MatchCommentVo> multiOperate(String ids, Integer status)
			throws BusinessException {
		throw new BusinessException("该方法未实现");
	}

}
