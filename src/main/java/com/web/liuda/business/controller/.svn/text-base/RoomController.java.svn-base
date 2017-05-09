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

import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.WebUitl;
import com.web.webstart.base.util.XaResult;
import com.web.liuda.business.entity.Room;
import com.web.liuda.business.service.RoomService;
import com.web.liuda.remote.vo.RoomVo;

/**
 * @Title: RoomController.java
 * @Package com.web.webstart.business.controller
 * @Description: 房间控制器
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Controller
@RequestMapping("/cms/room")
public class RoomController extends BaseController {

	@Autowired
	private RoomService roomService;
	
	/**
	 * @Title: findRoomList
	 * @Description: 分页查询Room	 * @param nextPage
	 * @param pageSize
	 * @param sortDate
	 * @param jsonFilter
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findRoomList",method=RequestMethod.POST)
	public XaResult<Page<RoomVo>> findRoomList(
		@RequestParam(defaultValue = "0") Integer nextPage,
		@RequestParam(defaultValue = "10") Integer pageSize,
		@RequestParam(defaultValue = "[{property:'createTime',direction:'DESC'}]") String sortData,
		//过滤字段,字段名:jsonFilter,选填,默认:{},示例:{'search_EQ_field1':'value1','search_EQ_field2':'value2'},字段名称拼接规则search_为固定查询标识,EQ为等于,filed为字段名
		//EQ等于, IN包含, ISNULL空, LIKE, GT大于, LT小于, GTE大于等于, LTE小于等于, NE不等于,LIKEIGNORE非like 
		@RequestParam(defaultValue = "{}") String jsonFilter
	) throws BusinessException{
		Map<String,Object> filterParams =  WebUitl.getParametersStartingWith(jsonFilter, "search_");
		return roomService.findRoomList(nextPage, pageSize, filterParams);
	}
	/**
	 * @Title: findRoomNEStatusPage
	 * @Description: 分页查询Room	 * @param nextPage
	 * @param pageSize
	 * @param status		//默认查询非删除3的数据,具体类型参照XaConstant.Status
	 * @param sortDate
	 * @param jsonFilter
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findRoomNEStatusPage",method=RequestMethod.POST)
	public XaResult<Page<Room>> findRoomNEStatusPage(
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
		return roomService.findListNEStatusByFilter(status, filterParams, pageable);
	}
	
	/**
	 * @Title: findRoomEQStatusPage
	 * @Description: (预留)分页查询Room	 * @param nextPage
	 * @param pageSize
	 * @param status		//默认查询正常状态1的数据,具体类型参照XaConstant.Status
	 * @param sortDate
	 * @param jsonFilter
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findRoomEQStatusPage",method=RequestMethod.POST)
	public XaResult<Page<Room>> findRoomEQStatusPage(
		@RequestParam(defaultValue = "0") Integer nextPage,
		@RequestParam(defaultValue = "10") Integer pageSize,
		@RequestParam(defaultValue = "1") Integer status,
		@RequestParam(defaultValue = "[{property:'createTime',direction:'DESC'}]") String sortData,
		@RequestParam(defaultValue = "{}") String jsonFilter
	) throws BusinessException{
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, sortData);
		Map<String,Object> filterParams =  WebUitl.getParametersStartingWith(jsonFilter, "search_");
		return roomService.findListEQStatusByFilter(status, filterParams, pageable);
	}
	
	/**
	 * @Title: findRoomById
	 * @Description: 根据ID查找单条实体
	 * @param modelId
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findRoomById",method=RequestMethod.POST)
	public XaResult<Room> findRoomById(
		@RequestParam Long modelId
	) throws BusinessException{
		return roomService.findOne(modelId);
	}
	/**
	 * @Title: saveModel
	 * @Description: 保存实体数据
	 * @param model
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="saveRoom",method=RequestMethod.POST)
	public XaResult<Room> saveRoom(
		Room model
	) throws BusinessException{
		return roomService.saveOrUpdate(model);
	}
	
	/**
	 * @Title: multiOperateRoomByIds
	 * @Description: 批量操作多个实体状态,根据status进行操作
	 * @param modelIds    	多个实体id,中间用逗号隔开
	 * @param status 		操作类型,status类型见XaConstant.Status,默认删除操作
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="operateRoomByIds",method=RequestMethod.POST)
	public XaResult<Room> operateRoomByIds(
		HttpServletRequest request,
		@RequestParam String modelIds,
		@RequestParam(defaultValue = "3") Integer status
	) throws BusinessException{
		return roomService.multiOperate(modelIds,status);
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
		String picturePath = "/upload/room";
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
	/**
	 * @Title: findRoomOrTicketsList
	 * @Description:web端商品列表显示(门票或者房间)
	 * @param id 酒店或者景点的id
	 * author:yanghui  2016-01-11
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findRoomOrTicketsList",method=RequestMethod.POST)
	public XaResult<Page<RoomVo>> findRoomOrTicketsList(
		HttpServletRequest request,
		@RequestParam Long id,
		@RequestParam(defaultValue = "0") Integer nextPage,
		@RequestParam(defaultValue = "6") Integer pageSize,
		@RequestParam int businessType,
		@RequestParam Integer status
	) throws BusinessException{
		return roomService.findRoomOrTicketsList(nextPage,pageSize,id,businessType,status);
	}
	/**
	 * @Title: upAnddown
	 * @Description:前台商品的上架或者下架
	 * @param id 酒店或者景点的id
	 * @param businessType 用户类型
	 * @param modelIds id
	 * author:yanghui  2016-01-12
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="upAnddown",method=RequestMethod.POST)
	public XaResult<RoomVo> upAnddown(
		HttpServletRequest request,
		@RequestParam int businessType,
		@RequestParam Integer status,
		@RequestParam String modelIds,
		@RequestParam  Long id
	) throws BusinessException{
		return roomService.upanddown(modelIds, businessType, status, id);
	}
	/**
	 * @Title: saveGoods
	 * @Description: 前端保存实体数据(有团购时)
	 * @param model
	 *  @param id  酒店id
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="saveGoods",method=RequestMethod.POST)
	public XaResult<Room> saveGoods(
		@RequestParam("hotelId") Long hotelId,
		@RequestParam("photo") String photo,
		@RequestParam("groupPrice") Double groupPrice,
		@RequestParam("validity") String validity,
		Room model,
		HttpServletRequest request
	) throws BusinessException{
		return roomService.saveandUpdate(model, hotelId,photo,request,groupPrice,validity);
	}
	/**
	 * @Title: saveGoods
	 * @Description: 前端保存实体数据(无团购时)
	 * @param model
	 *  @param id  酒店id
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="saveGoodsNoGroup",method=RequestMethod.POST)
	public XaResult<Room> saveGoodsNoGroup(
		@RequestParam("hotelId") Long hotelId,
		@RequestParam("photo") String photo,
		Room model,
		HttpServletRequest request
	) throws BusinessException{
		return roomService.saveandUpdateNoGroup(model, hotelId,photo,request);
	}
	/**
	 * @Title: findById
	 * @Description: web端根据ID查找房间详情
	 * @param modelId
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findById",method=RequestMethod.POST)
	public XaResult<RoomVo> findById(
		@RequestParam Long modelId
	) throws BusinessException{
		return roomService.findOneRoom(modelId);
	}
	/**
	 * @Title: findBusinessShopList
	 * @Description: 分页查询Room	 * @param nextPage
	 * @param pageSize
	 * @param sortDate
	 * @param jsonFilter
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findBusinessShopList",method=RequestMethod.POST)
	public XaResult<Page<RoomVo>> findBusinessShopList(
		@RequestParam(defaultValue = "0") Integer nextPage,
		@RequestParam(defaultValue = "10") Integer pageSize,
		@RequestParam(defaultValue = "[{property:'createTime',direction:'DESC'}]") String sortData,
		//过滤字段,字段名:jsonFilter,选填,默认:{},示例:{'search_EQ_field1':'value1','search_EQ_field2':'value2'},字段名称拼接规则search_为固定查询标识,EQ为等于,filed为字段名
		//EQ等于, IN包含, ISNULL空, LIKE, GT大于, LT小于, GTE大于等于, LTE小于等于, NE不等于,LIKEIGNORE非like 
		@RequestParam(defaultValue = "{}") String jsonFilter
	) throws BusinessException{
		Map<String,Object> filterParams =  WebUitl.getParametersStartingWith(jsonFilter, "search_");
		return roomService.findBusinessShopList(nextPage, pageSize, filterParams);
	}
	/**
	 * @Title: upAnddown
	 * @Description:后台商品的上架或者下架或者审批
	 * @param id 酒店或者景点的id
	 * @param businessType 用户类型
	 * @param modelIds id
	 * author:yanghui  2016-01-26
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="upAnddownback",method=RequestMethod.POST)
	public XaResult<RoomVo> upAnddownback(
		HttpServletRequest request,
		@RequestParam String roommodelIds,
		@RequestParam String ticketsmodelIds,
		@RequestParam  String btnVar
	) throws BusinessException{
		return roomService.upAnddownback(roommodelIds, ticketsmodelIds, btnVar);
	}
}

