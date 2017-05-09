package com.web.liuda.remote.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.MatchOrderTemp;
import com.web.liuda.remote.service.ApiMatchOrderTempService;
import com.web.liuda.remote.vo.MatchOrderTempVo;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.Validator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Title: ApiMatchOrderTempController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 赛事报名临时表信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "MatchOrderTemp", description = "活动/赛事报名临时表接口(二期)", position = 10)
@Controller
@RequestMapping("/api/matchOrderTemp")
public class ApiMatchOrderTempController extends BaseController {

	@Autowired
	private ApiMatchOrderTempService matchOrderTempService;
	
	/**
	 * @Title: findMatchOrderTempList
	 * @Description: 活动、赛事报名临时表信息
	 * @return    
	 */
	@ApiOperation(value="查询活动、赛事报名临时人员信息",notes="查询活动、赛事报名临时人员信息,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findMatchOrderTempList",method=RequestMethod.POST)
	public XaResult<List<MatchOrderTempVo>> findMatchOrderTempList(
		@ApiParam("用户Id,字段名:userId") @RequestParam(value="userId") Long userId,
		@ApiParam("活动Id/赛事Id,字段名:clubEventId") @RequestParam(value="clubEventId") Long clubEventId,
		@ApiParam("报名类型,字段名:type，1：赛事报名，2：俱乐部活动报名") @RequestParam(value = "type") Integer type
			
	) throws BusinessException{
		XaResult<List<MatchOrderTempVo>> xr=new XaResult<List<MatchOrderTempVo>>();
		if(XaUtil.isEmpty(clubEventId)){
			xr.error("活动Id不能为空！");
			return xr;
		}else if(clubEventId<1){
			xr.error("活动Id不能小于1！");
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
			xr.error("报名类型不能为空！");
			return xr;
		}else if(type!=1&&type!=2){
			xr.error("报名类型只能是1或者2！");
			return xr;
			
		}
		/*Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");*/
	//	Map<String , Object> filterParams =  WebUitl.getParametersStartingWith("{}", "search_");
		return matchOrderTempService.findMacthOrderTempList(userId,clubEventId,type);
	}
	
	/**
	 * @Title: findMatchOrderTempById
	 * @Description: 根据ID查找单条实体
	 * @param modelId
	 * @return    
	 */
	@ApiOperation(value="根据tId查询赛事报名临时表",notes="查询赛事报名临时表详细信息,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findMatchOrderTempById",method=RequestMethod.POST)
	public XaResult<MatchOrderTempVo> findMatchOrderTempById(
		@ApiParam("编号,字段名:modelId,必填") @RequestParam(value = "modelId") Long modelId
	) throws BusinessException{
		return matchOrderTempService.findOne(modelId);
	}
	
