package com.web.liuda.remote.controller;
import java.util.List;
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

import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.Club;
import com.web.liuda.remote.service.ApiClubService;
import com.web.liuda.remote.vo.ClubVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.HttpURLConnectionUtil;
import com.web.webstart.base.util.Validator;
import com.web.webstart.base.util.WebUitl;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Title: ApiClubController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 俱乐部信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "Club", description = "俱乐部接口(二期)", position = 10)
@Controller
@RequestMapping("/api/club")
public class ApiClubController extends BaseController {

	@Autowired
	private ApiClubService clubService;
	
	
	/**
	 * @Title: findClubList
	 * @Description: 俱乐部列表
	 * @return    
	 */
	@ApiOperation(value="俱乐部列表",notes="俱乐部列表,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findClubList",method=RequestMethod.POST)
	public XaResult<Page<ClubVo>> findClubList(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		@ApiParam("用户所在位置经度,字段名:lng") @RequestParam Double lng,
		@ApiParam("用户所在位置纬度,字段名:lat") @RequestParam Double lat,
		@ApiParam("用户Id,字段名:userId") @RequestParam(required=false) Long userId,
		@ApiParam("俱乐部类型Id,字段名:type") @RequestParam(required=false) Long type,
		@ApiParam("areaCode,字段名:所属区域") @RequestParam(required=false) String areaCode,
		@ApiParam("title,字段名:俱乐部名称") @RequestParam(required=false) String title
	) throws BusinessException{
		XaResult<Page<ClubVo>> xr=new XaResult<Page<ClubVo>> ();
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");
		Map<String , Object> filterParams =null;
		if(XaUtil.isEmpty(lng)){
			xr.error("经度不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(lat)){
			xr.error("纬度不能为空！");
			return xr;
		}
		if(!Validator.isDouble(lng + "")){
			xr.error("用户所在位置经度值不正确");
			return xr;
		}
		if(!Validator.isDouble(lat + "")){
			xr.error("用户所在位置纬度不正确");
			return xr;
		}
		//登录用户
		if(XaUtil.isNotEmpty(userId)){
			if(userId<1){
				xr.error("用户Id不能小于1！");
				return xr;
			}
			filterParams =  WebUitl.getParametersStartingWith("{'search_EQ_userId':'"+userId+"'}", "search_");
			
		}else{
			//游客
			filterParams =  WebUitl.getParametersStartingWith("{}", "search_");	
		}
		return clubService.findClubList(XaConstant.Status.valid, filterParams, type,areaCode,pageable,lng, lat,title);
		
	}
	/**
	 * @Title: findClubList
	 * @Description: 推荐俱乐部列表
	 * @return    
	 */
	@ApiOperation(value="推荐俱乐部列表",notes="推荐俱乐部列表,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findRecomendClubList",method=RequestMethod.POST)
	public XaResult<List<ClubVo>> findRecomendClubList(
		@ApiParam("用户Id,字段名:userId") @RequestParam(required=false) Long userId
	) throws BusinessException{
		XaResult<List<ClubVo>> xr=new XaResult<List<ClubVo>> ();
		Map<String , Object> filterParams = null;
		//登录用户
		if(XaUtil.isNotEmpty(userId)){
			if(userId<1){
				xr.error("用户Id不能小于1！");
				return xr;
			}
			filterParams = WebUitl.getParametersStartingWith("{'search_EQ_userId':'"+userId+"'}", "search_");
		}else{
			filterParams = WebUitl.getParametersStartingWith("{}", "search_");
		}
		return clubService.findRecomendClub(XaConstant.Status.valid, filterParams);
		
	}
	/**
	 * @Title: findClubMemberById
	 * @Description: 个人主页
	 * @param modelId
	 * @return    
	 *//*
	@ApiOperation(value="个人主页",notes="个人主页,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findClubMemberById",method=RequestMethod.POST)
	public XaResult<ClubVo> findClubMemberById(
		@ApiParam("用户Id,字段名:userId,") @RequestParam(required=false) Long userId,
		@ApiParam("编号,字段名:modelId,必填") @RequestParam(value = "modelId") Long modelId
	) throws BusinessException{
		XaResult<ClubVo> xr=new XaResult<ClubVo>();
		if(XaUtil.isEmpty(modelId)){
			xr.error("编号不能为空！");
			return xr;
		}
		if(userId<1){
			xr.error("编号不能小于1！");
			return xr;
		}
		return clubService.findOneDetail(userId,modelId);
	}*/
	/**
	 * @Title: findClubById
	 * @Description: 俱乐部详情
	 * @param modelId
	 * @return    
	 */
	@ApiOperation(value="俱乐部详情",notes="俱乐部详情,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findClubById",method=RequestMethod.POST)
	public XaResult<ClubVo> findClubById(
		@ApiParam("俱乐部Id,字段名:id,必填") @RequestParam(value = "id") Long id,
		@ApiParam("用户Id,字段名:userId") @RequestParam(required=false,value = "userId") Long userId
		
	) throws BusinessException{
		XaResult<ClubVo> xr=new XaResult<ClubVo>();
		if(XaUtil.isEmpty(id)){
			xr.error("俱乐部Id不能为空！");
			return xr;
		}else if(id<1){
			xr.error("俱乐部Id不能小于1！");
			return xr;
		}
		if(XaUtil.isNotEmpty(userId)&&userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		return clubService.findClubById(id,userId);
	}
	/**
	 * @Title: findClubDetail
	 * @Description: 俱乐部详情(web端修改俱乐部使用)
	 * @param modelId
	 * @return    
	 */
	@ApiOperation(value="俱乐部详情(web端修改俱乐部使用)",notes="俱乐部详情(web端修改俱乐部使用),当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findClubDetail",method=RequestMethod.POST)
	public XaResult<ClubVo> findClubDetail(
		@ApiParam("俱乐部Id,字段名:id,必填") @RequestParam(value = "id") Long id,
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId
		
	) throws BusinessException{
		XaResult<ClubVo> xr=new XaResult<ClubVo>();
		if(XaUtil.isEmpty(id)){
			xr.error("俱乐部Id不能为空！");
			return xr;
		}else if(id<1){
			xr.error("俱乐部Id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}else if(userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		return clubService.findOne(id);
	}
	/**
	 * @Title: findClubById
	 * @Description: 俱乐部详情(web端)
	 * @param modelId
	 * @return    
	 */
	@ApiOperation(value="俱乐部详情(web端)——web端使用",notes="俱乐部详情(web端),当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findClubDetailById",method=RequestMethod.POST)
	public XaResult<ClubVo> findClubDetailById(
		@ApiParam("俱乐部Id,字段名:id,必填") @RequestParam(value = "id") Long id,
		@ApiParam("用户Id,字段名:userId,必填") @RequestParam(value = "userId") Long userId
		
	) throws BusinessException{
		XaResult<ClubVo> xr=new XaResult<ClubVo>();
		if(XaUtil.isEmpty(id)){
			xr.error("俱乐部Id不能为空！");
			return xr;
		}else if(id<1){
			xr.error("俱乐部Id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(XaUtil.isNotEmpty(userId)&&userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		return clubService.findClubDetailById(id,userId);
	}
	
	/**
	 * @Title: save
	 * @Description: 创建俱乐部
	 * @param userId
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="创建俱乐部——web端和移动端使用",notes="创建俱乐部,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="saveClub",method=RequestMethod.POST)
	public XaResult<ClubVo> saveClub(
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId,
		@ApiParam("名称,字段名:title") @RequestParam(value = "title") String title,
		@ApiParam("头像,字段名:logo") @RequestParam(value = "logo") String logo,
		@ApiParam("类型,字段名:type") @RequestParam(value = "type") Long type,
		@ApiParam("详细地址,字段名:address") @RequestParam(value = "address") String address,
		@ApiParam("兴趣标签,字段名:interest") @RequestParam(value = "interest") Long interest,
		@ApiParam("俱乐部介绍,字段名:content") @RequestParam(value = "content") String content,
		@ApiParam("图片,字段名:mediaPath,多张图片之间以逗号分隔开") @RequestParam(required=false,value = "mediaPath") String mediaPath,
		@ApiParam("所属区域 ,字段名:areaCode") @RequestParam(value = "areaCode") String areaCode,
		
		HttpServletRequest request
	) throws BusinessException{
		XaResult<ClubVo> xr=new XaResult<ClubVo>();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}else if(userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(interest)){
			xr.error("兴趣标签不能为空！");
			return xr;
		}else if(interest<1){
			xr.error("兴趣标签Id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(type)){
			xr.error("类型不能为空！");
			return xr;
		}else if(type<1){
			xr.error("类型Id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(title)){
			xr.error("标题不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(logo)){
			xr.error("头像不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(address)){
			xr.error("详细地址不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(content)){
			xr.error("俱乐部介绍不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(areaCode)){
			xr.error("所属区域不能为空！");
			return xr;
		}
		Club model = new Club();
		model.setTitle(title);
		model.setLogo(logo);
		model.setType(type);
		address=address.replaceAll(" ", "");
		model.setAddress(address);
		model.setAreaCode(areaCode);
		//根据地址装换为经度和纬度
		model.setLng(HttpURLConnectionUtil.getLocation('"'+model.getAreaCode()+model.getAddress()+'"').getResult().getLocation().getLng());
		model.setLat(HttpURLConnectionUtil.getLocation('"'+model.getAreaCode()+model.getAddress()+'"').getResult().getLocation().getLat());
		model.setContent(content);
		model.setMediaPath(mediaPath);
		model.setApplyStatus(JConstant.ApplyStatus.CHECK);
		
		//等级（预留）
		//model.setLevel(Long.valueOf(1));
		model.setInterest(interest);
		model.setUserId(userId);
		return clubService.createModel(model);
	}
	
	/**
	 * @Title: 
	 * @Description: 修改俱乐部（审核失败的才能修改）
	 * @param userId
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="修改俱乐部（审核失败的才能修改）",notes="修改俱乐部（审核失败的才能修改）,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="updateClub",method=RequestMethod.POST)
	public XaResult<ClubVo> updateClub(
		@ApiParam("id,字段名:俱乐部Id") @RequestParam(value = "id") Long id,
		@ApiParam("名称,字段名:title") @RequestParam(value = "title") String title,
		@ApiParam("头像,字段名:logo") @RequestParam(value = "logo") String logo,
		@ApiParam("类型,字段名:type") @RequestParam(value = "type") Long type,
		@ApiParam("地址,字段名:address") @RequestParam(value = "address") String address,
		@ApiParam("兴趣标签,字段名:interest") @RequestParam(value = "interest") Long interest,
		@ApiParam("简介,字段名:content") @RequestParam(value = "content") String content,
		@ApiParam("图片,字段名:mediaPath") @RequestParam(required=false,value = "mediaPath") String mediaPath,
		@ApiParam("所属区域 ,字段名:areaCode") @RequestParam(value = "areaCode") String areaCode,
	HttpServletRequest request
	) throws BusinessException{
		XaResult<ClubVo> xr=new XaResult<ClubVo>();
		if(XaUtil.isEmpty(id)){
			xr.error("id不能为空！");
			return xr;
		}else if(id<1){
			xr.error("id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(interest)){
			xr.error("兴趣标签不能为空！");
			return xr;
		}else if(interest<1){
			xr.error("兴趣标签Id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(type)){
			xr.error("类型不能为空！");
			return xr;
		}else if(type<1){
			xr.error("类型Id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(title)){
			xr.error("标题不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(logo)){
			xr.error("头像不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(address)){
			xr.error("详细地址不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(content)){
			xr.error("俱乐部介绍不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(areaCode)){
			xr.error("所属区域不能为空！");
			return xr;
		}
		Club model = new Club();
		model.setTitle(title);
		model.setLogo(logo);
		model.setType(type);
		model.setAddress(address);
		model.setAreaCode(areaCode);
		//根据地址装换为经度和纬度
		model.setLng(HttpURLConnectionUtil.getLocation('"'+model.getAreaCode()+model.getAddress()+'"').getResult().getLocation().getLng());
		model.setLat(HttpURLConnectionUtil.getLocation('"'+model.getAreaCode()+model.getAddress()+'"').getResult().getLocation().getLat());
		model.setContent(content);
		model.setMediaPath(mediaPath);
		model.setApplyStatus(JConstant.ApplyStatus.CHECK);
		//等级（预留）
		model.setLevel(Long.valueOf(1));
		model.setInterest(interest);
		model.setId(id);
		return clubService.updateModel(model);
	}
	
	/**
	 * @Title: operateClubById
	 * @Description: 操作一个实体状态,根据status进行操作
	 * @param modelId
	 * @param status 		操作类型:-1锁定,0无效,1正常,2发布,3删除,默认删除操作
	 * @return    
	 */
	/*@ApiOperation(value="根据ID修改俱乐部状态",notes="修改俱乐部状态,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="operateClubById",method=RequestMethod.POST)
	public XaResult<ClubVo> operateClubById(
		HttpServletRequest request,
		@ApiParam("编号,字段名:modelIds,必填") @RequestParam(value = "modelId") String modelIds,
		@ApiParam("操作类型,字段名:status,-1锁定,0无效,1正常,2发布,3删除,选填,默认删除操作") @RequestParam(defaultValue = "3") Integer status
	) throws BusinessException{
		return clubService.multiOperate(modelIds,status);
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
		String picturePath = "/upload/club";
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

