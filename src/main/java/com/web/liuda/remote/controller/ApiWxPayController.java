package com.web.liuda.remote.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.remote.service.ApiMatchOrderService;
import com.web.liuda.remote.service.ApiOrderService;
import com.web.liuda.remote.service.ApiWxPayService;
import com.web.liuda.remote.vo.WxPayVo;
import com.web.webstart.base.util.XaResult;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wxpay.common.XMLParser;

/**
 * @Title: ApiWxPayController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 微信支付相关接口
 * @author zhanglin
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "wxpay", description = "微信支付", position = 10)
@Controller
@RequestMapping("/api/wxpay")
public class ApiWxPayController {
	
	@Autowired
	ApiWxPayService wxPayService;
	
	@Autowired
	ApiOrderService orderService;
	
	@Autowired
	ApiMatchOrderService matchOrderService;
	
	@ApiOperation(value="获取支付参数",notes="获取支付参数")
	@ResponseBody
	@RequestMapping(value="getPayParams",method=RequestMethod.POST)
	public XaResult<WxPayVo> getPayParams(
		@ApiParam("订单号,字段名:orderNo") @RequestParam(value = "orderNo") String orderNo,
		@ApiParam("订单价格,字段名:total") @RequestParam(value = "total") String total,
		HttpServletRequest request
	) throws Exception{
		String ip = request.getRemoteAddr();
		String nofity_url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/liuda/api/wxpay/notify";
		return wxPayService.getPayParams(orderNo,total,nofity_url,ip);
	}
	
	/**
	 * @Title: notify
	 * @Description: 异步通知
	 * @return    
	 */
	@ApiOperation(value="异步通知",notes="异步通知")
	@ResponseBody
	@RequestMapping(value="notify",method=RequestMethod.POST)
	public void notify(
		HttpServletRequest request,
		HttpServletResponse reponse
	) throws Exception{
		Logger log = Logger.getLogger(ApiWxPayController.class);
		log.info("开始异步通知");
		InputStream inStream = request.getInputStream();
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inStream.close();
		String result = new String(outSteam.toByteArray(),"utf-8");
		Map<String, Object> map = null;
		try {
			map = XMLParser.getMapFromXML(result);
			for(Object keyValue : map.keySet()){
				log.info("获取的参数:"+keyValue+"="+map.get(keyValue));
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			log.info("获取参数错误！");
			return;
		}
		if (map.get("return_code")==null || !map.get("return_code").toString().equalsIgnoreCase("SUCCESS")){
			log.info("商户接收通知:return_code不能为空或者不正确");
			return;
		}
		if (map.get("result_code")==null || !map.get("result_code").toString().equalsIgnoreCase("SUCCESS")){
			log.info("业务结果:result_code不能为空或者不正确");
			return;
		}
		PrintWriter out = reponse.getWriter();
		String out_trade_no = map.get("out_trade_no").toString();
		String total_fee = map.get("total_fee").toString();
		String transaction_id = map.get("transaction_id").toString();
		String nonce_str = map.get("nonce_str").toString();
		//处理业务逻辑
		String str = orderService.modifyOrder(out_trade_no,total_fee,transaction_id);
		if("0000".equals(str)){
			out.write(this.setXML("SUCCESS", "OK"));
		}else{
			//如果系统发生异常，查询订单是否支付成功
			if("0002".equals(str)){
				if(wxPayService.getTradeByReturn(nonce_str, transaction_id)){
					out.write(this.setXML("SUCCESS", "OK"));
				}else{
					out.write(this.setXML("SUCCESS", ""));
				}
			}else{
				out.write(this.setXML("SUCCESS", ""));
			}
			out.write(this.setXML("SUCCESS", ""));
		}
		
	}
	
	private String setXML(String return_code, String return_msg) {
		return "<xml><return_code><![CDATA[" + return_code
			+ "]]></return_code><return_msg><![CDATA[" + return_msg
			+ "]]></return_msg></xml>";
	}

}
