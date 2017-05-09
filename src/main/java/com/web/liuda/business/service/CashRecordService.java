package com.web.liuda.business.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.web.liuda.business.entity.CashRecord;
import com.web.liuda.remote.vo.CashRecordVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface CashRecordService extends BaseServiceInterFace<CashRecord>{
	/*
	 * 分页查询财务管理列表
	 * author:changlu
	 * time:2016-01-11 12:00:00
	 * params:businessId 商家Id
	 * params:businessType 商家类型
	 * params:filterParams 查询条件
	 * params:pageable 分页参数
	 */
	public XaResult<Page<CashRecordVo>> findList(
			Map<String, Object> filterParams, Pageable pageable) throws BusinessException;
	/*
	 * 查询财务总额、余额、收入
	 * author:changlu
	 * time:2016-01-12 11:00:00
	 * params:businessId 商家Id
	 * params:businessType 商家类型
	 */
	public XaResult<CashRecordVo> findFinancelist(Long businessId, Integer businessType)
			throws BusinessException ;
	/*
	 * 用户提现
	 * author:songjian
	 * time:2016-01-12 11:00:00
	 * params:id 用户id
	 * params:businessType 商家类型
	 */
	public XaResult<Boolean> saveNewCashRecord(Long id,Double cashNum,String password,String mobile,Integer type)
		throws BusinessException ;
	//确认打款
	public XaResult<CashRecord> sendMoney(
			String modelIds,Integer cashStatus) throws BusinessException;
	
	
	//pc端提现管理的余额
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<CashRecordVo> findRemain(Long businessId, Integer businessType)
			throws BusinessException ;
	//提现管理的导出
	public void exportdata(Map<String, Object> filterParams,HttpServletResponse response) throws BusinessException;
}
