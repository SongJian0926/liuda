package com.web.liuda.business.service;

import com.web.liuda.business.entity.ReplyComment;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface ReplyCommentService extends BaseServiceInterFace<ReplyComment>{
	public XaResult<ReplyComment> newSaveOrUpdate(ReplyComment model,String list)
			throws BusinessException ;

}
