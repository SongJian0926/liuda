package com.web.liuda.remote.controller;
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

import com.web.liuda.business.entity.EventMsg;
import com.web.liuda.remote.service.ApiEventMsgService;
import com.web.liuda.remote.vo.EventMsgVo;
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
 * @Title: ApiEventMsgController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 俱乐部留言表信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "EventMsg", description = "俱乐部留言表接口(二期)", position = 10)
@Controller
@RequestMapping("/api/eventMsg")
public class ApiEventMsgController extends BaseController {

	@Autowired
	private ApiEventMsgService eventMsgService;
	
	/**
	 * @Title: findEventMsgList
	 * @Description: 俱乐部活动留言列表
	 * @return    
	 */
	@ApiOperation(value="俱乐部活动留言列表",notes="俱乐部活动留言列表,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findEventMsgList",method=RequestMethod.POST)
	public XaResult<Page<EventMsgVo>> findEventMsgList(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		@ApiParam("活动Id,字段名:clubEventId") @RequestParam(value = "clubEventId") Long clubEventId
	) throws BusinessException{
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");
		Map<String , Object> filterParams =  WebUitl.getParametersStartingWith("{'search_EQ_clubEventId':'"+clubEventId+"'}", "search_");
		return eventMsgService.findEventMsgList(XaConstant.Status.valid, filterParams, pageable);
	}
	
	/**
	 * @Title: findEventMsgById
	 * @Description: 根据ID查找单条实体
	 * @param modelId
	 * @return    
	 */
	/*@ApiOperation(value="根据tId查询俱乐部留言表",notes="查询俱乐部留言表详细信息,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findEventMsgById",method=RequestMethod.POST)
	public XaResult<EventMsgVo> findEventMsgById(
		@ApiParam("编号,字段名:modelId,必填") @RequestParam(value = "modelId") Long modelId
	) throws BusinessException{
		return eventMsgService.findOne(modelId);
	}
	*/
	/**
	 * @Title: save
	 * @Description: 留言、回复留言
	 * @param userId
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="留言、回复留言",notes="留言、回复留言,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="saveEventMsg",method=RequestMethod.POST)
	public XaResult<EventMsgVo> saveEventMsg(
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId,
		@ApiParam("评论或者回复内容,字段名:content") @RequestParam(value = "content") String content,
		@ApiParam("活动Id,字段名:eventId") @RequestParam(value = "eventId") Long eventId,
		@ApiParam("留言Id,字段名:event_msg_id，回复留言时必传") @RequestParam(required=false,value = "event_msg_id") Long event_msg_id,
		@ApiParam("图片,字段名:mediaPath，多张图片以逗号分隔开") @RequestParam(required=false,value = "mediaPath") String mediaPath,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<EventMsgVo> xr=new XaResult<EventMsgVo>();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}else if(userId<1){
			xr.error("用户Id不能小于1！");
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
		EventMsg model = new EventMsg();
		model.setUserId(userId);
		model.setContent(content);
		model.setEventId(eventId);
		model.setEvent_msg_id(event_msg_id);
		model.setMediaPath(mediaPath);
		return eventMsgService.createModel(model);
	}
	
	/**
	 * @Title: 
	 * @Description: 修改一条实体
	 * @param userId
	 * @param model
	 * @return    
	 */
	/*@ApiOperation(value="修改俱乐部留言表",notes="修改俱乐部留言表,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="updateEventMsg",method=RequestMethod.POST)
	public XaResult<EventMsgVo> updateEventMsg(
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId,
		@ApiParam("评论或者回复内容,字段名:content") @RequestParam(value = "content") String content,
		@ApiParam("活动Id,字段名:eventId") @RequestParam(value = "eventId") Long eventId,
		@ApiParam("留言表Id,字段名:event_msg_id") @RequestParam(value = "event_msg_id") Long event_msg_id,
		@ApiParam("图片,字段名:mediaPath") @RequestParam(value = "mediaPath") String mediaPath,
		@ApiParam("版本编号,字段名:tId") @RequestParam(value = "tId") Long tId,
	HttpServletRequest request
	) throws BusinessException{
		EventMsg model = new EventMsg();
		model.setUserId(userId);
		model.setContent(content);
		model.setEventId(eventId);
		model.setEvent_msg_id(event_msg_id);
		model.setMediaPath(mediaPath);
		model.setId(tId);
		return eventMsgService.updateModel(model);
	}*/
	
	/**
	 * @Title: operateEventMsgById
	 * @Description: 操作一个实体状态,根据status进行操作
	 * @param modelId
	 * @param status 		操作类型:-1锁定,0无效,1正常,2发布,3删除,默认删除操作
	 * @return    
	 */
	/*@ApiOperation(value="根据ID修改俱乐部留言表状态",notes="修改俱乐部留言表状态,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="operateEventMsgById",method=RequestMethod.POST)
	public XaResult<EventMsgVo> operateEventMsgById(
		HttpServletRequest request,
		@ApiParam("编号,字段名:modelIds,必填") @RequestParam(value = "modelId") String modelIds,
		@ApiParam("操作类型,字段名:status,-1锁定,0无效,1正常,2发布,3删除,选填,默认删除操作") @RequestParam(defaultValue = "3") Integer status
	) throws BusinessException{
		return eventMsgService.multiOperate(modelIds,status);
	}*/
	
	/**
	 * @Title: upload
	 * @Description: 图片上传
	 * @param photoFile
	 * @param request
	 * @return    
	 */
	/*@ApiOperation(value="图片上传",notes="异步图片上传,返回上传图片的地址、宽、高")
	@ResponseBody
	@RequestMapping(value="photoUpload",method=RequestMethod.POST)
	public XaResult<Map<String, Object>> photoUpload(
		@ApiParam("上传的图片,字段名:photoFile,必填") @RequestParam(value = "photoFile") MultipartFile photoFile, 
		HttpServletRequest request
	) throws BusinessException{
		XaResult<Map<String, Object>> result = new XaResult<Map<String, Object>>();
		String root=request.getSession().getServletContext().getRealPath("/");
		String picturePath = "/upload/eventMsg";
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

