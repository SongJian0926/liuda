package com.web.liuda.remote.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.remote.service.ApiDictService;
import com.web.liuda.remote.vo.DictVo;
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
 * @Title: ApiDictController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 字典主表信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "Dict", description = "字典主表接口(二期)", position = 10)
@Controller
@RequestMapping("/api/dict")
public class ApiDictController extends BaseController {

	@Autowired
	private ApiDictService dictService;
	
	/**
	 * @Title: findDictList
	 * @Description: 查询字典列表
	 * author：changlu
	 * time:2016-4-8 11:32:00
	 * @return    
	 */
	@ApiOperation(value="查询字典列表",notes="查询字典列表（攻略标签、赛事标签、俱乐部类型、俱乐部等级、俱乐部兴趣标签）,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findDictList",method=RequestMethod.POST)
	public XaResult<List<DictVo>> findDictList(
		@ApiParam("字典类型,字段名:type,1:攻略标签，2:赛事标签，3:俱乐部类型，4:俱乐部等级，5:俱乐部兴趣标签") @RequestParam(value="type") Integer type	
	) throws BusinessException{
		XaResult<List<DictVo>> xr=new XaResult<List<DictVo>>();
		//type不能为空
		if(XaUtil.isEmpty(type)){
			xr.error("字典类型不能为空！");
			return xr;
		}
		//判断type格式是否正确
		if(type!=1&&type!=2&&type!=3&&type!=4&&type!=5){
			xr.error("公告类型只能是1，2，3，4，5！");
			return xr;
		}
		Pageable pageable = WebUitl.buildPageRequest(0, 100000, "[{property:'createTime',direction:'DESC'}]");
		Map<String , Object> filterParams =  WebUitl.getParametersStartingWith("{'search_EQ_type':'"+type+"'}", "search_");
		return dictService.findListEQStatusByFilter(XaConstant.Status.valid, filterParams, pageable);
	}
	
	/**
	 * @Title: findDictById
	 * @Description: 根据ID查找单条实体
	 * @param modelId
	 * @return    
	 *//*
	@ApiOperation(value="根据tId查询字典主表",notes="查询字典主表详细信息,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findDictById",method=RequestMethod.POST)
	public XaResult<DictVo> findDictById(
		@ApiParam("编号,字段名:modelId,必填") @RequestParam(value = "modelId") Long modelId
	) throws BusinessException{
		return dictService.findOne(modelId);
	}
	
	*//**
	 * @Title: save
	 * @Description: 新增一条实体
	 * @param userId
	 * @param model
	 * @return    
	 *//*
	@ApiOperation(value="新增字典主表",notes="供前端前期填充数据测试使用,上线后删除;新增字典主表,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="saveDict",method=RequestMethod.POST)
	public XaResult<DictVo> saveDict(
		@ApiParam("字典名称,字段名:dictName") @RequestParam(value = "dictName") String dictName,
		HttpServletRequest request
	) throws BusinessException{
		Dict model = new Dict();
		model.setDictName(dictName);
		return dictService.createModel(model);
	}
	
	*//**
	 * @Title: 
	 * @Description: 修改一条实体
	 * @param userId
	 * @param model
	 * @return    
	 *//*
	@ApiOperation(value="修改字典主表",notes="修改字典主表,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="updateDict",method=RequestMethod.POST)
	public XaResult<DictVo> updateDict(
		@ApiParam("字典名称,字段名:dictName") @RequestParam(value = "dictName") String dictName,
		@ApiParam("版本编号,字段名:tId") @RequestParam(value = "tId") Long tId,
	HttpServletRequest request
	) throws BusinessException{
		Dict model = new Dict();
		model.setDictName(dictName);
		model.setId(tId);
		return dictService.updateModel(model);
	}
	
	*//**
	 * @Title: operateDictById
	 * @Description: 操作一个实体状态,根据status进行操作
	 * @param modelId
	 * @param status 		操作类型:-1锁定,0无效,1正常,2发布,3删除,默认删除操作
	 * @return    
	 *//*
	@ApiOperation(value="根据ID修改字典主表状态",notes="修改字典主表状态,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="operateDictById",method=RequestMethod.POST)
	public XaResult<DictVo> operateDictById(
		HttpServletRequest request,
		@ApiParam("编号,字段名:modelIds,必填") @RequestParam(value = "modelId") String modelIds,
		@ApiParam("操作类型,字段名:status,-1锁定,0无效,1正常,2发布,3删除,选填,默认删除操作") @RequestParam(defaultValue = "3") Integer status
	) throws BusinessException{
		return dictService.multiOperate(modelIds,status);
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
		String picturePath = "/upload/dict";
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

