package com.web.liuda.business.controller;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.web.liuda.business.entity.Order;
import com.web.liuda.business.service.OrderService;
import com.web.liuda.remote.vo.OrderVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.WebUitl;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Title: OrderController.java
 * @Package com.web.webstart.business.controller
 * @Description: 订单控制器
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Controller
@RequestMapping("/cms/order")
public class OrderController extends BaseController {

	@Autowired
	private OrderService orderService;
	
	
	/**
	 * @Title: findOrder
	 * author：changlu
	 * time:2016-01-14 16:40:00
	 * @Description: pc端校验兑换码	
	 * @param businessId 
	 * @param businessType		
	 * @param orderNo
	 * @param orderStatus
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findOrder",method=RequestMethod.POST)
	public XaResult<List<OrderVo>> findOrder(
		@RequestParam(value="businessId") Long businessId,
		@RequestParam(value="businessType") Integer businessType,
		@RequestParam(value="orderNo") String orderNo,
		@RequestParam(value="orderStatus") Integer orderStatus
	) throws BusinessException{
		return orderService.findOrder(businessId,businessType,orderNo, orderStatus);
	}
	/**
	 * @Title: findOrderList
	 * author：changlu
	 * time:2016-01-14 13:40:00
	 * @Description: pc端订单管理列表	 * @param nextPage
	 * @param pageSize
	 * @param status		//默认查询非删除3的数据,具体类型参照XaConstant.Status
	 * @param sortDate
	 * @param jsonFilter
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findOrderList",method=RequestMethod.POST)
	public XaResult<Page<OrderVo>> findOrderList(
		@RequestParam(defaultValue = "0") Integer nextPage,
		@RequestParam(defaultValue = "10") Integer pageSize,
		@RequestParam(defaultValue = "[{property:'createTime',direction:'DESC'}]") String sortData,
		//过滤字段,字段名:jsonFilter,选填,默认:{},示例:{'search_EQ_field1':'value1','search_EQ_field2':'value2'},字段名称拼接规则search_为固定查询标识,EQ为等于,filed为字段名
		//EQ等于, IN包含, ISNULL空, LIKE, GT大于, LT小于, GTE大于等于, LTE小于等于, NE不等于,LIKEIGNORE非like 
/*		@RequestParam(value="businessId") Long businessId,
		@RequestParam(value="businessType") Integer businessType,*/
		@RequestParam(defaultValue = "{}") String jsonFilter
	) throws BusinessException{
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, sortData);
		Map<String,Object> filterParams =  WebUitl.getParametersStartingWith(jsonFilter, "search_");
		return orderService.findOrderListByFilter(filterParams, pageable);
	}
	/**
	 * @Title: findOrderList
	 * author：changlu
	 * time:2016-01-25 14:40:00
	 * @Description: 后台财务统计列表	 * @param nextPage
	 * @param pageSize
	 * @param status		//默认查询非删除3的数据,具体类型参照XaConstant.Status
	 * @param sortDate
	 * @param jsonFilter
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findAccountList",method=RequestMethod.POST)
	public XaResult<Page<OrderVo>> findAccountList(
		@RequestParam(defaultValue = "0") Integer nextPage,
		@RequestParam(defaultValue = "10") Integer pageSize,
		@RequestParam(defaultValue = "[{property:'createTime',direction:'DESC'}]") String sortData,
		//过滤字段,字段名:jsonFilter,选填,默认:{},示例:{'search_EQ_field1':'value1','search_EQ_field2':'value2'},字段名称拼接规则search_为固定查询标识,EQ为等于,filed为字段名
		//EQ等于, IN包含, ISNULL空, LIKE, GT大于, LT小于, GTE大于等于, LTE小于等于, NE不等于,LIKEIGNORE非like 
/*		@RequestParam(value="businessId") Long businessId,
		@RequestParam(value="businessType") Integer businessType,*/
		@RequestParam(defaultValue = "{}") String jsonFilter
	) throws BusinessException{
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, sortData);
		Map<String,Object> filterParams =  WebUitl.getParametersStartingWith(jsonFilter, "search_");
		return orderService.findAccountList(filterParams, pageable);
	}
	/**
	 * @Title: findExchangeNEStatusPage
	 * @Description: 分页查询兑换码	 * @param nextPage
	 * @param pageSize
	 * @param status		//默认查询非删除3的数据,具体类型参照XaConstant.Status
	 * @param sortDate
	 * @param jsonFilter
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findExchangeNEStatusPage",method=RequestMethod.POST)
	public XaResult<Page<OrderVo>> findExchangeNEStatusPage(
			@RequestParam(defaultValue = "0") Integer nextPage,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "3") Integer status,
			@RequestParam(defaultValue = "[{property:'createTime',direction:'DESC'}]") String sortData,
			//过滤字段,字段名:jsonFilter,选填,默认:{},示例:{'search_EQ_field1':'value1','search_EQ_field2':'value2'},字段名称拼接规则search_为固定查询标识,EQ为等于,filed为字段名
			//EQ等于, IN包含, ISNULL空, LIKE, GT大于, LT小于, GTE大于等于, LTE小于等于, NE不等于,LIKEIGNORE非like 
			@RequestParam(defaultValue = "{}") String jsonFilter	
		)throws BusinessException{
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, sortData);
		Map<String,Object> filterParams =  WebUitl.getParametersStartingWith(jsonFilter, "search_");
		return orderService.findExchangeNEStatusPage(pageable,filterParams);
	}
	/**
	 * @Title: findOrderNEStatusPage
	 * @Description: 分页查询Order	 * @param nextPage
	 * @param pageSize
	 * @param status		//默认查询非删除3的数据,具体类型参照XaConstant.Status
	 * @param sortDate
	 * @param jsonFilter
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findOrderNEStatusPage",method=RequestMethod.POST)
	public XaResult<Page<Order>> findOrderNEStatusPage(
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
		return orderService.findListNEStatusByFilter(status, filterParams, pageable);
	}
	
	/**
	 * @Title: findOrderEQStatusPage
	 * @Description: (预留)分页查询Order	 * @param nextPage
	 * @param pageSize
	 * @param status		//默认查询正常状态1的数据,具体类型参照XaConstant.Status
	 * @param sortDate
	 * @param jsonFilter
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findOrderEQStatusPage",method=RequestMethod.POST)
	public XaResult<Page<Order>> findOrderEQStatusPage(
		@RequestParam(defaultValue = "0") Integer nextPage,
		@RequestParam(defaultValue = "10") Integer pageSize,
		@RequestParam(defaultValue = "1") Integer status,
		@RequestParam(defaultValue = "[{property:'createTime',direction:'DESC'}]") String sortData,
		@RequestParam(defaultValue = "{}") String jsonFilter
	) throws BusinessException{
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, sortData);
		Map<String,Object> filterParams =  WebUitl.getParametersStartingWith(jsonFilter, "search_");
		return orderService.findListEQStatusByFilter(status, filterParams, pageable);
	}
	/**
	 * @Title: findOrderDetail
	 * @Description: 根据ID查找单条实体
	 * @param modelId
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findOrderDetail",method=RequestMethod.POST)
	public XaResult<List<OrderVo>> findOrderDetail(
		@RequestParam Integer businessType,
		@RequestParam String orderNo
	) throws BusinessException{
		return orderService.findOrderDetailById(businessType,orderNo);
	}
	/**
	 * @Title: findOrderById
	 * @Description: 根据ID查找单条实体
	 * @param modelId
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findOrderById",method=RequestMethod.POST)
	public XaResult<OrderVo> findOrderById(
		@RequestParam Long modelId,
		@RequestParam Integer orderType
	) throws BusinessException{
		return orderService.findOne(modelId, orderType);
	}
	/**
	 * @Title: saveModel
	 * @Description: 保存实体数据
	 * @param model
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="saveOrder",method=RequestMethod.POST)
	public XaResult<Order> saveOrder(
		Order model
	) throws BusinessException{
		return orderService.saveOrUpdate(model);
	}
	/**
	 * @Title: updateOrder
	 * @Description: 发货之后修改订单
	 * @param model
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="updateOrder",method=RequestMethod.POST)
	public XaResult<Boolean> updateOrder(
	  @RequestParam	String ids,
	  @RequestParam String address,
	  @RequestParam	String sentData
	) throws BusinessException{
		XaResult<Boolean>  xr=new XaResult<Boolean>();
		if(XaUtil.isEmpty(sentData)){
			xr.setMessage("订单编号不能为空");
			return xr;
		}
		String[] arr=ids.split(",");
		return orderService.updateOrder(arr,address,sentData);
	}
	/**
	 * @Title: multiOperateOrderByIds
	 * @Description: 批量操作多个实体状态,根据status进行操作
	 * @param modelIds    	多个实体id,中间用逗号隔开
	 * @param status 		操作类型,status类型见XaConstant.Status,默认删除操作
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="operateOrderByIds",method=RequestMethod.POST)
	public XaResult<Order> operateOrderByIds(
		HttpServletRequest request,
		@RequestParam String modelIds,
		@RequestParam(defaultValue = "3") Integer status
	) throws BusinessException{
		return orderService.multiOperate(modelIds,status);
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
		String picturePath = "/upload/order";
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
	 * @Title: upload
	 * @Description: 文件下载
	 * @param photoFile
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping(value = "fileDownload", method = RequestMethod.GET)
	public void fileDownload(
			@RequestParam(defaultValue = "{}") String jsonFilter,
			HttpServletResponse response) throws BusinessException {
		try {
			jsonFilter = URLDecoder.decode(jsonFilter, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String, Object> filterParams = WebUitl.getParametersStartingWith(
				jsonFilter, "search_");
		orderService.exportdata(filterParams, response);
	}

	/**
	 * @Title: upload
	 * @Description: 文件下载
	 * @param photoFile
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping(value = "fileDownloadMatchTickets", method = RequestMethod.GET)
	public void fileDownloadMatchTickets(
			@RequestParam(defaultValue = "{}") String jsonFilter,
			HttpServletResponse response) throws BusinessException {
		try {
			jsonFilter = URLDecoder.decode(jsonFilter, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String, Object> filterParams = WebUitl.getParametersStartingWith(jsonFilter, "search_");
		orderService.exportdataMatchTickets(filterParams, response);
	}

	/**
	 * @Title: updateRredeemCode
	 * @Description: 兑换码校验
	 * @param matchId    	赛事ID
	 * @param redeemCode 	兑换码
	 * @return    
	 */
	
	@ResponseBody
	@RequestMapping(value="updateRredeemCode",method=RequestMethod.POST)
	public XaResult<Order> updateRredeemCode(
		HttpServletRequest request,
		@RequestParam Long matchId,
		@RequestParam String redeemCode
	) throws BusinessException{
		return orderService.updateRredeemCode(matchId,redeemCode);
	}
	
}

