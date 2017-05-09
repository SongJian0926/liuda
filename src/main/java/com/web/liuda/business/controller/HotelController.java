package com.web.liuda.business.controller;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.Hotel;
import com.web.liuda.business.service.HotelService;
import com.web.liuda.business.service.ImagesService;
import com.web.liuda.remote.vo.BusinessInfoVo;
import com.web.liuda.remote.vo.HotelVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.HttpURLConnectionUtil;
import com.web.webstart.base.util.WebUitl;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Title: HotelController.java
 * @Package com.web.webstart.business.controller
 * @Description: 酒店控制器
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Controller
@RequestMapping("/cms/hotel")
public class HotelController extends BaseController {

	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private ImagesService imagesService;
	
	/**
	 * @Title: findHotelNEStatusPage
	 * @Description: 分页查询Hotel	 * @param nextPage
	 * @param pageSize
	 * @param status		//默认查询非删除3的数据,具体类型参照XaConstant.Status
	 * @param sortDate
	 * @param jsonFilter
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findHotelNEStatusPage",method=RequestMethod.POST)
	public XaResult<Page<Hotel>> findHotelNEStatusPage(
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
		return hotelService.findListNEStatusByFilter(status, filterParams, pageable);
	}
	
	/**
	 * @Title: findHotelEQStatusPage
	 * @Description: (预留)分页查询Hotel	 * @param nextPage
	 * @param pageSize
	 * @param status		//默认查询正常状态1的数据,具体类型参照XaConstant.Status
	 * @param sortDate
	 * @param jsonFilter
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findHotelEQStatusPage",method=RequestMethod.POST)
	public XaResult<Page<Hotel>> findHotelEQStatusPage(
		@RequestParam(defaultValue = "0") Integer nextPage,
		@RequestParam(defaultValue = "10") Integer pageSize,
		@RequestParam(defaultValue = "1") Integer status,
		@RequestParam(defaultValue = "[{property:'createTime',direction:'DESC'}]") String sortData,
		@RequestParam(defaultValue = "{}") String jsonFilter
	) throws BusinessException{
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, sortData);
		Map<String,Object> filterParams =  WebUitl.getParametersStartingWith(jsonFilter, "search_");
		return hotelService.findListEQStatusByFilter(status, filterParams, pageable);
	}
	
	/**
	 * @Title: findHotelById
	 * @Description: 根据ID查找单条实体
	 * @param modelId
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findHotelById",method=RequestMethod.POST)
	public XaResult<Hotel> findHotelById(
		@RequestParam Long modelId
	) throws BusinessException{
		return hotelService.findOne(modelId);
	}
	/**
	 * @Title: findHotelById
	 * @Description: 根据ID查找单条实体同时找到img表中的图片
	 * @param modelId
	 * @return    HotelVo
	 */
	@ResponseBody
	@RequestMapping(value="newFindHotelById",method=RequestMethod.POST)
	public XaResult<HotelVo> newFindHotelById(
		@RequestParam Long modelId
	) throws BusinessException{
		return hotelService.newFindOne(modelId);
	}
	
