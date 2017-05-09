package com.web.liuda.remote.service;

import org.springframework.data.domain.Pageable;

import com.web.liuda.business.entity.FondRecord;
import com.web.liuda.remote.vo.FondRecordVo;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;

public interface ApiFondRecordService extends ApiBaseService<FondRecordVo,FondRecord>{
	
	public XaResult<FondRecordVo> findFondRecordListByUserId(Pageable pageable,Long userId);
	
	public XaResult<String> withdrawCashIng(Long userId,Double cash,Long cardId);
}
