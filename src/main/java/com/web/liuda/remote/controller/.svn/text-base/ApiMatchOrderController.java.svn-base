package com.web.liuda.remote.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.MatchOrder;
import com.web.liuda.remote.service.ApiClubEventService;
import com.web.liuda.remote.service.ApiMatchOrderDetailService;
import com.web.liuda.remote.service.ApiMatchOrderService;
import com.web.liuda.remote.service.ApiMatchService;
import com.web.liuda.remote.service.ApiRefundOrderService;
import com.web.liuda.remote.vo.ClubEventVo;
import com.web.liuda.remote.vo.MacthVo;
import com.web.liuda.remote.vo.MatchOrderDetailVo;
import com.web.liuda.remote.vo.MatchOrderVo;
import com.web.liuda.remote.vo.RefundOrderVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.GetRandomOrderno;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Title: ApiMatchOrderController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 参赛订单主表信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "MatchOrder", description = "参赛订单主表接口(二期)", position = 10)
@Controller
@RequestMapping("/api/matchOrder")
public class ApiMatchOrderController extends BaseController {

	@Autowired
	private ApiMatchOrderService matchOrderService;
	@Autowired
	private ApiMatchOrderDetailService matchOrderDetailService;
	@Autowired
	private ApiMatchService matchService;
	@Autowired
	private ApiClubEventService clubEventService;
	@Autowired
	private ApiRefundOrderService refundOrderService;
	
	/**
	 * @Title: findMatchOrderList
	 * @Description: 查询所有参赛订单主表信息
	 * @return    
	 */
	/*@ApiOperation(value="查询所有参赛订单主表",notes="查询所有参赛订单主表信息,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findMatchOrderList",method=RequestMethod.POST)
	public XaResult<List<MatchOrderVo>> findMatchOrderList(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize
	) throws BusinessException{
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");
		Map<String , Object> filterParams =  WebUitl.getParametersStartingWith("{}", "search_");
		return matchOrderService.findListEQStatusByFilter(XaConstant.Status.valid, filterParams, pageable);
	}*/
	
