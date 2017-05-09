package com.web.liuda.business.service;

import java.util.List;

import com.web.liuda.business.entity.Vote;
import com.web.liuda.business.entity.VoteOption;
import com.web.liuda.remote.vo.VoteVo;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface VoteService extends BaseServiceInterFace<Vote>{

	
	public  XaResult<VoteVo> saveVoteing(Vote model,List<VoteOption> voteOptions);
	
	public XaResult<VoteVo> findOnes(Long modelId);
}
