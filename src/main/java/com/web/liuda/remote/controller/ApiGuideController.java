package com.web.liuda.remote.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.Guide;
import com.web.liuda.business.entity.GuideAppend;
import com.web.liuda.remote.service.ApiGuideService;
import com.web.liuda.remote.vo.GuideVo;
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
 * @Title: ApiGuideController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 攻略表信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "Guide", description = "攻略接口(二期)", position = 10)
@Controller
@RequestMapping("/api/guide")
public class ApiGuideController extends BaseController {

	@Autowired
	private ApiGuideService guideService;
	
	/**
	 * @Title: findGuideList
	 * @Description: 攻略列表
	 * @return    
	 */
	@ApiOperation(value="攻略列表",notes="攻略列表,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findGuideList",method=RequestMethod.POST)
	public XaResult<Page<GuideVo>> findGuideList(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		@ApiParam("攻略标签Id,字段名:dictItemId,如果不传查询全部；") @RequestParam(required=false,value="dictItemId") Long dictItemId,
		@ApiParam("攻略类型,字段名:guideType,如果不传查询全部；1：精品;2:大咖") @RequestParam(required=false) Integer guideType, 
		@ApiParam("title,字段名:攻略标题") @RequestParam(required=false) String title
		
	) throws BusinessException{
		XaResult<Page<GuideVo>> xr=new XaResult<Page<GuideVo>>();
		//攻略标签Id
		if(XaUtil.isNotEmpty(dictItemId)){
			if(dictItemId<1){
				xr.error("dictItemId不能小于1");
				return xr;
			}
		}
		//攻略类型
		if(XaUtil.isNotEmpty(guideType)){
			if(guideType!=1&&guideType!=2){
				xr.error("guideType只能是1或2！");
				return xr;
			}
		}
		
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");
		//Map<String , Object> filterParams =  WebUitl.getParametersStartingWith("{}", "search_");
		return guideService.findGuideList(XaConstant.Status.valid, dictItemId,guideType,title,pageable);
	}
	
