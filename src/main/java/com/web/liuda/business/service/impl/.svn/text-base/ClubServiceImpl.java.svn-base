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
import com.web.webstart.base.util.HttpURLConnectionUtil;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.Club;
import com.web.liuda.business.entity.ClubMember;
import com.web.liuda.business.repository.ClubMemberRepository;
import com.web.liuda.business.repository.ClubRepository;
import com.web.liuda.business.service.AreaService;
import com.web.liuda.business.service.ClubService;

@Service("ClubService")
@Transactional(readOnly = false)
public class ClubServiceImpl extends BaseService<Club> implements ClubService {

	@Autowired
	private ClubRepository clubRepository;
	@Autowired
	private AreaService areaService;
	@Autowired
	ClubMemberRepository clubMemberRepository;
	
	/**
	 * 查询单条Club信息
	 * @param tId
	 * @return 返回单个Club对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Club> findOne(Long modelId) throws BusinessException {
		Club obj = new Club();
		if(modelId != 0){
			obj = clubRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<Club> xr = new XaResult<Club>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的Club数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Club集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Club>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Club> page = clubRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Club.class), pageable);
		XaResult<Page<Club>> xr = new XaResult<Page<Club>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的Club数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Club集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Club>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Club> page = clubRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Club.class), pageable);
		XaResult<Page<Club>> xr = new XaResult<Page<Club>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存Club信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Club> saveOrUpdate(Club model) throws BusinessException {
		XaResult<Club> xr = new XaResult<Club>();
		Club obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = clubRepository.findOne(model.getId());
			if(!obj.getApplyStatus().equals(JConstant.ApplyStatus.CHECK))
			{
				xr.error("状态已经不是审核中，无法修改");
				return xr;
			}
		}else{
			obj = new Club();
		}
		
		String areaName = areaService.getAreaName(model.getAreaId());
		model.setAreaCode(areaName);
		
		obj.setTitle(model.getTitle());
		obj.setLogo(model.getLogo());
		obj.setType(model.getType());
		obj.setLevel(model.getLevel());
		obj.setAddress(model.getAddress());
		obj.setSort(model.getSort());
		obj.setContent(model.getContent());
		obj.setMediaPath(model.getMediaPath());
		obj.setAreaCode(model.getAreaCode());
		obj.setApplyStatus(JConstant.ApplyStatus.CHECK);
		obj.setIsRecommend(model.getIsRecommend());
		obj.setInterest(model.getInterest());
		obj.setAreaId(model.getAreaId());
		obj.setUserId(model.getUserId());
		obj.setIsUserAdd(model.getIsUserAdd());
		
		obj.setLng(HttpURLConnectionUtil.getLocation('"'+model.getAreaCode()+model.getAddress()+'"').getResult().getLocation().getLng());
		obj.setLat(HttpURLConnectionUtil.getLocation('"'+model.getAreaCode()+model.getAddress()+'"').getResult().getLocation().getLat());
		
		obj = clubRepository.save(obj);
		
		if(model.getUserId()!=null)
		{
			if(XaUtil.isNotEmpty(obj)){
				ClubMember o = clubMemberRepository.findByApplyStatusAndMemberTypeAndStatusNotAndClubId(JConstant.ApplyStatus.CHECKSUCCEED, JConstant.ClubRole.MINISTER, XaConstant.Status.delete,obj.getId());
				if(o==null)
				{
					ClubMember clubMember=new ClubMember();
					clubMember.setClubId(obj.getId());
					clubMember.setApplyStatus(JConstant.ApplyStatus.CHECKSUCCEED);
					clubMember.setMemberType(JConstant.ClubRole.MINISTER);
					clubMember.setUserId(model.getUserId());
					clubMember = clubMemberRepository.save(clubMember);
				}
				else if(o!=null && !model.getUserId().equals(o.getUserId()))
				{
					o.setUserId(model.getUserId());
					clubMemberRepository.save(o);
				}
			}
		}
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改Club状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回Club对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Club> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<Club> xr = new XaResult<Club>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Club obj = clubRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = clubRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<Club> applyClub(String clubIds,Integer status) throws BusinessException {
		XaResult<Club> xr = new XaResult<Club>();
		if(XaUtil.isNotEmpty(clubIds)){
			String[] rids = clubIds.split(",");
			for(String rid : rids){
				Club obj=clubRepository.findByIdAndStatusNot(Long.parseLong(rid), XaConstant.Status.delete);
				if (XaUtil.isNotEmpty(obj)) {
				  //
				  if(status.equals(JConstant.ApplyStatus.CHECKSUCCEED)){
					  if(obj.getApplyStatus()==JConstant.ApplyStatus.CHECK){
						  obj.setApplyStatus(JConstant.ApplyStatus.CHECKSUCCEED);
						  obj = clubRepository.save(obj);
					  }
				  //
				  }else if(status.equals(JConstant.ApplyStatus.CHECKFAIL)){
					  if(obj.getApplyStatus()==JConstant.ApplyStatus.CHECK){
						  obj.setApplyStatus(JConstant.ApplyStatus.CHECKFAIL);
						  obj = clubRepository.save(obj);
					  }
				  }
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
		    }
		}
		return xr;
	}

	@Override
	public XaResult<Club> recommendClub(String clubIds, Integer status)
			throws BusinessException {
		XaResult<Club> xr = new XaResult<Club>();
		if(XaUtil.isNotEmpty(clubIds)){
			String[] rids = clubIds.split(",");
			for(String rid : rids){
				Club obj=clubRepository.findByIdAndStatusNot(Long.parseLong(rid), XaConstant.Status.delete);
				if (XaUtil.isNotEmpty(obj)) {
					  obj.setIsRecommend(status);
					  obj = clubRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
		    }
		}
		return xr;
	}
	
}
