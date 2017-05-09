package com.web.liuda.remote.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.remote.service.ApiNoticeService;
import com.web.liuda.remote.vo.NoticeVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.WebUitl;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Title: ApiNoticeController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 公告通知信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "Notice", description = "公告通知接口(二期)", position = 10)
@Controller
@RequestMapping("/api/notice")
public class ApiNoticeController extends BaseController {

	@Autowired
	private ApiNoticeService noticeService;
	
	/**
	 * @Title: findNoticeList
	 * @Description: 攻略页面的黑板报、赛事页面活动公告列表显示最新的6个
	 * author：changlu
	 * time:2016-4-8 10:12:00
	 * @return    
	 */
	@ApiOperation(value="黑板报、活动公告、web端首页新闻报道列表显示最新的6个——web端和移动端使用",notes="黑板报、活动公告、web端首页新闻报道列表,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findNoticeList",method=RequestMethod.POST)
	public XaResult<List<NoticeVo>> findNoticeList(
		@ApiParam("公告类型,字段名:type,1:黑板报，2:活动公告,3:新闻报道") @RequestParam(value="type") Integer type	
	) throws BusinessException{
		XaResult<List<NoticeVo>> xr=new XaResult<List<NoticeVo>>();
		//type不能为空
		if(XaUtil.isEmpty(type)){
			xr.error("公告类型不能为空！");
			return xr;
		}
		//判断type格式是否正确
		if(type!=1&&type!=2&&type!=3){
			xr.error("公告类型只能是1或者2或者3！");
			return xr;
		}
		
		Pageable pageable = WebUitl.buildPageRequest(0, 6, "[{property:'createTime',direction:'DESC'}]");
		Map<String , Object> filterParams =  WebUitl.getParametersStartingWith("{'search_EQ_type':'"+type+"'}", "search_");
		return noticeService.findListEQStatusByFilter(XaConstant.Status.valid, filterParams, pageable);
	}
	/**
	 * @Title: findNoticeList
	 * @Description: web端新闻报道
	 * author：changlu
	 * time:2016-4-26 14:12:00
	 * @return    
	 */
	@ApiOperation(value="web端新闻报道列表——web端使用",notes="web端新闻报道列表,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findNoticeListEQStatus",method=RequestMethod.POST)
	public XaResult<Page<NoticeVo>> findNoticeListEQStatus(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		@ApiParam("公告类型,字段名:type,必传，3:新闻报道") @RequestParam(value="type") Integer type	
	) throws BusinessException{
		XaResult<Page<NoticeVo>> xr=new XaResult<Page<NoticeVo>>();
		//type不能为空
		if(XaUtil.isEmpty(type)){
			xr.error("公告类型不能为空！");
			return xr;
		}
		//判断type格式是否正确
		if(type!=3){
			xr.error("公告类型只能3！");
			return xr;
		}
		
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");
		Map<String , Object> filterParams =  WebUitl.getParametersStartingWith("{'search_EQ_type':'"+type+"'}", "search_");
		return noticeService.findNoticeListEQStatus(XaConstant.Status.valid, filterParams, pageable);
	}
	/**
	 * @Title: findNoticeById
	 * @Description: 黑板报、活动公告、新闻报道详情
	 * author：changlu
	 * time:2016-4-8 10:12:00
	 * @param modelId
	 * @return    
	 */
	@ApiOperation(value="黑板报、活动公告、新闻报道详情——web端和移动端使用",notes="黑板报、活动公告、新闻报道详情,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findNoticeById",method=RequestMethod.POST)
	public XaResult<NoticeVo> findNoticeById(
		@ApiParam("公告Id,字段名:id,必填") @RequestParam(value = "id") Long id
	) throws BusinessException{
		XaResult<NoticeVo> xr=new XaResult<NoticeVo>();
		//id不能为空
		if(XaUtil.isEmpty(id)){
			xr.error("公告Id不能为空！");
			return xr;
		}
		
		return noticeService.findOne(id);
	}
}

