package com.web.liuda.remote.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.remote.service.ApiBannerService;
import com.web.liuda.remote.vo.BannerVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.WebUitl;
import com.web.webstart.base.util.XaResult;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * @Title: ApiBannerController.java
 * @Package com.web.liuda.remote.controller
 * @Description: Banner图信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "Banner", description = "Banner图", position = 10)
@Controller
@RequestMapping("/api/banner")
public class ApiBannerController extends BaseController {

	@Autowired
	private ApiBannerService bannerService;
	
	/**
	 * @Title: findBanners
	 * @Description: 查询Banner图
	 * @return    
	 */
	@ApiOperation(value="查询Banner图",notes="查询Banner图,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findBanners",method=RequestMethod.POST)
	public XaResult<List<BannerVo>> findBannerList(
		
	) throws BusinessException{
		Pageable pageable = WebUitl.buildPageRequest(0, 10, "[{property:'sort',direction:'ASC'}]");
		Map<String , Object> filterParams =  WebUitl.getParametersStartingWith("{}", "search_");
		return bannerService.findListEQStatusByFilter(XaConstant.Status.valid, filterParams, pageable);
	}

}

