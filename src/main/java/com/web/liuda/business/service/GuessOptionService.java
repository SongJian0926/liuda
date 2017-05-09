package com.web.liuda.business.service;

import com.web.liuda.business.entity.GuessOption;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface GuessOptionService extends BaseServiceInterFace<GuessOption>{

	XaResult<GuessOption> setGuessOption(Long matchId, Long modelId)throws BusinessException;

}
