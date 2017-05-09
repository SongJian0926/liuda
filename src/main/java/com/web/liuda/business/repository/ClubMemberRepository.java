package com.web.liuda.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.ClubMember;


public interface ClubMemberRepository extends
		PagingAndSortingRepository<ClubMember, Long>,
		JpaSpecificationExecutor<ClubMember> {
	public ClubMember findByIdAndStatusNot(Long id,Integer status);
	
	//查询该用户在俱乐部中的角色
	public ClubMember findByUserIdAndApplyStatusAndStatusNotAndClubId(Long userId,Integer applyStatus,Integer status,Long club);
	//查询该用户是否在该俱乐部
	public ClubMember findByUserIdAndStatusNotAndClubId(Long userId,Integer status,Long club);
	//查询用户创建的俱乐部
	public List<ClubMember> findByUserIdAndApplyStatusAndMemberTypeAndStatusNot(Long id,Integer applyStstus,Integer memberType,Integer status);
	//查询该俱乐部的部长
	public ClubMember findByApplyStatusAndMemberTypeAndStatusNotAndClubId(Integer applyStstus, Integer memberType, Integer status, Long clubId);
}
