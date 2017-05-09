package com.web.liuda.remote.controller;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.web.liuda.business.entity.EventReview;
import com.web.liuda.remote.service.ApiEventReviewService;
import com.web.liuda.remote.vo.EventReviewVo;
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
 * @Title: ApiEventReviewController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 俱乐部活动回顾表信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "EventReview", description = "俱乐部活动回顾表接口(二期)", position = 10)
@Controller
@RequestMapping("/api/eventReview")
public class ApiEventReviewController extends BaseController {

	@Autowired
	private ApiEventReviewService eventReviewService;
	
	/**
	 * @Title: findEventReviewList
	 * @Description: 俱乐部活动回顾列表
	 * @return    
	 */
	@ApiOperation(value="俱乐部活动回顾列表",notes="俱乐部活动回顾列表,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findEventReviewList",method=RequestMethod.POST)
	public XaResult<Page<EventReviewVo>> findEventReviewList(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		@ApiParam("活动Id,字段名:clubEventId") @RequestParam(value = "clubEventId") Long clubEventId
	) throws BusinessException{
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");
		Map<String , Object> filterParams =  WebUitl.getParametersStartingWith("{'search_EQ_clubEventId':'"+clubEventId+"'}", "search_");
		return eventReviewService.findEventReviewList(XaConstant.Status.valid, filterParams, pageable);
	}
	
	/**
	 * @Title: findEventReviewById
	 * @Description: 活动回顾详情
	 * @param modelId
	 * @return    
	 */
	@ApiOperation(value="活动回顾详情——web端和移动端使用",notes="活动回顾详情,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findEventReviewById",method=RequestMethod.POST)
	public XaResult<EventReviewVo> findEventReviewById(
		@ApiParam("编号,字段名:modelId,必填") @RequestParam(value = "modelId") Long modelId
	) throws BusinessException{
		return eventReviewService.findOne(modelId);
	}
	
	/**
	 * @Title: save
	 * @Description: 上传活动回顾
	 * @param userId
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="上传活动回顾——web端和移动端使用",notes="上传活动回顾,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="saveEventReview",method=RequestMethod.POST)
	public XaResult<EventReviewVo> saveEventReview(
		@ApiParam("回顾标题,字段名:title") @RequestParam(value = "title") String title,
		@ApiParam("内容,字段名:content") @RequestParam(value = "content") String content,
		@ApiParam("图片,字段名:mediaPath") @RequestParam(required=false,value = "mediaPath") String mediaPath,
		@ApiParam("发布人,字段名:userId") @RequestParam(value = "userId") Long userId,
		@ApiParam("活动Id,字段名:eventId") @RequestParam(value = "eventId") Long eventId,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<EventReviewVo> xr=new XaResult<EventReviewVo>();
		if(XaUtil.isEmpty(userId)){
			xr.error("发布人不能为空！");
			return xr;
		}else if(userId<1){
			xr.error("发布人不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(eventId)){
			xr.error("活动Id不能为空！");
			return xr;
		}else if(eventId<1){
			xr.error("活动Id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(content)){
			xr.error("内容不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(title)){
			xr.error("标题不能为空！");
			return xr;
		}
		EventReview model = new EventReview();
		model.setTitle(title);
		model.setContent(content);
		model.setMediaPath(mediaPath);
		model.setUserId(userId);
		model.setEventId(eventId);
		return eventReviewService.createModel(model);
	}
	
	/**
	 * @Title: 
	 * @Description: 修改一条实体
	 * @param userId
	 * @param model
	 * @return    
	 */
	/*@ApiOperation(value="修改俱乐部活动回顾表",notes="修改俱乐部活动回顾表,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="updateEventReview",method=RequestMethod.POST)
	public XaResult<EventReviewVo> updateEventReview(
		@ApiParam("回顾标题,字段名:title") @RequestParam(value = "title") String title,
		@ApiParam("内容,字段名:content") @RequestParam(value = "content") String content,
		@ApiParam("图片或者视频,字段名:mediaPath") @RequestParam(value = "mediaPath") String mediaPath,
		@ApiParam("发布人,字段名:userId") @RequestParam(value = "userId") Long userId,
		@ApiParam("活动Id,字段名:eventId") @RequestParam(value = "eventId") Long eventId,
		@ApiParam("版本编号,字段名:tId") @RequestParam(value = "tId") Long tId,
	HttpServletRequest request
	) throws BusinessException{
		EventReview model = new EventReview();
		model.setTitle(title);
		model.setContent(content);
		model.setMediaPath(mediaPath);
		model.setUserId(userId);
		model.setEventId(eventId);
		model.setId(tId);
		return eventReviewService.updateModel(model);
	}*/
	
	/**
	 * @Title: operateEventReviewById
	 * @Description: 操作一个实体状态,根据status进行操作
	 * @param modelId
	 * @param status 		操作类型:-1锁定,0无效,1正常,2发布,3删除,默认删除操作
	 * @return    
	 */
	/*@ApiOperation(value="根据ID修改俱乐部活动回顾表状态",notes="修改俱乐部活动回顾表状态,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="operateEventReviewById",method=RequestMethod.POST)
	public XaResult<EventReviewVo> operateEventReviewById(
		HttpServletRequest request,
		@ApiParam("编号,字段名:modelIds,必填") @RequestParam(value = "modelId") String modelIds,
		@ApiParam("操作类型,字段名:status,-1锁定,0无效,1正常,2发布,3删除,选填,默认删除操作") @RequestParam(defaultValue = "3") Integer status
	) throws BusinessException{
		return eventReviewService.multiOperate(modelIds,status);
	}*/
	
