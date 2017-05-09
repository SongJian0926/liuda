package com.web.liuda.business.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.liuda.business.entity.FondRecord;
import com.web.liuda.remote.vo.FondRecordVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface FondRecordService extends BaseServiceInterFace<FondRecord>{

	public XaResult<Page<FondRecordVo>> findFondRecordList(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException;
	/**
	 * 导出
	 * author:changlu
	 * time:2016-05-10 10:27:00
	 * @param filterParams
	 * @param response
	 * @throws BusinessException
	 */
	public void exportdata(Map<String, Object> filterParams,HttpServletResponse response) throws BusinessException ;
	/**
	 * 修改FondRecord状态，打款中、提现失败、提现成功
	 * author:changlu
	 * time:2016-05-10 11:27:00
	 * @param modelIds
	 * @param status
	 * @return
	 * @throws BusinessException
	 */
	public XaResult<FondRecord> changePresentState(
			String modelIds,Integer status) throws BusinessException;
}