	/**
	 * @Title: findMatchOrderById
	 * @Description: 赛事、活动订单详情
	 * @param modelId
	 * author:changlu
	 * time:2016-04-20 15:07:00
	 * @return    
	 */
	@ApiOperation(value="赛事、活动订单详情",notes="赛事、活动订单详情,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findMatchOrderById",method=RequestMethod.POST)
	public XaResult<MatchOrderVo> findMatchOrderById(
		@ApiParam("订单Id,字段名:matchOrderId,必填") @RequestParam(value = "matchOrderId") Long matchOrderId,
		@ApiParam("类型,字段名:type;1，赛事活动；2，俱乐部活动") @RequestParam(value = "type") Integer type
	) throws BusinessException{
		XaResult<MatchOrderVo> xr=new XaResult<MatchOrderVo> ();
		if(XaUtil.isEmpty(matchOrderId)){
			xr.error("订单Id不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(type)){
			xr.error("订单Id不能为空！");
			return xr;
		}
		return matchOrderService.findMatchOrderDetailById(matchOrderId,type);
	}
	/**
	 * @Title: save
	 * @Description: 活动、赛事下单
	 * @param userId
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="活动、赛事下单",notes="活动、赛事下单,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="saveMatchOrder",method=RequestMethod.POST)
	public XaResult<MatchOrderVo> saveMatchOrder(
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId,
		@ApiParam("赛事Id,字段名:matchId") @RequestParam(value = "matchId") Long matchId,
		@ApiParam("报名Id,字段名:matchOrderTempIds,多个id时以逗号分隔开") @RequestParam(value = "matchOrderTempIds") String matchOrderTempIds,
		@ApiParam("订单数量,字段名:orderNumber,几个人报名就是订单数量就是几") @RequestParam(value = "orderNumber") Integer orderNumber,
		@ApiParam("订单类型,字段名:type;1.参加赛事订单,2.俱乐部活动订单") @RequestParam(value = "type") Integer type,
		@ApiParam("总金额,字段名:totalAmt") @RequestParam(value = "totalAmt") Double totalAmt,
		@ApiParam("支付方式,字段名:payType;0.微信,1.支付宝,2.银联") @RequestParam(value = "payType") Integer payType,
		@ApiParam("是否全款支付,字段名:isFull;0.定金支付,1.全款支付 ") @RequestParam(value = "isFull") Integer isFull,
		@ApiParam("线上支付金额,字段名:onlineAmt") @RequestParam(value = "onlineAmt") Double onlineAmt,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<MatchOrderVo> xr=new XaResult<MatchOrderVo>();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}else if(userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(matchId)){
			xr.error("赛事或者活动Id不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(matchOrderTempIds)){
			xr.error("报名Id不能为空！");
			return xr;
		}
		if(orderNumber<1){
			xr.error("订单数量不能小于1！");
			return xr;
		}
		
		if(XaUtil.isEmpty(type)){
			xr.error("订单类型不能为空！");
			return xr;
		}
		if(type!=1&&type!=2){
			xr.error("订单类型只能是1或者2！");
			return xr;
		}
		MatchOrder model = new MatchOrder();
		model.setUserId(userId);
		model.setMatchId(matchId);
		model.setOrderNumber(orderNumber);
		model.setOrderStatus(JConstant.MatchOrderStatus.UNPAY);
		model.setType(type);
		model.setTotalAmt(totalAmt);
		model.setPayType(payType);
		model.setIsFull(isFull);
		model.setOnlineAmt(onlineAmt);
		model.setOrderNo(GetRandomOrderno.getRandomString(16));
		model.setOfflineAmt(totalAmt-onlineAmt);
		return matchOrderService.createMatchOrder(matchOrderTempIds,model);
	}
	
	/**
	 * @Title: cancel
	 * @Description: 活动、赛事取消订单
	 * @param userId
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="活动、赛事取消订单",notes="活动、赛事取消订单,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="cancelMatchOrder",method=RequestMethod.POST)
	public XaResult<MatchOrderDetailVo> cancelMatchOrderDetail(
		@ApiParam("用户Id,字段名:userId") @RequestParam(required=true,value = "userId") Long userId,
		@ApiParam("报名明细Id,字段名:matchOrderDetailId") @RequestParam(required=true,value = "matchOrderDetailId") Long matchOrderDetailId,
		@ApiParam("取消原因,字段名:reason") @RequestParam(required=false,value = "reason") String reason,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<MatchOrderDetailVo> xr=new XaResult<MatchOrderDetailVo>();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(matchOrderDetailId)){
			xr.error("报名明细Id不能为空！");
			return xr;
		}
		//判断该报名明细是否存在并且判断用户
		xr = matchOrderDetailService.findOne(matchOrderDetailId);
		if(xr.getCode()!=XaConstant.Code.success)
		{
			return xr;
		}
		MatchOrderDetailVo modvo = xr.getObject();
		//判断状态，判断是否已经申请过了
		if(modvo.getIsValid().equals(XaConstant.Status.valid))//没有申请中的状态？
		{
			xr.error("该订单已核销");
			return xr;
		}
		RefundOrderVo rfovo = refundOrderService.findRefundOrderByOrderDetailId(XaConstant.Status.valid, matchOrderDetailId).getObject();
		if(rfovo!=null)
		{
			xr.error("该订单已经申请过取消了");
			return xr;
		}
		MatchOrderVo movo = matchOrderService.findOne(modvo.getMatchOrderId()).getObject();
		if(!movo.getUserId().equals(userId))
		{
			xr.error("没有权限操作此订单");
			return xr;
		}
		String deadline = null;
		//判断时间是否在截止日期之前
		if(movo.getType().equals(JConstant.MatchOrderType.MATCHORDER))
		{
			MacthVo mvo = matchService.findOne(modvo.getMatchId()).getObject();
			deadline = mvo.getDeadline();
		}
		else if(movo.getType().equals(JConstant.MatchOrderType.EVENTORDER))
		{
			ClubEventVo cevo = clubEventService.findOne(modvo.getMatchId()).getObject();
			deadline = cevo.getDeadline();
		}
		if(deadline.length()==10) deadline+=" 00:00";
		Date ddl = XaUtil.formatDateString2Date(deadline,"yyyy-MM-dd HH:mm");
		if(ddl==null)
		{
			xr.error("日期格式转换错误{"+deadline+"}，应为{yyyy-MM-dd HH:mm}");
			return xr;
		}
		if(ddl.compareTo(new Date())<0)
		{
			xr.error("不允许取消订单");
			return xr;
		}
		return matchOrderDetailService.cancelMatchOrderDetail(movo,matchOrderDetailId,reason);
	}
	/**
	 * @Title: operateMatchOrderById
	 * @Description: 支付时选择支付方式
	 * @return    
	 */
	@ApiOperation(value="支付时选择支付方式",notes="支付时选择支付方式,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="updatePayType",method=RequestMethod.POST)
	public XaResult<MatchOrderVo> updatePayType(
		HttpServletRequest request,
		@ApiParam("订单编号,字段名:orderNo,必填") @RequestParam(value = "orderNo") String orderNo,
		@ApiParam("支付方式,字段名:payType;0.微信,1.支付宝,2.银联，必填") @RequestParam(value = "payType") Integer payType 
	) throws BusinessException{
		XaResult<MatchOrderVo> xr=new XaResult<MatchOrderVo>();
		if(XaUtil.isEmpty(orderNo)){
			xr.error("订单号不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(payType)){
			xr.error("支付方式不能为空！");
			return xr;
		}
		if(payType!=0&&payType!=1&&payType!=2){
			xr.error("支付方式只能是“0、1、2”！");
			return xr;
		}
		MatchOrder model=new MatchOrder();
		model.setOrderNo(orderNo);
		model.setPayType(payType);
		return matchOrderService.updatePayType(model);
	}
	/**
	 * @Title: payeMatchOrder
	 * @Description: 活动、赛事支付
	 * @param userId
	 * @param model
	 * @return    
	 *//*
	@ApiOperation(value="活动、赛事支付",notes="活动、赛事支付,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="saveMatchOrder",method=RequestMethod.POST)
	public XaResult<MatchOrderVo> payeMatchOrder(
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId,
		@ApiParam("赛事Id,字段名:matchId") @RequestParam(value = "matchId") Long matchId,
		@ApiParam("报名Id,字段名:matchOrderTempIds,多个id时以逗号分隔开") @RequestParam(value = "matchOrderTempIds") String matchOrderTempIds,
		@ApiParam("订单数量,字段名:orderNumber") @RequestParam(value = "orderNumber") Integer orderNumber,
		@ApiParam("订单Id,字段名:matchOrderId") @RequestParam(value = "matchOrderId") Long matchOrderId,
		@ApiParam("总金额,字段名:totalAmt") @RequestParam(value = "totalAmt") Double totalAmt,
		@ApiParam("支付方式,字段名:payType;0.微信,1.支付宝,2.银联") @RequestParam(value = "payType") Integer payType,
		@ApiParam("是否全款支付,字段名:isFull;0.定金支付,1.全款支付 ") @RequestParam(value = "isFull") Integer isFull,
		@ApiParam("线上支付金额,字段名:onlineAmt") @RequestParam(value = "onlineAmt") Double onlineAmt,
		//@ApiParam("订单类型,字段名:type;1.参加赛事订单,2.俱乐部活动订单") @RequestParam(value = "type") Integer type,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<MatchOrderVo> xr=new XaResult<MatchOrderVo>();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}else if(userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(matchId)){
			xr.error("赛事或者活动Id不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(matchOrderTempIds)){
			xr.error("报名Id不能为空！");
			return xr;
		}
		if(orderNumber<1){
			xr.error("订单数量不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(matchOrderId)){
			xr.error("订单Id不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(payType)){
			xr.error("支付方式不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(isFull)){
			xr.error("是否全款支付不能为空！");
			return xr;
		}
		if(isFull!=1&&isFull!=2){
			xr.error("是否全款支付只能是0或者1！");
			return xr;
		}
		if(XaUtil.isEmpty(type)){
			xr.error("订单类型不能为空！");
			return xr;
		}
		if(type!=1&&type!=2){
			xr.error("订单类型只能是1或者2！");
			return xr;
		}
		MatchOrder model = new MatchOrder();
		model.setId(matchOrderId);
		model.setTotalAmt(totalAmt);
		model.setPayType(payType);
		model.setIsFull(isFull);
		model.setOnlineAmt(onlineAmt);
		return matchOrderService.payMatchOrder(model);
	}*/
	
	/**
	 * @Title: 
	 * @Description: 修改一条实体
	 * @param userId
	 * @param model
	 * @return    
	 */
	/*@ApiOperation(value="修改参赛订单主表",notes="修改参赛订单主表,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="updateMatchOrder",method=RequestMethod.POST)
	public XaResult<MatchOrderVo> updateMatchOrder(
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId,
		@ApiParam("赛事Id,字段名:matchId") @RequestParam(value = "matchId") Long matchId,
		@ApiParam("订单数量,字段名:orderNumber") @RequestParam(value = "orderNumber") Integer orderNumber,
		@ApiParam("订单状态,字段名:orderStatus") @RequestParam(value = "orderStatus") Integer orderStatus,
		@ApiParam("总金额,字段名:totalAmt") @RequestParam(value = "totalAmt") Double totalAmt,
		@ApiParam("退款金额,字段名:refundAmt") @RequestParam(value = "refundAmt") Double refundAmt,
		@ApiParam("支付方式,字段名:payType") @RequestParam(value = "payType") Integer payType,
		@ApiParam("TN,字段名:tradeNum") @RequestParam(value = "tradeNum") String tradeNum,
		@ApiParam("是否全款支付,字段名:isFull") @RequestParam(value = "isFull") Integer isFull,
		@ApiParam("线下支付金额 ,字段名:offlineAmt") @RequestParam(value = "offlineAmt") Double offlineAmt,
		@ApiParam("线上支付金额,字段名:onlineAmt") @RequestParam(value = "onlineAmt") Double onlineAmt,
		@ApiParam("订单类型,字段名:type") @RequestParam(value = "type") Integer type,
		@ApiParam("版本编号,字段名:tId") @RequestParam(value = "tId") Long tId,
	HttpServletRequest request
	) throws BusinessException{
		MatchOrder model = new MatchOrder();
		model.setUserId(userId);
		model.setMatchId(matchId);
		model.setOrderNumber(orderNumber);
		model.setOrderStatus(orderStatus);
		model.setTotalAmt(totalAmt);
		model.setRefundAmt(refundAmt);
		model.setPayType(payType);
		model.setTradeNum(tradeNum);
		model.setIsFull(isFull);
		model.setOfflineAmt(offlineAmt);
		model.setOnlineAmt(onlineAmt);
		model.setType(type);
		model.setId(tId);
		return matchOrderService.updateModel(model);
	}
	*/
	/**
	 * @Title: operateMatchOrderById
	 * @Description: 操作一个实体状态,根据status进行操作
	 * @param modelId
	 * @param status 		操作类型:-1锁定,0无效,1正常,2发布,3删除,默认删除操作
	 * @return    
	 */
	/*@ApiOperation(value="根据ID修改参赛订单主表状态",notes="修改参赛订单主表状态,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="operateMatchOrderById",method=RequestMethod.POST)
	public XaResult<MatchOrderVo> operateMatchOrderById(
		HttpServletRequest request,
		@ApiParam("编号,字段名:modelIds,必填") @RequestParam(value = "modelId") String modelIds,
		@ApiParam("操作类型,字段名:status,-1锁定,0无效,1正常,2发布,3删除,选填,默认删除操作") @RequestParam(defaultValue = "3") Integer status
	) throws BusinessException{
		return matchOrderService.multiOperate(modelIds,status);
	}*/
	
	/**
	 * @Title: upload
	 * @Description: 图片上传
	 * @param photoFile
	 * @param request
	 * @return    
	 */
/*	@ApiOperation(value="图片上传",notes="异步图片上传,返回上传图片的地址、宽、高")
	@ResponseBody
	@RequestMapping(value="photoUpload",method=RequestMethod.POST)
	public XaResult<Map<String, Object>> photoUpload(
		@ApiParam("上传的图片,字段名:photoFile,必填") @RequestParam(value = "photoFile") MultipartFile photoFile, 
		HttpServletRequest request
	) throws BusinessException{
		XaResult<Map<String, Object>> result = new XaResult<Map<String, Object>>();
		String root=request.getSession().getServletContext().getRealPath("/");
		String picturePath = "/upload/matchOrder";
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
	/*@ApiOperation(value="test",notes="test,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="test",method=RequestMethod.POST)
	public String test(
			@ApiParam("订单号,字段名:orderNo,必填") @RequestParam(value = "orderNo") String orderNo,
			@ApiParam("金额,字段名:price,必填") @RequestParam(value = "price") String price,
			HttpServletRequest request
		) throws BusinessException{
			
			return matchOrderService.modifyOrder1(orderNo, price,"4001732001201606016610687501");
	}		*/
}

