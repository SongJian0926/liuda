package com.web.liuda.business.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.liuda.business.entity.ClubMember;
import com.web.liuda.remote.vo.ClubMemberVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface ClubMemberService extends BaseServiceInterFace<ClubMember>{

	XaResult<Page<ClubMemberVo>> findClubMemberByClubIdPage(Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException;

}
