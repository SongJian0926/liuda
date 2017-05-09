package com.web.liuda.remote.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.liuda.business.entity.Vote;
import com.web.liuda.remote.vo.VoteVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;

public interface ApiVoteService extends ApiBaseService<VoteVo,Vote>{
	
	public XaResult<Page<VoteVo>> findListEQStatusByFilters(Integer status,Map<String , Object> filterParams,Pageable pageable)throws BusinessException;
	
	public XaResult<VoteVo> findOneByUserId(Long modelId,Long userId)throws BusinessException;

	public XaResult<String> voting(Long modelId,Long userId,String voteOptiones);
}
