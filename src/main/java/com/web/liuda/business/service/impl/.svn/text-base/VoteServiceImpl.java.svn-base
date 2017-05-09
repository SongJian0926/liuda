package com.web.liuda.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.entity.Vote;
import com.web.liuda.business.entity.VoteOption;
import com.web.liuda.business.repository.VoteOptionRepository;
import com.web.liuda.business.repository.VoteRepository;
import com.web.liuda.business.service.VoteService;
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

@Service("VoteService")
@Transactional(readOnly = false)
public class VoteServiceImpl extends BaseService<Vote> implements VoteService {

	@Autowired
	private VoteRepository voteRepository;
	@Autowired
	private VoteOptionRepository voteOptionRepository;
	/**
	 * 查询单条Vote信息
	 * @param tId
	 * @return 返回单个Vote对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Vote> findOne(Long modelId) throws BusinessException {
		Vote obj = new Vote();
		if(modelId != 0){
			obj = voteRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<Vote> xr = new XaResult<Vote>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			xr.error("");
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的Vote数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Vote集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Vote>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		XaResult<Page<Vote>> xr = new XaResult<Page<Vote>>();
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Vote> page = voteRepository.findAll(DynamicSpecifications.bySearchFilter(filters.values(), Vote.class), pageable);
		List<Vote>  voteList=page.getContent();
		for(Vote vote:voteList){
			//判断是否过期
			if(XaUtil.minutesBetweens(XaUtil.dateToStr(XaUtil.getNow()),vote.getDeadline())<0){
				//过期之后，判断是否上架
				if(vote.getStatus()==XaConstant.Status.valid){
						vote.setStatus(XaConstant.Status.locked);
				}
			}
		}
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的Vote数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Vote集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Vote>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		XaResult<Page<Vote>> xr = new XaResult<Page<Vote>>();
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Vote> page = voteRepository.findAll(DynamicSpecifications.bySearchFilter(filters.values(), Vote.class), pageable);
		xr.setObject(page);
		return xr;
	}

	

	/**
	 * 修改Vote状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回Vote对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Vote> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<Vote> xr = new XaResult<Vote>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Vote obj = voteRepository.findByIdAndStatusNot(Long.parseLong(id),XaConstant.Status.delete);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = voteRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	/**
	 * 保存Vote信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Vote> saveOrUpdate(Vote model) throws BusinessException {
		Vote obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = voteRepository.findOne(model.getId());
		}else{
			obj = new Vote();
		}
		obj.setDeadline(model.getDeadline());
		obj.setTitle(model.getTitle());
		obj.setContent(model.getContent());
		obj.setIsRadio(model.getIsRadio());
		obj.setNum(0);
		obj = voteRepository.save(obj);
		XaResult<Vote> xr = new XaResult<Vote>();
		xr.setObject(obj);
		return xr;
	}
	//保存Vote
	@Transactional
	public XaResult<VoteVo> saveVoteing(Vote model, List<VoteOption> voteOptions) {
		XaResult<VoteVo> xr = new XaResult<VoteVo>();
		Vote vote = null;
		if(XaUtil.isEmpty(model.getId())){
			//新增
			vote = new Vote();
			vote.setNum(0);
		}else{
			//修改
			vote = voteRepository.findOne(model.getId());
			if(vote.getStatus()!=XaConstant.Status.valid){
				xr.error("保存失败,已发布的投票无法编辑");
				return xr;
			}
		}
		vote.setDeadline(model.getDeadline());
		vote.setLogo(model.getLogo());
		vote.setTitle(model.getTitle());
		vote.setContent(model.getContent());
		vote.setIsRadio(model.getIsRadio());
		vote.setStatus(XaConstant.Status.valid);
		vote = voteRepository.save(vote);
		
		//删除已存在的
		String sqlDelet = "UPDATE tb_xa_voteoption set status = "+XaConstant.Status.delete+" where vote_id = "+vote.getId()+"";
		//保存修改或者新增的数据
		StringBuffer sqlUpdata = new StringBuffer("insert into tb_xa_voteoption(create_time,option_name,vote_id) value");
		
		for(int i=0;i<voteOptions.size();i++){
			sqlUpdata.append("('"+XaUtil.getToDayStr()+"','"+voteOptions.get(i).getOptionName()+"',"+vote.getId()+")");
			if(i!=voteOptions.size()-1){
				sqlUpdata.append(",");
			}
		}
		
		this.insert(sqlDelet);
		this.insert(sqlUpdata.toString());
		xr.setObject(JSON.parseObject(JSON.toJSONString(vote), VoteVo.class));
		return xr;
	}
	//通过id查找投票内容
	@Override
	public XaResult<VoteVo> findOnes(Long modelId) {
		Vote obj = new Vote();
		if(modelId != 0){
			obj = voteRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<VoteVo> xr = new XaResult<VoteVo>();
		if (XaUtil.isNotEmpty(obj)) {
			VoteVo voteVo=JSON.parseObject(JSON.toJSONString(obj), VoteVo.class);
			//查找选项
			List<VoteOptionVo> voteOptionVos=voteOptionRepository.findByVoteIdAndStatus(voteVo.getId(), XaConstant.Status.valid);
			if(XaUtil.isNotEmpty(voteOptionVos)){
				voteVo.setVoteOptionVoList(voteOptionVos);
			}
			xr.setObject(voteVo);
		} else {
			xr.error("查找失败，该投票不存在");
		}
		return xr;
	}
	
}
