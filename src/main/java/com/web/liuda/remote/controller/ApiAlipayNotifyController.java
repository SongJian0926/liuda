package com.web.liuda.remote.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.util.AlipayNotify;
import com.web.liuda.remote.service.ApiOrderService;
import com.web.liuda.remote.service.ApiRefundOrderService;
import com.web.webstart.base.controller.BaseController;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * @Title: ApiAlipayNotifyController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 支付宝异步通知
 * @author zhanglin
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "alipay", description = "支付宝", position = 10)
@Controller
@RequestMapping("/api/alipay")
public class ApiAlipayNotifyController extends BaseController {
	
	@Autowired
	ApiOrderService orderService;
	@Autowired
	ApiRefundOrderService refundOrderService;
	
	/**
	 * @Title: notify
	 * @Description: 异步通知
	 * @return    
	 */
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="异步通知",notes="异步通知")
	@ResponseBody
	@RequestMapping(value="notify",method=RequestMethod.POST)
	public void notify(
		HttpServletRequest request,
		HttpServletResponse reponse
	) throws Exception{
		reponse.reset();
		PrintWriter out = reponse.getWriter();
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		if(AlipayNotify.verify(params)){//验证成功
			//交易状态
			String trade_status = params.get("trade_status");
			//交易成功后执行的业务代码
			if(trade_status.equals("TRADE_FINISHED")){
				
			} else if (trade_status.equals("TRADE_SUCCESS")){//支付成功后执行的业务代码
				String out_trade_no = params.get("out_trade_no");
				String total_fee = params.get("total_fee");
				String trade_no = params.get("trade_no");//支付宝交易号(退款用)
				orderService.modifyOrder(out_trade_no,total_fee,trade_no);
			}
			out.println("success");	//请不要修改或删除
		}else{//验证失败
			out.println("fail");
		}
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="异步通知",notes="异步通知")
	@ResponseBody
	@RequestMapping(value="notifyRefund",method=RequestMethod.POST)
	public void notifyRefund(
		HttpServletRequest request,
		HttpServletResponse reponse
	) throws Exception{
		reponse.reset();
		PrintWriter out = reponse.getWriter();
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//批次号

		String batch_no = new String(request.getParameter("batch_no").getBytes("ISO-8859-1"),"UTF-8");

		//批量退款数据中转账成功的笔数

		String success_num = new String(request.getParameter("success_num").getBytes("ISO-8859-1"),"UTF-8");

		//批量退款数据中的详细信息
		String result_details = new String(request.getParameter("result_details").getBytes("ISO-8859-1"),"UTF-8");

		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if(AlipayNotify.verify(params)){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			
			//判断是否在商户网站中已经做过了这次通知返回的处理
				//如果没有做过处理，那么执行商户的业务程序
				//如果有做过处理，那么不执行商户的业务程序
			
			refundOrderService.refundSuccess(batch_no);
				
			out.print("success");	//请不要修改或删除

			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			out.print("fail");
		}
		
	}
}
