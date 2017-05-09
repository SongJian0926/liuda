package com.alipay;

import java.util.HashMap;
import java.util.Map;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;
import com.alipay.util.UtilDate;

public class PayService {
	
//	//退款接口地址
//	public static String REFUND_ORDER = "https://openapi.alipay.com/gateway.do";
	
    /**
     * 请求退款订单接口
     * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    
    public static String refund(PayReqRefundData data) throws Exception {
    	Map<String, String> sParaTemp = new HashMap<String, String>();
    	sParaTemp.put("service", AlipayConfig.service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("notify_url", data.getNotify_url());
		sParaTemp.put("seller_user_id", AlipayConfig.partner);
		sParaTemp.put("refund_date", UtilDate.getDateFormatter());
		sParaTemp.put("batch_no", data.getBatch_no());
		sParaTemp.put("batch_num", data.getBatch_num());
		sParaTemp.put("detail_data", data.getDetail_data());
		
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
		return sHtmlText;
		
//    	//把请求参数打包成数组
//		Map<String, String> sParaTemp = new HashMap<String, String>();
//		sParaTemp.put("app_id", data.getApp_id());
//        sParaTemp.put("method", data.getMethod());
//        sParaTemp.put("charset", data.getCharset());
//		//sParaTemp.put("sign_type", data.getSign_type());
//		sParaTemp.put("timestamp", data.getTimestamp());
//		sParaTemp.put("version", data.getVersion());
//		sParaTemp.put("out_trade_no", data.getOut_trade_no());
//		sParaTemp.put("refund_amount", data.getRefund_amount());
//		sParaTemp.put("out_request_no", data.getOut_request_no());
//		System.out.println("请求参数:"+sParaTemp);
//		//建立请求
//		String sHtmlText = AlipaySubmit
//		return sHtmlText;
    }
    	

	
}
