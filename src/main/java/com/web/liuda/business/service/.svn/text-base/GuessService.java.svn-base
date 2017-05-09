package com.web.liuda.business.service;

import com.web.liuda.business.entity.Guess;
import com.web.liuda.remote.vo.GuessVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface GuessService extends BaseServiceInterFace<Guess>{

	XaResult<GuessVo> findByMacthIdAndNotStatus(Long modelId, int delete) throws BusinessException ;

	XaResult<Guess> computeGuess(Long matchId, Long optionId) throws BusinessException ;

	XaResult<Guess> setGuessOption(Long matchId, Long optionId) throws BusinessException ;

}
