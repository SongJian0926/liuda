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

import com.web.liuda.business.entity.FeedBack;
import com.web.liuda.business.service.FeedBackService;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.WebUitl;
import com.web.webstart.base.util.XaResult;

/**
 * @Title: FeedBackController.java
 * @Package com.web.webstart.business.controller
 * @Description: 意见反馈控制器
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Controller
@RequestMapping("/cms/feedBack")
public class FeedBackController extends BaseController {

	@Autowired
	private FeedBackService feedBackService;

	/**
	 * @Title: findFeedBack
	 * @Description: 分页查询FeedBack * @param nextPage
	 * @param pageSize
	 * @param status
	 *            //默认查询非删除3的数据,具体类型参照XaConstant.Status
	 * @param sortDate
	 * @param jsonFilter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findFeedBack", method = RequestMethod.POST)
	public XaResult<Page<FeedBack>> findFeedBack(
			@RequestParam(defaultValue = "0") Integer nextPage,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "3") Integer status,
			@RequestParam(defaultValue = "[{property:'createTime',direction:'DESC'}]") String sortData,
			// 过滤字段,字段名:jsonFilter,选填,默认:{},示例:{'search_EQ_field1':'value1','search_EQ_field2':'value2'},字段名称拼接规则search_为固定查询标识,EQ为等于,filed为字段名
			// EQ等于, IN包含, ISNULL空, LIKE, GT大于, LT小于, GTE大于等于, LTE小于等于,
			// NE不等于,LIKEIGNORE非like
			@RequestParam(defaultValue = "{}") String jsonFilter)
			throws BusinessException {
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize,
				sortData);
		Map<String, Object> filterParams = WebUitl.getParametersStartingWith(
				jsonFilter, "search_");
		return feedBackService.findList(status, filterParams, pageable);
	}

	/**
	 * @Title: findFeedBackNEStatusPage
	 * @Description: 分页查询FeedBack * @param nextPage
	 * @param pageSize
	 * @param status
	 *            //默认查询非删除3的数据,具体类型参照XaConstant.Status
	 * @param sortDate
	 * @param jsonFilter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findFeedBackNEStatusPage", method = RequestMethod.POST)
	public XaResult<Page<FeedBack>> findFeedBackNEStatusPage(
			@RequestParam(defaultValue = "0") Integer nextPage,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "3") Integer status,
			@RequestParam(defaultValue = "[{property:'createTime',direction:'DESC'}]") String sortData,
			// 过滤字段,字段名:jsonFilter,选填,默认:{},示例:{'search_EQ_field1':'value1','search_EQ_field2':'value2'},字段名称拼接规则search_为固定查询标识,EQ为等于,filed为字段名
			// EQ等于, IN包含, ISNULL空, LIKE, GT大于, LT小于, GTE大于等于, LTE小于等于,
			// NE不等于,LIKEIGNORE非like
			@RequestParam(defaultValue = "{}") String jsonFilter)
			throws BusinessException {
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize,
				sortData);
		Map<String, Object> filterParams = WebUitl.getParametersStartingWith(
				jsonFilter, "search_");
		return feedBackService.findListNEStatusByFilter(status, filterParams,
				pageable);
	}

	/**
	 * @Title: findFeedBackEQStatusPage
	 * @Description: (预留)分页查询FeedBack * @param nextPage
	 * @param pageSize
	 * @param status
	 *            //默认查询正常状态1的数据,具体类型参照XaConstant.Status
	 * @param sortDate
	 * @param jsonFilter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findFeedBackEQStatusPage", method = RequestMethod.POST)
	public XaResult<Page<FeedBack>> findFeedBackEQStatusPage(
			@RequestParam(defaultValue = "0") Integer nextPage,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "1") Integer status,
			@RequestParam(defaultValue = "[{property:'createTime',direction:'DESC'}]") String sortData,
			@RequestParam(defaultValue = "{}") String jsonFilter)
			throws BusinessException {
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize,
				sortData);
		Map<String, Object> filterParams = WebUitl.getParametersStartingWith(
				jsonFilter, "search_");
		return feedBackService.findListEQStatusByFilter(status, filterParams,
				pageable);
	}

	/**
	 * @Title: findFeedBackById
	 * @Description: 根据ID查找单条实体
	 * @param modelId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findFeedBackById", method = RequestMethod.POST)
	public XaResult<FeedBack> findFeedBackById(@RequestParam Long modelId,
			@RequestParam String createTime) throws BusinessException {
		return feedBackService.findOneUser(modelId, createTime);
	}

	/**
	 * @Title: multiOperateFeedBackByIds
	 * @Description: 批量操作多个实体状态,根据status进行操作
	 * @param modelIds
	 *            多个实体id,中间用逗号隔开
	 * @param status
	 *            操作类型,status类型见XaConstant.Status,默认删除操作
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "operateFeedBackByIds", method = RequestMethod.POST)
	public XaResult<FeedBack> operateFeedBackByIds(HttpServletRequest request,
			@RequestParam String modelIds,
			@RequestParam(defaultValue = "3") Integer status)
			throws BusinessException {
		return feedBackService.multiOperate(modelIds, status);
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
		feedBackService.exportdata(filterParams, response);
	}
}
