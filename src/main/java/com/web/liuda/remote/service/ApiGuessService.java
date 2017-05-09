package com.web.liuda.remote.service;

import com.web.liuda.business.entity.Guess;
import com.web.liuda.business.entity.GuessLog;
import com.web.liuda.remote.vo.GuessLogVo;
import com.web.liuda.remote.vo.GuessOptionVo;
import com.web.liuda.remote.vo.GuessVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;


public interface ApiGuessService extends ApiBaseService<GuessVo,Guess>{
	
	/**
	 * 查询竞猜
	 * author:flora.li
	 * time:2016-04-22 11:16:00
	 * @param status
	 * @param matchId
	 * @return
	 * @throws BusinessException
	 */
	XaResult<GuessVo> findGuessByMatchIdAndUserId(Integer status, Long userId, Long matchId) throws BusinessException ;
	
	XaResult<GuessLogVo> createGuessLogModel(GuessLog guessLog) throws BusinessException ;

	XaResult<GuessLogVo> findGuessLogByMatchIdAndUserId(Integer status, Long userId, Long matchId) throws BusinessException;

	XaResult<GuessOptionVo> findGuessOptionByOptionId(Integer status, Long optionId) throws BusinessException;
}
