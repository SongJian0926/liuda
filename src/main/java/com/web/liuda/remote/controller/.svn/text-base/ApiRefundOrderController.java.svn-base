package com.web.liuda.remote.controller;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.WebUitl;
import com.web.liuda.remote.service.ApiRefundOrderService;
import com.web.liuda.remote.vo.RefundOrderVo;
import com.web.liuda.business.entity.RefundOrder;

/**
 * @Title: ApiRefundOrderController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 订单退款表信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "RefundOrder", description = "订单退款表接口", position = 10)
@Controller
@RequestMapping("/api/refundOrder")
public class ApiRefundOrderController extends BaseController {

	@Autowired
	private ApiRefundOrderService refundOrderService;
	
	/**
	 * @Title: findRefundOrderList
	 * @Description: 查询所有订单退款表信息
	 * @return    
	 */
	@ApiOperation(value="查询所有订单退款表",notes="查询所有订单退款表信息,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findRefundOrderList",method=RequestMethod.POST)
	public XaResult<List<RefundOrderVo>> findRefundOrderList(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize
	) throws BusinessException{
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");
		Map<String , Object> filterParams =  WebUitl.getParametersStartingWith("{}", "search_");
		return refundOrderService.findListEQStatusByFilter(XaConstant.Status.valid, filterParams, pageable);
	}
	
	/**
	 * @Title: findRefundOrderById
	 * @Description: 根据ID查找单条实体
	 * @param modelId
	 * @return    
	 */
	@ApiOperation(value="根据tId查询订单退款表",notes="查询订单退款表详细信息,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findRefundOrderById",method=RequestMethod.POST)
	public XaResult<RefundOrderVo> findRefundOrderById(
		@ApiParam("编号,字段名:modelId,必填") @RequestParam(value = "modelId") Long modelId
	) throws BusinessException{
		return refundOrderService.findOne(modelId);
	}
	
	/**
	 * @Title: save
	 * @Description: 新增一条实体
	 * @param userId
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="新增订单退款表",notes="供前端前期填充数据测试使用,上线后删除;新增订单退款表,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="saveRefundOrder",method=RequestMethod.POST)
	public XaResult<RefundOrderVo> saveRefundOrder(
		@ApiParam("订单Id,字段名:orderId") @RequestParam(value = "orderId") Long orderId,
		@ApiParam("订单详细Id,字段名:orderDetailId") @RequestParam(value = "orderDetailId") Long orderDetailId,
		@ApiParam("退款金额 ,字段名:refundAmt") @RequestParam(value = "refundAmt") Double refundAmt,
		@ApiParam("退款日期,字段名:refundDate") @RequestParam(value = "refundDate") String refundDate,
		@ApiParam("退款审核通过日期 ,字段名:refundApplyDate") @RequestParam(value = "refundApplyDate") String refundApplyDate,
		@ApiParam("退款状态,字段名:refundStatus") @RequestParam(value = "refundStatus") Integer refundStatus,
		@ApiParam("审核备注,字段名:memo") @RequestParam(value = "memo") String memo,
		@ApiParam("取消报名原因,字段名:reason") @RequestParam(value = "reason") String reason,
		HttpServletRequest request
	) throws BusinessException{
		RefundOrder model = new RefundOrder();
		model.setOrderId(orderId);
		model.setOrderDetailId(orderDetailId);
		model.setRefundAmt(refundAmt);
		model.setRefundDate(refundDate);
		model.setRefundApplyDate(refundApplyDate);
		model.setRefundStatus(refundStatus);
		model.setMemo(memo);
		model.setReason(reason);
		return refundOrderService.createModel(model);
	}
	
	/**
	 * @Title: 
	 * @Description: 修改一条实体
	 * @param userId
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="修改订单退款表",notes="修改订单退款表,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="updateRefundOrder",method=RequestMethod.POST)
	public XaResult<RefundOrderVo> updateRefundOrder(
		@ApiParam("订单Id,字段名:orderId") @RequestParam(value = "orderId") Long orderId,
		@ApiParam("订单详细Id,字段名:orderDetailId") @RequestParam(value = "orderDetailId") Long orderDetailId,
		@ApiParam("退款金额 ,字段名:refundAmt") @RequestParam(value = "refundAmt") Double refundAmt,
		@ApiParam("退款日期,字段名:refundDate") @RequestParam(value = "refundDate") String refundDate,
		@ApiParam("退款审核通过日期 ,字段名:refundApplyDate") @RequestParam(value = "refundApplyDate") String refundApplyDate,
		@ApiParam("退款状态,字段名:refundStatus") @RequestParam(value = "refundStatus") Integer refundStatus,
		@ApiParam("审核备注,字段名:memo") @RequestParam(value = "memo") String memo,
		@ApiParam("取消报名原因,字段名:reason") @RequestParam(value = "reason") String reason,
		@ApiParam("版本编号,字段名:tId") @RequestParam(value = "tId") Long tId,
	HttpServletRequest request
	) throws BusinessException{
		RefundOrder model = new RefundOrder();
		model.setOrderId(orderId);
		model.setOrderDetailId(orderDetailId);
		model.setRefundAmt(refundAmt);
		model.setRefundDate(refundDate);
		model.setRefundApplyDate(refundApplyDate);
		model.setRefundStatus(refundStatus);
		model.setMemo(memo);
		model.setReason(reason);
		model.setId(tId);
		return refundOrderService.updateModel(model);
	}
	
	/**
	 * @Title: operateRefundOrderById
	 * @Description: 操作一个实体状态,根据status进行操作
	 * @param modelId
	 * @param status 		操作类型:-1锁定,0无效,1正常,2发布,3删除,默认删除操作
	 * @return    
	 */
	@ApiOperation(value="根据ID修改订单退款表状态",notes="修改订单退款表状态,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="operateRefundOrderById",method=RequestMethod.POST)
	public XaResult<RefundOrderVo> operateRefundOrderById(
		HttpServletRequest request,
		@ApiParam("编号,字段名:modelIds,必填") @RequestParam(value = "modelId") String modelIds,
		@ApiParam("操作类型,字段名:status,-1锁定,0无效,1正常,2发布,3删除,选填,默认删除操作") @RequestParam(defaultValue = "3") Integer status
	) throws BusinessException{
		return refundOrderService.multiOperate(modelIds,status);
	}
	
	/**
	 * @Title: upload
	 * @Description: 图片上传
	 * @param photoFile
	 * @param request
	 * @return    
	 */
	@ApiOperation(value="图片上传",notes="异步图片上传,返回上传图片的地址、宽、高")
	@ResponseBody
	@RequestMapping(value="photoUpload",method=RequestMethod.POST)
	public XaResult<Map<String, Object>> photoUpload(
		@ApiParam("上传的图片,字段名:photoFile,必填") @RequestParam(value = "photoFile") MultipartFile photoFile, 
		HttpServletRequest request
	) throws BusinessException{
		XaResult<Map<String, Object>> result = new XaResult<Map<String, Object>>();
		String root=request.getSession().getServletContext().getRealPath("/");
		String picturePath = "/upload/refundOrder";
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
	}
}