	/**
	 * @Title: save
	 * @Description: 新增报名人员（活动、赛事）
	 * @param userId
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="新增报名人员（活动、赛事）",notes="新增报名人员,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="saveMatchOrderTemp",method=RequestMethod.POST)
	public XaResult<MatchOrderTempVo> saveMatchOrderTemp(
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId,
		@ApiParam("活动Id/赛事Id,字段名:matchId") @RequestParam(value = "matchId") Long matchId,
		@ApiParam("真实姓名,字段名:realName") @RequestParam(value = "realName") String realName,
		@ApiParam("手机号,字段名:mobile") @RequestParam(value = "mobile") String mobile,
		@ApiParam("性别,字段名:sex,0:男，1:女") @RequestParam(value = "sex") Integer sex,
		@ApiParam("血型,字段名:bloodType") @RequestParam(required=false,value = "bloodType") String bloodType,
		@ApiParam("身份证号 ,字段名:idCard") @RequestParam(required=false,value = "idCard") String idCard,
		@ApiParam("驾驶证档案号,字段名:profileNum") @RequestParam(required=false,value = "profileNum") String profileNum,
		@ApiParam("参赛组别,字段名:matchGroup") @RequestParam(required=false,value = "matchGroup") String matchGroup,
		@ApiParam("参赛车型,字段名:carModel") @RequestParam(required=false,value = "carModel") String carModel,
		@ApiParam("所属车队,字段名:carTeam") @RequestParam(required=false,value = "carTeam") String carTeam,
		@ApiParam("紧急联系人手机号 ,字段名:emeMobile") @RequestParam(required=false,value = "emeMobile") String emeMobile,
		@ApiParam("参赛经历,字段名:experience") @RequestParam(required=false,value = "experience") String experience,
		@ApiParam("荣誉,字段名:honor") @RequestParam(required=false,value = "honor") String honor,
		@ApiParam("车牌号码,字段名:carNumber") @RequestParam(required=false,value = "carNumber") String carNumber,
		@ApiParam("年龄,字段名:age") @RequestParam(required=false,value = "age") Integer age,
		@ApiParam("是否同意协议,字段名:isAgree，1：是，0：否") @RequestParam(value = "isAgree") Integer isAgree,
		@ApiParam("报名类型,字段名:type，1：赛事报名，2：俱乐部活动报名") @RequestParam(value = "type") Integer type,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<MatchOrderTempVo> xr=new XaResult<MatchOrderTempVo>();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}else if(userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(matchId)){
			xr.error("matchId不能为空！");
			return xr;
		}else if(matchId<1){
			xr.error("matchId不能小于1！");
			return xr;
		}
		
		if(isAgree!=JConstant.BooleanStatus.TRUE){
			xr.error("不同意报名协议不能报名！");
			return xr;
		}
		if(XaUtil.isEmpty(type)){
			xr.error("报名类型不能为空！");
			return xr;
		}else if(type!=1&&type!=2){
			xr.error("报名类型只能是1或者2！");
			return xr;
			
		}
		if(XaUtil.isEmpty(realName)){
			xr.error("真实姓名不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(mobile)){
			xr.error("手机号不能为空！");
			return xr;
		}
		if(!Validator.isMobile(mobile)){
			xr.error("手机号格式错误！");
			return xr;
		}
		if(XaUtil.isEmpty(sex)){
			xr.error("性别不能为空！");
			return xr;
		}
		if(sex!=0&&sex!=1){
			xr.error("性别只能是0或者1！");
			return xr;
		}
		if(XaUtil.isNotEmpty(emeMobile)&&!Validator.isMobile(emeMobile)){
			xr.error("紧急联系人手机号格式错误！");
			return xr;
		}
		if(XaUtil.isNotEmpty(idCard)&&!Validator.isIDCard(idCard)){
			xr.error("身份证号格式错误！");
			return xr;
		}
		if(XaUtil.isNotEmpty(age)&&age<0&&!Validator.isNumber(age.toString())){
			xr.error("年龄只能是大于等于0的整数！");
			return xr;
		}
		if(type==JConstant.MatchOrderType.MATCHORDER){
			if(XaUtil.isEmpty(bloodType)){
				xr.error("血型不能为空！");
				return xr;
			}
			if(XaUtil.isEmpty(idCard)){
				xr.error("身份证号不能为空！");
				return xr;
			}
			if(XaUtil.isEmpty(profileNum)){
				xr.error("驾驶证档案号不能为空！");
				return xr;
			}
			if(profileNum.length()>50){
				xr.error("驾驶证档案号过长！");
				return xr;
			}
			if(matchGroup.length()>50){
				xr.error("参赛组别过长！");
				return xr;
			}
			if(XaUtil.isEmpty(carModel)){
				xr.error("参赛车型不能为空！");
				return xr;
			}
			if(carModel.length()>50){
				xr.error("参赛车型过长！");
				return xr;
			}
			if(XaUtil.isEmpty(carTeam)){
				xr.error("参赛车队不能为空！");
				return xr;
			}
			if(carTeam.length()>50){
				xr.error("参赛车队名过长！");
				return xr;
			}
			if(XaUtil.isEmpty(emeMobile)){
				xr.error("紧急联系人不能为空！");
				return xr;
			}
			if(!Validator.isMobile(emeMobile)){
				xr.error("紧急联系人手机号错误");
				return xr;
			}
		}
		
		MatchOrderTemp model = new MatchOrderTemp();
		model.setUserId(userId);
		model.setMatchId(matchId);
		model.setRealName(realName);
		model.setMobile(mobile);
		model.setSex(sex);
		model.setBloodType(bloodType);
		model.setIdCard(idCard);
		model.setProfileNum(profileNum);
		model.setMatchGroup(matchGroup);
		model.setCarModel(carModel);
		model.setCarTeam(carTeam);
		model.setEmeMobile(emeMobile);
		model.setExperience(experience);
		model.setHonor(honor);
		model.setCarNumber(carNumber);
		model.setAge(age);
		model.setType(type);
		return matchOrderTempService.createModel(model);
	}
	
	

	/**
	 * @Title: 
	 * @Description: 修改报名人员信息
	 * @param userId
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="修改报名人员信息",notes="修改报名人员信息,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="updateMatchOrderTemp",method=RequestMethod.POST)
	public XaResult<MatchOrderTempVo> updateMatchOrderTemp(
		@ApiParam("Id,字段名:id") @RequestParam(value = "id") Long id,
		@ApiParam("真实姓名,字段名:realName") @RequestParam(required=false,value = "realName") String realName,
		@ApiParam("手机号,字段名:mobile") @RequestParam(required=false,value = "mobile") String mobile,
		@ApiParam("性别,字段名:sex") @RequestParam(required=false,value = "sex") Integer sex,
		@ApiParam("血型,字段名:bloodType") @RequestParam(required=false,value = "bloodType") String bloodType,
		@ApiParam("身份证号 ,字段名:idCard") @RequestParam(required=false,value = "idCard") String idCard,
		@ApiParam("驾驶证档案号,字段名:profileNum") @RequestParam(required=false,value = "profileNum") String profileNum,
		@ApiParam("参赛组别,字段名:matchGroup") @RequestParam(required=false,value = "matchGroup") String matchGroup,
		@ApiParam("参赛车型,字段名:carModel") @RequestParam(required=false,value = "carModel") String carModel,
		@ApiParam("所属车队,字段名:carTeam") @RequestParam(required=false,value = "carTeam") String carTeam,
		@ApiParam("紧急联系人手机号 ,字段名:emeMobile") @RequestParam(required=false,value = "emeMobile") String emeMobile,
		@ApiParam("参赛经历,字段名:experience") @RequestParam(required=false,value = "experience") String experience,
		@ApiParam("荣誉,字段名:honor") @RequestParam(required=false,value = "honor") String honor,
		@ApiParam("车牌号码,字段名:carNumber") @RequestParam(required=false,value = "carNumber") String carNumber,
		@ApiParam("年龄,字段名:age") @RequestParam(required=false,value = "age") Integer age,
	HttpServletRequest request
	) throws BusinessException{
		MatchOrderTemp model = new MatchOrderTemp();
		model.setId(id);
		model.setRealName(realName);
		model.setMobile(mobile);
		model.setSex(sex);
		model.setBloodType(bloodType);
		model.setIdCard(idCard);
		model.setProfileNum(profileNum);
		model.setMatchGroup(matchGroup);
		model.setCarModel(carModel);
		model.setCarTeam(carTeam);
		model.setEmeMobile(emeMobile);
		model.setExperience(experience);
		model.setHonor(honor);
		model.setCarNumber(carNumber);
		model.setAge(age);
		return matchOrderTempService.updateModel(model);
	}
	
	/**
	 * @Title: operateMatchOrderTempById
	 * @Description: 删除报名人员
	 * @param modelId
	 * @param status 		操作类型:-1锁定,0无效,1正常,2发布,3删除,默认删除操作
	 * @return    
	 */
	@ApiOperation(value="删除报名人员",notes="删除报名人员,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="operateMatchOrderTempById",method=RequestMethod.POST)
	public XaResult<MatchOrderTempVo> operateMatchOrderTempById(
		HttpServletRequest request,
		@ApiParam("编号,字段名:modelIds,必填") @RequestParam(value = "modelId") String modelIds,
		@ApiParam("操作类型,字段名:status,3删除") @RequestParam(value="status") Integer status
	) throws BusinessException{
		
		return matchOrderTempService.multiOperate(modelIds,status);
	}
	
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
		String picturePath = "/upload/matchOrderTemp";
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

