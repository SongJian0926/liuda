package com.web.liuda.business.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.liuda.business.entity.Message;
import com.web.liuda.remote.vo.MessageVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface MessageService extends BaseServiceInterFace<Message>{

	 //导出
	public void exportdata(Map<String, Object> filterParams,HttpServletResponse response) throws BusinessException;

	public XaResult<Page<MessageVo>> findListNEStatusByFilterTwo(Integer status, Map<String, Object> filterParams,
			Pageable pageable) throws BusinessException;
}
