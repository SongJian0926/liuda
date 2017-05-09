package com.web.liuda.remote.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.Vote;
import com.web.liuda.business.repository.VoteLogRepository;
import com.web.liuda.business.repository.VoteOptionRepository;
import com.web.liuda.business.repository.VoteRepository;
import com.web.liuda.remote.service.ApiVoteService;
import com.web.liuda.remote.vo.VoteLogVo;
import com.web.liuda.remote.vo.VoteOptionVo;
import com.web.liuda.remote.vo.VoteVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APIVote接口实现类
 **/
@Service("ApiVoteService")
@Transactional(readOnly = false)
public class ApiVoteServiceImpl extends BaseService<Vote> implements ApiVoteService{

	@Autowired
	VoteRepository voteRepository;
	@Autowired
	VoteLogRepository voteLogRepository;
	@Autowired
	VoteOptionRepository voteOptionRepository;
	@Override
	public XaResult<VoteVo> findOne(Long tId) throws BusinessException {
		Vote obj = voteRepository.findByIdAndStatus(tId,XaConstant.Status.valid);
		XaResult<VoteVo> xr = new XaResult<VoteVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),VoteVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<List<VoteVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Vote> page = voteRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Vote.class), pageable);
		XaResult<List<VoteVo>> xr = new XaResult<List<VoteVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), VoteVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}
	
	@Override
	public XaResult<VoteVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		XaResult<VoteVo> xr = new XaResult<VoteVo>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Vote obj = voteRepository.findByIdAndStatus(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = voteRepository.save(obj);
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj), VoteVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<VoteVo> createModel(Vote model)
			throws BusinessException {
		XaResult<VoteVo> xr = new XaResult<VoteVo>();
		Vote obj = voteRepository.save(model);
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), VoteVo.class));
		xr.getObject().setId(obj.getId());
		return xr;
	}

	@Override
	public XaResult<VoteVo> updateModel(Vote model)
			throws BusinessException {
		Vote obj = voteRepository.findOne(model.getId());
		XaResult<VoteVo> xr = new XaResult<VoteVo>();
		if (XaUtil.isNotEmpty(obj)) {
		obj.setDeadline(model.getDeadline());
		obj.setTitle(model.getTitle());
		obj.setContent(model.getContent());
		obj.setIsRadio(model.getIsRadio());
		obj.setNum(model.getNum());
			obj = voteRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), VoteVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	
	//投票列表
		@Transactional
		public XaResult<Page<VoteVo>> findListEQStatusByFilters(
				Integer status, Map<String, Object> filterParams, Pageable pageable)
				throws BusinessException {
			if(status == null){// 默认显示正常数据
				status = XaConstant.Status.publish;
			}
			Page<VoteVo> voPage = voteRepository.findByStatus(status,pageable);
			XaResult<Page<VoteVo>> xr = new XaResult<Page<VoteVo>>();
			/*//投票状态
			for(VoteVo vo:voList){
				int day = XaUtil.minutesBetween(XaUtil.getToDayStr(),(vo.getDeadline()+" 24:00:00"));
				if(day<0){
				   vo.setIsOld(JConstant.BooleanStatus.TRUE);
				}else{
				  vo.setIsOld(JConstant.BooleanStatus.FALSE);
				}
			}*/		
			xr.setObject(voPage);
			return xr;
		}

  //详情		
	@Override
	public XaResult<VoteVo> findOneByUserId(Long modelId, Long userId) throws BusinessException {
		XaResult<VoteVo> xr =new XaResult<VoteVo>();
		Vote obj = voteRepository.findByIdAndStatus(modelId,XaConstant.Status.publish);
		VoteVo vo;
		if (XaUtil.isNotEmpty(obj)) {
			 vo= JSON.parseObject(JSON.toJSONString(obj),VoteVo.class);
			//查询投票选项
			List<VoteOptionVo>  voteOptionVoList=  voteOptionRepository.findByVoteIdAndStatus(modelId, XaConstant.Status.valid);
			//查询投票人数
			String perpNum = "select COUNT(distinct v.user_id)  from tb_xa_votelog v where v.vote_id="+modelId+" ";
			vo.setVotePepoNum(Integer.parseInt(this.query(perpNum, 0, 0).get(0)+""));
			vo.setVoteOptionVoList(voteOptionVoList);
			//投票状态
			//int day = XaUtil.minutesBetween(XaUtil.getToDayStr(),vo.getDeadline());
			if(vo.getDeadline().compareTo(XaUtil.getToDayStr())>0){
				vo.setIsOld(JConstant.BooleanStatus.TRUE);
			}else{
				vo.setIsOld(JConstant.BooleanStatus.FALSE);
			}
			/*if(day<0){
				vo.setIsOld(JConstant.BooleanStatus.TRUE);
			}else{
				vo.setIsOld(JConstant.BooleanStatus.FALSE);
			}*/
			//判断用户是否投过票
			if(XaUtil.isNotEmpty(userId)){
				List<VoteLogVo> voteLogVoList = voteLogRepository.findByUserIdAndVoteIdAndStatus(userId, modelId, XaConstant.Status.valid);
				if(XaUtil.isNotEmpty(voteLogVoList)){
					vo.setUserIsVote(JConstant.BooleanStatus.TRUE);
				}else{
					vo.setUserIsVote(JConstant.BooleanStatus.FALSE);
				}
			}else{
				vo.setUserIsVote(JConstant.BooleanStatus.FALSE);
			}
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		xr.setObject(vo);
		return xr;
	}
	//投票动作
	@Transactional
	public XaResult<String> voting(Long modelId, Long userId,
			String voteOptiones) {
		XaResult<String> xr = new XaResult<String>();
		String voteOption[] =  voteOptiones.split(",");
		//判断用户是否投过票
		if(XaUtil.isNotEmpty(userId)){
			Vote vote=voteRepository.findByIdAndStatus(modelId,XaConstant.Status.valid);
			if(XaUtil.isNotEmpty(vote)&&XaUtil.getToDayStr().compareTo(vote.getDeadline())>0){
				xr.error("投票已结束！");
				return xr;
			}
			List<VoteLogVo> voteLogVoList = voteLogRepository.findByUserIdAndVoteIdAndStatus(userId, modelId, XaConstant.Status.valid);
			if(XaUtil.isEmpty(voteLogVoList)){
				//如果num为null,更改投票选项的数量
				StringBuffer sql=new StringBuffer("update tb_xa_voteoption set num=0 where num is null and ");
				
				//投票选项增加投票数量
				StringBuffer sqlOption = new StringBuffer("");
				sqlOption.append("UPDATE tb_xa_voteoption vo set vo.num = vo.num+1 where");
				
				//投票记录表插入数据
				StringBuffer sqlVoteLog = new StringBuffer("insert into tb_xa_votelog(create_time,status,user_id,vote_id,vote_option_id) values");
				if(XaUtil.isNotEmpty(voteOption)){
					sql.append(" id in (");
					sqlOption.append(" vo.id in (");
					for(int i=0;i<voteOption.length;i++){
						sql.append(" "+voteOption[i]+" ");
						sqlOption.append(" "+voteOption[i]+" ");
						
						sqlVoteLog.append("('"+XaUtil.getToDayStr()+"',"+XaConstant.Status.valid+","+userId+","+modelId+","+voteOption[i]+")");
						if(i != voteOption.length -1){
							sql.append(", ");
							sqlOption.append(",");
							sqlVoteLog.append(",");
						}
						if(i == voteOption.length -1){
							sql.append(") ");
							sqlOption.append(")");
							
						}
					}
				}
				this.insert(sql.toString());
				//如果num为null,更改投票的数量
				StringBuffer updatevotesql = new StringBuffer("update tb_xa_vote set num=0 where num is null and id="+modelId+" ");
				this.insert(updatevotesql.toString());
				//投票表增加投票数量
				StringBuffer sqlVote = new StringBuffer("");
				sqlVote.append("UPDATE tb_xa_vote vot set vot.num=vot.num+"+voteOption.length+" where vot.id="+modelId+"");
				
				//sqlVote.append("UPDATE tb_xa_vote vot set vot.num=vot.num+1 where vot.id="+modelId+"");
				this.insert(sqlVoteLog.toString());
				this.insert(sqlOption.toString());
				this.insert(sqlVote.toString());
				
				xr.setMessage("投票成功");			
			}else{
				xr.error("该用户已投票，不可重复投票！");
			}
		}
		return xr;
	}

	@Override
	public XaResult<List<VoteVo>> findListEQStatusByFilter(Integer status,
			Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
}
