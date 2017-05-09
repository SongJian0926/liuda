package com.web.liuda.remote.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.business.entity.Collect;
import com.web.liuda.remote.service.ApiCollectService;
import com.web.liuda.remote.vo.CollectVo;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Title: ApiCollectController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 关注信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "Collect", description = "关注接口(二期)", position = 10)
@Controller
@RequestMapping("/api/collect")
public class ApiCollectController extends BaseController {

	@Autowired
	private ApiCollectService collectService;
	

	
	/**
	 * @Title: save
	 * @Description: 关注(俱乐部活动/赛事活动)
	 * @param userId
	 * @param model
	 * author:changlu
	 * time:2016-04-19 14:24:00
	 * @return    
	 */
	@ApiOperation(value="关注(俱乐部活动/赛事活动)",notes="关注(俱乐部活动/赛事活动),当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="saveCollect",method=RequestMethod.POST)
	public XaResult<CollectVo> saveCollect(
		@ApiParam("对象Id,字段名:objectId") @RequestParam(value = "objectId") Long objectId,
		@ApiParam("类型,字段名:type,:1.赛事，2.活动") @RequestParam(value = "type") Integer type,
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<CollectVo> xr=new XaResult<CollectVo>();
		if(XaUtil.isEmpty(objectId)){
			xr.error("对象Id不能为空！");
			return xr;
		}else if(objectId<1){
			xr.error("对象Id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}else if(userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(type)){
			xr.error("类型不能小于1！");
			return xr;
		}
		if(type!=1&&type!=2){
			xr.error("类型只能是1或2！");
			return xr;
		}
		Collect model = new Collect();
		model.setObjectId(objectId);
		model.setType(type);
		model.setUserId(userId);
		return collectService.createModel(model);
	}
	
	
	
	/**
	 * @Title: operateCollectById
	 * @Description: 取消关注
	 * @param modelId
	 * @param status 		操作类型:-1锁定,0无效,1正常,2发布,3删除,默认删除操作
	 * author:changlu
	 * time:2016-04-19 14:24:00
	 * @return    
	 */
	@ApiOperation(value="取消关注(俱乐部活动/赛事)",notes="取消关注(俱乐部活动/赛事),当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="operateCollectById",method=RequestMethod.POST)
	public XaResult<CollectVo> operateCollectById(
		HttpServletRequest request,
		@ApiParam("编号,字段名:modelIds,必填") @RequestParam(value = "modelId") String modelIds,
		@ApiParam("操作类型,字段名:status,3删除") @RequestParam(defaultValue = "3") Integer status,
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId
	) throws BusinessException{
		XaResult<CollectVo> xr=new XaResult<CollectVo> ();
		if(XaUtil.isEmpty(modelIds)){
			xr.error("编号不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(status)){
			xr.error("status不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		return collectService.multiOperate(modelIds,status);
	}
	
}