	/**
	 * @Title: findGuideById
	 * @Description: 攻略详情
	 * @param modelId
	 * author:changlu
	 * @return    
	 */
	@ApiOperation(value="攻略详情——web端和移动端使用",notes="攻略详情,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findGuideById",method=RequestMethod.POST)
	public XaResult<GuideVo> findGuideById(
		@ApiParam("编号,字段名:id,必填") @RequestParam(value = "id") Long id
	) throws BusinessException{
		return guideService.findOne(id);
	}
	/**
	 * @Title: addPageview
	 * @Description: 点击攻略增加浏览量
	 * @param modelId
	 * author:changlu
	 * @return    
	 */
	@ApiOperation(value="点击攻略增加浏览量",notes="点击攻略增加浏览量,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="addPageview",method=RequestMethod.POST)
	public XaResult<GuideVo> addPageview(
		@ApiParam("编号,字段名:id,必填") @RequestParam(value = "id") Long id
	) throws BusinessException{
		return guideService.addPageview(id);
	}
	/**
	 * @Title: save
	 * @Description: 创建攻略、追加攻略
	 * author:changlu
	 * @return    
	 */
	@ApiOperation(value="创建攻略、追加攻略——web端和移动端使用",notes="创建攻略、追加攻略,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="saveGuide",method=RequestMethod.POST)
	public XaResult<GuideVo> saveGuide(
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId,
		@ApiParam("内容,字段名:content") @RequestParam(value = "content") String content,
		@ApiParam("标题,字段名:title，创建攻略必传，追加不传") @RequestParam(required=false,value = "title") String title,
		@ApiParam("兴趣标签,字段名:dictItemId，创建攻略必传，追加不传") @RequestParam(required=false,value = "dictItemId") Long dictItemId,
		@ApiParam("攻略Id,字段名:guideId,追加时必传") @RequestParam(required=false,value = "guideId") Long guideId,
		@ApiParam("图片,字段名:mediaPath,多张以逗号隔开") @RequestParam(required=false,value = "mediaPath") String mediaPath,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<GuideVo> xr=new XaResult<GuideVo> ();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		Guide model = new Guide();
		model.setUserId(userId);
		
		if(XaUtil.isEmpty(guideId)){
			model.setType(JConstant.BooleanStatus.FALSE);
			if(XaUtil.isEmpty(title)){
				xr.error("创建攻略时标题不能为空！");
				return xr;
			}
			if(XaUtil.isEmpty(dictItemId)){
				xr.error("兴趣标签不能为空！");
				return xr;
			}else if(dictItemId<1){
				xr.error("兴趣标签不能小于1！");
				return xr;
			}
		}
		model.setTitle(title);
		model.setDictItemId(dictItemId);
		model.setLastUpdate(XaUtil.getToDayStr().substring(0,10));
		GuideAppend guideAppend = new GuideAppend();
		guideAppend.setContent(content);
		guideAppend.setMediaPath(mediaPath);
		guideAppend.setGuideId(guideId);
		model.setPageview(0);
		return guideService.createGuide(model,guideAppend);
	}
	/**
	 * @Title: ApplyCompetitiv
	 * @Description: 申请为精品
	 * @param modelId
	 * author:sj
	 * @return    
	 */
	@ApiOperation(value="申请为精品——web端和移动端使用",notes="申请为精品,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="ApplyCompetitiv",method=RequestMethod.POST)
	public XaResult<String> ApplyCompetitiv(
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId,
		@ApiParam("攻略Id,字段名:id") @RequestParam(value = "id") Long id
	) throws BusinessException{
		XaResult<String> xr=new XaResult<String> ();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户id不能为空");
			return xr;
		}if(XaUtil.isEmpty(id)){
			xr.error("攻略id不能为空");
			return xr;
		}
		return guideService.ApplyCompetitiv(userId,id);
	}
	/**
	 * @Title: 
	 * @Description: 修改攻略
	 * @param userId
	 * @param model
	 * @return    
	 */
	/*@ApiOperation(value="修改攻略",notes="修改攻略,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="updateGuide",method=RequestMethod.POST)
	public XaResult<GuideVo> updateGuide(
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId,
		@ApiParam("标题,字段名:title") @RequestParam(value = "title") String title,
		@ApiParam("类型,字段名:type") @RequestParam(value = "type") Integer type,
		@ApiParam("浏览量,字段名:pageview") @RequestParam(value = "pageview") Integer pageview,
		@ApiParam("对象Id,字段名:objectId") @RequestParam(value = "objectId") Long objectId,
		@ApiParam("对象类型,字段名:objectType") @RequestParam(value = "objectType") Integer objectType,
		@ApiParam("申精状态,字段名:applyStatus") @RequestParam(value = "applyStatus") Integer applyStatus,
		@ApiParam("审核备注,字段名:applyMemo") @RequestParam(value = "applyMemo") String applyMemo,
		@ApiParam("最后更新日期,字段名:lastUpdate") @RequestParam(value = "lastUpdate") String lastUpdate,
		@ApiParam("版本编号,字段名:tId") @RequestParam(value = "tId") Long tId,
	HttpServletRequest request
	) throws BusinessException{
		Guide model = new Guide();
		model.setUserId(userId);
		model.setTitle(title);
		model.setType(type);
		model.setPageview(pageview);
		model.setObjectId(objectId);
		model.setObjectType(objectType);
		model.setApplyStatus(applyStatus);
		model.setApplyMemo(applyMemo);
		model.setLastUpdate(lastUpdate);
		model.setId(tId);
		return guideService.updateModel(model);
	}*/
	
	/**
	 * @Title: operateGuideById
	 * @Description: 操作一个实体状态,根据status进行操作
	 * @param modelId
	 * @param status 		操作类型:-1锁定,0无效,1正常,2发布,3删除,默认删除操作
	 * @return    
	 *//*
	@ApiOperation(value="根据ID修改攻略表状态",notes="修改攻略表状态,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="operateGuideById",method=RequestMethod.POST)
	public XaResult<GuideVo> operateGuideById(
		HttpServletRequest request,
		@ApiParam("编号,字段名:modelIds,必填") @RequestParam(value = "modelId") String modelIds,
		@ApiParam("操作类型,字段名:status,-1锁定,0无效,1正常,2发布,3删除,选填,默认删除操作") @RequestParam(defaultValue = "3") Integer status
	) throws BusinessException{
		return guideService.multiOperate(modelIds,status);
	}
	
	*//**
	 * @Title: upload
	 * @Description: 图片上传
	 * @param photoFile
	 * @param request
	 * @return    
	 *//*
	@ApiOperation(value="图片上传",notes="异步图片上传,返回上传图片的地址、宽、高")
	@ResponseBody
	@RequestMapping(value="photoUpload",method=RequestMethod.POST)
	public XaResult<Map<String, Object>> photoUpload(
		@ApiParam("上传的图片,字段名:photoFile,必填") @RequestParam(value = "photoFile") MultipartFile photoFile, 
		HttpServletRequest request
	) throws BusinessException{
		XaResult<Map<String, Object>> result = new XaResult<Map<String, Object>>();
		String root=request.getSession().getServletContext().getRealPath("/");
		String picturePath = "/upload/guide";
		String ext =photoFile.getOriginalFilename().substring(photoFile.getOriginalFilename().lastIndexOf("."));
		String newName = new Date().getTime()+ext;
		File filedict = new File(root+picturePath);
		if(!filedict.exists()){
			filedict.mkdir();
		}
		File targetFile=new File(root+picturePath+File.separator+newName);
		try {
			if(StringUtils.equalsIgnoreCase(ext, ".jpg") || StringUtils.equalsIgnoreCase(ext, ".png")){
				photoFile.transferTo(targetFile);
				BufferedImage bimg = ImageIO.read(targetFile);
				int width = bimg.getWidth();
				int height = bimg.getHeight();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("pictureHeight",height);
				map.put("pictureWidth",width);
				map.put("picturePath",picturePath+"/"+newName);
				result.setObject(map);
				return result;
			}
			else{
				throw new BusinessException("上传文件类型不允许,请上传jpg/png图片");
			}
		} catch (IllegalStateException e) {
			throw new BusinessException("图片上传失败");
		} catch (IOException e) {
			throw new BusinessException("图片上传失败");
		} catch (Exception e) {
			throw new BusinessException("图片上传失败");
		}
	}*/
}

