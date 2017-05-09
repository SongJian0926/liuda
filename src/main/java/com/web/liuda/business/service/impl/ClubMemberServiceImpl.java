package com.web.liuda.business.service.impl;

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
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.ClubMember;
import com.web.liuda.business.entity.User;
import com.web.liuda.business.repository.ClubMemberRepository;
import com.web.liuda.business.repository.UserRepository;
import com.web.liuda.business.service.ClubMemberService;
import com.web.liuda.remote.vo.ClubMemberVo;
import com.web.liuda.remote.vo.UserVo;

@Service("ClubMemberService")
@Transactional(readOnly = false)
public class ClubMemberServiceImpl extends BaseService<ClubMember> implements ClubMemberService {

	@Autowired
	private ClubMemberRepository clubMemberRepository;
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * 查询单条ClubMember信息
	 * @param tId
	 * @return 返回单个ClubMember对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<ClubMember> findOne(Long modelId) throws BusinessException {
		ClubMember obj = new ClubMember();
		if(modelId != 0){
			obj = clubMemberRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<ClubMember> xr = new XaResult<ClubMember>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的ClubMember数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象ClubMember集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<ClubMember>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<ClubMember> page = clubMemberRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), ClubMember.class), pageable);
		XaResult<Page<ClubMember>> xr = new XaResult<Page<ClubMember>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的ClubMember数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象ClubMember集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<ClubMember>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<ClubMember> page = clubMemberRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), ClubMember.class), pageable);
		XaResult<Page<ClubMember>> xr = new XaResult<Page<ClubMember>>();
		xr.setObject(page);
		return xr;
	}

	@Override
	public XaResult<Page<ClubMemberVo>> findClubMemberByClubIdPage(Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		filters.put("applyStatus", new SearchFilter("applyStatus", Operator.EQ, JConstant.ApplyStatus.CHECKSUCCEED));
		Page<ClubMember> page = clubMemberRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), ClubMember.class), pageable);
		//List<Object[]> members = clubMemberRepository.findClubMemberByClubIdPage(status,clubId,pageable);
		List<Long> userIds=new ArrayList<Long>();
		for(int i=0;i<page.getContent().size();i++)
		{
			userIds.add(page.getContent().get(i).getUserId());
		}
		Map<String, SearchFilter> filtersm = new HashMap<String, SearchFilter>();
		filtersm.put("id", new SearchFilter("id", Operator.IN, userIds));
		filtersm.put("status", new SearchFilter("status", Operator.NE, status));
		List<User> usrs = userRepository.findAll(DynamicSpecifications.bySearchFilter(filtersm.values(), User.class));
		Map<Long, UserVo> map = new HashMap<Long, UserVo>();
		for(User u : usrs)
		{
			map.put(u.getId(), JSON.parseObject(JSON.toJSONString(u),UserVo.class));
		}
		XaResult<Page<ClubMemberVo>> xr = new XaResult<Page<ClubMemberVo>>();
		
		List<ClubMemberVo> vos = new ArrayList<ClubMemberVo>();
		for(ClubMember obj : page.getContent()){
			ClubMemberVo vo = JSON.parseObject(JSON.toJSONString(obj),ClubMemberVo.class);
			vo.setUserVo(map.get(obj.getUserId()));
			vos.add(vo);
		}
		
		Page<ClubMemberVo> pagevo = new MyPage<ClubMemberVo>(pageable.getPageNumber(), pageable.getPageSize(), vos, Integer.parseInt(String.valueOf(page.getTotalElements())));
		
		xr.setObject(pagevo);
		return xr;
	}
	
	/**
	 * 保存ClubMember信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<ClubMember> saveOrUpdate(ClubMember model) throws BusinessException {
		ClubMember obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = clubMemberRepository.findOne(model.getId());
		}else{
			obj = new ClubMember();
		}
		obj.setClubId(model.getClubId());
		obj.setUserId(model.getUserId());
		obj.setMemberType(model.getMemberType());
		obj.setApplyStatus(model.getApplyStatus());
		obj = clubMemberRepository.save(obj);
		XaResult<ClubMember> xr = new XaResult<ClubMember>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改ClubMember状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回ClubMember对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<ClubMember> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<ClubMember> xr = new XaResult<ClubMember>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				ClubMember obj = clubMemberRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = clubMemberRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

}
