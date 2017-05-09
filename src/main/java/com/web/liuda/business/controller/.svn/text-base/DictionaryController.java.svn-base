package com.web.liuda.business.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.business.entity.Dictionary;
import com.web.liuda.business.service.DictionaryService;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.WebUitl;
import com.web.webstart.base.util.XaResult;


/**
 * @Title: CooperationController.java
 * @Package com.web.webstart.business.controller
 * @Description: 资讯分类管理控制器
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Controller
@RequestMapping("cms/dictionary")
public class DictionaryController extends BaseController
{
	@Autowired
	private DictionaryService dictionaryService;
	
	/**
	 * @Title: findCooperationById
	 * @Description: 根据ID查找单条实体
	 * @param modelId
	 * @return    
	 */

	@ResponseBody
	@RequestMapping(value="findDictionaryById",method=RequestMethod.POST)
	public XaResult<Dictionary> findCooperationById(
		@RequestParam Long modelId
	) throws BusinessException{
		return dictionaryService.findOne(modelId);
	}
	
	
	
	/**
	 * @Title: findChannelNEStatusPage
	 * @Description: 分页查询Channel	 * @param nextPage
	 * @param pageSize
	 * @param status		//默认查询非删除3的数据,具体类型参照XaConstant.Status
	 * @param sortDate
	 * @param jsonFilter
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findDictionaryNEStatusPage",method=RequestMethod.POST)
	public XaResult<Page<Dictionary>> findChannelNEStatusPage(
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
		return dictionaryService.findListNEStatusByFilter(status, filterParams, pageable);
	}
	
	/**
	 * @Title: saveModel
	 * @Description: 保存实体数据
	 * @param model
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="savedictionary",method=RequestMethod.POST)
	public XaResult<Dictionary> saveDictionary(
			Dictionary model
	) throws BusinessException{
		System.out.println(model.getName());
		System.out.println(model.getType());
		return dictionaryService.saveOrUpdate(model);
	}
	
	/**
	 * @Title: operateDictionaryByIds
	 * @Description: 批量操作多个实体状态,根据status进行操作
	 * @param modelIds    	多个实体id,中间用逗号隔开
	 * @param status 		操作类型,status类型见XaConstant.Status,默认删除操作
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="operateDictionaryByIds",method=RequestMethod.POST)
	public XaResult<Dictionary> operateInfoCommentByIds(
		HttpServletRequest request,
		@RequestParam String modelIds,
		@RequestParam(defaultValue = "3") Integer status
	) throws BusinessException{
		return dictionaryService.multiOperate(modelIds,status);
	}
	
}
