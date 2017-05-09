package com.web.liuda.business.controller;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.business.entity.CashRecord;
import com.web.liuda.business.service.CashRecordService;
import com.web.liuda.remote.vo.CashRecordVo;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.WebUitl;
import com.web.webstart.base.util.XaResult;
import com.wxpay.common.MD5;

/**
 * @Title: CashRecordController.java
 * @Package com.web.webstart.business.controller
 * @Description: 提现记录控制器
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Controller
@RequestMapping("/cms/cashRecord")
public class CashRecordController extends BaseController {

	@Autowired
	private CashRecordService cashRecordService;
	
	
	
	/**
	 * @Title: 后台提现管理列表
	 * @Description: 分页查询CashRecord	 * @param nextPage
	 * @param pageSize
	 * @param status		//默认查询非删除3的数据,具体类型参照XaConstant.Status
	 * @param sortDate
	 * @param jsonFilter
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findCashRecordNEStatusPage",method=RequestMethod.POST)
	public XaResult<Page<CashRecord>> findCashRecordNEStatusPage(
		@RequestParam(defaultValue = "0") Integer nextPage,
		@RequestParam(defaultValue = "10") Integer pageSize,
		@RequestParam(defaultValue = "3") Integer status,
		@RequestParam(defaultValue = "[{property:'createTime',direction:'DESC'}]") String sortData,
		//过滤字段,字段名:jsonFilter,选填,默认:{},示例:{'search_EQ_field1':'value1','search_EQ_field2':'value2'},字段名称拼接规则search_为固定查询标识,EQ为等于,filed为字段名
		//EQ等于, IN包含, ISNULL空, LIKE, GT大于, LT小于, GTE大于等于, LTE小于等于, NE不等于,LIKEIGNORE非like 
		@RequestParam(defaultValue = "{}") String jsonFilter
	) throws BusinessException{
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, sortData);
		Map<String,Object> filterParams =  WebUitl.getParametersStartingWith(jsonFilter, "search_");
		return cashRecordService.findListNEStatusByFilter(status, filterParams, pageable);
	}
	/**
	 * @Title: findFinanceList
	 * @Description: 分页查询财务管理	 * @param nextPage
	 * @param pageSize
	 * @param sortDate
	 * @param jsonFilter
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findFinanceList",method=RequestMethod.POST)
	public XaResult<Page<CashRecordVo>> findFinanceList(
		@RequestParam(defaultValue = "0") Integer nextPage,
		@RequestParam(defaultValue = "10") Integer pageSize,
		@RequestParam(defaultValue = "[{property:'createTime',direction:'DESC'}]") String sortData,
		@RequestParam(defaultValue = "{}") String jsonFilter
	) throws BusinessException{
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, sortData);
		Map<String,Object> filterParams =  WebUitl.getParametersStartingWith(jsonFilter, "search_");
		return cashRecordService.findList(filterParams, pageable);
	}
	/**
	 * @Title: findFinance
	 * @Description: 查询账户总额余额收入	 * @param nextPage
	 * @param pageSize
	 * @param sortDate
	 * @param jsonFilter
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findFinance",method=RequestMethod.POST)
	public XaResult<CashRecordVo> findFinance(
		@RequestParam(defaultValue = "0") Integer nextPage,
		@RequestParam(defaultValue = "10") Integer pageSize,
		@RequestParam(defaultValue = "[{property:'createTime',direction:'DESC'}]") String sortData,
		@RequestParam(value="businessId") Long businessId,
		@RequestParam(value="businessType") Integer businessType,
		@RequestParam(defaultValue = "{}") String jsonFilter
	) throws BusinessException{
		return cashRecordService.findFinancelist(businessId,businessType);
	}
	
	/**
	 * @Title: findRemain
	 * @Description: 查询余额	 * @param nextPage
	 * @param pageSize
	 * @param sortDate
	 * @param jsonFilter
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findRemain",method=RequestMethod.POST)
	public XaResult<CashRecordVo> findRemain(
		@RequestParam(value="businessId") Long businessId,
		@RequestParam(value="businessType") Integer businessType,
		@RequestParam(defaultValue = "{}") String jsonFilter
	) throws BusinessException{
		return cashRecordService.findRemain(businessId,businessType);
	}
	
	/**
	 * @Title: saveNewCashRecord
	 * @Description: 提现
	 * @param modelId
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="saveNewCashRecord",method=RequestMethod.POST)
	public XaResult<Boolean> saveNewCashRecord(
		@RequestParam Long id,
		@RequestParam String code,
		@RequestParam Double cashNum,
		@RequestParam String password,
		@RequestParam String mobile,
		@RequestParam Integer type,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<Boolean> xr=new XaResult<Boolean>();
		 //判断验证码是否正确
		if (BusinessInfoController.checkCode(request, mobile, code)!="ok" || !BusinessInfoController.checkCode(request, mobile, code).equals("ok")) {
			xr.error(BusinessInfoController.checkCode(request, mobile, code));
			return xr;
		}
		
		return cashRecordService.saveNewCashRecord(id, cashNum, MD5.MD5Encode(password),mobile,type);
	}
	/**
	 * @Title: findCashRecordById
	 * @Description: 根据ID查找单条实体
	 * @param modelId
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findCashRecordById",method=RequestMethod.POST)
	public XaResult<CashRecord> findCashRecordById(
		@RequestParam Long modelId
	) throws BusinessException{
		return cashRecordService.findOne(modelId);
	}
	/**
	 * @Title: saveModel
	 * @Description: 保存实体数据
	 * @param model
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="saveCashRecord",method=RequestMethod.POST)
	public XaResult<CashRecord> saveCashRecord(
		CashRecord model
	) throws BusinessException{
		return cashRecordService.saveOrUpdate(model);
	}
	//确认打款
	@ResponseBody
	@RequestMapping(value="sendMoney",method=RequestMethod.POST)
	public XaResult<CashRecord> sendMoney(
		HttpServletRequest request,
		@RequestParam String modelIds,
		@RequestParam Integer cashStatus
	) throws BusinessException{
		return cashRecordService.sendMoney(modelIds,cashStatus);
	}
	/**
	 * @Title: upload
	 * @Description: 文件下载
	 * @param photoFile
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping(value = "fileDownload", method = RequestMethod.GET)
	public void fileDownload(
			@RequestParam(defaultValue = "{}") String jsonFilter,
			HttpServletResponse response) throws BusinessException {
		try {
			jsonFilter = URLDecoder.decode(jsonFilter, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String, Object> filterParams = WebUitl.getParametersStartingWith(
				jsonFilter, "search_");
		cashRecordService.exportdata(filterParams, response);
	}
	
}

