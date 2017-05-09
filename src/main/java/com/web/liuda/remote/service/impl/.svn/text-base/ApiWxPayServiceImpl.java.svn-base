package com.web.liuda.remote.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.liuda.business.repository.OrderRepository;
import com.web.liuda.remote.service.ApiWxPayService;
import com.web.liuda.remote.vo.WxPayVo;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wxpay.PayReqData;
import com.wxpay.PayService;
import com.wxpay.common.Configure;
import com.wxpay.common.Signature;
import com.wxpay.common.XMLParser;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：微信支付参数获取实现类
 **/
@Service("ApiWxPayService")
@Transactional(readOnly = false)
public class ApiWxPayServiceImpl implements ApiWxPayService{
	
	@Autowired
	OrderRepository orderRepository;

	@Override
	public XaResult<WxPayVo> getPayParams(String orderNo,String orderPrice,String nofity_url,String ip) throws Exception{
		XaResult<WxPayVo> xr = new XaResult<WxPayVo>();
		String nonce_str = XaUtil.generateRandomCharAndNumber(16);
		PayReqData data = new PayReqData();
		data.setAppid(Configure.getAppID());
    	data.setMch_id(Configure.getMchID());
    	data.setNonce_str(nonce_str);
    	data.setBody(orderNo);
    	//data.setAttach("深圳分店");
    	data.setOut_trade_no(orderNo);
    	data.setTotal_fee(Integer.valueOf(orderPrice));
    	data.setSpbill_create_ip(ip);
    	data.setNotify_url(nofity_url);
    	data.setTrade_type(Configure.getTrade_type());
    	data.setSign(Signature.getSign(data));
    	//请求数据获取结果
    	String str = PayService.request(data);
    	Map<String,Object> map = XMLParser.getMapFromXML(str);
    	//验证是否成功
    	if("SUCCESS".equals(map.get("result_code")) && "SUCCESS".equals(map.get("return_code"))){
    		//重新生成签名后返回给APP
    		Long timeStamp = new Date().getTime() / 1000;
    		Map<String,Object> signData = new HashMap<String,Object>();
    		signData.put("appid", map.get("appid"));
    		signData.put("partnerid", map.get("mch_id"));
    		signData.put("prepayid", map.get("prepay_id"));
    		signData.put("noncestr", nonce_str);
    		signData.put("timestamp", timeStamp);
    		signData.put("package", "Sign=WXPay");
    		String sign = Signature.getSign(signData);
    		//将结果封装到WxPayVo返回给APP
    		WxPayVo pay = new WxPayVo();
    		pay.setPrepayid((String)map.get("prepay_id"));
    		pay.setNonceStr(nonce_str);
    		pay.setTimeStamp(timeStamp + "");
    		pay.setSign(sign);
    		xr.setObject(pay);
    	}else{
    		xr.error("统一下单失败！");
    		return xr;
    	}
		return xr;
	}

	@Override
	public Boolean getTradeByReturn(String nonce_str,
			String transaction_id) throws Exception{
		//请求参数
		PayReqData data = new PayReqData();
		data.setAppid(Configure.getAppID());
    	data.setMch_id(Configure.getMchID());
    	data.setNonce_str(nonce_str);
    	data.setTransaction_id(transaction_id);
    	data.setSign(Signature.getSign(data));
    	String str = PayService.queryRequest(data);
    	Map<String,Object> map = XMLParser.getMapFromXML(str);
		if(XaUtil.isNotEmpty(map.get("return_code")) && map.get("return_code").equals("SUCCESS")){
			if(XaUtil.isNotEmpty(map.get("return_msg")) && map.get("return_msg").equals("OK")){
				return true;
			}
		}
		return false;
	}

}