	/**
	 * @Title: upload
	 * @Description: 图片上传
	 * @param photoFile
	 * @param request
	 * @return    
	 */
	/*@ApiOperation(value="图片/视频上传——web端和移动端使用",notes="异步图片上传,返回上传图片的地址、宽、高")
	@ResponseBody
	@RequestMapping(value="photoUpload",method=RequestMethod.POST)
	public XaResult<String> photoUpload(
		@ApiParam("上传的图片,字段名:photoFile,必填,jpg/png图片或者mp4或者mov视频") @RequestParam(value = "photoFile") MultipartFile photoFile, 
		HttpServletRequest request
	) throws BusinessException{
		XaResult<String> xr = new XaResult<String>();
		String root=request.getSession().getServletContext().getRealPath("/");
		String picturePath = "/upload/eventReview";
		String ext =photoFile.getOriginalFilename().substring(photoFile.getOriginalFilename().lastIndexOf("."));
		String newName = new Date().getTime()+ext;
		File filedict = new File(root+picturePath);
		if(!filedict.exists()){
			filedict.mkdir();
		}
		File targetFile=new File(root+picturePath+File.separator+newName);
		//限制上传文件的大小
		if(photoFile.getSize() > 1024 * 1024 * 30){
			xr.setCode(0);
			xr.setObject(XaConstant.Code.error+":图片/视频大小不能超过30M");
			return xr;
		}
		try {
			if(StringUtils.equalsIgnoreCase(ext, ".jpg") || StringUtils.equalsIgnoreCase(ext, ".png") || StringUtils.equalsIgnoreCase(ext, ".mp4")|| StringUtils.equalsIgnoreCase(ext, ".mov")){
				photoFile.transferTo(targetFile);
				
				BufferedImage bimg = ImageIO.read(targetFile);
				int width = bimg.getWidth();
				int height = bimg.getHeight();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("pictureHeight",height);
				map.put("pictureWidth",width);
				xr.setCode(1);
				xr.setObject(XaConstant.Code.success+":"+picturePath+"/"+newName);
				return xr;
			}
			else{
				xr.setCode(0);
				xr.setObject(XaConstant.Code.error+":上传文件类型不允许,请上传jpg/png图片或者mp4或者mov视频");
				return xr;
			}
		} catch (IllegalStateException e) {
			xr.setCode(0);
			xr.setObject(XaConstant.Code.error+":图片上传失败");
			return xr;
		} catch (IOException e) {
			xr.setCode(0);
			xr.setObject(XaConstant.Code.error+":图片上传失败");
			return xr;
		} catch (Exception e) {
			xr.setCode(0);
			xr.setObject(XaConstant.Code.error+":图片上传失败");
			return xr;
		}
	}*/
	
	
	@ApiOperation(value="图片/视频上传——web端和移动端使用",notes="图片/视频上传——web端和移动端使用,返回上传图片的地址、宽、高")
	@ResponseBody
	@RequestMapping(value="photoUpload",method=RequestMethod.POST)
	public XaResult<Map<String, Object>> photoUpload(
		@ApiParam("上传的图片,字段名:photoFile,必填,jpg/png图片或者mp4或者mov视频") @RequestParam(value = "photoFile") MultipartFile photoFile, 
		HttpServletRequest request
	) throws BusinessException{
		XaResult<Map<String, Object>> result = new XaResult<Map<String, Object>>();
		String root=request.getSession().getServletContext().getRealPath("/");
		String picturePath = "/upload/eventReview";
		String ext =photoFile.getOriginalFilename().substring(photoFile.getOriginalFilename().lastIndexOf("."));
		String newName = new Date().getTime()+ext;
		File filedict = new File(root+picturePath);
		if(!filedict.exists()){
			filedict.mkdir();
		}
		
		//限制上传文件的大小
		if(photoFile.getSize() > 1024 * 1024 * 30){
			result.setCode(0);
			result.setMessage("图片/视频大小不能超过30M");
			return result;
		}
		File targetFile=new File(root+picturePath+File.separator+newName);
		try {
			if(StringUtils.equalsIgnoreCase(ext, ".jpg") || StringUtils.equalsIgnoreCase(ext, ".png") || StringUtils.equalsIgnoreCase(ext, ".mp4")|| StringUtils.equalsIgnoreCase(ext, ".mov")|| StringUtils.equalsIgnoreCase(ext, ".jpeg")){
				photoFile.transferTo(targetFile);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("picturePath",picturePath+"/"+newName);
				result.setObject(map);
				return result;
				/*BufferedImage bimg = ImageIO.read(targetFile);
				int width = bimg.getWidth();
				int height = bimg.getHeight();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("pictureHeight",height);
				map.put("pictureWidth",width);
				map.put("picturePath",picturePath+"/"+newName);
				result.setObject(map);
				return result;*/
			}
			else{
				result.error("上传文件类型不允许,请上传jpg/png/jpeg图片");
				return result;
				//throw new BusinessException("上传文件类型不允许,请上传jpg/png/jpeg图片");
			}
		} catch (IllegalStateException e) {
			throw new BusinessException("头像上传失败");
		} catch (IOException e) {
			throw new BusinessException("头像上传失败");
		} catch (Exception e) {
			throw new BusinessException("头像上传失败");
		}
		
	}
}