	/**
	 * @Title: findHotelByBusinessId
	 * @Description: 根据商户ID查找实体
	 * @param modelId
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findHotelByBusinessId",method=RequestMethod.POST)
	public XaResult<Hotel> findHotelByBusinessId(
		@RequestParam Long model
	) throws BusinessException{
		return hotelService.findHotelByBusinessId(model);
	}
	
	/**
	 * @Title: findOneById
	 * @Description: 根据ID查找单条实体
	 * @param modelId
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findOneById",method=RequestMethod.POST)
	public XaResult<HotelVo> findOneById(
		@RequestParam Long modelId
	) throws BusinessException{
		return hotelService.findHotelById(modelId);
	}
	/**
	 * @Title: newSaveModel
	 * @Description: 修改实体
	 * @param model
	 * @return  返回 Boolean
	 */
	@ResponseBody
	@RequestMapping(value="updataHotel",method=RequestMethod.POST)
	public XaResult<BusinessInfoVo> updataHotel(
			@RequestParam String imgUrl,
			@RequestParam Long id,
			@RequestParam String code,
			@RequestParam String model,
			@RequestParam Long businessUserId,
			HttpServletRequest request
 	) throws BusinessException{
		XaResult<BusinessInfoVo> xr=new XaResult<BusinessInfoVo>();
	    String[] arr=imgUrl.split(",");
	    if(arr.length<1){
	    	xr.error("至少上传一张图片");
	    	return xr;
	    }
	    Hotel hotel=formetModel(model, businessUserId,new Hotel(),arr[0]);
	    if(XaUtil.isNotEmpty(HttpURLConnectionUtil.getLocation(hotel.getAddress()).getResult())){
			imagesService.deleteImagesByObjectId(id, JConstant.ImageType.HOTEL);
	    	xr =hotelService.updataHotel(id,hotel,arr);
		}else{
			xr.error("请输入正确的详细地址");
			return xr;
		}
		return xr;
	}
	/**
	 * @Title: newSaveModel
	 * @Description: 保存实体数据
	 * @param model
	 * @return  返回 BusinessInfoVo
	 */
	@ResponseBody
	@RequestMapping(value="newSaveModel",method=RequestMethod.POST)
	public XaResult<BusinessInfoVo> newSaveModel(
			@RequestParam String imgUrl,
			@RequestParam String mobile,
			@RequestParam String code,
			@RequestParam String model,
			@RequestParam Long businessUserId,
			HttpServletRequest request
 	) throws BusinessException{
		 XaResult<BusinessInfoVo> xr=new  XaResult<BusinessInfoVo>();
		 String[] arr=imgUrl.split(",");
		 Hotel hotel=formetModel(model, businessUserId,new Hotel(),arr[0]);
		 if(arr.length<1){
		    	xr.error("至少上传一张图片");
		    	return xr;
		    }
		//判断验证码是否正确
		 if (BusinessInfoController.checkCode(request, mobile, code)!="ok" || !BusinessInfoController.checkCode(request, mobile, code).equals("ok")) {
				xr.error(BusinessInfoController.checkCode(request, mobile, code));
				return xr;
		}
		if(XaUtil.isNotEmpty(HttpURLConnectionUtil.getLocation(hotel.getAddress()).getResult())){
		    xr =hotelService.newSaveModel(hotel,arr);
		}else{
			xr.error("请输入正确的详细地址");
			return xr;
		}
		return  xr;
	}
	/**
	 * @Title: newSaveHotel
	 * @Description: 保存实体数据
	 * @param model
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="newSaveHotel",method=RequestMethod.POST)
	public XaResult<Hotel> newSaveHotel(
		Hotel model,String photo1,String photo2,String photo3,String photo4
	) throws BusinessException{
		XaResult<Hotel> xr=new XaResult<Hotel>();
		ArrayList<String> list = new ArrayList<String>();
		if(photo1!=""&&photo1!=null){
			list.add(photo1);
		}
		if(photo2!=""&&photo2!=null){
			list.add(photo2);
		}
		if(photo3!=""&&photo3!=null){
			list.add(photo3);
		}
		if(photo4!=""&&photo4!=null){
			list.add(photo4);
		}
		if(list.size()<1){
			xr.error("轮播图至少上传一张");
			return xr;
		}
		if(XaUtil.isEmpty(model.getAddress())){
			xr.error("酒店地址不能为空!");
			return xr;
		}
		return hotelService.newSaveOrUpdate(model,list);
	}
	
	/**
	 * @Title: multiOperateHotelByIds
	 * @Description: 批量操作多个实体状态,根据status进行操作
	 * @param modelIds    	多个实体id,中间用逗号隔开
	 * @param status 		操作类型,status类型见XaConstant.Status,默认删除操作
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="operateHotelByIds",method=RequestMethod.POST)
	public XaResult<Hotel> operateHotelByIds(
		HttpServletRequest request,
		@RequestParam String modelIds,
		@RequestParam(defaultValue = "3") Integer status
	) throws BusinessException{
		return hotelService.multiOperate(modelIds,status);
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
		String picturePath = "/upload/hotel";
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
	public Hotel formetModel(String model,Long businessUserId,Hotel hotel,String img){
		String[] arr=model.split("&");
			hotel.setHotelName(((arr[0].split("="))[1]).replace("+", " "));
			hotel.setMobile((arr[1].split("="))[1]);
			hotel.setTelphone((arr[3].split("="))[1]);
			hotel.setIntroduce(((arr[4].split("="))[1]).replace("+", " "));
			hotel.setHotelType(((arr[5].split("="))[1]).replace("+", " "));
			hotel.setPrompt(((arr[6].split("="))[1]).replace("+", " "));
			hotel.setPolicy(((arr[7].split("="))[1]).replace("+", " "));
			hotel.setAddress(((arr[8].split("="))[1]).replace("+", ""));
			hotel.setPicurl(img);
			hotel.setBusinessUserId(businessUserId);
		return hotel;
	}
	
}

