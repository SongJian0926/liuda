package com.web.liuda.remote.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.liuda.business.entity.ClubMember;
import com.web.liuda.remote.vo.ClubMemberVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;

public interface ApiClubMemberService extends ApiBaseService<ClubMemberVo,ClubMember>{
	//同意加入、删除请求
	public XaResult<ClubMemberVo> multiClubMember(Long userId,Long id,Integer applyStatus,
			Integer status) throws BusinessException;
	//成员列表
	public XaResult<Page<ClubMemberVo>> findClubMemberList(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException;
	//新成员列表
	public XaResult<Page<ClubMemberVo>> findNewClubMemberList(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException;
	//取消加入
	public XaResult<ClubMemberVo> cancelJoin(Long clubId,Long userId,
			Integer status) throws BusinessException ;
	//删除成员、设置管理员
	public XaResult<ClubMemberVo> multiOperate1(String modelIds,Long clubId,
			Integer status,Integer memberType) throws BusinessException;
	//个人主页
	public XaResult<ClubMemberVo> findOneDetail(Long userId,Long modelId)
			throws BusinessException ;
	//邀请成员
	public XaResult<ClubMemberVo> inviteClubMember(String modelIds,Long userId,Long clubId) 
			throws BusinessException ;
	//加入（同意别人的邀请）
	public XaResult<ClubMemberVo> joinClub(Long clubEventId) throws BusinessException ;
	//申请加入俱乐部
	public XaResult<ClubMemberVo> createClubMember(ClubMember model,String clubIds)
			throws BusinessException;
}
