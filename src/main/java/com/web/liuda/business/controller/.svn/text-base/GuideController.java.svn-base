package com.web.liuda.business.controller;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
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

import com.web.liuda.business.entity.Guide;
import com.web.liuda.business.service.GuideService;
import com.web.liuda.remote.vo.GuideVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.WebUitl;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Title: GuideController.java
 * @Package com.web.webstart.business.controller
 * @Description: 攻略表控制器
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Controller
@RequestMapping("/cms/guide")
public class GuideController extends BaseController {

	@Autowired
	private GuideService guideService;
	
	/**
	 * @Title: findGuideNEStatusPage
	 * @Description: 分页查询Guide	 * @param nextPage
	 * @param pageSize
	 * @param status		//默认查询非删除3的数据,具体类型参照XaConstant.Status
	 * @param sortDate
	 * @param jsonFilter
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findGuideNEStatusPage",method=RequestMethod.POST)
	public XaResult<Page<Guide>> findGuideNEStatusPage(
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
		return guideService.findListNEStatusByFilter(status, filterParams, pageable);
	}
	/**
	 * @Title: findGuideList
	 * @Description: 攻略列表
	 * @param nextPage
	 * @param pageSize
	 * @param status		//默认查询非删除3的数据,具体类型参照XaConstant.Status
	 * @param sortDate
	 * @param jsonFilter
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findGuideList",method=RequestMethod.POST)
	public XaResult<Page<GuideVo>> findGuideList(
		@RequestParam(defaultValue = "0") Integer nextPage,
		@RequestParam(defaultValue = "10") Integer pageSize,
		@RequestParam(defaultValue = "3") Integer status,
		@RequestParam(required=false,value="id") Long id,
		@RequestParam(defaultValue = "[{property:'createTime',direction:'DESC'}]") String sortData,
		//过滤字段,字段名:jsonFilter,选填,默认:{},示例:{'search_EQ_field1':'value1','search_EQ_field2':'value2'},字段名称拼接规则search_为固定查询标识,EQ为等于,filed为字段名
		//EQ等于, IN包含, ISNULL空, LIKE, GT大于, LT小于, GTE大于等于, LTE小于等于, NE不等于,LIKEIGNORE非like 
		@RequestParam(defaultValue = "{}") String jsonFilter
	) throws BusinessException{
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, sortData);
		Map<String,Object> filterParams =  WebUitl.getParametersStartingWith(jsonFilter, "search_");
		if(XaUtil.isNotEmpty(id)){
			filterParams.put("EQ_id", id);
		}
		return guideService.findGuideList(status, filterParams, pageable);
	}
	/**
	 * @Title: findGuideEQStatusPage
	 * @Description: (预留)分页查询Guide	 * @param nextPage
	 * @param pageSize
	 * @param status		//默认查询正常状态1的数据,具体类型参照XaConstant.Status
	 * @param sortDate
	 * @param jsonFilter
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findGuideEQStatusPage",method=RequestMethod.POST)
	public XaResult<Page<Guide>> findGuideEQStatusPage(
		@RequestParam(defaultValue = "0") Integer nextPage,
		@RequestParam(defaultValue = "10") Integer pageSize,
		@RequestParam(defaultValue = "1") Integer status,
		@RequestParam(defaultValue = "[{property:'createTime',direction:'DESC'}]") String sortData,
		@RequestParam(defaultValue = "{}") String jsonFilter
	) throws BusinessException{
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, sortData);
		Map<String,Object> filterParams =  WebUitl.getParametersStartingWith(jsonFilter, "search_");
		return guideService.findListEQStatusByFilter(status, filterParams, pageable);
	}
	
	/**
	 * @Title: findGuideById
	 * @Description: 根据ID查找单条实体
	 * @param modelId
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findGuideById",method=RequestMethod.POST)
	public XaResult<Guide> findGuideById(
		@RequestParam Long modelId
	) throws BusinessException{
		return guideService.findOne(modelId);
	}
	
	/**
	 * @Title: findGuideById
	 * @Description: 根据ID查找单条实体
	 * @param modelId
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findDetail",method=RequestMethod.POST)
	public XaResult<GuideVo> findDetail(
		@RequestParam Long modelId
	) throws BusinessException{
		return guideService.findDetail(modelId);
	}
	
	/**
	 * @Title: saveModel
	 * @Description: 保存实体数据
	 * @param model
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="saveGuide",method=RequestMethod.POST)
	public XaResult<Guide> saveGuide(
		Guide model
	) throws BusinessException{
		return guideService.saveOrUpdate(model);
	}
	
	/**
	 * @Title: multiOperateGuideByIds
	 * @Description: 批量操作多个实体状态,根据status进行操作
	 * @param modelIds    	多个实体id,中间用逗号隔开
	 * @param status 		操作类型,status类型见XaConstant.Status,默认删除操作
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="operateGuideByIds",method=RequestMethod.POST)
	public XaResult<Guide> operateGuideByIds(
		HttpServletRequest request,
		@RequestParam String modelIds,
		@RequestParam(defaultValue = "3") Integer status
	) throws BusinessException{
		return guideService.multiOperate(modelIds,status);
	}
	
	/**
	 * 申请精品
	 * @param id
	 * @param applyStatus
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="audit",method=RequestMethod.POST)
	public XaResult<Guide> audit(@RequestParam String id,@RequestParam(defaultValue = "3") Integer applyStatus,@RequestParam String applyMemo){
		XaResult<Guide> xr = new XaResult<Guide>();
		try{
			return guideService.audit(Long.valueOf(id), applyStatus, applyMemo);
		}catch(Exception e){
			xr.error(XaConstant.Message.error);
		}
		return xr;
	}
	
	/**
	 * 绑定景点
	 * @param id
	 * @param applyStatus
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="bandTourist",method=RequestMethod.POST)
	public XaResult<Guide> bandTourist(@RequestParam Long id,@RequestParam(defaultValue = "3") Integer objectType,@RequestParam Long objectId){
		XaResult<Guide> xr = new XaResult<Guide>();
		try{
			return guideService.bandTourist(id, objectId, objectType);
		}catch(Exception e){
			xr.error(XaConstant.Message.error);
		}
		return xr;
	}
	
	/**
	 * @Title: upload
	 * @Description: 图片上传
	 * @param photoFile
	 * @param request
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="photoUpload",method=RequestMethod.POST)
	public XaResult<String> photoUpload(
		@RequestParam(value = "photoFile", required = false) MultipartFile photoFile, 
		HttpServletRequest request
	){
		XaResult<String> xr = new XaResult<String>();
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
				xr.setCode(1);
				xr.setObject(XaConstant.Code.success+":"+picturePath+"/"+newName+":"+height+":"+width);
				return xr;
			}
			else{
				xr.setCode(0);
				xr.setObject(XaConstant.Code.error+":上传文件类型不允许,请上传jpg/png图片");
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
	}
	
}

