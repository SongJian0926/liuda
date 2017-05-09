package com.web.liuda.business.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.entity.Shop;
import com.web.liuda.business.entity.Standard;
import com.web.liuda.business.service.ShopService;
import com.web.liuda.remote.vo.ShopVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.WebUitl;
import com.web.webstart.base.util.XaResult;

/**
 * @Title: ShopController.java
 * @Package com.web.webstart.business.controller
 * @Description: 商品管理
 * @author eason.zt
 * @date 2015年12月30日
 * @version V1.0
 */
@Controller
@RequestMapping("/cms/shop")
public class ShopController extends BaseController{

	@Autowired
	private ShopService shopService;
	
	@ResponseBody
	@RequestMapping(value="findShopsNEStatusPage",method=RequestMethod.POST)
	public XaResult<Page<Shop>> findShopsNEStatusPage(
			@RequestParam(defaultValue = "0") Integer nextPage,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "3") Integer status,
			@RequestParam(defaultValue = "[{property:'createTime',direction:'DESC'}]") String sortData,
			//过滤字段,字段名:jsonFilter,选填,默认:{},示例:{'search_EQ_field1':'value1','search_EQ_field2':'value2'},字段名称拼接规则search_为固定查询标识,EQ为等于,filed为字段名
			//EQ等于, IN包含, ISNULL空, LIKE, GT大于, LT小于, GTE大于等于, LTE小于等于, NE不等于,LIKEIGNORE非like
			@RequestParam(defaultValue = "{}") String jsonFilter
	)throws BusinessException{
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize,sortData);
		Map<String,Object> filterParams =WebUitl.getParametersStartingWith(jsonFilter, "search_");
		return shopService.findListNEStatusByFilter(status, filterParams, pageable);
	}
	
	/**
	 * 商品详情
	 * @param modelId
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping(value="findShopById",method=RequestMethod.POST)
	public XaResult<ShopVo> findShopById(
		@RequestParam Long modelId
	)throws BusinessException{
		return shopService.findShopById(modelId);
	}
	
	/**
	 * 保存商品
	 * @param model
	 * @param imgUrls
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping(value="saveShop",method=RequestMethod.POST)
	public XaResult<Shop> saveShop(
		Shop model,
		@RequestParam(value="imgurls",required=false) String imgurls,
		@RequestParam(value="desimgs",required=false) String desimgs,
		String standard
	)throws BusinessException{
		XaResult<Shop> xr = new XaResult<Shop>();
		List<Standard> standards = null;
		try{
			standards = JSON.parseArray(standard, Standard.class);
		}catch(Exception e){
			xr.error("json格式转化异常");
			return xr;
		}
		return shopService.saveOrUpdate(model, imgurls, desimgs, standards);
	}
	
	@ResponseBody
	@RequestMapping(value="operateShopByIds",method=RequestMethod.POST)
	public  XaResult<Shop> deleteShopByIds(
			HttpServletRequest request,
			@RequestParam String modelIds,
			@RequestParam(defaultValue = "3") Integer status)throws BusinessException{
		return shopService.multiOperate(modelIds, status);
	}
	
	/**
	 * @Title: upload
	 * @Description: 图片上传
	 * @param photoFile
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "photoUpload", method = RequestMethod.POST)
	public XaResult<String> photoUpload(
			@RequestParam(value = "photoFile", required = false) MultipartFile photoFile,
			HttpServletRequest request) {
		XaResult<String> xr = new XaResult<String>();
		String root = request.getSession().getServletContext().getRealPath("/");
		String picturePath = "/upload/shop";
		String ext = photoFile.getOriginalFilename().substring(
				photoFile.getOriginalFilename().lastIndexOf("."));
		String newName = new Date().getTime() + ext;
		File filedict = new File(root + picturePath);
		if (!filedict.exists()) {
			filedict.mkdir();
		}
		File targetFile = new File(root + picturePath + File.separator
				+ newName);
		try {
			if (StringUtils.equalsIgnoreCase(ext, ".jpg")
					|| StringUtils.equalsIgnoreCase(ext, ".png")) {
				photoFile.transferTo(targetFile);
				BufferedImage bimg = ImageIO.read(targetFile);
				int width = bimg.getWidth();
				int height = bimg.getHeight();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("pictureHeight", height);
				map.put("pictureWidth", width);
				xr.setCode(1);
				xr.setObject(XaConstant.Code.success + ":" + picturePath + "/"
						+ newName + ":" + height + ":" + width);
				return xr;
			} else {
				xr.setCode(0);
				xr.setObject(XaConstant.Code.error + ":上传文件类型不允许,请上传jpg/png图片");
				return xr;
			}
		} catch (IllegalStateException e) {
			xr.setCode(0);
			xr.setObject(XaConstant.Code.error + ":图片上传失败");
			return xr;
		} catch (IOException e) {
			xr.setCode(0);
			xr.setObject(XaConstant.Code.error + ":图片上传失败");
			return xr;
		} catch (Exception e) {
			xr.setCode(0);
			xr.setObject(XaConstant.Code.error + ":图片上传失败");
			return xr;
		}
	}
}
