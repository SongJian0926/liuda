package com.web.liuda.remote.controller;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Title: ApiImagesController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 图片信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "Images", description = "图片接口", position = 10)
@Controller
@RequestMapping("/api/images")
public class ApiImagesController extends BaseController {

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
		if(XaUtil.isEmpty(photoFile)){
			result.error("上传的图片不能为空！");
			return result;
		}
		String root=request.getSession().getServletContext().getRealPath("/");
		String picturePath = "/upload/images";
		System.out.println(photoFile.getOriginalFilename());
		String[] imgs= photoFile.getOriginalFilename().split(",");
		String ext="";
		for(String image:imgs){
			 ext =image.substring(image.lastIndexOf("."));
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
			
		}
		//String ext =photoFile.getOriginalFilename().substring(photoFile.getOriginalFilename().lastIndexOf("."));
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
		return result;
	}*/
	@ApiOperation(value="图片上传",notes="异步图片上传,返回上传图片的地址、宽、高")
	@ResponseBody
	@RequestMapping(value="photoUpload",method=RequestMethod.POST)
	public XaResult<Map<String, Object>> photoUpload(
		@ApiParam("上传的图片,字段名:photoFile,必填") @RequestParam(value = "photoFile") MultipartFile photoFile, 
		HttpServletRequest request
	) throws BusinessException{
		XaResult<Map<String, Object>> result = new XaResult<Map<String, Object>>();
		if(XaUtil.isEmpty(photoFile)){
			result.error("上传的图片不能为空！");
			return result;
		}
		String root=request.getSession().getServletContext().getRealPath("/");
		String picturePath = "/upload/images";
		String ext = photoFile.getOriginalFilename().substring(
				photoFile.getOriginalFilename().lastIndexOf("."));
		String newName = new Date().getTime()+ext;
		File filedict = new File(root+picturePath);
		if(!filedict.exists()){
			filedict.mkdir();
		}
		File targetFile=new File(root+picturePath+File.separator+newName);
		try {
			if(StringUtils.equalsIgnoreCase(ext, ".jpg") || StringUtils.equalsIgnoreCase(ext, ".png") || StringUtils.equalsIgnoreCase(ext, ".jpeg")){
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
				result.error("上传文件类型不允许,请上传jpg/png/jpeg图片");
				return result;
				//throw new BusinessException("上传文件类型不允许,请上传jpg/png/jpeg图片");
			}
		} catch (IllegalStateException e) {
			throw new BusinessException("图片上传失败");
		} catch (IOException e) {
			throw new BusinessException("图片上传失败");
		} catch (Exception e) {
			throw new BusinessException("图片上传失败");
		}
	
	}
}

